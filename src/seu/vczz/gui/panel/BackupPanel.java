package seu.vczz.gui.panel;

import seu.vczz.util.ColorUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;

public class BackupPanel extends JPanel{
    static {
        GUIUtil.useLNF();
    }
    //难道是单例模式？
    public static BackupPanel instance = new BackupPanel();
    JButton backupButton = new JButton("备份");

    public BackupPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, backupButton);
        this.add(backupButton);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(BackupPanel.instance);
    }
}
