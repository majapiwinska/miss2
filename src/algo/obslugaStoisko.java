package algo;

import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.*;


public class obslugaStoisko extends EventOf2Entities<Klient, Stoisko>{

	private Sklep model;
	
	public obslugaStoisko(Model owner, String name, boolean ShowTrace) {
		super(owner, name, ShowTrace);
		// TODO Auto-generated constructor stub
		model = (Sklep)getModel();
		
	}

	@Override
	public void eventRoutine(Klient klient, Stoisko stoisko) {
		
		klient.kupuje();
		klient.ktoreStoiska.remove(0);
		klient.odwiedzone = stoisko.typ.id;

		stoisko.odejmijKlienta();
		
		if(!klient.ktoreStoiska.isEmpty()){
			model.odpowiednieStoisko(klient.getKtoreStoiska().get(0)).kolejkaStoisko.remove(klient);
		//System.out.println("@@ klienci na stoisku: " + stoisko.kolejkaStoisko.length());
			pojscieNaStoisko pojscie = new pojscieNaStoisko(model, "Przejscie na inne stoisko", true, klient);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		 else
			{

			klient.maZakupy = false;




				if(klient.maBiuro){
				pojscieDoBiura pojscie = new pojscieDoBiura(model, "podejście do biura", true);
				pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));

			}

			else if(klient.maRestauracje){
				pojscieDoRestauracji pojscie = new pojscieDoRestauracji(model, "przejście do restauracji" , true);
				pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
			}


			else if(!klient.zaplacil){
					model.getKasa().kolejkaDoKas.insert(klient);
				pojscieDoKasy pojscie = new pojscieDoKasy(model, "przejscie do kasy", true);
				pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
			}

			else {
				wyjscieZeSklepu wyjscie = new wyjscieZeSklepu(model, "Wyjście ze sklepu", true);
				wyjscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));

			}


			}

	}
	
	
	

}
	
	