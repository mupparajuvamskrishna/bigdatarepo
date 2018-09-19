package com.companyname.demo.sparkelasticsearchdemo;

import java.io.Serializable;

public class Employee implements Serializable {
private int empid;
private String empname;
private int empsal;
public int getEmpid() {
	return empid;
}
public void setEmpid(int empid) {
	this.empid = empid;
}
@Override
public String toString() {
	return "Employee [empid=" + empid + ", empname=" + empname + ", empsal=" + empsal + "]";
}
public Employee(int empid, String empname, int empsal) {
	super();
	this.empid = empid;
	this.empname = empname;
	this.empsal = empsal;
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public String getEmpname() {
	return empname;
}
public void setEmpname(String empname) {
	this.empname = empname;
}
public int getEmpsal() {
	return empsal;
}
public void setEmpsal(int empsal) {
	this.empsal = empsal;
}
}
