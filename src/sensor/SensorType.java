package sensor;

public enum SensorType {
    TEMPERATURA("TEMPERATURA", "°C"),
    UMIDADE("UMIDADE", "%"),
    VELOCIDADE("VELOCIDADE", "km/h");
	
	private String type;
	private String unit;
	
	SensorType(String type, String unit) {
		this.type = type;
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public String getUnit() {
		return unit;
	}
}

