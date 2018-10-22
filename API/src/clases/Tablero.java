package clases;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import observador.ObservableTablero;

//
public class Tablero  extends ObservableTablero {
	private List <Reclamo> reclamos;
	private Empleado empleado;
	
	
	Tablero(Empleado empleado) throws ConexionException, AccesoException{ //el parametro se lo pasa el sistema
		this.empleado = empleado;
		this.reclamos = this.getReclamostipo(this.empleado.getRolInstantaneo().getTipoReclamo());
	}
	

	public void registrarReclamoZona(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String zona) throws ConexionException, AccesoException{
		Zona reclamo = new Zona(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr(), zona);
		reclamo.guardate();
	}

	public void registrarReclamoFacturacion(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, LocalDate fechaFacturacion, int nroFactura) throws ConexionException, AccesoException{
		Facturacion reclamo = new Facturacion(numeroReclamo,fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr(), fechaFacturacion, nroFactura);
		reclamo.guardate();
	}
	public void registrarReclamoCantProdFalta(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, LocalDate fechaFacturacion, int nroFactura) throws ConexionException, AccesoException{
		CantYProdYFalta reclamo = new CantYProdYFalta(numeroReclamo,fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr());
		reclamo.guardate();
	}	

	public void registrarReclamoCompuesto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit) throws ConexionException, AccesoException{
		Compuesto reclamo = new Compuesto(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr());
		reclamo.guardate();
	}


	public void tratarReclamo(int idReclamo, Estados estado, String descripcion) throws ConexionException, AccesoException{
		
		Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamo(idReclamo);
		if(reclamo != null){
			reclamo.guardarActuralizacionEstado();
			reclamo.setEstado(estado);
			reclamo.setDescripcion(descripcion);
			reclamo.setFecha(LocalDate.now());
			reclamo.modificate();
		}
		else{
			System.out.println("Reclamo not found 404...");
		}
	}
	
	public List<Reclamo> getReclamosCliente(int numeroCliente) throws AccesoException, ConexionException{
		return ReclamoDAO.getInstancia().obtenerReclamosDeCliente(numeroCliente);
	}
	
	public Reclamo getReclamo(int nroReclamo) throws ConexionException, AccesoException {
		return ReclamoDAO.getInstancia().obtenerReclamo(nroReclamo);
	}
	
	public List<Reclamo>getReclamostipo(Enum<TipoReclamo> enum1) throws ConexionException, AccesoException{
		return ReclamoDAO.getInstancia().obtenerReclamosPorTipo(enum1);
	}
	
	public List<Reclamo>getReclamoCliente(int numeroCliente)throws ConexionException, AccesoException{
		return ReclamoDAO.getInstancia().obtenerReclamosDeCliente(numeroCliente);
	}


	public void realizarConsulta(){}
	 
	public void generarReportes(){}
	
	/*public void cargarReclamosXTipo(Enum tipo) throws ConexionException, AccesoException{//x tipo reclamo
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosXTipos(tipo);
	}ESto se hace en el contructor*/
	
	/*public void cargarReclamosXCliente(int nroDniCuit) throws ConexionException, AccesoException{
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosXCliente(nroDniCuit);
	} ESTO NO DEBERIA IR EN TABLERO (creo)*/
}
