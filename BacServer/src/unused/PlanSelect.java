package unused;

import interfaces.GetPlanInterface;

import javax.swing.JComboBox;

import view.MainFrameOld;

public class PlanSelect
{
	
	public GetPlanInterface select(JComboBox<String> choosePlanComboBox,
			JComboBox<String> moneyComboBox)
	{
		MainFrameOld.planSelect = choosePlanComboBox.getItemAt(choosePlanComboBox.getSelectedIndex());
		
		// if (MainFrame.planSelect.equals("平衡抽水"))
		// {
		// }
		// else if (MainFrame.planSelect.equals("闲赢免抽"))
		// {
		// return new GetPlayPlan(moneyComboBox);
		// }
		// else if (MainFrame.planSelect.equals("庄赢免抽"))
		// {
		// return new GetBankPlan(moneyComboBox);
		// }
		// else
		// {
		// }

		return null;
		
	}
}
