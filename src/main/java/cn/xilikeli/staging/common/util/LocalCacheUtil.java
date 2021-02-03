package cn.xilikeli.staging.common.util;

import cn.xilikeli.staging.common.constant.CommonConstant;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 本地缓存工具类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Slf4j
public class LocalCacheUtil {

    /**
     * 本地缓存统一前缀
     */
    public static final String CACHE_PREFIX = "local_cache_";

    /**
     * 保存缓存键值对的本地缓存
     * LRU 算法
     */
    private static final LoadingCache<String, String> LOCAL_CACHE = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                // 默认的数据加载实现,当调用 get 取缓存值的时候,如果 key 没有对应的值,就调用这个方法进行加载
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    /**
     * 添加缓存键值对到本地缓存中
     *
     * @param key   缓存键
     * @param value 缓存值
     */
    public static void setLocalCache(String key, String value) {
        LOCAL_CACHE.put(key, value);
    }

    /**
     * 获取本地缓存中缓存键对应的的缓存值
     *
     * @param key 缓存键
     * @return 返回缓存键 key 对应的缓存值, 没有值则返回 null
     */
    public static String getLocalCache(String key) {
        // 缓存值
        String value = null;

        try {
            value = LOCAL_CACHE.get(key);

            if (CommonConstant.NULL.equals(value)) {
                return null;
            }

            return value;
        } catch (ExecutionException e) {
            log.error("从本地缓存(LocalCacheUtil)获取 " + CACHE_PREFIX + key + " 的值出现异常: ", e);
        }

        return null;
    }

}