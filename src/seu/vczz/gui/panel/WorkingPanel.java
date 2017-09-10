package seu.vczz.gui.panel;

import javax.swing.*;

/**
 * 主面板
 */
public abstract class WorkingPanel extends JPanel{
    public abstract void updateData();
    public abstract void addListener();
}
