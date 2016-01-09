package other; /**
 * Created by kasiazukowska on 2015-12-13.
 **/
import rendering.KasyRender;
import rendering.StoiskaRender;

import javax.swing.*;
import java.awt.*;

public class Okienko extends JFrame {

    private static KasyRender kasyPanel;
    private static StoiskaRender stoiskaPanel;
    public int SCALE = 3;

//    public static ArrayList<other.Rysownik> obiekty = new ArrayList<>();
//    public static ArrayList<Stoisko> stoiskaLista = new ArrayList<>();

//    public void add(other.Rysownik obiekt) {
//        obiekty.add(obiekt);
//    }

    private static Okienko instance = null;
    public static Okienko getInstance(){
        if(instance==null)
            return instance=new Okienko();
        else
            return instance;
    }


    public Okienko() {
        this.setTitle("Uboga Ikea");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(760, 500));
        this.getContentPane().setBackground(new Color(255, 255, 255));
//        Color gray = new Color(68,64,64);
//        this.setBackground(gray);
        this.setLayout(null);

//        Sklep sklep = new Sklep(null, "Symulacja", true, true);
        Sklep sklep = Sklep.getInstance();
        sklep.executeSklep();

        kasyPanel = new KasyRender(Sklep.getKasyLista());
        kasyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));   //layout
        kasyPanel.setBounds(0,0,700,500);       // wymiary panelu
        kasyPanel.setBackground(new Color(255,255,255));
        kasyPanel.setOpaque(false);     //zeby nie bylo przezroczyste

        stoiskaPanel = new StoiskaRender(Sklep.getListaStoisk());
        stoiskaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        stoiskaPanel.setBounds(0,0,800,500);
        stoiskaPanel.setOpaque(false);

        this.add(kasyPanel);        //adding panel to frame
        kasyPanel.repaint();

        this.add(stoiskaPanel);
        stoiskaPanel.repaint();

        this.pack();
        this.setVisible(true);
    }

    public static void main (String[] args) {
        getInstance();

        //region zliczanie elementów przestrzeni
//        for(other.Rysownik rys : obiekty) {       // tutaj zliczamy ile czego mamy, żeby potem wyświetlić. Musimy to wiedzieć żeby w tym momencie wiedziec ile rysujamy
//            if(rys.getClass().isInstance(other.KasaG.class)) kasyIlosc++;
//            if(rys.getClass().isInstance(other.Stoisko.class)) stoiskaIlosc++;
//            if(rys.getClass().isInstance(other.BiuroObslugi.class)) biuraObslugiIlosc++;
//            if(rys.getClass().isInstance(other.Restauracja.class)) restauracjeIlosc++;
//        }
        //endregion

    }

}