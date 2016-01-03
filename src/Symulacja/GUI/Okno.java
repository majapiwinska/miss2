package Symulacja.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by maja on 02.01.16.
 */
public class Okno extends JFrame {

    public Okno(){


        GridLayout experimentLayout = new GridLayout(3,1);
        setLayout(experimentLayout);

        GridLayout layout1 = new GridLayout(2,3);

        JPanel panel1 = new JPanel(layout1);
        JPanel panel2 = new JPanel(layout1);
        JPanel panel3 = new JPanel(layout1);


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
        JLabel maxRestLabel = new JLabel("Wybierz max czas obłsugi klienta w restauracji");
        JLabel maxBiuroLabel = new JLabel("Wybierz max czas obsługi klienta w biurze");

        Container c = getContentPane();
        c.add(panel1);
        c.add(panel2);
        c.add(panel3);

        panel1.setBackground(Color.gray);
        panel1.setSize(255,100);

        panel2.setBackground(Color.gray);
        panel2.setSize(255,100);

        panel3.setBackground(Color.gray);
        panel3.setSize(255,100);


        Integer[] liczbKonsultantowWartosci = {1, 2, 3, 4, 5};
        Integer[] liczbaStolikowWartosci = {5, 10, 15, 20, 25, 30, 35, 40};
        Double[] pojawieniaSieKlientaWartosc = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0};

        final JComboBox<Integer> liczbaKonsultantow = new JComboBox(liczbaStolikowWartosci);
        final JComboBox<Integer> liczbaStolikow = new JComboBox(liczbaStolikowWartosci);
        final JComboBox<Integer> pojawienieSieKlienta = new JComboBox(pojawieniaSieKlientaWartosc);

    }

}

