package seu.vczz.gui.panel;

import seu.vczz.gui.listener.BackupListener;
import seu.vczz.util.ColorUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;

/**
 * 备份面板
 */
public class BackupPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }
    //难道是单例模式？是
    public static BackupPanel instance = new BackupPanel();
    JButton backupButton = new JButton("备份");

    private BackupPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, backupButton);
        this.add(backupButton);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(BackupPanel.instance);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        backupButton.addActionListener(listener);
    }
}
