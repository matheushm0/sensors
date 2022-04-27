package client;

import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import client.ui.UpdateReadingPopup;

public class Subscriber implements MessageListener {

 	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
 	
 	private UpdateReadingPopup updateReadingPopup;

	public Subscriber(List<String> subscribedTopics) {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			for (String topic : subscribedTopics) {
				Destination dest = session.createTopic(topic);
				MessageConsumer subscriber = session.createConsumer(dest);
				subscriber.setMessageListener(this);	
			}
			
			this.updateReadingPopup = new UpdateReadingPopup(subscribedTopics);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				updateReadingPopup.receiveMessage(((TextMessage) message).getText());
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
		
	}
}
