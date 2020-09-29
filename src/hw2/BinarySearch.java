package hw2;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        System.out.println(binarySearch(array,1));
    }

    //用int来简化书上的泛型，返回值为对象在数组中的index，找不到返回-1
    public static int binarySearch(int[] array,int element) {
        int low = 0, high = array.length - 1, mid;
        while (low < high) {
            //迭代公式很关键！因为整除的结果是向下取整，导致low和high差1时，mid为low
            //如果以element<array[mid]为判准，则low high差1，mid等于答案的情况下会不更新low

            mid = (low + high) / 2;
            System.out.println("low"+low+"high"+high+"mid"+mid);
            if (element < array[mid]) {
                high = mid - 1;
            }
            else low = Math.min(mid,low-1);
/*            if (element > array[mid]) {
                low = mid + 1;
            }
            else high = mid;*/
        }
        mid = (low + high) / 2;
        if (element == array[mid]) return mid;
        else return -1;
    }
}
