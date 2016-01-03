package algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


//import other.algo.Stoisko.TypStoiska;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeSpan;





/*@param owner the model this model is part of (set to null when there is
	                  no such model)
	     @param modelName this model's name
	    @param showInReport flag to indicate if this model shall produce output
	                        to the report file
	     @param showInTrace flag to indicate if this model shall produce output
	                        to the trace file*/
public class Sklep extends Model{
		public Sklep(Model owner, String name, boolean showReport, boolean showTrace) {
		super(null, name, showReport, showTrace);

	}

		public static void main(String [ ] args)
		{
			/*instantiate an experiment
			instantiate the model
			connect the model to the experiment
			determine the length of the simulation run or set an ending criterion for the simulation run
			set the start and end time for the trace file
			start the scheduler
			initiate reporting
			clean up after the simulation run has ended*/


			 //create model and experiment
			 // null as first parameter because it is the main model and has no mastermodel
			Sklep sklep = new Sklep(null, "Symulacja", false, false);
			Experiment exp = new Experiment("Symulacja - eksperyment", TimeUnit.SECONDS, TimeUnit.MINUTES, null);
			sklep.connectToExperiment(exp);

			/*other.algo.GeneratorKlientow generator = new other.algo.GeneratorKlientow(sklep, "generator klientow", true, new TimeSpan(2, TimeUnit.HOURS), new TimeSpan(6, TimeUnit.HOURS), 0); //DODANE
			generator.schedule(new TimeSpan(0));

			other.algo.Wypisz statystyki = new other.algo.Wypisz(sklep, "statystyki", true, sklep.getKlient());
			statystyki.schedule(new TimeSpan(45, TimeUnit.MINUTES));*/

			exp.setShowProgressBar(true);
			exp.stop(new TimeInstant(720, TimeUnit.MINUTES));
			exp.tracePeriod(new TimeInstant(0), new TimeInstant(100, TimeUnit.MINUTES));
			exp.debugPeriod(new TimeInstant(0), new TimeInstant(50, TimeUnit.MINUTES));


			exp.start();
			exp.report();


			System.out.println("Ilość obsłużonych łacznie klientów w kasach: " + sklep.iloscObsluzonychKlientow);
			System.out.println("Ilość obsłużonych łacznie klientów na stoisku meble: " +sklep.meble.klienciNaStoisku);
			System.out.println("Ilość obsłużonych łacznie klientów na stoisku dekoracje: " +sklep.dekoracje.klienciNaStoisku);
			System.out.println("Ilość obsłużonych łacznie klientów na stoisku lazienka: " +sklep.lazienka.klienciNaStoisku);
			System.out.println("Ilość obsłużonych łacznie klientów na stoisku kuchnia: " +sklep.kuchnia.klienciNaStoisku);
			System.out.println("Ilość obsłużonych łacznie klientów na stoisku sypialnia: " +sklep.sypialnia.klienciNaStoisku);
			System.out.println("Ilość ludzi w sklepie przez cały dzień: " + sklep.getWszyscyKlienci()+ "\r\n");
			System.out.println("Ilość wszystkich klientów w restauracji przez cały dzień: " + sklep.wszyscyWRestauracji);
			System.out.println("Ilość wszystkich klientów w bierze przez cały dzień: " + sklep.wszyscyWBiurze);
			exp.finish();
		};

@Override
public String description() {
	// TODO Auto-generated method stub
	return null;
}




@Override
public void init() {

	pojawienieSięKlienta = new ContDistExponential(this, "Pojawienie się klienta", 4.0, true, true);
	obslugaKasy = new ContDistUniform(this, "Czas obsługi", 2.0, 4.0, true, true);
	obslugaBiura = new ContDistUniform(this, "Czas osługi biura", 2.0, 4.0, true, true);
	obslugaStoiska = new ContDistUniform(this, "Czas obsługi stoiska", 12.0, 14.0, true, true);
	czasWRestauracji = new ContDistUniform(this, "Czas spędzony w restauracji", 20.0, 30.0, true, true);

	kasa =new Kasa(this, "other.algo.Kasa", false);
	biuro = new BiuroObslugi(this, "Biuro obsługi", true);
	restauracja = new Restauracja(this, "other.algo.Restauracja", true);
	akutalniKlienci = new Queue<Klient>(this, "Wszyscy klienci", true, true);
	klienci = new Queue<Klient>(this, "Klienci", true, true);
	konsultant1 = new Konsultant(this, "konsultant1", true );
	konsultant2 = new Konsultant(this, "konsultant2", true );

	getBiuro().wolniKonsultanci.insert(konsultant1);
	getBiuro().wolniKonsultanci.insert(konsultant2);



	meble = new Stoisko(this, "Meble", true, Stoisko.TypStoiska.meble);
	 lazienka = new Stoisko(this, "lazienka", true, Stoisko.TypStoiska.lazienka);
	 kuchnia = new Stoisko(this, "kuchnia", true, Stoisko.TypStoiska.kuchnia);
	 dekoracje = new Stoisko(this, "dekoracje", true, Stoisko.TypStoiska.dekoracje);
	 sypialnia = new Stoisko(this, "sypialnia", true, Stoisko.TypStoiska.sypialnia);


	mapa = new HashMap<Integer, Stoisko.TypStoiska>();
	mapa.put(1, Stoisko.TypStoiska.sypialnia);
	mapa.put(2, Stoisko.TypStoiska.dekoracje);
	mapa.put(3, Stoisko.TypStoiska.kuchnia);
	mapa.put(4, Stoisko.TypStoiska.lazienka);
	mapa.put(5, Stoisko.TypStoiska.meble);


}

@Override
public void doInitialSchedules() {
	GeneratorKlientow generator = new GeneratorKlientow(this, "generator klientów",false, new TimeSpan(3, TimeUnit.HOURS), new TimeSpan(9, TimeUnit.HOURS), 5);
	generator.schedule(new TimeSpan(3, TimeUnit.MINUTES));



	Wypisz statystyki = new Wypisz(this, "statystyki", false);
	statystyki.schedule(new TimeSpan(45, TimeUnit.MINUTES));


}



public double getPojawienieSięKlienta() {
	return pojawienieSięKlienta.sample();
}

public void setPojawienieSięKlienta(ContDistExponential pojawienieSięKlienta) {
	this.pojawienieSięKlienta = pojawienieSięKlienta;
}

public double getObslugaKasy() {
	return obslugaKasy.sample();
}

public void setObslugaKasy(ContDistUniform obslugaKasy) {
	this.obslugaKasy = obslugaKasy;
}

public Queue<Klient> getAkutalniKlienci() {
	return akutalniKlienci;
}

public double getObslugaBiura() {
	return obslugaBiura.sample();
}

public void setObslugaBiura(ContDistUniform obslugaBiura) {
	this.obslugaBiura = obslugaBiura;
}

public double getObslugaStoiska() {
	return obslugaStoiska.sample();
}

public void setObslugaStoiska(ContDistUniform obslugaStoiska) {
	this.obslugaStoiska = obslugaStoiska;
}

public double getCzasWRestauracji() {
	return czasWRestauracji.sample();
}

public void setCzasWRestauracji(ContDistUniform czasWRestauracji) {
	this.czasWRestauracji = czasWRestauracji;
}

public int getWszyscyKlienci() {
	return wszyscyKlienci;
}

public void dodajDoWszystkichKlientow() {
	wszyscyKlienci++;
}



public Kasa getKasa() {
	return kasa;
}

public void setKasa(Kasa kasa) {
	this.kasa = kasa;
}

public BiuroObslugi getBiuro() {
	return biuro;
}

public void setBiuro(BiuroObslugi biuro) {
	this.biuro = biuro;
}

public Restauracja getRestauracja() {
	return restauracja;
}

public void setRestauracja(Restauracja restauracja) {
	this.restauracja = restauracja;
}

public Stoisko getStoisko() {
	return stoisko;
}

public void setStoisko(Stoisko stoisko) {
	this.stoisko = stoisko;
}

public Klient getKlient() {
	return klient;
}


public void dodajDoKlientowWSklepie(){
	klienciWSklepie++;
}

