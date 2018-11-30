package top.vabook.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import top.vabook.dao.LendDao;
import top.vabook.dao.ScrapDao;
import top.vabook.util.DateUtil;
import top.vabook.util.ErrorUtil;
import top.vabook.util.MsgUtil;

//报废管理：报废日期，设备名，设备号，批准人
public class ScrapUI implements ActionListener{
	static JFrame jFrame;

	private static JLabel dateLabel, emNoLabel, emNameLabel, peopleLabel;

	private static Container container;

	private static JPanel panel, datePanel;

	private JComboBox<String> emNoBox, emNameBox, emPeopleBox;
	
	static String[] peopleNames = { "tom", "tomas", "tomcat", "mycat" };

	static String[] emNos = { "0", "1", "2", "3", "4", "5" };

	static String[] emNames = { "Computer", "Aircondition", "Projector", "Disk", "Sofa", "Cup" };
	
	private static Button submitButton, exitButton;
	
	private static Boolean flag = false;

	public ScrapUI() {
		
	}
	public void show() {
		jFrame = new JFrame("报废管理");
		jFrame.setSize(500, 300);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.setLocation(500, 300);

		container = jFrame.getContentPane();
		container.setLayout(new FlowLayout());

		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));

		dateLabel = new JLabel("报废日期");
		container.add(dateLabel);
		panel.add(dateLabel);
		datePanel = DateUtil.getDatePanel();
		container.add(datePanel);
		panel.add(datePanel);

		emNoLabel = new JLabel("设备号");
		container.add(emNoLabel);
		panel.add(emNoLabel);
		emNoBox = new JComboBox<>(emNos);
		container.add(emNoBox);
		panel.add(emNoBox);

		emNameLabel = new JLabel("设备名");
		container.add(emNameLabel);
		panel.add(emNameLabel);
		emNameBox = new JComboBox<>(emNames);
		container.add(emNameBox);
		panel.add(emNameBox);

		peopleLabel = new JLabel("批准人");
		container.add(peopleLabel);
		panel.add(peopleLabel);
		emPeopleBox= new JComboBox<>(peopleNames);
		container.add(emPeopleBox);
		panel.add(emPeopleBox);
		
		submitButton = new Button("提交");
		submitButton.setForeground(Color.BLUE);
//		submitButton.setBackground(Color.red);
		container.add(submitButton);
		panel.add(submitButton);
		submitButton.addActionListener(this);

		// 取消
		exitButton = new Button("退出");
		exitButton.setForeground(Color.BLUE);
		container.add(exitButton);
		panel.add(exitButton);
		exitButton.addActionListener(this);
		
		jFrame.add(panel);
		
		jFrame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		JComboBox yearBox = (JComboBox) datePanel.getComponent(0);
		JComboBox monthBox = (JComboBox) datePanel.getComponent(1);
		JComboBox dayBox = (JComboBox) datePanel.getComponent(2);
		String year = yearBox.getSelectedItem().toString();
		String month = monthBox.getSelectedItem().toString();
		String day = dayBox.getSelectedItem().toString();
		String date = year + "-" + month + "-" + day;
		
		String emNo = emNoBox.getSelectedItem().toString();
		String emName = emNameBox.getSelectedItem().toString();
		String people = emPeopleBox.getSelectedItem().toString();
		
		if (e.getSource() == submitButton) {
			flag = ScrapDao.insert(date,emNo,emName,people);
			MsgUtil.show(flag);
		}
		if (e.getSource() == exitButton) {
			jFrame.dispose();
		}
	}
	public void close() {
		jFrame.dispose();
	}
	
}
