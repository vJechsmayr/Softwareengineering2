package value.model;

public class ValueModel {
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void incr() {
		this.value++;
	}

	public void decr() {
		this.value--;
	}

	public void reset() {
		this.value = 0;
	}

}
