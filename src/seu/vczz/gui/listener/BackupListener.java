package seu.vczz.gui.listener;

import seu.vczz.gui.panel.BackupPanel;
import seu.vczz.gui.panel.ConfigPanel;
import seu.vczz.gui.panel.MainPanel;
import seu.vczz.service.ConfigService;
import seu.vczz.util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BackupListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel backupPanel = BackupPanel.instance;

        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (0 == mysqlPath.length()){
            JOptionPane.showMessageDialog(backupPanel, "请先配置好mysql路径");
            MainPanel.instance.workPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.textFieldMySQL.grabFocus();
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("sql/bill_record.sql"));
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal = fileChooser.showSaveDialog(backupPanel);
        File file = fileChooser.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            System.out.println(file);

            if (!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(), file.getName()+".sql");
            System.out.println(file);
            try {
                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(backupPanel, "备份成功，备份文件位于:\r\n"+file.getAbsolutePath());
            }catch (Exception e1){
                e1.printStackTrace();
                JOptionPane.showMessageDialog(backupPanel, "备份失败\r\n,错误\r\n"+e1.getMessage());
            }

        }

    }
}
