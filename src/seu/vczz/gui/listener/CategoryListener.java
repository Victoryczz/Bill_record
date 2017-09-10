package seu.vczz.gui.listener;

import seu.vczz.entity.Category;
import seu.vczz.gui.panel.CategoryPanel;
import seu.vczz.service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener{


    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel categoryPanel = new CategoryPanel();

        JButton button = (JButton) e.getSource();
        //添加目录
        if (button == categoryPanel.bAdd){
            String name = JOptionPane.showInputDialog(null);
            //先做空判断
            if (name == null){
                return;
            }
            if (name.length() == 0){
                JOptionPane.showMessageDialog(categoryPanel, "分类名称不能为空");
                return;
            }
            new CategoryService().add(name);
        }
        //编辑目录
        if (button == categoryPanel.bEdit){
            Category category = categoryPanel.getSelectedCategory();
            int id = category.getId();
            String name = JOptionPane.showInputDialog("修改分类名称", category.getName());
            //费空判断
            if (name == null){
                return;
            }
            if (name.length() == 0){
                JOptionPane.showMessageDialog(categoryPanel, "分类名称不能为空");
                return;
            }
            new CategoryService().update(id, name);
        }
        //删除目录
        if (button == categoryPanel.bDelete){
            Category category = categoryPanel.getSelectedCategory();

            //费空判断
            if (category.getRecordNum() != 0){
                JOptionPane.showMessageDialog(categoryPanel, "本目录下存在消费记录，不能删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(categoryPanel, "确定删除？")){
                return;
            }
            int id = category.getId();
            new CategoryService().delete(id);
        }
        categoryPanel.updateData();
    }
}
