package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 一个用于设置投注的模块类
 * 
 * @author tom
 *
 */
public class InitClients {
	
	private JLabel JLclientName;
	private JTextField JTFmoney;
	private ButtonGroup BGbankPlay;
	private JRadioButton JRBbank;
	private JRadioButton JRBplay;
	private volatile String select = "";
	
	/**
	 * 获得标签组件：客户名称
	 * 
	 * @return 返回该组件
	 */
	public JLabel getJLclientName() {
		return JLclientName;
	}
	
	/**
	 * 获得组件：庄闲单选按钮之庄
	 * 
	 * @return 返回这个组件
	 */
	public JRadioButton getJRBbank() {
		return JRBbank;
	}
	
	/**
	 * 获得组件：庄闲单选按钮之闲
	 * 
	 * @return 返回这个组件
	 */
	public JRadioButton getJRBplay() {
		return JRBplay;
	}
	
	/**
	 * 获得输入框组件：所投金额
	 * 
	 * @return 返回该组件
	 */
	public JTextField getJTFmoney() {
		return JTFmoney;
	}
	
	/**
	 * 获得庄闲的选择
	 * 
	 * @return 返回该选择
	 */
	public String getSelect() {
		return select;
	}
	
	/**
	 * 初始化各个组件
	 * 
	 * @param x 横坐标
	 * @param y 纵坐标
	 */
	public void addClientSingleMod(int x, int y) {
		Font font =SetFont.getJLabelFont();

		JLclientName = new JLabel("NULL");
		JLclientName.setFont(font);
		JLclientName.setBounds(600 + x, 200 + y, 200, 20);
		
		JTFmoney = new JTextField();
		JTFmoney.setFont(font);
		JTFmoney.setBounds(600 + x, 230 + y, 100, 30);
		
		BGbankPlay = new ButtonGroup();
		JRBbank = new JRadioButton("庄", false);
		JRBbank.setFont(font);
		JRBbank.setForeground(Color.red);
		JRBbank.setBounds(600 + x, 260 + y, 100, 30);
		BGbankPlay.add(JRBbank);
		JRBbank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				select = "bank";
				JLclientName.setForeground(Color.red);
				JTFmoney.setForeground(Color.red);
			}
		});
		
		JRBplay = new JRadioButton("闲", false);
		JRBplay.setFont(font);
		JRBplay.setBounds(600 + x, 290 + y, 100, 30);
		JRBplay.setForeground(Color.BLUE);
		BGbankPlay.add(JRBplay);
		JRBplay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				select = "play";
				JLclientName.setForeground(Color.blue);
				JTFmoney.setForeground(Color.blue);
			}
		});
		
	}
}
