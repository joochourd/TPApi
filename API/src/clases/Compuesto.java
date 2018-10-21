package clases;

import java.time.LocalDate;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Compuesto extends Reclamo {

	public Compuesto(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public void addReclamo(int numeroReclamo) {
		Simple simple = Sistema.getInstance().alta;
	}

	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}

}
