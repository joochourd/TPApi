package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ABMCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtDomicilio;
	private JTextField txtTel;
	private JTextField txtMail;


	public ABMCliente() {
		setTitle("ABM Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox selector = new JComboBox();
		selector.addItem("Alta");
		selector.addItem("Baja");
		selector.addItem("Modificar");
		selector.setBounds(271, 25, 192, 22);
		contentPane.add(selector);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 81, 553, 289);
		contentPane.add(layeredPane);
		
		JPanel panelAlta = new JPanel();
		panelAlta.setBounds(0, 0, 553, 289);
		layeredPane.add(panelAlta);
		panelAlta.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 30, 56, 16);
		panelAlta.add(lblNombre);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(23, 97, 56, 16);
		panelAlta.add(lblDomicilio);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(23, 128, 56, 16);
		panelAlta.add(lblTelefono);
		
		JLabel lblDni = new JLabel("DNI / CUIT");
		lblDni.setBounds(23, 62, 69, 16);
		panelAlta.add(lblDni);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(23, 157, 56, 16);
		panelAlta.add(lblMail);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(119, 27, 116, 22);
		panelAlta.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(119, 62, 116, 22);
		panelAlta.add(txtDni);
		txtDni.setColumns(10);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(119, 94, 116, 22);
		panelAlta.add(txtDomicilio);
		txtDomicilio.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(119, 125, 116, 22);
		panelAlta.add(txtTel);
		txtTel.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setBounds(119, 154, 116, 22);
		panelAlta.add(txtMail);
		txtMail.setColumns(10);
		
		JPanel panelBaja = new JPanel();
		panelBaja.setBounds(0, 0, 553, 289);
		layeredPane.add(panelBaja);
		panelBaja.setLayout(null);
		
		JPanel panelModificar = new JPanel();
		panelModificar.setBounds(0, 0, 553, 289);
		layeredPane.add(panelModificar);
		panelModificar.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(96, 402, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(366, 402, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblSeleccion = new JLabel("Seleccione una opcion...");
		lblSeleccion.setBounds(39, 28, 154, 16);
		contentPane.add(lblSeleccion);
	}
}
