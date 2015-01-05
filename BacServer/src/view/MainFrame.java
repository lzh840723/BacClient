package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import controller.GetInitClientsMod;
import controller.InputPlan;
import controller.JTAMessage;
import controller.checker.CheckPlanSimple;
import controller.planner.ClientsInfo;
import controller.sender.SendData;

public class MainFrame extends MainFrameOld {
	
	public MainFrame() {
		initClientsMod();
		
	}
	
	/**
	 * 一个class ID，没啥用。
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<InitClients> setClientAL = null;
	
	private void initClientsMod() {
		ArrayList<String[]> CBPList = ClientsInfo.getCBPList();
		setClientAL = new ArrayList<InitClients>();
		JButton JBrush = new JButton("刷  新");
		JButton JBinputAndCheck = new JButton("检  查");
		JButton JBsend = new JButton("投  注");
		JButton JBexe = new JButton("确  定");
		JButton JBreset = new JButton("恢  复");
		SendData sender = new SendData();
		JComboBox<String> JCBshortcut = new JComboBox<String>();
		
		if (CBPList.size() > 10) {
			JTAMessage.printMessages(JTAMessage.serverName, "客户端数目超过最大处理范围！！！");
			return;
		}
		
		int x = 0;
		int y = 0;
		for (int i = 0; i < 4; i++) {
			// 模块y轴(纵向)布局
			y = 200 * (i - i % 2) / 2;
			
			setClientAL.add(new InitClients());
			setClientAL.get(i).addClientSingleMod(x, y);
			add(setClientAL.get(i).getJLclientName());
			add(setClientAL.get(i).getJTFmoney());
			add(setClientAL.get(i).getJRBbank());
			add(setClientAL.get(i).getJRBplay());
			
			// 模块x轴(横向)布局
			if (x == 200) {
				x = 0;
				continue;
			}
			x += 200;
		}
		
		JBrush.setBounds(600, 140, 100, 45);
		JBrush.setFont(SetFont.getJBottonFont());
		add(JBrush);
		
		// 设置每个客户端控制模块对应的信息
		JBrush.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (setClientAL != null)
					new GetInitClientsMod().init(setClientAL);
			}
		});
		
		//快捷功能
		JCBshortcut.setBounds(750, 100, 100, 120);
		JCBshortcut.addItem("0");
		JCBshortcut.addItem("1000");
		JCBshortcut.addItem("2000");
		JCBshortcut.addItem("4000");
		add(JCBshortcut);
		
		JCBshortcut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		JBinputAndCheck.setBounds(1000, 220, 120, 45);
		JBinputAndCheck.setFont(SetFont.getJBottonFont());
		JBinputAndCheck.setForeground(Color.red);
		add(JBinputAndCheck);
		
		JBinputAndCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JBinputAndCheck.setForeground(Color.BLUE);
				
				if (setClientAL != null) {
					if (new CheckPlanSimple(setClientAL).checkIt() == true) {
						// 将设置好的投注计划输入到计划清单
						new InputPlan().input(setClientAL);
						JBsend.setEnabled(true);
						JBexe.setEnabled(true);
					}
					else {
						JBsend.setEnabled(false);
						JBexe.setEnabled(false);
					}
				}
			}
		});
		
		JBsend.setFont(SetFont.getJBottonFont());
		JBsend.setBounds(1000, 320, 120, 45);
		JBsend.setForeground(Color.BLUE);
		JBsend.setEnabled(false);
		add(JBsend);
		
		// 发送投注方案并在每个客户端根据方案进行设置
		JBsend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 向各个客户端发送各自的数据
				if (!ClientsInfo.getCBPList().isEmpty()) {
					try {
						sender.sendDataToAllClients();
						JBsend.setEnabled(false);
					}
					catch (IOException e1) {
						// TODO Auto-generated catch block
						JTAMessage.printMessages(JTAMessage.serverName, e1 + "");
					}
				}
			}
		});
		
		JBexe.setFont(SetFont.getJBottonFont());
		JBexe.setBounds(1000, 420, 120, 45);
		JBexe.setForeground(Color.blue);
		add(JBexe);
		
		// 在每个客户端按下确认键
		JBexe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JBinputAndCheck.setForeground(Color.RED);
				try {
					sender.sendDataToAllClients(SendData.cmd_done);
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					JTAMessage.printMessages(JTAMessage.serverName, e1 + "");
				}
			}
		});
		
		JBreset.setFont(SetFont.getJBottonFont());
		JBreset.setBounds(1000, 520, 120, 45);
		JBreset.setForeground(Color.blue);
		add(JBreset);
		
		JBreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					sender.sendDataToAllClients(SendData.cmd_reset);
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					JTAMessage.printMessages(JTAMessage.serverName, e1 + "");
				}
			}
		});
	}
}