package algo;

import java.util.*;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;


public class Stoisko extends Entity {

	public int klienciNaStoisku;
	private boolean otwarte;
	protected TypStoiska typ;
	Sklep model;
	protected Queue<Klient> kolejkaStoisko;
	
	
	public int aktualniKlienciNaStoisku = 0;
	
	public enum TypStoiska{	
		meble(5),
		sypialnia(1),
		dekoracje(2),
		kuchnia(3),
		lazienka(4);

		TypStoiska(int id){
			this.id = id;
		}
		public int id;


	};

	public Stoisko(Model owner, String name, boolean showTrace, TypStoiska typ) {
		super(owner, name, showTrace);
		model = (Sklep)getModel();
		this.typ = typ;
		kolejkaStoisko = new Queue<Klient>(model, "Klienci na stoiskiu", true, true );
	}



	public TypStoiska getTyp() {
		return typ;
	}


	public void dodajKlienta() {
		this.aktualniKlienciNaStoisku++;
	}

	public void odejmijKlienta() {
		this.aktualniKlienciNaStoisku--;
	}


}