package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import clases.Cliente;
import clases.Producto;
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
		String SQL = "INSERT INTO clientes values ('" + cliente.getNombre() +"','" + cliente.getDomicilio() + "','" + cliente.getTelefono() + "','" + cliente.getDniCuit() + "');";
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
	public void modificarCliente(Cliente cliente, String nombreAnterior, int dniCuitAnterior) throws ConexionException, AccesoException{
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
		String SQL = ("UPDATE clientes SET nombre =('" + cliente.getNombre() +"'), mail=('" + cliente.getMail() +"'), domicilio = ('" + cliente.getDomicilio() +"'), dniCuit=('" + cliente.getDniCuit() +"'), telefono = ('" + cliente.getTelefono() +"') WHERE nombre = ('" + nombreAnterior +"') AND dniCuit = ('" + dniCuitAnterior +"');");
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}
	public List<Cliente> buscarCliente(int dniCuit) throws ConexionException, AccesoException{
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;
		List<Cliente> resultado = new LinkedList<Cliente>();
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
				int dniCuit = rs.getInt("dniCuit");
				String nombre = rs.getString("nombre");
				String domicilio = rs.getString("domicilio");
				String telefono = rs.getString("telefono");
				String mail = rs.getString("mail");
				Cliente cliente = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
				resultado.add (cliente);
			}
			return resultado;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");//Rellenar msj
		}
	}
}
