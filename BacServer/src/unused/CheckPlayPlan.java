package unused;


public class CheckPlayPlan extends CheckBankPlan
{
	@Override
	protected int getBankRatio()
	{
		return new SetPlayRandomPlan().bankRatio();
	}
	
	@Override
	protected int getStandardDeviation(int bankSum, int playSum)
	{
		// TODO Auto-generated method stub
		return Math.abs(bankSum - playSum);
	}
}
