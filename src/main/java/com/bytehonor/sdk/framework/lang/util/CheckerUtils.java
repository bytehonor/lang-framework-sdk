package com.bytehonor.sdk.framework.lang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bytehonor.sdk.framework.lang.string.SpringString;


/**
 * @author lijianqiang
 *
 */
public class CheckerUtils {

	private static final String REGX = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$";

	/**
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (SpringString.isEmpty(mobile) || mobile.length() < 11) {
			return false;
		}
		Pattern p = Pattern.compile(REGX);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
}
