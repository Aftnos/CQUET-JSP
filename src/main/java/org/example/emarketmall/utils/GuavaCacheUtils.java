package org.example.emarketmall.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Guava 缓存
 * @author: april
 * @date: 2022年06月08日 21:33
 */
public class GuavaCacheUtils {
    private static final Logger log = LoggerFactory.getLogger(GuavaCacheUtils.class);

    public static CacheBuilder initCacheBuilder(){
        CacheBuilder cacheBuilder= CacheBuilder.newBuilder()
                //设置 cache 的初始大小为10，要合理设置该值
                .initialCapacity(10)
                //设置并发数为5，即同一时间最多只能有5个线程往 cache 执行写入操作
                .concurrencyLevel(5)
                //最大 key 个数
                .maximumSize(20)
                //设置 cache 中的数据在写入之后的存活时间为60秒
                .expireAfterWrite(60, TimeUnit.SECONDS);

        return cacheBuilder;
    }


}
