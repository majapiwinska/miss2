package algo;

import java.util.*;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class Klient extends Entity{

	 Sklep model;
	 Restauracja restauracja;
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

		model = (Sklep)getModel();
	}
	
	

	public ArrayList<Stoisko.TypStoiska> losuj(int ile){


		Map<Stoisko.TypStoiska, Integer> tmpmap = new HashMap<Stoisko.TypStoiska, Integer>();
		Map<Stoisko.TypStoiska, Integer> sortedMap = new LinkedHashMap<Stoisko.TypStoiska, Integer>();

		Collections.shuffle(listaStoisk);

		ArrayList tmp = new ArrayList<Stoisko.TypStoiska>();
		ArrayList finalList = new ArrayList<Stoisko.TypStoiska>();
		for(int i = 0; i < ile; i++)
			tmp.add(listaStoisk.get(i));

			List<Map.Entry<Stoisko.TypStoiska, Integer>> list = new LinkedList<Map.Entry<Stoisko.TypStoiska, Integer>>(tmpmap.entrySet());

			Collections.sort(tmp, new Comparator<Stoisko.TypStoiska>() {
				@Override
				public int compare(Stoisko.TypStoiska o1, Stoisko.TypStoiska o2) {
					return ((Integer)(o1.id)).compareTo((Integer)(o2.id));
				}
			});


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

/*	public boolean czyZrobilZakupy() {
		if (getLiczbaProduktow() == 0) {
			zrobilZakupy = false;
			return zrobilZakupy;
		} else

		{
			zrobilZakupy = true;
			return zrobilZakupy;
		}
	}
	*/
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
	

	public void setMaZakupy(boolean maZakupy) {
		this.maZakupy = maZakupy;
	}


	public void setMaRestauracje(boolean maRestauracje) {
		this.maRestauracje = maRestauracje;
	}

	public void setMaBiuro(boolean maBiuro) {
		this.maBiuro = maBiuro;
	}


	public ArrayList<Stoisko.TypStoiska> getKtoreStoiska() {
		return ktoreStoiska;
	}

	public int getLiczbaProduktow() {
		return liczbaProduktow;
	}


	public void skladaReklamacje() {
		Reklamacja reklamacja = new Reklamacja(model, "reklamacja", true);
		reklamacja.ileReklamacji++;
		
	}

	
}


