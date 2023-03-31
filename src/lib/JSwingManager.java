package lib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JSwingManager {
	//Variables
	private JFrame frame;
	private JPanel panel; 
	private DefaultTableModel oModel;
	private JTable table;
	
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
        
        //Create oModel to Table
        oModel = new DefaultTableModel();
        //Create and bind table
        table = new JTable();
        table.setModel(oModel);
        
        //Add panel and table to Frame
        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.getContentPane().add(BorderLayout.CENTER, table);
	}
	
	//Add button to left panel
	public void addButton (String text, Method method) {
		//Create button
		JButton button = new JButton(text);
		//Add action listener
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		      method.action();
		   }
		});
		//Add button to panel
		panel.add(button);
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
	public void addRows(Object[] values) {
		for (Object e : values) {
			addRow((Object[])e);
		}
	}
	public void addRow (Object[] values) {
		oModel.addRow(values);
	}
	
	//Show dialog
	public void open () {
		frame.setVisible(true);
	}
}
