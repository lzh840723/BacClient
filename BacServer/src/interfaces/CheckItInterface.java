package interfaces;

/**
 * 用于检测算法的正确性的接口
 * 
 * @author tom
 *
 */
public interface CheckItInterface
{
	/**
	 * 检测需要被检测的东西的方法
	 * 
	 * @param MinManey 与计划方案相应的最小值
	 * @return
	 */
	boolean checkIt(int MinManey);
}
