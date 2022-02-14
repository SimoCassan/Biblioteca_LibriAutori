package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Libro;

import java.io.IOException;
import java.sql.SQLException;

import controller.LibroController;
@WebServlet("/modificaLibro")
public class UpdateLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroController controller;
    public UpdateLibro() throws ClassNotFoundException, SQLException {
    	controller = LibroController.getController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLibro = Integer.parseInt(request.getParameter("modificaId"));
		
		try {
			Libro L = controller.getLibroById(idLibro);
			request.setAttribute("id", L.getId());
			request.setAttribute("titolo", L.getTitolo());
			request.setAttribute("prezzo", L.getPrezzo());
			request.setAttribute("pagine", L.getPagine());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("modificaLibro.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String titolo = request.getParameter("titolo");
			double prezzo = Double.parseDouble(request.getParameter("prezzo"));
			int pagine = Integer.parseInt(request.getParameter("pagine"));
			int nLibriModificati = controller.updateLibro(titolo, prezzo, pagine);
			if(nLibriModificati ==1) {
				
				request.setAttribute("avvisoMessaggio",  "Libro modificato con successo");
				
			}else {
				
				request.setAttribute("avvisoMessaggio",  "Anomalia, inseriti " + nLibriModificati + " records.");
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		request.getRequestDispatcher("listaLibri").forward(request, response);
	}

}
