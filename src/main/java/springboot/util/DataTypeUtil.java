package springboot.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 数据类型安全转换工具类
 *
 * @author 周伟锋
 *
 */
public class DataTypeUtil {


    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 转换成Long,转换失败时返回0L
     *
     * @param o
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static Long getLong(Object o) {
        return getLong(o, 0L);
    }

    /**
     * 转换成Long,转换失败时返回defaultValue
     *
     * @param o
     * @param defaultValue
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static Long getLong(Object o, Long defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Long value = Long.parseLong(o.toString());
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Float getFloat(Object o) {
        return getFloat(o, 0f);
    }

    public static Float getFloat(Map<String, Object> map, String key, Float defaultValue) {
        return getFloat(map.get(key), defaultValue);
    }

    public static Float getFloat(Object o, Float defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Float value = Float.parseFloat(o.toString());
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Double getDouble(Object o) {
        return getDouble(o, 0d);
    }

    public static Double getDouble(Map<String, Object> map, String key, Double defaultValue) {
        return getDouble(map.get(key), defaultValue);
    }

    public static Double getDouble(Object o, Double defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Double value = Double.parseDouble(o.toString());
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    /**
     * 从map中获取一个Long型数据
     *
     * @param map
     * @param key
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static Long getLong(Map<String, Object> map, String key) {
        return getLong(map.get(key), 0L);
    }

    /**
     * 从map中获取一个Long型数据
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static Long getLong(Map<String, Object> map, String key, Long defaultValue) {
        return getLong(map.get(key), defaultValue);
    }

    /**
     * 转换Integer,转换失败时返回0
     *
     * @param o
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static Integer getInteger(Object o) {
        return getInteger(o, 0);
    }

    /**
     * 转换Integer,转换失败时返回defaultValue
     *
     * @param o
     * @param defaultValue
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static Integer getInteger(Object o, Integer defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Integer value = Integer.parseInt(o.toString());
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 从map中获取一个Integer型数据
     *
     * @param map
     * @param key
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static Integer getInteger(Map<String, Object> map, String key) {
        return getInteger(map.get(key), 0);
    }

    /**
     * 从map中获取一个Integer型数据
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static Integer getInteger(Map<String, Object> map, String key, Integer defaultValue) {
        return getInteger(map.get(key), defaultValue);
    }

    /**
     * 从map中获取布尔值,默认返回false
     *
     * @param map
     * @param key
     * @return
     * @author IssacChow 2016年8月8日
     */
    public static boolean getBoolean(Map<String, Object> map, String key) {
        return getBoolean(map.get(key), false);
    }

    /**
     * 从Map中获取布尔值
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     * @author IssacChow 2016年8月8日
     */
    public static boolean getBoolean(Map<String, Object> map, String key, boolean defaultValue) {
        return getBoolean(map.get(key), defaultValue);
    }

    /**
     * 获取布尔值,默认返回false
     *
     * @param o
     * @return
     * @author IssacChow 2016年8月8日
     */
    public static boolean getBoolean(Object o) {
        return getBoolean(o, false);
    }

    /**
     * 获取布尔值
     *
     * @param o
     * @param defaultValue
     * @return
     * @author IssacChow 2016年8月8日
     */
    public static boolean getBoolean(Object o, boolean defaultValue) {

        if (o instanceof Boolean) {
            return (boolean) o;
        }

        String s = getStr(o);
        if ("".equals(s)) {
            return defaultValue;
        }

        if ("1".equals(s)) {
            return true;
        }
        if ("0".equals(s)) {
            return false;
        }
        if ("true".equals(s)) {
            return true;
        }
        if ("false".equals(s)) {
            return false;
        }

        return defaultValue;
    }

    /**
     * 转换成日期,转换失败时返回空字符串
     *
     * @param o
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static String getStr(Object o) {
        return getStr(o, "");
    }

    /**
     * 转换成字符串,转换失败时返回defaultValue
     *
     * @param o
     * @param defaultValue
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static String getStr(Object o, String defaultValue) {
        if (o == null) {
            return defaultValue;
        }

        if (o instanceof String) {
            if ("".equals(o)) {
                return defaultValue;
            }

            return (String) o;
        }

        return o.toString();
    }

    /**
     * 从map中获取一个字符串
     *
     * @param map
     * @param key
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static String getStr(Map<String, Object> map, String key) {
        return getStr(map, key, "");
    }

    /**
     * 从map中获取一个字符串
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static String getStr(Map<String, Object> map, String key, String defaultValue) {
        return getStr(map.get(key), defaultValue);
    }

    /**
     * 转换成日期,转换失败时返回null
     *
     * @param o
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static Date getDate(Object o) {
        return getDate(o, null);
    }

    /**
     * 转换成日期,转换失败时返回defaultValue
     *
     * @param o
     * @param defaultValue
     * @return
     * @date 2016年7月29日
     * @author 周伟锋
     */
    public static Date getDate(Object o, Date defaultValue) {
        if (o == null) {
            return defaultValue;
        }

        if (o instanceof Date) {
            return (Date) o;
        }

        String dateString = o.toString();

        try {
            Date date = datetimeFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            // 转换失败时尝试用日期格式来转换
            try {
                Date date2 = dateFormat.parse(dateString);
                return date2;
            } catch (ParseException e2) {
                return defaultValue;
            }
        }

    }

    /**
     * 从map中获取日期类型
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return
     * @date 2016年8月1日
     * @author 周伟锋
     */
    public static Date getDate(Map<String, Object> map, String key, Date defaultValue) {
        return getDate(map.get(key), defaultValue);
    }

