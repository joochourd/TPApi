package observador;

import java.util.ArrayList;
import java.util.List;

import clases.Estados;
import view.ReclamoView;

public class ObservableTablero {
	
private List<Observer> observadores;
	
	public ObservableTablero(){
		observadores = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer instancia){
		System.out.println(instancia.getClass().getName());
		observadores.add(instancia);
	}
	
	public void removeObserver(Observer instancia){
		observadores.remove(instancia);
	}
	
	public void updateObserver(ReclamoView reclamo){
		for(Observer observer : observadores)
			observer.update(reclamo);
	}

}
