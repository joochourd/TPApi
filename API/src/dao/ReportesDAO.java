
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clases.Cliente;
import clases.Empleado;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ReportesDAO {

//falta hacer el while del buscarReclamo y verificar el tipo de dato que devuelven los metodos aca 

	private static ReportesDAO instancia;

	public static ReportesDAO getInstancia() {
		if (instancia == null) {
			instancia = new ReportesDAO();
		}
		return instancia;
	}

	public List<Cliente> clientesConMasReclamosPorMes(int mes) throws ConexionException, AccesoException {
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
		String SQL = "SELECT TOP 5 c.nombre, c.domicilio, c.telefono, c.dniCuit, c.email, COUNT(r.idReclamo) AS cantidadReclamos "
				+ "FROM clientes c LEFT JOINreclamos r ON r.clienteDniCuit = c.dniCuit WHERE MONTH(r.fecha) = " 
				+ mes + " GROUP BY c.nombre, c.domicilio, c.telefono, c.dniCuit, c.email ORDER BY cantidadReclamos;";
		
		
		try {
			ResultSet resultSet = stmt.executeQuery(SQL);
			List<Cliente> clientes = new ArrayList<>();
			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				String domicilio= resultSet.getString("domicilio");
				String telefono= resultSet.getString("telefono");
				String mail= resultSet.getString("mail");
				int dniCuit= resultSet.getInt("dniCuit");
				Cliente cliente = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
				clientes.add(cliente);
			}
			return clientes;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public int cantidadReclamosTratadosPorMes(int numeroMes) throws ConexionException, AccesoException {
		
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
		String SQL = ("SELECT COUNT(r.idReclamo) as cantidadReclamo FROM reclamos r WHERE MONTH(r.fecha) = " + numeroMes + " AND not ESTADOS = 'cerrado'"+
		";");
		try {
			ResultSet resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				return resultSet.getInt("cantidadReclamo");
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
