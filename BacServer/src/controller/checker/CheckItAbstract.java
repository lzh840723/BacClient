package controller.checker;

import controller.JTAMessage;
import controller.planner.ClientsInfo;
import interfaces.CheckItInterface;

/**
 * 用于执行的前的检测
 * 
 * @author tom
 *
 */
public abstract class CheckItAbstract implements CheckItInterface {
	private int money;
	
	public CheckItAbstract() {
	}
	
	/**
	 * 老版本的检测
	 * 
	 * @param money 投注金额
	 */
	public CheckItAbstract(int money) {
		this.money = money;
	}
	
	/**
	 * 在计算计划之前进行检测
	 * 
	 * @param MinBankMoney 与计划类型相应的庄的最小值
	 * @return
	 */
	public boolean checkIt(int MinBankMoney) {
		if (money == 0) {
			JTAMessage.printMessages(JTAMessage.serverName, "请输入投注金额!!!");
			return false;
		}
		else if (money < MinBankMoney) {
			JTAMessage.printMessages(JTAMessage.serverName, "最小值不得小于" + MinBankMoney + "!!!");
			return false;
		}
		else if (ClientsInfo.getCBPList().isEmpty()) {
			JTAMessage.printMessages(JTAMessage.serverName, "没有账户连接到服务器!!!");
			return false;
		}
		else {
			int flag = 0;
			for (int i = 0; i < ClientsInfo.getClientsCount(); i++) {
				if (ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBalance]
						.isEmpty())
					flag++;
			}
			if (flag > 0) {
				JTAMessage.printMessages(JTAMessage.serverName, "请确定每个账户都设置了入金金额!!!");
				return false;
			}
			
		}
		
		return true;
	}
	
}
