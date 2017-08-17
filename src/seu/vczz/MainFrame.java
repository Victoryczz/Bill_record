package seu.vczz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.tools.Tool;

public class MainFrame {

	public static void main(String[] args) {
		//面板
		JFrame frame = new JFrame();
		frame.setSize(1000, 618);
		frame.setTitle("记账");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//工具栏
		JToolBar toolBar = new JToolBar();
		JButton bSpend = new JButton("消费一笔");
		JButton bRecord = new JButton("记一笔");
		JButton bCategory = new JButton("消费分类");
        JButton bReport = new JButton("月消费报表");
        JButton bConfig = new JButton("设置");
        JButton bBackup = new JButton("备份");
        JButton bRecover = new JButton("恢复");
        
        toolBar.add(bSpend);
        toolBar.add(bRecord);
        toolBar.add(bCategory);
        toolBar.add(bReport);
        toolBar.add(bConfig);
        toolBar.add(bBackup);
        toolBar.add(bRecover);
        toolBar.setOrientation(1);
        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.WEST);
        frame.add(new JPanel(), BorderLayout.CENTER);
        
        
		frame.setVisible(true);
		
		
	}

}
