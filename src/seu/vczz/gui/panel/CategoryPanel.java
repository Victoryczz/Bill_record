package seu.vczz.gui.panel;

import seu.vczz.entity.Category;
import seu.vczz.gui.listener.CategoryListener;
import seu.vczz.gui.model.CategoryTableModel;
import seu.vczz.service.CategoryService;
import seu.vczz.util.ColorUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class CategoryPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPanel instance = new CategoryPanel();

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    String[] columNames = new String[]{"分类名称", "消费次数"};

    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable table = new JTable((TableModel) ctm);

    public CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
        JScrollPane scrollPane = new JScrollPane();
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pSubmit, BorderLayout.SOUTH);

        addListener();
    }

    public Category getSelectedCategory(){
        int index = table.getSelectedRow();
        return ctm.categories.get(index);
    }
    @Override
    public void updateData() {
        ctm.categories = new CategoryService().list();
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0, 0);

        if (ctm.categories.size() == 0){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }else {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    @Override
    public void addListener() {
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(CategoryPanel.instance);
    }


}
