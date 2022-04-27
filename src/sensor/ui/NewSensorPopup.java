package sensor.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import sensor.Sensor;
import sensor.SensorType;

public class NewSensorPopup extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ButtonGroup sensorTypeGroup;
	
	private JRadioButton temperatureButton;
	private JRadioButton humidityButton;
	private JRadioButton speedButton;
	
	private JTextField idTextField;
	private JTextField minValueTextField;
	private JTextField maxValueTextField;
	
	private JButton submitButton;
	
	public NewSensorPopup() {
		initComponents();
		setUpGUI();
	}

	private void initComponents() {
		this.sensorTypeGroup = new ButtonGroup();
		
		this.temperatureButton = new JRadioButton();
		this.humidityButton = new JRadioButton();
		this.speedButton = new JRadioButton();
		
		this.idTextField = new JTextField();
		this.minValueTextField = new JTextField();
		this.maxValueTextField = new JTextField();
		
		this.submitButton = new JButton();
	}
	
	private void setUpGUI() {
		this.setResizable(false);
		this.setSize(300, 450);
		this.setTitle("Novo Sensor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new JLabel());		
		
		JLabel idLabel = new JLabel("Id do Sensor:");
		idLabel.setFont(new Font("Arial", Font.BOLD, 14));
		idLabel.setBounds(70, 0, 110, 60);
		
		idTextField.setBounds(70, 50, 150, 25);
		
		JLabel sensorLabel = new JLabel("Tipo de Sensor:");
		sensorLabel.setFont(new Font("Arial", Font.BOLD, 14));
		sensorLabel.setBounds(70, 65, 110, 60);
				
		temperatureButton.setText("Temperatura");
		temperatureButton.setFont(new Font("Arial", Font.PLAIN, 12));
		temperatureButton.setBounds(70, 115, 100, 20);
		temperatureButton.setBackground(new Color(0,0,0,0));
		temperatureButton.setOpaque(false);
		temperatureButton.setSelected(true);
		temperatureButton.setActionCommand("TEMPERATURA");
		sensorTypeGroup.add(temperatureButton);
		
		humidityButton.setText("Umidade");
		humidityButton.setFont(new Font("Arial", Font.PLAIN, 12));
		humidityButton.setBounds(70, 145, 100, 20);
		humidityButton.setBackground(new Color(0,0,0,0));
		humidityButton.setOpaque(false);
		humidityButton.setActionCommand("UMIDADE");
		sensorTypeGroup.add(humidityButton);
		
		speedButton.setText("Velocidade");
		speedButton.setFont(new Font("Arial", Font.PLAIN, 12));
		speedButton.setBounds(70, 175, 100, 20);
		speedButton.setBackground(new Color(0,0,0,0));
		speedButton.setOpaque(false);
		speedButton.setActionCommand("VELOCIDADE");
		sensorTypeGroup.add(speedButton);
		
		JLabel minValueLabel = new JLabel("Valor mínimo:");
		minValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
		minValueLabel.setBounds(70, 185, 100, 60);
		
		minValueTextField.setBounds(70, 235, 150, 25);
		minValueTextField.setText("0");
		
		JLabel maxValueLabel = new JLabel("Valor máximo:");
		maxValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
		maxValueLabel.setBounds(70, 250, 100, 60);
		
		maxValueTextField.setBounds(70, 300, 150, 25);
		maxValueTextField.setText("100");
	
		submitButton.setText("Confirmar");
		submitButton.setBounds(90, 350, 100, 30);
		submitButton.addActionListener(this);

		this.add(idLabel);
		this.add(idTextField);
		
		this.add(sensorLabel);
		this.add(temperatureButton);
		this.add(humidityButton);
		this.add(speedButton);
		
		this.add(minValueLabel);
		this.add(minValueTextField);
		
		this.add(maxValueLabel);
		this.add(maxValueTextField);
		
		this.add(submitButton);
		
		this.setVisible(true);
	}
	
	public boolean validateFields() {
		boolean isValid = true;
		
		if (idTextField.getText().isEmpty()) {
			isValid = false;
			idTextField.setBorder(new LineBorder(Color.RED,1));
		}
		else {
			idTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		}
		
		try {
			Integer.parseInt(minValueTextField.getText());
			minValueTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		}
		catch (Exception e) {
			isValid = false;
			minValueTextField.setBorder(new LineBorder(Color.RED,1));
		}
		
		try {
			Integer.parseInt(maxValueTextField.getText());
			maxValueTextField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		}
		catch (Exception e) {
			isValid = false;
			maxValueTextField.setBorder(new LineBorder(Color.RED,1));
		}
		
		return isValid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!validateFields()) {
			return;
		}

		SensorType sensorType = null;
		String typeStr = sensorTypeGroup.getSelection().getActionCommand();
		
		if (typeStr.equalsIgnoreCase("temperatura")) {
			sensorType = SensorType.TEMPERATURA;
		}
		
		if (typeStr.equalsIgnoreCase("umidade")) {
			sensorType = SensorType.UMIDADE;
		}
		
		if (typeStr.equalsIgnoreCase("velocidade")) {
			sensorType = SensorType.VELOCIDADE;
		}
		
		int minValue = Integer.parseInt(minValueTextField.getText());
		int maxValue = Integer.parseInt(maxValueTextField.getText());
		String id = idTextField.getText();
		
		Sensor sensor = new Sensor(id, sensorType, minValue, maxValue);
				
		this.setVisible(false);
		new UpdateSensorPopup(sensor);		
	}
}

