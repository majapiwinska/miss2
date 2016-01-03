package algo;

import java.util.concurrent.TimeUnit;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class pojscieDoKasy extends ExternalEvent{

	Sklep model;
	Klient klient;
	Kasa kasa;
	
	public pojscieDoKasy(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine() {
		
		model = (Sklep)getModel();
		kasa = model.getKasa();
		klient = kasa.kolejkaDoKas.first();
		
		if(!kasa.otwarteKasy.isEmpty() && !kasa.kolejkaDoKas.isEmpty()){
			kasa.kolejkaDoKas.remove(klient);
			OtwarteKasy otwartaKasa = kasa.otwarteKasy.first();
			kasa.otwarteKasy.remove(otwartaKasa);
			
			obslugaKasy obsluga = new obslugaKasy(model, "Obsługa w kasy", true);
			obsluga.schedule(klient, otwartaKasa, new TimeSpan(model.getObslugaKasy(), TimeUnit.MINUTES));
		
		}	
		
	}

}
