package com.companyname.demo.sprngbootcloudbinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@EnableAutoConfiguration
@EnableBinding(EmailQueues.class)
public class Example {
	@Autowired
	private EmailQueues emailQueues;

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "sendChatMessage")
	public void sendToQueue() throws JsonProcessingException {
		MessageChannel messageChannel = emailQueues.outboundEmails();
		// Email email = new Email();
		ChatMessage chatMessage = new ChatMessage("Hello World", System.currentTimeMillis());
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = objectMapper.writeValueAsString(chatMessage);
		messageChannel.send(MessageBuilder.withPayload(jsonStr)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());

	}

	@StreamListener(EmailQueues.INPUT)
	public void handleEmail(@Payload ChatMessage chatMessage) {
		//System.out.println("chatMessage chatMessage .........: " + chatMessage.getContents());
		System.out.println(chatMessage);
	}

	/*@ServiceActivator(inputChannel = EmailQueues.INPUT)
	public void loggerSink(String payload) {
		// String payloaddata=new String(payload);
		System.out.println("Received : " + payload);
	}*/

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}

}