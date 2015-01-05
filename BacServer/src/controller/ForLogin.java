package controller;

import java.io.IOException;
import java.sql.SQLException;

import linkDataBase.*;

/**
 * 为loginFrame窗体设计业务逻辑
 * 
 * @author tom
 *
 */
public class ForLogin 
{
	
	/**
	 * 判断登录信息并做出回应
	 * 
	 * @param userName 用户名
	 * @param passWord 用户密码
	 * @return 返回相映的处理方法
	 * @throws SQLException
	 * @throws IOException
	 */
	public String checkUserPassWord(String userName, String passWord) throws SQLException, IOException
	{
		MysqlBaccarat dataOpration = new selectData();
		String getPasswordQuery = "select password_ from usr where name_='" + userName + "'";
		String[][] info = dataOpration.getInfo(getPasswordQuery);
		String passWordFromSql = "";
		if (info.length > 0)
			passWordFromSql = info[0][0];

		if (userName.isEmpty())
			return "用户名不能为空！";
		else if (passWord.isEmpty())
			return "密码不能为空！";
		else if (passWord.equals(passWordFromSql))
			return "登录成功";
		else
			return "用户名或密码错误！";
		
	}

}
