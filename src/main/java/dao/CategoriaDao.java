package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Categoria;
import model.Editorial;

public class CategoriaDao {
	
	public static boolean registrar(Categoria cat) {
		try {
			String sql = "INSERT INTO categorias(nombre) values(?);";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, cat.getNombre());
			if (st.executeUpdate()>0) {
				return true;
			} else {
				return false;				
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static ArrayList<Categoria> listar() {
		try {
			String sql = "SELECT * FROM categorias;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet resultado = st.executeQuery();
			ArrayList<Categoria> lista = new ArrayList<>();
			Categoria cat;
			while(resultado.next()) {
				cat = new Categoria();
				cat.setCodigo(resultado.getInt("codigo"));
				cat.setNombre(resultado.getString("nombre"));
				lista.add(cat);
			}
			return lista;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static String getCategoria(int cod) {
		try {
			String sql = "SELECT nombre FROM categorias WHERE codigo=?;";
			Connection con = Conexion.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, cod);
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
