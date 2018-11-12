package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Empleado;
import dao.EmpleadoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

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
		lblLogIn.setBounds(187, 27, 82, 24);
		contentPane.add(lblLogIn);
		
		JLabel lblNombreDe = new JLabel("Nombre de Usuario:");
		lblNombreDe.setBounds(127, 62, 106, 24);
		contentPane.add(lblNombreDe);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setBounds(127, 97, 70, 24);
		contentPane.add(lblContrasenia);
		
		txtNomUsr = new JTextField();
		txtNomUsr.setBounds(243, 62, 86, 20);
		contentPane.add(txtNomUsr);
		txtNomUsr.setColumns(10);
		
		txtContr = new JTextField();
		txtContr.setBounds(243, 99, 86, 20);
		contentPane.add(txtContr);
		txtContr.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpleadoDAO empDAO = new EmpleadoDAO();
				empDAO.getInstancia();
				try {
					Empleado emp = empDAO.buscarEmpleado(txtNomUsr.getText());
					Integer ordenRol = null;
					if (emp != null) {
						if (emp.getPassword().equals(txtContr)) {
								if (emp.getRolTemporal().equals("responsableFacturacion")) {
									ordenRol = 1; 
								}
								if (emp.getRolTemporal().equals("responsableDistribucion")) {
									ordenRol = 2;
								}
								if (emp.getRolTemporal().equals("responsableZonas")) {
									ordenRol = 3;
								}
								if (emp.getRolTemporal().equals("callCenter")) {
									ordenRol = 4;
								}
								if (emp.getRolTemporal().equals("administrador")) {
									ordenRol = 5;
								}
								if (emp.getRolTemporal().equals("consulta")) {
									ordenRol = 6;
								}
								AdministrarReclamosGUI adminReclamos = new AdministrarReclamosGUI(ordenRol);
								adminReclamos.setVisible(true);
						}else {
							System.out.println("Contraseña incorrecta");
						}
					}else {
						System.out.println("Nombre de usuario incorrecto");
					}
				} catch (ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		btnIniciarSesion.setBounds(127, 132, 95, 31);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(253, 130, 89, 33);
		contentPane.add(btnCancelar);
		
	}
}
