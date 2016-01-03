package algo;

import java.util.*;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class Klient extends Entity{

	 Sklep model;
	 Restauracja restauracja;
	 Stoisko stoisko;
	 Kasa kasa;
	 Klient klient;
	 
	 boolean maZakupy;
	 boolean maRestauracje;
	 boolean maBiuro;
	 boolean zjadl = false;
	 boolean zaplacil = false;
	 int ileStoisk;
	int odwiedzone = 0;




	static List<Stoisko.TypStoiska> listaStoisk = Arrays.asList(Stoisko.TypStoiska.values());
	 ArrayList<Stoisko.TypStoiska> ktoreStoiska;
	
	 int liczbaProduktow;
	public boolean zrobilZakupy;
	
	 
	public Klient(Model owner, String name, boolean showTrace) {
		super(owner, name, false);
		
//		System.out.println(owner + " " +name + " " + showTrace );
		
		model = (Sklep)getModel();
		//klient = model.getKlient();
		
	}
	
	

	public ArrayList<Stoisko.TypStoiska> losuj(int ile){


		Map<Stoisko.TypStoiska, Integer> tmpmap = new HashMap<Stoisko.TypStoiska, Integer>();
		Map<Stoisko.TypStoiska, Integer> sortedMap = new LinkedHashMap<Stoisko.TypStoiska, Integer>();

		Collections.shuffle(listaStoisk);
		//Set<Stoisko.TypStoiska> keys = model.mapa.keySet();

		ArrayList tmp = new ArrayList<Stoisko.TypStoiska>();
		ArrayList finalList = new ArrayList<Stoisko.TypStoiska>();
		for(int i = 0; i < ile; i++)
			tmp.add(listaStoisk.get(i));

/*
		for(int i = 0; i < tmp.size(); i++) {
			for (Stoisko.TypStoiska typ : keys) {

				if (tmp.get(i).equals(typ))
					tmpmap.put(typ, model.mapa.get(typ));
			}*/

			List<Map.Entry<Stoisko.TypStoiska, Integer>> list = new LinkedList<Map.Entry<Stoisko.TypStoiska, Integer>>(tmpmap.entrySet());

			Collections.sort(tmp, new Comparator<Stoisko.TypStoiska>() {
				@Override
				public int compare(Stoisko.TypStoiska o1, Stoisko.TypStoiska o2) {
					return ((Integer)(o1.id)).compareTo((Integer)(o2.id));
				}
			});

		/*	for (Iterator<Map.Entry<Stoisko.TypStoiska, Integer>> it = list.iterator(); it.hasNext(); ) {
				Map.Entry<Stoisko.TypStoiska, Integer> entry = it.next();
				sortedMap.put(entry.getKey(), entry.getValue());

			}*/


		return tmp;
		
	};
	
	public boolean czyKupuje(){
		Random rand = new Random();
		boolean czyKupi;
		int i = rand.nextInt(2);
		if(i == 0)
			czyKupi = true;
		else czyKupi = false;
		
		return czyKupi;
	}

	public int kupuje(){
		Random rand = new Random();
		
		int tmp;
		if(czyKupuje()){
			tmp = rand.nextInt(4)+1;
			return getLiczbaProduktow() + tmp; 
		}
		else  
			return  getLiczbaProduktow();
		
		}

	public boolean czyZrobilZakupy() {
		if (getLiczbaProduktow() == 0) {
			zrobilZakupy = false;
			return zrobilZakupy;
		} else

		{
			zrobilZakupy = true;
			return zrobilZakupy;
		}
	}
	
	public boolean jedzenie(){
		restauracja = model.getRestauracja();
		restauracja.wolneStoliki--;
		return zjadl = true;
	}
	
	public void placi(){
		kasa = model.getKasa();
		liczbaProduktow = 0;
		zaplacil = true;
	};
	
	
	
	
	
	public int getIleStoisk() {
		return ileStoisk;
	}
	public void setIleStoisk(int ileStoisk) {
		this.ileStoisk = ileStoisk;
	}
	public boolean getMaZakupy() {
		return maZakupy;
	}

	public void setMaZakupy(boolean maZakupy) {
		this.maZakupy = maZakupy;
	}

	public boolean getMaRestauracje() {
		return maRestauracje;
	}

	public void setMaRestauracje(boolean maRestauracje) {
		this.maRestauracje = maRestauracje;
	}

	public boolean getMaBiuro() {
		return maBiuro;
	}

	public void setMaBiuro(boolean maBiuro) {
		this.maBiuro = maBiuro;
	}


	public ArrayList<Stoisko.TypStoiska> getKtoreStoiska() {
		return ktoreStoiska;
	}


	public void setKtoreStoiska(ArrayList<Stoisko.TypStoiska> ktoreStoiska) {
		this.ktoreStoiska = ktoreStoiska;
	}


	public int getLiczbaProduktow() {
		return liczbaProduktow;
	}


	public void dodajProdukt() {
		this.liczbaProduktow++;
	}


	public void skladaReklamacje() {
		Reklamacja reklamacja = new Reklamacja(model, "reklamacja", true);
		reklamacja.ileReklamacji++;
		
	}

	
}


