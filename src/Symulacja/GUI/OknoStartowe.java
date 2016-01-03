package Symulacja.GUI;



import algo.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoStartowe extends JFrame{


    static GridLayout layout1 = new GridLayout(2,3);
    static GridLayout layout2 = new GridLayout(4,1);

    final static JPanel panel1 = new JPanel(layout1);
    final static JPanel panel2 = new JPanel(layout1);
    final static JPanel panel3 = new JPanel(layout1);
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
    final static JButton startButton = new JButton("Start");



    public OknoStartowe() throws HeadlessException {

       initComponents();

    }

    public static void main(String [ ] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame();
                frame.setLayout(layout2);
                frame.setSize(1200, 600);
                frame.add(panel1);
                frame.add(panel2);
                frame.add(panel3);
                frame.add(startButton);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                new OknoStartowe();
            }
        });




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

        panel1.add(konsultantLabel);
        panel1.add(konsultantCombo);
        panel1.add(stolikLabel);
        panel1.add(stolikCombo);
        panel1.add(pojawienieLabel);
        panel1.add(pojawienieSieCombo);

        panel2.add(minStoiskoLabel);
        panel2.add(minCzasStoiska);
        panel2.add(minBiuroLaber);
        panel2.add(minCzasBiura);
        panel2.add(minRestLabel);
        panel2.add(minCzasRest);

        panel3.add(maxStoiskoLabel);
        panel3.add(maxCzasStoiska);
        panel3.add(maxBiuroLabel);
        panel3.add(maxCzasBiura);
        panel3.add(maxRestLabel);
        panel3.add(maxCzasRest);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

}

