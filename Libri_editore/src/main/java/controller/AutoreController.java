package controller;
 
import java.sql.SQLException;
import java.util.List;
 
import model.Autore;
import model.Libro;
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
	

	public int deleteAutore(int id) throws SQLException {
		
		/*
		 * if(db.getAutoriInAutoriLibri(idAutore)>0) { return false; }else {
		 * db.deleteAutore(idAutore); return true; }
		 */
		Autore daCancellare=new Autore();
		daCancellare.setId(id);
		return db.deleteAutore(daCancellare);	
	}

	public boolean insertAutore(String nome, String cognome, String nazionalita) throws SQLException {
		Autore daInserire=new Autore();
		
		daInserire.setNome(nome);
		daInserire.setCognome(cognome);
		daInserire.setNazionalita(nazionalita);

		return db.insertAutore(daInserire);
	}
	public Autore getAutoreById(int idAutore) throws SQLException {
		return db.getAutoreById(idAutore);
	}
	
	public int updateAutore(String nome,String cognome,String nazionalita ) throws SQLException{
		Autore daAggiornare=new Autore();
		
		daAggiornare.setNome(nome);
		daAggiornare.setCognome(cognome);
		daAggiornare.setNazionalita(nazionalita);

		return db.updateAutore(daAggiornare);
	}

	
}
 