import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class pojscieDoBiura extends ExternalEvent{

	private Sklep model;
	Klient klient;
	BiuroObslugi biuro;
	
	public pojscieDoBiura(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		
		model = (Sklep)getModel();
		biuro = model.getBiuro();
			
	}

	@Override
	public void eventRoutine() {
		
		if(!biuro.kolejkaDoBiura.isEmpty() && !biuro.wolniKonsultanci.isEmpty()){
			System.out.println("wchodzi ni whcodzi");
			model.wszyscyWBiurze++;
			System.out.println("@@ wszyscy klienci w biurze: " + model.wszyscyWBiurze);
			biuro.klienciWBiurze++;
			System.out.println("@@ aktualni klienci w biurze: " + biuro.klienciWBiurze);
			Klient klient = biuro.kolejkaDoBiura.first();
			biuro.kolejkaDoBiura.remove(klient);
			Konsultant konsultant = biuro.wolniKonsultanci.first();
			biuro.wolniKonsultanci.remove(konsultant);
			obslugaBiura obsluga = new obslugaBiura (model, "obsługa biura", true);
			obsluga.schedule(klient, konsultant, new TimeSpan(model.getObslugaBiura(), TimeUnit.MINUTES));
		}

	}
}
