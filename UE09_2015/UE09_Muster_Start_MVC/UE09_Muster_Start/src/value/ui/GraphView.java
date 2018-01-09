package value.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import value.model.ValueChangedEvent;
import value.model.ValueChangedListener;
import value.model.ValueModel;

public class GraphView extends JPanel {
	
	private final ValueModel model; 
	
	GraphView(ValueModel model) {
		setPreferredSize(new Dimension(40, 100)); 
		this.model = model; 
		this.model.addValueChangedListener(new ValueChangedListener() {
			
			@Override
			public void valueChanged(ValueChangedEvent evt) {
				repaint(); 
			}
		}); 
		
		this.addMouseListener(mouseHandler); 
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(20, 0, 20, this.getHeight()); 
		g.drawLine(17, getHeight()/2, 23, getHeight()/2); 
		g.drawString("0", 25, getHeight()/2);
		int y = ((- model.getValue()) * 5) + (getHeight() / 2); 
		g.drawLine(15, y, 25, y); 
	}
	
	private MouseAdapter mouseHandler = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			int y = e.getY(); 
			int value = getValueFromY(y); 
			model.setValue(value); 
		}	
		
	}; 

	private int getValueFromY(int y) {
		y =  y - (getHeight() / 2); 
		return - (y / 5);
	}

}
