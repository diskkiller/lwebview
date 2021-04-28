package com.longbei.lwebview.gson;

import android.content.Context;
import android.text.TextUtils;

import java.util.Locale;

/**
 * @author liuml.
 * @explain 字符串操作
 * @time 2017/12/5 10:02
 */
public class StringUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private StringUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || "null".equals(str.toString()) || TextUtils.isEmpty(str)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * byte[]数组转换为16进制的字符串
     *
     * @param data 要转换的字节数组
     * @return 转换后的结果
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 高效率的字符串拼接
     *
     * @param strings
     * @return
     */
    public static final String stringBulider(String... strings) {
        StringBuilder sb = new StringBuilder();

        for (String b : strings) {
            sb.append(b);
        }
        return sb.toString();
    }


    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    /**
     * 将给定的字符串中所有给定的关键字标红
     *
     * @param sourceString 给定的字符串
     * @param keyword      给定的关键字
     * @return 返回的是带Html标签的字符串，在使用时要通过Html.fromHtml()转换为Spanned对象再传递给TextView对象
     */
    public static String keywordMadeRed(String sourceString, String keyword) {
        String result = "";
        if (sourceString != null && !"".equals(sourceString.trim())) {
            if (keyword != null && !"".equals(keyword.trim())) {
                result = sourceString.replaceAll(keyword,
                        "<font color=\"red\">" + keyword + "</font>");
            } else {
                result = sourceString;
            }
        }
        return result;
    }

    /**
     * 为给定的字符串添加HTML红色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
     *
     * @param string 给定的字符串
     * @return
     */
    public static String addHtmlRedFlag(String string) {
        return "<font color=\"red\">" + string + "</font>";
    }

    /*public static String getString(Context context, int resId) {

    }*/

    /**
     * Get string.
     *
     * @param context The context is Context object.
     * @param resId   The resId is string resource id.
     * @param tagId   The tagId is a tag string resource id.
     * @return String value;
     */
    public static String getString(Context context, int resId, int tagId) {
        if (tagId == 0) {
            return context.getString(resId);
        } else {
            return context.getString(resId, context.getString(tagId));
        }
    }

    /**
     * Get string.
     *
     * @param context The context is Context object.
     * @param resId   The resId is string resource id.
     * @param tag     The tag is a string value.
     * @return String value;
     */
    public static String getString(Context context, int resId, String tag) {
        return context.getString(resId, tag);
    }

    /**
     * Remove colon.
     *
     * @param string The string is remove colon.
     * @return String value;
     */
    public static String removeColon(String string) {
        return string.substring(0, string.length() - 1);
    }

    public static String intToStr(int value) {
        String str = value + "";
        str = str.length() > 1 ? str : "0" + value;
        return str;
    }

    public static String addSpaceStr(String value) {
        String str = "";
        for (int i = 0; i < value.length(); i++) {
            str += value.charAt(i);
            if ((i + 1) % 4 == 0) {
                str += " ";
            }
        }
        return str;
    }


    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public static boolean isLegalName(String name) {
        if (name.contains("·") || name.contains("•")) {
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * String 为空时显示默认文本
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String getStringValue(String value, String defaultValue) {
        if (TextUtils.isEmpty(value) || "null".equals(value)) {
            return defaultValue;
        }
        return value;
    }


    /**
     * 安全转化类型  为Int
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public final static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * 安全转化类型  为String
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public final static String convertToString(Object value, String defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return String.valueOf(value.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * @explain 把Object转化为double
     * @author liuml.
     * @time 2017/12/5 10:19
     */
    public static double convertToDouble(Object value, double defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value.toString());
        } catch (Exception e) {
            return defaultValue;
        }

    }
    /**
     * @explain 把Object转化为float
     * @author liuml.
     * @time 2017/12/5 10:19
     */
    public static float convertToFloat(Object value, float defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Float.valueOf(value.toString());
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /**
     * 安全转化类型  为 long、double、String
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public final static long convertToLong(Object value, long defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Long.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Long.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串截取
     */
    public final static String safeSubString(String str, int begin, int end) {
        String temp = "";
        try {

            if (!isEmpty(str)) {
                if (str.length() > begin) {
                    temp = str.substring(begin, end);
                }
                return temp;
            } else {
                return temp;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return temp;
        }

    }

    /**
     * 是否double
     *
     * @param str
     * @return
     */
    public static boolean isFloat(String str) {
        boolean bCheckResult = true;
        try {
            Float iCheckValue = Float.parseFloat(str);
            if (iCheckValue instanceof Float == false) {
                bCheckResult = false;
            }
        } catch (NumberFormatException e) {
            bCheckResult = false;
        }
        return bCheckResult;
    }

    /**
     * 是否double
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        boolean bCheckResult = true;
        try {
            Double iCheckValue = Double.parseDouble(str);
            if (iCheckValue instanceof Double == false) {
                bCheckResult = false;
            }
        } catch (NumberFormatException e) {
            bCheckResult = false;
        }
        return bCheckResult;
    }

    //整型，整数判断
    public static boolean isInt(String str) {
        boolean bCheckResult = true;
        try {
            Integer iCheckValue = Integer.parseInt(str);
            if (iCheckValue instanceof Integer == false) {
                bCheckResult = false;
            }
        } catch (NumberFormatException e) {
            bCheckResult = false;
        }
        return bCheckResult;
    }

    public static String appendStr(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if (strs.length < 0) {
            return "";
        }
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

}