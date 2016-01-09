package rendering;

import algo.Kasa;
import algo.Klient;
import algo.Okienko;

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

    int width = 50;
    int height = 50;

    public KlientRenderer(Klient k){
        this.klient = k;
    }

    public KlientRenderer(ArrayList<Klient> listaKlientow) {
        this.listaKlientow = new ArrayList<>(listaKlientow);
    }


    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(color);
        g2.fillRect((new Random().nextInt(300) + 50)*instancjaOkna.SCALE,
                (new Random().nextInt(300) + 50)*instancjaOkna.SCALE, width, height);
    }
}
