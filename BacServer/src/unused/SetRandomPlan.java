package unused;

import java.util.ArrayList;
import java.util.Arrays;

import controller.JTAMessage;
import controller.planner.ClientsInfo;
import view.MainFrameOld;

public class SetRandomPlan extends SetRandomPlanAbstract
{
	public static int clientsCount = ClientsInfo.getClientsCount();
	public ArrayList<String[]> cbpLiset = ClientsInfo.getCBPList();
	
	/**
	 * 此清单用于储存所得的所有可能性的计划
	 */
	public static int[][][] planList = new int[(int) (0b1 << clientsCount)][clientsCount + 1][2];
	
	public SetRandomPlan()
	{
	}
	
	public SetRandomPlan(int inputMoney)
	{
		super(inputMoney);
		// TODO Auto-generated constructor stub
		
		/**
		 * 这里设置planList
		 */
		getOrientationPlan(creatPlan());
		
	}
	
	@Override
	public int bankMin()
	{
		// TODO Auto-generated method stub
		return 1000;
	}
	
	@Override
	public int playMin()
	{
		// TODO Auto-generated method stub
		return 950;
	}
	
	@Override
	public int bankRatio()
	{
		// TODO Auto-generated method stub
		return 20;
	}
	
	@Override
	public int playRatio()
	{
		// TODO Auto-generated method stub
		return 19;
	}
	
	@Override
	public boolean getRandomPlan()
	{
		// TODO Auto-generated method stub
		
		if (clientsCount > 30)
		{
			JTAMessage.printMessages("客户端数量过大！！！");
			return false;
		}
		
		int[][] bankMaxPlan = getMaxPlan();
		if (bankMaxPlan == null)
			return false;
		
		if (bankMaxPlan[0][bank] > money)
			finishPlan(getMidPlan());
		else
			finishPlan(bankMaxPlan);
		
		return true;
	}
	
	public int[][] getMidPlan()
	{
		int bankUsed = getRound(money, bankMin());
		int playUsed = bankUsed / bankRatio() * playRatio();
		
		ArrayList<int[][]> usefulPlan = new ArrayList<>();
		
		for (int i = 0; i < planList.length; i++)
		{
			if (planList[i][0][bank] >= bankUsed && planList[i][0][play] >= playUsed)
				usefulPlan.add(planList[i]);
		}
		
		int[][] usedPlan = makePlan(usefulPlan.get(getRandom(usefulPlan.size())), money);
		
		return usedPlan;
	}
	
	/**
	 * 根据庄的总投入金额计算出相应比例的闲的总金额
	 * 
	 * @param bankSumUsed 庄的总投入金额
	 * @return 闲的总投入金额
	 */
	public int getPlaySumUsed(int bankSumUsed)
	{
		return getRound(bankSumUsed, 50) / bankRatio() * playRatio();
	}
	
	/**
	 * 根据闲的总投入金额计算出相应比例的庄的总金额
	 * 
	 * @param playSumUsed 闲的总投入金额
	 * @return 庄的总投入金额
	 */
	public int getBankSumUsed(int playSumUsed)
	{
		return getRound(playSumUsed, 50) / playRatio() * bankRatio();
	}
	
