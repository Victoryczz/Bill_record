package seu.vczz.gui.listener;

import seu.vczz.gui.panel.*;

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
            p.workPanel.show(ConfigPanel.instance);
        if (b == p.bRecord)
            p.workPanel.show(RecordPanel.instance);
        if (b == p.bRecover)
            p.workPanel.show(RecoverPanel.instance);
        if (b == p.bReport)
            p.workPanel.show(ReportPanel.instance);
        if (b == p.bSpend)
            p.workPanel.show(SpendPanel.instance);
    }
}
