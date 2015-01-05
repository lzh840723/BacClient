package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.GetClientsNum;
import controller.server.MultiThreadServer;

/**
 * 主窗体类,用于具体的操作和显示
 * 
 * @author tom
 *
 */
public class MainFrameOld extends JFrame {
	private static final long serialVersionUID = 2L;
	
	// 设置窗体的宽和高
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 700;
	public static final int TEXT_ROWS = 60;
	public static final int TEXT_COLUMNS = 60;
	public static volatile int limit = 30000;
	// private static volatile String result = "庄赢";
	public static volatile String planSelect = "最小金额";
	
	// 4个JPanel瓜分界面
	// private JPanel messagePanel;
	// private JPanel clientsInitPanel;
	// private JPanel contrllerPanel;
	// private JPanel setClientsPanel;
	
	// 实例化一个文本区域,用于信息的交互
	private static JTextArea messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
	private JLabel clientsNum;
	private JScrollPane messagesScroll;
	private JButton switchServerButton;
// private JLabel planNameLabel;
// private JLabel moneyLabel;
// private JLabel clientNameLabel;
// private JLabel clientName;
// private JLabel balanceLabel;
// private JLabel limitLabel;
// private JLabel setResultLabel;
//
// private JTextField balanceText;
// private JButton getResultButton;
// private JButton resetButton;
// private JButton sureButton;
// private JButton balanceSureButton;
// private JComboBox<String> choosePlanComboBox;
// private JComboBox<String> moneyComboBox;
// private JComboBox<Integer> limitComboBox;
// private JComboBox<String> setResultCheckBox;
//
// private GetPlanInterface getPlan = new JustExeThePlan();
	private MultiThreadServer mts = new MultiThreadServer();
	
	public MainFrameOld() {
		// 设置窗体的宽和高
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// this.setExtendedState(Frame.MAXIMIZED_BOTH);
		// 设置窗体启动位置位屏幕的正中间
		this.setLocationRelativeTo(null);
		
		// 设置布局控制器
		this.getContentPane().setLayout(null);
		// setLayout(new GridBagLayout());
		
		// 给4个JPanel实例化，并进行布局
		// messagePanel = new JPanel();
		// contrllerPanel = new JPanel();
		// clientsInitPanel = new JPanel();
		// setClientsPanel = new JPanel();
		//
		// add(messagePanel, new GBC(0, 0).setSpan(1,
		// 2).setFill(GBC.HORIZONTAL).setWeight(30, 30));
		// add(contrllerPanel, new GBC(1, 0).setFill(GBC.HORIZONTAL));
		// add(clientsInitPanel, new GBC(0, 2).setFill(GBC.HORIZONTAL));
		// add(setClientsPanel, new GBC(1, 2).setSpan(2,
		// 1).setFill(GBC.HORIZONTAL));
		// pack();
		
		getMessageMod();
		getContrller();
		// getClientsInit();
		// setClients();
		
	}
	
	private void getContrller() {
		// contrllerPanel.setLayout(new GridBagLayout());
		
		showClientsCount();
		getServer();
		
		// getLimitMod();
		// getPlan();
		// setReultMod();
		
		// pack();
	}
	
// private void getClientsInit() {
// // clientsInitPanel.setLayout(new GridBagLayout());
//
// getBalanceMod();
//
// // pack();
// }
	
// private void setClients() {
// setClientsPanel.setLayout(new GridBagLayout());
//
// pack();
// }
	
	/**
	 * 设置输赢的结果,并更新余额
	 */
// private void setReultMod() {
// setResultLabel = new JLabel("  结  果:");
// setResultLabel.setBounds(550, 300, 150, 30);
// setResultLabel.setFont(new Font("", 1, 20));
// add(setResultLabel);
// // contrllerPanel.add(setResultLabel, new GBC(0,
// // 3).setFill(GBC.CENTER).setIpad(20, 10)
// // .setWeight(100, 100));
//
// setResultCheckBox = new JComboBox<>();
// setResultCheckBox.addItem("庄赢");
// setResultCheckBox.addItem("闲赢");
// setResultCheckBox.addItem("和");
// setResultCheckBox.setBounds(750, 300, 150, 30);
// setResultCheckBox.setFont(new Font("", 1, 20));
// setResultCheckBox.setEnabled(false);
// add(setResultCheckBox);
// // contrllerPanel.add(setResultCheckBox, new GBC(1, 3).setIpad(20,
// // 10).setWeight(100, 100));
//
// setResultCheckBox.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// result = setResultCheckBox.getItemAt(setResultCheckBox.getSelectedIndex());
// }
// });
//
// balanceSureButton = new JButton("确  定");
// balanceSureButton.setFont(new Font("", 1, 18));
// balanceSureButton.setBounds(950, 300, 100, 30);
// balanceSureButton.setEnabled(false);
// add(balanceSureButton);
// // contrllerPanel.add(balanceSureButton, new GBC(2, 3).setIpad(20,
// // 10).setWeight(10, 30));
//
// balanceSureButton.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// SetResult setResult = new SetResult(result);
// setResult.getBalance();
// setResult.getOtherInfo();
// }
// });
//
// }
	
