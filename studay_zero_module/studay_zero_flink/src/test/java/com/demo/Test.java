package com.demo;

import com.demo.domain.Person;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.springframework.boot.test.context.SpringBootTest;

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
//        SingleOutputStreamOperator<String> map =fromCollection.map(x->x.getName()+x.getAge());
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

}
