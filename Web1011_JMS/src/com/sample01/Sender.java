
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
		// ConnectionFactory ：連接工廠，JMS 用它創建連接  
        ConnectionFactory connectionFactory;  
        // Connection ：JMS 用戶端到JMS Provider 的連接  
        Connection connection = null;  
        // Session： 一個發送或接收消息的執行緒  
        Session session;  
        // Destination ：消息的目的地;消息發送給誰.  
        Destination destination;  
        // MessageProducer：消息發送者  
        MessageProducer producer;  
        // TextMessage message;  
        // 構造ConnectionFactory實例物件，此處採用ActiveMq的實現jar  
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        try {  
            // 構造從工廠得到連線物件  
            connection = connectionFactory.createConnection();  
            // 啟動  
            connection.start();  
            // 獲取操作連接  
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
            // 獲取session注意參數值xingbo.xu-queue是一個伺服器的queue，須在在ActiveMq的console配置  
            destination = session.createQueue("FirstQueue"); 
            
            //Destination  destination1=session.createTopic("Second");
            //MessageProducer producer1 = session.createProducer(destination1);
            //TextMessage message1 = session.createTextMessage("我想發送給多個人的消息");
            //producer1.send(message1);
            
            // 得到消息生成者【發送者】  
            producer = session.createProducer(destination);  
            //創建需要發佈的消息
            TextMessage message = session.createTextMessage("ActiveMq 發送的消息");            
            // 設置不持久化，此處學習，實際根據專案決定  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            //發送消息
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
