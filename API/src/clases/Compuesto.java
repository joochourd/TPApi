package clases;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Compuesto extends Reclamo {
	private String idCompuesto;
	private List <Simple>simples;

	public Compuesto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, Enum<Estados> estado, int clienteDniCuit, String empleadoNombreUsr, String idCompuesto) {
		super(numeroReclamo, fecha, descripcion, tipo, estado, clienteDniCuit, empleadoNombreUsr, idCompuesto);
		this.idCompuesto = idCompuesto;
		simples = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public Compuesto(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		simples = new ArrayList<>();
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
		Enum<Estados> e; 
		String rta = null;
		for (Simple simple : this.simples) {
			e = simple.getEstado();
			switch (e.toString()) {
			case "Registrado":
				rta = "Registrado";
				return rta;
			case "En Tratamiento":
				if (!rta.equalsIgnoreCase("Registrado")) {
					rta = "En Tratamiento";
				}
				
				break;
			case "Solucionado":
				if (!rta.equalsIgnoreCase("Registrado") && !rta.equalsIgnoreCase("en tratamiento")) {
					rta = "Solucionado";
				}
				break;
			case "Cerrado":
				if (!rta.equalsIgnoreCase("Registrado") && !rta.equalsIgnoreCase("en tratamiento")) {
					rta = "Cerrado";
				}
				break;
			}
		}
		return rta;
	}
	
	public void removeReclamo(int idReclamo) throws ConexionException, AccesoException{
		Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamo(idReclamo);
		this.simples.remove(reclamo);
	}
	
	

	public String getIdCompuesto() {
		return idCompuesto;
	}

	public void setIdCompuesto(String idCompuesto) {
		this.idCompuesto = idCompuesto;
	}

	public List<Simple> getSimples() {
		return simples;
	}

	public void setSimples(List<Simple> simples) {
		this.simples = simples;
	}

	@Override
	protected void guardate() throws ConexionException, AccesoException, SQLException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}

}