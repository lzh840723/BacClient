package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Expression
{
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				MainFrame frame = new MainFrame();
				// 设置关闭按钮
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// 使窗体可见
				frame.setVisible(true);
				// 使窗体大小不可变
				frame.setResizable(false);
				// 设置标题内容
				// frame.setTitle("百家乐分析软件");
			}
		});
		
	}
	
}
