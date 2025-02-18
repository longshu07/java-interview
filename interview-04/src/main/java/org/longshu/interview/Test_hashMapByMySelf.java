package org.longshu.interview;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于自实现的简陋hashMap
 * 假设我们有一组7个字符串，需要存放到数组中，但要求在获取每个元素的时候时间复杂度是O(1)。
 * 也就是说你不能通过循环遍历的方式进行获取，而是要定位到数组ID直接获取相应的元素
 */

public class Test_hashMapByMySelf {

    public static void main(String[] args) {
        int h1 = 0b11111111111111110000000000010010;
        int h2 = 0b11111111111111010000000000010010;
        testForHashMap扰动函数(h1);
        System.out.println("---------------------");
        testForHashMap扰动函数(h2);
        Map<String, String> hashMap = new HashMap(16);
        hashMap.put("longshu", "longshu1");
//        // 1 << 30 = 2的三十次方
//        System.out.println(1 << 30);
        ArrayList list = new ArrayList();
    }

    /**
     *
     *     static final int hash(Object key) {
     *         int h;
     *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     *     }
     * 通过异或 让 低16位 也包含了 高16位 的特征。这样当两个低位完全相同，高位不一样的 hash 算出来的结果就不会相同了。
     * 之所以使用异或是因为跟与、或相比，它的结果是 1、0 的概率更平均。与的结果更倾向于 0，或 的结果更倾向于 1
     */
    public static void testForHashMap扰动函数(int h){
        // 0b开头表示二进制数
//        int h = 0b11111111111111110000000000000000;
        System.out.println(Integer.toBinaryString( h ) + ":原值");

        // 无符号右移16位（包括符号位一起移）
        int i = h >>> 16;
        // 00000000000000001111111111111111 原本高位的16个1都移到了左边，左边空出的位置补0
        System.out.println("0000000000000000" + Integer.toBinaryString( i ) + ":无符号右移16位后的值");
        int hash = h ^ i;
        // 异或运算 11111111111111111111111111111111 i高16位没东西，直接照搬 h，低16位，不同为1，相同为 0
        System.out.println(Integer.toBinaryString( hash ) + ":异或运算后的值");

        System.out.println(Integer.toBinaryString( h & i ) + ":与运算后的值");
        System.out.println(Integer.toBinaryString( h | i ) + ":或运算后的值");

    }


    /**
     * 如果说我们需要通过ID从数组中获取元素，那么就需要把每个字符串都计算出一个在数组中的位置ID。字符串获取ID你能想到什么方式？
     * 一个字符串最直接的获取跟数字相关的信息就是HashCode，可HashCode的取值范围太大了[-2147483648, 2147483647]，不可能直接使用。
     * 那么就需要使用HashCode与数组长度做[与运算]，得到一个可以在数组中出现的位置。如果说有两个元素得到同样的ID，那么这个数组ID下就存放两个字符串
     *
     * 初始化一组字符串集合，这里初始化了7个。
     * 定义一个数组用于存放字符串，注意这里的长度是8，也就是2的3次幂。这样的数组长度才会出现一个 0111 除高位以外都是1的特征，也是为了散列。
     * 接下来就是循环存放数据，计算出每个字符串在数组中的位置。key.hashCode() & (tab.length - 1)。
     * 在字符串存放到数组的过程，如果遇到相同的元素，进行连接操作模拟链表的过程。
     * 最后输出存放结果。
     *
     * 结果：
     * key值=一, idx索引值=0
     * key值=二, idx索引值=4
     * key值=三, idx索引值=1
     * key值=四, idx索引值=3
     * key值=五, idx索引值=4
     * key值=六, idx索引值=5
     * key值=七, idx索引值=3
     * ["一","三",null,"四->七","二->五","六",null,null]
     */
    public static void test_hashMapByMySelf(){
        // 初始化一组字符串
        List<String> list = new ArrayList<>();
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        list.add("五");
        list.add("六");
        list.add("七");

        // 定义要存放的数组
        String[] tab = new String[8];

        // 循环存放
        for (String key : list) {
            // 计算索引位置
            int idx = key.hashCode() & (tab.length - 1);
            System.out.printf("key值=%s, idx索引值=%d%n", key, idx);
            // 存入数组中
            if (tab[idx] == null) {
                tab[idx] = key;
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            tab[idx] = stringBuilder.append(tab[idx]).append("->").append(key).toString();
        }
        // 输出测试结果
        System.out.println(JSON.toJSONString(tab));

    }
    public void jdk18HashMap(){
        Map hashMap = new HashMap();
        hashMap.put(null, null);
    }
}
