package algo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeOperations;
import desmoj.core.simulator.TimeSpan;


    public class GeneratorKlientow extends ExternalEvent{
	
    private Sklep model;
    private Stoisko stoisko;
    private Restauracja restauracja;
    private BiuroObslugi biuro;
	private TimeInstant okres1;
	private TimeInstant okres2;
	private TimeOperations cos = null;
	 private int wspGen= 1; //DODANE
     private final int MAX= 600;//DODANE
     private final int MIN= 60;//DODANE
     private final int AVG= 200;
	public	Klient klient;
         /* @param owner the model this event belongs to
         @param name this event's name
         @param showInTrace flag to indicate if this event shall produce output for the trace*/
        
	public GeneratorKlientow(Model owner, String name, boolean showTrace, TimeSpan okres1, TimeSpan okres2, int wspGen) { //DODANE
		super(owner, name, false);
		model = (Sklep)getModel();
		stoisko = model.getStoisko();
		restauracja = model.getRestauracja();
		biuro = model.getBiuro();
		this.okres1 = cos.add(okres1, presentTime());
		this.okres2 = cos.add(okres2, presentTime());
		this.wspGen= wspGen;
		
		
}
/*
	public void setWspGet (int wsp) { //DODANE
        this.wspGen= wspGen; //DODANE
    }*/
	@Override
	public void eventRoutine() {
		klient = new Klient(model, "other.algo.Klient", false);
		model.dodajDoWszystkichKlientow();
		model.klienci.insert(klient);
		model.dodajDoKlientowWSklepie();

		Random rand = new Random();
		
		//ustalenie czy klient ma zrobić zakupy 80% i wylosowanie na których stoiskach
		int zakupy = rand.nextInt(10);
		if(zakupy < 8){
			klient.setMaZakupy(true);
			
				Random ran = new Random();
				int ile = klient.ileStoisk = ran.nextInt(3) + 2;	
				klient.ktoreStoiska = klient.losuj(ile);
		}
		else klient.setMaZakupy(false);


		//ustalenie czy klient idzie do restauracji 40%
		int restauracja = rand.nextInt(10);
		if(restauracja < 4) {
			klient.setMaRestauracje(true);

		}
		else klient.setMaRestauracje(false);
		
		//ustalenie czy klient idzie do biura obsługi 10%
		int biuro = rand.nextInt(10);
		if (biuro < 4)
			klient.setMaBiuro(true);
		else klient.setMaBiuro(false);
		

		if(klient.maZakupy){
			
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
		
		else if(klient.maBiuro){
			model.getBiuro().kolejkaDoBiura.insert(klient);
			pojscieDoBiura pojscie = new pojscieDoBiura(model, "podejście do biura", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		
		else if(klient.maRestauracje){
			model.getRestauracja().kolejkaDoRestauracji.insert(klient);
			pojscieDoRestauracji pojscie = new pojscieDoRestauracji(model, "pojście do restauracji", true);
			pojscie.schedule(new TimeSpan(0, TimeUnit.MINUTES));
		}
		
		
		
		if(presentTime().getTimeAsDouble() > okres2.getTimeAsDouble()){
			schedule(new TimeSpan(MIN/wspGen*50, TimeUnit.SECONDS));
			
		}
		
		else if(presentTime().getTimeAsDouble() < okres1.getTimeAsDouble()){
			schedule(new TimeSpan(AVG/wspGen, TimeUnit.SECONDS));
			
		}
		else{
			schedule(new TimeSpan(MAX/wspGen, TimeUnit.SECONDS));
			
		}
	}

 }	
		

