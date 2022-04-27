package sensor.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import sensor.Publisher;
import sensor.Sensor;

public class UpdateSensorPopup extends JFrame {

	private static final long serialVersionUID = 1L;

	private Sensor sensor;
	private Publisher publisher;

	private JTextField minValueTextField;
	private JTextField maxValueTextField;
	private JTextField updateSensorValueTextField;

	private JButton minValueButton;
	private JButton maxValueButton;
	private JButton updateSensorValueButton;

	private JLabel minValueLabel;
	private JLabel maxValueLabel;
	private JLabel sensorValueLabel;
	private JLabel sensorTypeLabel;
	
	private static final String UPDATE_VALUE = "UPDATE_VALUE";
	private static final String MIN_VALUE = "MIN_VALUE";
	private static final String MAX_VALUE = "MAX_VALUE";

	public UpdateSensorPopup(Sensor sensor) {
		this.publisher = new Publisher(sensor);

		this.sensor = sensor;

		initComponents();
		setUpGUI();
		
		setUpSensorValueButton();
		setUpMinValueButton();
		setUpMaxValueButton();
	}

	private void initComponents() {
		this.minValueTextField = new JTextField();
		this.maxValueTextField = new JTextField();
		this.updateSensorValueTextField = new JTextField();

		this.minValueButton = new JButton();
		this.maxValueButton = new JButton();
		this.updateSensorValueButton = new JButton();

		this.minValueLabel = new JLabel();
		this.maxValueLabel = new JLabel();
		this.sensorValueLabel = new JLabel();
		this.sensorTypeLabel = new JLabel();
	}

	private void setUpGUI() {
		this.setResizable(false);
		this.setSize(600, 380);
		this.setTitle("Sensor de " + sensor.getName());
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new JLabel());		
		
		sensorTypeLabel.setText("SENSOR DE " + sensor.getType());
		sensorTypeLabel.setFont(new Font("Arial", Font.BOLD, 18));
		sensorTypeLabel.setBounds(165, 0, 250, 60);
		
		JLabel valueLabel = new JLabel("Valor Atual:");
		valueLabel.setFont(new Font("Arial", Font.BOLD, 16));
		valueLabel.setBounds(80, 50, 150, 30);
		
		sensorValueLabel.setText("0 " + sensor.getType().getUnit());
		sensorValueLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		sensorValueLabel.setBounds(200, 50, 150, 30);
		
		JLabel valueLabel1 = new JLabel("Valor Mínimo:");
		valueLabel1.setFont(new Font("Arial", Font.BOLD, 16));
		valueLabel1.setBounds(80, 80, 150, 30);
		
		minValueLabel.setText(String.valueOf(sensor.getMinValue()) + " " + sensor.getType().getUnit());
		minValueLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		minValueLabel.setBounds(200, 80, 150, 30);
		
		JLabel valueLabel2 = new JLabel("Valor Máximo:");
		valueLabel2.setFont(new Font("Arial", Font.BOLD, 16));
		valueLabel2.setBounds(80, 110, 150, 30);
		
		maxValueLabel.setText(String.valueOf(sensor.getMaxValue()) + " " + sensor.getType().getUnit());
		maxValueLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		maxValueLabel.setBounds(200, 110, 150, 30);
		
		JLabel buttonLabel = new JLabel ("Novo Valor Atual:");
		buttonLabel.setFont(new Font("Arial", Font.BOLD, 16));
		buttonLabel.setBounds(80, 165, 150, 30);
		
		updateSensorValueTextField.setBounds(250, 168, 150, 25);
		
		updateSensorValueButton.setText("Atualizar");
		updateSensorValueButton.setBounds(410, 168, 90, 24);
		
		JLabel buttonLabel1 = new JLabel ("Novo Valor Mínimo:");
		buttonLabel1.setFont(new Font("Arial", Font.BOLD, 16));
		buttonLabel1.setBounds(80, 200, 180, 30);
		
		minValueTextField.setBounds(250, 203, 150, 25);
		
		minValueButton.setText("Atualizar");
		minValueButton.setBounds(410, 203, 90, 24);
		
		JLabel buttonLabel2 = new JLabel ("Novo Valor Máximo:");
		buttonLabel2.setFont(new Font("Arial", Font.BOLD, 16));
		buttonLabel2.setBounds(80, 235, 180, 30);
		
		maxValueTextField.setBounds(250, 238, 150, 25);
		
		maxValueButton.setText("Atualizar");
		maxValueButton.setBounds(410, 238, 90, 24);
		
		this.add(valueLabel);
		this.add(valueLabel1);
		this.add(valueLabel2);
		
		this.add(buttonLabel);
		this.add(buttonLabel1);
		this.add(buttonLabel2);
		
		this.add(sensorTypeLabel);
		this.add(sensorValueLabel);
		this.add(minValueLabel);
		this.add(maxValueLabel);
		
		this.add(updateSensorValueTextField);
		this.add(minValueTextField);
		this.add(maxValueTextField);
		
		this.add(updateSensorValueButton);
		this.add(minValueButton);
		this.add(maxValueButton);
		
