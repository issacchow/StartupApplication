package tester;

import java.util.HashMap;
import java.util.Map;

import static springboot.util.LogUtil.log;

/**
 * Created by IssacChow on 18/4/14.
 */
public class MapTester {

    public static  void test(){
        addNull();
    }

    public static void addNull(){
        Map<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put(null, null);
        log("size:%s", hashMap.size());
//        dirMap(hashMap);
//        logLine();

        hashMap.put(null, "aa");
        log("size:%s", hashMap.size());
//        dirMap(hashMap);
//        logLine();

        hashMap.put("aa", null);
        log("size:%s", hashMap.size());
//        dirMap(hashMap);
//        logLine();

        log("值替换测试..");
        Object obj = null;
        obj = hashMap.put("1", 1);
        log("%s",obj);
        //替换前一个值，并返回前一个值
        obj = hashMap.put("1", 2);
        log("%s",obj);
    }




}
