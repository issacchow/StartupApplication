package tester.sort;

import static com.isc.application.startup.util.LogUtil.log;

public class BubbleSorter
{

    public static void test(){

        BubbleSorter sorter = new BubbleSorter();

        log("sort first");
        int[] ary = new int[]{ 1,2,3,4,5,6,7 };
        sorter.sort(ary);

        log("sort secondary");
        int[] ary2 = new int[]{ 1,3,7,2,4,6,5 };
        sorter.sort(ary2);
    }

    public void sort(int[] a)
    {
        int temp = 0;
        int left = -1;
        int right = -1;
        int count = 0;
        boolean hasSwap = true;//用于标记是否有进行交换,如果没有进行交换操作，证明当前数组已经是有序,结束循环体

        for (int i = a.length - 1; i > 0 && hasSwap==true;--i)
        {
            hasSwap = false;
            for (int j = 0; j < i; ++j)
            {
                count++;
                log("compare count:%s,  i:%s, j:%s",count,i,j);
                right = a[j+1];
                left = a[j];
                //如果右边值小于左边值,那就提升(向左边冒泡)一个位置
                if (right < left)
                {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    hasSwap = true;
                }
            }
        }
    }
}