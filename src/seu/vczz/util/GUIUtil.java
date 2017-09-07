package seu.vczz.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {
    private static String imgFolder = "E:\\Intellij_IDEA_PROJECT\\Bill_record\\img";
    //设置button的图标、文字以及文字tip的相对位置
    public static void setImgIcon(JButton button, String fileName, String tip){
        ImageIcon imageIcon = new ImageIcon(new File(imgFolder, fileName).getAbsolutePath());
        button.setIcon(imageIcon);
        button.setToolTipText(tip);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setText(tip);
    }
    public static void setColor(Color color, JComponent... cs){
        for (JComponent c : cs){
            c.setForeground(color);
        }
    }

    public static void showPanel(JPanel panel, double strechRate){
        GUIUtil.useLNF();
        JFrame frame = new JFrame();
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        CenterPanelUtil centerPanelUtil = new CenterPanelUtil(strechRate);
        frame.setContentPane(centerPanelUtil);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        centerPanelUtil.show(panel);

    }
    //默认为0.75
    public static void showPanel(JPanel panel){
        showPanel(panel, 0.75);
    }
    //检查输入是否是数字
    public static boolean checkNum(JTextField textField, String input){
        if(checkEmpty(textField, input)){
            return false;
        }
        String text = textField.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, input+"需要整数");
            textField.grabFocus();
            return false;
        }
    }
    //检查输入框是否为零
    public static boolean checkZero(JTextField textField, String input){
        if (!checkNum(textField, input)){
            return false;
        }
        return true;
    }
    //检查输入是否为空，是空就返回false
    public static boolean checkEmpty(JTextField textField, String input){
        String text = textField.getText().trim();
        if (0 == text.length()){
            JOptionPane.showMessageDialog(null, input+"不能为空");
            textField.grabFocus();
            return true;
        }
        return false;
    }
    public static int getInt(JTextField textField){
        return Integer.parseInt(textField.getText().trim());
    }

    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
