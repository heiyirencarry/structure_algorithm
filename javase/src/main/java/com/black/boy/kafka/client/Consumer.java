package com.black.boy.kafka.client;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.black.boy.kafka.IMsgHandler;
import com.black.boy.kafka.config.ConfigLoader;

public class Consumer<K,V> implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	
    private static final String PROP_KEY_GROUP_ID = "group.id"; //消费者组的key
    private static final String DEFAULT_GROUP_ID = "blackboy"; //默认的消费者组
    private String groupId = null; //消费者组  传入
	
	private KafkaConsumer<K, V> consumer = null;
	private List<String> topics;
	
	private IMsgHandler<K, V> handler;
	
	private ThreadPoolExecutor handlerMsgPool = null;
	
	private ScheduledExecutorService threadPoolMonitorSchedule = Executors.newScheduledThreadPool(1);//监控线程池
	
	public Consumer(List<String> topics, String groupId, IMsgHandler<K, V> handler) {
		init(topics, groupId, handler);
	}
	
	public Consumer(List<String> topic, IMsgHandler<K, V> handler) {
		init(topic, DEFAULT_GROUP_ID, handler);
	}
	
	private void init(List<String> topics, String groupId, IMsgHandler<K, V> handler) {
		this.topics = topics;
		this.handler = handler;
		this.groupId = groupId;
		
		Properties p = ConfigLoader.getInstance().loadConfig();
		p.put(PROP_KEY_GROUP_ID, groupId);//消费者组
		
		consumer = new KafkaConsumer<K, V>(p);
		consumer.subscribe(topics);
		
													//核心线程数  最大线程数  空闲多少时间回收线程但核心的数量不会回收，任务保存的队列，    这些参数可以配置文件也可以常量处理                          
		this.handlerMsgPool = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(200),
				//这个对象时任务被异常处理后，调用queue的阻塞方法，等待处理
				new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				if(executor.isShutdown()) {
					try {
						executor.getQueue().put(r);//阻塞方法等待放入队列
					} catch (InterruptedException e) {
						logger.error("handle rejectedExecution fail", e);
					}
				}
			}
		});
	}

	@Override
	public void run() {
		
		//设置一个监控的线程
		threadPoolMonitorSchedule.scheduleAtFixedRate(new MonitorJob(), 0, 30, TimeUnit.SECONDS);
		
		while(true) {
			ConsumerRecords<K,V> records = consumer.poll(100);
			records.forEach(record -> handlerMsgPool.submit(new Runnable() {
				@Override
				public void run() {
					handler.handler(record);
				}
			}));
		}
	}
	
	class MonitorJob implements Runnable {
		@Override
		public void run() {
			int size = handlerMsgPool.getQueue().size();
			int poolSize = handlerMsgPool.getPoolSize();
			int activeCount = handlerMsgPool.getActiveCount();
			long completedTaskCount = handlerMsgPool.getCompletedTaskCount(); //成功执行了的任务数量
//			logger.info("KfkConsumer thread pool status: poolSize[{}]", size, poolSize, activeCount, completedTaskCount);
		}
	}
	
}
