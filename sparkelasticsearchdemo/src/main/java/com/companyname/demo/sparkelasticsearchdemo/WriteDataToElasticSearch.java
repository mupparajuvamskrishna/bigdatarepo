package com.companyname.demo.sparkelasticsearchdemo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableList;

public class WriteDataToElasticSearch {
public static void main(String[] args) {
	Employee employee1 = new Employee(101, "vamsi", 1000);
	Employee employee2 = new Employee(201, "krishna", 2000);

	String json1 = "{\"reason\" : \"business\",\"airport\" : \"SFO\"}";
	String json2 = "{\"participants\" : 5,\"airport\" : \"OTP\"}";

	// configure spark
	SparkConf sparkConf = new SparkConf().setAppName("Spark RDD foreach Example").setMaster("local[2]")
			.set("spark.es.nodes.discovery", "true").set("spark.es.nodes.client.only", "false")
			.set("spark.es.nodes.wan.only", "false").set("spark.es.nodes", "localhost");
	// start a spark context
	JavaSparkContext sc = new JavaSparkContext(sparkConf);
	/*
	 * JavaRDD<Employee> javaRDD = sc.parallelize(ImmutableList.of(employee1,
	 * employee2)); JavaEsSpark.saveToEs(javaRDD, "spark/docs");
	 */
	JavaRDD<String> stringRDD = sc.parallelize(ImmutableList.of(json1, json2));
	JavaEsSpark.saveJsonToEs(stringRDD, "sparkjsonindex/sparkjsontypes");
	
}
}
