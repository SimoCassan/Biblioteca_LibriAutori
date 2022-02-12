package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import controller.LibroController;

@WebServlet("/deleteLibro")
public class DeleteLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private LibroController controller;
	
       
    
    public DeleteLibro() throws ClassNotFoundException, SQLException {
        controller = LibroController.getController();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idDaCancellare=Integer.parseInt(request.getParameter("deleteId"));
			int nLibroCancellati = controller.deleteLibro(idDaCancellare);
			
			if(nLibroCancellati == 1) {
				
				request.setAttribute("avvisoMessaggio",  "Libro cancellato con successo");
				
			}else {
				
				request.setAttribute("avvisoMessaggio",  "Anomalia, cancellati " + nLibroCancellati + " records.");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("listaAutori").forward(request, response);
	
	}

}
