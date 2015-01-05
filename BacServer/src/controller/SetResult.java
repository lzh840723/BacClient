package controller;

import interfaces.SetResultInerface;

import java.util.ArrayList;

import controller.planner.ClientsInfo;

/**
 * 用于计算每次投注后的余额
 * 
 * @author tom
 *
 */
public class SetResult implements SetResultInerface
{
	private String result;
	private ArrayList<String[]> planList = ClientsInfo.getCBPList();
	private static final int clientName = ClientsInfo.clientName;
	private static final int clientBalance = ClientsInfo.clientBalance;
	private static final int clientChips = ClientsInfo.clientChips;
	private static final int clientMoney = ClientsInfo.clientMoney;
	private static final int clientTieChips = ClientsInfo.clientTieChips;
	
	public SetResult(String result)
	{
		this.result = result;
	}
	
	private double bankWinBalance(int useNum, double balance)
	{
		return useNum + useNum * 0.95 + balance;
	}
	
	private double playWinBalance(int useNum, double balance)
	{
		return useNum * 2 + balance;
	}
	
	@Override
	public void getBalance() throws NumberFormatException
	{
		// TODO Auto-generated method stub
		int useNum = 0;
		double balance = 0;
		
		if (ClientsInfo.getClientsCount() == 0)
		{
			JTAMessage.printMessages(JTAMessage.serverName,
					"没有客户端连接到本服务器！！！");
			return;
		}
		
		JTAMessage.printMessages(JTAMessage.serverName, result);
		
		for (int i = 0; i < ClientsInfo.getClientsCount(); i++)
		{
			
			if (ClientsInfo.getCBPList().get(i)[ClientsInfo.clientMoney].isEmpty())
				ClientsInfo.getCBPList().get(i)[ClientsInfo.clientMoney] = "0";
			
			useNum = Integer
					.parseInt(ClientsInfo.getCBPList().get(i)[ClientsInfo.clientMoney]);
			balance = Double
					.parseDouble(ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBalance]);
			if (result.equals("庄赢"))
				if (ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBP]
						.equals("bank"))
					ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBalance] = bankWinBalance(
							useNum, balance) + "";
			if (result.equals("闲赢"))
				if (ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBP]
						.equals("play"))
					ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBalance] = playWinBalance(
							useNum, balance) + "";
			if (result.equals("和"))
			{
				JTAMessage
						.printMessages(
								JTAMessage.serverName,
								ClientsInfo.getCBPList().get(i)[ClientsInfo.clientName]
										+ ":  "
										+ (Double.parseDouble(ClientsInfo.getCBPList()
												.get(i)[ClientsInfo.clientBalance]) + useNum));
				
			}
			else
			{
				JTAMessage
						.printMessages(
								JTAMessage.serverName,
								ClientsInfo.getCBPList().get(i)[ClientsInfo.clientName]
										+ ":  "
										+ ClientsInfo.getCBPList().get(i)[ClientsInfo.clientBalance]);
			}
			
		}
		
	}
	
	@Override
	public void getOtherInfo() throws NumberFormatException
	{
		// TODO Auto-generated method stub
		
		double sumBalance = 0;
		double takeSum = 0;
		double currentTake = 0;
		if (result.equals("和"))
		{
			for (int i = 0; i < planList.size(); i++)
			{
				planList.get(i)[clientTieChips] = Integer.parseInt(planList.get(i)[clientTieChips])
						+ Integer.parseInt(planList.get(i)[clientMoney]) + "";
				
				sumBalance += Double.parseDouble(planList.get(i)[clientBalance]);
				sumBalance += Double.parseDouble(planList.get(i)[clientMoney]);
			}
			
			takeSum = ClientsInfo.firstSumBalance - sumBalance;
		}
		else
		{
			for (int i = 0; i < planList.size(); i++)
			{
				planList.get(i)[clientChips] = Integer.parseInt(planList.get(i)[clientMoney])
						+ Integer.parseInt(planList.get(i)[clientChips]) + "";
				
				sumBalance += Double.parseDouble(planList.get(i)[clientBalance]);
				
				// 最后将每个账户上次投注的金额归零，否则在下次计算投注清单的时候会被加到庄户余额里去。
				ClientsInfo.getCBPList().get(i)[ClientsInfo.clientMoney] = "0";
			}
			
			// 计算抽水
			takeSum = ClientsInfo.firstSumBalance - sumBalance;
			currentTake = takeSum - ClientsInfo.lastTakeSum;
			ClientsInfo.lastTakeSum = takeSum;
		}
		
		int chipsSum = 0;
		int tieChipsSum = 0;
		for (int i = 0; i < planList.size(); i++)
		{
			JTAMessage.printMessages(JTAMessage.serverName,
					planList.get(i)[clientName] + ":  码量为:" + planList.get(i)[clientChips] + "  和值为："
							+ planList.get(i)[clientTieChips]);
			chipsSum += Integer.parseInt(planList.get(i)[clientChips]);
			tieChipsSum += Integer.parseInt(planList.get(i)[clientTieChips]);
		}
		
		JTAMessage.printMessages(JTAMessage.serverName, "  总码量为："
				+ chipsSum + "   总和值为:" + tieChipsSum);
		JTAMessage.printMessages(JTAMessage.serverName, "此次抽水金额为："
				+ currentTake);
		JTAMessage.printMessages(JTAMessage.serverName, "总抽水金额为："
				+ takeSum);
		
		JTAMessage.printSpace();
		
	}
	
}
