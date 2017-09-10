package seu.vczz.gui.listener;

import seu.vczz.entity.Category;
import seu.vczz.gui.panel.CategoryPanel;
import seu.vczz.gui.panel.MainPanel;
import seu.vczz.gui.panel.RecordPanel;
import seu.vczz.gui.panel.SpendPanel;
import seu.vczz.service.RecordService;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
        int spend = Integer.parseInt(recordPanel.textFieldSpend.getText());
        Category category = recordPanel.getSelectedCategory();
        String comment = recordPanel.textFieldComment.getText();
        Date date = recordPanel.datePicker.getDate();
        new RecordService().add(spend, category, comment, date);
        JOptionPane.showMessageDialog(recordPanel, "添加成功");

        MainPanel.instance.workPanel.show(SpendPanel.instance);
    }
}
