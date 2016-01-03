package algo;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;


public class Restauracja extends Entity{
	
	Sklep model;
	Queue<Klient>  kolejkaDoRestauracji;
	int klienciWRestauracji;
	int wolneStoliki = 60;
	
	
	public Restauracja(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		// TODO Auto-generated constructor stub
		model = (Sklep)getModel();
				
		kolejkaDoRestauracji = new Queue<Klient> (model, "Klienci do restauracji", true, true);
	}
	
	
	public Queue<Klient> getKolejkaDoRestauracji() {
		return kolejkaDoRestauracji;
	}
	public void setKolejkaDoRestauracji(Queue<Klient> kolejkaDoRestauracji) {
		this.kolejkaDoRestauracji = kolejkaDoRestauracji;
	}
	public int getKlienciWRestauracji() {
		return klienciWRestauracji;
	}
	public void setKlienciWRestauracji(int klienciWRestauracji) {
		this.klienciWRestauracji = klienciWRestauracji;
	}
	public int getWolneStoliki() {
		return wolneStoliki;
	}
	public void setWolneStoliki(int wolneStoliki) {
		this.wolneStoliki = wolneStoliki;
	}

}
