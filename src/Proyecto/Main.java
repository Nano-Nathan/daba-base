package Proyecto;

import lib.JSwingManager;
//import lib.DBManager;

public class Main {
    public static void main(String[] args) {
        JSwingManager window = new JSwingManager();
		//DBManager db = new DBManager();
        
        window.addMenu("ACCIÓN", "SELECT", () -> {
            System.out.println("SELECT");
        });
        window.addMenu("ACCIÓN", "INSERT", () -> {
            System.out.println("INSERT");
        });
        window.addMenu("ACCIÓN", "UPDATE", () -> {
            System.out.println("UPDATE");
        });
        window.addMenu("ACCIÓN", "DELETE", () -> {
            System.out.println("DELETE");
        });

        window.open();
    }
}
