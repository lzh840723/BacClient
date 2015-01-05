package view;

import java.awt.Font;

/**
 * 为软件设置统一规格的字体
 * 
 * @author tom
 *
 */
public class SetFont {
	
	/**
	 * 获得标签字体规格
	 * 
	 * @return 返回字体规格
	 */
	public static Font getJLabelFont() {
		return new Font("", 1, 18);
	}
	
	/**
	 * 获得按钮字体规格
	 * 
	 * @return 返回字体规格
	 */
	public static Font getJBottonFont() {
		return new Font("", 1, 25);
	}
}
