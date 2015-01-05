package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.JTAMessage;

/**
 * 架设多进程服务器架构
 * 
 * @author tom
 *
 */
public class MultiThreadServer
{
	private int port = 8821;
	private ExecutorService executorService;// 线程池
	private final int POOL_SIZE = 10;// 单个CPU线程池大小
	private Socket incoming = null;
	
	public void serviceStart()
	{
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			JTAMessage.printMessages("Server echo:服务器已启动");
			// Runtime的availableProcessor()方法返回当前系统的CPU数目.
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
			while (true)
			{
				try
				{
					// 接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
					incoming = serverSocket.accept();
					ServerHandler sh = new ServerHandler(incoming);
					executorService.execute(sh);

				}
				catch (Exception e)
				{
					JTAMessage.printMessages("MultiThreadServer.serviceStart:" + e);
				}
			}
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			JTAMessage.printMessages("MultiThreadServer:" + e1);
		}
	}
	
}
