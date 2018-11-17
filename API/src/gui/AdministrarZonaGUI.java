package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Sistema;
import clases.TipoReclamo;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ReclamoView;

public class AdministrarZonaGUI extends JFrame {

private JPanel contentPane;
	
	private JTextField txtDescripcion;
	
	private JComboBox comboBoxReclamos;
	private JComboBox comboBoxEstado;
	
	private JLabel lblIngreseElNuevo;
	private JLabel lblIngreseLaDescripcion;
	private JLabel lblSeleccioneReclamo;
	
	private JButton btnTratar;
	private JButton btnCancelar;
	
	private List<ReclamoView> reclamosV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarZonaGUI frame = new AdministrarZonaGUI();
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
	public AdministrarZonaGUI() {
		setTitle("Administrar reclamos de zona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSeleccioneReclamo = new JLabel("Seleccione Reclamo:");
		lblSeleccioneReclamo.setBounds(10, 29, 126, 14);
		contentPane.add(lblSeleccioneReclamo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(327, 294, 91, 23);
		contentPane.add(btnCancelar);
		
		btnTratar = new JButton("Tratar");
		btnTratar.setBounds(127, 294, 91, 23);
		contentPane.add(btnTratar);
		
		comboBoxReclamos = new JComboBox();
		comboBoxReclamos.setBounds(148, 25, 389, 22);
		contentPane.add(comboBoxReclamos);
		
		lblIngreseLaDescripcion = new JLabel("Ingrese la descripcion:");
		lblIngreseLaDescripcion.setBounds(31, 116, 141, 39);
		contentPane.add(lblIngreseLaDescripcion);
		

		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(206, 106, 285, 54);
		contentPane.add(txtDescripcion);
		txtDescripcion.setText("");
		txtDescripcion.setColumns(10);
		
		lblIngreseElNuevo = new JLabel("Ingrese el nuevo estado:");
		lblIngreseElNuevo.setBounds(31, 211, 151, 14);
		contentPane.add(lblIngreseElNuevo);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(206, 207, 285, 22);
		comboBoxEstado.addItem("En tratamiento");
		comboBoxEstado.addItem("Cerrado");
		comboBoxEstado.addItem("Solucionado");
		contentPane.add(comboBoxEstado);
		
		try {
			reclamosV = Sistema.getInstance().getTablero().getReclamostipo(TipoReclamo.zona);
			for(ReclamoView objeto : reclamosV)
				comboBoxReclamos.addItem(objeto);
		} catch (ConexionException | AccesoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

}
