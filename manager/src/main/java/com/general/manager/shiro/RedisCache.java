package com.general.manager.shiro;

import com.general.manager.conts.RedisConstans;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作类
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private static final String REDIS_SHIRO_CACHE = RedisConstans.REDIS_SHIRO_CACHE;
    private String cacheKey;
    private RedisTemplate<K, V> redisTemplate;
    @Value("${shiro.globalSessionTimeout}")
    private Long globExpire;

    @SuppressWarnings("rawtypes")
    public RedisCache(String name, RedisTemplate client) {
        this.cacheKey = REDIS_SHIRO_CACHE + name + ":";
        this.redisTemplate = client;

    }

    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }

    @Override
    public V get(K k) throws CacheException {
        V value = redisTemplate.boundValueOps(getCacheKey(k)).get();
        if (value != null) {
            redisTemplate.boundValueOps(getCacheKey(k)).expire(globExpire, TimeUnit.SECONDS);
        }
        return value;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        V old = get(k);
        redisTemplate.boundValueOps(getCacheKey(k)).set(v);
        redisTemplate.boundValueOps(getCacheKey(k)).expire(globExpire, TimeUnit.SECONDS);
        return old;
    }

    @Override
    public V remove(K k) throws CacheException {
        V old = get(k);
        redisTemplate.delete(getCacheKey(k));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = this.keys();
        List<V> list = new ArrayList<>();
        for (K key : keys) {
            list.add(get(key));
        }
        return list;
    }
}
