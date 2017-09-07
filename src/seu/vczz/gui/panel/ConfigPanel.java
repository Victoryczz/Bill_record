package seu.vczz.gui.panel;

import seu.vczz.gui.listener.ConfigListener;
import seu.vczz.service.ConfigService;
import seu.vczz.util.ColorUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();

    JLabel lBudget = new JLabel("本月预算(元)");
    public JTextField textFieldBudget = new JTextField("0");

    JLabel lMySQL = new JLabel("安装目录");
    public JTextField textFieldMySQL = new JTextField("");

    JButton bSubmit = new JButton("更新");

    public ConfigPanel(){
        GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMySQL);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4,1, gap, gap));
        pInput.add(lBudget);
        pInput.add(textFieldBudget);
        pInput.add(lMySQL);
        pInput.add(textFieldMySQL);
        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);

        pSubmit.add(bSubmit);
        this.add(pSubmit, BorderLayout.CENTER);
        addListener();
    }


    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        textFieldBudget.setText(budget);
        textFieldMySQL.setText(mysqlPath);
        textFieldBudget.grabFocus();
    }

    @Override
    public void addListener() {
        ConfigListener listener = new ConfigListener();
        bSubmit.addActionListener(listener);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(ConfigPanel.instance);
    }
}
