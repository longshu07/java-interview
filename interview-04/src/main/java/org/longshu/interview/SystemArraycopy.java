package org.longshu.interview;

import java.util.Arrays;

public class SystemArraycopy {
    private static char[] value = {'a', 'b'};
    public static void main(String[] args) {

        String ab = "ab";
        String cd = "cd";
        String abcd = ab.concat(cd);
        System.out.println(abcd);
        concat("cd");
    }

    public  static void concat(String str) {
        if (str.isEmpty()) {
            return ;
        }
        int len = value.length;
        int otherLen = str.length();
        // 创建一个字符数组：长度是已有字符串 + 待拼接字符串的长度之和
        char[] buf = Arrays.copyOf(value, len + otherLen);
        System.out.println("buf:");
        for (char c : buf) {
            System.out.print(c);
        }
        System.out.println();
        /*
        *   strChar = {'c', 'd'}             : the source array. 源值数组
        *   0                              :srcPos – starting position in the source array. 从原值开始copy的位置
            buf= {'a', 'b', '', ''}        :dest – the destination array. 目标数组
            len = value.length = 2         :destPos – starting position in the destination data. 插入目标数组的位置
            strChar.length = 2               :length – the number of array elements to be copied. 从源数组拷贝的数量
        * */
        char[] strChar = str.toCharArray();
        System.arraycopy(strChar, 0, buf, len, strChar.length);
        System.out.println("System.arraycopy之后的buf:");
        for (char c : buf) {
            System.out.print(c);
        }
        System.out.println();

    }
}
