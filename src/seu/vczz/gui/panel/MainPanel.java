package seu.vczz.gui.panel;

import seu.vczz.gui.listener.ToolBarListener;
import seu.vczz.util.CenterPanelUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static MainPanel instance = new MainPanel();
    //各种按键和toolbar
    public JToolBar toolBar = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();
    //工作面板
    public CenterPanelUtil workPanel;

    public MainPanel() {
        GUIUtil.setImgIcon(bSpend, "home.png", "消费总览");
        GUIUtil.setImgIcon(bRecord, "record.png", "记录一下");
        GUIUtil.setImgIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImgIcon(bConfig, "config.png", "设置");
        GUIUtil.setImgIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImgIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImgIcon(bRecover, "restore.png", "恢复");

        toolBar.add(bSpend);
        toolBar.add(bCategory);
        toolBar.add(bRecord);
        toolBar.add(bReport);
        toolBar.add(bConfig);
        toolBar.add(bBackup);
        toolBar.add(bRecover);
        //浮动
        toolBar.setFloatable(false);
        //workingPanel缩放比例0.8
        workPanel = new CenterPanelUtil(0.8);
        //布局设置边界，toolbar在上，workingPanel在中间
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(workPanel, BorderLayout.CENTER);


        addListener();
    }

    public void addListener(){
        ToolBarListener toolbarlistener = new ToolBarListener();
        bSpend.addActionListener(toolbarlistener);
        bCategory.addActionListener(toolbarlistener);
        bRecord.addActionListener(toolbarlistener);
        bReport.addActionListener(toolbarlistener);
        bBackup.addActionListener(toolbarlistener);
        bRecover.addActionListener(toolbarlistener);
        bConfig.addActionListener(toolbarlistener);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
