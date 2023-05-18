package Proyecto;

import java.util.ArrayList;

import lib.JSwingManager;
import lib.DBManager;

public class Main {
    private static JSwingManager window = new JSwingManager("ABM para tabla Vehículos");
    private static DBManager db = new DBManager();
    static boolean isVisible = false;

    public static void main(String[] args) {
        window.addMenu("SELECT", () -> {
            createSELECTPanel();
        });
        window.addMenu("INSERT", () -> {
            createINSERTPanel();
        });
        window.addMenu("UPDATE", () -> {
            createUPDATEPanel();
        });
        window.addMenu("DELETE", () -> {
            createDELETEPanel();
        });
        window.open();
    }

    private static void createSELECTPanel() {
        reset();
        String[] columns = {
            "CHASIS",
            "SUCURSAL_ID",
            "PROVEEDOR_ID",
            "PATENTE",
            "CONDICION",
            "MARCA",
            "VERSION",
            "ANIO",
            "PRECIO",
            "KILOMETRAJE",
            "ESTADO",
            "ACTIVO",
            "CREACION",
            "ELIMINACION",
            "MODIFICACION"
        };

        //Add checkboxs
        for (String column : columns) {
            window.addCheckBox(column);
        }

        //Add text area for where
        window.addTextAreaRight("WHERE", 15, 1);

        //Add button to execute
        window.addButton("EJECUTAR", () -> {
            //List with columns selected
            ArrayList<String> result = window.getSelectedCheckBox();
            if(result.size() > 0){
                String columnsSelected = "", query, where;
                
                //Generate string with selected columns
                for (String selectedCheckBox : result) {
                    columnsSelected += selectedCheckBox + ", ";
                }
                columnsSelected = columnsSelected.substring(0, columnsSelected.length() - 2);

                //Get where 
                where = window.getTextAreaLeft().get(0)[1];

                //Generate query
                query = "SELECT " + columnsSelected + " FROM VEHICULOS WHERE "+where+";";

                System.out.println(query);
                //Execute and show result
                try {
                    ArrayList<Object[]> results = db.executeSELECT(query);
                    window.addColumns((String[])results.get(0));
                    window.addRows(results.subList(1, results.size()));
                    System.out.println("OK.");
                } catch (Exception e) {}
            } else {
                System.out.println("Debe seleccionar al menos una columna!");
            }
        });
    }

    private static void createINSERTPanel() {
        reset();
    }
    
    private static void createUPDATEPanel() {
        reset();
    }
    
    private static void createDELETEPanel() {
        reset();
    }

    private static void reset () {
        window.resetTable();
        window.clearPanels();
    }
}
