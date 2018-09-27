package com.companyname.demo.readymlfileapp;

import java.util.Map;

public class YamlConfigurationClass {
	private Map<String, Map<String, String>> spring;
	private Map<String, String> server;
	public Map<String, String> getServer() {
		return server;
	}

	public void setServer(Map<String, String> server) {
		this.server = server;
	}

	public Map<String, Map<String, String>> getSpring() {
		return spring;
	}

	public void setSpring(Map<String, Map<String, String>> spring) {
		this.spring = spring;
	}

	@Override
	public String toString() {
		return "YamlConfigurationClass [spring=" + spring + ", server=" + server + "]";
	}

	
}
