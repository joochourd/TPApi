package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import clases.Empleado;
import clases.Rol;
import clases.TipoReclamo;
import clases.TipoRol;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.EmpleadoView;

public class EmpleadoDAO {

	private static EmpleadoDAO instancia;

	// private ClienteDAO(){}

	public static EmpleadoDAO getInstancia() {
		if (instancia == null) {
			instancia = new EmpleadoDAO();
		}
		return instancia;
	}

	public void grabarEmpleado(Empleado empleado) throws ConexionException, AccesoException {
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
		String SQL = "INSERT INTO empleados values ('" + empleado.getNomUsr() + "','" + empleado.getPassword() + "','"
				+ empleado.getNombre() + "','" + empleado.getNroLU() + "','" + empleado.getRolOriginal().getId() + "','"
				+ empleado.getRolTemporal().getId() + "');";
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public void borrarEmpleado(String nombreUsr) throws ConexionException, AccesoException {
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
		String SQL = ("DELETE FROM empleados WHERE nombreUsr = ('" + nombreUsr + "');");
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public void modificarEmpleado(Empleado empleado, String nombreAnterior, int dniCuitAnterior)
			throws ConexionException, AccesoException {
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
		String SQL = ("UPDATE empleados SET nombre =('" + empleado.getNombre() + "'), keyword=('"
				+ empleado.getPassword() + "'), nroLu = ('" + empleado.getNroLU() + "'), rolOriginal = ('"
				+ empleado.getRolOriginal() + "'), rolTemporal = ('" + empleado.getRolTemporal()
				+ "') WHERE nombreUsr = ('" + empleado.getNomUsr() + "');");
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public EmpleadoView buscarEmpleado(String nomUsr, String password) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}

		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		// String SQL = ("SELECT * FROM empleados INNER JOIN roles ON
		// roles.idRoles AS idRolOriginal = empleados.idRolOriginal AND
		// roles.idRoles AS idRolTemporal = empleados.idRolTemporal WHERE
		// NombreUsr =('" + nomUsr +"';)");
		String SQL = "select * from empleados where nombreUsr = ('" + nomUsr + "') AND keyword = ('" + password + "')";
		// String SQLidTemp = ("SELECT * FROM empleados INNER JOIN roles ON
		// roles.idRoles = empleados.idRolTemporal WHERE NombreUsr =('" + nomUsr
		// +"');");
		try {
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				Date fechaNac = rs.getDate("fechaNacim");
				String nombreUsr = rs.getString("nombreUsr"); 
				int nroLU = rs.getInt("nroLU");
				int rolOrig = rs.getInt("idRolOriginal");
				int rolTemp = rs.getInt("idRolTemporal");
				EmpleadoView emp = new EmpleadoView(nombre, fechaNac, nombreUsr, nroLU, rolOrig, rolTemp);
				
				return emp;
			}
			return null;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el empleado");
		}
	}

}

