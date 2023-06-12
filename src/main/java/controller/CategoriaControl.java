package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;

import java.io.IOException;

import dao.CategoriaDao;

/**
 * Servlet implementation class CategoriaControl
 */
public class CategoriaControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		Categoria c = new Categoria();
		c.setNombre(nombre);
		if (CategoriaDao.registrar(c)) {
			request.setAttribute("mensaje", "La categoria fue registrada.");
		} else {
			request.setAttribute("mensaje", "La categoria no fue registrada.");
		}
		request.getRequestDispatcher("registroCategoria.jsp").forward(request, response);
	}

}
