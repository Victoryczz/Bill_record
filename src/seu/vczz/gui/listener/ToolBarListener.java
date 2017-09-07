package seu.vczz.gui.listener;

import seu.vczz.gui.panel.BackupPanel;
import seu.vczz.gui.panel.CategoryPanel;
import seu.vczz.gui.panel.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel p = MainPanel.instance;
        JButton b = (JButton) e.getSource();

        if (b == p.bBackup)
            p.workPanel.show(BackupPanel.instance);
        if (b == p.bCategory)
            p.workPanel.show(CategoryPanel.instance);
        if(b == p.bConfig)
            p.workPanel.show();
    }
}
