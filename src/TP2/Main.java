package TP2;

import java.util.List;
import lib.DBManager;
import lib.JSwingManager;

public class Main {
	public static void main(String[] args) {
		//Queries to execute
		String[] queries = {
			"SELECT hire_date, count(*) FROM employees GROUP BY hire_date;",
			"SELECT gender, count(*) FROM employees GROUP BY gender;",
			"SELECT EXTRACT(year from birth_date) as birth_year, count(*) FROM employees GROUP BY birth_year;",
			"SELECT dept_no, count(*) FROM dept_emp WHERE to_date='9999-01-01' GROUP BY dept_no;",
			"SELECT title, count(*) FROM titles WHERE to_date = '9999-01-01' GROUP BY title;",
			"SELECT MAX(salary) as maximo, MIN(salary) as minimo, AVG(salary) as promedio FROM salaries WHERE to_date='9999-01-01';",
			"SELECT dept_no, MAX(salary) as maximo, MIN(salary) as minimo, AVG(salary) as promedio " +
			"FROM salaries s " +
			"INNER JOIN dept_emp de " +
			"ON s.emp_no=de.emp_no " +
			"WHERE de.to_date = '9999-01-01' " +
			"GROUP BY dept_no;",
			"SELECT title, MAX(salary) as maximo, MIN(salary) as minimo, AVG(salary) as promedio " +
			"FROM salaries s " +
			"INNER JOIN titles t " +
			"ON s.emp_no=t.emp_no " +
			"WHERE t.to_date = '9999-01-01' " +
			"GROUP BY title;",
			"SELECT (EXTRACT(year from current_date) - EXTRACT(year from birth_date)) as years_old, count(*) FROM employees GROUP BY years_old;",
			"SELECT title, MAX(salary) as salary FROM titles t " +
			"INNER JOIN salaries s " +
			"ON t.emp_no=s.emp_no " +
			"WHERE t.to_date = '9999-01-01' AND t.to_date='9999-01-01' " +
			"GROUP BY title " +
			"ORDER BY salary DESC " +
			"LIMIT 3;",
			"SELECT dept_no, SUM(salary) as gasto FROM salaries s " +
			"INNER JOIN dept_emp de " +
			"ON s.emp_no=de.emp_no " +
			"WHERE de.to_date='9999-01-01' AND s.to_date='9999-01-01' " +
			"GROUP BY dept_no " +
			"ORDER BY gasto DESC " +
			"LIMIT 3;"
		};
		JSwingManager window = new JSwingManager();
		DBManager db = new DBManager();

		//Create buttons
		for (int i = 0; i < queries.length; i++) {
			final int index = i;
			window.addButton("EJERCICIO " + ((i + 5 < 10) ? "0" + (i+5) : (i+5)), 
			() -> {
				window.resetTable();
				//System.out.println(queries[index]);
				List<Object[]> results = db.executeSELECT(queries[index]);
				window.addColumns((String[])results.get(0));
				window.addRows(results.subList(1, results.size()));
			});	
		}

		//Open window
		window.open();
	}
}