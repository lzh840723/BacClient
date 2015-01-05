package controller;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;

import controller.command.Command;
import Data.ConfigEnum;

public class ClientServer implements Runnable {
	private Socket socket;
	private int port = 8821;
	private String clientName;
	private OutputStream socketOut;
	private boolean serverState = false;
	private ClientStateToBtn cstb;
	
	public ClientServer(JButton btn) {
		cstb = new ClientStateToBtn(btn);
	}
	
	public void run() {
		
		try {
			
			socket = new Socket(ConfigEnum.serverIP.getValue(), port);
			clientName = ConfigEnum.name.getValue();
			JTAMessage.printMessages("Info: " + socket.getInetAddress() + ":" + socket.getPort());
			JTAMessage.printMessages("The client " + clientName + ":start");
			socketOut = socket.getOutputStream();
			// 发送客户端名称
			socketOut.write((clientName + "\r\n").getBytes());
			
			serverState = true;
			cstb.switchBtn(serverState);
			
			// 接收服务器的反馈
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = null;
			while ((msg = br.readLine()) != null) {
				JTAMessage.printMessages(msg);
				try {
					new Command(msg).exeCmd();
				}
				catch (AWTException e) {
					// TODO Auto-generated catch block
					JTAMessage.printMessages("ClientServer.run: "+e);
				}
			}
		}
		catch (IOException e) {
			JTAMessage.printMessages("ClientServer.run:" + e);
			
			serverState = false;
			cstb.switchBtn(serverState);
		}
	}
	
	public void stopCmd() throws IOException {
		serverState = false;
		socketOut.write("exit".getBytes());
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public boolean getState() {
		return serverState;
	}
	
}
