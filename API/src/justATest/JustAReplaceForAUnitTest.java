package justATest;

import java.time.LocalDate;

import clases.TipoReclamo;
import clases.Zona;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class JustAReplaceForAUnitTest {

	public static void main(String[] args) throws ConexionException, AccesoException {
		// TODO Auto-generated method stub
		System.out.println("hola");
		Zona zona = new Zona(0, LocalDate.now(), "just a description", TipoReclamo.Zona, 0, "Just a zone", null);
		ReclamoDAO dao = ReclamoDAO.getInstancia();
		dao.grabarReclamo(zona);
		
	}

}
