package org.longshu.interview.test;


import org.longshu.interview.Disturb;
import org.junit.Before;
import org.junit.Test;
import org.longshu.interview.FileUtil;
import org.longshu.interview.Test_hashMapByMySelf;

import java.util.*;

public class ApiTest {
    private Set<String> words;

    @Before
    public void before() {
        // 读取文件，103976个英语单词库.txt
        words = FileUtil.readWordList("D:\\code\\java-interview\\interview-04\\103976个英语单词库.txt");
    }

    /**
     * 使用扰动函数
     */
    @Test
    public void test_disturb() {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (String word : words) {
            // 使用扰动函数
            int idx = Disturb.disturbHashIdx(word, 128);
            if (map.containsKey(idx)) {
                Integer integer = map.get(idx);
                map.put(idx, ++integer);
            } else {
                map.put(idx, 1);
            }
        }
        System.out.println(map.values());
    }

    /**
     * 不使用扰动函数
     */
    @Test
    public void test_hashIdx() {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (String word : words) {
            // 不使用扰动函数
            int idx = Disturb.hashIdx(word, 128);
            if (map.containsKey(idx)) {
                Integer integer = map.get(idx);
                map.put(idx, ++integer);
            } else {
                map.put(idx, 1);
            }
        }
        System.out.println(map.values());
    }


    @Test
    public void test_hashMap() {
        List<String> list = new ArrayList<>();
        list.add("jlkk");
        list.add("lopi");
        list.add("jmdw");
        list.add("e4we");
        list.add("io98");
        list.add("nmhg");
        list.add("vfg6");
        list.add("gfrt");
        list.add("alpo");
        list.add("vfbh");
        list.add("bnhj");
        list.add("zuio");
        list.add("iu8e");
        list.add("yhjk");
        list.add("plop");
        list.add("dd0p");

        for (String key : list) {
                int hash = key.hashCode() ^ (key.hashCode() >>> 16);
            System.out.println("字符串：" + key + " \tIdx(16)：" + ((16 - 1) & hash) + " \tBit值：" + Integer.toBinaryString(hash) + " -【hash & oldCap】= " + Integer.toBinaryString(hash & 16) + " \t\tIdx(32)：" + ((32 - 1) & hash));
            System.out.println(Integer.toBinaryString(key.hashCode()) +" "+ Integer.toBinaryString(hash) + " " + Integer.toBinaryString((32 - 1) & hash));
        }
    }



}
