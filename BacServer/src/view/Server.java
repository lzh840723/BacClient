package view;

import javax.swing.JFrame;

public class Server
{
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() {
		 * LoginFrame frame = new LoginFrame(); // 设置关闭按钮
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 使窗体可见
		 * frame.setVisible(true); // 使窗体大小可变 frame.setResizable(true); //
		 * 设置标题内容 // frame.setTitle("百家乐分析软件"); } });
		 */
		MainFrame mf = new MainFrame();
		// 设置关闭按钮
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 使窗体可见
		mf.setVisible(true);
		// 使窗体大小不可变
		mf.setResizable(false);
	}
}
