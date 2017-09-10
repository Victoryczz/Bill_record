package seu.vczz.gui.model;

import seu.vczz.entity.Category;
import seu.vczz.service.CategoryService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * 组合table
 */
public class CategoryTableModel extends AbstractTableModel{

    String[] columnsNames = new String[]{"分类名称", "消费次数"};

    public List<Category> categories = new CategoryService().list();

    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return columnsNames.length;
    }
    //就是表格的获得第几行第几列的数字
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category category = categories.get(rowIndex);
        if (columnIndex == 0)
            return category.getName();
        if (columnIndex == 1)
            return category.getRecordNum();
        return null;
    }

    public String getColumnsName(int index) {
        return columnsNames[index];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
}
