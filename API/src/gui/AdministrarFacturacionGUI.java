package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.ActualizacionEstado;
import clases.Estados;
import clases.Reclamo;
import clases.Sistema;
import clases.TipoReclamo;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ReclamoView;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AdministrarFacturacionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtNuevoEstado;
	static volatile JList listaFacturacion = null;

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
		lblSeleccioneReclamo.setBounds(4, 11, 126, 14);
		contentPane.add(lblSeleccioneReclamo);
		
		List<ReclamoView> reclamosV = new ArrayList<>();
		String[] datos = new String[reclamosV.size()];
		List<Reclamo> reclamos = null;
		
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
			listaFacturacion.setBounds(142, 9, 309, 18);
			contentPane.add(listaFacturacion);
			
		} catch (ConexionException | AccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel panelAceptar = new JPanel();
		panelAceptar.setBounds(10, 77, 476, 213);
		contentPane.add(panelAceptar);
		panelAceptar.setLayout(null);
		panelAceptar.setVisible(false);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(360, 50, 91, 23);
		contentPane.add(btnCancelar);
		

		
		txtDescripcion = new JTextField();
		txtDescripcion.setText("");
		txtDescripcion.setBounds(181, 42, 285, 54);
		panelAceptar.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblIngreseLaDescripcion = new JLabel("Ingrese la descripcion:");
		lblIngreseLaDescripcion.setBounds(0, 42, 141, 54);
		panelAceptar.add(lblIngreseLaDescripcion);
		
		txtNuevoEstado = new JTextField();
		txtNuevoEstado.setBounds(181, 117, 285, 34);
		panelAceptar.add(txtNuevoEstado);
		txtNuevoEstado.setColumns(10);
		
		JLabel lblIngreseElNuevo = new JLabel("Ingrese el nuevo estado:");
		lblIngreseElNuevo.setBounds(0, 127, 151, 14);
		panelAceptar.add(lblIngreseElNuevo);
		
		JButton btnAceptarNuevoEstado = new JButton("Aceptar");
		btnAceptarNuevoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List <Reclamo> selection = listaFacturacion.getSelectedValuesList();
				//ActualizacionEstado update = new ActualizacionEstado( selection.get(0).getNumeroReclamo(), LocalDate.now(), txtDescripcion.getText(), Estados.valueOf(txtNuevoEstado.getText()), Sistema.getInstance().getEmpleadoActual().getNomUsr());
				try {
					Sistema.getInstance().getTablero().tratarReclamo(selection.get(0).getNumeroReclamo(), Estados.valueOf(txtNuevoEstado.getText()), txtDescripcion.getText());
				} catch (ConexionException | AccesoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptarNuevoEstado.setBounds(137, 164, 91, 23);
		panelAceptar.add(btnAceptarNuevoEstado);
		
		JButton btnCancelarNuevoEstado = new JButton("Cancelar");
		btnCancelarNuevoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelarNuevoEstado.setBounds(359, 164, 91, 23);
		panelAceptar.add(btnCancelarNuevoEstado);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(143, 50, 91, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAceptar.setVisible(true);
			}
		});
		


		
	}
}
