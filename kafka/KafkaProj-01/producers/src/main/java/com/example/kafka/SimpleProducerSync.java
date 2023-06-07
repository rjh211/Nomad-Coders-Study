package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleProducerSync {
    public static final Logger logger = LoggerFactory.getLogger(SimpleProducerSync.class.getName());
    public static void main(String[] args) {
        String topicName = "simple-topic";

        //kafka producer object configuration setting
        Properties props = new Properties();

        //bootstrap.servers(브로커를 어디로 보낼지?), Message key.serializer.class, value.serializer.class 설정
        //props.setProperty("bootstrap.servers", "192.168.56.101:9092"); 아래 코드와 동일한 설정
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //숫자 사용시 IntegerSerializer등 사용

        //KafkaProducer 객체 생성
        //key = null, value = "hello world"
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);

        //producerRecord 객체 생성. (Producer의 key, value 타입이 같아야한다.)
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "id-001", "hello world 2");

        //producerRecore 객체 생성2 (Key : null)
//        ProducerRecord<String, String> producernullRecord = new ProducerRecord<>(topicName, "null value");//아래 코드와 동일하다
        ProducerRecord<String, String> producernullRecord = new ProducerRecord<>(topicName, null, "null value");

        //kafkaproducer message send
        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
        try {
            RecordMetadata recordMetadata = future.get();
            logger.info("\n #####record metadata received #####\n+" +
                    "partition : "+ recordMetadata.partition() + "\n" +
                    "offset : " + recordMetadata.offset() + "\n" +
                    "timestamp : " + recordMetadata.timestamp() + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}
