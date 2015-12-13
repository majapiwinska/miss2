import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class pojscieDoRestauracji extends ExternalEvent{

	private Sklep model;
	Klient klient; 
	Restauracja restauracja;
	
	public pojscieDoRestauracji(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated 	 stub
	}

	@Override
	public void eventRoutine() {
		
		model = (Sklep)getModel();
		restauracja = model.getRestauracja();
		klient = restauracja.kolejkaDoRestauracji.first();
		
		if(restauracja.wolneStoliki != 0 && !restauracja.kolejkaDoRestauracji.isEmpty()){
			restauracja.wolneStoliki--;
			restauracja.klienciWRestauracji++;
			model.wszyscyWRestauracji++;
			obslugaRestauracji obsluga = new obslugaRestauracji(model, "pójście do ratsuracji", true);
			obsluga.schedule(klient, restauracja, new TimeSpan(model.getCzasWRestauracji(), TimeUnit.MINUTES));
		}
		
		
	}

}
