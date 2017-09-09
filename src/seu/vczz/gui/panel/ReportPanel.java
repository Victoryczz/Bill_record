package seu.vczz.gui.panel;

import seu.vczz.entity.Record;
import seu.vczz.service.ReportService;
import seu.vczz.util.ChartUtil;
import seu.vczz.util.GUIUtil;

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
        List<Record> recordList = new ReportService().listThisMonthRecords();
        Image image = ChartUtil.getImage(recordList, 400, 300);
        ImageIcon icon = new ImageIcon(image);
        label.setIcon(icon);
        this.add(label);
        addListener();
    }


    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        label.setIcon(icon);
    }

    @Override
    public void addListener() {

    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }
}
