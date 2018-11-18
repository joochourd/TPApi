package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.ActualizacionEstado;
import clases.Estados;
import clases.Facturacion;
import clases.Reclamo;
import clases.Sistema;
import clases.TipoReclamo;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import gui.ConsultasGUI.ManejoBoton;
import view.ProductoView;
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
import javax.swing.JComboBox;

public class AdministrarFacturacionGUI extends JFrame {

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
		setTitle("Administrar reclamos de facturacion");
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
		btnTratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tratar(e);
			}
		});
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
		comboBoxEstado.addItem("--select--");
		comboBoxEstado.addItem("EnTratamiento");
		comboBoxEstado.addItem("Cerrado");
		comboBoxEstado.addItem("Resuelto");
		contentPane.add(comboBoxEstado);
		
		try {
			reclamosV = Sistema.getInstance().getTablero().getReclamostipo(TipoReclamo.facturacion);
			for(ReclamoView objeto : reclamosV)
				comboBoxReclamos.addItem(objeto);
		} catch (ConexionException | AccesoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	protected void tratar(ActionEvent e) {
		if(txtDescripcion.getText().length() > 0 && comboBoxEstado.getSelectedIndex() != 0){
			try {
				Sistema.getInstance().getTablero().tratarReclamo(this.reclamosV.get(this.comboBoxReclamos.getSelectedIndex()).getNumeroReclamo(), Estados.valueOf(this.comboBoxEstado.getSelectedItem().toString()), this.txtDescripcion.getText());
			} catch (ConexionException | AccesoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			System.out.println("Agregar una descripccion y seleccionar un estado...");
		}
	}

}
