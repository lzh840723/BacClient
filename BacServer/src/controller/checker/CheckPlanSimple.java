package controller.checker;

import java.util.ArrayList;
import java.util.regex.Pattern;

import view.InitClients;
import controller.JTAMessage;
import controller.planner.ClientsInfo;

/**
 * 对客户端输入计划的检测（简易版）
 * 
 * @author tom
 *
 */
public class CheckPlanSimple extends CheckItAbstract {
	
	/**
	 * 对客户端输入计划的检测（简易版）
	 * 
	 * @param setClientAL 客户端输入模块组件list
	 */
	public CheckPlanSimple(ArrayList<InitClients> setClientAL) {
		this.setClientAL = setClientAL;
	}
	
	public CheckPlanSimple() {
	};
	
	private ArrayList<InitClients> setClientAL;
	private int sumBank = 0;
	private int sumPlay = 0;
	
	/**
	 * 检测计划
	 * 
	 * @return 返回对错
	 */
	public boolean checkIt() {
		boolean flag = true;
		int money;
		double getMoneyDiff;
		String clientName;
		
		if (ClientsInfo.getCBPList().isEmpty()) {
			JTAMessage.printMessages(JTAMessage.serverName, "没有客户端连接到此服务器!!!");
			return false;
		}
		
		for (int i = 0; i < ClientsInfo.getClientsCount(); i++) {
			if (!Pattern.matches("^[0-9]+$", setClientAL.get(i).getJTFmoney().getText().trim())) {
				JTAMessage.printMessages(JTAMessage.serverName, "客户端"
						+ setClientAL.get(i).getJLclientName().getText() + "输入只能为数字！！！");
				
				flag = false;
			}
			
			if (setClientAL.get(i).getSelect() == "") {
				JTAMessage.printMessages(JTAMessage.serverName, "客户端"
						+ setClientAL.get(i).getJLclientName().getText() + "庄闲未选择!!!");
				
				flag = false;
			}
			
			money = Integer.parseInt(setClientAL.get(i).getJTFmoney().getText().trim());
			clientName = setClientAL.get(i).getJLclientName().getText();

			//检测限额下限
			if (money < 300 && money > 0) {
				JTAMessage.printMessages(JTAMessage.serverName, "⚠  <<" + clientName
						+ ">>的金额小于300，请检查限额下限！！！");
			}
			
			//检测限额上线
			if (money > 30000) {
				JTAMessage.printMessages(JTAMessage.serverName, "⚠  <<" + clientName
						+ ">>的金额大于30000，请检查限额上限！！！");
			}
		}
		
		if (flag) {
			getSumBankSumPlay();
			getMoneyDiff = Math.abs(getEarnings("bank") - getEarnings("play"));
			JTAMessage.printMessages(JTAMessage.serverName, "庄闲差: " + getBankPlayDiff());
			JTAMessage.printMessages(JTAMessage.serverName, "码量: " + getChips());
			JTAMessage.printMessages(JTAMessage.serverName, "收益: ");
			JTAMessage.printMessages(JTAMessage.serverName, "     庄赢: " + getEarnings("bank"));
			JTAMessage.printMessages(JTAMessage.serverName, "     闲赢: " + getEarnings("play"));
			JTAMessage.printMessages(JTAMessage.serverName, "     收益差: " + getMoneyDiff);
			if (getMoneyDiff > 100) {
				JTAMessage.printMessages(JTAMessage.serverName, "⚠  <<收益差>>的值大于100，请选择最优方案！！！");
			}
			JTAMessage.printMessages(JTAMessage.serverName,
					"----------------------------------\n\n\n\n");
		}
		
		return flag;
	}
	
	/**
	 * 分别获得庄和闲的总和，存入变量sumBank和sumPlay
	 */
	private void getSumBankSumPlay() {
		
		for (int i = 0; i < setClientAL.size(); i++) {
			if (setClientAL.get(i).getSelect().equals("play"))
				sumPlay += Integer.parseInt(setClientAL.get(i).getJTFmoney().getText().trim());
			
			if (setClientAL.get(i).getSelect().equals("bank"))
				sumBank += Integer.parseInt(setClientAL.get(i).getJTFmoney().getText().trim());
		}
		
	}
	
	/**
	 * 求的庄闲差的绝对值
	 * 
	 * @return 返回庄闲差
	 */
	private int getBankPlayDiff() {
		return Math.abs(sumPlay - sumBank);
	}
	
	/**
	 * 这次投注的码量
	 * 
	 * @return 返回码量
	 */
	private int getChips() {
		return sumBank + sumPlay;
	}
	
	/**
	 * 计算收益
	 * 
	 * @param winner 庄赢还是闲赢
	 * @return 返回收益值
	 */
	private double getEarnings(String winner) {
		double earnings = 0;
		
		if (winner.equals("bank")) {
			earnings = ((int) ((sumBank * 0.95 + getChips() * 0.008 - sumPlay) * 100)) / 100;
		}
		else {
			earnings = ((int) ((sumPlay + getChips() * 0.008 - sumBank) * 100)) / 100;
		}
		
		return earnings;
	}
	
	/**
	 * 根据客户端名字检测对应的每个客户端输入的金额是否存在或为0,用于决定信息指令是否有必要发送
	 * 
	 * @param money 发送的金额
	 * 
	 * @return 返回是或否
	 */
	public boolean checkMoney(String money) {
		if (money.equals("0") || money.equals(""))
			return false;
		else
			return true;
	}
	
}
