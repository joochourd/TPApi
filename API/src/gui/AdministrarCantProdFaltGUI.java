package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Estados;
import clases.Reclamo;
import clases.Sistema;
import clases.TipoReclamo;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ReclamoView;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministrarCantProdFaltGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtNuevoEstado;
	static volatile JList listaCPF = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarCantProdFaltGUI frame = new AdministrarCantProdFaltGUI();
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
	public AdministrarCantProdFaltGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneReclamo = new JLabel("Seleccione reclamo:");
		lblSeleccioneReclamo.setBounds(10, 11, 108, 20);
		contentPane.add(lblSeleccioneReclamo);
		
		List<Reclamo> reclamosC = null;
		List<Reclamo> reclamosP = null;
		List<Reclamo> reclamosF = null;
		List<Reclamo> reclamos = null;

		try {
		//Consigo todos los reclamos de Cantidad/Producto/Falta
			reclamosC = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Cantidad);
			reclamosP = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Producto);
			reclamosF = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Falta);
			reclamos.addAll(reclamosC);
			reclamos.addAll(reclamosP);
			reclamos.addAll(reclamosF);
		//Paso los reclamos a ReclamosView
			List<ReclamoView> reclamosV = new ArrayList<>();
			for (int i = 0; i < reclamos.size(); i++) {
				reclamosV.add(reclamos.get(i).reclamoToReclamoView());
			}
		//Los cargo en un array para mostrarlos en la lista
			String[] datos = new String[reclamosV.size()];
			for (int i = 0; i < reclamosV.size(); i++) {
				datos[i] = reclamosV.get(i).toString();
			}
		//Cargo los datos en la lista
			JList aux = new JList(datos);
			listaCPF = aux;
		} catch (ConexionException | AccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		//JList listaCPF = new JList();
		listaCPF.setBounds(128, 13, 351, 29);
		contentPane.add(listaCPF);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(128, 78, 101, 20);
		contentPane.add(btnCancelar);
		
		JPanel panelAceptar = new JPanel();
		panelAceptar.setBounds(10, 111, 469, 169);
		contentPane.add(panelAceptar);
		panelAceptar.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelAceptar.setVisible(true);
			}
		});
		btnAceptar.setBounds(307, 77, 89, 23);
		contentPane.add(btnAceptar);
		

		
		JLabel lblIngreseLaDescripcion = new JLabel("Ingrese la descripcion:");
		lblIngreseLaDescripcion.setBounds(10, 11, 119, 14);
		panelAceptar.add(lblIngreseLaDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(139, 8, 320, 49);
		panelAceptar.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblIngreseNuevoEstado = new JLabel("Ingrese nuevo estado: ");
		lblIngreseNuevoEstado.setBounds(10, 90, 119, 14);
		panelAceptar.add(lblIngreseNuevoEstado);
		
		txtNuevoEstado = new JTextField();
		txtNuevoEstado.setBounds(139, 84, 320, 43);
		panelAceptar.add(txtNuevoEstado);
		txtNuevoEstado.setColumns(10);
		
		JButton btnCancelarNuevoEstado = new JButton("Cancelar");
		btnCancelarNuevoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelarNuevoEstado.setBounds(139, 138, 89, 23);
		panelAceptar.add(btnCancelarNuevoEstado);
		
		JButton btnAceptarNuevoEstado = new JButton("Aceptar");
		btnAceptarNuevoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List <Reclamo> selection = listaCPF.getSelectedValuesList();
				try {
					Sistema.getInstance().getTablero().tratarReclamo(selection.get(0).getNumeroReclamo(), Estados.valueOf(txtNuevoEstado.getText()), txtDescripcion.getText());
				} catch (ConexionException | AccesoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptarNuevoEstado.setBounds(303, 138, 89, 23);
		panelAceptar.add(btnAceptarNuevoEstado);
		panelAceptar.setVisible(false);
		
}

	}
}
