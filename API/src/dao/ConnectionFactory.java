package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory instancia;
	String connectionUrl = "jdbc:sqlserver://bd;databaseName=AI_4034m_03;user=AI_4034m_03;password=AI_4034m_03";
	
	private ConnectionFactory() throws ClassNotFoundException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	}
	
	public static ConnectionFactory getInstancia() throws ClassNotFoundException{
		if(instancia == null)
			instancia = new ConnectionFactory();
		return instancia;
	}
	
	public Connection getConection() throws SQLException{
		return DriverManager.getConnection(connectionUrl);
	}

}
