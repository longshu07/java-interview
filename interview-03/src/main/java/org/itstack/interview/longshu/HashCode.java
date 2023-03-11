package org.itstack.interview.longshu;

import java.util.*;

public class HashCode {

    /**
     * String中的hashCode
     *     public int hashCode() {
     *         int h = hash;
     *         if (h == 0 && value.length > 0) {
     *             char val[] = value;
     *
     *             for (int i = 0; i < value.length; i++) {
     *                 h = 31 * h + val[i];
     *             }
     *             hash = h;
     *         }
     *         return h;
     *     }
     *
     *     s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * @param str
     * @param multiplier
     * @return
     */
    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }


    /**
     * 计算Hash碰撞概率
     * @param multiplier hash计算乘机因子
     * @param hashCodeList hashCode 集合
     * @return
     */
    private static RateInfo hashCollisionRate(Integer multiplier, List<Integer> hashCodeList) {
        int maxHash = hashCodeList.stream().max(Integer::compareTo).get();
        int minHash = hashCodeList.stream().min(Integer::compareTo).get();
        // 碰撞数 = 总数 - 不重复数量
        int collisionCount = (int) (hashCodeList.size() - hashCodeList.stream().distinct().count());
        // 碰撞比率
        double collisionRate = (collisionCount * 1.0) / hashCodeList.size();

        return new RateInfo(maxHash, minHash, multiplier, collisionCount, collisionRate);
    }

    /**
     * 输入多个值，以及多个乘数，返回对应的乘数结果
     * @param strList
     * @param multipliers
     * @return
     */
    public static List<RateInfo> collisionRateList(Set<String> strList, Integer... multipliers) {
        List<RateInfo> rateInfoList = new ArrayList<>();
        for (Integer multiplier : multipliers) {
            List<Integer> hashCodeList = new ArrayList<>();
            for (String str : strList) {
                Integer hashCode = hashCode(str, multiplier);
                hashCodeList.add(hashCode);
            }
            rateInfoList.add(hashCollisionRate(multiplier, hashCodeList));
        }
        return rateInfoList;
    }

    /**
     * 把2 ^ 32方分64个格子进行存放，每个格子都会有对应的数量的hash值，最终把这些数据展示在图表上
     * 64 = 2^6次方。  2 ^ 32 / 2^6 = 2^26 =  67108864
     * @param hashCodeList
     * @return
     */
    public static Map<Integer, Integer> hashArea(List<Integer> hashCodeList) {
        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        // 0x7fffffff Integer的最大值  = 2^31 - 1 = 2147483647
        int intMaxValue = Integer.MAX_VALUE;
        //0x80000000 = Integer的最小值 = -2^31 = -2147483648
        int intMinValue = Integer.MIN_VALUE;
        //67108864 = 2的26次方 = 67108864
        // 把2 ^ 32方分64个格子进行存放，每个格子都会有对应的数量的hash值，最终把这些数据展示在图表上
        // * 64 = 2^6次方。  2 ^ 32 / 2^6 = 2^26 =  67108864
        int twoAnd26 = 67108864;
        for (long i = intMinValue; i <= intMaxValue; i += twoAnd26) {
            long min = i;
            long max = min + twoAnd26;
            // 筛选出每个格子里的哈希值数量，java8流统计；https://bugstack.cn/itstack-demo-any/2019/12/10/%E6%9C%89%E7%82%B9%E5%B9%B2%E8%B4%A7-Jdk1.8%E6%96%B0%E7%89%B9%E6%80%A7%E5%AE%9E%E6%88%98%E7%AF%87(41%E4%B8%AA%E6%A1%88%E4%BE%8B).html
            int num = (int) hashCodeList.parallelStream().filter(x -> x >= min && x < max).count();
            statistics.put(start++, num);
        }
        return statistics;
    }

    public static Map<Integer, Integer> hashArea(Set<String> strList, Integer multiplier){
        System.out.println("multiplier: " + multiplier);
        List<Integer> hashCodeList = new ArrayList<>();
        for (String str : strList) {
            Integer hashCode = hashCode(str, multiplier);
            hashCodeList.add(hashCode);
        }
        return hashArea(hashCodeList);
    }
}
