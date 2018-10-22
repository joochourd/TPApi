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

	public Rol obtenerRol(String tipoRol) throws ConexionException, AccesoException {
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
		String SQL = ("SELECT * FROM roles WHERE descripcion =('" + tipoRol + "');");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				String auxDescripcion = resultSet.getString("descripcion");
				String auxTipoReclamo = resultSet.getString("tipoReclamo");
				//List<Empleado> reclamos = new ArrayList<Empleado>();

				
				if(auxTipoReclamo.equals("zona")) {
					Enum <TipoReclamo> tipoReclamo = TipoReclamo.Zona;	
					Enum <TipoRol> descripcion = null;
					if (auxDescripcion.equals("responsableFacturacion")) {
						descripcion = TipoRol.responsableFacturacion;
					}
					if (auxDescripcion.equals("responsableDistribucion")) {
						descripcion = TipoRol.responsableDistribucion;
					}
					if (auxDescripcion.equals("responsableZonas")) {
						descripcion = TipoRol.responsableZonas;
					}
					if (auxDescripcion.equals("callCenter")) {
						descripcion = TipoRol.callCenter;
					}
					if (auxDescripcion.equals("administrador")) {
						descripcion = TipoRol.administrador;
					}
					if (auxDescripcion.equals("consulta")) {
						descripcion = TipoRol.consulta;
					}
					
					Rol rol = new Rol(descripcion, tipoReclamo);
					return rol;
				}
				if(auxTipoReclamo.equals("Facturacion")) {
					Enum <TipoReclamo> tipoReclamo = TipoReclamo.Facturacion;	
					Enum <TipoRol> descripcion = null;
					if (auxDescripcion.equals("responsableFacturacion")) {
						descripcion = TipoRol.responsableFacturacion;
					}
					if (auxDescripcion.equals("responsableDistribucion")) {
						descripcion = TipoRol.responsableDistribucion;
					}
					if (auxDescripcion.equals("responsableZonas")) {
						descripcion = TipoRol.responsableZonas;
					}
					if (auxDescripcion.equals("callCenter")) {
						descripcion = TipoRol.callCenter;
					}
					if (auxDescripcion.equals("administrador")) {
						descripcion = TipoRol.administrador;
					}
					if (auxDescripcion.equals("consulta")) {
						descripcion = TipoRol.consulta;
					}
					
					Rol rol = new Rol(descripcion, tipoReclamo);
					return rol;
				}

				if(auxTipoReclamo.equals("Cantidad")) {
					Enum <TipoReclamo> tipoReclamo = TipoReclamo.Cantidad;	
					Enum <TipoRol> descripcion = null;
					if (auxDescripcion.equals("responsableFacturacion")) {
						descripcion = TipoRol.responsableFacturacion;
					}
					if (auxDescripcion.equals("responsableDistribucion")) {
						descripcion = TipoRol.responsableDistribucion;
					}
					if (auxDescripcion.equals("responsableZonas")) {
						descripcion = TipoRol.responsableZonas;
					}
					if (auxDescripcion.equals("callCenter")) {
						descripcion = TipoRol.callCenter;
					}
					if (auxDescripcion.equals("administrador")) {
						descripcion = TipoRol.administrador;
					}
					if (auxDescripcion.equals("consulta")) {
						descripcion = TipoRol.consulta;
					}
					
					Rol rol = new Rol(descripcion, tipoReclamo);
					return rol;
				}
				if(auxTipoReclamo.equals("Producto")) {
					Enum <TipoReclamo> tipoReclamo = TipoReclamo.Producto;	
					Enum <TipoRol> descripcion = null;
					if (auxDescripcion.equals("responsableFacturacion")) {
						descripcion = TipoRol.responsableFacturacion;
					}
					if (auxDescripcion.equals("responsableDistribucion")) {
						descripcion = TipoRol.responsableDistribucion;
					}
					if (auxDescripcion.equals("responsableZonas")) {
						descripcion = TipoRol.responsableZonas;
					}
					if (auxDescripcion.equals("callCenter")) {
						descripcion = TipoRol.callCenter;
					}
					if (auxDescripcion.equals("administrador")) {
						descripcion = TipoRol.administrador;
					}
					if (auxDescripcion.equals("consulta")) {
						descripcion = TipoRol.consulta;
					}
					
					Rol rol = new Rol(descripcion, tipoReclamo);
					return rol;
				}
				if(auxTipoReclamo.equals("Falta")) {
					Enum <TipoReclamo> tipoReclamo = TipoReclamo.Falta;	
					Enum <TipoRol> descripcion = null;
					if (auxDescripcion.equals("responsableFacturacion")) {
						descripcion = TipoRol.responsableFacturacion;
					}
					if (auxDescripcion.equals("responsableDistribucion")) {
						descripcion = TipoRol.responsableDistribucion;
					}
					if (auxDescripcion.equals("responsableZonas")) {
						descripcion = TipoRol.responsableZonas;
					}
					if (auxDescripcion.equals("callCenter")) {
						descripcion = TipoRol.callCenter;
					}
					if (auxDescripcion.equals("administrador")) {
						descripcion = TipoRol.administrador;
					}
					if (auxDescripcion.equals("consulta")) {
						descripcion = TipoRol.consulta;
					}
					
					Rol rol = new Rol(descripcion, tipoReclamo);
					return rol;
				}
			}
		return null;	 
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el Rol");// Rellenar msj
		}
	}

}