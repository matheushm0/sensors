package client;

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

public class Subscriber implements MessageListener {

 	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
 	public static void main(String[] args) {
		new Subscriber().initialize();
	}
 	
	public void initialize() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination dest = session.createTopic("TOPICO_TESTE");
			MessageConsumer subscriber = session.createConsumer(dest);
			subscriber.setMessageListener(this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				System.out.println(((TextMessage) message).getText());
			} catch (Exception e) {
				e.printStackTrace();			
			}
		}
		
	}
}
