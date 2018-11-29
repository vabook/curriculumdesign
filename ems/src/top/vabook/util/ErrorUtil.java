package top.vabook.util;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorUtil {
	
	private static JFrame jFrame = new JFrame("信息提示");

	private static JLabel jLabel;
	
	public static void show(String msg) {
		jFrame.setSize(400, 300);
		jFrame.setLocation(500, 300);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.setVisible(true);
		jLabel = new JLabel(msg,jLabel.CENTER);
		
		jFrame.add(jLabel);
	}

}
