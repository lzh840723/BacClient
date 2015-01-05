package controller;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.planner.ClientsInfo;

/**
 * 设置每个客户端的余额
 * 
 * @author tom
 *
 */
public class SetClientsBalance
{
	private volatile JTextField balanceTextField;
	private volatile static int flag = 0;
	private JLabel client;
	private String clientBalance;
	
	public SetClientsBalance(JTextField btf, JLabel c)
	{
		this.balanceTextField = btf;
		this.client = c;
	}
	
	/**
	 * 设置余额
	 */
	public void setBalance()
	{
		clientBalance = balanceTextField.getText();
		
		if (ClientsInfo.getCBPList().isEmpty())
		{
			JTAMessage.printMessages(JTAMessage.serverName,
					"没有账户连接到服务器!!!");
			return;
		}
		// 检验说输入的信息是否为数字
		if (JTAMessage.checkInt(clientBalance) == false)
			return;
		
		EventQueue.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				client.setText(ClientsInfo.getCBPList().get(flag)[ClientsInfo.clientName]);
			}
		});
		ClientsInfo.getCBPList().get(flag)[ClientsInfo.clientBalance] = clientBalance;
		
		JTAMessage
				.printMessages(ClientsInfo.getCBPList().get(flag)[ClientsInfo.clientName]
						+ "余额设置为：" + clientBalance);
		flag++;
		
		// 检验是否到达list尾部，并归零
		if (flag >= ClientsInfo.getClientsCount())
		{
			for (int i = 0; i < ClientsInfo.getClientsCount(); i++)
			{
				ClientsInfo.firstSumBalance += Double.parseDouble(ClientsInfo
						.getCBPList().get(i)[ClientsInfo.clientBalance]);
				
			}
			
			flag = 0;
			JTAMessage.printMessages("设置完毕！！！");
			EventQueue.invokeLater(new Runnable()
			{
				
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					balanceTextField.setText("");
				}
			});
			return;
		}
	}
}
