package tester;

import java.util.HashSet;
import java.util.Set;

import static springboot.util.LogUtil.log;

public class HashCodeTester {


    public static void test(){
        Set<A> set = new HashSet<A>();
        log("%s",set.add(new HashCodeTester().new A()));
        log("%s",set.add(new HashCodeTester().new A()));
        log("%s",set.add(new HashCodeTester().new A()));
        log("%s",set.add(new HashCodeTester().new A()));
    }

    public class A{
        private int hashcode = 0;

        @Override
        public int hashCode() {
            log("get hashCode");
           return 1;
        }

        @Override
        public boolean equals(Object obj) {
            log("invoke equals");
            return true;
        }
    }




}
