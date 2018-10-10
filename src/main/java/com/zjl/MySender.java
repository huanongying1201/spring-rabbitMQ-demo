package com.zjl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 
 * 描述:消息发送类，项目启动时执行
 * @author  zhoujialin
 * @created 2018年10月10日 上午11:40:57
 * @since   v1.0.0
 */
@Component
public class MySender implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MySender.class);
	//注入消息处理模板
	@Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
    	logger.info("Sending message...");
        rabbitTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", "Hello from RabbitMQ!");
    }

}
