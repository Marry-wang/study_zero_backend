import com.demo.AppZero;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisTest
 * @Author 王孟伟
 * @Date 2021/10/20 17:12
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppZero.class})
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void residZwero1(){
        redisTemplate.opsForValue().set("myKey","myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

//    @Autowired(required = false)
//    private JedisCluster jedisCluster;
//
//    @Test
//    public void residZwero(){
//        jedisCluster.set("myKey1","myValue");
//        System.out.println(jedisCluster.get("myKey1"));
//    }

//    @Test
//    public void testJedisPool(){
//        //创建一个数据库连接池对象（单例，即一个系统共用一个连接池），需要指定服务的IP和端口号
//        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"192.168.252.128", 6379,1000,"demo1");
//        //从连接池中获得连接
//        Jedis jedis = jedisPool.getResource();
//        //使用jedis操作数据库（方法级别，就是说只是在该方法中使用，用完就关闭）
//        String result = jedis.get("a");
//        System.out.println(result);
//        //用完之后关闭jedis连接
//        jedis.close();
//        //系统关闭前先关闭数据库连接池
//        jedisPool.close();


//        HostAndPort hostAndPort = new HostAndPort("192.168.252.128", 7010);
//        HostAndPort hostAndPort1 = new HostAndPort("192.168.252.128", 7011);
//        HostAndPort hostAndPort2 = new HostAndPort("192.168.252.128", 7012);
//        Set<HostAndPort> hostAndPortSet = new HashSet<>();
//        hostAndPortSet.add(hostAndPort);
//        hostAndPortSet.add(hostAndPort1);
//        hostAndPortSet.add(hostAndPort2);
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(300);
//
//
//        JedisCluster jediscluster = new JedisCluster(hostAndPortSet, 1000, 1000, 10, "demo1", jedisPoolConfig);
//
//
//
//        String a = jediscluster.get("a");
//        System.out.println(a);
//    }


}
