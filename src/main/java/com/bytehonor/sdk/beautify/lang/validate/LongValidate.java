package com.bytehonor.sdk.beautify.lang.validate;

import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.exception.ParameterException;
import com.bytehonor.sdk.define.bytehonor.lang.StringCreator;

public class LongValidate {

	public static void range(Long src, long min, long max, String message) {
		Objects.requireNonNull(src, message);
		if (src.longValue() < min) {
			throw new ParameterException(StringCreator.create().append(message).append(" < ").append(min).toString());
		}
		if (src.longValue() > max) {
			throw new ParameterException(StringCreator.create().append(message).append(" > ").append(max).toString());
		}
	}
}
