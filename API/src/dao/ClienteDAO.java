package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String SQL = "insert into clientes values ('" + cliente.getNombre() +"','" + cliente.getDomicilio() + "'," + cliente.getTelefono() + ",'" + cliente.getDni() + "');";
		try{
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
		
	}
	
		

	
}
