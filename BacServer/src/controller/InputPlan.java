package controller;

import java.util.ArrayList;

import controller.planner.ClientsInfo;
import view.InitClients;

/**
 * 将界面输入的信息存入客户端投注清单
 * 
 * @author tom
 *
 */
public class InputPlan {
	
	/**
	 * 将信息输入投注清单
	 * 
	 * @param setClientAL 客户端的交互窗口
	 */
	public void input(ArrayList<InitClients> setClientAL) {
		// TODO Auto-generated method stub
		ArrayList<String[]> CBPlist = ClientsInfo.getCBPList();
		
		for (int i = 0; i < CBPlist.size(); i++) {
			CBPlist.get(i)[ClientsInfo.clientMoney] = setClientAL.get(i).getJTFmoney()
					.getText().trim();
			CBPlist.get(i)[ClientsInfo.clientBP] = setClientAL.get(i).getSelect();
		}
	}
}
