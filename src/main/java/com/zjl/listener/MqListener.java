package com.zjl.listener;

/**
 * 
 * 描述:消息监听接口
 * @author  zhoujialin
 * @created 2018年10月10日 上午11:32:38
 * @since   v1.0.0
 */
public interface MqListener {
	/**
	 * 消息处理方法
	 * @author  zhoujialin
	 * @param message
	 */
	public void handleMessage(Object message);
	
}
