package sensor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public void sendMessage(String message) {
		
	}
	
	public void initialize(Sensor sensor) {
		try {
			String topic = sensor.getType() + "_" + sensor.getId();
			
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination dest = session.createTopic(topic);
			
			MessageProducer publisher = session.createProducer(dest);
			
//			while (true) {
//				TextMessage message = session.createTextMessage();
//				Scanner input = new Scanner(System.in);
//				message.setText(input.nextLine());
//				publisher.send(message);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
