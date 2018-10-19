package gui;

import java.awt.Container;

import javax.swing.JFrame;

import clases.Estados;
import clases.Tablero;
import observador.Observer;

public class Vista extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;


	public Vista(){
		configurar();
		this.setVisible(true);
	}
	
	private void configurar(){
		Container c = this.getContentPane();
		c.setLayout(null);
		
	}
	
	
	@Override
	public void update(Enum<Estados> estado) {
		// TODO Auto-generated method stub
		
	}

}
