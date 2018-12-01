package top.vabook.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import top.vabook.dao.StoreDao;
import top.vabook.dao.UserDao;
import top.vabook.domain.StoreEm;
import top.vabook.domain.User;
import top.vabook.util.MsgUtil;
import top.vabook.util.QueryUtil;

//包括查询设备数据信息与用户登陆系统和修改密码
public class UserUI implements ActionListener {

	static JFrame jFrame;

	private static Container container;

	private static JLabel findInNameLabel, userLabel, changePasswordLabel,errorLabel;

	private static JTable emTable, userTable;

	private static DefaultTableModel emTableModel, userTableModel;

	private static TableRowSorter<TableModel> sorter;

	private static JTextField textField,nameField,passwordField,passwordLastField1,passwordLastField2;

	private static JPanel panel,panel1, panel2,panel3, emPanel, userPanel, emtablePanel, usertablePanel;
	
	//至关重要的滑动面板
	private static JScrollPane jScrollPane;

	private static Button queryButton,userSubmitButton,cancleButton;

	private static String[] emColumns = { "设备号", "设备名", "设备状态", "数量" };

	private static String[][] data = {};
	
	private static List<StoreEm> storeEms,queryEms;
	
	private StoreDao storeDao;
	
	
	private List<User> users;
	
	private static Boolean usernameFlag = false;
	private static Boolean passwordFlag = false;
	private static Boolean newPasswordFlag = false;
	private static Boolean againPasswordFlag = false;
	

	public UserUI() {
		
	}
	public void show() {

		jFrame = new JFrame("用户管理");
		jFrame.setSize(700, 620);
		jFrame.setLocation(400, 200);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		container = jFrame.getContentPane();
		container.setLayout(new FlowLayout());

		panel = new JPanel();
		//设置后,panel1,panel2,panel3会平分组件的高度
//		panel.setPreferredSize(new Dimension(620, 480));
		panel.setLayout(new GridLayout(3, 1));
		
		// panel1承载了查询设备,以及查询结果
		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(400, 250));
		panel1.setLayout(new GridLayout(2, 1));
		

		// emPanel 承载查询
		emPanel = new JPanel();
		emPanel.setPreferredSize(new Dimension(380, 30));
		emPanel.setLayout(new FlowLayout());

		// 查询
		findInNameLabel = new JLabel("查找设备名");
		container.add(findInNameLabel);
		emPanel.add(findInNameLabel, new FlowLayout(FlowLayout.LEFT));

		textField = new JTextField("", 15);
		container.add(textField);
		emPanel.add(textField, new FlowLayout(FlowLayout.LEFT));

		queryButton = new Button("查询");
		container.add(queryButton);
		emPanel.add(queryButton, new FlowLayout(FlowLayout.LEFT));
		queryButton.addActionListener(this);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		container.add(errorLabel);
		emPanel.add(errorLabel,new FlowLayout(FlowLayout.LEFT));

		// emTablePanel 承载设备查询结果
		emtablePanel = new JPanel();
		emtablePanel.setPreferredSize(new Dimension(340, 210));
		emtablePanel.setLayout(new BorderLayout());

		// 设备表
		emTableModel = new DefaultTableModel(data, emColumns);
		emTable = new JTable(emTableModel);
		emTable.setSize(new Dimension(330, 200));
		sorter = new TableRowSorter<TableModel>(emTable.getModel());
		emTable.setRowSorter(sorter);
		
		emtablePanel.add(emTable);
		jScrollPane = new JScrollPane(emTable);
		jScrollPane.setPreferredSize(new Dimension(330, 200));
		emtablePanel.add(jScrollPane);
		
