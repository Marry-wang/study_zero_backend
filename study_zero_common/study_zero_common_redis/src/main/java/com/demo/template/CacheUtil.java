package com.demo.template;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.demo.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存工具类
 */
@SuppressWarnings("unused")
@RequiredArgsConstructor
@Component
@Slf4j
public class CacheUtil {

    private static RedisProperties redisProperties;
    private final RedisProperties properties;

    private static StringRedisTemplate stringRedisTemplate;

    private final StringRedisTemplate beanStringRedisTemplate;

    public static RedisTemplate<String, Object> redisTemplate;
    public final RedisTemplate<String, Object> beanRedisTemplate;

    @PostConstruct
    public void init() {
        stringRedisTemplate = beanStringRedisTemplate;
        redisTemplate = beanRedisTemplate;
        redisProperties = properties;
    }

    /**
     * 校验字符串方式的redis操作类是否异常
     */
    private static void checkStrTemplate() {
        if (null == stringRedisTemplate) {
            log.error("缓存类初始化失败，请检查");
        }
    }

    /**
     * 删除key
     *
     * @param key 键
     */
    public static void del(String key) {
        checkStrTemplate();
        stringRedisTemplate.delete(key);
    }

    /**
     * 获取当前值的类型
     *
     * @param key 键
     */
    public static DataType keyType(String key) {
        checkStrTemplate();
        return stringRedisTemplate.type(key);
    }

    /**
     * 获取队列长度
     *
     * @param key 键
     */
    public static long len(String key) {
        checkStrTemplate();
        Long length = stringRedisTemplate.opsForList().size(key);
        if (null == length) {
            return 0L;
        }
        return length;
    }

    /**
     * 根据key和步长生成Long Id
     *
     * @param key       RedisKey
     * @param increment 步长
     */
    public static Long generateLongKey(String key, int increment) {
        checkStrTemplate();
        RedisAtomicLong counter = new RedisAtomicLong(key, Objects.requireNonNull(stringRedisTemplate.getConnectionFactory()));
        return counter.addAndGet(increment);
    }

    /**
     * 根据key生成Long Id （步长为1）
     *
     * @param key RedisKey
     */
    public static Long generateLongKey(String key) {
        checkStrTemplate();
        return generateLongKey(key, 1);
    }

    @SuppressWarnings("all")
    public static long increment(String key, long value) {
        checkStrTemplate();
        return stringRedisTemplate.opsForValue().increment(key, value);
    }

    /**
     * key存储的值递增 key不存在 值初始化为0
     *
     * @Param:
     * @return:
     */
    public static void increment(String key) {
        checkStrTemplate();
        stringRedisTemplate.opsForValue().increment(key);
    }


    /**
     * 重新设置过期时间
     *
     * @param key  键
     * @param time 秒
     */
    public static Boolean setTimeOut(String key, long time) {
        checkStrTemplate();
        return stringRedisTemplate.execute((RedisConnection conn) -> {
            conn.expire(key.getBytes(), time);
            return true;
        });
    }

    // ********************************* value只能是string ************************************************

    /**
     * string方式设置缓存
     *
     * @param key     键
     * @param value   值
     * @param timeout 秒
     */
    public static void setSeconds(String key, String value, int timeout) {
        checkStrTemplate();
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * string方式设置缓存
     *
     * @param key     键
     * @param value   值
     * @param timeout 分钟
     */
    public static void setMinutes(String key, String value, int timeout) {
        checkStrTemplate();
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
    }

    /**
     * string方式设置缓存
     *
     * @param key     键
     * @param value   值
     * @param timeout 小时
     */
    public static void setHour(String key, String value, int timeout) {
        checkStrTemplate();
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.HOURS);
    }

