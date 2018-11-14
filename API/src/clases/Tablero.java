package clases;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.internal.matchers.Each;

import dao.ReclamoDAO;
import dao.ReportesDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import observador.ObservableTablero;
import view.ReclamoView;

//
public class Tablero  extends ObservableTablero {
	private List <Reclamo> reclamos;
	private Empleado empleado;
	
	
	Tablero(Empleado empleado) throws ConexionException, AccesoException{ //cuando usr se loguea, se traen los reclamos correspondietes a su rolTemporal
		this.empleado = empleado;
		if (empleado.getRolTemporal().getTipoReclamo() != null)
			this.reclamos = this.getReclamostipo(empleado.getRolTemporal().getTipoReclamo());
	}
	

	public void registrarReclamoZona(LocalDate fecha, String descripcion, int clienteDniCuit, String zona) throws ConexionException, AccesoException{
		Zona reclamo = new Zona(fecha, descripcion, TipoReclamo.zona, clienteDniCuit, empleado.getNomUsr(), zona);
		reclamo.guardate();
		this.reclamos.add(reclamo);
		this.updateObserver(reclamo);
	}

	public void registrarReclamoFacturacion(LocalDate fecha, String descripcion, int clienteDniCuit, LocalDate fechaFacturacion, int nroFactura) throws ConexionException, AccesoException{
		Facturacion reclamo = new Facturacion(fecha, descripcion, TipoReclamo.facturacion, clienteDniCuit, empleado.getNomUsr(), fechaFacturacion, nroFactura);
		reclamo.guardate();
		this.reclamos.add(reclamo);
		this.updateObserver(reclamo);
	}
	
	public void registrarReclamoCantProdFalta(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, LocalDate fechaFacturacion, int nroFactura) throws ConexionException, AccesoException{
		CantYProdYFalta reclamo = new CantYProdYFalta(fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr());
		reclamo.guardate();
		this.reclamos.add(reclamo);
		this.updateObserver(reclamo);
	}	

	public void registrarReclamoCompuesto(LocalDate fecha, String descripcion, int clienteDniCuit) throws ConexionException, AccesoException{
		Compuesto reclamo = new Compuesto(fecha, descripcion, TipoReclamo.compuesto, clienteDniCuit, empleado.getNomUsr());
		reclamo.guardate();
		this.reclamos.add(reclamo);
		this.updateObserver(reclamo);
	}


	public void tratarReclamo(int idReclamo, Estados estado, String descripcion) throws ConexionException, AccesoException{
		
		Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamo(idReclamo);
		if(reclamo != null){
			reclamo.guardarActuralizacionEstado();
			reclamo.setEstado(estado);
			reclamo.setDescripcion(descripcion);
			//reclamo.setFecha(LocalDate.now());
			reclamo.modificate();
			this.updateObserver(reclamo);
		}
		else{
			System.out.println("Reclamo not found 404...");
		}
	}
	
	//ver!
	public List<Reclamo> getReclamosCliente(int numeroCliente) throws AccesoException, ConexionException{
		return ReclamoDAO.getInstancia().obtenerReclamosDeCliente(numeroCliente);
	}
	
	public Reclamo getReclamo(int nroReclamo) throws ConexionException, AccesoException {
		return ReclamoDAO.getInstancia().obtenerReclamo(nroReclamo);
	}
	
	public List<Reclamo>getReclamostipo(Enum<TipoReclamo> tipo) throws ConexionException, AccesoException{
		return ReclamoDAO.getInstancia().obtenerReclamosPorTipo(tipo);
	}
	
	public List<Reclamo>getReclamoCliente(int numeroCliente)throws ConexionException, AccesoException{
		return ReclamoDAO.getInstancia().obtenerReclamosDeCliente(numeroCliente);
	}
	
	public void reclamosEmpleadoLogueado() throws ConexionException, AccesoException{
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamoXEmpleado(this.empleado.getNomUsr());
		
	}
// termina ver!!

	public String realizarConsultaReclamo(int idReclamo) throws ConexionException, AccesoException{
		Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamo(idReclamo);
		return  "Estado: " + reclamo.getEstado() + "\n" + "Descripcion: " + reclamo.getDescripcion();
	}
	
	
	public List<ReclamoView> getReclamosView(){
		List<ReclamoView> views = null;
		for (Reclamo reclamo : this.reclamos){
			views.add(reclamo.toView());
		}
		return views;
	}
	 
	/* Reportes*/
	
	public List<Cliente> clientesConMasReclamosPorMes(int mes) throws ConexionException, AccesoException{
		return ReportesDAO.getInstancia().clientesConMasReclamosPorMes(mes);
	}
	
	public int cantidadReclamosTratadosPorMes(int numeroMes) throws ConexionException, AccesoException{
		return ReportesDAO.getInstancia().cantidadReclamosTratadosPorMes(numeroMes);
	}
	
	public Map tiempoPromedioRespuestaReclamosPorResponsable(Empleado empl) throws ConexionException, AccesoException{
		return ReportesDAO.getInstancia().tiempoPromedioRespuestaReclamosPorResponsable(empl);
	}
	
	/*public void cargarReclamosXTipo(Enum tipo) throws ConexionException, AccesoException{//x tipo reclamo
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosXTipos(tipo);
	}ESto se hace en el contructor*/
	
	/*public void cargarReclamosXCliente(int nroDniCuit) throws ConexionException, AccesoException{
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosXCliente(nroDniCuit);
	} ESTO NO DEBERIA IR EN TABLERO (creo)*/
}
