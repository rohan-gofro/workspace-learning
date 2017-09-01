package job.scheduling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.MissingFormatArgumentException;

import job.mail.EmailContent;
import job.mail.EmailRecipients;

public class Config {

	// ----------------Cron---------------------------
	private String cron;

	// Mail Configuration
	EmailRecipients emailRecipients;

	// Email Content
	EmailContent emailContent;

	public Config(File f) throws ParseException {

		if (!parseConfigFile(f)) {
			throw new ParseException("Error parsing config file", 0);
		}

	}

	public String getCron() {
		return cron;
	}

	public EmailRecipients getEmailRecipients() {
		return emailRecipients;
	}

	public EmailContent getEmailContent() {
		return emailContent;
	}

	// ------------Parse config file and set dedsired values--------------------
	private boolean parseConfigFile(File f) {
		String arg[] = new String[2];
		String cc, bcc, to, message, subject, filename, url , cron;
		cc = bcc = to = message = subject = filename = url = cron = null;
		String line;
		int lineno = 1;

		try {
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			
			while ((line = b.readLine()) != null) {
				if (line.length() == 0) {

				}

				else if (line.split("#").length != 2) {
					throw new IllegalArgumentException("Error in passing argument on line: " + lineno);
				}

				else {
					arg = line.split("#");
					if (arg[0].equals("cron")) {
						if (cron == null)
							cron = arg[1];
						else
							throw new IllegalArgumentException("Multiple cron passed in file");
					} else if (arg[0].equals("to")) {
						if (arg[1].contains(" ")) {
							throw new IllegalArgumentException(
									"Error on line:" + lineno + " emails must be only comma separated");
						} else {
							if (to == null) {
								to = arg[1];
							} else {
								to = to + "," + arg[1];
							}
						}
					}

					else if (arg[0].equals("cc")) {
						if (arg[1].contains(" ")) {
							throw new IllegalArgumentException(
									"Error on line:" + lineno + " emails must be only comma separated");
						} else {
							if (cc == null) {
								cc = arg[1];
							} else {
								cc = cc + "," + arg[1];
							}
						}
					}

					else if (arg[0].equals("bcc")) {
						if (arg[1].contains(" ")) {
							throw new IllegalArgumentException(
									"Error on line:" + lineno + " emails must be only comma separated");
						} else {
							if (bcc == null) {
								bcc = arg[1];
							} else {
								bcc = bcc + "," + arg[1];
							}
						}
					}

					else if (arg[0].equals("api-url")) {
						if (url == null) {
							url = arg[1];
						} else {
							throw new IllegalArgumentException("Multiple API URL not allowed");
						}
					} else if (arg[0].equals("filename")) {
						if (filename == null) {
							filename = arg[1];
						} else {
							throw new IllegalArgumentException("Multiple file names not allowed");
						}
					} else if (arg[0].equals("subject")) {
						if (subject == null) {
							subject = arg[1];
						} else {
							throw new IllegalArgumentException("Multiple Subjects not allowed");
						}
					}

					else if (arg[0].equals("message")) {
						if (message == null) {
							message = arg[1];
						} else {
							message = message + "\n" + arg[1];
						}
					}

					else {
						throw new IllegalArgumentException(lineno + " :" + arg[0] + " is not a valid type ");
					}
				}
				lineno++;
			}
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to locate file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error reading file");
			e.printStackTrace();
		}
		if (cron == null) {
			throw new NullPointerException("cron expression not found in file");
		}
		if (to == null && cc == null && bcc == null) {
			throw new NullPointerException("No recipient found");
		} 
		if (subject == null) {
			throw new MissingFormatArgumentException("Subject Missing");
		} else if (message == null) {
			throw new MissingFormatArgumentException("Message Missing");
		} else if (url == null) {
			throw new MissingFormatArgumentException("URL Missing");
		} else if (filename == null) {
			throw new MissingFormatArgumentException("Filename Missing");
		}
		emailRecipients = new EmailRecipients(to, cc, bcc);
		emailContent = new EmailContent(subject, message, filename, url);
		this.cron = cron;
		return true;
	}
}