	public void odeijmijOdKlientowWSklepie(){
		klienciWSklepie--;
	}


public Stoisko odpowiednieStoisko(Stoisko.TypStoiska typ){

	if(typ.equals(meble.getTyp()))
			return meble;
	else if(typ.equals(lazienka.getTyp()))
		return lazienka;
	else if(typ.equals(kuchnia.getTyp()))
		return kuchnia;
	else if(typ.equals(dekoracje.getTyp()))
		return dekoracje;
	else
		return sypialnia;

}

	Map<Integer, Stoisko.TypStoiska> mapa;
private ContDistExponential pojawienieSięKlienta;
private ContDistUniform obslugaKasy;
private ContDistUniform obslugaBiura;
private ContDistUniform obslugaStoiska;
private ContDistUniform czasWRestauracji;

private Kasa kasa;
private BiuroObslugi biuro;
private Restauracja restauracja;
private Stoisko stoisko;
	Konsultant konsultant1, konsultant2;

 Klient klient ;

private Random rand = new Random();
protected int maxLudzi = rand.nextInt(50);
protected Queue<Klient> akutalniKlienci, klienci;
protected int ileLudzi;
;

protected Stoisko meble;
protected Stoisko lazienka;
protected Stoisko kuchnia;
protected Stoisko dekoracje;
protected Stoisko sypialnia;

	int iloscObsluzonychKlientow = 0;
	int wszyscyKlienci = 0;
	int	klienciWSklepie = 0;
	int wszyscyWRestauracji = 0;
	int wszyscyWBiurze = 0;




}

/*
 * sredni czas pobytu klienta, sredni czas obslugi przy poszczegolnych stoiskach, w jakich miejscach gromadza się ludzie
 * sfml - jsfml - 
 * 
 */
