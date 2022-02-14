package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Autore;
import model.AutoreLibro;
import model.Libro;

public class Database {

	private static Database instance;
	private Connection con;

	private final static String DB_URL = "jdbc:mysql://localhost:3306/generation";
	private final static String DB_USER = "app_generation";
	private final static String DB_PASSWORD = "generation_2022";
	private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private Database() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	public static Database getDatabase() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public List<Libro> getAllLibri() throws SQLException {
		String sql = "SELECT id, titolo, prezzo, pagine "
				+ "FROM generation.libro";
		PreparedStatement istruzione = con.prepareStatement(sql);
		ResultSet risultatiQuery = istruzione.executeQuery();
		List<Libro> listaLibri = new ArrayList<>();
		while(risultatiQuery.next()) {
			Libro libro = new Libro();
			libro.setId(risultatiQuery.getInt("id"));
			libro.setTitolo(risultatiQuery.getString("titolo"));
			libro.setPrezzo(risultatiQuery.getDouble("prezzo"));
			libro.setPagine(risultatiQuery.getInt("pagine"));

			
			listaLibri.add(libro);
		}
		return listaLibri;
	}

	public List<Autore> getAllAutori() throws SQLException {
		String sql = "SELECT id, nome, cognome, nazionalita "
				+ "FROM generation.autore";
		PreparedStatement istruzione = con.prepareStatement(sql);
		ResultSet risultatiQuery = istruzione.executeQuery();

		List<Autore> listaAutori = new ArrayList<>();

		while(risultatiQuery.next()) {
			Autore autore = new Autore();
			autore.setId(risultatiQuery.getInt("id"));
			autore.setNome(risultatiQuery.getString("nome"));
			autore.setCognome(risultatiQuery.getString("cognome"));
			autore.setNazionalita(risultatiQuery.getString("nazionalita"));
			listaAutori.add(autore);
		}
		return listaAutori;
	}

	public List<AutoreLibro> getAllAutoriLibri() throws SQLException {
		String sql = "SELECT "
				+ "al.autore_id, "
				+ "al.libro_id, "
				+ "a.cognome, "
				+ "l.titolo, "
				+ "l.prezzo"
				+ "FROM generation.autore_libro al "
				+ "JOIN generation.autore a ON (a.id = al.autore_id) "
				+ "JOIN generation.libro l ON (l.id = al.libro_id)";

		PreparedStatement istruzione = con.prepareStatement(sql);
		ResultSet risultatiQuery = istruzione.executeQuery();

		List<AutoreLibro> listaAutoriLibri = new ArrayList<>();

		while(risultatiQuery.next()) {
			AutoreLibro autoreLibro = new AutoreLibro();

			autoreLibro.setAlAutoreId(risultatiQuery.getInt("al.autore_id"));
			autoreLibro.setAlLibroId(risultatiQuery.getInt("al.libro_id"));
			autoreLibro.setaCognome(risultatiQuery.getString("a.cognome"));
			autoreLibro.setlTitolo(risultatiQuery.getString("l.titolo"));
			autoreLibro.setlPrezzo(risultatiQuery.getDouble("l.prezzo"));
			

			listaAutoriLibri.add(autoreLibro);
		}
		return listaAutoriLibri;
	}

	public int getAutoriInAutoriLibri(int idAutore) throws SQLException {
		String sql= "SELECT count(*) AS counter "
				+ "FROM generation.autore_libro al "
				+ "WHERE al.autore_id = ?";

		PreparedStatement istruzione = con.prepareStatement(sql);

		istruzione.setInt(1, idAutore);

		ResultSet risultatiQuery = istruzione.executeQuery();
		int counter = 0;
		while(risultatiQuery.next()) {
			counter =  risultatiQuery.getInt("counter");
		}

		return counter;
	}
		
