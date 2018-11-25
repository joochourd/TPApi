package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
				String auxTipoReclamo = resultSet.getString("tipoReclamo");
				
					Rol rol = new Rol(id, TipoReclamo.valueOf(auxTipoReclamo).name());
					return rol;
				}
			}
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el Rol");
		}
		return null;	
		} 
}


