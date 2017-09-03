package seu.vczz.util;

import javax.swing.*;

/**
 * 控件居中util
 */
public class CenterPanelUtil {
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
}
