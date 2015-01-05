package unused;

import java.util.concurrent.ThreadLocalRandom;

public abstract class SetRandomPlanAbstract implements interfaces.RandomPlanInterface
{
	protected int money;
	public static final int bank = 0;
	public static final int play = 1;
	
	public SetRandomPlanAbstract()
	{
	}
	
	/**
	 * 对客户端进行随机的钱数分配
	 * 
	 * @param inputMoney 输入的钱数
	 */
	public SetRandomPlanAbstract(int inputMoney)
	{
		this.money = inputMoney;
	}
	
	/**
	 * bankRatio()和 playRatio()方法用于描述庄闲的比值
	 * 
	 * @return 返回庄的比值
	 */
	public abstract int bankRatio();
	
	/**
	 * bankRatio()和 playRatio()方法用于描述庄闲的比值
	 * 
	 * @return 返回闲的比值
	 */
	public abstract int playRatio();
	
	/**
	 * 庄的最小值
	 * 
	 * @return 返回庄的最小值
	 */
	public abstract int bankMin();
	
	/**
	 * 闲的最小值
	 * 
	 * @return 返回闲的最小值
	 */
	public abstract int playMin();
	
	/**
	 * 取得随机值
	 * 
	 * @param i 设置随机范围
	 * @return 返回随机数
	 */
	public static int getRandom(int i)
	{
		if (i <= 0)
			return 0;
		
		return ThreadLocalRandom.current().nextInt(i);
	}
	
	/**
	 * 在max和min之间取得随机整数
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @return 返回随机整数
	 */
	public static int getRandom(int min, int max)
	{
		
		if (min < 0)
			min = 0;
		if (max < 0)
			max = 0;
		if (min > max)
		{
			int a = min;
			min = max;
			max = a;
		}
		// 为了让随机可以包括max本身
		max++;
		if (min == max)
			max++;
		
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	/**
	 * 得到整个随机计划
	 */
	public abstract boolean getRandomPlan();
}
