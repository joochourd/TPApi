package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Compuesto extends Reclamo {
	private static int ids = 0;
	private int idCompuesto;
	private List <Simple>simples;

	public Compuesto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		simples = new ArrayList<Simple>();
		this.idCompuesto = ids ++;
		// TODO Auto-generated constructor stub
	}
	
	public Compuesto(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		simples = new ArrayList<Simple>();
		this.idCompuesto = ids ++;
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public void addReclamo(Simple reclamosSimple) throws ConexionException, AccesoException {
		this.simples.add(reclamosSimple);
		//this.guardate();
	}
	
	public String chequearEstado(){
		for (Simple simple : simples) {
			//simple.getEstado()
		}
		
		return ;}
	
	public void removeReclamo(int idReclamo) throws ConexionException, AccesoException{
		Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamo(idReclamo);
		this.simples.remove(reclamo);
	}

	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}

}
