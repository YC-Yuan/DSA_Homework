package hw2;

public class Majority {
    public static void main(String[] args) {
        int[] array = {1,1,2,3,2};
        int result = findMajority(array,array.length - 1);
        if (result == -666) System.out.println("数组中没有主元素");
        else System.out.println("数组的主元素是：" + result);
    }

    //递归函数，输入数组打印主元素(出现次数超过数组元素一半的元素)
    public static int findMajority(int[] array,int index) {
        //终止条件
        //只剩一个元素时，检测其是否为主元素
        if (index == 0) {
            if (IsMajority(array,array[0])) return array[0];
            else return -666;
        }
        //不剩下元素时，说明没有主元素
        if (index == -1) return -666;

        /*
        为了避免开辟数组B的空间，将A的前n(两两比较的相等次数)项作为B的代替，
        将原本的元素和需要放入的元素交换来保证A数组内容信息不损失(以便检查是否是主元)
         */
        int currentIndex = 0;
        int result = -666;
        for (int i = 0; i <= index; i += 2) {
            //还没到末两位，进行pk
            if (i + 1 < index) {
                if (array[i] == array[i + 1]) {
                    swap(array,i,currentIndex);
                    currentIndex++;
                }
            }
            //末两位已经到结尾了，递归
            if (i + 1 == index) return findMajority(array,currentIndex);
            //i本身就到结尾了，奇数项
            if (i == index) {
                if (IsMajority(array,array[i])) return array[i];
                else return -666;
            }
        }
        return result;
    }

    //检测某元素是否为主元素,O(n)
    public static boolean IsMajority(int[] array,int candidate) {
        int count = 0;
        for (int value : array) {
            if (candidate == value) count++;
        }
        return count > array.length / 2;
    }

    public static void swap(int[] array,int p,int q) {
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp;
    }
}
