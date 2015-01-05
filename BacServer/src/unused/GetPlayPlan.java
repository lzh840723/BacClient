package unused;

import javax.swing.JComboBox;

import controller.JTAMessage;

/**
 * 闲赢不抽水的方案
 * 
 * @author tom
 *
 */
public class GetPlayPlan extends GetPlan
{
	
	public GetPlayPlan(JComboBox<String> mC)
	{
		super(mC);
		// TODO Auto-generated constructor stub
		JTAMessage.printMessages("请输入庄的金额，最小值不得小于" + minBankMoney() + "!!!");
		setMoneyComboBox();
	}

	@Override
	protected void checkThisPlan()
	{
		// TODO Auto-generated method stub
		new CheckPlayPlan().checkIt();
	}
	
	@Override
	protected int minBankMoney()
	{
		// TODO Auto-generated method stub
		return new SetPlayRandomPlan().bankMin();
	}
	
	@Override
	public boolean setRandomPlan(int money)
	{
		// TODO Auto-generated method stub
		return new SetPlayRandomPlan(money).getRandomPlan();
	}
	
}
