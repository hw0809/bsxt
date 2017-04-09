package com.system.util;

public class StringUtil {

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
