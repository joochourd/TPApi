package gui;

import dao.EmpleadoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import view.EmpleadoView;

public class LogInGUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInGUI window = new LogInGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogInGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LogIn");
		lblLogin.setFont(new Font("Dialog", Font.ITALIC, 18));
		lblLogin.setBounds(146, 53, 70, 19);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(27, 109, 163, 15);
		frame.getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(27, 153, 93, 15);
		frame.getContentPane().add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(208, 107, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(208, 151, 114, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBounds(27, 222, 135, 25);
		frame.getContentPane().add(btnIniciarSesion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(197, 222, 125, 25);
		frame.getContentPane().add(btnCancelar);
	
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpleadoDAO empDAO = new EmpleadoDAO();
				empDAO.getInstancia();
				try {
					@SuppressWarnings("deprecation")
					EmpleadoView emp = empDAO.buscarEmpleado(textField.getText(), passwordField.getText());
					if (emp != null) {
						emp.getRolTemporal();
						switch (emp.getRolTemporal()) {
						case 1://responsableFacturacion
						
							break;
						case 2://responsableFacturacion
							
							break;
						case 3://responsableZonas
							
							break;
						case 4://callCenter
							RegistrarReclamos venta = new RegistrarReclamos();
							venta.setVisible(true);
							break;
						case 5://administrador
							
							break;
						case 6://consulta
							
							break;

						default:
							break;
						}
						
					}else {
						System.out.println("Nombre de usuario incorrecto");
					}
				} catch (ConexionException | AccesoException e) {
					e.printStackTrace();
				} 
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
