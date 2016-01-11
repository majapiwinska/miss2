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
        System.out.println("Asdasd");
        instancjaOkna = Okienko.getInstance();
        //g.clearRect(0,0, this.getWidth(), this.getHeight());
       // g.fillRect(50,50,100,100);
        //g.fillRect((new Random().nextInt(700) + 50),(new Random().nextInt(700) + 50), width, height);
//        for (int i = 0; i < listaKlientow.size(); i++){
        synchronized (listaKlientow = Sklep.getListaKlientow()) {
            for (Klient k : listaKlientow) {
//            g.fillRect((new Random().nextInt(700) + 50)*i*instancjaOkna.SCALE,(new Random().nextInt(700) + 50)*i*instancjaOkna.SCALE, width, height);
                g.setColor(k.getMyColor());
//            g.fillRect(400,455,width,height);
                g.fillOval(listaKlientow.get(listaKlientow.indexOf(k)).getPath().get(k.getGlobalPathCounter()).getIntX(), listaKlientow.get(listaKlientow.indexOf(k)).getPath().get(k.getGlobalPathCounter()).getIntY(), width, height);
//            g.fillRect(617,455,width, height);
//                g.setColor(color);
            }
        }
    }
}