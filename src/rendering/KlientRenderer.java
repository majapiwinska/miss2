package rendering;

import algo.Kasa;
import algo.Klient;
import algo.Okienko;
import algo.Sklep;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kasiazukowska on 2016-01-09.
 */
public class KlientRenderer extends JPanel {
    Klient klient;
    Sklep sklep = new Sklep(null, "Symulacja", false, false);
    Okienko instancjaOkna;
    Color color = new Color(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256));
    ArrayList<Klient> listaKlientow = new ArrayList<>();

    int width = 10;
    int height = 10;

    public KlientRenderer(Klient k){
        this.klient = k;
    }

    public KlientRenderer(ArrayList<Klient> listaKlientow) {
        this.listaKlientow = new ArrayList<>(listaKlientow);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int counter = 0;
        instancjaOkna = Okienko.getInstance();
        synchronized (listaKlientow = Sklep.getListaKlientow()) {
            while (counter <= sklep.getWszyscyKlienci()){
                for (Klient k : listaKlientow) {
                    g.setColor(k.getMyColor());
                    try{
                        g.fillOval(listaKlientow.get(listaKlientow.indexOf(k)).getPath().get(k.getGlobalPathCounter()).getIntX(), listaKlientow.get(listaKlientow.indexOf(k)).getPath().get(k.getGlobalPathCounter()).getIntY(), width, height);
                        counter++;
                    }
                    catch(IndexOutOfBoundsException e) {}
                }
            }
        }
    }
}