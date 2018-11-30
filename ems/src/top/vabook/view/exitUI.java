package top.vabook.view;

import javax.swing.JTextField;

//退出本系统，恢复系统的实始状态
public class exitUI {

	public exitUI(JTextField nameField, JTextField passwordField, BuyUI buyUI, LendUI lendUI, RepairUI repairUI, StoreUI storeUI, ScrapUI scrapUI, UserUI userUI) {
		nameField.setText("");
		passwordField.setText("");
		
		//先判断是否点开了,再关闭
		if (buyUI.jFrame != null) {
			buyUI.close();
		}
		if (lendUI.jFrame != null) {
			lendUI.close();
		}
		if (repairUI.jFrame != null) {
			repairUI.close();
		}
		if (storeUI.jFrame != null) {
			storeUI.close();
		}
		if (scrapUI.jFrame != null) {
			scrapUI.close();
		}
	}
	
}
