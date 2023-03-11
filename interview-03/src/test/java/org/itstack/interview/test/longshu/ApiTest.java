package org.itstack.interview.test.longshu;


import org.itstack.interview.longshu.HashCode;
import org.itstack.interview.longshu.FileUtil;
import org.itstack.interview.longshu.RateInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class ApiTest {

    private Set<String> words;

    @Before
    public void before() {
        "abc".hashCode();
        // 读取文件，103976个英语单词库.txt

        words = FileUtil.readWordList("D:\\code\\java-interview\\interview-03\\103976个英语单词库.txt");
    }

    /**
     * 单词数量：103976
     * 乘数 =    2, 最小Hash =          97, 最大Hash = 1842581979, 碰撞数量 = 60382, 碰撞概率 = 58.0730%
     * 乘数 =    3, 最小Hash = -2147308825, 最大Hash = 2146995420, 碰撞数量 = 24300, 碰撞概率 = 23.3708%
     * 乘数 =    5, 最小Hash = -2147091606, 最大Hash = 2147227581, 碰撞数量 =  7994, 碰撞概率 = 7.6883%
     * 乘数 =    7, 最小Hash = -2147431389, 最大Hash = 2147226363, 碰撞数量 =  3826, 碰撞概率 = 3.6797%
     * 乘数 =   17, 最小Hash = -2147238638, 最大Hash = 2147101452, 碰撞数量 =   576, 碰撞概率 = 0.5540%
     * 乘数 =   31, 最小Hash = -2147461248, 最大Hash = 2147444544, 碰撞数量 =     2, 碰撞概率 = 0.0019%
     * 乘数 =   32, 最小Hash = -2007883634, 最大Hash = 2074238226, 碰撞数量 = 34947, 碰撞概率 = 33.6106%
     * 乘数 =   33, 最小Hash = -2147469046, 最大Hash = 2147378587, 碰撞数量 =     1, 碰撞概率 = 0.0010%
     * 乘数 =   39, 最小Hash = -2147463635, 最大Hash = 2147443239, 碰撞数量 =     0, 碰撞概率 = 0.0000%
     * 乘数 =   41, 最小Hash = -2147423916, 最大Hash = 2147441721, 碰撞数量 =     1, 碰撞概率 = 0.0010%
     * 乘数 =  199, 最小Hash = -2147459902, 最大Hash = 2147480320, 碰撞数量 =     0, 碰撞概率 = 0.0000%
     */
    @Test
    public void test_collisionRate() {
        System.out.println("单词数量：" + words.size());
        List<RateInfo> rateInfoList = HashCode.collisionRateList(words, 2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199);
        for (RateInfo rate : rateInfoList) {
            System.out.println(String.format("乘数 = %4d, 最小Hash = %11d, 最大Hash = %10d, 碰撞数量 =%6d, 碰撞概率 = %.4f%%", rate.getMultiplier(), rate.getMinHash(), rate.getMaxHash(), rate.getCollisionCount(), rate.getCollisionRate() * 100));
        }
    }

    @Test
    public void test_hashArea() {

        System.out.println(HashCode.hashArea(words, 2).values());
        System.out.println(HashCode.hashArea(words, 7).values());
        System.out.println(HashCode.hashArea(words, 31).values());
        System.out.println(HashCode.hashArea(words, 32).values());
        System.out.println(HashCode.hashArea(words, 199).values());
    }

}
