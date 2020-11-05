package com.gym.imgutils;


public class StringUtils {

	public final static String CHINESE_SPACE = "　";
	public final static String ENGLISH_SPACE = " ";
	/**
	 * 字符串是否非空，对圆角符号输入的空格有效。
	 * @param str
	 * @return false表示空字符串，true表示不是空字符串
	 */
	public static final boolean isNotEmpty(String str) {
		if (str == null) {
			return false; // 为空
		}
		if (str.length() == 0) {
			return false; // 为空
		}
		char[] arr = str.toCharArray();
		int arrLength = arr.length;
		for (int i = 0; i < arrLength; i++) {
			char c = arr[i];
			if (!Character.isWhitespace(c)) {
				return true; // 不是空字符串
			}
		}
		return false; // 为空
	}
	
	public static final boolean isEmpty(String str) {
		return !StringUtils.isNotEmpty(str);
	}
	
	public static final String trim(String str){
		if (isEmpty(str)) {
			return "";
		}
		char[] arr = str.toCharArray();
		int begin = 0;
		int end = arr.length - 1;
		// 从前向后，找到第一个非空字符
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (!Character.isWhitespace(c)) {
				begin = i;
				break;
			}
		}
		// 从后向前，找到第一个非空
		for (int j = arr.length - 1; j >= 0; j--) {
			char c2 = arr[j];
			if (!Character.isWhitespace(c2)) {
				end = j;
				break;
			}
		}
		// 只有一个字符
		if(begin == end){
			return new String(new char[]{arr[begin]});
		}
		// 多个字符
		else {
			return str.substring(begin, end + 1);
		}
	}
}
