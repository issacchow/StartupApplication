package tester.sort;

public class SpeedSorter {


    public  static void test() {

        int[] score = new int[]{ 3,2,1,6,5,4,0,1 };
        new SpeedSorter().sortCore(score);
    }

    public void sortCore(int[] array) {
        sortCore(array, 0, array.length - 1);
    }

    /*
     * 排序的核心算法
     *
     * @param array
     *      待排序数组
     * @param startIndex
     *      开始位置
     * @param endIndex
     *      结束位置
     */
    private void sortCore(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int boundary = boundary(array, startIndex, endIndex);


        //调用完boundary后，计算得出中间值的索引位置
        //根据这个中间值临界点，分割成左右两边数组，进一步排序左右组内的数据
        sortCore(array, startIndex, boundary - 1);
        sortCore(array, boundary + 1, endIndex);
    }

    /*
     * 交换并返回分界点
     *
     * @param array
     *      待排序数组
     * @param startIndex
     *      开始位置
     * @param endIndex
     *      结束位置
     * @return
     *      分界点
     */
    private int boundary(int[] array, int startIndex, int endIndex) {
        int standard = array[startIndex]; // 定义标准
        int leftIndex = startIndex; // 左指针
        int rightIndex = endIndex; // 右指针

        while (leftIndex < rightIndex) {
            //以基准值为临界点，从右边找出第一个少于基准值的位置
            while (leftIndex < rightIndex && array[rightIndex] >= standard) {
                rightIndex--;
            }
            //并将第一个值替换当前基准值
            array[leftIndex] = array[rightIndex];

            //找以基准值为临界点，从左边找出第一个大于基准值的位置
            while (leftIndex < rightIndex && array[leftIndex] <= standard) {
                leftIndex++;
            }
            //并将右边第一个小于基准值的值 与 左边第一个大于基准值的值 交互位置
            array[rightIndex] = array[leftIndex];
        }

        //运行到这里已经将  小于基准值的值全部迁移到左边 而 大于基准值的值全部迁移到右边
        //所以此时leftIndex已经成为了 左右两边中间的值位置
        //故需要将基准值在返回函数前，替换到这里，避免丢失
        array[leftIndex] = standard;
        return leftIndex;
    }


}
