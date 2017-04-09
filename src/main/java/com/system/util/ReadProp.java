package com.system.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProp {

	// 属性文件的路径
	private static String profilepath = "prop.properties";
	/**
	 * 采用静态方法
	 */
	private static Properties props = new Properties();

	static {
		try {
			InputStream in = ReadProp.class.getClassLoader()
					.getResourceAsStream(profilepath);
			props.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取属性文件中相应键的值
	 * 
	 * @param key
	 *            主键
	 * @return String
	 */
	public static String getKeyValue(String key) {
		return props.getProperty(key);
	}
}
