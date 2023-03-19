package org.longshu.interview;

public class ArraycopyTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        //a:源数组; 2:源数组中的起始位置; a：目标数组；3：目标数组中的起始位置； 3：要复制的数组元素的数量；
        System.arraycopy(a, 2, a, 3, 3);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        a[2]=99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
