package seu.vczz.gui.panel;

import org.jdesktop.swingx.JXDatePicker;
import seu.vczz.entity.Category;
import seu.vczz.gui.listener.RecordListener;
import seu.vczz.gui.model.CategoryComboBoxModel;
import seu.vczz.util.ColorUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel{

    static {
        GUIUtil.useLNF();
    }
    //单例模式
    public static RecordPanel instance = new RecordPanel();

    JLabel lspend = new JLabel("花费");
    JLabel lcategory = new JLabel("条目");
    JLabel lcomment = new JLabel("备注");
    JLabel ldate = new JLabel("日期");

    public JTextField textFieldSpend = new JTextField("0");

    public CategoryComboBoxModel categoryComboBoxModel = new CategoryComboBoxModel();
    //comboBox的构造函数需要传递一个comboBoxModel类型
    public JComboBox<Category> categoryJComboBox = new JComboBox<Category>(categoryComboBoxModel);
    public JTextField textFieldComment = new JTextField();
    //第三方的一个日历包
    public JXDatePicker datePicker = new JXDatePicker(new Date());

    JButton bSubmit = new JButton("记录");

    public RecordPanel(){
        GUIUtil.setColor(ColorUtil.grayColor, lspend, lcategory, lcomment, ldate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        JPanel pInput = new JPanel();//记录输入框
        JPanel pSubmit = new JPanel();
        int gap = 40;//?

        pInput.setLayout(new GridLayout(4, 2, gap, gap));

        pInput.add(lspend);
        pInput.add(textFieldSpend);
        pInput.add(lcategory);
        pInput.add(categoryJComboBox);
        pInput.add(lcomment);
        pInput.add(textFieldComment);
        pInput.add(ldate);
        pInput.add(datePicker);

        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);

        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
}
