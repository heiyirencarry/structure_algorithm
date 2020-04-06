package com.black.boy.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class PrintMsgHandler implements IMsgHandler<String, String> {

	@Override
	public void handler(ConsumerRecord<String, String> record) {
		System.out.println(record.value());
		System.out.println(record.key());
		System.out.println(record.value());
		System.out.println(record.offset());
	}

}
