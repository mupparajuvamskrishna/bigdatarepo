package com.companyname.demo.kafkasampleapplication;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Properties props = new Properties();
        /*props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "client1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
       */ 
    	props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "client1");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer
                <String, String>(props);
        
        /*for(int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>("mytopic", 
               Integer.toString(i), Integer.toString(i)));
                  System.out.println(“Message sent successfully”);
                  producer.close();*/
        
        producer.send(new ProducerRecord<String, String>("mytopic","Hi How are you"));
        producer.close();
    }
}
