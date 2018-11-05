package justATest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import clases.TipoReclamo;
import clases.Zona;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

class TestSQLQueries {

	@Test
	void test() throws ConexionException, AccesoException {
		
		Zona zona = new Zona(0, LocalDate.now(), "just a description", TipoReclamo.Zona, 0, "Just a zone", null);
		ReclamoDAO dao = new ReclamoDAO();
		dao.grabarReclamo(zona);
		fail("Not yet implemented");
	}

	private void fail(String string) {
		// TODO Auto-generated method stub
		
	}

}
