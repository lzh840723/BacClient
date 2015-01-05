package unused;

import java.util.ArrayList;

import controller.JTAMessage;
import controller.checker.CheckItAbstract;
import controller.planner.ClientsInfo;
import view.MainFrameOld;

public class CheckBankPlan extends CheckItAbstract
{
	public CheckBankPlan()
	{
	}
	
	public CheckBankPlan(int money)
	{
		super(money);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 返回SetRandomPlan类中设置的庄的分配比例
	 * 
	 * @return
	 */
	protected int getBankRatio()
	{
		return new SetRandomPlan().bankRatio();
	}
	
	/**
	 * 得到实际的投注方案和目标标准方案之间的差距
	 * 
	 * @param bankSum 庄的金额的总和
	 * @param playSum 闲的近的的总和
	 * @return 返回这个差值
	 */
	protected int getStandardDeviation(int bankSum, int playSum)
	{
		return Math.abs((bankSum - playSum) - bankSum / getBankRatio());
	}
	
	/**
	 * 用于检验投注计划的正确性
	 */
	public void checkIt()
	{
		ArrayList<String[]> planList = ClientsInfo.getCBPList();
		
		int bankSum = 0;
		int playSum = 0;
		for (int i = 0; i < planList.size(); i++)
		{
			if (planList.get(i)[ClientsInfo.clientBP].equals("bank"))
				bankSum += Integer.parseInt(planList.get(i)[ClientsInfo.clientMoney]);
			else
				playSum += Integer.parseInt(planList.get(i)[ClientsInfo.clientMoney]);
			
			if (Integer.parseInt(planList.get(i)[ClientsInfo.clientMoney]) > MainFrameOld.limit)
				JTAMessage.printMessages(JTAMessage.serverName, "<"
						+ planList.get(i)[ClientsInfo.clientName] + ">的分配金额超出限额！！！<< ⚠ >>");
		}
		
		int flag = getStandardDeviation(bankSum, playSum);
		JTAMessage.printMessages(JTAMessage.serverName, "庄闲差值: " + flag);
		
		flag = Math.abs(flag - bankSum / getBankRatio());
		if (flag == 0)
		{
			JTAMessage.printMessages(JTAMessage.serverName, "标准差: "
					+ flag + " ===========> << OK >>");
		}
		else
			JTAMessage.printMessages(JTAMessage.serverName, "标准差: "
					+ flag + " ===========> << ⚠ >>");
		
	}
	
	/**
	 * 这是空的，不与使用
	 */
	public boolean checkIt(int MinManey)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
