
package com.sample01;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

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
        // MessageProducer�G�����o�e��  
        MessageProducer producer;  
        // TextMessage message;  
        // �c�yConnectionFactory��Ҫ���A���B�ĥ�ActiveMq����{jar  
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        try {  
            // �c�y�q�u�t�o��s�u����  
            connection = connectionFactory.createConnection();  
            // �Ұ�  
            connection.start();  
            // ����ާ@�s��  
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
            // ���session�`�N�Ѽƭ�xingbo.xu-queue�O�@�Ӧ��A����queue�A���b�bActiveMq��console�t�m  
            destination = session.createQueue("FirstQueue"); 
            
            //Destination  destination1=session.createTopic("Second");
            //MessageProducer producer1 = session.createProducer(destination1);
            //TextMessage message1 = session.createTextMessage("�ڷQ�o�e���h�ӤH������");
            //producer1.send(message1);
            
            // �o������ͦ��̡i�o�e�̡j  
            producer = session.createProducer(destination);  
            //�Ыػݭn�o�G������
            TextMessage message = session.createTextMessage("ActiveMq �o�e������");            
            // �]�m�����[�ơA���B�ǲߡA��ڮھڱM�רM�w  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            //�o�e����
            producer.send(message);
            session.commit();  
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
