package com.bytehonor.sdk.beautify.lang.validate;

import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.exception.ParameterException;
import com.bytehonor.sdk.define.bytehonor.lang.StringCreator;

public class IntegerValidate {

	public static void range(Integer src, int min, int max, String message) {
		Objects.requireNonNull(src, message);
		if (src < min) {
			throw new ParameterException(StringCreator.create().append(message).append(" < ").append(min).toString());
		}
		if (src > max) {
			throw new ParameterException(StringCreator.create().append(message).append(" > ").append(max).toString());
		}
	}
}
