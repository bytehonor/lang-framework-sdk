package com.bytehonor.sdk.lang.bytehonor.validate;

import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.error.ParameterExcption;
import com.bytehonor.sdk.lang.bytehonor.string.StringCreator;

public class IntegerValidate {

	public static void range(Integer src, int min, int max, String message) {
		Objects.requireNonNull(src, message);
		if (src < min) {
			throw new ParameterExcption(StringCreator.create().append(message).append(" < ").append(min).toString());
		}
		if (src > max) {
			throw new ParameterExcption(StringCreator.create().append(message).append(" > ").append(max).toString());
		}
	}
}
