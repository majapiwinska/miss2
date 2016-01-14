package algo; /**
 * Created by kasiazukowska on 2015-12-13.
 **/
import rendering.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Okienko extends JFrame {

    private static KasyRender kasyPanel;
    private static StoiskaRender stoiskaPanel;
    private static RestauracjaRender restauracjaPanel;
    private static BiuroRender biuroPanel;
    private static KlientRenderer klientPanel;
    public int SCALE = 3;
    private int ji=0;
    int counter = 0;


    private static Okienko instance = null;
    public static Okienko getInstance(){
        if(instance==null) {
            return instance = new Okienko();
        }
        else {
            return instance;
        }
    }

    public Okienko() {
        this.setTitle("Uboga Ikea");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(790, 550));
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("MISS2/src/algo/ikea.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(null);

        Sklep.getInstance().executeSklep();

        kasyPanel = new KasyRender(Sklep.getKasyLista());
        kasyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));   //layout
        kasyPanel.setBounds(0,0,800,500);       // wymiary panelu
        kasyPanel.setOpaque(false);     //zeby nie bylo przezroczyste

        stoiskaPanel = new StoiskaRender(Sklep.getListaStoisk());
        stoiskaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        stoiskaPanel.setBounds(0,0,800,500);
        stoiskaPanel.setOpaque(false);

        restauracjaPanel = new RestauracjaRender(Sklep.getListaRestauracji());
        restauracjaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        restauracjaPanel.setBounds(0,0,800,500);
        restauracjaPanel.setOpaque(false);

        biuroPanel = new BiuroRender(Sklep.getListaBiur());
        biuroPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        biuroPanel.setBounds(0,0,800,500);
        biuroPanel.setOpaque(false);

        klientPanel = new KlientRenderer(Sklep.getListaKlientow());
        klientPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        klientPanel.setBounds(0,0,800,500);
        klientPanel.setOpaque(false);

        this.add(kasyPanel);        //adding panel to frame
        kasyPanel.repaint();
        this.add(stoiskaPanel);
        stoiskaPanel.repaint();
        this.add(restauracjaPanel);
        restauracjaPanel.repaint();
        this.add(biuroPanel);
        biuroPanel.repaint();
        this.add(klientPanel);
        klientPanel.repaint();


        Runnable animation = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Klient k : Sklep.getListaKlientow()) {
                        k.move();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    klientPanel.repaint();
                    //if(counter%100) <= Sklep.getInstance().getWszyscyKlienci()) {
                    if(counter <= Sklep.getInstance().getWszyscyKlienci()) {
                        ArrayList<Klient> tempLista = Sklep.getListaKlientow();
                        tempLista.add(new Klient(Sklep.getInstance(), "KlientX", true));
                        Sklep.setListaKlientow(tempLista);

                    }
                    counter++;
                }
            }
        };

        new Thread(animation).start();

        this.pack();
        this.setVisible(true);
    }

    public static void main (String[] args) {
        getInstance();

    }

}