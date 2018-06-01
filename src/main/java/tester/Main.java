package tester;

import tester.sort.BubbleSorter;
import tester.sort.SpeedSorter;

public class Main {



    public static void main(String... args){
       // HashCodeTester.test();

//        SpeedSorter.test();
        BubbleSorter.test();
    }

    static void stringEqualTest(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());

        System.out.println();
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1.equals(s1.intern()));
    }

}
