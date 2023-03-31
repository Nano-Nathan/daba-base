package TP2;

import lib.JSwingManager;

public class Main {
	public static void main(String[] args) {
		JSwingManager window = new JSwingManager();
		window.addButton("agregar columnas", () -> {
			window.addColumns("test", "test2", "test3");
		});
		window.addButton("agregar filas", () -> {
			window.addRow(new Object[] {"value 1", "value 2", "value 3"});
		});
		window.addButton("eliminar columna", () -> {
			window.resetTable();
		});
		//window.addButton("test1");
		//window.addButton("tes2");
		window.open();
	}
}