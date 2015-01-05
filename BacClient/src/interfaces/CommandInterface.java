package interfaces;

public interface CommandInterface {
	/**
	 * 执行命令
	 */
	void exeCmd();
	
	/**
	 * 运行确认动作
	 */
	void done();
	
	/**
	 * 运行重置动作
	 */
	void reset();
	
	/**
	 * 选择打庄
	 */
	void selectBank();
	
	/**
	 * 选择打闲
	 */
	void selectPlay();
	
	/**
	 * 运行点击10000的筹码
	 * 
	 * @param i 点击次数
	 */
	void tenThousand(int i);
	
	/**
	 * 运行点击5000的筹码
	 * 
	 * @param i 点击次数
	 */
	void fiveThousand(int i);
	
	/**
	 * 运行点击1000的筹码
	 * 
	 * @param i 点击次数
	 */
	void thousand(int i);
	
	/**
	 * 运行点击500的筹码
	 * 
	 * @param i 点击次数
	 */
	void fiveHundred(int i);
	
	/**
	 * 运行点击100的筹码
	 * 
	 * @param i 点击次数
	 */
	void hundred(int i);
}
