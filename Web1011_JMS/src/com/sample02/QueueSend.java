//Queue傳送者
package com.sample02;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class QueueSend {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionfactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		//建立與JMS服務的連接:ConnectionFactory被管理的物件，由用戶端建立，用來建立一個連線物件
		Connection connection = connectionfactory.createConnection();//獲取連接，connection一個到JMS系統提供者的活動連接
		Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE );//打開會話，一個單獨的發送和接受消息的執行緒上下文
	Queue queue = new ActiveMQQueue("queue.msgText");
		MessageProducer msgProducer = session.createProducer(queue);
		Message msg = session.createTextMessage("使用jms發送文本消息");
		msgProducer.send(msg);
		System.out.println("文本消息已發送");
                session.close();
		connection.close();

    }
}
