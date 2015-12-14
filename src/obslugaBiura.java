import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class obslugaBiura extends EventOf2Entities<Klient, Konsultant>{

	Sklep model;
	BiuroObslugi biuro;
	Konsultant konsultant;
	
	public obslugaBiura(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		model = (Sklep)getModel();
		biuro = model.getBiuro();
	}


	public void eventRoutine(Klient klient, Konsultant konsultant) {
		

		Reklamacja reklamacja = new Reklamacja(model, "other.Reklamacja", true);
		klient.skladaReklamacje();
		int ile = reklamacja.getIleReklamacji();
		biuro.wolniKonsultanci.insert(konsultant);
		biuro.klienciWBiurze--;
		klient.maBiuro = false;
		
		

		if(klient.maZakupy){
			model.odpowiednieStoisko(klient.ktoreStoiska.get(0)).kolejkaStoisko.insert(klient);
			System.out.println("Ludzie ktorzy poszli do kasy: " + model.getKasa().kolejkaDoKas.length());
			pojscieNaStoisko pojscie = new pojscieNaStoisko(model, "podejście do stoiska", true, klient);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		
		
		else if(klient.maRestauracje){
			model.getRestauracja().kolejkaDoRestauracji.insert(klient);
			pojscieDoRestauracji pojscie = new pojscieDoRestauracji(model, "pojscie do restauracji", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
			
		}
		
		else if(!klient.zaplacil){
			model.getKasa().kolejkaDoKas.insert(klient);
			pojscieDoKasy pojscie = new pojscieDoKasy(model, "przejscie do kasy", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		

		else{
			wyjscieZeSklepu wyjscie = new wyjscieZeSklepu(model, "Wyjście ze sklepu", true);
			wyjscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));


		}
		
	}

}

