package client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQTopic;

import client.Subscriber;

public class NewReadingPopup extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    
	//CONNECTION
	private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private ConnectionFactory connectionFactory;
	private DestinationSource destination;
	private Connection connection;
	
	//UI
	private JPanel topicList;
	private JScrollPane topicsScrollPane;
	private List<JCheckBox> topicsCheckBox;
	private JButton confirmButton;
	private JButton updateButton;
	private JLabel errorLabel;

	public NewReadingPopup() {
		initComponents();
		setUpGUI();
		
		setUpUpdateButton();
		
		connectToServer();
	}
	
	private void initComponents() {
		this.topicList = new JPanel();
		this.topicsScrollPane = new JScrollPane();
				
		this.updateButton = new JButton();
		this.confirmButton = new JButton();
		
		this.errorLabel = new JLabel();
	}
	
	private void setUpGUI() {
		this.setResizable(false);
		this.setSize(400, 500);
		this.setTitle("Monitor de Sensores");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new JLabel());	
		
		JLabel sensorLabel = new JLabel("Lista de sensores");
		sensorLabel.setFont(new Font("Arial", Font.BOLD, 14));
		sensorLabel.setBounds(130, 0, 200, 60);

		topicList.setBackground(Color.WHITE);
		topicList.setLayout(new BoxLayout(topicList, BoxLayout.Y_AXIS));
		
		topicsScrollPane.setViewportView(topicList);
		topicsScrollPane.setBounds(40, 50, 310, 300);
		
		updateButton.setText("Atualizar");
		updateButton.setBounds(80, 380, 100, 30);
		
		confirmButton.setText("Confirmar");
		confirmButton.setBounds(200, 380, 100, 30);
		confirmButton.addActionListener(this);
		
		this.add(sensorLabel);
		this.add(topicsScrollPane);
		this.add(updateButton);
		this.add(confirmButton);
		this.setVisible(true);
	}
	
	private void setUpUpdateButton() {
		ActionListener al = new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) {
				retrieveAvailableTopics(destination);
			}
		};
		
		updateButton.addActionListener(al);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		List<String> subscribedTopics = new ArrayList<String>();

		for (JCheckBox topicCheckBox : topicsCheckBox) {
			if (topicCheckBox.isSelected()) {
				subscribedTopics.add(topicCheckBox.getText());
			}
		}

		if (!subscribedTopics.isEmpty()) {
			try {
				connection.close();
				destination.stop();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			this.setVisible(false);
			
			new Subscriber(subscribedTopics);
		} 
		else {
			errorLabel.setText("Você deve escolher pelo menos um sensor!");
			errorLabel.setForeground(Color.RED);
			errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			errorLabel.setBounds(70, 335, 300, 60);
			
			this.add(errorLabel);
			return;
		}

	}
	
	private void connectToServer() {
		connectionFactory = new ActiveMQConnectionFactory(url);

		try {
			connection = connectionFactory.createConnection();
			destination = new DestinationSource(connection);
			connection.start();
			destination.start();
			retrieveAvailableTopics(destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void retrieveAvailableTopics(DestinationSource destination) {
		 try {
			Set<ActiveMQTopic> topics = destination.getTopics();
			
			this.topicsCheckBox = new ArrayList<JCheckBox>();
			topicList.removeAll();
			
			for (ActiveMQTopic topic : topics) {
				JCheckBox checkbox = new JCheckBox(topic.getTopicName());
				checkbox.setFont(new Font("Arial", Font.PLAIN, 12));
				checkbox.setBackground(new Color(0,0,0,0));
				checkbox.setOpaque(false);
				
				topicsCheckBox.add(checkbox);
				topicList.add(checkbox);								
			}
			
			SwingUtilities.updateComponentTreeUI(topicList);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
