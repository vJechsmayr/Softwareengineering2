package value.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import value.model.ValueModel;

/*
 * Diese Frame-Implementierung zeigt:
 * 1) Aufbau und Anzeigen eines Frame
 * 2) Verwendung von defaultClosingOperation()
 * 3) Menus
 * 4) Aufbau einer GUI aus Hierarchie von Komponenten
 * 5) Definition und Verwendung eines Model
 * 6) Implementierung der ActionListener
 */
public class ValueApp {
	
	// 1) Deklaration des Frame
	private JFrame frame;
	
	// 5) Definition eines Model
	private ValueModel model;

	// 4) Deklaration von Komponenten
	private JLabel valueLabel;
	private JButton incrButton, decrButton, resetButton;
	private JTextField newValueField;
	private DefaultListModel<Integer> historyListModel;
	private JList<Integer> historyList;

	public void start() {
		// 1) Erzeugen des Frame
		frame = new JFrame("Value");

		// 5) Definition eines Model
		model = new ValueModel();

		// 2) Verwendung von defaultClosingOperation()
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// 3) Menus
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem exitMenu = new JMenuItem("Exit");
		fileMenu.add(exitMenu);
		frame.setJMenuBar(menuBar);

		// 4) Aufbau einer GUI aus Hierarchie von Komponenten
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		incrButton = new JButton("Incr");
		topPanel.add(incrButton);
		decrButton = new JButton("Decr");
		topPanel.add(decrButton);
		resetButton = new JButton("Reset");
		topPanel.add(resetButton);

		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBorder(BorderFactory.createTitledBorder("Value"));
		bottomPanel.setLayout(new GridLayout(2, 2, 5, 5));
		bottomPanel.add(new JLabel("Value: "));
		valueLabel = new JLabel();
		valueLabel.setBorder(BorderFactory.createEtchedBorder());
		bottomPanel.add(valueLabel);
		bottomPanel.add(new JLabel("New value: "));
		newValueField = new JTextField(10);
		bottomPanel.add(newValueField);

		JPanel historyPanel = new JPanel();
		contentPane.add(historyPanel, BorderLayout.CENTER);
		historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
		historyPanel.setLayout(new BorderLayout());
		historyListModel = new DefaultListModel<Integer>();
		historyList = new JList<Integer>(historyListModel);
		historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyPanel.add(new JScrollPane(historyList), BorderLayout.CENTER);

		// 6) Implementierung der ActionListener
		exitMenu.addActionListener(exitHandler);
		incrButton.addActionListener(incrHandler);
		decrButton.addActionListener(decrHandler);
		resetButton.addActionListener(resetHandler);
		newValueField.addActionListener(newValueHandler);
		historyList.getSelectionModel().addListSelectionListener(listSelectionHandler); 

		// view initialisieren
		updateViews(model.getValue());
		
		// 1) Anzeigen des Frame
		frame.setLocation(100, 100);
		// frame.setSize(400, 280);
		frame.pack();
		frame.setVisible(true);
	}

	// 6) Implementierung der ActionListener
	private ActionListener exitHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	};

	private ActionListener incrHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.incr();
			updateViews(model.getValue()); 
		}

	};

	private ActionListener decrHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.decr();
			updateViews(model.getValue()); 
		}
	};

	private ActionListener resetHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			historyListModel.clear(); 
			model.reset();
			updateViews(model.getValue()); 
		}
	};

	private ActionListener newValueHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int val = Integer.parseInt(newValueField.getText());
				model.setValue(val);
				updateViews(model.getValue()); 
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Wrong format for new value",
						frame.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	private ListSelectionListener listSelectionHandler = new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int i = historyList.getSelectedIndex(); 
			if (i >= 0 && i < historyListModel.size() - 1) {
				int value = historyListModel.get(i); 
				historyListModel.removeRange(i, historyListModel.size() - 1); 
				model.setValue(value); 
				updateViews(value); 
			}
		}
	};

	private void updateViews(int value) {
		valueLabel.setText(String.valueOf(value)); 
		newValueField.setText(String.valueOf(value)); 
		historyListModel.addElement(value); 
	}

	// main
	
	public static void main(String[] args) {
		ValueApp app = new ValueApp();
		app.start();
	}
}
