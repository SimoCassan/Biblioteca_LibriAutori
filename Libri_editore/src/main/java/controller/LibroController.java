package controller;
 
import java.sql.SQLException;
import java.util.List;
 
import model.Libro;
import repository.Database;
 
public class LibroController {
 
	private static LibroController instance;
 
	private Database db;
 
	public LibroController() throws ClassNotFoundException, SQLException {
		db = Database.getDatabase();
	}
 
	public static LibroController getController() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new LibroController();
		}
		return instance;
	}	
 
	public List<Libro> getAllLibri() throws SQLException{
		return db.getAllLibri();
	}
	public int updateStudente(int id, String titolo, double prezzo, int pagine) throws SQLException{
		Libro daAggiornare=new Libro();
		daAggiornare.setId(id);
		daAggiornare.setTitolo(titolo);
		daAggiornare.setPrezzo(prezzo);
		daAggiornare.setPagine(pagine);
		
		return db.updateLibro(daAggiornare);
	}
	public int deleteLibro(int id) throws SQLException{
		Libro daCancellare=new Libro();
		daCancellare.setId(id);
		return db.deleteLibro(daCancellare);
	}
}
 