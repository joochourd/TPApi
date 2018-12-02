package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clases.Factura;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class FacturaDAO {
	
private static FacturaDAO instancia;
	
		
	public static FacturaDAO getInstancia(){
		if(instancia == null){
			instancia = new FacturaDAO();
		}
		return instancia;
	}
	
	public void grabarFactura(Factura factura) throws ConexionException, AccesoException{
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
		String SQL = "INSERT INTO facturas values ('" + factura.getNro() +"','" + factura.getTipo() + "','" + factura.getFecha() + "','" + factura.getTotal() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	public void borrarFactura(int nro) throws ConexionException, AccesoException{
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
			String SQL = ("DELETE FROM facturas WHERE numero = ('" + nro +"');");
			try{
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
	}
	/*public void modificarFactura(Factura factura) throws ConexionException, AccesoException{
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
		String SQL = ("UPDATE facturas SET numero =('" + factura.getNro() +"'), tipo=('" + factura.getTipo() +"'), fecha = ('" + factura.getFecha() +"'), total = ('" + factura.getTotal() +"') WHERE numero = ('" + factura.getNro() +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}*/
	public Factura buscarFactura(int nro) throws ConexionException, AccesoException{
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
		String SQL = ("SELECT * FROM facturas WHERE numero = " + nro +";");
		try{
			rs = stmt.executeQuery(SQL);
			String tipo = null;
			Date fecha = null;
			float total = 0;
			while (rs.next()) {
				tipo = rs.getString("tipo");
				fecha = rs.getDate("fecha");
				total = rs.getFloat("total");
				
			}
			Factura factura = new Factura(nro, tipo, fecha, total);
			return factura;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo encontrar la Factura");
		}
	}
	
	public List<Factura> buscarFacturaCliente(int dniCuitCliente) throws ConexionException, AccesoException{

		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;
		List<Factura> facturas = new ArrayList<>();
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
		String SQL = ("SELECT * FROM facturas WHERE dniCuitCliente = " + dniCuitCliente +";");
		try{
			rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				int numero = rs.getInt("numero");
				String tipo = rs.getString("tipo");
				Date fecha = rs.getDate("fecha");
				float total = rs.getFloat("total");
				facturas.add(new Factura(numero, tipo, fecha, total));
				
			}

			return facturas;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo encontrar la Factura");
		}
	
	}
}
