package Proyecto;

import java.util.ArrayList;

import lib.JSwingManager;
import lib.DBManager;

public class Main {
    private static JSwingManager window = new JSwingManager("ABM para tabla Vehículos");
    private static DBManager db = new DBManager();
    private static String[] columns = {
        "CHASIS",
        "PATENTE",
        "CONDICION",
        "MARCA",
        "VERSION",
        "ANIO",
        "PRECIO",
        "KILOMETRAJE",
        "ESTADO",
        "SUCURSAL_ID",
        "PROVEEDOR_ID",
        "ACTIVO",
        "CREACION",
        "ELIMINACION",
        "MODIFICACION"
    };

    public static void main(String[] args) {
        //Default
        createINSERTPanel();

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

                //Generate query
                query = "SELECT " + columnsSelected + " FROM VEHICULOS";
                
                //Get and insert where 
                where = window.getTextAreaLeft().get(0)[1];
                if (!where.isEmpty()){
                    query += " WHERE " + where;
                }

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
        renderINSERT();
    }
    private static void renderINSERT () {
        //Add inputs
        for (int i = 0; i < 9; i++) {
            String column = columns[i];
            window.addTextAreaLeft(column, 15, 1);
        }

        //Add selects
        __insertComboBox("SUCURSALES");
        __insertComboBox("PROVEEDORES");
    }
    private static void __insertComboBox (String columnName) {
        String[] items;
        ArrayList<Object[]> data = db.executeSELECT("SELECT ID, NOMBRE FROM " + columnName + " WHERE ACTIVO = TRUE;");
        int j = 1, countItems = data.size() - 1;
        items = new String[countItems];
        for (int i = 0; i < countItems; i++) {
            items[i] = data.get(j)[0] + " - " + (String)data.get(j)[1];
            j++;
        }
        window.addComboBox(columnName, items);
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
