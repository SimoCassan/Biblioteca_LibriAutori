package controller;
 
import java.sql.SQLException;
import java.util.List;
 
import model.Autore;
import repo.Database;
 
public class AutoreController {
 
	private static AutoreController instance;
 
	private Database db;
 
	public AutoreController() throws ClassNotFoundException, SQLException {
		db = Database.getDatabase();
	}
 
	public static AutoreController getController() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new AutoreController();
		}
		return instance;
	}	
 
	public List<Autore> getAllAutori() throws SQLException{
		return db.getAllAutori();
	}
	
	//metodo per fare il controllo per il metodo di delete
	
	public boolean deleteAutore(int idAutore) throws SQLException {
		
		if(db.getAutoriInAutoriLibri(idAutore)>0) {
			return false;
		}else {
			db.deleteAutore(idAutore);
			return true;
		}
				
	}
	
}
 