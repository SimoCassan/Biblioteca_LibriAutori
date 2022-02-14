package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import controller.AutoreController;

@WebServlet("/aggiungiAutore")
public class AggiungiAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AutoreController controller;
    
	public AggiungiAutore() throws ClassNotFoundException, SQLException {
       
        controller = AutoreController.getController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String nazionalita = request.getParameter("nazionalita");
			
			boolean nAutoriInseriti = controller.insertAutore(nome, cognome, nazionalita);
			
				if(nAutoriInseriti == true) {
					
					request.setAttribute("avvisoMessaggio",  "Autore inserito con successo");
					
				}else {
					
					request.setAttribute("avvisoMessaggio",  "Anomalia, inseriti " + nAutoriInseriti + " records.");
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("listaAutori").forward(request, response);
	}

}