	/**
	 * 获得信息交互模块
	 */
	private void getMessageMod() {
		// 设置为不可编辑
		messages.setEditable(false);
		// 为文本区域添加滚动条并设置文本区域的约束
		messagesScroll = new JScrollPane(messages);
		messagesScroll.setBounds(20, 20, 500, 500);
		add(messagesScroll);
		// messagePanel.setLayout(new GridBagLayout());
		// messagePanel.add(messagesScroll, new GBC(0,
		// 0).setFill(GBC.HORIZONTAL).setWeight(100, 100)
		// .setIpad(300, 300));
		
		// pack();
	}
	
	/**
	 * 取得交互用的textArea
	 * 
	 * @return 返回交互用的textArea
	 */
	public static JTextArea getMessages() {
		return messages;
	}
	
	/**
	 * 实时显示客户端数目模块
	 */
	private void showClientsCount() {
		// 实例化一个标签,用于显示连接的客户端数目
		clientsNum = new JLabel("0");
		// 设置标签的约束
		clientsNum.setFont(new Font("", 1, 50));
		clientsNum.setForeground(Color.green);
		clientsNum.setBounds(600, 20, 100, 100);
		add(clientsNum);
		// contrllerPanel.add(clientNum, new GBC(1,
		// 0).setFill(GBC.BOTH).setIpad(50, 50));
		
		// 新建一个线程每隔1秒获得一次客户端的总数
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				GetClientsNum.getClientsNum(mts, clientsNum);
			}
		}).start();
	}
	
	/**
	 * 启动服务模块
	 */
	private void getServer() {
		// 实例化一个服务器开关的按钮
		switchServerButton = new JButton("启   动");
		// 设置按钮的约束
		switchServerButton.setFont(new Font("", 1, 50));
		switchServerButton.setForeground(Color.green);
		switchServerButton.setBounds(900, 35, 200, 70);
		add(switchServerButton);
		// contrllerPanel.add(switchServerButton, new GBC(3,
		// 0).setFill(GBC.CENTER).setIpad(30, 30)
		// .setWeight(100, 100));
		
		// 设置按钮的监听功能,用于开启服务器
		switchServerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 新建一个线程打开服务器
				new Thread(new Runnable() {
					public void run() {
						mts.serviceStart();
					}
				}).start();
			}
		});
	}
	
	/**
	 * 获得投注方案模块
	 */
// private void getPlan() {
// // 实例化一个计算投注方案的按钮
// getResultButton = new JButton("计  算");
// // 设置按钮的约束
// getResultButton.setFont(new Font("", 1, 35));
// getResultButton.setBounds(1000, 190, 150, 70);
// getResultButton.setEnabled(false);
// add(getResultButton);
// // contrllerPanel.add(getResultButton, new GBC(2, 1).setIpad(30,
// // 10).setWeight(20, 50));
//
// // 投注方案选择标签
// planNameLabel = new JLabel("投注方案选择：");
// // planNameLabel.setBounds(550, 200, 150, 30);
// planNameLabel.setFont(new Font("", 1, 20));
// // add(planNameLabel);
// // contrllerPanel.add(planNameLabel, new GBC(0, 2).setIpad(30,
// // 10).setWeight(20, 50));
//
// // 投注金额标签
// moneyLabel = new JLabel("请输入金额：");
// moneyLabel.setBounds(550, 250, 150, 30);
// moneyLabel.setFont(new Font("", 1, 20));
// add(moneyLabel);
// // contrllerPanel.add(moneyLabel, new GBC(2, 2).setIpad(30,
// // 50).setWeight(20, 50));
//
// // 投注金额输入框
// moneyComboBox = new JComboBox<>();
// moneyComboBox.setBounds(750, 250, 150, 30);
// moneyComboBox.setFont(new Font("", 1, 18));
// moneyComboBox.setEnabled(false);
// add(moneyComboBox);
// // contrllerPanel.add(moneyComboBox, new GBC(3, 2).setWeight(20,
// // 50).setIpad(20, 10));
//
// // 投注方案复选框
// choosePlanComboBox = new JComboBox<>();
// choosePlanComboBox.addItem("最小金额");
// choosePlanComboBox.addItem("庄赢免抽");
// choosePlanComboBox.addItem("闲赢免抽");
// choosePlanComboBox.addItem("平衡抽水");
// choosePlanComboBox.setBounds(750, 200, 150, 30);
// choosePlanComboBox.setFont(new Font("", 1, 18));
// choosePlanComboBox.setEnabled(false);
// add(choosePlanComboBox);
// // contrllerPanel.add(choosePlanComboBox, new GBC(1, 2).setIpad(30,
// // 10).setWeight(20, 50));
//
// // 设置监听,选择投注方案算法
// choosePlanComboBox.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// // getPlan = new PlanSelect().select(choosePlanComboBox,
// // moneyComboBox);
// }
// });
//
// // 设置按钮的监听,用于计算投注方案
// getResultButton.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// EventQueue.invokeLater(new Runnable() {
//
// @Override
// public void run() {
// if (mts != null) {
// if (getPlan != null)
// getPlan.getPlan();
// }
// else
// JTAMessage.printMessages("请先启动服务器！");
// }
// });
// }
// });
// }
	
	/**
	 * 设置客户端余额
	 */
