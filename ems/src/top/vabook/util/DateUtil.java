package top.vabook.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DateUtil {

	private static int startYear = 1990;

	private static int endYear = 2020;

	private static JComboBox<Integer> yearBox, monthBox, dayBox;

	private static JPanel datePanel;

	public static JPanel getDatePanel() {
		yearBox = new JComboBox<>();
		monthBox = new JComboBox<>();
		dayBox = new JComboBox<>();
		for (int i = startYear; i < endYear; i++) {
			yearBox.addItem(i);
		}
		for (int j = 1; j < 13; j++) {
			monthBox.addItem(j);
		}

		for (int k = 1; k < 31; k++) {
			dayBox.addItem(k);
		}

		datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
//		datePanel.setLayout(new BorderLayout());
		datePanel.setSize(156, 35);

		datePanel.add(yearBox);
		datePanel.add(monthBox);
		datePanel.add(dayBox);

		return datePanel;

	}

	/*public static void main(String[] args) {
		JPanel j = DateUtil.getDatePanel();

		JComboBox yearBox = (JComboBox) j.getComponent(0);
		JComboBox monthBox = (JComboBox) j.getComponent(1);
		JComboBox dayBox = (JComboBox) j.getComponent(2);
		String year = yearBox.getSelectedItem().toString();
		String month = monthBox.getSelectedItem().toString();
		String day = dayBox.getSelectedItem().toString();
		System.out.println(year + "." + month + "." + day);
	}*/

}
