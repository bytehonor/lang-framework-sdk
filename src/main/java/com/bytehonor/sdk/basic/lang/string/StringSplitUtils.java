package com.bytehonor.sdk.basic.lang.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.lang.constant.CharConstants;
import com.bytehonor.sdk.basic.lang.constant.StringConstants;

public class StringSplitUtils {
    
    private static final String REG_COMMA = "\\,|\\，";
    private static final Pattern COMMA_PATTERN = Pattern.compile(REG_COMMA);

    /**
     * 逗号分隔，中英逗号均支持
     * 
     * @param src
     * @return
     */
    public static List<String> split(String src) {
        Assert.notNull(src, "src spilt cannt be null!");
        if (StringUtils.isEmpty(src)) {
            return new ArrayList<String>();
        }
        src = COMMA_PATTERN.matcher(src).replaceAll(StringConstants.BLANK);
        return splitWithBlank(src);
    }

	public static List<String> splitWithBlank(String src) {
		return split(src, CharConstants.BLANK);
	}

	public static List<String> split(String src, char sp) {
		if (StringUtils.isEmpty(src)) {
			return new ArrayList<String>();
		}
		int length = src.length();
		List<String> res = new ArrayList<String>(length);
		int begin = 0;
		int count = 0;
		boolean findOne = false;
		boolean beginNewOne = true;
		char[] charArray = src.toCharArray();
		for (int i = 0; i < length; i++) {
			if (sp != charArray[i]) {
				if (beginNewOne) {
					begin = i;
					beginNewOne = false;
				}
				count++;
				findOne = false;
			} else {
				findOne = true;
			}

			if (findOne && count > 0) {
				res.add(new String(charArray, begin, count));
				count = 0;
				beginNewOne = true;
			}
		}

		if (count > 0) {
			res.add(new String(charArray, begin, count));
		}

		return res;
	}
}
