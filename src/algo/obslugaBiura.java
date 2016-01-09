package algo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class obslugaBiura extends EventOf2Entities<Klient, Konsultant>{

	Sklep model;
	BiuroObslugi biuro;
	
	public obslugaBiura(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		model = (Sklep)getModel();
		biuro = model.getBiuro();
	}


	public void eventRoutine(Klient klient, Konsultant konsultant) {


		Reklamacja reklamacja = new Reklamacja(model, "other.algo.Reklamacja", true);
		klient.skladaReklamacje();
		int ile = reklamacja.getIleReklamacji();
		biuro.wolniKonsultanci.insert(konsultant);
		biuro.klienciWBiurze--;
		klient.maBiuro = false;



		if(klient.maZakupy){
			Random rand = new Random();
//			model.odpowiednieStoisko(klient.getKtoreStoiska().get(0)).kolejkaStoisko.insert(klient);
			Stoisko stoisko = model.odpowiednieStoisko(klient.getKtoreStoiska().get(0));
			if (stoisko.typ.id > klient.odwiedzone + 1) {
				int tmp = rand.nextInt(20);
				if (tmp < 5) {
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

