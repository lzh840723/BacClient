package unused;



public class SetPlayRandomPlan extends SetRandomPlan
{
	public SetPlayRandomPlan()
	{
		// TODO Auto-generated constructor stub
	}
	
	public SetPlayRandomPlan(int inputMoney)
	{
		super(inputMoney);
		getOrientationPlan(creatPlan());
	}

	@Override
	public int bankMin()
	{
		return 50;
	}
	
	@Override
	public int playMin()
	{
		return 50;
	}
	
	@Override
	public int bankRatio()
	{
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public int playRatio()
	{
		// TODO Auto-generated method stub
		return 1;
	}
}
