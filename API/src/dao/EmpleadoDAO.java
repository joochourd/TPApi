package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Empleado;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class EmpleadoDAO {
	
	private static EmpleadoDAO instancia;
	
	//private ClienteDAO(){}
		
	public static EmpleadoDAO getInstancia(){
		if(instancia == null){
			instancia = new EmpleadoDAO();
		}
		return instancia;
	}
	
	public void grabarEmpleado(Empleado empleado) throws ConexionException, AccesoException{
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
		String SQL = "INSERT INTO empleados values ('" + empleado.getNomUsr() +"','" + empleado.getContraseña() + "','" + empleado.getNombre() + "','" + empleado.getNroLU() + "','" + empleado.getRolOriginal() + "','" + empleado.getRolTemporal() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	public void borrarEmpleado(String nombreUsr) throws ConexionException, AccesoException{
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
			String SQL = ("DELETE FROM empleados WHERE nombreUsr = ('" + nombreUsr +"');");
			try{
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
	}
	public void modificarEmpleado(Empleado empleado, String nombreAnterior, int dniCuitAnterior) throws ConexionException, AccesoException{
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
		String SQL = ("UPDATE clientes SET nombre =('" + empleado.getNombre() +"'), contraseña=('" + empleado.getContraseña() +"'), nroLu = ('" + empleado.getNroLU() +"'), rolOriginal = ('" + empleado.getRolOriginal() +"'), rolTemporal = ('" + empleado.getRolTemporal() +"') WHERE nombreUsr = ('" + empleado.getNomUsr() +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}
	public void buscarEmpleado(String nomUsr) throws ConexionException, AccesoException{
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
		String SQL = ("SELECT * FROM empleados WHERE NombreUsr =('" + nomUsr +"');");
		try{
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");//Rellenar msj
		}
	}

}
