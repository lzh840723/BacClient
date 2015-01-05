package view;

import java.awt.GridBagConstraints;

/**
 * This class simplifies the use of the GridBagConstraints class.
 * 
 * @version 1.01 2004-05-06
 * @author Cay Horstmarnn
 *
 */
public class GBC
		extends
		GridBagConstraints
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a GBC with a given gridx and gridy position and all other grid
	 * bag constraint values set to the default.
	 * 设置组件的位置，gridx设置为GridBagConstraints.RELATIVE代表此组件位于之前所加入组件的右边。
	 * 若将gridy设置为GridBagConstraints
	 * .RELATIVE代表此组件位于以前所加入组件的下面。建议定义出gridx,gridy的位置
	 * ，以便以后维护程序。表示放在几行几列，gridx=0,gridy=0时放在0行0列。
	 * 
	 * @param gridx the gridx position
	 * @param gridy the gridy position
	 */
	public GBC(
			int gridx,
			int gridy)
	{
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	/**
	 * Sets the cell spans.
	 * 用来设置组件所占的单位长度与高度，默认值皆为1。你可以使用GridBagConstraints.REMAINDER常
	 * 量，代表此组件为此行或此列的最后一个组件，而且会占据所有剩余的空间。
	 * 
	 * @param gridwidth the cell span in x-direction
	 * @param gridheight the cell span in y-direction
	 * @return this object for further modification
	 */
	public GBC setSpan(
			int gridwidth,
			int gridheight)
	{
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		return this;
	}
	
	/**
	 * Sets the anchor.
	 * 当组件空间大于组件本身时，要将组件置于何处，有CENTER(默认值)、NORTH、NORTHEAST、EAST、SOUTHEAST、
	 * WEST、NORTHWEST可供选择
	 * 
	 * @param anchor the anchor value
	 * @return this object for further modification
	 */
	public GBC setAnchor(
			int anchor)
	{
		this.anchor = anchor;
		return this;
	}
	
	/**
	 * Sets the fill
	 * direction.指定组件在单元格内的填充行为。取值为NONE,BOTH,HORIZONTAL,VERTICAL.默认NONE.
	 * 
	 * @param fill the fill direction
	 * @return this object for further modification
	 */
	public GBC setFill(
			int fill)
	{
		this.fill = fill;
		return this;
	}
	
	/**
	 * Sets the cell weights. 用来设置窗口变大时，各组件跟着变大的比例， 当数字越大，表示组件能得到更多的空间，默认值皆为0。
	 * 
	 * @param weightx the cell weight in x-direction
	 * @param weighty the cell weight in y-direction
	 * @return this object for further modification
	 */
	public GBC setWeight(
			double weightx,
			double weighty)
	{
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	
	/**
	 * Sets the insets of this cell. 设置组件之间彼此的间距，它有四个参数，分别是上，左，下，右，默认为(0,0,0,0).
	 * 
	 * @param distance the spacing to use in all directions
	 * @return this object for further modification
	 */
	public GBC setInsets(
			int distance)
	{
		this.insets = new java.awt.Insets(
				distance,
				distance,
				distance,
				distance);
		return this;
	}
	
	/**
	 * Sets the insets of this cell. 设置组件之间彼此的间距，它有四个参数，分别是上，左，下，右，默认为(0,0,0,0).
	 * 
	 * @param top the spacing to use on top
	 * @param left the spacing to use to the left
	 * @param bottom the spacing to use on the bottom
	 * @param right the spacing to use to the right
	 * @return this object for further modification
	 */
	public GBC setInsets(
			int top,
			int left,
			int bottom,
			int right)
	{
		this.insets = new java.awt.Insets(
				top,
				left,
				bottom,
				right);
		return this;
	}
	
	/**
	 * Sets the internal padding 设置组件内的间距，默认值为0。
	 * 
	 * @param ipadx the internal padding in x-direction
	 * @param ipady the internal padding in y-direction
	 * @return this object for further modification
	 */
	public GBC setIpad(
			int ipadx,
			int ipady)
	{
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}
