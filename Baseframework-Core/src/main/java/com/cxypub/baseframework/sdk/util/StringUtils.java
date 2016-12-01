package com.cxypub.baseframework.sdk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.apache.commons.codec.binary.Hex;

/**
 * String工具类。
 * 
 * <p>
 * 提供了String的常用操作，基于其他开源组件提供二次封装。
 * </P>
 * 
 * @author huangb@pingtech.com.cn
 * @date 2011-1-10
 * @see org.apache.commons.lang.StringUtils
 */
public class StringUtils {
	//	public static final Character SQL_ESCAPE_STR = '/';

	//	/** 字母小写的标识 */
	//	final int LOWER_FLAG = -1;
	//	/** 字母大写的标识 */
	//	final int UPPER_FLAG = 1;

	enum CharacterFlag {
		Lower, Upper;
	}

	/**
	 * 使用可变参数进行字符串连接。
	 * <p>
	 * 示例：<br>
	 * join("1") = "1";<br>
	 * join("1","2") = "12";
	 * </p>
	 * 
	 * @param args
	 * @return string
	 */
	//	public static String join(String... args) {
	//		return org.apache.commons.lang.StringUtils.join(args);
	//	}

	/**
	 * 使用参数替换模板中的"{}"字样。
	 * <p>
	 * templates("Hello {}!","world") = "Hello world!";
	 * </p>
	 * 
	 * @param template
	 * @param args
	 * @return
	 * @author huangb@pingtech.com.cn
	 * @date 2010-01-18
	 */
	//	public static String templates(String template, String... args) {
	//		String result = template;
	//		for (String arg : args) {
	//			result = org.apache.commons.lang.StringUtils.replaceOnce(result,
	//					"{}", arg);
	//		}
	//		return result;
	//	}

	/**
	 * 从最后一个sub开始截取str，或者self为true将包含sub字符串，否则只截取最后一个sub以外的str。
	 * <p>
	 * 注意：若在str中查找不到sub则返回空字符串""
	 * </p>
	 * 
	 * @param str
	 * @param sub
	 * @param self
	 * @return str
	 */
	//	public static String lastSubString(String str, String sub, boolean self) {
	//		int li = str.lastIndexOf(sub);
	//		if (li == -1) {
	//			return "";
	//		}
	//		li = self ? li : li + 1;
	//		return str.substring(li);
	//	}

	/**
	 * 从最后一个sub开始截取str（包含sub字符串）。
	 * 
	 * @param str
	 * @param sub
	 * @return str
	 */
	//	public static String lastSubString(String str, String sub) {
	//		return lastSubString(str, sub, true);
	//	}

	/**
	 * 是否需要对该串进行字符串的更新。
	 * 
	 * 测试字符串中有无%或_标记。
	 * 
	 * @param str
	 * @return needEscape
	 * 
	 * @author huangb@pingtech.com.cn
	 * @date 2010-01-27
	 */
	//	public static Boolean needSqlLikeEscape(String str) {
	//		return str.indexOf("%") >= 0 || str.indexOf("_") >= 0;
	//	}

	/**
	 * 对传入字符串进行%及_的转义，默认使用/字符。
	 * 
	 * @param str
	 * @return escapeValue
	 * 
	 * @author huangb@pingtech.com.cn
	 * @date 2010-01-27
	 */
	//	public static String escapeSqlLike(String str) {
	//		String _str = String.valueOf(str);
	//		_str = _str.replaceAll("_", SQL_ESCAPE_STR + "_");
	//		_str = _str.replaceAll("%", SQL_ESCAPE_STR + "%");
	//		return _str;
	//	}

	/**
	 * 对传入字符串进行%及_的转义，查询此字符。对*及?转化为%或_的格式。而/*仍表示为*，/?仍表示为?字符。
	 * 
	 * TODO 目前先实现*到%，?到_的转义。即，1、2、3、5
	 * 
	 * 默认使用/字符。
	 * <p>
	 * 示例：
	 * 
	 * 1、huang*n = huang%b
	 * 
	 * 2、huang?n = huang_b
	 * 
	 * 3、huang% = huang/%
	 * 
	 * 4、huangb/ = huang//
	 * 
	 * 5、huangb_n = huangb/_n
	 * 
	 * 6、huangb/*n = huangb*n
	 * 
	 * 7、huangb/?n = huangb?n
	 * 
	 * </p>
	 * 
	 * @param str
	 * @return escapeValue
	 * 
	 * @author huangb@pingtech.com.cn
	 * @date 2010-01-27
	 */
	//	public static String escapeSqlLikeFormat(String str) {
	//		String _str = String.valueOf(str);
	//		_str = _str.replaceAll("_", SQL_ESCAPE_STR + "_");
	//		_str = _str.replaceAll("\\?", "_");
	//		_str = _str.replaceAll("%", SQL_ESCAPE_STR + "%");
	//		_str = _str.replaceAll("\\*", "%");
	//		return _str;
	//	}

	private static Object initLock = new Object();

	private static int[] m_Seed = new int[256];

