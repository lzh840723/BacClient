package controller.command;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import Data.ConfigEnum;
import controller.JTAMessage;

/**
 * 命令的解析与执行
 * 
 * @author tom
 *
 */
public class Command extends CommandAbstract {
	
	private String data;
	/**
	 * 协议：第一个:投注方向，第二个:万位点击数量，第三个：五千点击数量，第四个：千位点击数量，第五个：五百点击数量，第六个：一百点击数量
	 */
	private static ArrayList<String> cmd = new ArrayList<String>();
	/**
	 * 储存格式化后的数据，第一个为投注方向，第二个为数额
	 */
	private String[] f_data;
	
	private Robot robot = null;
	
	/**
	 * 对主服务器传输过来的数据进行处理
	 * 
	 * @param data 被处理的数据
	 * @throws AWTException 错误信息
	 */
	public Command(String data) throws AWTException {
		this.data = data;
		robot = new Robot();
		
		formatData();
		getCmd(f_data);
	}
	
	/**
	 * 根据传递过来的命令来执
	 */
	public void exeCmd() {
		if (cmd.isEmpty()) {
			JTAMessage.printMessages("命令为空!!!");
		}
		else if (cmd.get(0).equals("reset")) {
			reset();
		}
		else if (cmd.get(0).equals("done")) {
			done();
		}
		else {
			if (cmd.get(0).equals("bank"))
				selectBank();
			else
				selectPlay();
			
			tenThousand(Integer.parseInt(cmd.get(1)));
			fiveThousand(Integer.parseInt(cmd.get(2)));
			thousand(Integer.parseInt(cmd.get(3)));
			fiveHundred(Integer.parseInt(cmd.get(4)));
			hundred(Integer.parseInt(cmd.get(5)));
			
		}
	}
	
	/**
	 * 格式化主服务器传过来的数据
	 */
	private void formatData() {
		// TODO Auto-generated method stub
		if (data.equals("reset") || data.equals("done")) {
			cmd.clear();
			cmd.add(data);
		}
		else {
			f_data = data.split(" ");
		}
		
	}
	
	/**
	 * 对数据进行解析,并转化称命令添加进cmd
	 * 
	 * @param f_data 格式化后的数据
	 */
	private void getCmd(String[] f_data) {
		
		if (f_data != null) {
			cmd.clear();
			cmd.add(f_data[0]);
			
			int num = Integer.parseInt(f_data[1]);
			cmd.add(String.valueOf(num / 10000));
			num = num - num / 10000 * 10000;
			cmd.add(String.valueOf(num / 5000));
			num = num - num / 5000 * 5000;
			cmd.add(String.valueOf(num / 1000));
			num = num - num / 1000 * 1000;
			cmd.add(String.valueOf(num / 500));
			num = num - num / 500 * 500;
			cmd.add(String.valueOf(num / 100));
		}
	}
	
	/**
	 * 根据设置信息作出相应的动作
	 * 
	 * @param x 纵坐标
	 * @param y 横坐标
	 */
	private void doIt(ConfigEnum x, ConfigEnum y) {
		String strX = x.getValue();
		String strY = y.getValue();
		if (strX != null && strY != null) {
			int xInt = Integer.parseInt(strX);
			int yInt = Integer.parseInt(strY);
			// 鼠标移动
			robot.mouseMove(xInt, yInt);
			robot.delay(200);
			// 鼠标的左键点击动作
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(200);
		}
		
	}
	
	@Override
	public void done() {
		// TODO Auto-generated method stub
		doIt(ConfigEnum.x_done, ConfigEnum.y_done);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		doIt(ConfigEnum.x_reset, ConfigEnum.y_reset);
	}
	
	@Override
	public void tenThousand(int i) {
		// TODO Auto-generated method stub
		for (int a = 1; a <= i; a++) {
			doIt(ConfigEnum.x_10000, ConfigEnum.y_10000);
		}
	}
	
	@Override
	public void fiveThousand(int i) {
		// TODO Auto-generated method stub
		for (int a = 1; a <= i; a++) {
			doIt(ConfigEnum.x_5000, ConfigEnum.y_5000);
		}
	}
	
	@Override
	public void thousand(int i) {
		// TODO Auto-generated method stub
		for (int a = 1; a <= i; a++) {
			doIt(ConfigEnum.x_1000, ConfigEnum.y_1000);
		}
	}
	
	@Override
	public void fiveHundred(int i) {
		// TODO Auto-generated method stub
		for (int a = 1; a <= i; a++) {
			doIt(ConfigEnum.x_500, ConfigEnum.y_500);
		}
	}
	
	@Override
	public void hundred(int i) {
		// TODO Auto-generated method stub
		for (int a = 1; a <= i; a++) {
			doIt(ConfigEnum.x_100, ConfigEnum.y_100);
		}
	}
	
	@Override
	public void selectBank() {
		// TODO Auto-generated method stub
		doIt(ConfigEnum.x_bank, ConfigEnum.y_bank);
	}
	
	@Override
	public void selectPlay() {
		// TODO Auto-generated method stub
		doIt(ConfigEnum.x_play, ConfigEnum.y_play);
	}
	
}
