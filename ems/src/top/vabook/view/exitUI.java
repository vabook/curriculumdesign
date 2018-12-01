package top.vabook.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

//退出本系统，恢复系统的实始状态
public class exitUI {

	public exitUI(JTextField nameField, JTextField passwordField, BuyUI buyUI, LendUI lendUI, RepairUI repairUI, StoreUI storeUI, ScrapUI scrapUI, UserUI userUI, JLabel errorLaebl) {
		nameField.setText("");
		passwordField.setText("");
		errorLaebl.setText("请先登录");
		//先判断是否点开了,再关闭
		if (BuyUI.jFrame != null) {
			buyUI.close();
		}
		if (LendUI.jFrame != null) {
			lendUI.close();
		}
		if (repairUI.jFrame != null) {
			repairUI.close();
		}
		if (storeUI.jFrame != null) {
			storeUI.close();
		}
		if (ScrapUI.jFrame != null) {
			scrapUI.close();
		}
		if (UserUI.jFrame != null) {
			userUI.close();
		}
	}
	
}
