package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.internal.matchers.Each;

import dao.ReclamoDAO;
import dao.ReportesDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import observador.ObservableTablero;
import view.ProductoView;
import view.ReclamoView;

//
public class Tablero  extends ObservableTablero {
	private List <Reclamo> reclamos;
	private Empleado empleado;
	
	
	Tablero(Empleado empleado) throws ConexionException, AccesoException{ 
		this.reclamos = new ArrayList<>();
		this.empleado = empleado;
		
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
	
	public void registrarReclamoCantProdFalta(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, ProductoView prod, int cant) throws ConexionException, AccesoException{
		Producto p = new Producto(prod.getCodigoPublicacion(), prod.getTitulo(), prod.getDescripcion(), prod.getPrecio());
		CantYProdYFalta reclamo = new CantYProdYFalta(fecha, descripcion, tipo, clienteDniCuit, empleado.getNomUsr(), p, cant);
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
	

	public List<ReclamoView> getReclamosCliente(int numeroCliente) throws AccesoException, ConexionException{
		List<Reclamo> rec = ReclamoDAO.getInstancia().obtenerReclamosDeCliente(numeroCliente);
		List<ReclamoView> recV = new ArrayList<>();
		for(int i = 0; i<rec.size(); i++){
			recV.add(rec.get(i).toView());
		}
		return recV;
	}
	
	public Reclamo getReclamo(int nroReclamo) throws ConexionException, AccesoException {//Ver Si se necesita pasar a View
		return ReclamoDAO.getInstancia().obtenerReclamo(nroReclamo);
	}
	
	public List<ReclamoView>getReclamostipo(Enum<TipoReclamo> tipo) throws ConexionException, AccesoException{
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(tipo);
		List<ReclamoView> recV = new ArrayList<>();
		for(int i = 0; i<this.reclamos.size(); i++){
			recV.add(this.reclamos.get(i).toView());
		}
		return recV;
	}

	
	public void reclamosEmpleadoLogueado() throws ConexionException, AccesoException{//NO se usa parece!
		this.reclamos = ReclamoDAO.getInstancia().obtenerReclamoXEmpleado(this.empleado.getNomUsr());
		
	}


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
	
}
