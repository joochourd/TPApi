package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Compuesto extends Reclamo {
	private List <Simple>simples;

	public Compuesto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		simples = new ArrayList<Simple>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public void addReclamo(Simple reclamosSimple) throws ConexionException, AccesoException {
		this.simples.add(reclamosSimple);
		this.guardate();
	}

	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}

}