	/**
	 * 对随机抽取的计划进行随机分配额度
	 * 
	 * @param usedPlan 随机抽取的计划
	 * @param useBankNum 单方庄闲可以使用的Sum总额度,一般为bankMax或者是money
	 * @return 返回处理后的计划清单
	 */
	public int[][] makePlan(int[][] usedPlan, int useBankNum)
	{
		int bankCounts = 0;
		int playCounts = 0;
		int bankPaySum = 0;
		int playPaySum = 0;
		int bankSum = 0;
		int playSum = 0;
		
		for (int i = 1; i < usedPlan.length; i++)
		{
			if (usedPlan[i][0] == bank)
			{
				bankCounts++;
				bankSum += getLess(usedPlan[i][1], MainFrameOld.limit);
			}
			else if (usedPlan[i][0] == play)
			{
				playCounts++;
				playSum += getLess(usedPlan[i][1], MainFrameOld.limit);
			}
		}
		
		useBankNum = getLess(useBankNum, bankSum);
		int usePlayNum = getPlaySumUsed(useBankNum);
		
		if (usePlayNum > playSum)
		{
			usePlayNum = playSum;
			useBankNum = getBankSumUsed(usePlayNum);
		}
		
		int top = 0;
		int buttom = 0;
		
		for (int i = 1; i < usedPlan.length; i++)
		{
			if (usedPlan[i][0] == bank)
			{
				bankSum -= getLess(usedPlan[i][1], MainFrameOld.limit);
				buttom = (useBankNum - bankPaySum) - bankSum;
				buttom = getLess(buttom, MainFrameOld.limit);
				if (buttom < 0)
					buttom = 0;
				bankCounts--;
				top = getLess((useBankNum - bankPaySum) - bankCounts * 50,
						getLess(usedPlan[i][1], MainFrameOld.limit));
				top = getLess(top, MainFrameOld.limit);
				
				usedPlan[i][1] = getRound(getRandom(buttom, top), 50);
				bankPaySum += usedPlan[i][1];
			}
			else
			{
				playSum -= getLess(usedPlan[i][1], MainFrameOld.limit);
				buttom = (usePlayNum - playPaySum) - playSum;
				buttom = getLess(buttom, MainFrameOld.limit);
				if (buttom < 0)
					buttom = 0;
				playCounts--;
				top = getLess((usePlayNum - playPaySum) - playCounts * 50,
						getLess(usedPlan[i][1], MainFrameOld.limit));
				top = getLess(top, MainFrameOld.limit);
				
				usedPlan[i][1] = getRound(getRandom(buttom, top), 50);
				playPaySum += usedPlan[i][1];
			}
		}
		
		return usedPlan;
	}
	
	/**
	 * 将int[][]型的计划清单赋给arraylist，完成清单的最后形成
	 * 
	 * @param plan int[][]的方案清单
	 */
	public void finishPlan(int[][] plan)
	{
		String bp;
		for (int i = 1; i < plan.length; i++)
		{
			if (plan[i][0] == bank)
				bp = "bank";
			else
				bp = "play";
			cbpLiset.get(i - 1)[ClientsInfo.clientBP] = bp;
			cbpLiset.get(i - 1)[ClientsInfo.clientMoney] = plan[i][1] + "";
			cbpLiset.get(i - 1)[ClientsInfo.clientBalance] = (Double.parseDouble(cbpLiset
					.get(i - 1)[ClientsInfo.clientBalance]) - (double) plan[i][1]) + "";
		}
	}
	
	/**
	 * 分别得到庄闲的金额实际可以使用的金额,并放入方案数组第一个元素
	 * 
	 * @param plan 方案数组
	 */
	public int[][] getBankPlaySum(int[][] plan)
	{
		int banksum = 0;
		int playsum = 0;
		int bankful = 0;
		int playful = 0;
		
		for (int i = 1; i < plan.length; i++)
		{
			if (plan[i][0] == bank)
				banksum = banksum + getLess(plan[i][1], MainFrameOld.limit);
			else
				playsum = playsum + getLess(plan[i][1], MainFrameOld.limit);
		}
		
		bankful = getRound(banksum, bankMin());
		playful = bankful / bankRatio() * playRatio();
		
		if (playful > playsum)
		{
			playful = getRound(playsum, playMin());
			bankful = playful / playRatio() * bankRatio();
		}
		
		plan[0][bank] = bankful;
		plan[0][play] = playful;
		
		return plan;
	}
	
	/**
	 * 获得一个int[][]包含所有的bank和play组合的情况,如果客户端适量过多则返回null
	 * 
	 * @return int[][]
	 */
	public int[][] getallBP()
	{
		if (clientsCount > 30)
		{
			JTAMessage.printMessages(JTAMessage.serverName,
					"连接到本服务起的客户端过多！！！");
			return null;
		}
		int[][] allPlan = new int[(int) (0b1 << clientsCount)][1];
		
		int buttom = (int) (0b1 << 31) - 0b1;
		
		for (int i = 1; i < buttom && i < (int) ((0b1 << clientsCount) - 0b1); i++)
			for (int x = 1; x <= clientsCount; x++)
				allPlan[x - 1][0] = i << (32 - x) >>> 31;
		
		return allPlan;
	}
	