	private static void InitRC4Encryption(String strPassword) {
		/***********************************************************************
		 * ' 根据密码（种子）字符串初始化加密种子数组 ' 数组有 256 个元素，各元素取值从 0 到 255，无重复值 '
		 * strPassword 为输入的密码（种子）
		 **********************************************************************/
		int i = 0, j = 0, lngA = 0;
		int[] aSeed = new int[256];
		if (strPassword == null || strPassword.length() == 0) {
			strPassword = "*";
		}
		@SuppressWarnings("unused")
		String tem_pas = "";
		byte[] bytes = new byte[0];

		// 将 Unicode 字符串处理成 ANSI 字符串，使密码可以接受汉字
		try {
			// strPassword = new String(strPassword.getBytes(), "UTF-16LE");
			// System.out.println(strPassword);
			bytes = strPassword.getBytes();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// strPassword = StrConv(strPassword, vbFromUnicode);
		// 生成密码数组序列。例如密码为 "12345"，数组序列为 "1234512345..."

		lngA = bytes.length;
		j = -1;
		for (i = 0; i <= 255; i++) {
			j = j + 1;
			if (j >= lngA) {
				j = 0;
			}
			if (bytes[j] < 0) {
				aSeed[i] = 256 + bytes[j];
			} else {
				aSeed[i] = bytes[j];
			}
		}

		// 种子数组序列各元素的初始值为 0 到 255 依次递增
		for (i = 0; i <= 255; i++) {
			m_Seed[i] = i;
		}

		// 根据密码数组序列重新排列种子数组序列的元素
		j = 0;
		for (i = 0; i <= 255; i++) {
			j = (j + m_Seed[i] + aSeed[i]) % 256;
			// 交换 m_Seed(i) 和 m_Seed(j) 的值
			lngA = m_Seed[i];
			m_Seed[i] = m_Seed[j];
			m_Seed[j] = lngA;
		}
	}

	public static String RC4EncryptionBS(String strPassword, byte[] bytIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Byte 数组，输出 Unicode 字符串 '
		 **********************************************************************/
		// return StrConv(RC4EncryptionByte(strPassword, bytIn), vbUnicode);
		return String.valueOf(RC4EncryptionByte(strPassword, bytIn));
	}

	public static String EncryptionBS(String strPassword, byte[] bytIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Byte 数组，输出 Unicode 字符串 '
		 **********************************************************************/
		// return StrConv(RC4EncryptionByte(strPassword, bytIn), vbUnicode);
		int[] intEncryResult = new int[0];
		String strEncryResult = "";
		try {
			intEncryResult = StringUtils.RC4EncryptionByte(strPassword, bytIn);
			strEncryResult = "";
			for (int element : intEncryResult) {
				strEncryResult = strEncryResult + (char) element;
			}
			return StringUtils.encodeBase64(strEncryResult.getBytes("ISO-8859-1")).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int[] RC4EncryptionByte(String strPassword, byte bytIn[]) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Byte 数组，输出 Byte 数组 '
		 **********************************************************************/

		int lngLength = 0;
		int lngA = 0, i, j, k;
		int[] aOut = new int[0];
		lngLength = bytIn.length;
		int[] intofByte = new int[lngLength];

		for (i = 0; i < bytIn.length; i++) {
			if (bytIn[i] < 0) {
				intofByte[i] = (256 + bytIn[i]);
			} else {
				intofByte[i] = bytIn[i];
			}
		}

		if (lngLength > 0) {
			aOut = new int[lngLength];
			InitRC4Encryption(strPassword);
			j = 0;
			k = 0;
			for (i = 1; i <= lngLength; i++) {
				j = (j + 1) % 256;
				k = (k + m_Seed[j]) % 256;
				// 交换 m_Seed(j) 和 m_Seed(k) 的值
				lngA = m_Seed[j];
				m_Seed[j] = m_Seed[k];
				m_Seed[k] = lngA;
				// 原文与加密种子数组的元素异或，生成密文
				// System.out.println(bytIn[i - 1] ^ m_Seed[(m_Seed[j] +
				// m_Seed[k]) % 256]);
				aOut[i - 1] = intofByte[i - 1] ^ m_Seed[(m_Seed[j] + m_Seed[k]) % 256];
			}
		}
		return aOut;
	}

	public static String RC4EncryptionSS(String strPassword, String strIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Unicode 字符串，输出 Unicode 字符串 '
		 **********************************************************************/
		// return StrConv(RC4EncryptionString(strPassword, strIn), vbUnicode);
		try {
			for (int i = 0; i < RC4EncryptionString(strPassword, strIn).length; i++) {
				// System.out.println(RC4EncryptionString(strPassword,
				// strIn)[i]);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return "s";
		// return String.valueOf(RC4EncryptionString(strPassword, strIn));

		// ByteBuffer bb=new ByteBuffer();
		// decoder.decode(String.valueOf(RC4EncryptionString(strPassword,
		// strIn)).getBytes());
	}

	public static byte[] RC4EncryptionStringofjava(String strPassword, String strIn) {
		return null;

	}

	public static byte[] RC4EncryptionString(String strPassword, String strIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Unicode 字符串，输出 Byte 数组 '
		 **********************************************************************/
		int lngLength, lngA, i, j, k;
		byte[] aOut = new byte[0];
		strIn = "";
		try {
			// strIn = StrConv(strIn, vbFromUnicode);
			try {
				strIn = new String(strIn.getBytes(), "UTF-16LE");
			} catch (Exception e) {
				e.printStackTrace();
			}
			lngLength = strIn.length();
			if (lngLength > 0) {
				aOut = new byte[lngLength];
				InitRC4Encryption(strPassword);
				j = 0;
				k = 0;
				for (i = 1; i < lngLength; i++) {
					j = (j + 1) % 256;
					k = (k + m_Seed[j]) % 256;
					// 交换 m_Seed(j) 和 m_Seed(k) 的值
					lngA = m_Seed[j];
					m_Seed[j] = m_Seed[k];
					m_Seed[k] = lngA;
					// 原文与加密种子数组的元素异或，生成密文
					aOut[i - 1] = (byte) (strIn.charAt(i) ^ m_Seed[(m_Seed[j] + m_Seed[k]) % 256]);
					// System.out.print(aOut[i - 1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return aOut;
	}

	/**
	 * Replaces all instances of oldString with newString in line.
	 * 
	 * @param line
	 *            the String to search to perform replacements on
	 * @param oldString
	 *            the String that should be replaced by newString
	 * @param newString
	 *            the String that will replace all instances of oldString
	 * @return a String will all instances of oldString replaced by newString
	 */
	//	public static final String replace(String line, String oldString,
	//			String newString) {
	//		if (line == null) {
	//			return null;
	//		}
	//		int i = 0;
	//		if ((i = line.indexOf(oldString, i)) >= 0) {
	//			char[] line2 = line.toCharArray();
	//			char[] newString2 = newString.toCharArray();
	//			int oLength = oldString.length();
	//			StringBuffer buf = new StringBuffer(line2.length);
	//			buf.append(line2, 0, i).append(newString2);
	//			i += oLength;
	//			int j = i;
	//			while ((i = line.indexOf(oldString, i)) > 0) {
	//				buf.append(line2, j, i - j).append(newString2);
	//				i += oLength;
	//				j = i;
	//			}
	//			buf.append(line2, j, line2.length - j);
	//			return buf.toString();
	//		}
	//		return line;
	//	}

	/**
	 * Replaces all instances of oldString with newString in line with the added
	 * feature that matches of newString in oldString ignore case.
	 * 
	 * @param line
	 *            the String to search to perform replacements on
	 * @param oldString
	 *            the String that should be replaced by newString
	 * @param newString
	 *            the String that will replace all instances of oldString
	 * @return a String will all instances of oldString replaced by newString
	 */
	//	public static final String replaceIgnoreCase(String line, String oldString,
	//			String newString) {
	//		if (line == null) {
	//			return null;
	//		}
	//		String lcLine = line.toLowerCase();
	//		String lcOldString = oldString.toLowerCase();
	//		int i = 0;
	//		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
	//			char[] line2 = line.toCharArray();
	//			char[] newString2 = newString.toCharArray();
	//			int oLength = oldString.length();
	//			StringBuffer buf = new StringBuffer(line2.length);
	//			buf.append(line2, 0, i).append(newString2);
	//			i += oLength;
	//			int j = i;
	//			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
	//				buf.append(line2, j, i - j).append(newString2);
	//				i += oLength;
	//				j = i;
	//			}
	//			buf.append(line2, j, line2.length - j);
	//			return buf.toString();
	//		}
	//		return line;
	//	}

	/**
	 * Replaces all instances of oldString with newString in line. The count
	 * Integer is updated with number of replaces.
	 * 
	 * @param line
	 *            the String to search to perform replacements on
	 * @param oldString
	 *            the String that should be replaced by newString
	 * @param newString
	 *            the String that will replace all instances of oldString
	 * @return a String will all instances of oldString replaced by newString
	 */
	//	public static final String replace(String line, String oldString,
	//			String newString, int[] count) {
	//		if (line == null) {
	//			return null;
	//		}
	//		int i = 0;
	//		if ((i = line.indexOf(oldString, i)) >= 0) {
	//			int counter = 0;
	//			counter++;
	//			char[] line2 = line.toCharArray();
	//			char[] newString2 = newString.toCharArray();
	//			int oLength = oldString.length();
	//			StringBuffer buf = new StringBuffer(line2.length);
	//			buf.append(line2, 0, i).append(newString2);
	//			i += oLength;
	//			int j = i;
	//			while ((i = line.indexOf(oldString, i)) > 0) {
	//				counter++;
	//				buf.append(line2, j, i - j).append(newString2);
	//				i += oLength;
	//				j = i;
	//			}
	//			buf.append(line2, j, line2.length - j);
	//			count[0] = counter;
	//			return buf.toString();
	//		}
	//		return line;
	//	}

	/**
	 * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
	 * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
	 * their HTML escape sequences.
	 * 
	 * @param input
	 *            the text to be converted.
	 * @return the input string with the characters '&lt;' and '&gt;' replaced
	 *         with their HTML escape sequences.
	 */
	//	public static final String escapeHTMLTags(String input) {
	//		// Check if the string is null or zero length -- if so, return
	//		// what was sent in.
	//		if (input == null || input.length() == 0) {
	//			return input;
	//		}
	//		// Use a StringBuffer in lieu of String concatenation -- it is
	//		// much more efficient this way.
	//		StringBuffer buf = new StringBuffer(input.length());
	//		char ch = ' ';
	//		for (int i = 0; i < input.length(); i++) {
	//			ch = input.charAt(i);
	//			if (ch == '<') {
	//				buf.append("&lt;");
	//			} else if (ch == '>') {
	//				buf.append("&gt;");
	//			} else {
	//				buf.append(ch);
	//			}
	//		}
	//		return buf.toString();
	//	}

	/**
	 * This method takes a string which may contain Quotation tags (ie, &quot;.
	 * etc) and converts the '"' characters to their HTML escape sequences.
	 * 
	 * @param input
	 *            the text to be converted.
	 * @return the input string with the characters '"' replaced with their HTML
	 *         escape sequences.
	 */
	//	public static final String escapeQuotationTags(String input) {
	//		// Check if the string is null or zero length -- if so, return
	//		// what was sent in.
	//		if (input == null || input.length() == 0) {
	//			return input;
	//		}
	//		// Use a StringBuffer in lieu of String concatenation -- it is
	//		// much more efficient this way.
	//		StringBuffer buf = new StringBuffer(input.length());
	//		char ch = ' ';
	//		for (int i = 0; i < input.length(); i++) {
	//			ch = input.charAt(i);
	//			if (ch == '"') {
	//				buf.append("&quot;");
	//			} else {
	//				buf.append(ch);
	//			}
	//		}
	//		return buf.toString();
	//	}

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	/**
	 * Hashes a String using the Md5 algorithm and returns the result as a
	 * String of hexadecimal numbers. This method is synchronized to avoid
	 * excessive MessageDigest object creation. If calling this method becomes a
	 * bottleneck in your code, you may wish to maintain a pool of MessageDigest
	 * objects instead of using this method. <p/> A hash is a one-way function --
	 * that is, given an input, an output is easily computed. However, given the
	 * output, the input is almost impossible to compute. This is useful for
	 * passwords since we can store the hash and a hacker will then have a very
	 * hard time determining the original password. <p/> take their plain text
	 * password, compute the hash, and compare the generated hash to the stored
	 * hash. Since it is almost impossible that two passwords will generate the
	 * same hash, we know if the user gave us the correct password or not. The
	 * only negative to this system is that password recovery is basically
	 * impossible. Therefore, a reset password method is used instead.
	 * 
	 * @param data
	 *            the String to compute the hash of.
	 * @return a hashed version of the passed-in String
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. " + "system will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return toHex(digest.digest());
	}

	/**
	 * 将字符串做 SHA1摘要
	 * @param message
	 * @return 摘要值十六进制转换后的字符串 
	 */
	@SuppressWarnings("static-access")
	public static String doSHA1(String message) {
		if (null == message) {
			return null;
		}
		java.security.MessageDigest alga = null;
		try {
			alga = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		//使用指定的字节数组对摘要进行最后更新，然后完成摘要计算。
		alga.update(message.getBytes());
		//计算SHA-1值
		byte[] digesta = alga.digest();

		//将字节数组转换成十六进制数	Hex 类在 commons-codec-1.3.jar中
		Hex hex = new Hex();
		return new String(hex.encodeHex(digesta));
	}

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number. <p/> Method by Santeri Paavolainen, Helsinki Finland
	 * 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Turns a hex encoded string into a byte array. It is specifically meant to
	 * "reverse" the toHex(byte[]) method.
	 * 
	 * @param hex
	 *            a hex encoded String to transform into a byte array.
	 * @return a byte array representing the hex String[
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Returns the the byte value of a hexadecmical char (0-f). It's assumed
	 * that the hexidecimal chars are lower case as appropriate.
	 * 
	 * @param ch
	 *            a hexedicmal character (0-f)
	 * @return the byte value of the character (0x00-0x0F)
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	// *********************************************************************
	// * Base64 - a simple base64 encoder and decoder.
	// *
	// * Copyright (c) 1999, Bob Withers - bwit@pobox.com
	// *
	// * This code may be freely used for any purpose, either personal
	// * or commercial, provided the authors copyright notice remains
	// * intact.
	// *********************************************************************

	/**
	 * Encodes a String as a base64 String.
	 * 
	 * @param data
	 *            a String to encode.
	 * @return a base64 encoded String.
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	/**
	 * Encodes a byte array into a base64 String.
	 * 
	 * @param data
	 *            a byte array to encode.
	 * @return a base64 encode String.
	 */
	public static String encodeBase64(byte[] data) {
		int c;
		int len = data.length;
		StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
		for (int i = 0; i < len; ++i) {
			c = (data[i] >> 2) & 0x3f;
			ret.append(cvt.charAt(c));
			c = (data[i] << 4) & 0x3f;
			if (++i < len) {
				c |= (data[i] >> 4) & 0x0f;
			}

			ret.append(cvt.charAt(c));
			if (i < len) {
				c = (data[i] << 2) & 0x3f;
				if (++i < len) {
					c |= (data[i] >> 6) & 0x03;
				}

				ret.append(cvt.charAt(c));
			} else {
				++i;
				ret.append((char) fillchar);
			}

			if (i < len) {
				c = data[i] & 0x3f;
				ret.append(cvt.charAt(c));
			} else {
				ret.append((char) fillchar);
			}
		}
		return ret.toString();
	}

	/**
	 * Decodes a base64 String.
	 * 
	 * @param data
	 *            a base64 encoded String to decode.
	 * @return the decoded String.
	 */
	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	/**
	 * Decodes a base64 aray of bytes.
	 * 
	 * @param data
	 *            a base64 encode byte array to decode.
	 * @return the decoded String.
	 */
	public static String decodeBase64(byte[] data) {
		int c, c1;
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; ++i) {
			c = cvt.indexOf(data[i]);
			++i;
			c1 = cvt.indexOf(data[i]);
			c = ((c << 2) | ((c1 >> 4) & 0x3));
			ret.append((char) c);
			if (++i < len) {
				c = data[i];
				if (fillchar == c) {
					break;
				}

				c = cvt.indexOf((char) c);
				c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
				ret.append((char) c1);
			}

			if (++i < len) {
				c1 = data[i];
				if (fillchar == c1) {
					break;
				}

				c1 = cvt.indexOf((char) c1);
				c = ((c << 6) & 0xc0) | c1;
				ret.append((char) c);
			}
		}
		return ret.toString();
	}

	private static final int fillchar = '=';

	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number. <p/> Method by Santeri Paavolainen, Helsinki Finland
	 * 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param hash
	 *            an rray of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String toHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if ((hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(hash[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Converts a line of text into an array of lower case words. Words are
	 * delimited by the following characters: , .\r\n:/\+ <p/> In the future,
	 * this method should be changed to use a BreakIterator.wordInstance(). That
	 * class offers much more fexibility.
	 * 
	 * @param text
	 *            a String of text to convert into an array of words
	 * @return text broken up into an array of words.
	 */
	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}
		StringTokenizer tokens = new StringTokenizer(text, " ,\r\n.:/\\+");
		String[] words = new String[tokens.countTokens()];
		for (int i = 0; i < words.length; i++) {
			words[i] = tokens.nextToken().toLowerCase();
		}
		return words;
	}

	/**
	 * A list of some of the most common words. For searching and indexing, we
	 * often want to filter out these words since they just confuse searches.
	 * The list was not created scientifically so may be incomplete :)
	 */
	private static final String[] commonWords = new String[] { "a", "and", "as", "at", "be", "do", "i", "if", "in", "is", "it", "so", "the", "to" };

	@SuppressWarnings("unchecked")
	private static Map commonWordsMap = null;

	/**
	 * Returns a new String array with some of the most common English words
	 * removed. The specific words removed are: a, and, as, at, be, do, i, if,
	 * in, is, it, so, the, to
	 */
	@SuppressWarnings("unchecked")
	public static final String[] removeCommonWords(String[] words) {
		// See if common words map has been initialized. We don't statically
		// initialize it to save some memory. Even though this a small savings,
		// it adds up with hundreds of classes being loaded.
		if (commonWordsMap == null) {
			synchronized (initLock) {
				if (commonWordsMap == null) {
					commonWordsMap = new HashMap();
					for (String commonWord : commonWords) {
						commonWordsMap.put(commonWord, commonWord);
					}
				}
			}
		}
		// Now, add all words that aren't in the common map to results
		ArrayList results = new ArrayList(words.length);
		for (int i = 0; i < words.length; i++) {
			if (!commonWordsMap.containsKey(words[i])) {
				results.add(words[i]);
			}
		}
		return (String[]) results.toArray(new String[results.size()]);
	}

	/**
	 * Pseudo-random number generator object for use with randomString(). The
	 * Random class is not considered to be cryptographically secure, so only
	 * use these random Strings for low to medium security applications.
	 */
	private static Random randGen = null;

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = null;

	/**
	 * Returns a random String of numbers and letters of the specified length.
	 * The method uses the Random class that is built-in to Java which is
	 * suitable for low to medium grade security uses. This means that the
	 * output is only pseudo random, i.e., each number is mathematically
	 * generated so is not truly random.
	 * <p>
	 * <p/> For every character in the returned String, there is an equal chance
	 * that it will be a letter or number. If a letter, there is an equal chance
	 * that it will be lower or upper case.
	 * <p>
	 * <p/> The specified length must be at least one. If not, the method will
	 * return null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomStringOfStr(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(26)];
		}
		return new String(randBuffer);
	}

	/**
	 * Intelligently chops a String at a word boundary (whitespace) that occurs
	 * at the specified index in the argument or before. However, if there is a
	 * newline character before <code>length</code>, the String will be
	 * chopped there. If no newline or whitespace is found in
	 * <code>string</code> up to the index <code>length</code>, the String
	 * will chopped at <code>length</code>. <p/> For example,
	 * chopAtWord("This is a nice String", 10) will return "This is a" which is
	 * the first word boundary less than or equal to 10 characters into the
	 * original String.
	 * 
	 * @param string
	 *            the String to chop.
	 * @param length
	 *            the index in <code>string</code> to start looking for a
	 *            whitespace boundary at.
	 * @return a substring of <code>string</code> whose length is less than or
	 *         equal to <code>length</code>, and that is chopped at
	 *         whitespace.
	 */
	public static final String chopAtWord(String string, int length) {
		if (string == null) {
			return string;
		}

		int sLength = string.length();
		char[] charArray = string.toCharArray();
		if (length < sLength) {
			sLength = length;
		}

		// First check if there is a newline character before length; if so,
		// chop word there.
		for (int i = 0; i < sLength - 1; i++) {
			// Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i);
			}
			// Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		// Also check boundary case of Unix newline
		if (sLength - 1 >= 0) {
			if (charArray[sLength - 1] == '\n') {
				return string.substring(0, sLength - 1);
			}
		}

		// Done checking for newline, now see if the total string is less than
		// the specified chop point.
		if (string.length() < length) {
			return string;
		}
		/*
		 * //No newline, so chop at the first whitespace. for (int i = length -
		 * 1; i > 0; i--) { if (charArray[i] == ' ') { return string.substring
		 * (0, i).trim (); } }
		 */
		// Did not find word boundary so return original String chopped at
		// specified length.
		return string.substring(0, length);
	}

	/**
	 * Highlights words in a string. Words matching ignores case. The actual
	 * higlighting method is specified with the start and end higlight tags.
	 * Those might be beginning and ending HTML bold tags, or anything else.
	 * 
	 * @param string
	 *            the String to highlight words in.
	 * @param words
	 *            an array of words that should be highlighted in the string.
	 * @param startHighlight
	 *            the tag that should be inserted to start highlighting.
	 * @param endHighlight
	 *            the tag that should be inserted to end highlighting.
	 * @return a new String with the specified words highlighted.
	 */
	//	public static final String highlightWords(String string, String[] words,
	//			String startHighlight, String endHighlight) {
	//		if (string == null || words == null || startHighlight == null
	//				|| endHighlight == null) {
	//			return null;
	//		}
	//
	//		// Iterate through each word.
	//		for (int x = 0; x < words.length; x++) {
	//			// we want to ignore case.
	//			String lcString = string.toLowerCase();
	//			// using a char [] is more efficient
	//			char[] string2 = string.toCharArray();
	//			String word = words[x].toLowerCase();
	//
	//			// perform specialized replace logic
	//			int i = 0;
	//			if ((i = lcString.indexOf(word, i)) >= 0) {
	//				int oLength = word.length();
	//				StringBuffer buf = new StringBuffer(string2.length);
	//
	//				// we only want to highlight distinct words and not parts of
	//				// larger words. The method used below mostly solves this. There
	//				// are a few cases where it doesn't, but it's close enough.
	//				boolean startSpace = false;
	//				char startChar = ' ';
	//				if (i - 1 > 0) {
	//					startChar = string2[i - 1];
	//					if (!Character.isLetter(startChar)) {
	//						startSpace = true;
	//					}
	//				}
	//				boolean endSpace = false;
	//				char endChar = ' ';
	//				if (i + oLength < string2.length) {
	//					endChar = string2[i + oLength];
	//					if (!Character.isLetter(endChar)) {
	//						endSpace = true;
	//					}
	//				}
	//				if ((startSpace && endSpace) || (i == 0 && endSpace)) {
	//					buf.append(string2, 0, i);
	//					if (startSpace && startChar == ' ') {
	//						buf.append(startChar);
	//					}
	//					buf.append(startHighlight);
	//					buf.append(string2, i, oLength).append(endHighlight);
	//					if (endSpace && endChar == ' ') {
	//						buf.append(endChar);
	//					}
	//				} else {
	//					buf.append(string2, 0, i);
	//					buf.append(string2, i, oLength);
	//				}
	//
	//				i += oLength;
	//				int j = i;
	//				while ((i = lcString.indexOf(word, i)) > 0) {
	//					startSpace = false;
	//					startChar = string2[i - 1];
	//					if (!Character.isLetter(startChar)) {
	//						startSpace = true;
	//					}
	//
	//					endSpace = false;
	//					if (i + oLength < string2.length) {
	//						endChar = string2[i + oLength];
	//						if (!Character.isLetter(endChar)) {
	//							endSpace = true;
	//						}
	//					}
	//					if ((startSpace && endSpace)
	//							|| i + oLength == string2.length) {
	//						buf.append(string2, j, i - j);
	//						if (startSpace && startChar == ' ') {
	//							buf.append(startChar);
	//						}
	//						buf.append(startHighlight);
	//						buf.append(string2, i, oLength).append(endHighlight);
	//						if (endSpace && endChar == ' ') {
	//							buf.append(endChar);
	//						}
	//					} else {
	//						buf.append(string2, j, i - j);
	//						buf.append(string2, i, oLength);
	//					}
	//					i += oLength;
	//					j = i;
	//				}
	//				buf.append(string2, j, string2.length - j);
	//				string = buf.toString();
	//			}
	//		}
	//		return string;
	//	}

	/**
	 * Escapes all necessary characters in the String so that it can be used in
	 * an XML doc.
	 * 
	 * @param string
	 *            the string to escape.
	 * @return the string with appropriate characters escaped.
	 */
	//	public static final String escapeForXML(String string) {
	//		// Check if the string is null or zero length -- if so, return
	//		// what was sent in.
	//		if (string == null || string.length() == 0) {
	//			return string;
	//		}
	//		char[] sArray = string.toCharArray();
	//		StringBuffer buf = new StringBuffer(sArray.length);
	//		char ch;
	//		for (int i = 0; i < sArray.length; i++) {
	//			ch = sArray[i];
	//			if (ch == '<') {
	//				buf.append("&lt;");
	//			} else if (ch == '&') {
	//				buf.append("&amp;");
	//			} else if (ch == '"') {
	//				buf.append("&quot;");
	//			} else {
	//				buf.append(ch);
	//			}
	//		}
	//		return buf.toString();
	//	}

	//	/* added by wangshupeng 2003.4.30 */
	//	public static final String[] getDateQueryString(String strBefore,
	//			String strEnd) {
	//		if (strBefore == null || strEnd == null || strBefore.equals("")
	//				|| strEnd.equals(""))
	//			return null;
	//
	//		String[] strDateQuery = new String[2];
	//		String[] strDateArr = null;
	//		String[] strTimeArr = null;
	//		Calendar calendar = null;
	//		calendar = Calendar.getInstance();
	//
	//		try {
	//			String[] strBeforeArr = strBefore.split(" ");
	//			String[] strEndArr = strEnd.split(" ");
	//
	//			/* 得到开始日期字符串 */
	//
	//			strDateArr = strBeforeArr[0].split("-");
	//			strTimeArr = strBeforeArr[1].split(":");
	//
	//			if (strDateArr == null || strTimeArr == null)
	//				return null;
	//
	//			calendar.set(Integer.parseInt(strDateArr[0]), Integer
	//					.parseInt(strDateArr[1]) - 1, Integer
	//					.parseInt(strDateArr[2]), Integer.parseInt(strTimeArr[0]),
	//					Integer.parseInt(strTimeArr[1]), Integer
	//							.parseInt(strTimeArr[2]));
	//			strDateQuery[0] = Long.toString(calendar.getTimeInMillis());
	//
	//			/* 得到结束日期字符串 */
	//			strDateArr = strEndArr[0].split("-");
	//			strTimeArr = strEndArr[1].split(":");
	//
	//			if (strDateArr == null || strTimeArr == null)
	//				return null;
	//
	//			calendar.set(Integer.parseInt(strDateArr[0]), Integer
	//					.parseInt(strDateArr[1]) - 1, Integer
	//					.parseInt(strDateArr[2]), Integer.parseInt(strTimeArr[0]),
	//					Integer.parseInt(strTimeArr[1]), Integer
	//							.parseInt(strTimeArr[2]));
	//			strDateQuery[1] = Long.toString(calendar.getTimeInMillis());
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return null;
	//		}
	//
	//		return strDateQuery;
	//
	//	}

	//	public static final String getDateString(String strDateTime) {
	//		if (strDateTime == null)
	//			return null;
	//
	//		String strRet = null;
	//		String[] strDateArr = null;
	//		String[] strTimeArr = null;
	//		Calendar calendar = null;
	//		calendar = Calendar.getInstance();
	//
	//		try {
	//			String[] strDateTimeArr = strDateTime.split(" ");
	//
	//			/* 得到日期时间字符串 */
	//
	//			strDateArr = strDateTimeArr[0].split("-");
	//			strTimeArr = strDateTimeArr[1].split(":");
	//
	//			if (strDateArr == null || strTimeArr == null)
	//				return null;
	//
	//			calendar.set(Integer.parseInt(strDateArr[0]), Integer
	//					.parseInt(strDateArr[1]) - 1, Integer
	//					.parseInt(strDateArr[2]), Integer.parseInt(strTimeArr[0]),
	//					Integer.parseInt(strTimeArr[1]), Integer
	//							.parseInt(strTimeArr[2]));
	//			strRet = Long.toString(calendar.getTimeInMillis());
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return null;
	//		}
	//
	//		return strRet;
	//	}

	//	public static final java.util.Date stringToDate(String strDateTime) {
	//		if (strDateTime == null)
	//			return null;
	//
	//		java.util.Date date = null;
	//		String[] strDateArr = null;
	//		String[] strTimeArr = null;
	//		Calendar calendar = null;
	//		calendar = Calendar.getInstance();
	//
	//		try {
	//			String[] strDateTimeArr = strDateTime.split(" ");
	//
	//			if (strDateTimeArr == null)
	//				return null;
	//
	//			/* 得到日期和时间字符串 */
	//
	//			strDateArr = strDateTimeArr[0].split("-");
	//			strTimeArr = strDateTimeArr[1].split(":");
	//
	//			if (strDateArr == null || strTimeArr == null)
	//				return null;
	//
	//			calendar.set(Integer.parseInt(strDateArr[0]), Integer
	//					.parseInt(strDateArr[1]) - 1, Integer
	//					.parseInt(strDateArr[2]), Integer.parseInt(strTimeArr[0]),
	//					Integer.parseInt(strTimeArr[1]), Integer
	//							.parseInt(strTimeArr[2]));
	//
	//			date = calendar.getTime();
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return null;
	//		}
	//
	//		return date;
	//	}

	//	/**
	//	 * 对字符串进行解码处理
	//	 * 
	//	 * @param s
	//	 *            要转换的字符串
	//	 * @return 返回转换后的字符串
	//	 */
	//	public static String decodeWord(String s) {
	//		if (s == null || "".equals(s))
	//			return s;
	//		if (!s.startsWith("=?")) {
	//			try {
	//				return new String(s.getBytes("ISO-8859-1"), "GB2312");
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//				System.out.println("decodeWord():convert string failed!");
	//				return s;
	//			}
	//		}
	//		if (s.startsWith("=?UTF-8")) {
	//			String endStr = "";
	//			String[] UTFStr = s.split("\r\n");
	//			for (int k = 0; k < UTFStr.length; k++) {
	//				try {
	//					endStr += javax.mail.internet.MimeUtility
	//							.decodeWord(UTFStr[k].trim());
	//				} catch (ParseException e1) {
	//					// TODO Auto-generated catch block
	//					e1.printStackTrace();
	//				} catch (UnsupportedEncodingException e1) {
	//					// TODO Auto-generated catch block
	//					e1.printStackTrace();
	//				}
	//			}
	//			return endStr;
	//		}
	//		if (s.indexOf("=?") != -1) {
	//			int i = 2;
	//			int j;
	//
	//			if ((j = s.indexOf(63, i)) == -1)
	//				return s;
	//
	//			@SuppressWarnings("unused")
	//			String s1 = (s.substring(i, j));
	//			i = j + 1;
	//			if ((j = s.indexOf(63, i)) == -1)
	//				return s;
	//			String s2 = s.substring(i, j);
	//			i = j + 1;
	//			if ((j = s.indexOf("?=", i)) == -1)
	//				return s;
	//
	//			String s3 = s.substring(i, j);
	//			String s4 = decodeWord(s.substring(j + 2).trim());
	//			try {
	//				ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
	//						s3.getBytes());
	//				Object obj;
	//				if (s2.equalsIgnoreCase("B"))
	//					obj = new com.sun.mail.util.BASE64DecoderStream(
	//							bytearrayinputstream);
	//				else if (s2.equalsIgnoreCase("Q"))
	//					obj = new com.sun.mail.util.QDecoderStream(
	//							bytearrayinputstream);
	//				else
	//					return s;
	//				int k = bytearrayinputstream.available();
	//				byte abyte0[] = new byte[k];
	//				k = ((InputStream) (obj)).read(abyte0, 0, k);
	//
	//				if (s4 != null)
	//					return ((new String(abyte0, 0, k)) + s4);// (new
	//																// String(s4.getBytes("ISO-8859-1"),"GB2312")));
	//				else
	//					return new String(abyte0, 0, k);
	//			} catch (Exception e) {
	//				return s;
	//			}
	//		}
	//
	//		return s;
	//	}

	//	public static String TagUrltoA(String sStr) {
	//		int startpos = 0;
	//		int endpos = 0;
	//		String oldstr = "";
	//		String newstr = "";
	//		String returnstr = "";
	//		if (sStr == null || sStr.equals("")) {
	//			returnstr = sStr;
	//		}
	//		try {
	//			startpos = sStr.indexOf("[url=", 0);
	//			endpos = sStr.indexOf("[/url]", 0) + 6;
	//			oldstr = sStr.substring(startpos, endpos);
	//			newstr = StringUtils.replace(oldstr, "[url=", "<a href=");
	//			newstr = StringUtils.replace(newstr, "[/url]", "</a>");
	//			newstr = StringUtils.replace(newstr, "]", ">");
	//			returnstr = StringUtils.replace(sStr, oldstr, newstr);
	//		} catch (Exception ee) {
	//			returnstr = sStr;
	//		}
	//		return returnstr;
	//	}

	//	public static String cutLinkTag(String sStr) {
	//		int startpos = 0;
	//		int endpos = 0;
	//		String oldstr = "";
	//		String newstr = "";
	//		String returnstr = "";
	//		if (sStr == null || sStr.equals("")) {
	//			returnstr = sStr;
	//		}
	//		try {
	//			startpos = sStr.indexOf("[url=", 0);
	//			endpos = sStr.indexOf("[/url]", 0) + 6;
	//			oldstr = sStr.substring(startpos, endpos);
	//			startpos = oldstr.indexOf("]", 0);
	//			endpos = oldstr.indexOf("[/url]", 0);
	//			newstr = oldstr.substring(startpos + 1, endpos);
	//			returnstr = StringUtils.replace(sStr, oldstr, newstr);
	//		} catch (Exception ee) {
	//			returnstr = sStr;
	//		}
	//		return returnstr;
	//	}

	//	public static String fillUpZero(String p_str, int p_long) {
	//		int num = p_long - p_str.length();
	//		if (num <= 0)
	//			return p_str;
	//		for (int i = 0; i < num; i++) {
	//			p_str = "0" + p_str;
	//		}
	//		return p_str;
	//	}

	//	public static String RC4(String inp, String key) {
	//
	//		int[] S = new int[256];
	//		int[] K = new int[256];
	//		int i = 0;
	//		int j = 0;
	//		int temp;
	//		int Y;
	//		int t = 0;
	//		int x = 0;
	//		String Outp = "";
	//		for (i = 0; i <= 255; i++) {
	//			S[i] = i;
	//		}
	//		j = 0;
	//		for (i = 0; i <= 255; i++) {
	//			if (j >= key.length())
	//				j = 0;
	//			K[i] = key.charAt(j);
	//			j = j + 1;
	//		}
	//		j = 0;
	//		for (i = 0; i <= 255; i++) {
	//			j = (j + S[i] + K[i]) % 256;
	//			temp = S[i];
	//			S[i] = S[j];
	//			S[j] = temp;
	//		}
	//		i = 0;
	//		j = 0;
	//		for (x = 0; x < inp.length(); x++) {
	//			i = (i + 1) % 256;
	//			j = (j + S[i]) % 256;
	//			temp = S[i];
	//			S[i] = S[j];
	//			S[j] = temp;
	//			t = (S[i] + (S[j] % 256)) % 256;
	//			Y = S[t];
	//			Outp = Outp + (char) (inp.charAt(x) ^ Y);
	//			try {
	//				Outp = java.net.URLEncoder.encode(Outp, "GB2312");
	//
	//			} catch (Exception ee) {
	//				ee.printStackTrace();
	//			}
	//		}
	//		return Outp;
	//	}

	/* added by zengjy 2003.4.30 *//*
									 * public static final String
									 * highlightWordsII(String string, String[]
									 * words, String startHighlight, String
									 * endHighlight) { if (string == null ||
									 * words == null || startHighlight == null ||
									 * endHighlight == null) { return null; }
									 * byte[][] wordsBytes = new
									 * byte[words.length][]; byte[] stringBytes =
									 * null; byte[] startBytes = null; byte[]
									 * endBytes = null; try { for (int i = 0; i <
									 * words.length; i++) { wordsBytes[i] =
									 * words[i].getBytes(); } stringBytes =
									 * string.getBytes("GB2312"); startBytes =
									 * startHighlight.getBytes("GB2312");
									 * endBytes =
									 * endHighlight.getBytes("GB2312"); } catch
									 * (Exception e) { return null; }
									 * 
									 * ByteBuffer resultStringBytes = new
									 * ByteBuffer(); if (wordsBytes != null &&
									 * stringBytes != null) { int j = 0; int n =
									 * 0; for (int i = 0; i <
									 * stringBytes.length; i++) { for (n = 0; n <
									 * wordsBytes.length; n++) { for (j = 0; j <
									 * wordsBytes[n].length; j++) { try { if
									 * (stringBytes[i + j] != wordsBytes[n][j]) {
									 * break; } } catch (Exception e) { break; } }
									 * if (j == wordsBytes[n].length) { break; } }
									 * if (n >= wordsBytes.length || (n <
									 * wordsBytes.length && j !=
									 * wordsBytes[n].length)) {
									 * resultStringBytes.appendByte(stringBytes[i]);
									 * continue; } else if (n <
									 * wordsBytes.length) { for (j = 0; j <
									 * startBytes.length; j++) {
									 * resultStringBytes.appendByte(startBytes[j]); }
									 * for (j = 0; j < wordsBytes[n].length;
									 * j++) {
									 * resultStringBytes.appendByte(wordsBytes[n][j]); }
									 * i += j - 1; for (j = 0; j <
									 * endBytes.length; j++) {
									 * resultStringBytes.appendByte(endBytes[j]); } } } }
									 * try { return new
									 * String(resultStringBytes.elems,
									 * "GB2312"); } catch (Exception e) { return
									 * null; } }
									 */

	//	/**
	//	 * 生成缓冲的key
	//	 * 
	//	 * @param cacheTypeCode
	//	 *            对象类型编号
	//	 * @param cacheObjectID
	//	 *            对象的id号
	//	 * @return 缓冲对象的编号
	//	 */
	//	public static long getCacheKey(int cacheTypeCode, long cacheObjectID) {
	//		long lcacheTypeCode = cacheTypeCode;
	//		long lcacheObjectID = cacheObjectID;
	//		long empty = 0L;
	//		long cachekey = 0L;
	//		try {
	//			lcacheObjectID = cacheObjectID << 8;
	//			empty = lcacheObjectID & 0xFFFFFFFFFFFFFF00L;
	//			cachekey = empty ^ lcacheTypeCode;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return cachekey;
	//	}

	//	/**
	//	 * 通过缓冲的key获得对象的类型和对象的id
	//	 * 
	//	 * @param cacheKey
	//	 * @return 数组返回两个值下标为[0]是类型编号 [1]对象的id号
	//	 */
	//
	//	public static long[] getCacheObjectID(long cacheKey) {
	//		long cacheTypeCode = 0L;
	//		long cacheObjectID = 0L;
	//		long[] result = new long[2];
	//		try {
	//			cacheTypeCode = cacheKey & 0xFFL;
	//			cacheObjectID = (cacheKey >> 8) & 0x00FFFFFFFFFFFFFFL;
	//			result[0] = cacheTypeCode;
	//			result[1] = cacheObjectID;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return result;
	//	}

	/**
	 * 去除掉字符串中第一次以给定字符串开头和结尾的部分
	 * 
	 * @param aimStr
	 *            String 目标字符串
	 * @param beginStr
	 *            String 起始字符串
	 * @param endStr
	 *            String 结束字符串
	 * @return String
	 */
	public static String trimStr(String aimStr, String beginStr, String endStr) {
		String newStr = aimStr;
		int iStart = aimStr.indexOf(beginStr);
		int iEnd = aimStr.indexOf(endStr) + endStr.length();
		if (iStart != -1 && iEnd != -1) {
			newStr = aimStr.substring(0, iStart) + aimStr.substring(iEnd, aimStr.length());
		}
		return newStr;
	}

	//	/**
	//	 * 比较两个字符串大小 firstStr>secStr 则返回值>0 否则<0
	//	 * 
	//	 * @param firstStr
	//	 * @param secStr
	//	 * @return
	//	 */
	//	public static int compareStr(String firstStr, String secStr) {
	//		@SuppressWarnings("unused")
	//		char[] firstChars = firstStr.toCharArray();
	//		@SuppressWarnings("unused")
	//		char[] secChars = secStr.toCharArray();
	//		return 0;
	//	}

	// 根据系统配置中的假期设置判断是否是节假日
	@SuppressWarnings("deprecation")
	public static boolean isHoliday(Date date, String holidays) {
		/*
		 * 节假日的定义: 周六,周日, 1.1--1.3; 5.1--5.7;
		 */
		if (date.getDay() == 0 || date.getDay() == 6) {
			return true;
		}
		//		String holidays = PropertyManager.getProperty("holidays");
		holidays = holidays != null ? holidays : "";
		String[] holidayarr = holidays.split(",");
		for (String element : holidayarr) {
			// String holidaystr = todayYear + "-" + holidayarr[i];
			try {
				String[] yd = element.split("-");
				if (yd[0].trim().equals(date.getMonth() + 1 + "") && yd[1].trim().equals(date.getDate() + "")) {
					return true;
					// if(holiday.compareTo(date) == 0)
					// return true;
				}
			} catch (Exception e) {
				System.out.println("节假日" + element + "有误");
			}

		}
		return false;
	}

	/**
	 * 把类似于 |fsdjl|fjskadlfj|fjskafj| 这样的id串拼成类似于 'fsdjl','fjskadlfj','fjskafj'
	 * 这样的串返回(删除中间""空字符串) cutStringToArray(str, "|", "'", ",")
	 * 
	 * @param str
	 * @param split
	 * @param replacement
	 * @param replacementSplit
	 * @return
	 */
	public static String regroupString(String str, String split, String replacement, String replacementSplit) {
		if (str == null) {
			return "";
		}
		String[] strArr = str.split(split);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < strArr.length; i++) {
			if (!"".equals(strArr[i])) {
				sb.append(replacement + strArr[i] + replacement + replacementSplit);
			}
		}
		// 去掉最后一个replacementSplit
		if (sb.length() > replacementSplit.length()) {
			sb.deleteCharAt(sb.length() - replacementSplit.length());
		}
		return sb.toString();
	}

	/**
	 * 把String[] 拼成 一个String。
	 * 如：regroupStringArr(new String[]{"a", "b", "c"}, "#", "%", ",") 返回  #a%,#b%,#c%
	 * @param strArr
	 * @param split
	 * @return
	 */
	public static String regroupStringArr(String[] strArr, String prefix, String subfix, String split) {
		if (strArr == null || strArr.length == 0) {
			return "";
		}
		if (prefix == null) {
			prefix = "";
		}
		if (subfix == null) {
			subfix = "";
		}
		if (split == null) {
			split = "";
		}
		StringBuffer returnValue = new StringBuffer("");
		for (String element : strArr) {
			returnValue.append(prefix).append(element).append(subfix).append(split);
		}
		// 去掉最后一个split
		if (split.length() > 0) {
			returnValue.deleteCharAt(returnValue.length() - split.length());
		}
		return returnValue.toString();
	}

	/**
	 * 把String 拼成 一个String[]。
	 * @param str
	 * @param split  默认是","
	 * @return
	 */
	public static String[] regroupStringArr(String str, String split) {
		if (str == null) {
			return null;
		}
		if (split == null) {
			split = ",";
		}
		String[] returnStrArr;
		String[] strArr = str.split(split);
		boolean[] booArr = new boolean[strArr.length];//判断每一项是否符合要求，符合记为true。
		int count = 0;//符合要求的总数
		for (int i = 0; i < strArr.length; i++) {
			if (!strArr[i].trim().equals("")) {
				count++;
				booArr[i] = true;
			} else {
				booArr[i] = false;
			}
		}
		returnStrArr = new String[count];
		count = 0;
		for (int i = 0; i < booArr.length; i++) {
			if (booArr[i]) {
				returnStrArr[count++] = strArr[i];
			}
		}
		return returnStrArr;
	}

	/**
	 * 解析HttpServletRequest传的参数。解析成为一个二维数组。
	 * @param str
	 * @return
	 */
	//	@SuppressWarnings("unchecked")
	//	public static HashMap parseRequestParams(String str){
	//		String[] tempArr = regroupStringArr(str, "&");
	//		if(tempArr == null || tempArr.length == 0){
	//			return null;
	//		}
	//		HashMap result = new HashMap();
	//		for (int i = 0; i < tempArr.length; i++) {
	//			String[] string = regroupStringArr(tempArr[i], "=");
	//			if(string.length != 2){
	//				continue;
	//			}
	//			result.put(string[0], string[1]);
	//		}
	//		return result;
	//	}

	//	public static String changeDiffEncodingString(String str){
	//		if(str == null){
	//			return str;
	//		}
	//		String returnStr = "";
	//		try {
	//			returnStr = new String(str.getBytes("GBK"), "GBK");
	//		} catch (UnsupportedEncodingException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		if(!returnStr.equals(str)){
	//			try {
	//				returnStr = new String(str.getBytes("gb2312"), "GBK");
	//			} catch (UnsupportedEncodingException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}
	//		if(!returnStr.equals(str)){
	//			try {
	//				returnStr = new String(str.getBytes("iso8859-1"),"GBK");
	//			} catch (UnsupportedEncodingException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}
	//		if(!returnStr.equals(str)){
	//			try {
	//				returnStr = new String(str.getBytes("utf-8"),"GBK");
	//			} catch (UnsupportedEncodingException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}
	//		return returnStr;
	//	}

	public static String upperCaseFirstLetter(String str) {
		return str.replaceFirst(String.valueOf(str.charAt(0)), String.valueOf(str.charAt(0)).toUpperCase());
	}

	/**
	 * 返回obj在objArr中的位置
	 * @param objArr
	 * @param obj
	 * @return
	 */
	public static int getIndexInArr(Object[] objArr, Object obj) {
		if (objArr == null) {
			return -1;
		}
		for (int i = 0; i < objArr.length; i++) {
			Object object = objArr[i];
			if (object.equals(obj)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将字符串转成布尔型
	 * @param str
	 * @return
	 */
	public static boolean convertStringToBoolean(String str) {
		if ("1".equals(str)) {
			return true;
		} else if ("yes".equals(str)) {
			return true;
		} else if ("y".equals(str)) {
			return true;
		} else if ("true".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 将字符串转成布尔型
	 * @param str
	 * @return
	 */
	//	public static boolean convertStringToBoolean(Object str){
	//		if("1".equals(str)){
	//			return true;
	//		}else if("yes".equals(str)){
	//			return true;
	//		}else if("y".equals(str)){
	//			return true;
	//		}else if("true".equals(str)){
	//			return true;
	//		}
	//		return false;
	//	}

	/**
	 * 从origin中把del的数组内容都删掉。
	 * @param origin
	 * @param del
	 * @return
	 */
	//	@SuppressWarnings("unchecked")
	//	public static String[] filter(String[] origin, String[] del){
	//		List<String> list = new ArrayList();
	//		for (int i = 0; i < origin.length; i++) {
	//			String string = origin[i];
	//			boolean needDel = false;
	//			for (int j = 0; j < del.length; j++) {
	//				String string2 = del[j];
	//				if(string.equals(string2)){
	//					needDel = true;
	//					break;
	//				}
	//			}
	//			if(!needDel){
	//				list.add(string);
	//			}
	//		}
	//		String[] result = new String[list.size()];
	//		return list.toArray(result);
	//	}

	/**
	 * arrs数组里是否包含有obj结点
	 * @param arrs
	 * @param obj
	 * @return
	 */
	//	public static boolean contain(Object[] arrs, Object obj){
	//		if(arrs == null || arrs.length == 0){
	//			return false;
	//		}
	//		for (int i = 0; i < arrs.length; i++) {
	//			Object object = arrs[i];
	//			if(object.equals(obj)){
	//				return true;
	//			}
	//		}
	//		return false;
	//	}

	/**
	 * 把字符串转换编码
	 * @param input
	 * @param sourceEncoding
	 * @param targetEncoding
	 * @return
	 */
	//	public static String changeEncoding(String input, String sourceEncoding, String targetEncoding) {
	//		if (input == null || input.equals("")) {
	//			return input;
	//		}
	//
	//		try {
	//			byte[] bytes = input.getBytes(sourceEncoding);
	//			return new String(bytes, targetEncoding);
	//		} catch (Exception ex) {
	//		}
	//		return input;
	//	}

	/**
	 * 提供32位全球无重复的字符串 UUID
	 * @author yel
	 * @return String 
	 * @time Jan 27, 2010 4:02:19 PM
	 * @since jdk1.5
	 */
	public static String UUID32() {
		return UUID36().replaceAll("-", "");
	}

	/**
	 * 提供36位全球无重复的字符串 UUID
	 * @author yel
	 * @return String 
	 * @time Jan 27, 2010 4:02:19 PM
	 * @since jdk1.5
	 */
	public static String UUID36() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 将Object对象转换为String
	 * @param o
	 * @return
	 */
	//	public static String objToStr(Object o){
	//		return o==null?null:o.toString();
	//	}

	/**
	 * 判断一个字符串是否是空串 null或者""
	 * @param str 传入的字符串
	 * @return boolean true 是空的 false不是空
	 */
	//	public static boolean checkStringNull(String str){
	//		if(null == str || "".equals(str)){
	//			return true;
	//		}
	//		return false;
	//	}

	/**
	 * 将字符串s第index个字母小写（index从0开始），并返回。
	 * @param s
	 * @return
	 */
	public static String lowerCharInString(String s, int index) {
		return lowerAndUpperCharInStringUtil(s, index, CharacterFlag.Lower);
	}

	/**
	 * 将字符串s第index个字母大写（index从0开始），并返回。
	 * @param s
	 * @return
	 */
	public static String upperCharInString(String s, int index) {
		return lowerAndUpperCharInStringUtil(s, index, CharacterFlag.Upper);
	}

	/**
	 * lowerCharInString方法和upperCharInString的公用类
	 * @param s
	 * @param index
	 * @param flag 1代表upper，-1代表lower
	 * @return
	 */
	private static String lowerAndUpperCharInStringUtil(String s, int index, CharacterFlag flag) {
		if (s == null || s.length() < index + 1 || index < 0) {
			System.err.println("字符串参数与序列参数不正确");
			return s;
		}
		//		if()
		StringBuffer sb = new StringBuffer(s);
		Character c;
		//转换大小写
		if (flag.equals(CharacterFlag.Lower)) {
			c = Character.toLowerCase(sb.charAt(index));
		} else if (flag.equals(CharacterFlag.Upper)) {
			c = Character.toUpperCase(sb.charAt(index));
		} else {
			c = null;
		}
		//删除第index个字符
		sb.deleteCharAt(index);
		//重新拼接
		if (index == 0) {
			return c + sb.toString();
		} else if (index == s.length() - 1) {
			return sb.substring(0, index) + c;
		} else {
			return sb.substring(0, index) + c + sb.substring(index);
		}
	}

	/**
	 * 对于String类型的非空判断
	 * @param s String类型的数据
	 * @return boolean true-null或者”“，false-有值
	 */
	public static boolean isNullOrEmpty(String s) {
		if (s == null || "".equals(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <b>方法名</b>：arrayToJson<br>
	 * <b>功能</b>：把 字符串数组转化成json串数组<br>
	 * @author <font color='blue'>zohan</font> 
	 * @date  2012-6-21 上午11:02:26
	 * @param arg  字符串数组
	 * @return
	 * @see JsonUtil#ArrayToStr(Object)
	 */
	public static String arrayToJson(String[] arg) {
		JSONArray array = new JSONArray();
		for (String str : arg) {
			array.add(str);
		}
		return array.toString();
	}

	/**
	 * <b>方法名</b>：isNullorBlank<br>
	 * <b>功能</b>：判断字符串为null或者为""<br>
	 * @param value  要判断的字符按串
	 * @return  如果字符串是null或者为""，返回true，否则返回false
	 * @see StringUtils#isNullOrEmpty(String)
	 */
	@Deprecated
	public static boolean isNullorBlank(String value) {
		return null == value || "".equals(value);
	}

	/**
	 * @description  去掉左右空格后字符串是否为空.
	 * @date 2010-7-12
	 * @author liuhh
	 * @param astr 要处理的字符串
	 * @return 如果去掉左右空格后为空，返回true，否则返回false
	 * @see StringUtils#isNullOrEmpty(String)
	 */
	@Deprecated
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(trim(astr))) {
			return true;
		}
		return false;
	}

	/**
	 * @description 去掉指定字符串两端的空格
	 * @date 2010-7-12
	 * @author liuhh
	 * @param value 指定的字符串
	 * @return 去掉两端空格后的字符串。如果传入的指定字符串是null，返回""。
	 * @see StringUtils#trimStr(String, String, String)
	 */
	@Deprecated
	public static String trim(String value) {
		if (value == null) {
			return "";
		} else {
			return value.trim();
		}
	}

	/**
	 * @description 字符串是否为空:null或者长度为0.
	 * @date 2010-7-12
	 * @author liuhh
	 * @param astr 要判断的字符串
	 * @return null或者长度为0.
	 * @see StringUtils#isNullOrEmpty(String)
	 */
	@Deprecated
	public static boolean isBlank(String astr) {
		return ((null == astr) || (astr.length() == 0));
	}

	/**
	 * 方便测试本类中的方法
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(lowerCharInString("FJDKF", 0));
		System.out.println(lowerCharInString("FJDKF", 4));
		System.out.println(lowerCharInString("FJDKF", 2));
		System.out.println(lowerCharInString("FJDKF", 5));
		System.out.println(lowerCharInString("FJDKF", -1));
	}
}
