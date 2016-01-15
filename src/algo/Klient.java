package algo;

import java.awt.*;
import java.util.List;
import java.util.Random;

import java.util.*;

import Symulacja.GUI.OknoStartowe;
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

    public int globalPathCounter = 0;

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

        for (int i = 400; i >= 90; i = i - 10) {
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
        for (int i = 90; i <= 617; i = i + 10) {
            path.add(new Point(i, 40));
        }
        for (int i = 40; i < 250; i = i + 10) {
            path.add(new Point(617, i));
        }
        {
//            int prawdopodobienstwoZakupow = OknoStartowe.getInstance().getPrawdopodobienstwoZakupow();
            int prawdopodobienstwoZakupow = 80;
            Random generator = new Random();
            int k = generator.nextInt(100);
            if (k <= prawdopodobienstwoZakupow) {               // rozdzielenie do poszczególnych kas
                //idziesz do kasy
                int l = generator.nextInt(4);
                if (l % 4 == 0) {
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((617 - (j * 10)), (250 + (j * 10))));
                    }
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((547 + (j * 10)), (320 + (j * 10))));
                    }
                } else if (l % 4 == 1) {
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((617 - (j * 5)), (250 + (j * 10))));
                    }
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((582 + (j * 5)), (320 + (j * 10))));
                    }
                } else if (l % 4 == 2) {
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((617 + (j * 5)), (250 + (j * 10))));
                    }
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((652 - (j * 5)), (320 + (j * 10))));
                    }
                } else if (l % 4 == 3) {
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((617 + (j * 10)), (250 + (j * 10))));
                    }
                    for (int j = 0; j <= 7; j++) {
                        path.add(new Point((687 - (j * 10)), (320 + (j * 10))));
                    }
                }
            } else {
                for (int r = 250; r < 390; r = r + 10) {
                    path.add(new Point(617, r));
                }
            }
        }
        for (int i = 390; i <= 450; i = i + 10) {       // dokończenie ścieżki od łączenia z tymi co wyszli z kas
            path.add(new Point(617, i));
        }
    }

    public void move() {
//        this.x += 10;
//        this.y += 10;
        try {
            this.x = path.get(globalPathCounter).getIntX();
            this.y = path.get(globalPathCounter).getIntY();
            globalPathCounter++;
        } catch (IndexOutOfBoundsException e) {
        }
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


