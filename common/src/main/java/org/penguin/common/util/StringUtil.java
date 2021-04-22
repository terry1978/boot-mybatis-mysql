package org.penguin.common.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public static final boolean isNotBlank(String value) {
        return StringUtils.isNotBlank(value);
    }

    public static final String trim(String value) {
        return value == null ? null : value.trim();
    }
}
