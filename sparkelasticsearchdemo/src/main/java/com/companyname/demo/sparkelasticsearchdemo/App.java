package com.companyname.demo.sparkelasticsearchdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableList;

/**
 * Hello world!
 *
 */
public class App {
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
		/*
		 * //Read Data from Elastic Search Index spark of type docs JavaPairRDD<String,
		 * Map<String, Object>> esRDD = JavaEsSpark.esRDD(sc, "spark/docs");
		 * esRDD.foreach(data -> { //System.out.println("model="+data._1() + " label=" +
		 * data._2()); System.out.println("Document Id"+data._1());
		 * System.out.println("Document Value"+data._2()); });
		 */

	}
}
