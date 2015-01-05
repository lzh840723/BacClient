package controller.check;

import java.util.regex.Pattern;

public class Check {
	
	/**
	 * 检验str是否为数字
	 * 
	 * @param str 被检验的str
	 * @return 返回boolen，true为是，false为否
	 */
	public static boolean checkStrIsInt(String str) {
		if (Pattern.matches("^[0-9]+$", str))
			return true;
		else
			return false;
	}
}
