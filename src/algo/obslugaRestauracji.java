package algo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class obslugaRestauracji extends EventOf2Entities<Klient, Restauracja>{

	Sklep model;
	
	public obslugaRestauracji(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Klient klient, Restauracja restauracja) {
		// TODO Auto-generated method stub
		
		model = (Sklep)getModel();
		
		
		klient.jedzenie();
		if(klient.zjadl){
			restauracja.kolejkaDoRestauracji.remove(klient);
			restauracja.wolneStoliki++;
			klient.maRestauracje = false;
			restauracja.klienciWRestauracji--;
		}



		if(klient.maZakupy){
			Random rand = new Random();
			Stoisko stoisko = model.odpowiednieStoisko(klient.getKtoreStoiska().get(0));
			if (stoisko.typ.id > klient.odwiedzone + 1) {
				int tmp = rand.nextInt(20);
				if (tmp < 1) {
					int nastepne = klient.odwiedzone + 1;
					Stoisko.TypStoiska pomiedzy = model.mapa.get(nastepne);
					Stoisko nastStoisko = model.odpowiednieStoisko(pomiedzy);
					nastStoisko.kolejkaStoisko.insert(klient);

				}
			} else {
				model.odpowiednieStoisko(klient.getKtoreStoiska().get(0)).kolejkaStoisko.insert(klient);

			}

			pojscieNaStoisko pojscie = new pojscieNaStoisko(model,"podejście do stoiska", false, klient);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}


		else if(klient.maBiuro){
			model.getBiuro().kolejkaDoBiura.insert(klient);
			pojscieDoBiura pojscie = new pojscieDoBiura(model, "podejście do biura", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		

		else if(!klient.zaplacil){
			model.getKasa().kolejkaDoKas.insert(klient);
			//System.out.println("Ludzie ktorzy poszli do kasy: " + model.getKasa().kolejkaDoKas.length());
			pojscieDoKasy pojscie = new pojscieDoKasy(model, "przejscie do kasy", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		
		else {
			wyjscieZeSklepu wyjscie = new wyjscieZeSklepu(model, "Wyjście ze sklepu", true);
			wyjscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));

		}
	
	
	}
}
