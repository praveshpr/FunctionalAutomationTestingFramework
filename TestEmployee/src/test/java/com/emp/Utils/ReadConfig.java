package com.emp.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public  String configFile = System.getProperty("user.dir") + "\\config.properties";
	public ReadConfig() {
		File src = new File(configFile);

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	public String getUsername() {
		String username = pro.getProperty("userName");
		return username;
	}

	public String getPassword() {
		String password = pro.getProperty("password");
		return password;
	}

	public String getBrowser() {
		String password = pro.getProperty("browser");
		return password;
	}

	/*
	 * public String getChromePath() { String
	 * chromepath=pro.getProperty("chromepath"); return chromepath; }
	 * 
	 * public String getIEPath() { String iepath=pro.getProperty("iepath");
	 * return iepath; }
	 * 
	 * public String getFirefoxPath() { String
	 * firefoxpath=pro.getProperty("firefoxpath"); return firefoxpath; }
	 */

}
