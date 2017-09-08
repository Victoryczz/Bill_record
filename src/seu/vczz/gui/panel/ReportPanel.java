package seu.vczz.gui.panel;

import seu.vczz.entity.Record;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportPanel extends WorkingPanel {
//    static {
//        GUIUtil.useLNF();
//    }
//
    public static ReportPanel instance = new ReportPanel();

    JLabel label = new JLabel();

    public ReportPanel(){
        this.setLayout(new BorderLayout());
        List<Record> recordList;
    }


    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {

    }
}