		panel1.add(emPanel);
		panel1.add(emtablePanel);

		
		/**
		 * 设置系统用户
		 * 
		 * 系统用户密码修改
		 * 请在此处输入用户名
		 * 请输入原始密码
		 * 请输入新密码
		 * 请再次输入新密码
		 */
		
		
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300, 280));
		
		users = UserDao.query();
		
		userPanel = new JPanel();
		userPanel.setPreferredSize(new Dimension(290, 180));
		userPanel.setLayout(new GridLayout(5, 1));
		
		userLabel = new JLabel("系统用户密码修改");
		container.add(userLabel);
		userPanel.add(userLabel);
		
		nameField = new JTextField("请在此处输入用户名");
		nameField.setForeground(Color.red);
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = nameField.getText();
				
					if (!QueryUtil.searchUserName(string, users)) {
						nameField.setText("没有此用户");
					}else {
						usernameFlag = true;
					}
				
			}
			@Override
			//有作用
			public void focusGained(FocusEvent e) {
				String string = nameField.getText();
				if ("请在此处输入用户名".equals(string) || "没有此用户".equals(string)) {
					nameField.setText("");
				}
			}
		});
		nameField.addActionListener(this);
		container.add(nameField);
		userPanel.add(nameField);
		
		passwordField = new JTextField("请输入原始密码");
		passwordField.setForeground(Color.red);
		passwordField.addFocusListener(new FocusListener() {
			
			//用户输入的密码
			@Override
			public void focusLost(FocusEvent e) {
				String string = passwordField.getText();
				//查询数据库中的密码是否一致,直接显示
				users = UserDao.query();
				if (!QueryUtil.searchPassword(string, users)) {
					passwordField.setText("请输入正确的密码");
				}else {
					passwordFlag = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				String string = passwordField.getText();
				if ("请输入正确的密码".equals(string) || "请输入原始密码".equals(string)) {
					passwordField.setText("");
				}
			}
		});
		passwordField.addActionListener(this);
		container.add(passwordField);
		userPanel.add(passwordField);
		
		passwordLastField1 = new JTextField("请输入新密码");
		passwordLastField1.setForeground(Color.red);
		passwordLastField1.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = passwordLastField1.getText();
				if (string.isEmpty()) {
					passwordLastField1.setText("请输入新密码");
				}
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				String string = passwordLastField1.getText();
				if ("请输入新密码".equals(string)) {
					passwordLastField1.setText("");
				}
			}
		});
		passwordLastField1.addActionListener(this);
		container.add(passwordLastField1);
		userPanel.add(passwordLastField1);
		
		passwordLastField2 = new JTextField("请再次输入新密码");
		passwordLastField2.setForeground(Color.red);
		passwordLastField2.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = passwordLastField2.getText();
				if (string.isEmpty()) {
					passwordLastField2.setText("请再次输入新密码");
				}
				if (!string.equals(passwordLastField1.getText())) {
					passwordLastField2.setText("两次密码不一致");
				}else {
					againPasswordFlag = true;
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				String string = passwordLastField2.getText();
				if ("请再次输入新密码".equals(string) || "两次密码不一致".equals(string)) {
					passwordLastField2.setText("");
				}
				
			}
		});
		
		
		
		panel2.add(userPanel);
		
		panel.add(panel1);
		panel.add(panel2);
		
		container.add(passwordLastField2);
		userPanel.add(passwordLastField2);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2));
		panel3.setPreferredSize(new Dimension(180, 40));
		
		userSubmitButton = new Button("修改");
		container.add(userSubmitButton);
		panel3.add(userSubmitButton);
		userSubmitButton.addActionListener(this);
		
		cancleButton = new Button("取消修改");
		container.add(cancleButton);
		panel3.add(cancleButton);
		cancleButton.addActionListener(this);
		
		
		jFrame.add(panel);
		jFrame.add(panel3);
		jFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			
			//每一次查询时都置空
			emTableModel.setRowCount(0);
			String emName = textField.getText();  
			if (!emName.isEmpty()) {
				
				//设置提示消息
				errorLabel.setText("正在查询...");
				
				storeDao = new StoreDao();
				//查询全部结果
				storeEms = storeDao.query();
				//筛选
				queryEms =  QueryUtil.searchInName(emName, storeEms);
				if (queryEms.size() > 0) {
					for(StoreEm store :queryEms) {
						emTableModel.addRow(store.covertArray());
					}
					errorLabel.setText("查询成功");
				}else {
					errorLabel.setText("没有此设备");
				}
				
			}else {
				errorLabel.setText("请输入设备名!");
			}
		}
		
		if (e.getSource() == userSubmitButton) {
			String newName = nameField.getText();
			String newpassword = passwordLastField1.getText();
			MsgUtil.show((UserDao.update(newName, newpassword)));
			
		}
		if (e.getSource() == cancleButton) {
			nameField.setText("请在此处输入用户名");
			passwordField.setText("请输入原始密码");
			passwordLastField1.setText("请输入新密码");
			passwordLastField2.setText("请再次输入新密码");
		}
	}

	public void close() {
		jFrame.dispose();
	}
}
