package utiliies;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileIO {
	private static Properties prop;

	public static Properties initProperties() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\resources\\Develop.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
