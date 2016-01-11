package algo;

import java.awt.*;
import java.util.List;
import java.util.Random;

import java.util.*;

import algo.primitives.*;
import algo.primitives.Point;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import rendering.KlientRenderer;

import javax.swing.*;

public class Klient extends Entity {
    ArrayList<algo.primitives.Point> path = new ArrayList<>();
    public ArrayList<Point> getPath() {
        return path;
    }

    Color myColor = new Color(new Random().nextInt(254), new Random().nextInt(254), new Random().nextInt(254));
    public Color getMyColor() {
        return myColor;
    }

    Restauracja restauracja;
    Kasa kasa;

    Sklep model;
    Klient klient;

    boolean maZakupy;
    boolean maRestauracje;
    boolean maBiuro;
    boolean zjadl = false;
    boolean zaplacil = false;
    int ileStoisk;
    int odwiedzone = 0;
    int x, y;

    public int globalPathCounter=0;
    public int getGlobalPathCounter() {
        return globalPathCounter;
    }

    static List<Stoisko.TypStoiska> listaStoisk = Arrays.asList(Stoisko.TypStoiska.values());
    ArrayList<Stoisko.TypStoiska> ktoreStoiska;

    int liczbaProduktow;
    public boolean zrobilZakupy;

    Renderer renderer;

    public Klient(Model owner, String name, boolean showTrace) {
        super(owner, name, false);

        model = (Sklep) getModel();
        this.x = 400;
        this.y = 455;

        for(int i = 400; i>=90; i= i-10) {
            path.add(new Point(i, 455));
        }
        for(int i=445; i>=345; i= i-10) {
            path.add(new Point(90, i));
        }
        for(int i=100; i<=400; i= i+10) {
            path.add(new Point(i, 345));
        }
        for(int i=345; i>=200; i= i-10) {
            path.add(new Point(400, i));
        }
        for(int i=400; i>=90; i= i-10) {
            path.add(new Point(i, 200));
        }
        for(int i=200; i>=40; i= i-10) {
            path.add(new Point(90, i));
        }
        for(int i=90; i<=617; i= i+10) {
            path.add(new Point(i, 40));
        }
        for(int i=40; i<=455; i= i+10) {
            path.add(new Point(617, i));
        }
    }

    public void move() {
//        this.x += 10;
//        this.y += 10;
       try{
           this.x=path.get(globalPathCounter).getIntX();
           this.y=path.get(globalPathCounter).getIntY();
           globalPathCounter++;
       }
       catch (IndexOutOfBoundsException e) {}
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public ArrayList<Stoisko.TypStoiska> losuj(int ile) {


        Map<Stoisko.TypStoiska, Integer> tmpmap = new HashMap<Stoisko.TypStoiska, Integer>();
        Map<Stoisko.TypStoiska, Integer> sortedMap = new LinkedHashMap<Stoisko.TypStoiska, Integer>();

        Collections.shuffle(listaStoisk);

        ArrayList tmp = new ArrayList<Stoisko.TypStoiska>();
        ArrayList finalList = new ArrayList<Stoisko.TypStoiska>();
        for (int i = 0; i < ile; i++)
            tmp.add(listaStoisk.get(i));

        List<Map.Entry<Stoisko.TypStoiska, Integer>> list = new LinkedList<Map.Entry<Stoisko.TypStoiska, Integer>>(tmpmap.entrySet());

        Collections.sort(tmp, new Comparator<Stoisko.TypStoiska>() {
            @Override
            public int compare(Stoisko.TypStoiska o1, Stoisko.TypStoiska o2) {
                return ((Integer) (o1.id)).compareTo((Integer) (o2.id));
            }
        });


        return tmp;

    }

    ;

    public boolean czyKupuje() {
        Random rand = new Random();
        boolean czyKupi;
        int i = rand.nextInt(2);
        if (i == 0)
            czyKupi = true;
        else czyKupi = false;

        return czyKupi;
    }

    public int kupuje() {
        Random rand = new Random();

        int tmp;
        if (czyKupuje()) {
            tmp = rand.nextInt(4) + 1;
            return getLiczbaProduktow() + tmp;
        } else
            return getLiczbaProduktow();

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
    public boolean jedzenie() {
        restauracja = model.getRestauracja();
        restauracja.wolneStoliki--;
        return zjadl = true;
    }

    public void placi() {
        kasa = model.getKasa();
        liczbaProduktow = 0;
        zaplacil = true;
    }

    ;


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


