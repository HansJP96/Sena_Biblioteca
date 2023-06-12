package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Editorial;

public class EditorialDao {
	
	public static boolean registrar(Editorial e) {
		try {
			String sql = "INSERT INTO editoriales(nit,nombre,telefono,direccion,email,sitioweb)"
					+ " values(?,?,?,?,?,?);";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, e.getNit());
			st.setString(2, e.getNombre());
			st.setString(3, e.getTelefono());
			st.setString(4, e.getDireccion());
			st.setString(5, e.getEmail());
			st.setString(6, e.getSitioweb());
			if (st.executeUpdate()>0) {
				return true;
			} else {
				return false;				
			}
		} catch (SQLException exc) {
			return false;
		}
	}
	
	public static ArrayList<Editorial> listar() {
		try {
			String sql = "SELECT * FROM editoriales;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet resultado = st.executeQuery();
			ArrayList<Editorial> lista = new ArrayList<>();
			Editorial e;
			while(resultado.next()) {
				e = new Editorial();
				e.setNit(resultado.getString("nit"));
				e.setNombre(resultado.getString("nombre"));
				e.setTelefono(resultado.getString("telefono"));
				e.setDireccion(resultado.getString("direccion"));
				e.setEmail(resultado.getString("email"));
				e.setSitioweb(resultado.getString("sitioweb"));
				lista.add(e);
			}
			return lista;
		} catch (SQLException exc) {
			return null;
		}
	}
	
	public static String getEditorial(String nit) {
		try {
			String sql = "SELECT nombre FROM editoriales WHERE nit=?;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nit);
			ResultSet resultado = st.executeQuery();
			if (resultado.next()) {
				return resultado.getString("nombre");
			}
			return "--";
		} catch (SQLException exc) {
			return "--";
		}
	}
}
