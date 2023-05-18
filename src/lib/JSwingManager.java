package lib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JSwingManager {
	//Variables
	private JFrame frame;
	private JPanel panel; 
	private JPanel panelOptions;
	private JPanel secondPanel;
	private DefaultTableModel oModel;
	private JTable table;
	private ArrayList<JCheckBox> checkBoxs;
	private ArrayList<JTextArea> textAreas;
	
	//Constructor
	public JSwingManager() {
		frame = new JFrame("Nueva ventana");
		initFrame();
	}
	public JSwingManager(String title) {
		frame = new JFrame(title);
		initFrame();
	}
	private void initFrame() {
		//Setting frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(900, 600);
        
        //Create panel for buttons
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Create second panel
        secondPanel = new JPanel();
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));

        //Create panel for options
        panelOptions = new JPanel();
        panelOptions.setLayout(new BoxLayout(panelOptions, BoxLayout.X_AXIS));
        
        //Create oModel to Table
        oModel = new DefaultTableModel();
        //Create and bind table
        table = new JTable();
        table.setModel(oModel);
		JScrollPane scrollPane = new JScrollPane(table);

		//Init list of checkbox
		checkBoxs = new ArrayList<>();

		//Init list of text areas
		textAreas = new ArrayList<>();
        
        //Add menu bar, panel and table to Frame
        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.getContentPane().add(BorderLayout.EAST, secondPanel);
		frame.getContentPane().add(BorderLayout.NORTH, panelOptions);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
	}

	
	//Add checkbox to left panel
	public void addCheckBox (String text) {
		//Create checkbox
		JCheckBox checkBox = new JCheckBox(text);
		checkBox.setSelected(true);
		//Add checkbox to checkbox array
		checkBoxs.add(checkBox);
		//Add checkbox to panel
		panel.add(checkBox);
		panel.updateUI();
	}
	public ArrayList<String> getSelectedCheckBox () {
		ArrayList<String> result = new ArrayList<>();

		for (JCheckBox checkBox : checkBoxs) {
			if (checkBox.isSelected()){
				result.add(checkBox.getText());
			}
		}
		return result;
	}

	//Methods to manage Table
	public void addColumns (String... values) {
		for (String e : values) {
			oModel.addColumn(e);
		}
	}
	public void resetTable() {
		int columnCount = oModel.getColumnCount();
		for (int i = columnCount - 1; i >= 0; i--) {
		    table.removeColumn(table.getColumnModel().getColumn(i));
		}
		oModel.setDataVector(new Object[][]{}, new Object[]{});
		table.updateUI();
	}	
	public void addRows(List<Object[]> values) {
		for (Object[] e : values) {
			addRow(e);
		}
	}
	public void addRow (Object[] values) {
		oModel.addRow(values);
	}
	
	//Methods to manage Panels
	public void clearPanels() {
		panel.removeAll();
		secondPanel.removeAll();
		checkBoxs.clear();
		panel.updateUI();
		secondPanel.updateUI();
	}
	
	//Ad buttons
	private void addButtons (String text, Method method, String namePanel) {
		//Create button
		JButton button = new JButton(text);
		
		//Add action listener
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				method.action();
		   	}
		});

		//Add button to panel
		switch (namePanel) {
			case "left":
				panel.add(button);
				break;
			case "up":
				panelOptions.add(button);
				break;
			case "right":
				secondPanel.add(button);
				break;
			default:
				break;
		}
	}
	public void addButton (String text, Method method) {
		addButtons(text, method, "left");
	}
	public void addMenu(String text, Method method) {
		addButtons(text, method, "up");
	}
	public void addSecondButton(String text, Method method) {
		addButtons(text, method, "right");
	}
	
	//Add text areas
	private void addTextAreas (String label, int columns, int rows, String namePanel) {
		//Create label
		JLabel l = new JLabel(label);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		//Create text area
		JTextArea textArea = new JTextArea();
		textArea.setColumns(columns);
		textArea.setRows(rows);
		textArea.setName(label);
		textAreas.add(textArea);
		switch (namePanel) {
			case "left":
				panel.add(l);
				panel.add(textArea);
				break;
			case "right":
				secondPanel.add(l);
				secondPanel.add(textArea);
				break;
			default:
				break;
		}
	}
	public void addTextAreaLeft (String label, int columns, int rows) {
		addTextAreas(label, columns, rows, "left");
	}
	public void addTextAreaRight (String label, int columns, int rows) {
		addTextAreas(label, columns, rows, "right");
	}
	
	//Get text areas
	public ArrayList<String[]> getTextAreaLeft() {
		ArrayList<String[]> result = new ArrayList<>();

		for (JTextArea textArea : textAreas) {
			String[] item = {textArea.getName(), textArea.getText()};
			result.add(item);
		}

		return result;
	}

	//Show dialog
	public void open () {
		frame.setVisible(true);
	}
}
