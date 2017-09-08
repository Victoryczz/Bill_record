package seu.vczz.util;

import java.io.*;

public class MysqlUtil {
    //备份
    public static void backup(String mysqlPath, String backupFile){
        //命令行的命令，应该是直接调用本地mysqldump.EXE执行
        //格式化
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d %s -r \"%s\"";

        String command = String.format(commandFormat, mysqlPath, DBUtil.username, DBUtil.password, DBUtil.port, DBUtil.database, backupFile);
        //还能这么用，执行命令吧
        try {
            //执行一个进程
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //恢复
    public static void recover(String mysqlPath, String recoverFile){
        try {
            String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
            String command = String.format(commandFormat, mysqlPath, DBUtil.username, DBUtil.password, DBUtil.database);
            //创建一个进程，执行
            Process process = Runtime.getRuntime().exec(command);
            OutputStream out = process.getOutputStream();
            String inStr;
            String outStr;
            StringBuffer sb = new StringBuffer("");
            //包装类，要好好看
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile), "utf8"));
            while ((inStr = br.readLine())!= null){
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
