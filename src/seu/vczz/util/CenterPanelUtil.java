package seu.vczz.util;

import seu.vczz.gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 控件居中util
 */
public class CenterPanelUtil extends JPanel{
    private double rate;//拉伸比例
    private JComponent component;//显示拉伸组件
    private boolean strech;//是否拉伸

    public CenterPanelUtil(double rate, boolean strech) {
        this.rate = rate;
        this.strech = strech;
    }
    public CenterPanelUtil(double rate){
        this(rate, true);//调用上一个构造函数
    }

    @Override
    public void repaint() {
        if (component != null){
            Dimension containerSize = this.getSize();//尺寸
            Dimension componentSize = component.getSize();

            if (strech){
                component.setSize( (int)(containerSize.width*rate), (int) (containerSize.height*rate));
            }else {
                component.setSize(componentSize);
            }
            //矩形居中
            component.setLocation(containerSize.width / 2 - component.getSize().width / 2, containerSize.height / 2 - component.getSize().height / 2);
        }
        super.repaint();
    }

    public void show(JComponent component){
        this.component = component;
        Component[] cs = getComponents();
        for(Component c : cs){
            remove(c);
        }
        add(component);
        if (component instanceof WorkingPanel){
            ((WorkingPanel)component).updateData();
        }
        this.updateUI();
    }
}
