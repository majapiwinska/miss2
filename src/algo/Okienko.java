package algo; /**
 * Created by kasiazukowska on 2015-12-13.
**/
/**
 * Created by kasiazukowska on 2015-12-13.
 **/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Okienko extends JFrame{

    public static ArrayList<Rysownik> obiekty = new ArrayList<Rysownik>();

    public void add(Rysownik obiekt) {
        obiekty.add(obiekt);
    }

    public Okienko(){
        this.setSize(700, 500);
        this.getContentPane().setBackground(new Color(68,64,64));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void paint(Graphics g) {
        //g.setColor(new Color(0,0,0));
        //g.fillRect(100, 100, 50, 50);
        for(Rysownik rys : obiekty){
            rys.rysuj(g);
        }
    }

    public static void main (String[] args){
        Okienko okienko = new Okienko();
        obiekty.add(new KasaG());
        obiekty.add(new KlientG());
        obiekty.add(new WejscieG());
        okienko.repaint();  // ta metode wywolujesz jak np chcesz zeby sie wszystko przerysowalo (jak zmienisz pozycje ludzika, to musisz narysowac wszystko jeszcze raz)

    }

}