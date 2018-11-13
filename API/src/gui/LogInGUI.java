package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Empleado;
import clases.Rol;
import clases.Sistema;
import dao.EmpleadoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.EmpleadoView;

public class LogInGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomUsr;
	private JTextField txtContr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInGUI frame = new LogInGUI();
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
	public LogInGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(169, 23, 82, 24);
		contentPane.add(lblLogIn);
		
		JLabel lblNombreDe = new JLabel("Nombre de Usuario:");
		lblNombreDe.setBounds(50, 60, 132, 24);
		contentPane.add(lblNombreDe);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setBounds(50, 97, 118, 24);
		contentPane.add(lblContrasenia);
		
		txtNomUsr = new JTextField();
		txtNomUsr.setBounds(243, 62, 132, 20);
		contentPane.add(txtNomUsr);
		txtNomUsr.setColumns(10);
		
		txtContr = new JTextField();
		txtContr.setBounds(243, 99, 132, 20);
		contentPane.add(txtContr);
		txtContr.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EmpleadoView emp = Sistema.getInstance().login(txtNomUsr.getText(), txtContr.getText());
					if (emp != null) {
						emp.getRolTemporal();
						switch (emp.getRolTemporal()) {
						case 1://responsableFacturacion
							AdministrarFacturacionGUI admFact = new AdministrarFacturacionGUI();
							admFact.setVisible(true);
							break;
						case 2://responsableFacturacion
							AdministrarCantProdFaltGUI admCPF = new AdministrarCantProdFaltGUI();
							admCPF.setVisible(true);
							break;
						case 3://responsableZonas
							AdministrarZonaGUI admZona = new AdministrarZonaGUI();
							admZona.setVisible(true);
							break;
						case 4://callCenter
							RegistrarReclamos venta = new RegistrarReclamos();
							venta.setVisible(true);
							break;
						case 5://administrador
							AdministradorAppGUI administrador = new AdministradorAppGUI();
							administrador.setVisible(true);
							break;
						case 6://consulta
							ConsultasGUI consultas = new ConsultasGUI();
							consultas.setVisible(true);
							break;

						default:
							break;
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Usurario incorrecto...", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		btnIniciarSesion.setBounds(73, 184, 132, 24);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(243, 185, 132, 24);
		contentPane.add(btnCancelar);
		
	}
}
