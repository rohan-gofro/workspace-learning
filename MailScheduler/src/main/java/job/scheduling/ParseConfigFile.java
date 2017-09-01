package job.scheduling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseConfigFile {

	private static final Logger LOG = LoggerFactory.getLogger(ParseConfigFile.class);

	public static HashMap<String, String> parseFile(File f) throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		String[] arg = new String[2];
		String temp;
		int lineno = 0;

		while ((line = br.readLine()) != null) {
			lineno++;
			if (line.length() == 0 || line.equals("")) {

			} else if (line.split("#").length != 2) {
				throw new IllegalArgumentException("Error parsing line " + lineno);
			}

			else {
				arg = line.split("#");
				arg[0] = arg[0].toLowerCase();
				if (arg[0].equals("cc") || arg[0].equals("bcc") || arg[0].equals("to")) {
					if (map.containsKey(arg[0])) {
						temp = map.get(arg[0]);
						map.put(arg[0].toLowerCase(), temp + "," + arg[1]);
					} else {
						map.put(arg[0], arg[1]);
					}
				} else if (arg[0].equals("message")) {
					if (map.containsKey(arg[0])) {
						temp = map.get(arg[0]);
						map.put(arg[0], temp + "\n" + arg[1]);
					} else {
						map.put(arg[0], arg[1]);
					}
				} else if (arg[0].equals("subject") || arg[0].equals("cron") || arg[0].equals("api-url")
						|| arg[0].equals("filename")|| arg[0].equals("from")) {
					if (map.containsKey(arg[0])) {
						throw new IllegalArgumentException("Mutiple declaration for " + arg[0] + " on line " + lineno);
					} else {
						map.put(arg[0], arg[1]);
					}
				} else {
					throw new IllegalArgumentException(arg[0] + " Argument not identified on line: " + lineno);
				}
			}
		}
		br.close();

		if (!(map.containsKey("to"))) {
			LOG.error("No TO Recipient found in config file");
			throw new IllegalArgumentException("No TO recipient found in config file");
		} else if (!(map.containsKey("subject"))) {
			LOG.error("No subject found in config file");
			throw new IllegalArgumentException("No subject found in config file");
		} else if (!(map.containsKey("cron"))) {
			LOG.error("No cron found in config file");
			throw new IllegalArgumentException("No CRON found in config file");
		}else if (!(map.containsKey("from"))) {
			LOG.error("No from found in config file");
			throw new IllegalArgumentException("No Sender found in config file");
		} else {
			LOG.info("Parsed Successfully");
		}
		return map;
	}

}
