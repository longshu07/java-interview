package org.longshu.interview;

public class MyString {
    public static void main(String[] args) {

//        String a = "a";
//        String b = "b";
//        String a_Add_b = a + b;
//        String ab = "a" + "b";
//        String fullName1 = "longshu1" + "liu1";
//
//        String firstName = "longshu";
//        String lastName = "liu";
//        String fullName = firstName + lastName;

//        testM();
        testIntern();
    }

    public static void testM(){
        // 堆内存的地址值
        String s1 = new String("longshu");
        String s2 = "longshu";
        // 输出 false,因为[s1]是堆内存，[s2]是常量池的内存，故两者是不同的。
        System.out.println(s1 == s2);
        // 输出 true
        System.out.println(s1.equals(s2));
    }
    public static void testIntern(){
        // 设s1的引用内存地址为：x01。x01是在字符串常量池的地址
        String s1 = "Javatpoint";
        // s1.intern():如果字符串常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用.
        // 即：s2的引用内存地址也为：x01
        String s2 = s1.intern();
        // s3 = new String("Javatpoint"); 发现字符串常量池中已有，则不在常量池创建对象，就只在堆内存中创建一个对象。
        // 设该对象内存地址为：x03。
        // 即：s3的引用内存地址为：x03
        String s3 = new String("Javatpoint");
        // s3.intern()。如果字符串常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用
        // 即：s4的引用内存地址为：x01
        String s4 = s3.intern();
        System.out.println("s1==s2:");
        // True
        System.out.println(s1==s2);
        System.out.println("----------------------------------");
        // False
        System.out.println("s1==s3:");
        System.out.println(s1==s3);
        System.out.println("----------------------------------");

        // True
        System.out.println("s1==s4:");
        System.out.println(s1==s4);
        System.out.println("----------------------------------");

        // False
        System.out.println("s2==s3:");
        System.out.println(s2==s3);
        System.out.println("----------------------------------");

        // True
        System.out.println("s2==s4:");
        System.out.println(s2==s4);
        System.out.println("----------------------------------");

        // False
        System.out.println("s3==s4:");
        System.out.println( s3==s4);
    }
}
