package controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * 连接服务器的开关控制器
 * 
 * @author tom
 *
 */
public class SwitchClient {
	private static final int POOLSIZE = 3;
	private ClientServer cs = null;
	private ClientStateToBtn cstn;
	
	public SwitchClient(JButton btn, JTextArea msg) {
		cs = new ClientServer(btn);
	}
	
	private void start() {
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * POOLSIZE);
		exec.execute(cs);
	}
	
	private void stop() {
		try {
			cs.stopCmd();
			cs.getSocket().close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			JTAMessage.printMessages("\nSwitchClient.stop:" + e);
			cstn.switchBtn(false);
		}
		
	}
	
	public void contrlClientServer() {
		if (!cs.getState())
			start();
		else
			stop();
	}
	
}
