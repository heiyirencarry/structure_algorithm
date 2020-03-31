package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author black boy
 *
 */
public class Test {

	public static void main(String args[]) {
		String s = "2020-01-17,1101,6A91967D,1101,20CS,2020-01-01 00:00:00,2020-12-31 23:59:59,1,CDYH001, , ,741100,741101,分行营业部                                       ";
		String[] ss = s.split(",");
		for (String g : ss) {
			System.out.println(replaceAllunUsedChar(g) + ";");
		}
//
//		try {
//			testFile();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
    private static String replaceAllunUsedChar(String val) {
        val = val.replaceAll("\"", "");
        val = val.replaceAll("'", "");
        val = val.replaceAll("	", "");
        val = val.trim();
        return val;
    }

	public static void testFile() throws FileNotFoundException {
		// C:\Users\eBuy\Desktop\test.txt
		File file = new File("C:\\Users\\eBuy\\Desktop\\test.txt");
		FileInputStream fileInp = new FileInputStream(file);
		System.out.println(fileInp);
	}

}
