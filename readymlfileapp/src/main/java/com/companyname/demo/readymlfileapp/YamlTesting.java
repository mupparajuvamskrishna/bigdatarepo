package com.companyname.demo.readymlfileapp;

import java.io.File;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlTesting {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			// User user = mapper.readValue(new File("user.yaml"), User.class);
			User user = mapper.readValue(
					new File("F:\\springbootworkspace\\readymlfileapp\\src\\main\\resources\\test.yml"), User.class);
			YamlConfigurationClass yamlConfigurationClass = mapper.readValue(
					new File("F:\\springbootworkspace\\readymlfileapp\\src\\main\\resources\\application.yml"),
					YamlConfigurationClass.class);
//		/	System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
			System.out.println(ReflectionToStringBuilder.toString(yamlConfigurationClass, ToStringStyle.MULTI_LINE_STYLE));
			System.out.println(yamlConfigurationClass.getSpring().get("applicaton").get("name"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}