package com.companyname.service;

import java.util.List;

import com.companyname.model.Employee;

public interface EmployeeService {
    void insertEmployeeUsingEmployeeId(String employeeId);
       
	void insertEmployee(Employee emp);

	void insertEmployees(List<Employee> employees);

	void getAllEmployees();

	void getEmployeeById(String empid);

}