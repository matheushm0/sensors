package client.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

public class UpdateReadingPopup extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private List<String> subscribedTopics;
	
	private JPanel topicPanel;
	private JScrollPane topicsScrollPane;
	
	private JTextArea logArea;
	private JScrollPane logScroll;
	
	public UpdateReadingPopup(List<String> subscribedTopics) {
		this.subscribedTopics = subscribedTopics;
		
		initComponents();
		setUpGUI();
	}

	private void initComponents() {
		this.topicPanel = new JPanel();
		this.topicsScrollPane = new JScrollPane();
		
		this.logArea = new JTextArea();
		this.logScroll = new JScrollPane();
	}
	
	private void setUpGUI() {
		this.setResizable(false);
		this.setSize(800, 500);
		this.setTitle("Monitor de Sensores");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new JLabel());	
		
		JLabel topicLabel = new JLabel("Sensores Monitorados");
		topicLabel.setFont(new Font("Arial", Font.BOLD, 14));
		topicLabel.setBounds(50, 0, 200, 60);
		
		topicPanel.setBackground(Color.WHITE);
		topicPanel.setLayout(new BoxLayout(topicPanel, BoxLayout.Y_AXIS));
		
		topicsScrollPane.setViewportView(topicPanel);
		topicsScrollPane.setBounds(15, 40, 230, 400);
		
		for (String subscribedTopic : subscribedTopics) {
			JLabel topic = new JLabel(subscribedTopic);
			topic.setFont(new Font("Arial", Font.PLAIN, 12));	
			topic.setAlignmentX(Component.CENTER_ALIGNMENT);
			topic.setBorder(new EmptyBorder(5,0,0,0));

			
			topicPanel.add(topic);
		}
		
		JLabel logLabel = new JLabel("Log dos Sensores");
		logLabel.setFont(new Font("Arial", Font.BOLD, 14));
		logLabel.setBounds(450, 0, 200, 60);
		
		logArea.setEditable(false);
		logArea.setColumns(20);
		logArea.setRows(5);		
		logArea.setWrapStyleWord(true);
		logArea.setLineWrap(true);
		logArea.setFont(logArea.getFont().deriveFont(12f));
		logArea.setMargin(new Insets(10, 10, 10, 10));
		
		DefaultCaret caret = (DefaultCaret) logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		logScroll.setViewportView(logArea);
		logScroll.setBounds(265, 40, 500, 400);
		
		this.add(topicLabel);
		this.add(topicsScrollPane);
		this.add(logLabel);
		this.add(logScroll);
		this.setVisible(true);
	}
	
	
	public void receiveMessage(String message) {
		logArea.append(message + "\n");
		
		try {
			logArea.setCaretPosition(logArea.getLineStartOffset(logArea.getLineCount() - 1));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
