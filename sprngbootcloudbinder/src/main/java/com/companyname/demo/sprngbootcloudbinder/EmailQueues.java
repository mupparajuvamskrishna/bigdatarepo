package com.companyname.demo.sprngbootcloudbinder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EmailQueues {

	String INPUT = "email-in";
	String OUTPUT = "email-out";

	@Input(INPUT)
	SubscribableChannel inboundEmails();

	@Output(OUTPUT)
	MessageChannel outboundEmails();
}