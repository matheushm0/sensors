package sensor;

public class Sensor {

	private SensorType type;
	private String id;
	private int minValue;
	private int maxValue;

	public Sensor(String id, SensorType type, int minValue, int maxValue) {
		this.id = id;
		this.type = type;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

}
