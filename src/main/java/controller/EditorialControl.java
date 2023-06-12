package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Editorial;
import java.io.IOException;

import dao.EditorialDao;

/**
 * Servlet implementation class EditorialControl
 */
public class EditorialControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorialControl() {
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
		String nit = request.getParameter("nit");
		String nombre = request.getParameter("nombre");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String email = request.getParameter("email");
		String sitioWeb = request.getParameter("sitio_web");
		Editorial e = new Editorial();
		e.setNit(nit);
		e.setNombre(nombre);
		e.setEmail(email);
		e.setDireccion(direccion);
		e.setTelefono(telefono);
		e.setSitioweb(sitioWeb);
		if (EditorialDao.registrar(e)) {
			request.setAttribute("mensaje", "La editorial fue registrada.");
		} else {
			request.setAttribute("mensaje", "La editorial no fue registrada.");
		}
		request.getRequestDispatcher("registroEditorial.jsp").forward(request, response);
	}

}
