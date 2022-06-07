package com.demo.domain;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.Properties;

/**
 * @author wangmengwei
 * @date 2022年06月07日 11:56
 */
public class RedisSource extends RichSourceFunction<String> {

    private FlinkJedisPoolConfig config;

    private String key = "materListJson";

    public RedisSource(FlinkJedisPoolConfig config) {
        this.config = config;
    }

    @Override
    public void open(Configuration configuration) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("port", String.valueOf(config.getPort()));
        properties.setProperty("host", config.getHost());
        properties.setProperty("password", config.getPassword());
        properties.setProperty("database", String.valueOf(config.getDatabase()));
        configuration.addAllToProperties(properties);
    }

    @Override
    public void run(SourceContext<String> sourceContext) throws Exception {
        JedisPool jedisPool = new JedisPool(
                new JedisPoolConfig(),
                config.getHost(),
                config.getPort(),
                Protocol.DEFAULT_TIMEOUT,
                config.getPassword());
        Jedis resource = jedisPool.getResource();
        sourceContext.collect(resource.get(key));

    }

    @Override
    public void cancel() {

    }
}
