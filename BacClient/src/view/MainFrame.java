package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.SwitchClient;

/**
 * 创建主窗体
 * 
 * @author tom
 *
 */
public class MainFrame extends JFrame {
	
	// 没有用
	private static final long serialVersionUID = 1L;
	
	// 设置窗体的宽和高
	private static final int DEFAULT_WIDTH = 550;
	private static final int DEFAULT_HEIGHT = 600;
	public static final int TEXT_ROWS = 60;
	public static final int TEXT_COLUMNS = 120;
	
	private static JTextArea messages;
	private JButton clientServerButton;
	private JScrollPane messagesScroll;
	
	public MainFrame() {
		// 设置窗体的宽和高
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// 设置窗体启动位置位屏幕的正中间
		this.setLocationRelativeTo(null);
		
		// 设置布局控制器
		this.getContentPane().setLayout(null);
		
		// 实例化一个文本区域
		messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		// 为文本区域添加滚动条并设置文本区域的约束
		messagesScroll = new JScrollPane(messages);
		messagesScroll.setBounds(0, 20, 420, 500);
		add(messagesScroll);
		
		// 实例化一个按钮
		clientServerButton = new JButton("连  接");
		// 为按钮添加约束
		clientServerButton.setFont(new Font("", 1, 30));
		clientServerButton.setBounds(420, 20, 120, 60);
		add(clientServerButton);
		
		SwitchClient sc = new SwitchClient(clientServerButton, messages);
		// 为按钮添加事件
		clientServerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 通过控制器开启和关闭连接
				sc.contrlClientServer();
			}
		});
		
		// JTAMessage.printMessages(ConfigEnum.serverIP.getValue());
	}
	
	/**
	 * 获得交互窗口
	 * 
	 * @return 返回这个组件
	 */
	public static JTextArea getMessage() {
		return messages;
	}
	
}
