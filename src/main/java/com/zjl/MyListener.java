package com.zjl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.zjl.listener.MqListener;

/**
 * 
 * 描述:消息监听实现类
 * @author  zhoujialin
 * @created 2018年10月10日 上午11:28:47
 * @since   v1.0.0
 */
@Component
public class MyListener implements MqListener{
	
	private static final Logger logger = LoggerFactory.getLogger(MyListener.class);
	
	/**
	 * 消息处理方法
	 * @author  zhoujialin
	 * @param message 消息体
	 */
	@Override
	public void handleMessage(Object message) {
		//打印消息
		logger.info("Received <{}>", (String)message);
	}

}
