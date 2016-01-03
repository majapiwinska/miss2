package algo;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;



public class BiuroObslugi extends Entity{

	Sklep model;
	protected Queue<Klient> kolejkaDoBiura;
	int klienciWBiurze;
	protected Queue<Konsultant> wolniKonsultanci;

	
	public BiuroObslugi(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
		
		model = (Sklep)getModel();
		kolejkaDoBiura = new Queue<Klient> (model, "Klienci do biura", true, true);
		wolniKonsultanci = new Queue<Konsultant> (model, "Wolni konsultanci", true, true);

	}
	
	
}
