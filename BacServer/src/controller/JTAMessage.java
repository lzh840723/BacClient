package controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

import controller.planner.ClientsInfo;
import view.MainFrameOld;

/**
 * 用另外进程池的进程给文本区域输出信息yy
 * 
 * @author tom
 *
 */

public class JTAMessage
{
	private static JTextArea messages = MainFrameOld.getMessages();
	
	/**
	 * 主服务器的名称
	 */
	public static final String serverName = "Server";
	
	/**
	 * 向交互用的textArea组件打印信息
	 * 
	 * @param msg 信息的内容
	 */
	public static void printMessages(String msg)
	{
		
		EventQueue.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				messages.append(msg + "\n");
			}
		});
	}
	
	/**
	 * 向交互用的textArea组件打印信息
	 * 
	 * @param who 打印的主体是谁
	 * @param msg 打印的内容
	 */
	public static void printMessages(String who, String msg)
	{
		EventQueue.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				messages.append(who + " echo:" + msg + "\n");
			}
		});
	}
	
	/**
	 * 检测输入内容是否为数字，并转换为int型
	 * 
	 * @param mF 输入的数字
	 * @return 返回转换后的数字
	 */
	public static int toIntAndCheck(String mF)
	{
		if (Pattern.matches("^[0-9]+$", mF))
			return Integer.parseInt(mF);
		else
			printMessages("只能输入数字！！！");
		
		return 0;
	}
	
	/**
	 * 检验输入的信息是否为数字
	 * 
	 * @param mf 被检验的信息
	 * @return 是数字则返回true，反之为false
	 */
	public static boolean checkInt(String mf)
	{
		if (Pattern.matches("^[0-9]+$", mf))
			return true;
		else
			printMessages("只能输入数字！！！");
		
		return false;
	}
	
	/**
	 * 将整个计划清单打印出来
	 */
	public static void printList()
	{
		ArrayList<String[]> planList = ClientsInfo.getCBPList();
		
		for (int i = 0; i < planList.size(); i++)
			printMessages(
					serverName,
					"名字:" + planList.get(i)[ClientsInfo.clientName] + " | 庄闲:"
							+ planList.get(i)[ClientsInfo.clientBP] + " | 数额:"
							+ planList.get(i)[ClientsInfo.clientMoney] + " | 余额:"
							+ planList.get(i)[ClientsInfo.clientBalance]);
		
		printMessages(serverName, "-------------------------------------------");
	}
	
	/**
	 * 打印空白行，用于体现间隔
	 */
	public static void printSpace()
	{
		printMessages("\n\n");
	}
}
