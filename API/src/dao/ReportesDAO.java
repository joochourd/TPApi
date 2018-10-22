
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clases.Cliente;
import clases.Empleado;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ReportesDAO {

//falta hacer el while del buscarReclamo y verificar el tipo de dato que devuelven los metodos aca 

	private static ReclamoDAO instancia;

	public static ReclamoDAO getInstancia() {
		if (instancia == null) {
			instancia = new ReclamoDAO();
		}
		return instancia;
	}

	public List<Cliente> clientesConMasReclamosPorMes() throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}

		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = "SELECT c.clientes, COUNT(r.reclamos)" +
		"FROM" +
		"    clientes c LEFT JOIN" +
		"    reclamos r ON r.clienteDniCuit = c.dniCuit" +
		"GROUP BY c.dniCuit ;";
		
		
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
		return null;
	}

	public int cantidadReclamosTratadosPorMes(String numeroMes) throws ConexionException, AccesoException {
		
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}

		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = ("SELECT COUNT(fecha) as fechaFacturacion FROM reclamos WHERE MONTH(Date) = " + numeroMes + "AND ESTADOS = CERRADO"+
		";");
		try {
			ResultSet resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				return resultSet.getInt(0);
			}
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
		return 0;
	}

	public Map tiempoPromedioRespuestaReclamosPorResponsable(Empleado empl) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}

		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = "SELECT e.empleados, avg ( DATEDIFF(month, r.fecha, r.fechaFinalizacion)) AS Promedio;" +  //ese r.fecha me genera dudas 
				"From" + 
				" 	empleados e LEFT JOIN" + 
				"    reclamos r ON r.empleadoNomUsr = e.nombreUsr" + 
				"GROUP BY e.empleados;";
		try {
			ResultSet resultSet = stmt.executeQuery(SQL);
			Date fecha = null;
			String empleadoNomUsr = null;
			Map <String, Date> tiemposProm = new HashMap <String, Date>();
			while (resultSet.next()) {
				fecha = resultSet.getDate("Promedio");
				empleadoNomUsr = resultSet.getString("empleadoNomUsr");
				tiemposProm.put(empleadoNomUsr, fecha);
			}
			
			return tiemposProm;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

}
