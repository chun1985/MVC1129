//Queue������
package com.sample02;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueuesAccept {
	public static void main(String[] args) throws JMSException {
		String jmsProviderAddress = "tcp://localhost:61616";// �a�}
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jmsProviderAddress);
		Connection conn = connectionFactory.createConnection();
		System.out.println("createConnection" );
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		System.out.println("createSession" );
		String destinationName = "queue.msgText";
		Destination dest = session.createQueue(destinationName);
		System.out.println("createQueue" );
		MessageConsumer consumer = session.createConsumer(dest);
		System.out.println("createConsumer" );
		conn.start();
		System.out.println("conn.start()" );
		TextMessage textMessage = (TextMessage) consumer.receive();//����ʱ���JMS�����A�o��i�H�κ�ť
		System.out.println("consumer.receive()" );
		String text = textMessage.getText();
		System.out.println("�o�e���e���G" + text);
		consumer.close();
		session.close();
		conn.close();
	}
}
