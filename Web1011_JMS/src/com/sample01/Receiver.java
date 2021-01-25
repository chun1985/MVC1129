
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
		  // ConnectionFactory ：連接工廠，JMS 用它創建連接  
        ConnectionFactory connectionFactory;  
        // Connection ：JMS 用戶端到JMS Provider 的連接  
        Connection connection = null;  
        // Session： 一個發送或接收消息的執行緒  
        Session session;  
        // Destination ：消息的目的地;消息發送給誰.  
        Destination destination;  
        // 消費者，消息接收者  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        try {  
            // 構造從工廠得到連線物件  
            connection = connectionFactory.createConnection();  
            // 啟動  
            connection.start();  
            // 獲取操作連接  
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
            // 獲取session注意參數值xingbo.xu-queue是一個伺服器的queue，須在在ActiveMq的console配置  
            destination = session.createQueue("FirstQueue");
            //destination = session.createTopic("Second");  
            consumer = session.createConsumer(destination);  
            while (true) {  
                // 設置接收者接收消息的時間，為了便於測試，這裡設定為100s  
                TextMessage message = (TextMessage) consumer.receive(100000);  
                if (null != message) {  
                    System.out.println("收到消息：" + message.getText());  
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