    /**
     * string方式设置缓存
     *
     * @param key      键
     * @param value    值
     * @param timeUnit 过期时间的单位
     */
    public static boolean set(final String key, String value, TimeUnit timeUnit) {
        checkStrTemplate();
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, redisProperties.getTimeout(), timeUnit);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存异常", e);
        }
        return result;
    }

    /**
     * string方式设置缓存7天
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        setHour(key, value, 24 * 7);
    }

    /**
     * string方式设置缓存 永久缓存
     *
     * @param key   键
     * @param value 值
     */
    public static void setPermanent(String key, String value) {
        checkStrTemplate();
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * string方式获取缓存，并将内容(json)转换为对象
     *
     * @param key   键
     * @param clazz 结果转换成的类
     */
    public static <T> T get(String key, Class<T> clazz) {
        String jsonStr = get(key);
        if (!StringUtils.hasText(jsonStr)) {
            return null;
        }
        return JSONObject.parseObject(jsonStr, clazz);
    }

    /**
     * string方式获取缓存，并将内容(json)转换为对象
     *
     * @param key  键
     * @param type 结果转换成的类型
     */
    public static <T> T get(String key, TypeReference<T> type) {
        String jsonStr = get(key);
        if (!StringUtils.hasText(jsonStr)) {
            return null;
        }
        return JSONObject.parseObject(jsonStr, type);
    }

    /**
     * string方式获取缓存
     *
     * @param key 键
     */
    public static String get(String key) {
        checkStrTemplate();
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * string方式列表右侧入队
     *
     * @param key   键
     * @param value 值
     */
    public static Long rightPush(String key, String value) {
        checkStrTemplate();
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * string方式列表左侧入队
     *
     * @param key   键
     * @param value 值
     */
    public static Long leftPush(String key, String value) {
        checkStrTemplate();
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * string方式列表左侧出队列
     *
     * @param key 键
     */
    public static String leftPop(String key) {
        checkStrTemplate();
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * string方式列表右侧出队列
     *
     * @param key 键
     */
    public static String rightPop(String key) {
        checkStrTemplate();
        return stringRedisTemplate.opsForList().rightPop(key);
    }


    /**
     * string方式往集合中添加值
     *
     * @param key   键
     * @param value 值
     */
    public static void setAdd(String key, String value) {
        checkStrTemplate();
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        ops.add(value);
    }

    /**
     * string方式判断值是否属于当前集合
     *
     * @param key   键
     * @param value 值
     */
    public static boolean setIsMember(String key, String value) {
        checkStrTemplate();
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        return Boolean.TRUE.equals(ops.isMember(value));
    }

    /**
     * string方式往集合中添加一个集合
     *
     * @param key 键
     * @param set 集合
     */
    public static void setAdd(String key, Set<String> set) {
        for (String val : set) {
            setAdd(key, val);
        }
    }

    /**
     * string方式删除集合中的值
     *
     * @param key   键
     * @param value 值
     */
    @SuppressWarnings("all")
    public static long removeMember(String key, String value) {
        checkStrTemplate();
        return stringRedisTemplate.opsForSet().remove(key, value);
    }

    /**
     * string方式获取集合
     *
     * @param key 键
     */
    public static Set<String> setMembers(String key) {
        checkStrTemplate();
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        //查出所有数据
        return ops.members();
    }

    /**
     * string方式获取列表
     *
     * @param key   键
     * @param start 起始下标 0开始
     * @param end   结束下标 (包含) -1 是到最后
     */
    public static List<String> range(String key, int start, int end) {
        checkStrTemplate();
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * string方式如果不存在则写入数据
     *
     * @param key   键
     * @param value 值
     * @param time  秒
     */
    public static boolean setNx(String key, String value, int time) {
        checkStrTemplate();
        //如果写入成功了，需要给这条数据加时效
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.SECONDS));
    }

    public static boolean setIfAbsent(String key, String value) {
        checkStrTemplate();
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, value));
    }

    /**
     * string方式获取hash中的值
     *
     * @param key     键
     * @param hashKey hash键
     */
    public static Object hashGet(String key, String hashKey) {
        checkStrTemplate();
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * string方式判断是否有该属性
     *
     * @param key   键
     * @param field 属性
     */
    public static boolean hashExists(String key, Object field) {
        checkStrTemplate();
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * string方式往hash中存放数据
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     */
    public static void hashSet(String key, String hashKey, String value) {
        checkStrTemplate();
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * string方式删除hash的某个属性
     *
     * @param key     键
     * @param hashKey hash键
     */
    public static void hashDel(String key, String hashKey) {
        checkStrTemplate();
        stringRedisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * string方式批量删除redis下的数据
     *
     * @param key      键
     * @param hashKeys hash键集合
     */
    @SuppressWarnings("all")
    public static void hashDel(String key, Set<String> hashKeys) {
        checkStrTemplate();
        String[] hashKeyArr = hashKeys.toArray(new String[0]);
        stringRedisTemplate.opsForHash().delete(key, hashKeyArr);
    }

    /**
     * string方式获取hash中属性的数量
     *
     * @param key 键
     */
    public static long hashLen(String key) {
        checkStrTemplate();
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * 获取剩余生效时间
     *
     * @param key
     * @return
     */
    public static long getExpire(String key) {
        checkStrTemplate();
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * string方式获取hash中所有的值列表
     *
     * @param key 键
     */
    public static List<Object> hashValues(String key) {
        checkStrTemplate();
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public static <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = (ValueOperations<String, T>) redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public static boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public static long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 获取递增的序列号
     *
     * @param prefix 生成序列号的前缀
     * @return
     */
    public static String getIncre(String prefix) {
        //序列号前缀加特定标识，如系统模块名之类的 防止重复
        String increResult = null;
        try {
            //如果该key不存在 会自动创建，值为第二个参数delta
            //最终调用的还是jedis的incrBy(byte[] key, long value)方法
            Long increNum = redisTemplate.opsForValue().increment(prefix, 1);
            redisTemplate.expire(prefix, 1, TimeUnit.DAYS);
            //不足4补位
            increResult = String.format("%1$04d", increNum);
        } catch (Exception e) {
            log.error("获取序列号失败");
            /*这里可以根据需求手动生成一个不重复的单号，
             * */
        }
        return increResult;
    }

    /**
     * 加锁，自旋重试三次
     *
     * @param lockKey 锁实体
     * @param data    锁实体
     * @return
     */
    public static boolean lock(String lockKey, Object data) {
        boolean locked = false;
        int tryCount = 3;
        while (!locked && tryCount > 0) {
            locked = redisTemplate.opsForValue().setIfAbsent(lockKey, data, 2, TimeUnit.MINUTES);
            tryCount--;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                log.error("线程被中断" + Thread.currentThread().getId(), e);
            }
        }
        return locked;
    }
}

