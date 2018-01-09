package value.model;

import java.util.EventListener;

// Event:  valueChanged
public interface ValueChangedListener extends EventListener {
	
	public void valueChanged(ValueChangedEvent evt); 

}
