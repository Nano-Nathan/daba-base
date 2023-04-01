package TP2;

import java.util.List;
import lib.DBManager;
import lib.JSwingManager;

public class Main {
	public static void main(String[] args) {
		JSwingManager window = new JSwingManager();
		DBManager db = new DBManager();

		window.addButton("SELECT query", () -> {
			window.resetTable();
			List<Object[]> results = db.executeSELECT("SELECT * FROM schools WHERE id=42974117;");
			window.addColumns((String[])results.get(0));
			window.addRows(results);
		});

		window.addButton("Limpiar tabla", () -> {
			window.resetTable();
		});

		window.addButton("INSERT query", () -> {
			db.executeINSERT("INSERT INTO schools (id) VALUES (?);", 42974117);
		});

		window.open();
	}
}