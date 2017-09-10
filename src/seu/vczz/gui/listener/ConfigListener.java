package seu.vczz.gui.listener;

import seu.vczz.gui.panel.ConfigPanel;
import seu.vczz.service.ConfigService;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel configPanel = ConfigPanel.instance;
        //预算是不是数字，有没有填
        if (!GUIUtil.checkNum(configPanel.textFieldBudget, "本月预算"))
            return;
        String mysqlPath = configPanel.textFieldMySQL.getText();
        if (0 != mysqlPath.length()){
            //设置mysql路径干什么，都有了系统变量，这里存疑
            File commandFile = new File(mysqlPath, "bin/mysql.exe");
            if (!commandFile.exists()){
                JOptionPane.showMessageDialog(configPanel, "mysql路径不正确");
                configPanel.textFieldMySQL.grabFocus();
                return;
            }
        }
        //更新
        ConfigService configService = new ConfigService();
        configService.update(ConfigService.budget, configPanel.textFieldBudget.getText());
        configService.update(ConfigService.mysqlPath, mysqlPath);
        JOptionPane.showMessageDialog(configPanel, "设置修改成功");
    }
}
