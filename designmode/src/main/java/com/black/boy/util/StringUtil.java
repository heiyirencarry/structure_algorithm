package com.black.boy.util;

import com.black.boy.modeexception.ParamException;

public class StringUtil {
	
	public static String stringRide(String s, int count) {
		if(isEmpty(s)) {
			throw new ParamException("s["+s+"] not be empty");
		}
		if(count < 0) {
			throw new ParamException("count["+count+" <0 error]");
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < count; i++) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s);
	}
	
}