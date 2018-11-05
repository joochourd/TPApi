package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Producto;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ProductoDAO {
	

	private static ProductoDAO instancia;
	
		
	public static ProductoDAO getInstancia(){
		if(instancia == null){
			instancia = new ProductoDAO();
		}
		return instancia;
	}
	
	public void grabarProducto(Producto producto) throws ConexionException, AccesoException{
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
		String SQL = "INSERT INTO productos values ('" + producto.getTitulo() + "','" + producto.getDescripcion() + "','" + producto.getPrecio() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	public void borrarProducto(int codigoPublicacion) throws ConexionException, AccesoException{
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
			String SQL = ("DELETE FROM productos WHERE codigo = ('" + codigoPublicacion +"');");
			try{
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
	}
	public void modificarProducto(Producto producto) throws ConexionException, AccesoException{
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
		String SQL = ("UPDATE productos SET titulo =('" + producto.getTitulo() +"'), descripcion=('" + producto.getDescripcion() +"'), precio = ('" + producto.getPrecio() +"') WHERE codigo = ('" + producto.getCodigoPublicacion() +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}
	
	public Producto buscarProducto(String titulo, int codigoPublicacion ) throws ConexionException, AccesoException{
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
		String SQL = ("SELECT * FROM productos WHERE titulo =('" + titulo +"') or codigo = ('" + codigoPublicacion +"');");
		try{
			rs = stmt.executeQuery(SQL);
			String descripcion = null;
			float precio = 0;
			while (rs.next()) {
				descripcion = rs.getString("descripcion");
				precio = rs.getFloat("precio");
			}
			Producto producto = new Producto(titulo, descripcion, precio); //El constructor no tiene el codigoPublicacion, pero es cuestion de agregarlo si llega a ser necesario tenerlo
			return producto;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el Producto");
		}
	}


}