	/**
	 * 获得庄闲的所有组合
	 * 
	 * @param plan 庄闲搭配的不同方案
	 * @return 返回方案集合
	 */
	public void getOrientationPlan(int[][] plan)
	{
		
		int buttom = (int) (0b1 << 31) - 0b1;
		for (int i = 1; i < buttom && i < (int) ((0b1 << clientsCount) - 0b1); i++)
		{
			for (int x = 1; x <= clientsCount; x++)
				plan[x][0] = i << (32 - x) >>> 31;
			
			int[][] pan = getBankPlaySum(plan);
			for (int j = 0; j < pan.length; j++)
				planList[i][j] = Arrays.copyOf(pan[j], pan[j].length);
		}
	}
	
	/**
	 * 根据clientList的格式建立计划,并将第一个元素空出来
	 * 
	 * @return 返回int[][]型计划
	 */
	public int[][] creatPlan()
	{
		// plan的第一个组数据为bank和play的实际可以使用的值
		int[][] plan = new int[clientsCount + 1][2];
		// 为plan赋值的同时给余额以为算子50取整,将第一个元素空出来存放庄闲的总和值
		for (int i = 0; i < clientsCount; i++)
		{
			if (cbpLiset.get(i)[ClientsInfo.clientMoney].isEmpty())
				cbpLiset.get(i)[ClientsInfo.clientMoney] = "0";
			
			cbpLiset.get(i)[ClientsInfo.clientBalance] = Double
					.parseDouble(cbpLiset.get(i)[ClientsInfo.clientBalance])
					+ Double.parseDouble(cbpLiset.get(i)[ClientsInfo.clientMoney]) + "";
			
			plan[i + 1][1] = getRound(
					(int) Double.parseDouble(cbpLiset.get(i)[ClientsInfo.clientBalance]), 50);
		}
		
		return plan;
	}
	
	/**
	 * 得到最高额度的庄分配方案
	 * 
	 * @return 返回int[][]型的方案
	 */
	public int[][] getMaxPlan()
	{
		int bankMax = 0;
		int newbankMax = 0;
		ArrayList<int[][]> bankMaxList = new ArrayList<>();
		
		for (int i = 0; i < planList.length; i++)
		{
			if (planList[i][0][bank] == 0)
				continue;
			
			newbankMax = planList[i][0][bank];
			
			if (newbankMax > bankMax)
			{
				bankMaxList.clear();
				bankMax = newbankMax;
				bankMaxList.add(planList[i]);
			}
			if (newbankMax == bankMax)
				bankMaxList.add(planList[i]);
		}
		if (bankMaxList.size() == 0)
		{
			JTAMessage.printMessages(JTAMessage.serverName, "没有可用的方案！！！");
			return null;
		}
		int[][] selectPlan = bankMaxList.get(getRandom(bankMaxList.size()));
		
		int[][] plan = makePlan(selectPlan, selectPlan[0][bank]);
		
		return plan;
	}
	
	/**
	 * 取整
	 * 
	 * @param i 被取整的数
	 * @param flag 取整的标准，以100，1000或其他取整
	 * @return 返回取整后的值
	 */
	public int getRound(int i, int flag)
	{
		return i - i % flag;
	}
	
	/**
	 * 在余额和可用范围之间取较小的值
	 * 
	 * @param a
	 * @param b
	 * @return 返回较小的值
	 */
	public int getLess(int a, int b)
	{
		if (a > b)
			return b;
		else
			return a;
	}
	
	/**
	 * 随机得到庄闲客户端的数量
	 * 
	 * @return 返回包含庄闲客户端的数量的数组
	 * 
	 */
	public int[] getRandomBPCount()
	{
		int bankCount = getRandom(1, clientsCount);
		int playCount = clientsCount - bankCount;
		
		int[] bp = new int[2];
		bp[bank] = bankCount;
		bp[play] = playCount;
		
		return bp;
	}
	
}
