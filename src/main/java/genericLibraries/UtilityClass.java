package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UtilityClass {
	
	static String ConfigProp= System.getProperty("user.dir")+"\\src\\main\\resources\\Config.Properties";
	
	public static String getPropertyFile(String key) 	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ConfigProp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = prop.getProperty(key);
		return value;
	}
	
	/*public static void main(String[] args) throws IOException {
		System.out.println(getPropertyFile("xlReferenceSheet"));
	}*/
}
