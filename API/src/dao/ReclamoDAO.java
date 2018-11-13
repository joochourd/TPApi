package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.CantYProdYFalta;
import clases.Facturacion;
import clases.Reclamo;
import clases.TipoReclamo;
import clases.Zona;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ReclamoView;

//
public class ReclamoDAO {

	private static ReclamoDAO instancia;

	private ReclamoDAO() {
	}

	public static ReclamoDAO getInstancia() {
		if (instancia == null) {
			instancia = new ReclamoDAO();
		}
		return instancia;
	}

	// Mar
	public void grabarReclamo(Reclamo reclamo) throws ConexionException, AccesoException {
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
		String SQL = "INSERT INTO reclamos  (" + "fecha," + "descripcion," + "tipo," + "estados," + "clienteDniCuit," + "empleadoNomUsr,"
				+ getSpecificColunmForType((TipoReclamo) reclamo.getTipo(), reclamo) + ") "
				+ "values ('" + reclamo.getFecha() + "','" + reclamo.getDescripcion() + "','"
				+ reclamo.getTipo().toString() + "','" + reclamo.getEstado().toString() + "','" 
				+ reclamo.getClienteDniCuit() + "','" + reclamo.getEmpleadoNombreUsr() + "'," 
				+ getSpecificColunmValueForType((TipoReclamo) reclamo.getTipo(), reclamo) + ");";

		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public void borrarReclamo(int nro) throws ConexionException, AccesoException {
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
		String SQL = ("DELETE FROM reclamos WHERE numero = ('" + nro + "');");
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public void modificarReclamo(Reclamo reclamo) throws ConexionException, AccesoException {
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
		String SQL = ("UPDATE reclamos SET " + "idReclamo = ('" + reclamo.getNumeroReclamo() + "')," + " fecha = ('"
				+ reclamo.getFecha() + "')," + " descripcion = ('" + reclamo.getDescripcion() + "')" + // despues
																										// del
																										// parentesis
																										// y
																										// adentro
																										// de
																										// las
																										// comillas,
																										// va
																										// una
																										// coma?
				" estados= ('" + reclamo.getEstado() + "')" + " tipo = ('" + reclamo.getTipo().toString() + "')"
				+ getSpecificQueryForType((TipoReclamo) reclamo.getTipo(), reclamo) + " WHERE idReclamo = ('"
				+ reclamo.getNumeroReclamo() + "');");

		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public Reclamo obtenerReclamo(int numeroReclamo) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
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
		String SQL = ("SELECT * FROM reclamos WHERE numero =('" + numeroReclamo + "');");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int auxId = resultSet.getInt("idReclamo");
				Date auxFecha = resultSet.getDate("fecha"); // Change this to
															// local date
				String auxDescripcion = resultSet.getString("descripcion");
				String auxEstados = resultSet.getString("estados");
				int auxCliente = resultSet.getInt("clienteDniCuit");
				String auxEmpleadoNumUsr = resultSet.getString("empleadoNumUsr");
				String auxTipo = resultSet.getString("tipo");

				if (auxTipo.equals("zona")) {
					String zona = resultSet.getString("zona");
					Zona reclamoZona = new Zona(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, zona);
					return reclamoZona;

				}
				if (auxTipo.equals("Facturacion")) {
					Date fecha = resultSet.getDate("fechaFacturacion");
					int numFactura = resultSet.getInt("nroFactura");
					Facturacion reclamoFacturacion = new Facturacion(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, fecha.toLocalDate(),
							numFactura);
					return reclamoFacturacion;
				}

				if (auxTipo.equals("CantYProdYFalta")) {
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr);
					return reclamoDeCantidadProductoYfalta;
				}
			}
			return null;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el reclamo");
		}
	}

	public List<Reclamo> obtenerReclamosPorTipo(Enum<TipoReclamo> tipoReclamo)
			throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		String SQL = null;
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
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
		if (tipoReclamo == TipoReclamo.cantYProdYFalta) {
			SQL = "SELECT * FROM reclamos WHERE  tipo = 'falta' and tipo = 'producto' and tipo = 'cantidad';";
		} else {
			SQL = "SELECT * FROM reclamos WHERE  tipo = ('" + tipoReclamo.toString() + "');";
		}
		try {
			
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int auxId = resultSet.getInt("idReclamo");
				Date auxFecha = resultSet.getDate("fecha"); // Change this to
															// local date
				String auxDescripcion = resultSet.getString("descripcion");
				String auxEstados = resultSet.getString("estados");
				int auxCliente = resultSet.getInt("clienteDniCuit");
				String auxEmpleadoNumUsr = resultSet.getString("empleadoNumUsr");
				String auxTipo = resultSet.getString("tipo");

				if (tipoReclamo.toString().equals("zona")) {
					String zona = resultSet.getString("zona");
					Zona reclamoZona = new Zona(auxId, auxFecha.toLocalDate(), auxDescripcion, tipoReclamo, auxCliente,
							auxEmpleadoNumUsr, zona);
					reclamos.add(reclamoZona);

				}
				if (tipoReclamo.toString().equals("facturacion")) {
					Date fecha = resultSet.getDate("fechaFacturacion");
					int numFactura = resultSet.getInt("nroFactura");
					Facturacion reclamoFacturacion = new Facturacion(auxId, auxFecha.toLocalDate(), auxDescripcion,
							tipoReclamo, auxCliente, auxEmpleadoNumUsr, fecha.toLocalDate(), numFactura);
					reclamos.add(reclamoFacturacion);
				}

				if (tipoReclamo.toString().equals("cantYProdYFalta")) {
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, tipoReclamo, auxCliente, auxEmpleadoNumUsr);
					reclamos.add(reclamoDeCantidadProductoYfalta);
				}

			}
			return reclamos;

		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudieron crear los reclamos");
		}
	}

	public ReclamoView reclamoToReclamoView(Reclamo r) {

		return null;

	}

	public List<Reclamo> obtenerReclamoXEmpleado(String nomUsr) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
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
		String SQL = ("SELECT * FROM reclamos WHERE empleadoNomUsr =('" + nomUsr + "');");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int auxId = resultSet.getInt("idReclamo");
				Date auxFecha = resultSet.getDate("fecha");
				String auxDescripcion = resultSet.getString("descripcion");
				String auxEstados = resultSet.getString("estados");
				int auxCliente = resultSet.getInt("clienteDniCuit");
				String auxEmpleadoNumUsr = resultSet.getString("empleadoNumUsr");
				String auxTipo = resultSet.getString("tipo");

				if (auxTipo.equals("zona")) {
					String zona = resultSet.getString("zona");
					Zona reclamoZona = new Zona(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, zona);
					reclamos.add(reclamoZona);

				}
				if (auxTipo.equals("Facturacion")) {
					Date fecha = resultSet.getDate("fechaFacturacion");
					int numFactura = resultSet.getInt("nroFactura");
					Facturacion reclamoFacturacion = new Facturacion(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, fecha.toLocalDate(),
							numFactura);
					reclamos.add(reclamoFacturacion);
				}

				if (auxTipo.equals("CantYProdYFalta")) {
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr);
					reclamos.add(reclamoDeCantidadProductoYfalta);
				}
			}
			return reclamos;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el reclamo");
		}
	}

	public List<Reclamo> obtenerReclamosDeCliente(int numeroCliente) throws AccesoException, ConexionException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
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
		String SQL = ("SELECT * FROM reclamos WHERE  clienteDniCuit = ('" + numeroCliente + "');");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int auxId = resultSet.getInt("idReclamo");
				Date auxFecha = resultSet.getDate("fecha"); // Change this to
															// local date
				String auxDescripcion = resultSet.getString("descripcion");
				String auxEstados = resultSet.getString("estados");
				int auxCliente = resultSet.getInt("clienteDniCuit");
				String auxEmpleadoNumUsr = resultSet.getString("empleadoNumUsr");
				String auxTipo = resultSet.getString("tipo");

				if (auxTipo.equals("zona")) {
					String zona = resultSet.getString("zona");
					Zona reclamoZona = new Zona(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, zona);
					reclamos.add(reclamoZona);

				}
				if (auxTipo.equals("Facturacion")) {
					Date fecha = resultSet.getDate("fechaFacturacion");
					int numFactura = resultSet.getInt("nroFactura");
					Facturacion reclamoFacturacion = new Facturacion(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, fecha.toLocalDate(),
							numFactura);
					reclamos.add(reclamoFacturacion);
				}

				if (auxTipo.equals("CantYProdYFalta")) {
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr);
					reclamos.add(reclamoDeCantidadProductoYfalta);
				}
			}
			return reclamos;

		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudieron crear los reclamos");
		}
	}

	private String getSpecificQueryForType(TipoReclamo tipo, Reclamo reclamo) {

		switch (tipo) {
		case zona:
			return ("zona = " + ((Zona) reclamo).getZona() + "idCompuesto="
					+ ((Zona) reclamo).getIdCompuesto().toString() + "");

		case facturacion:
			return ("fechaFacturacion = " + ((Facturacion) reclamo).getFecha().toString() + ", nroFactura = "
					+ ((Facturacion) reclamo).getNroFactura() + "idCompuesto ="
					+ ((Facturacion) reclamo).getIdCompuesto().toString());

		case cantidad:
		case producto:
		case falta:
			return ("");
		}

		return "";
	}

	private String getSpecificColunmForType(TipoReclamo tipo, Reclamo reclamo) {
		switch (tipo) {
		case zona:
			return "zona";

		case facturacion:
			return ("fechaFacturacion, nroFactura");

		case cantidad:
			return ("");
		case producto:
			return ("");
		case falta:
			return ("");
		default:
			return ("");
		}
	}

	private String getSpecificColunmValueForType(TipoReclamo tipo, Reclamo reclamo) {

		switch (tipo) {
		case zona:
			return "'" + ((Zona) reclamo).getZona() + "'";

		case facturacion:
			return "'" + ((Facturacion) reclamo).getFechaFacturacion().toString() + "'," + ((Facturacion) reclamo).getNroFactura();

		case cantidad:
		case producto:
		case falta:
			return ("");
		default:
			return ("");
		}

	}

}
