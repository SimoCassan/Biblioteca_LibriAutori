package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import controller.AutoreController;

@WebServlet("/deleteAutore")
public class DeleteAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private AutoreController controller;
	
       
    
    public DeleteAutore() throws ClassNotFoundException, SQLException {
        controller = AutoreController.getController();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idDaCancellare = Integer.parseInt(request.getParameter("idAutore"));
		try {
			int nAutoreCancellati = controller.deleteAutore(idDaCancellare);
			
			if(nAutoreCancellati == 1) {
				
				request.setAttribute("avvisoMessaggio",  "Autore cancellato con successo");
				
			}else {
				
				request.setAttribute("avvisoMessaggio",  "Anomalia, cancellati " + nAutoreCancellati + " records.");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("listaAutori").forward(request, response);
	
	}

}
