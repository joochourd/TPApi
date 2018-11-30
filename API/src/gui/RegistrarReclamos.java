package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JFrame;

import java.awt.Label;

import javax.swing.JComboBox;

import java.awt.Panel;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import extensions.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import clases.Sistema;
import clases.TipoReclamo;
import excepciones.AccesoException;
import excepciones.ConexionException;

import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.JLayeredPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class RegistrarReclamos extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFrame fecha;
	private JTextField textFieldZona;
	private JTextField textFieldDescripcion;
	private JTextField textFieldNumerocliente;
	private JTextField textFieldNumeroFactura;
	private JTextField txtFieldCantidad;
	private JPanel panelZona = new JPanel();
	private JPanel panelFacturacion = new JPanel();
	private JPanel panelCantidadProductoYFalta = new JPanel();
	private JLayeredPane layeredPane = new JLayeredPane();
	
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

	public RegistrarReclamos() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		Component lblRegistrarReclamo = new JLabel("Registrar Reclamo");
		lblRegistrarReclamo.setFont(new Font("Dialog", Font.ITALIC, 18));
		lblRegistrarReclamo.setBounds(117, 34, 173, 20);
		frame.getContentPane().add(lblRegistrarReclamo);
		
		JLabel lblTipoDeReclamo = new JLabel("Tipo de Reclamo:");
		lblTipoDeReclamo.setBounds(27, 96, 144, 15);
		frame.getContentPane().add(lblTipoDeReclamo);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(30, 150, 87, 15);
		frame.getContentPane().add(lblDescripcion);
		
		JLabel lblNumeroDeCliente = new JLabel("Numero de Cliente:");
		lblNumeroDeCliente.setBounds(31, 208, 163, 15);
		frame.getContentPane().add(lblNumeroDeCliente);
		
		final JComboBox<String> comboBox_TipoReclamo = new JComboBox<String>();
		comboBox_TipoReclamo.setBounds(214, 93, 136, 20);
		frame.getContentPane().add(comboBox_TipoReclamo);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(214, 148, 136, 19);
		frame.getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(214, 206, 136, 20);
		frame.getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblRegistrarReclamo, lblTipoDeReclamo, lblDescripcion, lblNumeroDeCliente, comboBox_TipoReclamo}));
	
		comboBox_TipoReclamo.addItem("Cantidades");
		comboBox_TipoReclamo.addItem("Producto");
		comboBox_TipoReclamo.addItem("Faltantes");
		comboBox_TipoReclamo.addItem("Zona");
		comboBox_TipoReclamo.addItem("Facturacion");
		
		comboBox_TipoReclamo.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myBox(evt);
            }
		}));
		
		frame.getContentPane().add(comboBox_TipoReclamo);
		layeredPane.setLayer(panelZona, 0);
		panelZona.setBounds(0, 0, 400, 450);
		panelZona.setLayout(null);
		panelZona.setVisible(true);
		
		JLabel lblNombreZona = new JLabel("Nombre Zona:");
		lblNombreZona.setBounds(30, 265, 167,15);
		panelZona.add(lblNombreZona);
		
		textFieldZona = new JTextField();
		textFieldZona.setText("Nombre de la Zona");
		textFieldZona.setBounds(214, 260, 136, 20);
		panelZona.add(textFieldZona);
		textFieldZona.setColumns(10);
		
		layeredPane.setLayer(panelFacturacion, 0);
		panelFacturacion.setBounds(0, 0, 400, 450);
		panelFacturacion.setLayout(null);
		panelFacturacion.setVisible(true);

		
		JLabel lblFechaDeFacturacion = new JLabel("Fecha de facturacion:");
		lblFechaDeFacturacion.setBounds(30, 310, 167,15);
		panelFacturacion.add(lblFechaDeFacturacion);
		
		JLabel lblNumeroFactura = new JLabel("Numero de factura:");
		lblNumeroFactura.setBounds(30, 265, 167,15);
		panelFacturacion.add(lblNumeroFactura);
		JDateChooser chooseDate = new JDateChooser();
		chooseDate.setBounds(214, 310, 136, 20);
		panelFacturacion.add(chooseDate);
						
		textFieldNumeroFactura = new JTextField();
		textFieldNumeroFactura.setBounds(214, 260, 136, 20);
		panelFacturacion.add(textFieldNumeroFactura);
		textFieldNumeroFactura.setColumns(10);
		layeredPane.setLayer(panelCantidadProductoYFalta, 0);
		panelCantidadProductoYFalta.setBounds(0, 0, 400,450);
		panelCantidadProductoYFalta.setLayout(null);
		panelCantidadProductoYFalta.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Cantidad:");
		lblNewLabel.setBounds(30, 310, 87, 15);
		panelCantidadProductoYFalta.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setBounds(30, 190, 87, 155);
		panelCantidadProductoYFalta.add(lblNewLabel_1);
		
		JComboBox comboBox_Producto = new JComboBox();
		comboBox_Producto.setBounds(214, 260, 136, 20);
		panelCantidadProductoYFalta.add(comboBox_Producto);
		
		txtFieldCantidad = new JTextField();
		txtFieldCantidad.setBounds(214, 310, 136, 20);
		panelCantidadProductoYFalta.add(txtFieldCantidad);
		txtFieldCantidad.setColumns(10);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sistema sistema = Sistema.getInstance();
				switch (comboBox_TipoReclamo.getSelectedIndex()) {
				case 1:
					
					try {
						sistema.getTablero().registrarReclamoZona(0, LocalDate.now(), textFieldDescripcion.getText(), Integer.parseInt(textFieldNumerocliente.getText()) , textFieldZona.getText());
					} catch (NumberFormatException | ConexionException | AccesoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Crashed trying to parse String to int");
					}
					break;
				case 2:
					//Change to local Date
					try {
						sistema.getTablero().registrarReclamoFacturacion(0, LocalDate.now(), textFieldDescripcion.getText(), Integer.parseInt(textFieldNumerocliente.getText()), ExtensionHelper.dateToLocalDate(jcalendar.getDate()), Integer.parseInt(textFieldNumeroFactura.getText()));
					} catch (NumberFormatException | ConexionException | AccesoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				case 3:
				case 4:
				case 5:
					try {
						sistema.getTablero().registrarReclamoCantProdFalta(0, LocalDate.now(), textFieldDescripcion.getText(), TipoReclamo.Falta, Integer.parseInt(textFieldNumerocliente.getText()), ExtensionHelper.dateToLocalDate(jcalendar.getDate()), Integer.parseInt(textFieldNumeroFactura.getText()));
					} catch (NumberFormatException | ConexionException | AccesoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				default:
					break;
				}
			}
		});
		
		btnCargar.setBounds(21, 370, 143, 30);
		frame.add(btnCargar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.repaint();
				layeredPane.revalidate();
				frame.setBounds(100, 100, 400, 308);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnCancelar.setBounds(215, 370, 143, 30);
		frame.add(btnCancelar);
		
		layeredPane.setLayout(null);
		layeredPane.setBounds(0, 0, 600, 600);
		frame.getContentPane().add(layeredPane);
		
	}

	protected void myBox(ActionEvent evt) {
		// TODO Auto-generated method stub	
		JComboBox cb = (JComboBox) evt.getSource();
		if(cb.getSelectedItem() != null){
			switch (cb.getSelectedIndex()) {
			case 0:
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				frame.setBounds(100, 100, 400, 450);
				frame.setLocationRelativeTo(null);
				break;
			case 1:
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				frame.setBounds(100, 100, 400, 450);
				frame.setLocationRelativeTo(null);
				break;
			case 2:
				layeredPane.removeAll();
				layeredPane.add(panelCantidadProductoYFalta);
				layeredPane.repaint();
				layeredPane.revalidate();
				frame.setBounds(100, 100, 400, 450);
				frame.setLocationRelativeTo(null);
				break;
			case 3:
				layeredPane.removeAll();
				layeredPane.add(panelZona);
				layeredPane.repaint();
				layeredPane.revalidate();
				frame.setBounds(100, 100, 400, 450);
				frame.setLocationRelativeTo(null);
				break;
			case 4:
				layeredPane.removeAll();
				layeredPane.add(panelFacturacion);
				layeredPane.repaint();
				layeredPane.revalidate();
				frame.setBounds(100, 100, 400, 450);
				frame.setLocationRelativeTo(null);
				break;
			default:
				break;
			}
		}
			//System.out.println(cb.getSelectedItem().toString());
			System.out.println(cb.getSelectedIndex());
}}
