import java.*;


import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;


public class Kasa extends Entity{


	protected Queue<Klient> kolejkaDoKas, klientWKasach;
	private OtwarteKasy kasa1, kasa2, kasa3, kasa4;
	protected Queue<OtwarteKasy> otwarteKasy;
	
	
	
	public Kasa(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		Sklep model = (Sklep)getModel();
		kasa1 = new OtwarteKasy(model, "kasa1", true);
		kasa2 = new OtwarteKasy(model, "kasa2", true);
		kasa3 = new OtwarteKasy(model, "kasa3", true);
		otwarteKasy = new Queue<OtwarteKasy>(model, "otwarte kasy", true, true);
		kolejkaDoKas = new Queue<Klient>(model, "kolejka do kasy", true, true);
		klientWKasach = new Queue<Klient>(model, "Klienci przy kasach", true, true);
		otwarteKasy.insert(kasa1);
		otwarteKasy.insert(kasa2);
		otwarteKasy.insert(kasa3);
		otwarteKasy.insert(kasa4);
		
	}

    public Queue<Klient> getKolejkaDoKas() {
        return kolejkaDoKas;
    }
	
	
	
	
}