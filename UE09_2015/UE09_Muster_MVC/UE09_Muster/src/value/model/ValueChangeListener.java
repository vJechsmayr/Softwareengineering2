package value.model;

import java.util.EventListener;

public interface ValueChangeListener extends EventListener {
	
	public void valueChanged(ValueChangeEvent evt);
	
}
