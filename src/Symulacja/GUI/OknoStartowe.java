package Symulacja.GUI;

import Symulacja.GUI.Panel;
import b.b.J;
import clojure.lang.IFn;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Created by maja on 14.12.15.
 */
public class OknoStartowe extends JFrame{

    JFrame frame = new JFrame();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JComboBox<Integer> konsultantCombo = new JComboBox<Integer>();
    JComboBox<Integer> stolikCombo = new JComboBox<Integer>();
    JComboBox<Double> pojawienieSieCombo = new JComboBox<Double>();
    JComboBox<Double> minCzasStoiska = new JComboBox<Double>();
    JComboBox<Double> minCzasRest = new JComboBox<Double>();
    JComboBox<Double> minCzasBiura = new JComboBox<Double>();
    JComboBox<Double> maxCzasStoiska = new JComboBox<Double>();
    JComboBox<Double> maxCzasRest = new JComboBox<Double>();
    JComboBox<Double> maxCzasBiura = new JComboBox<Double>();
    JLabel konsultantLabel = new JLabel("Wybierz liczbę konsultantów");
    JLabel stolikLabel = new JLabel("Wbierz liczbę stolików w restauracji");
    JLabel pojawienieLabel = new JLabel("Wybierz co jaki czas pojawi się klient (minuty)");
    JLabel minStoiskoLabel = new JLabel("Wybierz min czas obsługi klienta na stoisku");
    JLabel minRestLabel = new JLabel("Wybierz min czas obłsugi klienta w restauraci");
    JLabel minBiuroLaber = new JLabel("Wybierz min czas obsługi klienta w biurze");
    JLabel maxStoiskoLabel = new JLabel("Wybierz max czas obsługi klienta na stoisku");
    JLabel maxRestLabel = new JLabel("Wybierz max czas obłsugi klienta w restauraci");
    JLabel maxBiuroLabel = new JLabel("Wybierz max czas obsługi klienta w biurze");






    public OknoStartowe() throws HeadlessException {

       initComponents();

    }

    public static void main(String [ ] args){


        new OknoStartowe();



    }

    private void initComponents(){

        konsultantCombo.addItem(1);
        konsultantCombo.addItem(2);
        konsultantCombo.addItem(3);
        konsultantCombo.addItem(4);
        konsultantCombo.addItem(5);

        stolikCombo.addItem(5);
        stolikCombo.addItem(10);
        stolikCombo.addItem(15);
        stolikCombo.addItem(20);
        stolikCombo.addItem(25);
        stolikCombo.addItem(30);
        stolikCombo.addItem(35);
        stolikCombo.addItem(40);

        pojawienieSieCombo.addItem(0.5);
        pojawienieSieCombo.addItem(1.0);
        pojawienieSieCombo.addItem(1.5);
        pojawienieSieCombo.addItem(2.0);
        pojawienieSieCombo.addItem(2.5);
        pojawienieSieCombo.addItem(3.0);

        minCzasStoiska.addItem(2.0);
        minCzasStoiska.addItem(3.0);
        minCzasStoiska.addItem(4.0);
        minCzasStoiska.addItem(5.0);

        maxCzasStoiska.addItem(6.0);
        maxCzasStoiska.addItem(7.0);
        maxCzasStoiska.addItem(8.0);
        maxCzasStoiska.addItem(9.0);
        maxCzasStoiska.addItem(10.0);
        maxCzasStoiska.addItem(11.0);
        maxCzasStoiska.addItem(12.0);
        maxCzasStoiska.addItem(13.0);
        maxCzasStoiska.addItem(14.0);
        maxCzasStoiska.addItem(15.0);

        minCzasRest.addItem(20.0);
        minCzasRest.addItem(23.0);
        minCzasRest.addItem(26.0);

        maxCzasRest.addItem(29.0);
        maxCzasRest.addItem(32.0);
        maxCzasRest.addItem(35.0);
        maxCzasRest.addItem(38.0);
        maxCzasRest.addItem(41.0);
        maxCzasRest.addItem(44.0);
        maxCzasRest.addItem(47.0);
        maxCzasRest.addItem(50.0);

        minCzasBiura.addItem(2.0);
        minCzasBiura.addItem(4.0);

        maxCzasBiura.addItem(6.0);
        maxCzasBiura.addItem(8.0);
        maxCzasBiura.addItem(10.0);

        panel1.add(konsultantLabel, BorderLayout.WEST);
        panel1.add(konsultantCombo, BorderLayout.WEST);
        panel1.add(stolikLabel, BorderLayout.CENTER);
        panel1.add(stolikCombo, BorderLayout.CENTER);
        panel1.add(pojawienieLabel, BorderLayout.EAST);
        panel1.add(pojawienieSieCombo, BorderLayout.EAST);

        panel2.add(minStoiskoLabel, BorderLayout.WEST);
        panel2.add(minCzasStoiska, BorderLayout.WEST);
        panel2.add(minBiuroLaber, BorderLayout.CENTER);
        panel2.add(minCzasBiura, BorderLayout.CENTER);
        panel2.add(minRestLabel, BorderLayout.EAST);
        panel2.add(minCzasRest, BorderLayout.EAST);

        panel3.add(maxStoiskoLabel, BorderLayout.WEST);
        panel3.add(maxCzasStoiska , BorderLayout.WEST);
        panel3.add(maxBiuroLabel, BorderLayout.CENTER);
        panel3.add(maxCzasBiura, BorderLayout.CENTER);
        panel3.add(maxRestLabel, BorderLayout.EAST);
        panel3.add(maxCzasRest, BorderLayout.EAST);

        frame.setSize(600, 600);
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}

