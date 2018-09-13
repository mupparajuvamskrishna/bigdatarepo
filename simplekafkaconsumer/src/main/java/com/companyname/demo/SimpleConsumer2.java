package com.companyname.demo;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer2 {
	public static void main(String[] args) {
		System.out.println("getmessagefromkafkatopic.............my-consumer-group-2 ..........started");
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
		props.put("group.id", "my-consumer-group-2");
		props.put("zookeeper.connect", "localhost:2181");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("my-kafka-topic"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			//System.out.println("getmessagefromkafkatopic.............my-consumer-group-2 ..........ended");
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}
	}
}