package com.common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Logfile {

	static Logger logger = Logger.getLogger("com.common");

	/***
	 * write log into log file
	 * 
	 * @param logContent
	 *            log content
	 */
	public static void log(String logContent) {

		FileHandler fh;
		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler("/Users/zjw/Desktop/log.log");
//			fh = new FileHandler("D:/home/LogFiles/homeyLog.log");
			logger.addHandler(fh);
			// the following statement is used to log any messages
			logger.info(logContent);
			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}