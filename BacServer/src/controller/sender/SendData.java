package controller.sender;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import controller.checker.CheckPlanSimple;
import controller.planner.ClientsInfo;

/**
 * 获得向客户端发送的数据
 * 
 * @author tom
 *
 */
public class SendData extends SendDataAbstract {
	/**
	 * 指令确认
	 */
	public static final String cmd_done = "done";
	/**
	 * 指令重置
	 */
	public static final String cmd_reset = "reset";
	
	@Override
	public void send(String clientName, String data) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter sender = getSender(ClientsInfo.getClientSocket(clientName));
		
		sender.println(data);
	}
	
	/**
	 * 将信息发给所有的客户端
	 * 
	 * @throws IOException 错误信息
	 */
	public void sendDataToAllClients() throws IOException {
		ArrayList<String[]> CBPList = ClientsInfo.getCBPList();
		String clientName;
		String money;
		for (int i = 0; i < CBPList.size(); i++) {
			clientName = CBPList.get(i)[ClientsInfo.clientName];
			money = ClientsInfo.getSendMoney(clientName);
			if (new CheckPlanSimple().checkMoney(money)) {
				send(clientName, ClientsInfo.getSendData(clientName));
			}
		}
	}
	
	/**
	 * 向所有客户端发送统一的信息
	 * 
	 * @param data 发送的数据
	 * @throws IOException 错误信息
	 */
	public void sendDataToAllClients(String data) throws IOException {
		ArrayList<String[]> CBPList = ClientsInfo.getCBPList();
		String clientName;
		for (int i = 0; i < CBPList.size(); i++) {
			clientName = CBPList.get(i)[ClientsInfo.clientName];
			if (data.equals(cmd_done)) {
				if (new CheckPlanSimple().checkMoney(ClientsInfo.getSendMoney(clientName)))
					send(clientName, data);
			}
			else
				send(clientName, data);
		}
	}
	
	/**
	 * 获得输出的流
	 * 
	 * @param socket 正在通信中的socket
	 * @return 返回这个打印流
	 * @throws IOException 错误信息
	 */
	private PrintWriter getSender(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}
}
