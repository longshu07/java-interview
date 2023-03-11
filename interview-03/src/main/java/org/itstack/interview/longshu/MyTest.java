package org.itstack.interview.longshu;

public class MyTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String abc = "abc";
        /*
        s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
        1、31 是一个奇质数，如果选择偶数会导致乘积运算时数据溢出。
        2、另外在二进制中，2个5次方是32，那么也就是 31 * i == (i << 5) - i。这主要是说乘积运算可以使用位移提升性能，同时目前的JVM虚拟机也会自动支持此类的优化。
         */
        int a = abc.hashCode();
        System.out.println(a);
    }
}
