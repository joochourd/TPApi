
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import clases.Cliente;
import clases.Facturacion;
import clases.Reclamo;
import clases.TipoReclamo;
import clases.Zona;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ReportesDAO {



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
	}

	public void cantidadReclamosTratadosPorMes(String numeroMes) throws ConexionException, AccesoException {
		
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
		String SQL = ("SELECT fechaFacturacion FROM reclamos WHERE MONTH(Date) = " + numeroMes + 
		";");
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public void tiempoPromedioRespuestaReclamosPorResponsable(Reclamo reclamo) throws ConexionException, AccesoException {
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
		String SQL = "SELECT e.empleados DATEDIFF(month, r.fecha, r.fechaFinalizacion) AS DateDiff;\n" + 
				"From \n" + 
				" 	empleados e LEFT JOIN\n" + 
				"    reclamos r ON r.empleadoNomUsr = e.nombreUsr\n" + 
				"GROUP BY DateDiff;";
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public void buscarReclamo(int nro, TipoReclamo tipo) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
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
		String SQL = ("SELECT * FROM reclamos WHERE numero =('" + nro + "') or tipo = ('" + tipo + "');");
		try {
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {

			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");// Rellenar msj
		}
	}



}
