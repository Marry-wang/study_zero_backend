package com.demo;

import com.demo.domain.Person;
import com.demo.domain.RedisSource;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.util.Collector;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wangmengwei
 * @date 2022年06月06日 13:45
 */
@SpringBootTest
public class Test {

    @org.junit.Test
    public void test1() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("1");
        Person person1 = new Person();
        person1.setName("2");
        personList.add(person1);
        personList.add(person);
        DataStreamSource<Person> objectDataStreamSource = env.fromCollection(personList);

        objectDataStreamSource.print();
        env.execute("sensor");
    }

    @org.junit.Test
    public void testReadFile() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> readTextFile = env.readTextFile("C:\\Users\\zh_he\\Desktop\\1.txt");
        readTextFile.print();
        env.execute();
    }

    @org.junit.Test
    public void toMapTest() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("1");
        person.setAge("1");
        Person person1 = new Person();
        person1.setName("2");
        person1.setAge("2");
        Person person2 = new Person();
        person2.setName("2");
        person2.setAge("2");
        personList.add(person2);
        personList.add(person1);
        personList.add(person);


        DataStreamSource<Person> fromCollection = env.fromCollection(personList);
        fromCollection.keyBy(Person::getName).print();
        System.out.println("---------------------------------------------");
        fromCollection.filter(x->x.getName().equals("2")).print();

//        SingleOutputStreamOperator<Person> filter = fromCollection.filter(x -> x.getName().equals("1"));
//        filter.print();
        SingleOutputStreamOperator<String> map =fromCollection.map(Person::getName);
//        map.print();
        env.execute();
    }

    @org.junit.Test
    public void executionEnvironment() throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("1");
        person.setAge("1");
        Person person1 = new Person();
        person1.setName("2");
        person1.setAge("2");
        Person person2 = new Person();
        person2.setName("2");
        person2.setAge("2");
        personList.add(person2);
        personList.add(person);
        personList.add(person1);
        DataSource<Person> personDataSource = env.fromCollection(personList);
        personDataSource.print();
    }

    @org.junit.Test
    public void shuffle() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("1");
        person.setAge("1");
        Person person1 = new Person();
        person1.setName("2");
        person1.setAge("2");
        Person person2 = new Person();
        person2.setName("2");
        person2.setAge("2");
        personList.add(person2);
        personList.add(person);
        personList.add(person1);
        DataStreamSource<Person> personDataStreamSource = env.fromCollection(personList);

        DataStream<Person> shuffle = personDataStreamSource.shuffle();

        personDataStreamSource.print("data");
        shuffle.print("shuffle");

        env.execute();
    }

    @org.junit.Test
    public void flinkSplit() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("1");
        person.setAge("1");
        Person person1 = new Person();
        person1.setName("2");
        person1.setAge("2");
        Person person2 = new Person();
        person2.setName("2");
        person2.setAge("2");
        personList.add(person2);
        personList.add(person);
        personList.add(person1);
        DataStreamSource<Person> personDataStreamSource = env.fromCollection(personList);

        personDataStreamSource.process(new ProcessFunction<Person, String>() {
            @Override
            public void processElement(Person person, ProcessFunction<Person, String>.Context context, Collector<String> collector) throws Exception {

            }
        });

        env.execute();
    }
    
    @org.junit.Test
    public void connect() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("1");
        person.setAge("1");
        Person person1 = new Person();
        person1.setName("2");
        person1.setAge("2");
        Person person2 = new Person();
        person2.setName("2");
        person2.setAge("2");
        personList.add(person2);
        personList.add(person);


        List<Person> personList1 = new ArrayList<>();
        personList1.add(person1);

        DataStreamSource<List<Person>> listDataStreamSource = env.fromElements(personList);
        DataStreamSource<List<Person>> listDataStreamSource1 = env.fromElements(personList1);
        ConnectedStreams<List<Person>, List<Person>> connect = listDataStreamSource.connect(listDataStreamSource1);
        connect.map(new CoMapFunction<List<Person>, List<Person>, List<Person>>() {

            List<Person> personList = new ArrayList<>();
            @Override
            public List<Person> map1(List<Person> people) throws Exception {
                people.forEach(x->{x.setAge("1"); personList.add(x);});
                return personList;
            }

            @Override
            public List<Person> map2(List<Person> people) throws Exception {
                people.forEach(x->{x.setAge("1"); personList.add(x);});
                return personList;
            }
        }).print();

        env.execute();
    }

    @org.junit.Test
    public void finkWriteToRedis() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<String> list = new ArrayList<>();
        list.add("1");
        DataStreamSource<String> fromCollection = env.fromCollection(list);

        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder()
                .setHost("47.104.9.149")
				.setPort(6379)
                .setPassword("ShuangDuan.123")
				.setDatabase(0)
                .build();

        fromCollection.addSink(new RedisSink<>(conf, new RedisMapper<String>() {
            @Override
            public RedisCommandDescription getCommandDescription() {
                return new RedisCommandDescription(RedisCommand.HSET, "login_web");
            }

            @Override
            public String getKeyFromData(String s) {
                return s;
            }

            @Override
            public String getValueFromData(String s) {
                return s;
            }
        }));

        fromCollection.print();

        env.execute();
    }

    @org.junit.Test
    public void finkReadOnRedis() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder()
                .setHost("47.104.9.149")
                .setPort(6379)
                .setPassword("ShuangDuan.123")
                .setDatabase(0)
                .build();

        env.addSource(new RedisSource(conf)).print();

        env.execute();
    }

}
