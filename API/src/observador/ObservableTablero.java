package observador;

import java.util.ArrayList;
import java.util.List;

import clases.Estados;

public class ObservableTablero {
	
private List<Observer> observadores;
	
	public ObservableTablero(){
		observadores = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer instancia){
		observadores.add(instancia);
	}
	
	public void removeObserver(Observer instancia){
		observadores.remove(instancia);
	}
	
	public void updateObserver(Enum<Estados> estado){
		for(Observer observer : observadores)
			observer.update(estado);
	}

}
