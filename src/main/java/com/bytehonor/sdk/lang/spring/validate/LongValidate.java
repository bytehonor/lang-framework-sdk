package com.bytehonor.sdk.lang.spring.validate;

import java.util.Objects;

import com.bytehonor.sdk.define.spring.exception.ParameterException;
import com.bytehonor.sdk.define.spring.lang.StringCreator;

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
