package algo;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;


public class wyjscieZeSklepu extends ExternalEvent{

	Klient klient;
	Sklep model;
	
	public wyjscieZeSklepu(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
		model = (Sklep)getModel();
		klient = model.getKlient();
		
	
	}

	@Override
	public void eventRoutine() {
		model.akutalniKlienci.remove(klient);
		model.odeijmijOdKlientowWSklepie();
		//model.klienci.remove(klient);
		
		
	}

}
