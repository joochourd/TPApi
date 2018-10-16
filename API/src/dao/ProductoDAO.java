package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Clases.Cliente;
import Clases.Producto;
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
		String SQL = "INSERT INTO productos values ('" + producto.getCodigoPublicacion() +"','" + producto.getTitulo() + "','" + producto.getDescripcion() + "','" + producto.getPrecio() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	public void borrarProducto(int codigoPublicacion) throws ConexionException, AccesoException{
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
			String SQL = ("DELETE FROM productos WHERE codigo = ('" + codigoPublicacion +"');");
			try{
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
	}
	public void modificarProducto(Producto producto, ) throws ConexionException, AccesoException{
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
		String SQL = ("UPDATE productos SET titulo =('" + producto.getTitulo() +"'), descripcion=('" + producto.getDescripcion() +"'), precio = ('" + producto.getPrecio() +"') WHERE x = ('" + x +"') AND x = ('" + x +"');");//x no tiene valor alguno. Ahi debe ir un valor
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}
	public void buscarProducto(int ) throws ConexionException, AccesoException{
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
		String SQL = ("SELECT FROM productos WHERE x =('" + x +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}


}
