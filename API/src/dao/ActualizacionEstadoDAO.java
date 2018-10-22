package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.ActualizacionEstado;
import clases.Estados;
import excepciones.AccesoException;
import excepciones.ConexionException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.ActualizacionEstado;
import clases.CantYProdYFalta;
import clases.Cliente;
import clases.Facturacion;
import clases.Reclamo;
import clases.TipoReclamo;
import clases.Zona;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ActualizacionEstadoDAO {

private static ActualizacionEstadoDAO instancia;
	
		
	public static ActualizacionEstadoDAO getInstancia(){
		if(instancia == null){
			instancia = new ActualizacionEstadoDAO();
		}
		return instancia;
	}
	
	public void grabarActualizacionEstado(ActualizacionEstado actEstado) throws ConexionException, AccesoException{
		Connection con = null;  
		Statement stmt = null;  
		try {    
			con = ConnectionFactory.getInstancia().getConection();
		}
		catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = "INSERT INTO actualizacionEstado values ('" +
				actEstado.getFecha() +"','" 
				+ actEstado.getDescripcion() + "','" 
				+ actEstado.getEstado() + "','" 
				+ actEstado.getNombreUsrEmpleado() + "','" 
				+ actEstado.getIdReclamo() + 
				"');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	
	public List<ActualizacionEstado> buscarActualizacionesEstadosxReclamo(int idReclamo) throws ConexionException, AccesoException{
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;
		try {    
			con = ConnectionFactory.getInstancia().getConection();
		}
		catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = ("SELECT * FROM actualizacionEstado WHERE idReclamo =('" + idReclamo +"');");
		try{
			rs = stmt.executeQuery(SQL);
			List<ActualizacionEstado> actualizaciones = new ArrayList<ActualizacionEstado>();
			Enum <Estados> estado = null;
			Date fecha = null;
			String descripcion = null;
			String auxEstado = null;
			while (rs.next()) {
				fecha = rs.getDate("fecha");
				descripcion = rs.getString("descripcion");
				auxEstado = rs.getString("estado");
				
				if (auxEstado.equals("Registrado")) {
					estado = Estados.Registrado;
				}
				if (auxEstado.equals("Resuelto")) {
					estado = Estados.Resuelto;
				}
				if (auxEstado.equals("EnTratamineto")) {
					estado = Estados.EnTratamineto;
				}
				if (auxEstado.equals("Cerrado")) {
					estado = Estados.Cerrado;
				}
				String nombreUsrEmpleado = rs.getString("empleadoNombreUsr");
				ActualizacionEstado actualizacion = new ActualizacionEstado(idReclamo, fecha.toLocalDate(), descripcion,estado, nombreUsrEmpleado);
				actualizaciones.add(actualizacion);
			}
			return actualizaciones;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudieron crear las actualizaciones");
		}
	}
	
	
}
