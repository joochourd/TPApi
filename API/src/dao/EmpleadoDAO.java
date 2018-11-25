package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public void modificarEmpleado(Empleado empleado)
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

	public Empleado buscarEmpleado(String nomUsr, String password) throws ConexionException, AccesoException {
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
		
		String SQL = "select * from empleados, "
				+ "(select descripcion as desRolOrig from empleados inner join roles on empleados.idRolOriginal = roles.idRoles where nombreUsr = ('" + nomUsr + "')) a,"
				+ "(select descripcion as desRolTemp from empleados inner join roles on empleados.idRolTemporal = roles.idRoles where nombreUsr = ('" + nomUsr + "')) b "
				+ "where nombreUsr = ('" + nomUsr + "') AND keyword = ('" + password + "')";
		
		try {
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				Date fechaNac = rs.getDate("fechaNacim");
				//String nombreUsr = rs.getString("nombreUsr");
				//String pass = rs.getString("keyword");
				int nroLU = rs.getInt("nroLU");
				int idRolOrig = rs.getInt("idRolOriginal");
				int idRolTemp = rs.getInt("idRolTemporal");
				String descripcionRolOrig = rs.getString("desRolOrig");
				String descripcionRolTemp = rs.getString("desRolTemp");
				Empleado emp = new Empleado(nombre, fechaNac, password, nomUsr, nroLU, new Rol (idRolOrig, descripcionRolOrig), new Rol (idRolTemp, descripcionRolTemp));
				
				return emp;
			}
			return null;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el empleado");
		}
	}
	
	public List<Empleado> buscarTodosLosEmpleados() throws AccesoException, ConexionException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Empleado> empleados = new ArrayList<>();
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
		
		String SQL = "select * from empleados inner join (select a.nombreUsr, descripcionOriginal, descripcionTemporal "
				+ "from (select nombreUsr, descripcion as descripcionOriginal from empleados inner join roles on idRolOriginal = idRoles)a "
				+ "inner join (select nombreUsr, descripcion as descripcionTemporal from empleados inner join roles on idRolTemporal = idRoles)b "
				+ "on a.nombreUsr = b.nombreUsr)x on empleados.nombreUsr = x.nombreUsr";
		
		try {
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				Date fechaNac = rs.getDate("fechaNacim");
				String nombreUsr = rs.getString("nombreUsr");
				String pass = rs.getString("keyword");
				int nroLU = rs.getInt("nroLU");
				int idRolOrig = rs.getInt("idRolOriginal");
				int idRolTemp = rs.getInt("idRolTemporal");
				String descripcionRolOrig = rs.getString("descripcionOriginal");
				String descripcionRolTemp = rs.getString("descripcionTemporal");
				Empleado emp = new Empleado(nombre, fechaNac, pass, nombreUsr, nroLU, new Rol (idRolOrig, descripcionRolOrig), new Rol (idRolTemp, descripcionRolTemp));
				
				empleados.add(emp);
			}
			return empleados;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo encontrar empleados");
		}
	}

}

