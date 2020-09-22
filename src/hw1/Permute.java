package hw1;

import java.util.Arrays;

public class Permute {
    public static void permute(String str) {
        char[] array = str.toCharArray();
        int low = 0;
        for (int i = 0; i < array.length; i++) {
            permute(array, low, i);
        }
    }

    private static void permute(char[] str, int low, int high) {
//        System.out.println("Permute running:"+ Arrays.toString(str) +low+high+str.length);
        //终止条件：low==str.length-1,说明所有顺序都确定，直接输出
        if (low == str.length - 1) System.out.println(String.valueOf(str));
        else {
            //将str数组中low与high位互换，递归
            char temp = str[low];
            str[low] = str[high];
            str[high] = temp;
            low++;
            for (int i = low; i < str.length; i++) {
                permute(str.clone(), low, i);
            }
        }
    }

    public static void main(String[] args) {
        permute("abc");
    }
}