		this.setVisible(true);
	}
	
	private void setUpSensorValueButton() {
		ActionListener actionListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) {
				sendMessage(UPDATE_VALUE);
			}
		};
		
		KeyListener keyListener = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage(UPDATE_VALUE);
				}
			}
		};
		
		updateSensorValueButton.addActionListener(actionListener);
		updateSensorValueTextField.addKeyListener(keyListener);
	}
	
	private void setUpMinValueButton() {
		ActionListener actionListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) {
				sendMessage(MIN_VALUE);
			}
		};
		
		KeyListener keyListener = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage(MIN_VALUE);
				}
			}
		};
		
		minValueButton.addActionListener(actionListener);
		minValueTextField.addKeyListener(keyListener);
	}
	
	private void setUpMaxValueButton() {
		ActionListener actionListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) {
				sendMessage(MAX_VALUE);
			}
		};
		
		KeyListener keyListener = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage(MAX_VALUE);
				}
			}
		};
		
		maxValueButton.addActionListener(actionListener);
		maxValueTextField.addKeyListener(keyListener);
	}
	
	private void sendMessage(String buttonType) {

		switch (buttonType) {
			case UPDATE_VALUE:
				
				if (!validateField(updateSensorValueTextField.getText(), buttonType)) {
					updateSensorValueTextField.setText("");
					return;
				}
				
				int updateValue = Integer.parseInt(updateSensorValueTextField.getText());
				
				updateSensorValueTextField.setText("");
				sensorValueLabel.setText(String.valueOf(updateValue) + " " + sensor.getType().getUnit());
				sensor.setActualValue(updateValue);
				
				publisher.sendMessage(sensor.getName() + ": O valor medido pelo sensor foi alterado para " 
							+ updateValue + " " + sensor.getType().getUnit());
				
				if (updateValue < sensor.getMinValue()) {
					publisher.sendMessage(sensor.getName() + ": O valor atual é menor que o valor mínimo! "
							+ "Valor atual: " + updateValue + " " + sensor.getType().getUnit() + " "
							+ "Valor mínimo: " + sensor.getMinValue() + " " + sensor.getType().getUnit());
				}
				
				if (updateValue > sensor.getMaxValue()) {
					publisher.sendMessage(sensor.getName() + ": O valor atual é maior que o valor máximo! "
							+ "Valor atual: " + updateValue + " " + sensor.getType().getUnit() + " "
							+ "Valor máximo: " + sensor.getMaxValue() + " " + sensor.getType().getUnit());
				}
				
				break;
	
			case MIN_VALUE:
				
				if (!validateField(minValueTextField.getText(), buttonType)) {
					minValueTextField.setText("");
					return;
				}
				
				int minValue = Integer.parseInt(minValueTextField.getText());
				
				minValueTextField.setText("");
				minValueLabel.setText(String.valueOf(minValue) + " " + sensor.getType().getUnit());
				sensor.setMinValue(minValue);
				
				publisher.sendMessage(sensor.getName() + ": O valor mínimo permitido pelo sensor foi alterado para " 
						+ minValue + " " + sensor.getType().getUnit());
			
				if (minValue > sensor.getActualValue()) {
					publisher.sendMessage(sensor.getName() + ": O valor mínimo é maior que o valor atual! "
							+ "Valor mínimo: " + minValue + " " + sensor.getType().getUnit() + " "
							+ "Valor atual: " + sensor.getActualValue() + " " + sensor.getType().getUnit());
				}
				
				if (minValue > sensor.getMaxValue()) {
					publisher.sendMessage(sensor.getName() + ": O valor mínimo é maior que o valor máximo! "
							+ "Valor mínimo: " + minValue + " " + sensor.getType().getUnit() + " "
							+ "Valor máximo: " + sensor.getMaxValue() + " " + sensor.getType().getUnit());
				}				
				
				break;
	
			case MAX_VALUE:
				
				if (!validateField(maxValueTextField.getText(), buttonType)) {
					maxValueTextField.setText("");
					return;
				}

				int maxValue = Integer.parseInt(maxValueTextField.getText());
				
				maxValueTextField.setText("");
				maxValueLabel.setText(String.valueOf(maxValue) + " " + sensor.getType().getUnit());
				sensor.setMaxValue(maxValue);
				
				publisher.sendMessage(sensor.getName() + ": O valor máximo permitido pelo sensor foi alterado para " 
						+ maxValue + " " + sensor.getType().getUnit());
			
				if (maxValue < sensor.getMinValue()) {
					publisher.sendMessage(sensor.getName() + ": O valor máximo é menor que o valor mínimo! "
							+ "Valor máximo: " + maxValue + " " + sensor.getType().getUnit() + " "
							+ "Valor mínimo: " + sensor.getMinValue() + " " + sensor.getType().getUnit());
				}
				
				if (maxValue < sensor.getActualValue()) {
					publisher.sendMessage(sensor.getName() + ": O valor máximo é menor que o valor atual! "
							+ "Valor máximo: " + maxValue + " " + sensor.getType().getUnit() + " "
							+ "Valor atual: " + sensor.getActualValue() + " " + sensor.getType().getUnit());
				}
				
				break;
	
			default:
				break;
		}
	}
	
	public boolean validateField(String value, String buttonType) {
		boolean isValid = true;
		
		try {
			Integer.parseInt(value);
			
			if (buttonType.equalsIgnoreCase(UPDATE_VALUE)) {
				updateSensorValueTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			}
			
			if (buttonType.equalsIgnoreCase(MIN_VALUE)) {
				minValueTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			}
			
			if (buttonType.equalsIgnoreCase(MAX_VALUE)) {
				maxValueTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			}
		}
		catch (Exception e) {
			isValid = false;
			
			if (buttonType.equalsIgnoreCase(UPDATE_VALUE)) {
				updateSensorValueTextField.setBorder(new LineBorder(Color.RED,1));
			}
			
			if (buttonType.equalsIgnoreCase(MIN_VALUE)) {
				minValueTextField.setBorder(new LineBorder(Color.RED,1));
			}
			
			if (buttonType.equalsIgnoreCase(MAX_VALUE)) {
				maxValueTextField.setBorder(new LineBorder(Color.RED,1));
			}
		}
		
		return isValid;
	}
}
