package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Label;
import javax.swing.JComboBox;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import extensions.*;

import com.toedter.calendar.JCalendar;

import clases.Sistema;
import clases.TipoReclamo;
import excepciones.AccesoException;
import excepciones.ConexionException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class RegistrarReclamos {

	private JFrame frame;

	private JTextField textFieldZona;
	private JTextField textFieldDescripcion;
	private JTextField textFieldNumerocliente;
	private JTextField textFieldNumeroFactura;
	private JTextField txtFieldCantidad;

	private JComboBox comboBox_TipoReclamo;
	private JComboBox comboBox_Producto;

	private JCalendar calendar;

	private JLabel lblNombreZona;
	private JLabel lblDescripcion;
	private JLabel lblNumeroDeCliente;
	private JLabel lblCantidad;
	private JLabel lblFechaDeFacturacion;
	private JLabel lblNumeroFactura;
	private JLabel lblProducto;

	private JPanel panelZona = new JPanel();
	private JPanel panelFacturacion = new JPanel();
	private JPanel panelCantidadProductoYFalta = new JPanel();
	private JLayeredPane layeredPane = new JLayeredPane();

	private JButton btnCargar;
	private JButton btnCancelar;

	public void setVisible(boolean a) { // Porque es una aplicacion windows
		this.frame.setVisible(a);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarReclamos window = new RegistrarReclamos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrarReclamos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Label label = new Label("Tipo de reclamo");
		label.setBounds(36, 10, 95, 22);
		frame.getContentPane().add(label);

		comboBox_TipoReclamo = new JComboBox();
		comboBox_TipoReclamo.setBounds(222, 10, 117, 20);
		comboBox_TipoReclamo.addItem("Cantidades");
		comboBox_TipoReclamo.addItem("Producto");
		comboBox_TipoReclamo.addItem("Faltantes");
		comboBox_TipoReclamo.addItem("Zona");
		comboBox_TipoReclamo.addItem("Facturacion");
		comboBox_TipoReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				seleccion(evt);
			}
		});
		frame.getContentPane().add(comboBox_TipoReclamo);
		layeredPane.setLayer(panelZona, 0);
		panelZona.setBounds(0, 0, 651, 455);
		panelZona.setLayout(null);
		panelZona.setVisible(true);

		lblNombreZona = new JLabel("Nombre Zona");
		lblNombreZona.setBounds(99, 145, 88, 14);
		panelZona.add(lblNombreZona);

		textFieldZona = new JTextField();
		textFieldZona.setText("Nombre de Zona ");
		textFieldZona.setBounds(243, 142, 121, 20);
		panelZona.add(textFieldZona);
		textFieldZona.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(36, 55, 95, 14);
		frame.getContentPane().add(lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(222, 52, 117, 20);
		frame.getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		lblNumeroDeCliente = new JLabel("DNI/CUIT de cliente");
		lblNumeroDeCliente.setBounds(36, 93, 117, 14);
		frame.getContentPane().add(lblNumeroDeCliente);

		textFieldNumerocliente = new JTextField();
		textFieldNumerocliente.setBounds(222, 90, 117, 20);
		frame.getContentPane().add(textFieldNumerocliente);
		textFieldNumerocliente.setColumns(10);
		
		layeredPane.setLayer(panelFacturacion, 0);
		panelFacturacion.setBounds(0, 0, 651, 455);
		panelFacturacion.setLayout(null);
		panelFacturacion.setVisible(true);

		lblFechaDeFacturacion = new JLabel("Fecha de facturacion");
		lblFechaDeFacturacion.setBounds(32, 113, 123, 14);
		panelFacturacion.add(lblFechaDeFacturacion);

		lblNumeroFactura = new JLabel("Numero de factura");
		lblNumeroFactura.setBounds(32, 39, 109, 14);
		panelFacturacion.add(lblNumeroFactura);

		calendar = new JCalendar();
		calendar.setBounds(209, 113, 316, 272);
		panelFacturacion.add(calendar);

		textFieldNumeroFactura = new JTextField();
		textFieldNumeroFactura.setBounds(209, 36, 123, 20);
		panelFacturacion.add(textFieldNumeroFactura);
		textFieldNumeroFactura.setColumns(10);
		layeredPane.setLayer(panelCantidadProductoYFalta, 0);
		panelCantidadProductoYFalta.setBounds(0, 0, 651, 455);
		panelCantidadProductoYFalta.setLayout(null);
		panelCantidadProductoYFalta.setVisible(true);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(133, 187, 80, 14);
		panelCantidadProductoYFalta.add(lblCantidad);

		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(133, 60, 61, 14);
		panelCantidadProductoYFalta.add(lblProducto);

		comboBox_Producto = new JComboBox();
		comboBox_Producto.setBounds(248, 57, 129, 20);
		panelCantidadProductoYFalta.add(comboBox_Producto);

		txtFieldCantidad = new JTextField();
		txtFieldCantidad.setBounds(248, 184, 129, 20);
		panelCantidadProductoYFalta.add(txtFieldCantidad);
		txtFieldCantidad.setColumns(10);

		btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargar(e);
			}
		});
		btnCargar.setBounds(112, 587, 89, 23);
		frame.getContentPane().add(btnCargar);
		layeredPane.setLayout(null);

		/*
		 * layeredPane.add(panelCantidadProductoYFalta); No borrar!
		 * layeredPane.add(panelFacturacion); layeredPane.add(panelZona);
		 */
		layeredPane.setBounds(10, 119, 573, 455);
		frame.getContentPane().add(layeredPane);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(389, 586, 97, 25);
		frame.getContentPane().add(btnCancelar);

	}

	protected void seleccion(ActionEvent evt) {
		// JComboBox cb = (JComboBox) evt.getSource();
		// if(cb.getSelectedItem() != null){
		if (this.comboBox_TipoReclamo.getSelectedItem() != null) {
			// switch (cb.getSelectedIndex()) {
			switch (this.comboBox_TipoReclamo.getSelectedIndex()) {
			case 0:
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 1:
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 2:
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 3:
				layeredPane.removeAll();
				layeredPane.add(panelZona);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 4:
				layeredPane.removeAll();
				layeredPane.add(panelFacturacion);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			default:
				break;
			}
		}
		// System.out.println(cb.getSelectedIndex());
		System.out.println(this.comboBox_TipoReclamo.getSelectedIndex());
	}

	protected void cargar(ActionEvent evt) {
		Sistema sistema = Sistema.getInstance();
		
		if (this.textFieldDescripcion.getText().length() > 0 && this.textFieldNumerocliente.getText().length() > 0) {
			switch (this.comboBox_TipoReclamo.getSelectedIndex()) {
			case 0:
				try {
					sistema.getTablero().registrarReclamoCantProdFalta(LocalDate.now(), textFieldDescripcion.getText(),
							TipoReclamo.cantidad, Integer.parseInt(textFieldNumerocliente.getText()),
							ExtensionHelper.dateToLocalDate(calendar.getDate()),
							Integer.parseInt(textFieldNumeroFactura.getText()));
				} catch (NumberFormatException | ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				try {
					sistema.getTablero().registrarReclamoCantProdFalta(LocalDate.now(), textFieldDescripcion.getText(),
							TipoReclamo.producto, Integer.parseInt(textFieldNumerocliente.getText()),
							ExtensionHelper.dateToLocalDate(calendar.getDate()),
							Integer.parseInt(textFieldNumeroFactura.getText()));
				} catch (NumberFormatException | ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					sistema.getTablero().registrarReclamoCantProdFalta(LocalDate.now(), textFieldDescripcion.getText(),
							TipoReclamo.falta, Integer.parseInt(textFieldNumerocliente.getText()),
							ExtensionHelper.dateToLocalDate(calendar.getDate()),
							Integer.parseInt(textFieldNumeroFactura.getText()));
				} catch (NumberFormatException | ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					sistema.getTablero().registrarReclamoZona(LocalDate.now(), this.textFieldDescripcion.getText(), Integer.parseInt(this.textFieldNumerocliente.getText()), this.textFieldZona.getText());
				} catch (NumberFormatException | ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Crashed trying to parse String to int");
				}
				break;
			case 4:
				// Change to local Date
				try {
					sistema.getTablero().registrarReclamoFacturacion(LocalDate.now(), textFieldDescripcion.getText(),
							Integer.parseInt(textFieldNumerocliente.getText()),
							ExtensionHelper.dateToLocalDate(calendar.getDate()),
							Integer.parseInt(textFieldNumeroFactura.getText()));
				} catch (NumberFormatException | ConexionException | AccesoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Faltan completar parametros", "Cuidado", JOptionPane.WARNING_MESSAGE);
	    }
		 
	}
}