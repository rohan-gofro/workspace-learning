import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropsFile {
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("configuration.properties");
		FileInputStream fis = new FileInputStream(file);
		String key = "aavishkar.abes@gmail.com";
		Properties props = new Properties();
		props.load(fis);
		System.out.println(props.getProperty(key));
		System.out.println(props.getProperty("something"));
		
	}

}
