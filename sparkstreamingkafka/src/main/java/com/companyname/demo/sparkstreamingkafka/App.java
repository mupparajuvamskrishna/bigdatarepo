package com.companyname.demo.sparkstreamingkafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import kafka.serializer.StringDecoder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		SparkConf conf = new SparkConf().setAppName("kafka-sandbox").setMaster("local[*]");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(2000));

		// TODO: processing pipeline
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", "localhost:9092,localhost:9093,localhost:9094");
		Set<String> topics = Collections.singleton("my-kafka-topic");
		JavaPairInputDStream<String, String> directKafkaStream = KafkaUtils.createDirectStream(ssc, String.class,
				String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topics);
		directKafkaStream.foreachRDD(rdd -> {
		    System.out.println("--- New RDD with " + rdd.partitions().size()
		            + " partitions and " + rdd.count() + " records");
		    rdd.foreach(record -> System.out.println(record._2));
		});
		ssc.start();
		ssc.awaitTermination();
	}
}
