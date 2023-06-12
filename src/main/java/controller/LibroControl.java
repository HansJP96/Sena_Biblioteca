package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Libro;

import java.io.IOException;

import dao.LibroDao;

/**
 * Servlet implementation class LibroControl
 */
public class LibroControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibroControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion");
		System.out.println(descripcion);
		String nombre_autor = request.getParameter("nombre_autor");
		String publicacion = request.getParameter("publicacion");
		String codigo_categoria = request.getParameter("codigo_categoria");
		String nit_editorial = request.getParameter("nit_editorial");
		String accion = request.getParameter("accion").toLowerCase();
		Libro lib = new Libro();
		lib.setIsbn(isbn);
		lib.setTitulo(titulo);
		lib.setDescripcion(descripcion);
		lib.setNombre_autor(nombre_autor);
		lib.setPublicacion(publicacion);
		lib.setCodigo_categoria(Integer.parseInt(codigo_categoria));
		lib.setNit_editorial(nit_editorial);

		if (accion.equals("registrar")) {
			if (LibroDao.registrar(lib)) {
				request.setAttribute("mensaje", "El libro fue registrado.");
			} else {
				request.setAttribute("mensaje", "El libro NO fue registrado.");
			}
		} else if (accion.equals("actualizar")) {
			if (LibroDao.actualizar(lib)) {
				request.setAttribute("mensaje", "El libro fue actualizado.");
			} else {
				request.setAttribute("mensaje", "El libro NO fue actualizado.");
			}
		} else if (accion.equals("eliminar")) {
			if (LibroDao.eliminar(lib)) {
				request.setAttribute("mensaje", "El libro fue eliminado.");
			} else {
				request.setAttribute("mensaje", "El libro NO fue eliminado.");
			}
		}else {
			request.setAttribute("mensaje", "Accion desconocida.");
		}
		request.getRequestDispatcher("registroLibro.jsp").forward(request, response);
	}
}
