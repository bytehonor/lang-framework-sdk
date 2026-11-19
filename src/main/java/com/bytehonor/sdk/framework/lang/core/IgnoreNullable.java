package com.bytehonor.sdk.framework.lang.core;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface IgnoreNullable extends Serializable {

}
