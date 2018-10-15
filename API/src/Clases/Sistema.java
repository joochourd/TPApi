package Clases;

import java.util.Date;
import java.util.List;

import dao.ClienteDAO;

public class Sistema {
	public void modificarCliente(int dniCuitAnterior, String nombreAnterior, int dniCuit, String nombre, String domicilio, String telefono, String mail){
		Cliente cliente = new Cliente();
		cliente.modificarCliente(dniCuit, nombre, domicilio, telefono, mail);
		ClienteDAO cDao = new ClienteDAO();
		cDao.modificarCliente(cliente, nombreAnterior, dniCuitAnterior);
	}
	public Cliente altaCliente(int dniCuit, String nombre, String domicilio, String telefono, String mail){
		Cliente cliente = new Cliente();
		cliente.setDniCuit(dniCuit);
		cliente.setDomicilio(domicilio);
		cliente.setMail(mail);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);
		ClienteDAO cDao= new ClienteDAO();
		cDao.grabarCliente(cliente);
	}
	public void bajaCliente(int dniCuit, String nombre){
		ClienteDAO cDao= new ClienteDAO();
		cDao.borrarCliente(dniCuit, nombre);
	}
	public Cliente buscarCliente (int dniCuit){
		ClienteDAO cDao = new ClienteDAO();
		cDao.buscarCliente(dniCuit);
	}
	public Producto altaProducto(String titulo, String descripcion, float precio){}
	public void modificarProducto(int codigoPublicacion,String titulo, String descripcion, float precio){}
	public void bajaProducto(int codigoPublicacion){}
	public Producto buscarProducto(List<String> tituloProd){}
	public Reclamo iniciarReclamo(int dniCuit){}
	public List <String> reclamoCantProdFalta(List<int> cantidad, String tituloProd){}	
	public Reclamo registrarReclamoZona(String zona){}
	public Reclamo registrarReclamoFacturacion(Date fecha, int nroFactura){}	
	public void administrarReclamoCantProdFaltante(){}
	public void administrarReclamoZona(){}
	public void administrarReclamoFacturacion(){}
	public void realizarConsulta(){}
	public void login(String usuario, String contraseña){}
	public List<Reclamo> getReclamos(Cliente cliente){}
	public void registrarReclamoCompuesto(){}
}	