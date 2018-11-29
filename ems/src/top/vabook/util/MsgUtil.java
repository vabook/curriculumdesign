package top.vabook.util;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MsgUtil {
	private static JFrame jFrame = new JFrame("操作提示框");
	
	
	private static JLabel jLabel;
	
	private static String errMsg = "发生错误,请重试";
	
	private static String sucMsg = "操作成功,请继续...";
	

	public static void show(boolean flag) {
		
		jFrame.setSize(300, 200);
		
		if (flag) {
			jLabel = new JLabel(sucMsg,JLabel.CENTER);
		}else {
			jLabel = new JLabel(errMsg,JLabel.CENTER);
		}
		
		jFrame.add(jLabel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
		jFrame.setLocation(500, 300);
	}
	
}
