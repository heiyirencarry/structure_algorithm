package com.black.boy.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IMsgHandler<K,V> {

	void handler(ConsumerRecord<K, V> record);
}
