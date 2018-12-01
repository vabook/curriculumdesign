package top.vabook.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import top.vabook.dao.UserDao;
import top.vabook.domain.User;
import top.vabook.util.ErrorUtil;
import top.vabook.util.QueryUtil;

public class LoginUI implements ActionListener {
	static String title = "欢迎登陆设备管理系统";
	private static JFrame jFrame = new JFrame(title);

	// 其他功能模块
	public static JFrame buyFrame, lendFrame, repairFrame, scrapFrame, storeFrame, userFrame;

	// 其他功能模块
	public static BuyUI buyUI;
	public static LendUI lendUI;
	public static RepairUI repairUI;
	public static ScrapUI scrapUI;
	public static StoreUI storeUI;
	public static UserUI userUI;

	// 用户登陆
	private static JLabel nameLabel, passwordLabel, errorLaebl;

	private static JPanel loginPanel, namePanel, passPanel, functionPanel;

	// 设置为静态,退出系统时作为参数传递,可以直接改变其值
	public static JTextField nameField, passwordField;

	private static JButton loginButton;
	// 容器
	private static Container container;

	private static boolean login_success = false;

	private static boolean NoExitFlag = true;
	// 七个功能模块,先用按钮做出来
	private static Button buyButton, lendButton, repairButton, storeButton, scrapButton, userButton, exitButton;

	// 初始化
	public LoginUI() {

		buyUI = new BuyUI();
		lendUI = new LendUI();
		repairUI = new RepairUI();
		storeUI = new StoreUI();
		scrapUI = new ScrapUI();
		userUI = new UserUI();

		// 对JFrame进行设置
		jFrame.setSize(598, 500);
		jFrame.setLocation(350, 150);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 对jFrame容器设置
		container = jFrame.getContentPane();
		// 布局设置,居中对齐,默认5单元水平和垂直间隙
		container.setLayout(new FlowLayout());

		// 登陆面板,构造一个新的边界布局，组件之间没有间隙
		namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
//		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		passPanel = new JPanel();
		passPanel.setLayout(new BorderLayout());
//		passPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		// 设置了浮动效果
//		loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// 用户名
		nameLabel = new JLabel("用户名 :");
		container.add(nameLabel);
		namePanel.add(nameLabel, BorderLayout.WEST);

		nameField = new JTextField("", 15);
		container.add(nameField);
		namePanel.add(nameField, BorderLayout.EAST);
		nameField.addActionListener(this);

		// 密码
		passwordLabel = new JLabel("密码:");
		container.add(passwordLabel);
		passPanel.add(passwordLabel, BorderLayout.WEST);

		passwordField = new JTextField("", 15);
		container.add(passwordField);
		passPanel.add(passwordField, BorderLayout.EAST);
		passwordField.addActionListener(this);

		// 登陆按钮
		loginButton = new JButton("登陆");
		container.add(loginButton);
		loginPanel.add(loginButton, BorderLayout.WEST);
		loginButton.addActionListener(this);

		// 登录错误
		errorLaebl = new JLabel("请先登录!");
		errorLaebl.setForeground(Color.red);
		container.add(errorLaebl);
		loginPanel.add(errorLaebl, BorderLayout.EAST);

		// 功能模块
		functionPanel = new JPanel();
		functionPanel.setSize(new Dimension(150, 300));
		functionPanel.setPreferredSize(new Dimension(150, 300));
		functionPanel.setForeground(Color.black);
		functionPanel.setBackground(Color.gray);
//		functionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// 用网格布局!
		functionPanel.setLayout(new GridLayout(7, 1));
//		functionPanel.setSize(160, 300);

		// 购买管理
		buyButton = new Button("购买管理");
		container.add(buyButton);
		functionPanel.add(buyButton);
		buyButton.addActionListener(this);

		// 转借管理
		lendButton = new Button("转借管理");
		container.add(lendButton);
		functionPanel.add(lendButton);
		lendButton.addActionListener(this);

		// repairButton,storeButton,scrapButton,userButton,exitButton;

		// 维修管理
		repairButton = new Button("维修管理");
		container.add(repairButton);
		functionPanel.add(repairButton);
		repairButton.addActionListener(this);

		// 存储管理
		storeButton = new Button("存储管理");
		container.add(storeButton);
		functionPanel.add(storeButton);
		storeButton.addActionListener(this);

		// 报废管理
		scrapButton = new Button("报废管理");
		container.add(scrapButton);
		functionPanel.add(scrapButton);
		scrapButton.addActionListener(this);

		// 用户管理
		userButton = new Button("用户管理");
		container.add(userButton);
		functionPanel.add(userButton);
		userButton.addActionListener(this);

		// 退出系统
		exitButton = new Button("退出系统");
		container.add(exitButton);
		functionPanel.add(exitButton);
		exitButton.addActionListener(this);

		// 将面板添加到框架中
		jFrame.add(namePanel);
		jFrame.add(passPanel);
		jFrame.add(loginPanel);
		jFrame.add(functionPanel, BorderLayout.CENTER);

		jFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String password = passwordField.getText();
		List<User> users = UserDao.query();
		if (e.getSource() == loginButton) {
			if (QueryUtil.searchBoth(name, password, users)) {
				errorLaebl.setText("登录成功!");
				login_success = true;
			} else {
				errorLaebl.setText("登录失败");
				login_success = false;
			}
		}
		
		/**
		 *  显示其他模块功能
		 * 如果登录成功,可以显示,否则显示错误提示
		 */
		if (e.getSource() == buyButton) {
			if (login_success && NoExitFlag) {
				buyUI.show();

			} else {
				ErrorUtil.show("请先登录...");
			}
		}
		if (e.getSource() == lendButton) {
			if (login_success && NoExitFlag) {
				lendUI.show();
			} else {
				ErrorUtil.show("请先登录...");
			}
		}
		if (e.getSource() == repairButton) {
			if (login_success && NoExitFlag) {

				repairUI.show();
			} else {
				ErrorUtil.show("请先登录...");
			}
		}
		if (e.getSource() == storeButton) {
			if (login_success && NoExitFlag) {
				storeUI.show();
			} else {
				ErrorUtil.show("请先登录...");
			}
		}
		
		if (e.getSource() == scrapButton) {
			if (login_success && NoExitFlag) {
				scrapUI.show();
			} else {
				ErrorUtil.show("请先登录...");
			}
		}
		
		if (e.getSource() == userButton) {
			if (login_success && NoExitFlag) {
				userUI.show();
			} else {
				ErrorUtil.show("请先登录...");
			}
		}
		//退出管理,销毁其他界面,并清除登录信息
		//有bug 如果点击某个功能按钮多次,只有销毁最后一个
		if (e.getSource() == exitButton) {
			login_success = false;
			NoExitFlag = false;
			new exitUI(nameField, passwordField, buyUI, lendUI, repairUI, storeUI, scrapUI,userUI,errorLaebl);
		}
	}

	public static void main(String[] args) {
		new LoginUI();
	}
}
