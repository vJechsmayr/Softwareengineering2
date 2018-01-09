package value.model;

import javax.swing.event.EventListenerList;

public class ValueModel {
	
	private int value;
	private EventListenerList listeners; 
	
	public ValueModel() {
		listeners = new EventListenerList(); 
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		int previous = this.value; 
		this.value = value;
		fireValueChanged(previous, this.value); 
	}

	public void incr() {
		int previous = this.value; 
		this.value++;
		fireValueChanged(previous, this.value); 
	}

	public void decr() {
		int previous = this.value; 
		this.value--;
		fireValueChanged(previous, this.value); 
	}

	public void reset() {
		int previous = this.value; 
		this.value = 0;
		fireValueChanged(previous, this.value); 
	}
	
	public void addValueChangedListener(ValueChangedListener l) {
		listeners.add(ValueChangedListener.class, l); 
	}

	public void removeValueChangedListener(ValueChangedListener l) {
		listeners.remove(ValueChangedListener.class, l); 
	}

	private void fireValueChanged(int previous, int value) {
		ValueChangedEvent evt = new ValueChangedEvent(this, previous, value); 
		for (ValueChangedListener l : listeners.getListeners(ValueChangedListener.class)) {
			l.valueChanged(evt); 
		}
		
		
	}

}
