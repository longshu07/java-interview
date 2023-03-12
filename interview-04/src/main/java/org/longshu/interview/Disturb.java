package org.longshu.interview;

/**
 * 扰动函数
 */
public class Disturb {
    /**
     * 扰动函数
     * @param key
     * @param size
     * @return
     */
    public static int disturbHashIdx(String key, int size) {
        return (size - 1) & (key.hashCode() ^ (key.hashCode() >>> 16));
    }

    /**
     * 不带扰动函数
     * @param key
     * @param size
     * @return
     */
    public static int hashIdx(String key, int size) {
        return (size - 1) & (key.hashCode());
    }
}
