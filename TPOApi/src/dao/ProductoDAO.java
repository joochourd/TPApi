package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

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
	
	public List<Producto> buscarProducto(String tituloProd, int codigo ) throws ConexionException, AccesoException{
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;
		List<Producto> resultado = new LinkedList<Producto>();
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
		String SQL = ("SELECT * FROM productos WHERE titulo =('" + tituloProd +"') or codigo = ('" + codigo +"');");
		try{
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				int codigoPublicacion = rs.getInt("codigoPublicacion");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				float precio = rs.getFloat("precio");
				Producto producto = new Producto(codigoPublicacion, nombre, descripcion, precio);
				resultado.add (producto);
			}
			return resultado;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");//Rellenar msj
		}
	}


}