    /**
     * 从源中获取日期字符串,格式为yyyy-MM-dd
     *
     * @param source
     * @param defaultValue
     * @return
     * @author IssacChow
     * 2016年8月12日
     */
    public static String getDateString(Object source, String defaultValue) {
        return getDateOrDateTimeString(source, false, defaultValue);
    }

    /**
     * 从源中获取日期时间字符串,格式为yyyy-MM-dd HH:mm:ss
     *
     * @param source
     * @param defaultValue
     * @return
     * @author IssacChow
     * 2016年8月12日
     */
    public static String getDateTimeString(Object source, String defaultValue) {
        return getDateOrDateTimeString(source, true, defaultValue);
    }

    /**
     * 转换日期字符串
     *
     * @param source
     * @param convertToFullDatetime
     * @param defaultValue
     * @return
     * @author IssacChow
     * 2016年8月12日
     */
    private static String getDateOrDateTimeString(Object source, boolean convertToFullDatetime, String defaultValue) {

        Date date = getDate(source);
        if(date==null)
            return defaultValue;

        if (convertToFullDatetime) {
            return datetimeFormat.format(date);
        } else {
            return dateFormat.format(date);
        }

    }

    /**
     * 判断输入的字符串是否由中文、英文、数字或下划线组成。
     * <br>
     * 1、如果n和m都不为0，则匹配次数在n和m的范围内。
     * <br>
     * 2、如果n=0,m=0,则匹配次数在1次以上
     * <br>
     * 3、如果n != 0 && n > m，则匹配次数在0和m之间
     *
     * @param str 需要检测的字符串
     * @param n   最少匹配n次
     * @param m   最多匹配m次
     * @return true-是由中文、英文、数字或下划线组成的字符串<br>false-不是否由中文、英文、数字或下划线组成
     */
    public static boolean checkStrVaild(String str, int n, int m) {
        String regex = "[\u4E00-\u9FA5\\w]";
        regex = String.format("%s%s", "^", regex);
        n = (n < 0 ? 0 : n);
        m = (m < 0 ? 0 : m);
        if (n > m) {
            n = 0;
        }
        if (n == 0 && m == 0) {
            regex = String.format("%s%s", regex, "+");
        } else {
            regex = String.format("%s%s%d%s%d%s", regex, "{", n, ",", m, "}");
        }
        regex = String.format("%s%s", regex, "$");
        System.out.println("regex == " + regex);
        Pattern emoji = Pattern.compile(regex);
        Matcher emojiMatcher = emoji.matcher(str);
        if (emojiMatcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断输入的字符串是否由中文、英文字母组成。
     * <br>
     * 1、如果n和m都不为0，则匹配次数在n和m的范围内。
     * <br>
     * 2、如果n=0,m=0,则匹配次数在1次以上
     * <br>
     * 3、如果n != 0 && n > m，则匹配次数在0和m之间
     *
     * @param str 需要检测的字符串
     * @param n   最少匹配n次
     * @param m   最多匹配m次
     * @return true-是由中文、英文字母组成的字符串<br>false-不是否由中文、英文字母组成
     */
    public static boolean checkStrVaild2(String str, int n, int m) {
        String regex = "[A-Za-z\u4E00-\u9FA5]";
        regex = String.format("%s%s", "^", regex);
        n = (n < 0 ? 0 : n);
        m = (m < 0 ? 0 : m);
        if (n > m) {
            n = 0;
        }
        if (n == 0 && m == 0) {
            regex = String.format("%s%s", regex, "+");
        } else {
            regex = String.format("%s%s%d%s%d%s", regex, "{", n, ",", m, "}");
        }
        regex = String.format("%s%s", regex, "$");
        System.out.println("regex == " + regex);
        Pattern emoji = Pattern.compile(regex);
        Matcher emojiMatcher = emoji.matcher(str);
        if (emojiMatcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 检查字符串是否为空白字符，包括空格、制表符、换页符等
     *
     * @param str 需要检测的字符串
     * @return true-是空白字符<br>false-不是空白字符
     */
    public static boolean checkStrIsWhiteSpace(String str) {
        Pattern pattern = Pattern.compile("^[\\s]+$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为null或空值
     *
     * @param str
     * @return
     * @author IssacChow
     * 2016年9月14日
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null) return true;
        return str.trim().length() == 0;
    }

    public static boolean isNullOrEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isAllNullEmpty(String... str){
        if (str==null) return true;
        boolean isAllNullEmpty = true;
        for (int i = 0; i < str.length; i++) {
            boolean nullOrEmpty = DataTypeUtil.isNullOrEmpty(str[i]);
            if (!nullOrEmpty){
                isAllNullEmpty = false;
                break;
            }
        }
        return isAllNullEmpty;
    }

    public static boolean isNullOrEmpty(String... str) {
        for (String s : str) {
            if (isNullOrEmpty(s)) return true;
        }
        return false;
    }

    /**
     * 判断集合是否空
     *
     * @param list
     * @return
     */
    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 合并多个路径
     *
     * @param paths
     * @return
     * @author IssacChow
     * 2016年9月14日
     */
    public static String combinePath(String... paths) {
        String path = "";
        for (String s : paths) {
            String temp = s;
            if (s.startsWith("/")) {
                temp = s.substring(1);
            }
            path += "/" + temp;
        }
        return path;
    }

    /**
     * 合并多个url路径
     *
     * @param paths
     * @return
     */
    public static String combineUrlPath(String baseUrl, String... paths) {

        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
        if (baseUrl.endsWith("/") == false) {
            sb.append("/");
        }

        for (String s : paths) {
            if (sb.charAt(sb.length() - 1) != '/') {
                sb.append("/");
            }

            if (s.startsWith("/")) {
                sb.append(s.substring(1));
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }


}
