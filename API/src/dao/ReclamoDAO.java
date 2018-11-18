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
import clases.Producto;
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
		
		String SQL = "INSERT INTO reclamos  (" + "fecha," + "descripcion," + "tipo," + "estados," + "clienteDniCuit," + "empleadoNomUsr"
				+ getSpecificColunmForType((TipoReclamo) reclamo.getTipo(), reclamo) + ") "
				+ "values ('" + reclamo.getFecha() + "','" + reclamo.getDescripcion() + "','"
				+ reclamo.getTipo().toString() + "','" + reclamo.getEstado().toString() + "','" 
				+ reclamo.getClienteDniCuit() + "','" + reclamo.getEmpleadoNombreUsr() + "'" 
				+ getSpecificColunmValueForType((TipoReclamo) reclamo.getTipo(), reclamo) + ");";
		
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
		if(reclamo.getTipo().equals(TipoReclamo.cantidad) || reclamo.getTipo().equals(TipoReclamo.producto) || reclamo.getTipo().equals(TipoReclamo.falta)){
			crearProductosReclamos((CantYProdYFalta)reclamo);
		}
	}

	private void crearProductosReclamos(CantYProdYFalta reclamo) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		int id = 0;
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

		String obt = "select idReclamo from reclamos where fecha='" + reclamo.getFecha() + "' and descripcion='"
				+ reclamo.getDescripcion() + "' and tipo='" + reclamo.getTipo() + "' and clienteDniCuit="
				+ reclamo.getClienteDniCuit() + " and empleadoNomUsr='" + reclamo.getEmpleadoNombreUsr() + "';";

		try {
			resultSet = stmt.executeQuery(obt);
			while (resultSet.next()) {
				id = resultSet.getInt("idReclamo");
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo encontrar ID reclamo...");
		}
		
		if (id != 0) {
			String SQL = "insert into productosReclamos (idReclamo, productoCodigoPublicacion, Cantidad) " + "values ("
					+ id + "," + reclamo.getProducto().getCodigoPublicacion() + "," + reclamo.getCantidad() + ");";

			try {
				stmt.execute(SQL);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				throw new AccesoException("Error de escritura");
			}
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
		String SQL = ("UPDATE reclamos SET " + " fecha = ('" + reclamo.getFecha() + "')," + " descripcion = ('"
				+ reclamo.getDescripcion() + "')," + " estados= ('" + reclamo.getEstado() + "')," 
				+ " empleadoNomUsr = '" + reclamo.getEmpleadoNombreUsr() + "' "
				+ " WHERE idReclamo = " + reclamo.getNumeroReclamo() + ";");

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
		String SQL = ("SELECT * FROM (reclamos JOIN  prudctosReclamos on recamosl.idReclamo = prductosReclamos.idReclamo)Join productos on productosReclamos.productoCodigoPublicacion = productos.codigoPublicacion WHERE idReclamo = " + numeroReclamo + ";");
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int auxId = resultSet.getInt("idReclamo");
				Date auxFecha = resultSet.getDate("fecha"); // Change this to
															// local date
				String auxDescripcion = resultSet.getString("descripcion");
				String auxEstados = resultSet.getString("estados");
				int auxCliente = resultSet.getInt("clienteDniCuit");
				String auxEmpleadoNumUsr = resultSet.getString("empleadoNomUsr");
				String auxTipo = resultSet.getString("tipo");

				if (auxTipo.equals("zona")) {
					String zona = resultSet.getString("zona");
					Zona reclamoZona = new Zona(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, zona);
					return reclamoZona;

				}
				if (auxTipo.equals("facturacion")) {
					Date fecha = resultSet.getDate("fechaFacturacion");
					int numFactura = resultSet.getInt("nroFactura");
					Facturacion reclamoFacturacion = new Facturacion(auxId, auxFecha.toLocalDate(), auxDescripcion,
							TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, fecha.toLocalDate(),
							numFactura);
					return reclamoFacturacion;
				}

				if (auxTipo.equals("cantidad") || auxTipo.equals("producto") || auxTipo.equals("falta")) {
					String titulo = resultSet.getString("titulo");
					String descripcion = resultSet.getString("descripcion");
					float precio = resultSet.getInt("precio");
					int cantidad = resultSet.getInt("cantidad");
					Producto producto = new Producto(titulo, descripcion, precio);
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, producto, cantidad);
					
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
		
		if(tipoReclamo.equals(TipoReclamo.cantYProdYFalta)){
			SQL = "SELECT * FROM reclamos WHERE  tipo in ('cantidad', 'producto', 'falta');";
		}
		else{
		SQL = "SELECT * FROM reclamos WHERE  tipo = '" + tipoReclamo.toString() + "';";
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
				String auxEmpleadoNumUsr = resultSet.getString("empleadoNomUsr");
				String tipoAux = resultSet.getString("tipo");

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

				if (tipoAux.equals("cantidad") || tipoAux.equals("producto") || tipoAux.equals("falta")) {
					Producto productoAux = obtenerProducto(auxId);
					int cantAux = obtenerCantidad(auxId);
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(), auxDescripcion, TipoReclamo.valueOf(tipoAux), auxCliente, auxEmpleadoNumUsr, productoAux, cantAux);
					reclamos.add(reclamoDeCantidadProductoYfalta);
				}
				if(tipoReclamo.toString().equals("compuesto")){}

			}
			return reclamos;

		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudieron crear los reclamos");
		}
	}

	private int obtenerCantidad(int auxId) throws AccesoException, ConexionException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		int cantidad = 0;
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
		String SQL = "select cantidad from productosReclamos where idReclamo = " +auxId+ ";";
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				cantidad = resultSet.getInt("cantidad");
				}
			return cantidad;
		}
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo obtener la cantidad");
		}
	}
			

	private Producto obtenerProducto(int auxId) throws ConexionException, AccesoException {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		Producto prod = null;
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
		String SQL = "select * from productos where codigoPublicacion = "
				+ "(select productoCodigoPublicacion from productosReclamos " + "where idReclamo = " + auxId + ");";
		try {
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				int codPublicacion = resultSet.getInt("codigoPublicacion");
				String titulo = resultSet.getString("titulo");
				String descripcion = resultSet.getString("descripcion");
				float precio = resultSet.getFloat("precio");
				prod = new Producto(codPublicacion, titulo, descripcion, precio);

			}
			return prod;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("No se pudo crear el producto");
		}
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
		
		
		
		String SQL = ("SELECT * FROM (reclamos JOIN  prudctosReclamos on recamosl.idReclamo = prductosReclamos.idReclamo)Join productos on productosReclamos.productoCodigoPublicacion = productos.codigoPublicacion WHERE empleadoNomUsr =('" + nomUsr + "');");
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
					String titulo = resultSet.getString("titulo");
					String descripcion = resultSet.getString("descripcion");
					float precio = resultSet.getInt("precio");
					Producto producto = new Producto(titulo, descripcion, precio);
					int cantidad = resultSet.getInt("cantidad");
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, producto, cantidad);
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
		String SQL = ("SELECT * FROM (reclamos JOIN  prudctosReclamos on recamosl.idReclamo = prductosReclamos.idReclamo)Join productos on productosReclamos.productoCodigoPublicacion = productos.codigoPublicacion WHERE  clienteDniCuit = ('" + numeroCliente + "');");
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
					String titulo = resultSet.getString("titulo");
					String descripcion = resultSet.getString("descripcion");
					float precio = resultSet.getInt("precio");
					Producto producto = new Producto(titulo, descripcion, precio);
					int cantidad = resultSet.getInt("cantidad");
					CantYProdYFalta reclamoDeCantidadProductoYfalta = new CantYProdYFalta(auxId, auxFecha.toLocalDate(),
							auxDescripcion, TipoReclamo.valueOf(auxTipo), auxCliente, auxEmpleadoNumUsr, producto, cantidad);
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
			return ", zona";

		case facturacion:
			return (", fechaFacturacion, nroFactura");

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
			return ", '" + ((Zona) reclamo).getZona() + "'";

		case facturacion:
			return ", '" + ((Facturacion) reclamo).getFechaFacturacion().toString() + "'," + ((Facturacion) reclamo).getNroFactura();

		case cantidad:
		case producto:
		case falta:
			return ("");
		default:
			return ("");
		}

	}

}
