package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.ActualizacionEstado;
import clases.Reclamo;
import clases.TipoReclamo;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ReclamoView;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AdministrarFacturacionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtNuevoEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarFacturacionGUI frame = new AdministrarFacturacionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdministrarFacturacionGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneReclamo = new JLabel("Seleccione Reclamo:");
		lblSeleccioneReclamo.setBounds(10, 11, 103, 14);
		contentPane.add(lblSeleccioneReclamo);
		
		List<ReclamoView> reclamosV = new ArrayList<>();
		String[] datos = new String[reclamosV.size()];
		List<Reclamo> reclamos = null;
		JList listaFacturacion;
		try {
		//Consigo todos los reclamos de facturacion
			reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Facturacion);
		//Paso los reclamos a ReclamosView
			for (int i = 0; i < reclamos.size(); i++) {
				reclamosV.add(reclamos.get(i).reclamoToReclamoView());
			}
		//Los cargo en un array para mostrarlos en la lista
			for (int i = 0; i < reclamosV.size(); i++) {
				datos[i] = reclamosV.get(i).toString();
			}
		//Cargo la lista con los datos
			JList aux = new JList(datos);
			listaFacturacion = aux;
			listaFacturacion.setBounds(123, 11, 309, 26);
			contentPane.add(listaFacturacion);
			
		} catch (ConexionException | AccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel panelAceptar = new JPanel();
		panelAceptar.setBounds(10, 77, 476, 213);
		contentPane.add(panelAceptar);
		panelAceptar.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAceptar.setVisible(true);
			}
		});
		btnAceptar.setBounds(341, 48, 91, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(133, 48, 91, 23);
		contentPane.add(btnCancelar);
		

		
		txtDescripcion = new JTextField();
		txtDescripcion.setText("");
		txtDescripcion.setBounds(181, 42, 285, 54);
		panelAceptar.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblIngreseLaDescripcion = new JLabel("Ingrese la descripcion:");
		lblIngreseLaDescripcion.setBounds(0, 42, 117, 54);
		panelAceptar.add(lblIngreseLaDescripcion);
		
		txtNuevoEstado = new JTextField();
		txtNuevoEstado.setBounds(181, 117, 285, 34);
		panelAceptar.add(txtNuevoEstado);
		txtNuevoEstado.setColumns(10);
		
		JLabel lblIngreseElNuevo = new JLabel("Ingrese el nuevo estado:");
		lblIngreseElNuevo.setBounds(0, 127, 131, 14);
		panelAceptar.add(lblIngreseElNuevo);
		
		JButton btnAceptarNuevoEstado = new JButton("Aceptar");
		btnAceptarNuevoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizacionEstado update = new ActualizacionEstado(listaFacturacion.getSelectedValuesList().get(0), fecha, descripcion, estado, nombreUsrEmpleado)
			}
		});
		btnAceptarNuevoEstado.setBounds(375, 179, 91, 23);
		panelAceptar.add(btnAceptarNuevoEstado);
		
		JButton btnCancelarNuevoEstado = new JButton("Cancelar");
		btnCancelarNuevoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelarNuevoEstado.setBounds(185, 179, 91, 23);
		panelAceptar.add(btnCancelarNuevoEstado);
		


		
	}
}
