package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Libro;

public class LibroDao {
	
	public static boolean registrar(Libro lib) {
		try {
			String sql = "INSERT INTO libros(isbn,titulo,descripcion,nombre_autor,publicacion,fecha_registro,codigo_categoria,nit_editorial)"
					+ " values(?,?,?,?,?,(select now()),?,?);";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, lib.getIsbn());
			st.setString(2, lib.getTitulo());
			st.setString(3, lib.getDescripcion());
			st.setString(4, lib.getNombre_autor());
			st.setString(5, lib.getPublicacion());
			st.setInt(6, lib.getCodigo_categoria());
			st.setString(7, lib.getNit_editorial());
			if (st.executeUpdate()>0) {
				return true;
			} else {
				return false;				
			}
		} catch (SQLException exc) {
			return false;
		}
	}
	
	public static ArrayList<Libro> listar() {
		try {
			String sql = "SELECT * FROM libros;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet resultado = st.executeQuery();
			ArrayList<Libro> lista = new ArrayList<>();
			Libro lib;
			while(resultado.next()) {
				lib = new Libro();
				lib.setIsbn(resultado.getString("isbn"));
				lib.setTitulo(resultado.getString("titulo"));
				lib.setDescripcion(resultado.getString("descripcion"));
				lib.setNombre_autor(resultado.getString("nombre_autor"));
				lib.setPublicacion(resultado.getString("publicacion"));
				lib.setFecha_registro(resultado.getString("fecha_registro"));
				lib.setCodigo_categoria(resultado.getInt("codigo_categoria"));
				lib.setNit_editorial(resultado.getString("nit_editorial"));
				lista.add(lib);
			}
			return lista;
		} catch (SQLException exc) {
			return null;
		}
	}
	
	public static boolean actualizar(Libro lib) {
		try {
			String sql = "UPDATE libros SET titulo=?,descripcion=?,nombre_autor=?,publicacion=?,codigo_categoria=?,nit_editorial=?"
					+ "where isbn=?;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, lib.getTitulo());
			st.setString(2, lib.getDescripcion());
			st.setString(3, lib.getNombre_autor());
			st.setString(4, lib.getPublicacion());
			st.setInt(5, lib.getCodigo_categoria());
			st.setString(6, lib.getNit_editorial());
			st.setString(7, lib.getIsbn());
			if (st.executeUpdate()>0) {
				return true;
			} else {
				return false;				
			}
		} catch (SQLException exc) {
			return false;
		}
	}
	
	public static boolean eliminar(Libro lib) {
		try {
			String sql = "DELETE from libros WHERE isbn=?;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, lib.getIsbn());
			if (st.executeUpdate()>0) {
				return true;
			} else {
				return false;				
			}
		} catch (SQLException exc) {
			return false;
		}
	}
}
