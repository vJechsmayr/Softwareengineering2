package value.model;

import java.util.ArrayList;
import java.util.List;

public class ValueModel {
	
	private int value;
	private List<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		fireChangeEvent();
	}

	public void incr() {
		this.value++;
		fireChangeEvent();
	}

	public void decr() {
		this.value--;
		fireChangeEvent();
	}

	public void reset() {
		this.value = 0;
		fireChangeEvent();
	}

	public void addValueChangeListener(ValueChangeListener listener) {
		listeners.add(listener);
	}

	public void removeValueChangeListener(ValueChangeListener listener) {
		listeners.remove(listener);
	}

	protected void fireChangeEvent() {
		ValueChangeEvent evt = new ValueChangeEvent(this, getValue());
		for (ValueChangeListener l : listeners) {
			l.valueChanged(evt);
		}
	}
	
}
