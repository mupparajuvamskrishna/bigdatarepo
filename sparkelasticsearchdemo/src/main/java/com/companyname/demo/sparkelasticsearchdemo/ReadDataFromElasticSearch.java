package com.companyname.demo.sparkelasticsearchdemo;

import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

public class ReadDataFromElasticSearch {
	public static void main(String[] args) {
		// configure spark
		SparkConf sparkConf = new SparkConf().setAppName("Spark RDD foreach Example").setMaster("local[2]")
				.set("spark.es.nodes.discovery", "true").set("spark.es.nodes.client.only", "false")
				.set("spark.es.nodes.wan.only", "false").set("spark.es.nodes", "localhost");
		// start a spark context
		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		// Read Data from Elastic Search Index spark of type docs
		JavaPairRDD<String, Map<String, Object>> esRDD = JavaEsSpark.esRDD(sc, "spark/docs");
		esRDD.foreach(data -> {
			// System.out.println("model="+data._1() + " label=" + data._2());
			System.out.println("Document Id" + data._1());
			System.out.println("Document Value" + data._2());
		});

	}
}
