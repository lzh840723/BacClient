package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import controller.JTAMessage;
import controller.planner.ClientsInfo;

/**
 * 建立一个进程和客户端取得连接
 * 
 * @author tom
 *
 */
public class ServerHandler implements Runnable {
	private Socket socket;
	private String clientName = null;
	public static volatile PrintWriter sender;
	
	public ServerHandler() {
	}
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}
	
	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}
	
	public void run() {
		try {
			BufferedReader br = getReader(socket);
			sender = getWriter(socket);
			clientName = br.readLine();
			
			JTAMessage.printMessages("New connection accepted " + clientName);
			ClientsInfo.addCient(clientName, socket);
			String msg = null;
			while ((msg = br.readLine()) != null) {
				
				JTAMessage.printMessages(clientName + ":" + msg);
				
				if (msg.equals("bye") || msg.equals("exit")) {
					ClientsInfo.removeClientInList(clientName);
					break;
				}
			}
		}
		catch (IOException e) {
			ClientsInfo.removeClientInList(clientName);
			JTAMessage.printMessages("ServerHandler.run:" + e);
		}
		finally {
			try {
				if (socket != null) {
					ClientsInfo.removeClientInList(clientName);
					socket.close();
				}
			}
			catch (IOException e) {
				ClientsInfo.removeClientInList(clientName);
				JTAMessage.printMessages("ServerHandler.run.finally:" + e);
			}
		}
	}
	
}