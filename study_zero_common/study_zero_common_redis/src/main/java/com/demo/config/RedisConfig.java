package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Author 王孟伟
 * @Date 2023/0525 15:35
 * @Version 1.0
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }





//    @Value("${spring.redis.cluster.nodes}")
//    private String clusterNodes;
//    @Value("${spring.redis.cluster.max-redirects}")
//    private int maxRedirects;
//    @Value("${redis.password}")
//    private String password;
//    @Value("${redis.timeout}")
//    private int timeout;
//    @Value("${redis.maxIdle}")
//    private int maxIdle;
//    @Value("${redis.maxTotal}")
//    private int maxTotal;
//    @Value("${redis.maxWaitMillis}")
//    private int maxWaitMillis;
//    @Value("${redis.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//    @Value("${redis.numTestsPerEvictionRun}")
//    private int numTestsPerEvictionRun;
//    @Value("${redis.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//    @Value("${redis.testOnBorrow}")
//    private boolean testOnBorrow;
//    @Value("${redis.testWhileIdle}")
//    private boolean testWhileIdle;
//    @Bean
//    public JedisPoolConfig getJedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        // 最大空闲数
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        // 连接池的最大数据库连接数
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        // 最大建立连接等待时间
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
//        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
//        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
//        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
//        // 在空闲时检查有效性, 默认false
//        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
//        return jedisPoolConfig;
//    }
//
//    /**
//     * Redis集群的配置
//     * @return RedisClusterConfiguration
//     * @throws
//     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = clusterNodes.split(",");
//        Set<RedisNode> nodes = new HashSet<RedisNode>();
//        for(String ipPort:serverArray){
//            String[] ipAndPort = ipPort.split(":");
//            nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
//        }
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(maxRedirects);
//        redisClusterConfiguration.setPassword(RedisPassword.of(password));
//        return redisClusterConfiguration;
//    }
//
//    /**
//     * @param
//     * @return
//     * @Description:redis连接工厂类
//     * @date 2018/10/25 19:45
//     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        //集群模式
//        JedisConnectionFactory  factory = new JedisConnectionFactory(redisClusterConfiguration(),getJedisPoolConfig());
//        factory.setDatabase(0);
//        factory.setTimeout(timeout);
//        factory.setUsePool(true);
//        return factory;
//    }
//
//    /**
//     * 实例化 RedisTemplate 对象
//     *
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        initDomainRedisTemplate(redisTemplate);
//        return redisTemplate;
//    }
//
//    /**
//     * 设置数据存入 redis 的序列化方式,并开启事务
//     * 使用默认的序列化会导致key乱码
//     *
//     */
//    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //这个地方有一个问题，这种序列化器会将value序列化成对象存储进redis中，如果
//        //你想取出value，然后进行自增的话，这种序列化器是不可以的，因为对象不能自增；
//        //需要改成StringRedisSerializer序列化器。
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setEnableTransactionSupport(false);
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//    }







//    @Autowired
//    private RedisProperties redisProperties;
//
//    /**
//     * 注意：
//     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
//     * @return
//     */
//    @Bean
//    public JedisCluster getJedisCluster() {
//        //获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
//        String[] serverArray = redisProperties.getHost().split(",");
//        Set<HostAndPort> nodes = Sets.newHashSet();
//
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(300);
//        JedisCluster jedisCluster = new JedisCluster(nodes, redisProperties.getTimeout(), redisProperties.getTimeout(), 10, redisProperties.getPassword(),jedisPoolConfig );
//        return jedisCluster;
//    }


}
