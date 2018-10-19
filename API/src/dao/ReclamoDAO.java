package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Cliente;
import clases.Reclamo;
import clases.Tipo;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ReclamoDAO {
	private static ReclamoDAO instancia;
	
	
	public static ReclamoDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoDAO();
		}
		return instancia;
	}
	
	public void grabarReclamo(Reclamo reclamo) throws ConexionException, AccesoException{
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
		String SQL = "INSERT INTO reclamos values ('" + reclamo.getTipo() + "','" + reclamo.getFecha() + "','" + reclamo.getDescripcion() + "','" + reclamo.getEstado() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	public void borrarReclamo(int nro) throws ConexionException, AccesoException{
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
			String SQL = ("DELETE FROM reclamos WHERE numero = ('" + nro +"');");
			try{
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
	}
	public void modificarReclamo(Reclamo reclamo) throws ConexionException, AccesoException{
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
		//String SQL = ("UPDATE reclamos SET tipo=('" + reclamo.getTipo() +"'), fecha = ('" + reclamo.getFecha() +"'), total = ('" + reclamo.getTotal() +"') WHERE numero = ('" + reclamo.getNro() +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}
	public void buscarReclamo(int nro, Tipo tipo) throws ConexionException, AccesoException{
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
		String SQL = ("SELECT * FROM reclamos WHERE numero =('" + nro +"') or tipo = ('" + tipo +"');");
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
