package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Cliente;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ClienteDAO {
	
	private static ClienteDAO instancia;
	
	//private ClienteDAO(){}
		
	public static ClienteDAO getInstancia(){
		if(instancia == null){
			instancia = new ClienteDAO();
		}
		return instancia;
	}
	
	public void grabarCliente(Cliente cliente) throws ConexionException, AccesoException{
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
		String SQL = "INSERT INTO clientes values ('" +
				cliente.getNombre() +"','" 
				+ cliente.getDomicilio() + "','" 
				+ cliente.getTelefono() + "','" 
				+ cliente.getDniCuit() + "','" 
				+ cliente.getMail() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
	}	}
		
	public void borrarCliente(int dniCuit, String nombre) throws ConexionException, AccesoException{
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
			String SQL = ("DELETE FROM clientes WHERE nombre = ('" + nombre +"') AND dniCuit = ('" + dniCuit +"');");
			try{
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
	}
	public void modificarCliente(Cliente cliente) throws ConexionException, AccesoException{
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
		String SQL = ("UPDATE clientes SET nombre =('" + cliente.getNombre() +"'), mail=('" + cliente.getMail() +"'), domicilio = ('" + cliente.getDomicilio() +"'), telefono = ('" + cliente.getTelefono() +"') WHERE dniCuit = ('" + cliente.getDniCuit() +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}
	public Cliente buscarCliente(int dniCuit) throws ConexionException, AccesoException{
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
		String SQL = ("SELECT * FROM clientes WHERE dniCuit =('" + dniCuit +"');");
		try{
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");//Rellenar msj
		}
		return
	}
	
	public List<Cliente> obtenerTodosClientes() throws ConexionException, AccesoException, SQLException{
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;  
		List <Cliente> clientes = new ArrayList<Cliente>();
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
		String SQL = "";
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("");
		}
		while(rs.next()){
			
		}
		return clientes;
	}
	
	public obtenerReclamosCliente() throws ConexionException, AccesoException, SQLException{
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;  
		Map<Cliente, Integer> map = new HashMap<Cliente, Integer>();
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
		String SQL = 
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("");
		}
		while(rs.next()){
			
		}
		return		
	}
}
