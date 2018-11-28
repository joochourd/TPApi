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
import view.ProductoView;
import view.ReclamoView;

import com.toedter.calendar.JCalendar;

import clases.Sistema;
import clases.TipoReclamo;
import excepciones.AccesoException;
import excepciones.ConexionException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.Font;

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

	private JPanel panelZona;
	private JPanel panelFacturacion;
	private JPanel panelCantidadProductoYFalta;
	private JLayeredPane layeredPane;
	private JPanel panelCompuesto;

	private JButton btnCargar;
	private JButton btnCancelar;


	private JScrollPane scrollPane;
	private DefaultListModel listModel;
	private JList listReclamos;

	private List<ProductoView> prods;

	private List<ReclamoView> reclamosV;
	private JLabel lblNewLabel;

	public void setVisible(boolean a) { // Porque es una aplicacion windows
		this.frame.setVisible(a);
	}

	public RegistrarReclamos() {
		initializePaneles();
		initialize();
	}

	private void initializePaneles(){
		panelZona = new JPanel();
		panelFacturacion = new JPanel();
		panelCantidadProductoYFalta = new JPanel();
		layeredPane = new JLayeredPane();
		panelCompuesto = new JPanel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Registrar reclamos");
		frame.setBounds(100, 100, 812, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		comboBox_TipoReclamo = new JComboBox();
		comboBox_TipoReclamo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_TipoReclamo.setBounds(240, 22, 145, 20);
		comboBox_TipoReclamo.addItem("--Seleccion--");
		comboBox_TipoReclamo.addItem("Cantidades");
		comboBox_TipoReclamo.addItem("Producto");
		comboBox_TipoReclamo.addItem("Faltantes");
		comboBox_TipoReclamo.addItem("Zona");
		comboBox_TipoReclamo.addItem("Facturacion");
		comboBox_TipoReclamo.addItem("Compuesto");
		comboBox_TipoReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				seleccion(evt);
			}
		});
		frame.getContentPane().add(comboBox_TipoReclamo);
		layeredPane.setLayer(panelZona, 0);
		panelZona.setBounds(0, 0, 740, 111);
		panelZona.setLayout(null);
		panelZona.setVisible(true);

		lblNombreZona = new JLabel("Nombre Zona");
		lblNombreZona.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreZona.setBounds(153, 44, 132, 20);
		panelZona.add(lblNombreZona);

		textFieldZona = new JTextField();
		textFieldZona.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldZona.setText("Nombre de Zona ");
		textFieldZona.setBounds(358, 44, 156, 21);
		panelZona.add(textFieldZona);
		textFieldZona.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripcion.setBounds(411, 22, 117, 20);
		frame.getContentPane().add(lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldDescripcion.setBounds(515, 22, 235, 48);
		frame.getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		lblNumeroDeCliente = new JLabel("DNI/CUIT de cliente");
		lblNumeroDeCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroDeCliente.setBounds(63, 71, 165, 19);
		frame.getContentPane().add(lblNumeroDeCliente);

		textFieldNumerocliente = new JTextField();
		textFieldNumerocliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNumerocliente.setBounds(240, 70, 145, 20);
		frame.getContentPane().add(textFieldNumerocliente);
		textFieldNumerocliente.setColumns(10);

		layeredPane.setLayer(panelFacturacion, 1);
		panelFacturacion.setBounds(0, 0, 752, 418);
		panelFacturacion.setLayout(null);
		panelFacturacion.setVisible(true);

		lblFechaDeFacturacion = new JLabel("Fecha de facturacion");
		lblFechaDeFacturacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeFacturacion.setBounds(32, 107, 151, 20);
		panelFacturacion.add(lblFechaDeFacturacion);

		lblNumeroFactura = new JLabel("Numero de factura");
		lblNumeroFactura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroFactura.setBounds(32, 36, 151, 17);
		panelFacturacion.add(lblNumeroFactura);

		calendar = new JCalendar();
		panelFacturacion.add(calendar);

		textFieldNumeroFactura = new JTextField();
		textFieldNumeroFactura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNumeroFactura.setBounds(209, 36, 123, 20);
		panelFacturacion.add(textFieldNumeroFactura);
		textFieldNumeroFactura.setColumns(10);
		layeredPane.setLayer(panelCantidadProductoYFalta, 0);
		panelCantidadProductoYFalta.setBounds(0, 0, 752, 168);
		panelCantidadProductoYFalta.setLayout(null);
		panelCantidadProductoYFalta.setVisible(true);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidad.setBounds(161, 113, 86, 20);
		panelCantidadProductoYFalta.add(lblCantidad);

		lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProducto.setBounds(161, 40, 86, 20);
		panelCantidadProductoYFalta.add(lblProducto);

		comboBox_Producto = new JComboBox();
		comboBox_Producto.setBounds(283, 40, 299, 20);
		try {
			prods = Sistema.getInstance().getProductos();
			for(ProductoView objeto : prods)
				comboBox_Producto.addItem(objeto);
			panelCantidadProductoYFalta.add(comboBox_Producto);
		} catch (ConexionException | AccesoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		txtFieldCantidad = new JTextField();
		txtFieldCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldCantidad.setBounds(283, 114, 105, 20);
		panelCantidadProductoYFalta.add(txtFieldCantidad);
		txtFieldCantidad.setColumns(10);

		btnCargar = new JButton("Cargar");
		btnCargar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargar(e);
			}
		});
		btnCargar.setBounds(211, 300, 103, 23);
		frame.getContentPane().add(btnCargar);


		layeredPane.setLayout(null);

		/*layeredPane.add(panelCantidadProductoYFalta);          
		layeredPane.add(panelFacturacion);
		layeredPane.add(panelZona);
		layeredPane.add(panelCompuesto);*/

		layeredPane.setBounds(10, 119, 752, 426);
		frame.getContentPane().add(layeredPane);


		layeredPane.setLayer(panelCompuesto, 0);
		panelCompuesto.setBounds(0, 0, 752, 418);
		panelCompuesto.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 30, 646, 345);
		panelCompuesto.add(scrollPane);
		panelCompuesto.setVisible(true);



		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(452, 300, 103, 25);
		frame.getContentPane().add(btnCancelar);

		lblNewLabel = new JLabel("Tipo de reclamo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(63, 20, 152, 25);
		frame.getContentPane().add(lblNewLabel);

	}

	protected void seleccion(ActionEvent evt) {
		// JComboBox cb = (JComboBox) evt.getSource();
		// if(cb.getSelectedItem() != null){
		if (this.comboBox_TipoReclamo.getSelectedItem() != null) {
			// switch (cb.getSelectedIndex()) {
			switch (this.comboBox_TipoReclamo.getSelectedIndex()) {
			case 1:
				frame.setBounds(100, 100, 812, 413);
				layeredPane.setBounds(10, 119, 752, 168);
				btnCargar.setBounds(211, 300, 103, 23);
				btnCancelar.setBounds(452, 300, 103, 25);
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 2:
				frame.setBounds(100, 100, 812, 413);
				layeredPane.setBounds(10, 119, 752, 168);
				btnCargar.setBounds(211, 300, 103, 23);
				btnCancelar.setBounds(452, 300, 103, 25);
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 3:
				frame.setBounds(100, 100, 812, 413);
				layeredPane.setBounds(10, 119, 752, 168);
				btnCargar.setBounds(211, 300, 103, 23);
				btnCancelar.setBounds(452, 300, 103, 25);
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 4:
				frame.setBounds(100, 100, 812, 413);
				layeredPane.setBounds(10, 119, 752, 168);
				btnCargar.setBounds(211, 300, 103, 23);
				btnCancelar.setBounds(452, 300, 103, 25);
				layeredPane.removeAll();
				layeredPane.add(panelZona);
				layeredPane.repaint();
				layeredPane.revalidate();
				break;
			case 5:
				frame.setBounds(100, 100, 812, 677);
				layeredPane.setBounds(10, 119, 752, 426);
				btnCargar.setBounds(211, 587, 103, 23);
				btnCancelar.setBounds(452, 586, 103, 25);
				layeredPane.removeAll();
				layeredPane.add(panelFacturacion);
				layeredPane.repaint();
				layeredPane.revalidate();
				calendar.setBounds(209, 113, 486, 236);
				break;
			case 6:
				if (!textFieldNumerocliente.getText().isEmpty()) {
					frame.setBounds(100, 100, 812, 677);
					layeredPane.setBounds(10, 119, 752, 426);
					btnCargar.setBounds(211, 587, 103, 23);
					btnCancelar.setBounds(452, 586, 103, 25);
					layeredPane.removeAll();
					layeredPane.add(panelCompuesto);
					layeredPane.repaint();
					layeredPane.revalidate();

					listModel = new DefaultListModel();
					try {
						reclamosV = Sistema.getInstance().getTablero()
								.getReclamosCliente(Integer.parseInt(textFieldNumerocliente.getText()));
						for (ReclamoView objeto : reclamosV)
							listModel.addElement(objeto);
						listReclamos = new JList(listModel);
						scrollPane.setViewportView(listReclamos);
						listReclamos.setBackground(Color.WHITE);
					} catch (ConexionException | AccesoException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese DNI/Cuit cliente", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
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
			case 1:
				try {
					sistema.getTablero().registrarReclamoCantProdFalta(LocalDate.now(), textFieldDescripcion.getText(),
							TipoReclamo.cantidad, Integer.parseInt(textFieldNumerocliente.getText()),
							prods.get(this.comboBox_Producto.getSelectedIndex()),
							Integer.parseInt(txtFieldCantidad.getText()));

					JOptionPane.showMessageDialog(null, "El reclamo se registro correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);


				} catch (NumberFormatException | ConexionException | AccesoException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					sistema.getTablero().registrarReclamoCantProdFalta(LocalDate.now(), textFieldDescripcion.getText(),
							TipoReclamo.producto, Integer.parseInt(textFieldNumerocliente.getText()),
							prods.get(this.comboBox_Producto.getSelectedIndex()),
							Integer.parseInt(txtFieldCantidad.getText()));

					JOptionPane.showMessageDialog(null, "El reclamo se registro correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException | ConexionException | AccesoException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					sistema.getTablero().registrarReclamoCantProdFalta(LocalDate.now(), textFieldDescripcion.getText(),
							TipoReclamo.falta, Integer.parseInt(textFieldNumerocliente.getText()),
							prods.get(this.comboBox_Producto.getSelectedIndex()),
							Integer.parseInt(txtFieldCantidad.getText()));

					JOptionPane.showMessageDialog(null, "El reclamo se registro correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException | ConexionException | AccesoException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					sistema.getTablero().registrarReclamoZona(LocalDate.now(), this.textFieldDescripcion.getText(), Integer.parseInt(this.textFieldNumerocliente.getText()), this.textFieldZona.getText());

					JOptionPane.showMessageDialog(null, "El reclamo se registro correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException | ConexionException | AccesoException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Crashed trying to parse String to int");
				}
				break;
			case 5:
				// Change to local Date
				try {
					sistema.getTablero().registrarReclamoFacturacion(LocalDate.now(), textFieldDescripcion.getText(),
							Integer.parseInt(textFieldNumerocliente.getText()),
							ExtensionHelper.dateToLocalDate(calendar.getDate()),
							Integer.parseInt(textFieldNumeroFactura.getText()));

					JOptionPane.showMessageDialog(null, "El reclamo se registro correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException | ConexionException | AccesoException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 6:
				try {
					sistema.getTablero().registrarReclamoCompuesto(LocalDate.now(), textFieldDescripcion.getText(), Integer.parseInt(textFieldNumerocliente.getText()), this.listReclamos.getSelectedValuesList());

					JOptionPane.showMessageDialog(null, "El reclamo se registro correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException | ConexionException | AccesoException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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