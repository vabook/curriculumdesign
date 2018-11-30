package top.vabook.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


//包括查询设备数据信息与用户登陆系统和修改密码
public class UserUI implements ActionListener {
	
	private static JFrame jFrame;
	
	private static Container container;

	private static JLabel findInNameLabel,userLabel,changePasswordLabel;
	
	private static JTable emTable,userTable;
	
	private static TableModel emTableModel,userTableModel;
	
	private static TableRowSorter<TableModel> sorter;
	
	private static JTextField textField;
	
	private static JPanel panel1,panel2,emPanel,userPanel,emtablePanel,usertablePanel;
	
	private static Button queryButton;
	
	private static String[] emColumns = {"设备号","设备名","设备状态","数量"};
	
	private static String[][] data = {};
	

	public  UserUI()  {
		 
		jFrame = new JFrame("用户管理");
		jFrame.setSize(500, 300);
		jFrame.setLocation(400, 200);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		container = jFrame.getContentPane();
		container.setLayout(new FlowLayout());
		
		//panel1承载了查询设备,以及查询结果
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));
		
		//emPanel 承载查询
		emPanel = new JPanel();
		emPanel.setLayout(new FlowLayout());
		
		findInNameLabel = new JLabel("查找设备名");
		container.add(findInNameLabel);
		emPanel.add(findInNameLabel,new FlowLayout(FlowLayout.LEFT));
		
		textField = new JTextField("",15);
		container.add(textField);
		emPanel.add(textField,new FlowLayout(FlowLayout.LEFT));
		
		queryButton = new Button("查询");
		container.add(queryButton);
		emPanel.add(queryButton,new FlowLayout(FlowLayout.LEFT));
		
		emTableModel = new DefaultTableModel(data,emColumns);
		emTable = new JTable(emTableModel);
		sorter = new TableRowSorter<TableModel>(emTable.getModel());
		emTable.setRowSorter(sorter);
		
		//emTablePanel 承载设备查询结果
		emtablePanel = new JPanel();
		emtablePanel.add(emTable);
		
		panel1.add(emPanel);
		panel1.add(emtablePanel);
		
		userLabel = new JLabel("用户查询");
		
		
		jFrame.add(panel1);
		jFrame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new UserUI();
	}

}
