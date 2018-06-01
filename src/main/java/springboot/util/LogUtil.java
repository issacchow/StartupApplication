package springboot.util;

public class LogUtil {
    public static void log(String format,Object... args){
        System.out.println();
        System.out.printf(format,args);
    }
}
