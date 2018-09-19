package com.companyname.demo.sparkelasticsearchdemo;

import java.util.ArrayList;
import java.util.List;

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

public class CreateDataSetFromEmployeeClassData {
	public static void main(String[] args) {
		SparkSession sparkSession = SparkSession.builder().master("local").appName("jsonreader").getOrCreate();
		Dataset<Employee> employees = sparkSession.read()
				.json(Thread.currentThread().getContextClassLoader().getResource("employees.json").getFile())
				.as(Encoders.bean(Employee.class));
		employees.show();
		System.out.println("..................Dataset employees.........................");
		Dataset<Row> employeeRowDataSet = sparkSession.read()
				.json(Thread.currentThread().getContextClassLoader().getResource("employees.json").getFile()); // employees.show();
																												// //
		Employee employee1 = new Employee(101, "vamsi", 1000);
		Employee employee2 = new Employee(201, "krishna", 2000);
		JavaSparkContext jsc = new JavaSparkContext(sparkSession.sparkContext());
		JavaRDD<Employee> stringRDD = jsc.parallelize(ImmutableList.of(employee1, employee2));
		//Save employeerdd to elasticsearch
		//JavaEsSpark.saveToEs(stringRDD, "readdatafromjson/readdatafromjson");
		
		Row r1 = RowFactory.create("name1", "value1", "101");
		Row r2 = RowFactory.create("name2", "value2", "201");
		StructType schemata = DataTypes.createStructType(
				new StructField[] { new StructField("empid", DataTypes.StringType, false, Metadata.empty()),
						new StructField("empname", DataTypes.StringType, false, Metadata.empty()),
						new StructField("empsal", DataTypes.StringType, false, Metadata.empty()) });
		List<Row> rowList = ImmutableList.of(r1, r2);
		Dataset<Row> data = sparkSession.sqlContext().createDataFrame(rowList, schemata);
		data.show();
	}
}
