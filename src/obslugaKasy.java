import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.*;


public class obslugaKasy extends EventOf2Entities<Klient, OtwarteKasy>{

	Sklep model;
	Kasa kasa;

	
	public obslugaKasy(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eventRoutine(Klient klient, OtwarteKasy otwartaKasa) {
		// TODO Auto-generated method stub
		
		model = (Sklep)getModel();
		kasa = model.getKasa();
		
		kasa.klientWKasach.insert(klient);
		model.iloscObsluzonychKlientow++;
		klient.placi();
		if(!klient.zaplacil && !kasa.kolejkaDoKas.isEmpty()){
			kasa.otwarteKasy.insert(otwartaKasa);
			kasa.klientWKasach.remove(klient);
			//klient.zrobilZakupy = true;
			
			pojscieDoKasy idzDoKasy = new pojscieDoKasy(model, "idzie do kasy", true);
			idzDoKasy.schedule(new TimeSpan(0));
		}
		else{
			kasa.otwarteKasy.insert(otwartaKasa);
		
		
			if(klient.maRestauracje){
			model.getRestauracja().kolejkaDoRestauracji.insert(klient);
			pojscieDoRestauracji pojscie = new pojscieDoRestauracji(model, "pojscie do restauracji", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
			
			}
		
			else if(klient.maBiuro){
			pojscieDoBiura pojscie = new pojscieDoBiura(model, "podejście do biura", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
			
			}
		
			else {
			wyjscieZeSklepu wyjscie = new wyjscieZeSklepu(model, "Wyjście ze sklepu", true);
			wyjscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));

			}
	
		}
	}

}