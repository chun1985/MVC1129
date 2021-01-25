
package com.sample01;

import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.Destination;  
import javax.jms.MessageConsumer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import org.apache.activemq.ActiveMQConnection;  
import org.apache.activemq.ActiveMQConnectionFactory;  

public class Receiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  // ConnectionFactory �G�s���u�t�AJMS �Υ��Ыسs��  
        ConnectionFactory connectionFactory;  
        // Connection �GJMS �Τ�ݨ�JMS Provider ���s��  
        Connection connection = null;  
        // Session�G �@�ӵo�e�α��������������  
        Session session;  
        // Destination �G�������ت��a;�����o�e����.  
        Destination destination;  
        // ���O�̡A����������  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        try {  
            // �c�y�q�u�t�o��s�u����  
            connection = connectionFactory.createConnection();  
            // �Ұ�  
            connection.start();  
            // ����ާ@�s��  
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
            // ���session�`�N�Ѽƭ�xingbo.xu-queue�O�@�Ӧ��A����queue�A���b�bActiveMq��console�t�m  
            destination = session.createQueue("FirstQueue");
            //destination = session.createTopic("Second");  
            consumer = session.createConsumer(destination);  
            while (true) {  
                // �]�m�����̱����������ɶ��A���F�K����աA�o�̳]�w��100s  
                TextMessage message = (TextMessage) consumer.receive(100000);  
                if (null != message) {  
                    System.out.println("��������G" + message.getText());  
                } else {  
                    break;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != connection)  
                    connection.close();  
            } catch (Throwable ignore) {  
            }  
        }  
	}
}
