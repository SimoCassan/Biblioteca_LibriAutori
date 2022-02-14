package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import controller.AutoreController;
import controller.LibroController;

@WebServlet("/aggiungiLibro")
public class AggiungiLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroController controller;
    
	public AggiungiLibro() throws ClassNotFoundException, SQLException {
       
        controller = LibroController.getController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String titolo = request.getParameter("titolo");
			double prezzo = Double.parseDouble(request.getParameter("prezzo"));
			int pagine = Integer.parseInt(request.getParameter("pagine"));
			boolean nLibroInseriti = controller.insertLibro(titolo, prezzo, pagine);
			
				if(nLibroInseriti == true) {
					
					request.setAttribute("avvisoMessaggio",  "Libro inserito con successo");
					
				}else {
					
					request.setAttribute("avvisoMessaggio",  "Anomalia, inseriti " + nLibroInseriti + " records.");
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("listaLibri").forward(request, response);
	}

}
