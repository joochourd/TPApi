package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clases.Estados;
import clases.Reclamo;
import clases.Sistema;
import clases.TipoReclamo;
import excepciones.AccesoException;
import excepciones.ConexionException;
import observador.Observer;
import view.ReclamoView;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministrarCantProdFaltGUI extends JFrame implements Observer{


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

	private JScrollPane scrollPane;
	private DefaultListModel listModel;
	private JList list;

	public AdministrarCantProdFaltGUI() {
		setTitle("Administrar reclamos de cantidad, producto y faltante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSeleccioneReclamo = new JLabel("Seleccione un Reclamo:");
		lblSeleccioneReclamo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeleccioneReclamo.setBounds(31, 44, 169, 23);
		contentPane.add(lblSeleccioneReclamo);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(445, 487, 111, 23);
		contentPane.add(btnCancelar);

		btnTratar = new JButton("Tratar");
		btnTratar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTratar.setBounds(217, 487, 111, 23);
		btnTratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tratar(e);
			}
		});
		contentPane.add(btnTratar);

		lblIngreseLaDescripcion = new JLabel("Ingrese la descripcion:");
		lblIngreseLaDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngreseLaDescripcion.setBounds(31, 215, 169, 39);
		contentPane.add(lblIngreseLaDescripcion);



		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDescripcion.setBounds(85, 268, 581, 54);
		contentPane.add(txtDescripcion);
		txtDescripcion.setText("");
		txtDescripcion.setColumns(10);

		lblIngreseElNuevo = new JLabel("Ingrese el nuevo estado:");
		lblIngreseElNuevo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngreseElNuevo.setBounds(31, 400, 195, 22);
		contentPane.add(lblIngreseElNuevo);

		comboBoxEstado = new JComboBox();
		comboBoxEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxEstado.setBounds(251, 401, 285, 22);
		comboBoxEstado.addItem("En tratamiento");
		comboBoxEstado.addItem("Cerrado");
		comboBoxEstado.addItem("Resuelto");
		contentPane.add(comboBoxEstado);

		listModel = new DefaultListModel();

		try {
			reclamosV = Sistema.getInstance().getTablero().getReclamostipo(TipoReclamo.cantYProdYFalta);
			for(ReclamoView objeto : reclamosV){
				listModel.addElement(objeto);
			}
			list = new JList(listModel);
			list.setBorder(UIManager.getBorder("TextField.border"));
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setBackground(Color.WHITE);
			list.setBounds(85, 80, 581, 93);
			scrollPane = new JScrollPane();
			scrollPane.setBounds(85, 80, 581, 93);
			scrollPane.setViewportView(list);
			contentPane.add(scrollPane);
		} catch (ConexionException | AccesoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
	
	protected void tratar(ActionEvent e) {
		if(txtDescripcion.getText().length() > 0 && comboBoxEstado.getSelectedIndex() != -1){
			try {
				if(this.comboBoxEstado.getSelectedItem().toString() == "En tratamiento"){
					Sistema.getInstance().getTablero().tratarReclamo(this.reclamosV.get(this.list.getSelectedIndex()).getNumeroReclamo(), Estados.EnTratamiento, this.txtDescripcion.getText());
				}
				else{
					Sistema.getInstance().getTablero().tratarReclamo(this.reclamosV.get(this.list.getSelectedIndex()).getNumeroReclamo(), Estados.valueOf(this.comboBoxEstado.getSelectedItem().toString()), this.txtDescripcion.getText());
				}
				JOptionPane.showMessageDialog(null, "El reclamo se trato correctamente", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

			}

			catch (ConexionException | AccesoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Agregar una descripccion y seleccionar un estado", "Cuidado", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void update(Reclamo reclamo) {
		// TODO Auto-generated method stub
		System.out.println("llamo al update " + reclamo.numeroReclamo());
		listModel.addElement(reclamo);
	}
}
