//Queue�ǰe��
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
		//�إ߻PJMS�A�Ȫ��s��:ConnectionFactory�Q�޲z������A�ѥΤ�ݫإߡA�Ψӫإߤ@�ӳs�u����
		Connection connection = connectionfactory.createConnection();//����s���Aconnection�@�Ө�JMS�t�δ��Ѫ̪����ʳs��
		Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE );//���}�|�ܡA�@�ӳ�W���o�e�M����������������W�U��
	Queue queue = new ActiveMQQueue("queue.msgText");
		MessageProducer msgProducer = session.createProducer(queue);
		Message msg = session.createTextMessage("�ϥ�jms�o�e�奻����");
		msgProducer.send(msg);
		System.out.println("�奻�����w�o�e");
                session.close();
		connection.close();

    }
}
