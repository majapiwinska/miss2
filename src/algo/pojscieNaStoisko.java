package algo;

import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class pojscieNaStoisko extends ExternalEvent{

	private Sklep model;
	Stoisko stoisko;
	Stoisko.TypStoiska typ;
	Klient klient;
	
	public pojscieNaStoisko(Model owner, String name, boolean showTrace, Klient klient) {
		super(owner, name, showTrace);
	
		model = (Sklep)getModel();
		this.klient = klient;
		}

	@Override
	public void eventRoutine() {
		
		//klient = stoisko.kolejkaStoisko.first();
		
		typ = klient.ktoreStoiska.get(0);
		stoisko = model.odpowiednieStoisko(typ);
			
			obslugaStoisko obsluga = new obslugaStoisko(model, "Obsluga stoiska " + typ, false);
			stoisko.dodajKlienta();
			stoisko.klienciNaStoisku++;

			obsluga.schedule(klient, stoisko, new TimeSpan(model.getObslugaStoiska(), TimeUnit.MINUTES));	
		
		
	}



}

