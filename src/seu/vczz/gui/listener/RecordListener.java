package seu.vczz.gui.listener;

import seu.vczz.gui.panel.CategoryPanel;
import seu.vczz.gui.panel.MainPanel;
import seu.vczz.gui.panel.RecordPanel;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel recordPanel = RecordPanel.instance;

        if (recordPanel.categoryComboBoxModel.categories.size() == 0){
            JOptionPane.showMessageDialog(recordPanel, "暂无消费分类，请添加");
            MainPanel.instance.workPanel.show(CategoryPanel.instance);
            return;
        }

        if (!GUIUtil.checkZero(recordPanel.textFieldSpend, "花费金额"))
            return;

    }
}