		public Libro getLibroById(int idLibro) throws SQLException {
			String sql= "SELECT id, titolo, prezzo, pagine "
					+ "FROM generation.libro l "
					+ "WHERE id = ?";

			PreparedStatement istruzione = con.prepareStatement(sql);

			istruzione.setInt(1, idLibro);

			ResultSet risultatiQuery = istruzione.executeQuery();
			while(risultatiQuery.next()) {
			Libro L=new Libro();
			L.setId(risultatiQuery.getInt("id"));
			L.setTitolo(risultatiQuery.getString("titolo"));
			L.setPrezzo(risultatiQuery.getDouble("prezzo"));
			L.setPagine(risultatiQuery.getInt("pagine"));
			return L;
			}
			
			return null;

	}
		
	public boolean insertAutore(Autore A) throws SQLException{
		String sql="INSERT INTO generation.autore "
				+ " (id, nome, cognome, nazionalita)"
				+ " VALUES(?, ?, ?, ?);";

		PreparedStatement istruzione= con.prepareStatement(sql);

		istruzione.setInt(1, A.getId());
		istruzione.setString(2, A.getNome());
		istruzione.setString(3, A.getCognome());
		istruzione.setString(4, A.getNazionalita());

		int numRigheModificate=istruzione.executeUpdate();

		if(numRigheModificate==1) return true;

		return false;
	}

	public int updateAutore(Autore A) throws SQLException{
		String sql="UPDATE generation.autore "
				+ " SET id=?, nome=?, cognome=?, nazionalita=? "
				+ " WHERE id=? ; ";
		PreparedStatement istruzione= con.prepareStatement(sql);
		istruzione.setInt(1, A.getId());
		istruzione.setString(2, A.getNome());
		istruzione.setString(3, A.getCognome());
		istruzione.setString(4, A.getNazionalita());
		return istruzione.executeUpdate();
	}

	
	public boolean insertLibro(Libro lib) throws SQLException{
		String sql="INSERT INTO generation.libro "
				+ " (titolo, prezzo, pagine)"
				+ " VALUES(?, ?, ?);";

		PreparedStatement istruzione= con.prepareStatement(sql);

		
		istruzione.setString(1, lib.getTitolo());
		istruzione.setDouble(2, lib.getPrezzo());
		istruzione.setInt(3, lib.getPagine());

		int numRigheModificate=istruzione.executeUpdate();

		if(numRigheModificate==1) return true;

		return false;
	}

	public int updateLibro(Libro lib) throws SQLException{
		String sql="UPDATE generation.libro "
				+ " SET titolo=?, prezzo=?, pagine=? "
				+ " WHERE id=? ; ";
		PreparedStatement istruzione= con.prepareStatement(sql);
		istruzione.setString(1, lib.getTitolo());
		istruzione.setDouble(2, lib.getPrezzo());
		istruzione.setInt(3, lib.getPagine());
		istruzione.setInt(4, lib.getId());
		return istruzione.executeUpdate();
	}

	public int deleteLibro(Libro lib) throws SQLException{
		String sql="DELETE FROM generation.libro WHERE id=? ; ";
		PreparedStatement istruzione= con.prepareStatement(sql);		
		istruzione.setInt(1, lib.getId());

		return istruzione.executeUpdate();
	}
	
	public int deleteAutore(int idAutore) throws SQLException {
		String sql= "DELETE FROM generation.autore "
				+ "WHERE al.autore_id = ?";
		
		PreparedStatement istruzione = con.prepareStatement(sql);
		
		istruzione.setInt(1, idAutore);
		
		return istruzione.executeUpdate();
	}
	public Autore getAutoreById(int idAutore) throws SQLException {
		String sql= "SELECT id, nome, cognome, nazionalita "
				+ "FROM generation.autore a "
				+ "WHERE id = ?";

		PreparedStatement istruzione = con.prepareStatement(sql);

		istruzione.setInt(1, idAutore);

		ResultSet risultatiQuery = istruzione.executeQuery();
		while(risultatiQuery.next()) {
		Autore A=new Autore();
		A.setId(risultatiQuery.getInt("id"));
		A.setNome(risultatiQuery.getString("nome"));
		A.setCognome(risultatiQuery.getString("cognome"));
		A.setNazionalita(risultatiQuery.getString("nazionalita"));
		return A;
		}
	return null;
	}

	
	}

