package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {
        //kafka producer object configuration setting
        Properties props = new Properties();

        //bootstrap.servers(브로커를 어디로 보낼지?), Message key.serializer.class, value.serializer.class 설정
        //props.setProperty("bootstrap.servers", "192.168.56.101:9092"); 아래 코드와 동일한 설정
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }
}
