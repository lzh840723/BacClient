package controller.planner;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 用于保存客户的名字,余额和每个客户接受命令以及每个客户端的socket等信息，其中socket和其他信息不在一个arraylist中
 * 
 * @author tom
 *
 */
public class ClientsInfo {
	/**
	 * 客户的清单
	 */
	private volatile static ArrayList<String[]> CBPList = new ArrayList<>();
	private volatile static ArrayList<Socket> socetList = new ArrayList<Socket>();
	/**
	 * 客户名称
	 */
	public static final int clientName = 0;
	/**
	 * 客户余额
	 */
	public static final int clientBalance = 1;
	/**
	 * 打庄还是打闲
	 */
	public static final int clientBP = 2;
	/**
	 * 所打金额
	 */
	public static final int clientMoney = 3;
	/**
	 * 总投注量
	 */
	public static final int clientChips = 4;
	/**
	 * 和的码量
	 */
	public static final int clientTieChips = 5;
	/**
	 * 上次到第一次的抽水总和
	 */
	public static double lastTakeSum = 0;
	public static double firstSumBalance = 0;
	
	/**
	 * 获得客户名单
	 * 
	 * @return 返回名单
	 */
	public static ArrayList<String[]> getCBPList() {
		return CBPList;
	}
	
	/**
	 * 为客户清单添加客户
	 * 
	 * @param name 客户名称
	 * @param socket 客户端socket
	 */
	public static void addCient(String name, Socket socket) {
		String[] cp = { name, "0", "0", "0", "0", "0" };
		socetList.add(socket);
		CBPList.add(cp);
	}
	
	/**
	 * 获得socket链表
	 * 
	 * @return 返回这个Socket类型的ArrayList
	 */
	public static ArrayList<Socket> getSocketList() {
		return socetList;
	}
	
	/**
	 * 根据客户端名获得所处socket信息清单的下标
	 * 
	 * @param clientName 客户端名称
	 * @return 返回下标，－1为错误
	 */
	public static int getSocketListIndex(String clientName) {
		if (CBPList.size() == socetList.size()) {
			for (int i = 0; i < CBPList.size(); i++) {
				if (clientName.equals(CBPList.get(i)[ClientsInfo.clientName])) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * 获得总客户数量
	 * 
	 * @return 客户数量
	 */
	public static int getClientsCount() {
		return CBPList.size();
	}
	
	/**
	 * 删除连接名单中退出的客户信息
	 */
	public static void removeClientInList(String clientName) {
		if (clientName != null) {
			Iterator<String[]> iteratorStr = CBPList.iterator();
			Iterator<Socket> iteratorSocket = socetList.iterator();
			while (iteratorStr.hasNext()) {
				String[] str = iteratorStr.next();
				iteratorSocket.next();
				if (clientName.equals(str[ClientsInfo.clientName])) {
					iteratorStr.remove();
					iteratorSocket.remove();
				}
			}
			
		}
	}
	
	/**
	 * 给每个客户端发送的指令全部清除
	 */
	public static void clearPlan() {
		for (int i = 0; i < CBPList.size(); i++) {
			CBPList.get(i)[clientBP] = "0";
			CBPList.get(i)[clientMoney] = "0";
		}
	}
	
	/**
	 * 将每个客户点的余额全部清零
	 */
	public static void clearBalance() {
		for (int i = 0; i < CBPList.size(); i++)
			for (int j = 1; j < CBPList.get(i).length; j++)
				CBPList.get(i)[j] = "0";
		
		lastTakeSum = 0;
		firstSumBalance = 0;
	}
	
	/**
	 * 根据客户端名获得所处计划信息清单的下标
	 * 
	 * @param clientName 客户端名称
	 * @return 返回下标，－1为错误
	 */
	public static int getPlanIndex(String clientName) {
		if (CBPList.size() == socetList.size()) {
			for (int i = 0; i < CBPList.size(); i++) {
				if (clientName.equals(CBPList.get(i)[ClientsInfo.clientName])) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * 根据客户端名称获得所需发送的数据
	 * 
	 * @param clientName 客户端名称
	 * @return 所需发送的数据
	 */
	public static String getSendData(String clientName) {
		// TODO Auto-generated method stub
		int i = ClientsInfo.getPlanIndex(clientName);
		
		return CBPList.get(i)[ClientsInfo.clientBP] + " " + CBPList.get(i)[ClientsInfo.clientMoney];
	}
	
	/**
	 * 根据客户端名称获得所需发送的投注金额
	 * 
	 * @param clientName 客户端名称
	 * @return 所需发送的投注金额
	 */
	public static String getSendMoney(String clientName) {
		int i = ClientsInfo.getPlanIndex(clientName);
		
		return CBPList.get(i)[ClientsInfo.clientMoney];
		
	}
	
	/**
	 * 根据客户端名称获得正在通信的socket
	 * 
	 * @param clientName 客户端名称
	 * @return socket
	 */
	public static Socket getClientSocket(String clientName) {
		return socetList.get(getSocketListIndex(clientName));
	}
}
