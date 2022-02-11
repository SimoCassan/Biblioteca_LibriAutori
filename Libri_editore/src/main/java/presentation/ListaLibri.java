package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Libro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.LibroController;

@WebServlet ("/listaLibri")
public class ListaLibri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LibroController controller;
    
    public ListaLibri() throws ClassNotFoundException, SQLException {
        super();
        controller = LibroController.getController();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libro> listaLibri;
		try {
			listaLibri = controller.getAllLibri();
			request.setAttribute("listaLibri", listaLibri);
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
		
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
