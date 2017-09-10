package seu.vczz.startup;

import seu.vczz.gui.frame.MainFrame;
import seu.vczz.gui.panel.MainPanel;
import seu.vczz.gui.panel.SpendPanel;
import seu.vczz.util.GUIUtil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstrap {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

        GUIUtil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workPanel.show(SpendPanel.instance);
            }
        });
    }

}
