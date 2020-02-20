package com.companyname.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author VamsiKrishna Mupparaju
 *
 */
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class HelloWorldController {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	/*
	 * @Autowired public DataSource dataSource;
	 */

	@RequestMapping("/hello")
	@ResponseBody
	public String sayHello() {
		return "Hello World Developer!!!";
	}

	@RequestMapping("/getAllEmployees")
	@ResponseBody
	public List<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
		List<Employee> employees = new ArrayList<Employee>();
		Class.forName(driverName);
		// replace "hduser" here with the name of the user the queries should run as
		Connection connection = DriverManager.getConnection(
				"jdbc:hive2://ec2-34-232-77-88.compute-1.amazonaws.com:10000/default", "hadoop", "hadoop");
		// Connection connection = dataSource.getConnection();
		String selectQuery = "select * from employee";
		Statement statement = connection.createStatement();
		ResultSet res = statement.executeQuery(selectQuery);
		while (res.next()) {
			// inception.setTop1(res.getString("top1"));
			employees.add(new Employee(Integer.parseInt(res.getString("empid").toString()),
					res.getString("empname").toString()));
		}

		return employees;
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldController.class, args);
	}

}