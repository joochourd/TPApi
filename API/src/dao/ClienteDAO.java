package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clases.ActualizacionEstado;
import clases.Cliente;
import clases.Estados;
import clases.TipoReclamo;
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
			String SQL = ("DELETE FROM clientes WHERE dniCuit = ('" + dniCuit +"');");
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
			String nombre = null;
			String domicilio = null;
			String telefono = null;
			String mail = null;
			while (rs.next()) {
				nombre = rs.getString("nombre");
				domicilio = rs.getString("domicilio");
				telefono = rs.getString("telefono");;
				mail = rs.getString("mail");
			}
			Cliente cliente = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
			return cliente;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el Cliente");
		}
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
		String SQL = "SELECT * FROM clientes";
		try {
			rs = stmt.executeQuery(SQL);
			//List<Cliente> clientes = new ArrayList<Cliente>();
			while(rs.next()){
				int dniCuit = rs.getInt("dniCuit");
				String nombre = rs.getString("nombre") ;
				String domicilio = rs.getString("domicilio") ;
				String telefono = rs.getString("telefono") ;
				String mail = rs.getString("mail") ;
				Cliente cliente = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
				clientes.add(cliente);
			}
			return clientes;
		} catch (SQLException e1) {
			throw new AccesoException("");
		}
	}
	
}
