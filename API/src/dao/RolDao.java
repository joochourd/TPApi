package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Rol;
import clases.TipoReclamo;
import clases.TipoRol;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class RolDao {
	private static RolDao instancia;

	public static RolDao getInstancia() {
		if (instancia == null) {
			instancia = new RolDao();
		}
		return instancia;
	}

	public Rol obtenerRol(int id) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}

		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = ("SELECT * FROM roles WHERE idRoles =('" + id + "');");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				String auxDescripcion = resultSet.getString("descripcion");

				Rol rol = new Rol(id, auxDescripcion);
				return rol;
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el Rol");
		}
		return null;
	}

	public List<Rol> obtenerTodosLosRoles() throws AccesoException, ConexionException {

		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		List<Rol> roles = new ArrayList<>();
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}

		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = ("SELECT * FROM roles;");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int auxId = resultSet.getInt("idRoles");
				String auxDescripcion = resultSet.getString("descripcion");

				Rol rol = new Rol(auxId, auxDescripcion);

				roles.add(rol);
			}
			return roles;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el Rol");
		}

	}
}
