package com.companyname.controller;

public class Employee {
private int empid;
@Override
public String toString() {
	return "Employee [empid=" + empid + ", empname=" + empname + "]";
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee(int empid, String empname) {
	super();
	this.empid = empid;
	this.empname = empname;
}
private String empname;
public int getEmpid() {
	return empid;
}
public void setEmpid(int empid) {
	this.empid = empid;
}
public String getEmpname() {
	return empname;
}
public void setEmpname(String empname) {
	this.empname = empname;
}
}
