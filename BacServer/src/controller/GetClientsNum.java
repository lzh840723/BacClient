package controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;

import controller.planner.ClientsInfo;
import controller.server.MultiThreadServer;

/**
 * 获得连接到服务器的客户端数目
 * 
 * @author tom
 *
 */
public class GetClientsNum {
	private static int num = ClientsInfo.getClientsCount();
	private static boolean flag = true;
	
	/**
	 * 实时获得连接到服务器的客户端数目
	 * 
	 * @param mts 开启的服务器类
	 * @param clientsNum 显示客户数目的标签
	 */
	public static void getClientsNum(MultiThreadServer mts, JLabel clientsNum) {
		while (true) {
			try {
				Thread.sleep(1000);
				if (mts != null) {
					EventQueue.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							clientsNum.setText(ClientsInfo.getClientsCount() + "");
							// 当客户数目发生变化时重新设置输入界面数量
							// if (num != ClientsBalancePlan.getClientsCount())
							// MainFrame.setClients();
							
							// 当客户数量减少时标示数字变红，增长和不变时为绿色
							if (num <= ClientsInfo.getClientsCount()) {
								if (flag == true) {
									clientsNum.setForeground(Color.green);
								}
								else {
									if (num < ClientsInfo.getClientsCount()) {
										clientsNum.setForeground(Color.green);
										flag = true;
									}
									
								}
								
							}
							else {
								clientsNum.setForeground(Color.red);
								flag = false;
							}
							
							num = ClientsInfo.getClientsCount();
						}
					});
				}
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				JTAMessage.printMessages(e.toString());
			}
		}
		
	}
}
