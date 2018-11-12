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
import clases.Rol;
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
					EmpleadoView emp = empDAO.buscarEmpleado(txtNomUsr.getText(), txtContr.getText());
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
