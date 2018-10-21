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

public class ReclamoDAO {
	private static ReclamoDAO instancia;

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
		String SQL = "INSERT INTO reclamos  (" +
			"idReclamo,"+
			"fecha,"+
			"descripcion,"+
			"estados"+
			getSpecificColunmForType((TipoReclamo) reclamo.getTipo(), reclamo)+
			" values ('" +
			reclamo.getNumeroReclamo() + "','" +
			reclamo.getFecha() + "','"+
			reclamo.getDescripcion() + "','" +
			reclamo.getEstado().toString() +
			getSpecificColunmValueForType((TipoReclamo) reclamo.getTipo(), reclamo)+
			"');";
		
		
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
		String SQL = ("UPDATE reclamos SET " +
			"idReclamo = ('" + reclamo.getNumeroReclamo() + "')," +
			" fecha = ('"+ reclamo.getFecha() + "')," +
			" descripcion = ('"+ reclamo.getDescripcion() +"')" +
			" estados= ('"+ reclamo.getEstado() + "')" +
			" tipo = ('"+ reclamo.getTipo().toString() +"')" +
			getSpecificQueryForType((TipoReclamo) reclamo.getTipo(), reclamo)+
			" WHERE idReclamo = ('" +reclamo.getNumeroReclamo()+ "');");

		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("Error de escritura");
		}
	}

	public Reclamo buscarReclamo(int nro, TipoReclamo tipo) throws ConexionException, AccesoException {
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
		return
	}
	
	public List<Reclamo> obtenerReclamosXTipos(Enum tipo) throws ConexionException, AccesoException {
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
		String SQL = ("SELECT * FROM reclamos WHERE tipo = ('" + tipo + "');");
		try {
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {

			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");// Rellenar msj
		}
		return
	}
	
	public List<Reclamo> obtenerReclamosXCliente(int nroDniCuit) throws ConexionException, AccesoException {
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
		String SQL = ("SELECT * FROM reclamos WHERE clienteDniCuit = ('" + nroDniCuit + "');");
		try {
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {

			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			throw new AccesoException("");// Rellenar msj
		}
		return
	}

	private String getSpecificQueryForType(TipoReclamo tipo, Reclamo reclamo) {

		switch (tipo) {
		case Zona:
			return ("zona = "+((Zona) reclamo).getZona()+"");
			
		case Facturacion:
			return ("fechaFacturacion = "+((Facturacion) reclamo).getFecha().toString() +", nroFactura = "+ ((Facturacion) reclamo).getNroFactura());
			
		case Cantidad:
		case Producto:
		case Falta:
			return ("");
		}

		return "";
	}
	private String getSpecificColunmForType(TipoReclamo tipo, Reclamo reclamo) {

		switch (tipo) {
		case Zona:
			return (", zona");
			
		case Facturacion:
			return (",  fechaFacturacion, nroFactura");
			
		case Cantidad:
		case Producto:
		case Falta:
			return ("");
		}

		return "";
	}
	
	private String getSpecificColunmValueForType(TipoReclamo tipo, Reclamo reclamo) {

		switch (tipo) {
		case Zona:
			return ((Zona) reclamo).getZona();
			
		case Facturacion:
			return ((Facturacion) reclamo).getFecha().toString() + ((Facturacion) reclamo).getNroFactura();
			
		case Cantidad:
		case Producto:
		case Falta:
			return ("");
		}

		return "";
	}

}
