package interfaces;

import java.io.IOException;

/**
 * 获得向客户端发送的数据
 * 
 * @author tom
 *
 */
public interface SendDataInterface {
	/**
	 * 发送
	 * 
	 * @param clientName 数据的接受者
	 * @param data 所发内容
	 * @throws IOException 错误信息
	 */
	void send(String clientName, String data) throws IOException;
}
