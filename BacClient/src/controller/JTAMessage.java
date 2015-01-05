package controller;

import java.awt.EventQueue;

import view.MainFrame;

/**
 * 用另外进程池的进程给文本区域输出信息
 * 
 * @author tom
 *
 */
public class JTAMessage {
	public static void printMessages(String msg) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainFrame.getMessage().append(msg + "\n");
			}
		});
	}
	
}
