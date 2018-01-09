package value.model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class ValueChangedEvent extends EventObject {
	
	private final int previous; 
	private final int value; 

	public ValueChangedEvent(Object source, int previous, int value) {
		super(source);
		this.previous = previous; 
		this.value = value; 
	}

	public int getPrevious() {
		return previous;
	}

	public int getValue() {
		return value;
	}

}
