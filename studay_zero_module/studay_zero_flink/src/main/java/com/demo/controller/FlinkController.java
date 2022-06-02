package com.demo.controller;

import com.demo.domain.Person;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmengwei
 * @date 2022年06月02日 14:34
 */
public class FlinkController {

    public static void main(String[] args) throws Exception {
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
}
