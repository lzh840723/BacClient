package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.*;

/**
 * 登录窗体
 * 
 * @author tom
 *
 */
public class LoginFrame extends JFrame
{
	
	/**
	 * 用于区分类的版本
	 */
	private static final long serialVersionUID = 1L;
	// 设置窗体的宽高
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
	
	// 申明窗体的控件
	private JLabel labelName;
	private JLabel labelTitle;
	private JLabel labelPassword;
	private JLabel labelCue;
	private JButton buttonLogin;
	private JTextField textUserName;
	private JPasswordField userPassword;
	
	/**
	 * 给登录窗体添加各种控件
	 */
	public LoginFrame()
	{
		// 设置窗体大小
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// 设置窗体启动位置位屏幕的正中间
		this.setLocationRelativeTo(null);
		// 设置布局控制器
		this.getContentPane().setLayout(null);
		// 添加标签"输入用户名"
		this.add(this.getLabelName(), null);
		// 添加标签"工作攻击分析系统"
		this.add(this.getLabelTitle(), null);
		// 添加标签——密码
		this.add(this.getLabelPassword(), null);
		// 添加标签提示信息
		this.add(this.getLabelCue(), null);
		
		// 添加输入框——用户名
		this.add(this.getTextUserName(), null);
		// 添加密码框——密码
		this.add(this.getUserPassword(), null);
		
		// 添加按键——登录
		this.add(this.getButtonLogin(), null);
		
		// 给userPassword加监听
		userPassword.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				login();
			}
		});

		// 给button添加监听
		buttonLogin.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				login();
			}
		});
	}
	
	ForLogin login_ = new ForLogin();
	
	/**
	 * 实现登录
	 */
	private void login()
	{
		// 对用户名和密码进行判断,并取得需要输出的提示信息
		String message = "";
		try
		{
			message = login_.checkUserPassWord(textUserName.getText(), String.valueOf(userPassword.getPassword()));
			labelCue.setText(message);
			if (message.equals("登录成功"))
			{
				// 跳转到下个窗体
				dispose();
				// 创建主窗体
				MainFrameOld mf = new MainFrameOld();
				// 设置关闭按钮
				mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// 使窗体可见
				mf.setVisible(true);
				// 使窗体大小不可变
				mf.setResizable(false);
			}
		}
		catch (SQLException ex)
		{
			for (Throwable e1 : ex)
				e1.printStackTrace();
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * 设置按键——登录
	 * 
	 * @return buttonLogin控件
	 */
	private JButton getButtonLogin()
	{
		if (buttonLogin == null)
		{
			buttonLogin = new JButton("登    录");
			buttonLogin.setBounds(160, 210, 80, 20);
		}
		
		return buttonLogin;
	}
	
	/**
	 * 设置标签“密码”
	 * 
	 * @return labelPassword控件
	 */
	private JLabel getLabelPassword()
	{
		if (labelPassword == null)
		{
			labelPassword = new JLabel();
			// 设置标签的位置和大小
			labelPassword.setBounds(74, 130, 100, 18);
			// 设置标签的文本内容
			labelPassword.setText("输入密码：");
			// 设置鼠标悬停标签时显示的内容
			labelPassword.setToolTipText("请将你的密码输入此处");
			
		}
		
		return labelPassword;
	}
	
	/**
	 * 设置标签“提示信息”
	 * 
	 * @return 返回labelCue控件
	 */
	private JLabel getLabelCue()
	{
		if (labelCue == null)
		{
			// 设置标签——提示信息的输出内容和使文字居中显示
			labelCue = new JLabel("", JLabel.CENTER);
			// 设置标签的位置和大小
			labelCue.setBounds(100, 170, 200, 18);
			labelCue.setForeground(Color.red);
		}
		
		return labelCue;
	}
	
	/**
	 * 设置标签"工作统计分析系统"
	 * 
	 * @return labelTitle控件
	 */
	private JLabel getLabelTitle()
	{
		if (labelTitle == null)
		{
			labelTitle = new JLabel();
			// 设置标签的内容
			labelTitle.setText("工作统计分析系统");
			// 设置字体的种类和大小
			labelTitle.setFont(new Font("", 1, 25));
			// 设置标签的位置和宽高
			labelTitle.setBounds(103, 30, 200, 30);
		}
		
		return labelTitle;
	}
	
	/**
	 * 设置标签“输入用户名”
	 * 
	 * @return labelName控件
	 */
	private JLabel getLabelName()
	{
		if (labelName == null)
		{
			labelName = new JLabel();
			// 设置标签显示的文本
			labelName.setText("输入用户名：");
			// 设置标签的位置和宽高
			labelName.setBounds(74, 89, 100, 18);
			// 设置鼠标悬停标签控件时显示的文字
			labelName.setToolTipText("请将您的用户名输入此处");
		}
		
		return labelName;
	}
	
	/**
	 * 设置输入框"用户名"
	 * 
	 * @return textUserName控件
	 */
	private JTextField getTextUserName()
	{
		if (textUserName == null)
		{
			textUserName = new JTextField();
			// 设置输入框"用户名"的大小和位置
			textUserName.setBounds(160, 89, 150, 20);
		}
		
		return textUserName;
	}
	
	/**
	 * 设置密码框"密码"
	 * 
	 * @return userPassword控件
	 */
	private JPasswordField getUserPassword()
	{
		if (userPassword == null)
		{
			userPassword = new JPasswordField();
			// 设置密码框“密码”的大小和位置
			userPassword.setBounds(160, 130, 150, 20);
		}
		
		return userPassword;
	}
}
