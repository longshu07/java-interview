package org.longshu.interview;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件工具类
 */
public class FileUtil {
    /**
     * 读取本地文件，单词表
     *
     * @param url 单词表.txt文件
     * @return 单词集合(去重)
     */
    public static Set<String> readWordList(String url) {
        Set<String> set = new HashSet<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(url), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split("\t");
                set.add(strings[1]);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }
}
