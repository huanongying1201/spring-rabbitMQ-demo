package com.zjl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zjl.listener.MqListener;

/**
 * 
 * 描述:消息对列配置类
 * @author  zhoujialin
 * @created 2018年10月10日 上午11:56:58
 * @since   v1.0.0
 */
@Configuration
public class MqConfig {
	
	@Value("${rabbitmq.exchangeName}")
	private String exchangeName;
	@Value("${rabbitmq.queueName}")
	private String queueName;
	@Value("${rabbitmq.routingKey}")
	private String routingKey;
	//处理消息的方法名
	private static final String HANDLE_MESSAGE = "handleMessage";

	/**
	 * 声明对列，不进行持久化
	 * @author  zhoujialin
	 * @return
	 */
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * 声明主题类型的交换机
     * @author  zhoujialin
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * 将队列绑定到交换机，指定routingKey
     * @author  zhoujialin
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * 简单的消息监听器容器
     * @author  zhoujialin
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    
    /**
     * 消息监听适配器
     * @author  zhoujialin
     * @param listener
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MqListener listener) {
        return new MessageListenerAdapter(listener, HANDLE_MESSAGE);
    }
}
