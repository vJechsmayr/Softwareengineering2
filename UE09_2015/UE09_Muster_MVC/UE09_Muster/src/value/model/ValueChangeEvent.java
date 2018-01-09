package value.model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class ValueChangeEvent extends EventObject {
	
	private final int value;

	public ValueChangeEvent(ValueModel source, int value) {
		super(source);
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
