package unused;

import java.awt.EventQueue;

import javax.swing.JComboBox;

import controller.JTAMessage;

/**
 * 计算庄赢不抽水的方案
 * 
 * @author tom
 *
 */
public class GetPlan extends GetPlanAbstract
{
	private JComboBox<String> moneyComboBox;
	
	public GetPlan(JComboBox<String> mC)
	{
		this.moneyComboBox = mC;
		
		JTAMessage.printMessages("请输入庄的金额，最小值不得小于" + minBankMoney() + "!!!");
		setMoneyComboBox();
	}
	
	public void setMoneyComboBox()
	{
		EventQueue.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				moneyComboBox.addItem("1000");
				moneyComboBox.addItem("2000");
				moneyComboBox.addItem("3000");
				moneyComboBox.addItem("4000");
				moneyComboBox.addItem("5000");
				moneyComboBox.addItem("6000");
				moneyComboBox.addItem("7000");
				moneyComboBox.addItem("8000");
				moneyComboBox.addItem("9000");
				moneyComboBox.addItem("10000");
				moneyComboBox.addItem("16000");
				moneyComboBox.addItem("32000");
				moneyComboBox.setEnabled(true);
				moneyComboBox.setEditable(true);
			}
		});
		
	}
	
	/**
	 * 检验这个投注方案
	 */
	protected void checkThisPlan()
	{
		new CheckBankPlan().checkIt();
	}
	
	/**
	 * 返回庄的最小值
	 * 
	 * @return 返回庄的最小值
	 */
	protected int minBankMoney()
	{
		return new SetRandomPlan().bankMin();
		
	}
	
	/**
	 * 执行计划，并查看计划分配是否成功
	 * 
	 * @param money 投入的钱数
	 * @return 成功返回true,没有计划则为false
	 */
	public boolean setRandomPlan(int money)
	{
		return new SetBankRandomPlan(money).getRandomPlan();
	}
	
	public void getPlan()
	{
		int money = JTAMessage.toIntAndCheck(moneyComboBox.getSelectedItem()
				.toString());
		
		if (new CheckBeforeGetPlan(money).checkIt(minBankMoney()) == false)
			return;
		
		// 执行计划，并查看计划分配是否成功
		if (setRandomPlan(money) == false)
			return;
		
		JTAMessage.printList();
		
		checkThisPlan();
		JTAMessage.printSpace();
		
	}
}
