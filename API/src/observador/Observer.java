package observador;

import clases.Estados;

public interface Observer {
	
	public void update(Enum<Estados> estado);

}
