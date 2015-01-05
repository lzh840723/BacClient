package interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DataInterface
{
	/**
	 * 得到数据库的链接
	 * 
	 * @return 返回数据库的链接
	 */
	Connection getConnection() throws SQLException,IOException;
	
	/**
	 * 只执行一个sql语句，无返回信息
	 * 
	 * @param sql sql语句
	 * @throws SQLException
	 * @throws IOException
	 */
	void justExeSql(String sql) throws SQLException, IOException;
	
	/**
	 * 执行sql语句并以ResultSet类返回所有信息
	 *
	 * @param sql sql语句
	 * @throws IOException
	 * @throws SQLException
	 * @return info 执行sql之后得到的结果集
	 */
	 String[][] getInfo(String sql) throws SQLException, IOException;
}
