package linkDataBase;

import interfaces.DataInterface;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 放置一些对数据库数据操作的公共函数
 * 
 * @author tom
 *
 */
public abstract class MysqlBaccarat implements DataInterface
{
	/**
	 * 得到数据库的链接
	 * 
	 * @return Connection 返回数据库的链接
	 */
	public Connection getConnection() throws SQLException,IOException
	{
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("src/linkDataBase/database.properties")))
		{
			props.load(in);
		}
		
		String drivers = props.getProperty("jdbc.drivers");
		//System.out.println(drivers);
		if (drivers != null)
		{
			System.setProperty("jdbc.drivers", drivers);
		}
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(url,username,password);
	}
	
	/**
	 * 只执行一个sql语句，无返回信息
	 * 
	 * @param sql sql语句
	 * @throws IOException
	 * @throws SQLException
	 */
	public void justExeSql(String sql) throws SQLException, IOException
	{
		
		try (Connection conn = getConnection())
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		}
	}
	
	/**
	 * 执行sql语句并以ResultSet类返回所有信息
	 *
	 * @param sql sql语句
	 * @throws IOException
	 * @throws SQLException
	 * @return info 执行sql之后得到的结果集
	 */
	public String[][] getInfo(String sql) throws SQLException, IOException
	{
		try (Connection conn = getConnection())
		{
			// 设置可滚动结果集
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, // 打开可滚动结果集开关
					ResultSet.CONCUR_READ_ONLY); // 结果集不可用于更新数据库
			try (ResultSet result = stmt.executeQuery(sql))
			{
				ResultSetMetaData metaData = result.getMetaData();
				// 得到结果集的总列数
				int columnCount = metaData.getColumnCount();
				// 得到结果集的总行数
				int rowCount = 0;
				result.last();
				rowCount = result.getRow();
				
				// System.out.println(rowCount+","+columnCount);
				
				// 将指针放回第一行
				result.first();
				
				String info[][] = new String[rowCount][columnCount];
				int colIndex = 0;
				for (int i = 0; i < info.length; i++)
				{
					for (int j = 0; j < info[i].length; j++)
					{
						colIndex = j + 1;
						info[i][j] = result.getString(colIndex);
						result.next();
					}
				}
				
				return info;
			}
		}
	}
	
}
