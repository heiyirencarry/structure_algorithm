package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ReadFileOperator {
	public static final String BASEFILE = "‪‪C:\\Users\\eBuy\\Desktop\\test.txt";
	public static final String OUT_FOLDER = "‪C:/Users/eBuy/Desktop/outfile/";
	public static final String PREFIX = "20191224dengluyouli";

	public static final int SPLIT_ARRAY_SIZE = 13;

	public static void main(String[] args) throws IOException {

		if (!checkInputFile()) {
			System.err.println("文件校验失败，请检查!");
			return;
		}

		try {
			FileInputStream input = new FileInputStream(new File(BASEFILE));
			try {
				testUpload(input, OUT_FOLDER);
			} finally {
				input.close();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static boolean checkInputFile() throws IOException {
		Charset fileEncoding = Charset.forName("GBK");
		FileInputStream input = null;
		BufferedReader reader = null;
		try {
//			File file = new File(BASEFILE);
			input = new FileInputStream(BASEFILE);
//			input = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(input, fileEncoding));
			String line = null;
			int lineIndex = 0;
//		    2018/12/08,4bc96ec09d6c6a265c3b7449c7729897,1k6tdo69,7221,201811,2018-12-15 00:00:01,2018-12-31 23:59:59,1^0,TWYC^TWYZ,,,729100,729111
			while ((line = reader.readLine()) != null) {
				if (lineIndex == 0) {
					lineIndex++;
//                	head = line;
//                	writer.write(head + "\r\n");
					continue;
				}

				String[] strLineInfo = line.split(",");

				// 判断是否字段数够
				if (strLineInfo.length != SPLIT_ARRAY_SIZE) {
					System.out.println("line index : " + lineIndex);
					System.out.println("split string array length illegal(not" + SPLIT_ARRAY_SIZE + ") : " + line);
					return false;
				}

				// 判断分行号字段非空
				if (isNullOrEmpty(strLineInfo[11])) {
					System.out.println("line index : " + lineIndex);
					System.out.println("citic branch code illegal: " + line);
					return false;
				}

				// 判断支行号非空
				if (isNullOrEmpty(strLineInfo[12])) {
					System.out.println("line index : " + lineIndex);
					System.out.println("citic sub branch code illegal: " + line);
					return false;
				}

				String[] rghCates = strLineInfo[8].split("\\^");
				String[] rghAmounts = strLineInfo[7].split("\\^");
				if (rghCates.length <= 0 || rghCates.length != rghAmounts.length) {
					System.out.println("line index : " + lineIndex);
					System.out.println("citic rihgts format illegal: " + line);
					System.out.println("type and count not matched; cust_id[" + strLineInfo[1] + "]");
					return false;
				} else {
					for (int j = 0; j < rghCates.length; j++) {
						if (isNullOrEmpty(rghCates[j]) || isNullOrEmpty(rghAmounts[j])) {
							System.out.println("line index : " + lineIndex);
							System.out.println("citic rihgts format illegal: " + line);
							return false;
						}
					}
				}
				lineIndex++;
			}
		} finally {
			reader.close();
			input.close();
		}

		return true;
	}

	private static void testUpload(InputStream input, String outFolder) throws IOException {

		Charset fileEncoding = Charset.forName("GBK");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, fileEncoding));
		try {
			int lineCount = 0;

			int file_index = 0;
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(outFolder, PREFIX.concat("_") + String.valueOf(file_index) + ".csv")),
					fileEncoding));
			file_index++;
//            String head = null;
			HashMap<String, Long> hashCount = new HashMap<String, Long>();
			try {
				while (true) {
					String line = reader.readLine();
					if (line == null) {
						break;
					}

					if (lineCount == 0) {
						lineCount++;
//                    	head = line;
//                    	writer.write(head + "\r\n");
						continue;
					}

					String[] strLineInfo = line.split(",");
					String[] rghCates = strLineInfo[8].split("\\^");
					String[] rghAmounts = strLineInfo[7].split("\\^");
					if (rghCates.length <= 0 || rghCates.length != rghAmounts.length) {
						System.out.println("type and count not matched; cust_id[" + strLineInfo[1] + "]");
					} else {
						for (int j = 0; j < rghCates.length; j++) {
							if (isNullOrEmpty(rghCates[j]) || isNullOrEmpty(rghAmounts[j])) {
								System.out.println("type or count empty; cust_id[" + strLineInfo[1] + "]");
								break;
							}

							if (hashCount.containsKey(rghCates[j]) == false) {
								hashCount.put(rghCates[j], new Long(rghAmounts[j]));
							} else {
								long addCount = hashCount.get(rghCates[j]).longValue() + Long.parseLong(rghAmounts[j]);
								hashCount.put(rghCates[j], new Long(addCount));
							}
						}
					}

					if (file_index == 1 ? (lineCount % 100001) == 0 : (lineCount % 100000) == 0) {
						// reset writer
						writer.close();
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(
										new File(outFolder, PREFIX.concat("_") + String.valueOf(file_index) + ".csv")),
								fileEncoding));
//                        writer.write(head + "\r\n");
						file_index++;
					}

					writer.write(line + "\r\n");
					lineCount++;
				}
			} finally {
				writer.close();
			}

			Set<String> setNames = hashCount.keySet();
			Iterator<String> iterNames = setNames.iterator();

			int total = 0;
			while (iterNames.hasNext()) {
				String paramName = iterNames.next();
				Long value = hashCount.get(paramName);
				total += value;
				System.out.println("TYPE[" + paramName + "]VALUE[" + value.longValue() + "]");
			}

			System.out.println("total_num: " + total);

		} finally {
			reader.close();
		}
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().equals("");
	}
}
