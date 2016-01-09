package algo;

import desmoj.core.dist.ContDistExponential;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.TimeSpan;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;





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
			public GeneratorKlientow generator;

			private Random rand = new Random();
		protected Queue<Klient> akutalniKlienci, klienci;

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

		double czasGenerowaniaKlienta ;


	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void init() {

		pojawienieSięKlienta = new ContDistExponential(this, "Pojawienie się klienta", czasGenerowaniaKlienta, true, true);
		obslugaKasy = new ContDistUniform(this, "Czas obsługi", 2.0, 4.0, true, true);
		obslugaBiura = new ContDistUniform(this, "Czas osługi biura", 2.0, 4.0, true, true);
		obslugaStoiska = new ContDistUniform(this,"Czas obsługi stoiska", 12.0, 14.0, true, true);
		czasWRestauracji = new ContDistUniform(this, "Czas spędzony w restauracji", 20.0, 30.0, true, true);

		kasa =new Kasa(this, "other.algo.Kasa", false);

		kasa = new Kasa(this, "Kasa 1", true);
		kasyLista.add(kasa);
		kasyLista.add(new Kasa(this, "Kasa 2", true));
		kasyLista.add(new Kasa(this, "Kasa 3", true));
		kasyLista.add(new Kasa(this, "Kasa 4", true));
		kasyLista.add(new Kasa(this, "Kasa 5", true));
		kasyLista.add(new Kasa(this, "Kasa 6", true));

		akutalniKlienci = new Queue<Klient>(this, "Wszyscy klienci", true, true);
		biuro = new BiuroObslugi(this, "Biuro obsługi", true);
		restauracja = new Restauracja(this, "other.algo.Restauracja", true);
		klienci = new Queue<Klient>(this, "Klienci", true, true);
		konsultant1 = new Konsultant(this, "konsultant1", true );
		konsultant2 = new Konsultant(this, "konsultant2", true );

		meble = new Stoisko(this, "Meble", true, Stoisko.TypStoiska.meble);
		listaStoisk.add(new Stoisko(this, "Meble", true, Stoisko.TypStoiska.meble));
		lazienka = new Stoisko(this, "lazienka", true, Stoisko.TypStoiska.lazienka);
		listaStoisk.add(new Stoisko(this, "Lazienka", true, Stoisko.TypStoiska.lazienka));
		kuchnia = new Stoisko(this, "kuchnia", true, Stoisko.TypStoiska.kuchnia);
		listaStoisk.add(new Stoisko(this, "Kuchnia", true, Stoisko.TypStoiska.kuchnia));
		dekoracje = new Stoisko(this, "dekoracje", true, Stoisko.TypStoiska.dekoracje);
		listaStoisk.add(new Stoisko(this, "Dekoracje", true, Stoisko.TypStoiska.dekoracje));
		sypialnia = new Stoisko(this, "sypialnia", true, Stoisko.TypStoiska.sypialnia);
		listaStoisk.add(new Stoisko(this, "Sypialnia", true, Stoisko.TypStoiska.sypialnia));

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


	generator.schedule(new TimeSpan(3, TimeUnit.MINUTES));


	Wypisz statystyki = new Wypisz(this, "statystyki", false);
	statystyki.schedule(new TimeSpan(45, TimeUnit.MINUTES));

}

	public void inicjalizacjaGeneratora(int prawdopodobienstwoZakupow, int prawdopodobienstwoRestauracji, int prawdopodobienstwoBiura){
		generator = new GeneratorKlientow(this, "generator klientów",false, new TimeSpan(3, TimeUnit.HOURS), new TimeSpan(9, TimeUnit.HOURS), 5);
		generator.setPrawdopodobienstwoZakupow(prawdopodobienstwoZakupow);
		generator.setPrawdopodobienstwoRestauracji(prawdopodobienstwoRestauracji);
		generator.setPrawdopodobienstwoBiura(prawdopodobienstwoBiura);

	}



public double getObslugaKasy() {
	return obslugaKasy.sample();
}


public double getObslugaBiura() {
	return obslugaBiura.sample();
}

public double getObslugaStoiska() {
	return obslugaStoiska.sample();
}

public double getCzasWRestauracji() {
	return czasWRestauracji.sample();
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

public BiuroObslugi getBiuro() {
	return biuro;
}

public Restauracja getRestauracja() {
	return restauracja;
}

public Stoisko getStoisko() {
	return stoisko;
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


	public Stoisko getMeble() {
		return meble;
	}

	public Stoisko getLazienka() {
		return lazienka;
	}

	public Stoisko getKuchnia() {
		return kuchnia;
	}

	public Stoisko getDekoracje() {
		return dekoracje;
	}

	public Stoisko getSypialnia() {
		return sypialnia;
	}

	public int getIloscObsluzonychKlientow() {
		return iloscObsluzonychKlientow;
	}

	public int getWszyscyWRestauracji() {
		return wszyscyWRestauracji;
	}

	public int getWszyscyWBiurze() {
		return wszyscyWBiurze;
	}

	public void setCzasGenerowaniaKlienta(double czasGenerowaniaKlienta) {
		this.czasGenerowaniaKlienta = czasGenerowaniaKlienta;
	}

}

/*
 * sredni czas pobytu klienta, sredni czas obslugi przy poszczegolnych stoiskach, w jakich miejscach gromadza się ludzie
 * sfml - jsfml - 
 * 
 */
