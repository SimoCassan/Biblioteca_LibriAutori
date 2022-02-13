package controller;
 
import java.sql.SQLException;
import java.util.List;

import model.AutoreLibro;
import repo.Database;
 
public class AutoreLibroController {
 
	private static AutoreLibroController instance;
 
	private Database db;
 
	public AutoreLibroController() throws ClassNotFoundException, SQLException {
		db = Database.getDatabase();
	}
 
	public static AutoreLibroController getController() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new AutoreLibroController();
		}
		return instance;
	}	
 
	public List<AutoreLibro> getAllAutoriLibri() throws SQLException{
		return db.getAllAutoriLibri();
	}
}
 