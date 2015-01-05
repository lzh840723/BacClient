package controller;

import java.util.ArrayList;

import controller.planner.ClientsInfo;
import view.InitClients;

/**
 * 设置每个客户端控制窗口的对应信息
 * 
 * @author tom
 *
 */
public class GetInitClientsMod {
	
	/**
	 * 设置每个客户端控制窗口的对应信息
	 * 
	 * @param setClientAL 储存每个客户端对应窗口的类
	 */
	public void init(ArrayList<InitClients> setClientAL) {
		
		ArrayList<String[]> CBPlist = ClientsInfo.getCBPList();
		
		for (int i = 0; i < setClientAL.size(); i++) {
			if (i < CBPlist.size()) {
				setClientAL.get(i).getJLclientName()
						.setText(CBPlist.get(i)[ClientsInfo.clientName]);
				setClientAL.get(i).getJTFmoney().setText("0");
			}
			else {
				setClientAL.get(i).getJLclientName().setText("null");
				setClientAL.get(i).getJTFmoney().setText("0");
			}
		}
	}
}
