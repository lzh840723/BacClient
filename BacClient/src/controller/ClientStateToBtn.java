package controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;

public class ClientStateToBtn
{
	private JButton clientServerButton;
	
	public ClientStateToBtn(JButton csb)
	{
		this.clientServerButton = csb;
	}
	
	private void changeBtn(String btnName, Color btnColor)
	{
		EventQueue.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				clientServerButton.setText(btnName);
				clientServerButton.setForeground(btnColor);
			}
		});
	}
	
	public void switchBtn(Boolean serverState)
	{
		
		if (serverState)
			changeBtn("关  闭", Color.red);
		else
			changeBtn("连  接", Color.black);
	}
	
}
