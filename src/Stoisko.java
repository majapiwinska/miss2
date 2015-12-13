import java.util.*;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;


public class Stoisko extends Entity {

	public int klienciNaStoisku;
	private boolean otwarte;
	protected TypStoiska typ;
	Sklep model;
	Random rand = new Random();
	protected Queue<Klient> kolejkaStoisko;
	
	
	public int aktualniKlienciNaStoisku = 0;
	
	public enum TypStoiska{	
		meble, 
		sypialnia, 
		dekoracje, 
		kuchnia, 
		lazienka;
	};
	
	boolean odwiedzone;

	public Stoisko(Model owner, String name, boolean showTrace, TypStoiska typ) {
		super(owner, name, showTrace);
		model = (Sklep)getModel();
		this.typ = typ;
		kolejkaStoisko = new Queue<Klient>(model, "Klienci na stoiskiu", true, true );
	}
	
	
	
	
	public Queue<Klient> getKolejkaStoisko() {
		return kolejkaStoisko;
	}

	public void setKolejkaStoisko(Queue<Klient> kolejkaStoisko) {
		this.kolejkaStoisko = kolejkaStoisko;
	}
	

	public int getKlienciNaStoisku() {
		return klienciNaStoisku;
	}
	public void setKlienciNaStoisku(int klienciNaStoisku) {
		this.klienciNaStoisku = klienciNaStoisku;
	}
	public boolean isOtwarte() {
		return otwarte;
	}
	public void setOtwarte(boolean otwarte) {
		this.otwarte = otwarte;
	}


	public TypStoiska getTyp() {
		return typ;
	}


	public void setTyp(TypStoiska typ) {
		this.typ = typ;
	}

	public void dodajKlienta() {
		this.aktualniKlienciNaStoisku++;
	}

	public void odejmijKlienta() {
		this.aktualniKlienciNaStoisku--;
	}


}