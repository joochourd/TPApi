package clases;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import observador.ObservableTablero;


public class Tablero  extends ObservableTablero {
	private List <Reclamo> reclamos;
	private Empleado empleado;
	
	Tablero(Empleado empleado){ //el parametro se lo pasa el sistema
		this.empleado = empleado;
	}
	

	public void registrarReclamoZona(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String zona) throws ConexionException, AccesoException{
		Zona reclamo = new Zona(fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr(), zona);
		reclamo.guardate();
	}

	public void registrarReclamoFacturacion(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, LocalDate fechaFacturacion, int nroFactura) throws ConexionException, AccesoException{
		Facturacion reclamo = new Facturacion(fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr(), fechaFacturacion, nroFactura);
		reclamo.guardate();
	}	

	public void registrarReclamoCompuesto(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit) throws ConexionException, AccesoException{
		Compuesto reclamo = new Compuesto(fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr());
		reclamo.guardate();
	}

	public void administrarReclamoCantProdFaltante(){}

	public void administrarReclamoZona(){}

	public void administrarReclamoFacturacion(){}

	public void realizarConsulta(){}
	 
	public void generarReportes(){}
	
	public void cargarReclamosXTipo(Enum tipo) throws ConexionException, AccesoException{//x tipo reclamo
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosXTipos(tipo);
	}
	
	public void cargarReclamosXCliente(int nroDniCuit) throws ConexionException, AccesoException{
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosXCliente(nroDniCuit);
	}
}
