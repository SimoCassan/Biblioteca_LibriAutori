package model;

public class AutoreLibro {

	
	private int alAutoreId;
	private int alLibroId;
	private String aCognome;
	private String lTitolo;
	private double lPrezzo;
	
	
	public AutoreLibro() {}


	public AutoreLibro(int alAutoreId, int alLibroId, String aCognome, String lTitolo, double lPrezzo) {
		super();
		this.alAutoreId = alAutoreId;
		this.alLibroId = alLibroId;
		this.aCognome = aCognome;
		this.lTitolo = lTitolo;
		this.lPrezzo = lPrezzo;
	}


	public int getAlAutoreId() {
		return alAutoreId;
	}


	public void setAlAutoreId(int alAutoreId) {
		this.alAutoreId = alAutoreId;
	}


	public int getAlLibroId() {
		return alLibroId;
	}


	public void setAlLibroId(int alLibroId) {
		this.alLibroId = alLibroId;
	}


	public String getaCognome() {
		return aCognome;
	}


	public void setaCognome(String aCognome) {
		this.aCognome = aCognome;
	}


	public String getlTitolo() {
		return lTitolo;
	}


	public void setlTitolo(String lTitolo) {
		this.lTitolo = lTitolo;
	}


	public double getlPrezzo() {
		return lPrezzo;
	}


	public void setlPrezzo(double lPrezzo) {
		this.lPrezzo = lPrezzo;
	}
	
	
	

	
	
	
}
