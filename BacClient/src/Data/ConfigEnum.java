package Data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import controller.JTAMessage;
import controller.check.Check;

/**
 * 存储客户端的各种信息
 * 
 * @author tom
 *
 */
public enum ConfigEnum {
	
	name {
		public String getValue() {
			return props.getProperty("name");
		}
	},
	serverIP {
		public String getValue() {
			return props.getProperty("serverIP");
		}
	},
	x_bank {
		public String getValue() {
			String str = props.getProperty("x_bank");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_bank {
		public String getValue() {
			String str = props.getProperty("y_bank");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_play {
		public String getValue() {
			String str = props.getProperty("x_play");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_play {
		public String getValue() {
			String str = props.getProperty("y_play");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_100 {
		public String getValue() {
			String str = props.getProperty("x_hundred");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_100 {
		public String getValue() {
			String str = props.getProperty("y_hundred");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_500 {
		public String getValue() {
			String str = props.getProperty("x_five_hundred");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_500 {
		public String getValue() {
			String str = props.getProperty("y_five_hundred");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_1000 {
		public String getValue() {
			String str = props.getProperty("x_thousand");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_1000 {
		public String getValue() {
			String str = props.getProperty("y_thousand");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_5000 {
		public String getValue() {
			String str = props.getProperty("x_five_thousand");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_5000 {
		public String getValue() {
			String str = props.getProperty("y_five_thousand");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_10000 {
		public String getValue() {
			String str = props.getProperty("x_ten_thousand");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	y_10000 {
		public String getValue() {
			String str = props.getProperty("y_ten_thousand");
			if (Check.checkStrIsInt(str))
				return str;
			else
				return null;
		}
	},
	x_done {
		public String getValue() {
			return props.getProperty("x_done");
		}
	},
	y_done {
		public String getValue() {
			return props.getProperty("y_done");
		}
	},
	x_reset {
		public String getValue() {
			return props.getProperty("x_reset");
		}
	},
	y_reset {
		public String getValue() {
			return props.getProperty("y_reset");
		}
	};
	
	Properties props = new Properties();
	
	ConfigEnum() {
		String address = "src/Data/client.properties";      //test
		//String address = "client.properties";
		
		try (InputStream in = Files.newInputStream(Paths.get(address))) {
			props.load(in);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			JTAMessage.printMessages(e + "");
		}
	}
	
	/**
	 * 获得各个属性的值
	 * 
	 * @return 返回值
	 */
	public abstract String getValue();
}
