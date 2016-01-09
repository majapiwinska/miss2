package algo;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;


public class Restauracja extends Entity{
	
	Sklep model;
	Queue<Klient>  kolejkaDoRestauracji;
	int klienciWRestauracji;
	int wolneStoliki = 40;

	
	public Restauracja(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
		model = (Sklep)getModel();
				
		kolejkaDoRestauracji = new Queue<Klient> (model, "Klienci do restauracji", true, true);
	}



}
