package com.github.tivon.shoppingreceipt.utils;

import org.apache.commons.lang3.StringUtils;

public final class PaddingUtils {

    private static final int COLUMN_DEFAULT_RIGHT_MAXIMUM_PADDING = 15;
    private static final int COLUMN_DEFAULT_LEFT_MAXIMUM_PADDING = 10;

    public static StringBuffer right(StringBuffer sb, String text) {
        return sb.append(StringUtils.rightPad(text, COLUMN_DEFAULT_RIGHT_MAXIMUM_PADDING, " "));
    }

    public static StringBuffer right(StringBuffer sb, String text, int customPaddingSize) {
        return sb.append(StringUtils.rightPad(text, customPaddingSize, " "));
    }

    public static StringBuffer left(StringBuffer sb, String text) {
        return sb.append(StringUtils.leftPad(text, COLUMN_DEFAULT_LEFT_MAXIMUM_PADDING, " "));
    }

    public static StringBuffer newLine(StringBuffer sb) {
        return sb.append(System.lineSeparator());
    }

    public static int getColumnDefaultRightMaximumPadding() {
        return COLUMN_DEFAULT_RIGHT_MAXIMUM_PADDING;
    }

    public static int getColumnDefaultLeftMaximumPadding() {
        return COLUMN_DEFAULT_LEFT_MAXIMUM_PADDING;
    }

}
