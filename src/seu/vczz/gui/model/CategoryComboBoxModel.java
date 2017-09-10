package seu.vczz.gui.model;

import seu.vczz.entity.Category;
import seu.vczz.service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * 组合选择框
 */
public class CategoryComboBoxModel implements ComboBoxModel<Category>{
    //或得列表
    public List<Category> categories = new CategoryService().list();
    public Category category;

    public CategoryComboBoxModel(){
        if (!categories.isEmpty()){
            category = categories.get(0);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        category = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if (!categories.isEmpty())
            return category;
        else
            return null;
    }

    @Override
    public int getSize() {
        return categories.size();
    }

    @Override
    public Category getElementAt(int index) {
        return categories.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