// private void getBalanceMod() {
// // 客户端余额设置模块
// clientNameLabel = new JLabel("客户端名称:");
// clientNameLabel.setBounds(20, 550, 150, 30);
// clientNameLabel.setFont(new Font("", 1, 20));
// add(clientNameLabel);
// // clientsInitPanel.add(clientNameLabel, new GBC(0, 0).setIpad(20,
// // 10).setWeight(20, 50));
//
// clientName = new JLabel();
// clientName.setBounds(170, 550, 150, 30);
// clientName.setFont(new Font("", 1, 20));
// add(clientName);
// // clientsInitPanel.add(clientName, new GBC(2, 0).setIpad(30,
// // 50).setWeight(30, 60));
//
// balanceLabel = new JLabel("  余  额：");
// balanceLabel.setBounds(20, 600, 150, 30);
// balanceLabel.setFont(new Font("", 1, 20));
// add(balanceLabel);
// // clientsInitPanel.add(balanceLabel, new GBC(0, 1).setIpad(20,
// // 10).setWeight(20, 50));
//
// balanceText = new JTextField();
// balanceText.setBounds(170, 600, 150, 30);
// balanceText.setFont(new Font("", 1, 20));
// balanceText.setEditable(false);
// add(balanceText);
// // clientsInitPanel.add(balanceText, new GBC(1, 1).setIpad(300,
// // 50).setWeight(100, 100));
//
// resetButton = new JButton("重  置");
// resetButton.setBounds(400, 545, 100, 40);
// resetButton.setFont(new Font("", 1, 20));
// resetButton.setEnabled(false);
// add(resetButton);
// // clientsInitPanel.add(resetButton, new GBC(2, 0).setIpad(100,
// // 50).setWeight(100, 100));
//
// // 清除所有客户端的余额信息
// resetButton.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// ClientsBalancePlan.clearBalance();
// JTAMessage.printMessages("客户端余额已清除，请重新设置。");
//
// // 设置标签clientName为清单上的第一个客户名字
// EventQueue.invokeLater(new Runnable() {
//
// @Override
// public void run() {
// if (ClientsBalancePlan.getClientsCount() > 0)
// clientName.setText(ClientsBalancePlan.getCBPList().get(0)[0]);
// }
// });
// }
// });
//
// sureButton = new JButton("下一个");
// sureButton.setBounds(400, 595, 100, 40);
// sureButton.setFont(new Font("", 1, 20));
// sureButton.setEnabled(false);
// add(sureButton);
// // clientsInitPanel.add(sureButton, new GBC(2, 1).setIpad(100,
// // 50).setWeight(100, 100));
//
// // 设置每个客户端的余额信息
// sureButton.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// new SetClientsBalance(balanceText, clientName).setBalance();
// }
// });
//
// }
	
	/**
	 * 设置限额模块
	 */
// private void getLimitMod() {
// // 设置限额模块
// limitLabel = new JLabel("  限  额:");
// limitLabel.setBounds(20, 650, 150, 30);
// limitLabel.setFont(new Font("", 1, 20));
// add(limitLabel);
// // contrllerPanel.add(limitLabel, new GBC(0,
// // 1).setFill(GBC.CENTER).setIpad(20, 10));
//
// limitComboBox = new JComboBox<>();
// limitComboBox.setBounds(170, 650, 150, 30);
// limitComboBox.setFont(new Font("", 1, 20));
// limitComboBox.addItem(30000);
// limitComboBox.addItem(50000);
// limitComboBox.setEnabled(false);
// add(limitComboBox);
// // contrllerPanel.add(limitComboBox, new GBC(1,
// // 1).setFill(GBC.CENTER).setIpad(20, 10)
// // .setInsets(0, -100, 0, 100).setWeight(20, 50));
//
// limitComboBox.addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
//
// limit = limitComboBox.getItemAt(limitComboBox.getSelectedIndex());
// }
// });
//
// }
	
}
