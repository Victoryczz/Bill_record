package seu.vczz.gui.panel;

import seu.vczz.gui.listener.RecoverListener;
import seu.vczz.util.ColorUtil;
import seu.vczz.util.GUIUtil;

import javax.swing.*;

public class RecoverPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static RecoverPanel instance = new RecoverPanel();

    JButton bRecover = new JButton("恢复");

    private RecoverPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);

        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(RecoverPanel.instance);
    }
}
