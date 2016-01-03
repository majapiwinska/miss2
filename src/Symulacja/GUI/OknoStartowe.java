package Symulacja.GUI;



import algo.Sklep;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


    public class OknoStartowe extends JFrame{

    public double czasPojawieniaKlienta;
    private int prawdopodobienstwoZakupow, prawdopodobienstwoRestauracji, prawdopodobienstwoBiura;



        static GridLayout layout1 = new GridLayout(2,3);
        static GridLayout layout2 = new GridLayout(4,1);

        final static JPanel panel1 = new JPanel(layout1);
        final static JPanel panel2 = new JPanel(layout1);
        final static JPanel panel3 = new JPanel(layout1);
        //JComboBox<Integer> konsultantCombo = new JComboBox<Integer>();
        final JComboBox<Integer> stolikCombo = new JComboBox<Integer>();
         JComboBox<Double> pojawienieSieCombo = new JComboBox<Double>();
        final JComboBox<Double> minCzasStoiska = new JComboBox<Double>();
        JComboBox<Double> minCzasRest = new JComboBox<Double>();
        JComboBox<Double> minCzasBiura = new JComboBox<Double>();
        JComboBox<Double> maxCzasStoiska = new JComboBox<Double>();
        JComboBox<Double> maxCzasRest = new JComboBox<Double>();
        JComboBox<Double> maxCzasBiura = new JComboBox<Double>();
      //  JLabel konsultantLabel = new JLabel("Wybierz liczbę konsultantów");
        JLabel stolikLabel = new JLabel("Wbierz liczbę stolików w restauracji");
        JLabel pojawienieLabel = new JLabel("Wybierz co jaki czas pojawi się klient (minuty)");
        JLabel minStoiskoLabel = new JLabel("Wybierz min czas obsługi klienta na stoisku");
        JLabel minRestLabel = new JLabel("Wybierz min czas obłsugi klienta w restauraci");
        JLabel minBiuroLaber = new JLabel("Wybierz min czas obsługi klienta w biurze");
        JLabel maxStoiskoLabel = new JLabel("Wybierz max czas obsługi klienta na stoisku");
        JLabel maxRestLabel = new JLabel("Wybierz max czas obłsugi klienta w restauracji");
        JLabel maxBiuroLabel = new JLabel("Wybierz max czas obsługi klienta w biurze");

        final static JButton startButton = new JButton("Start");
        final static JTextField zakupyJTF = new JTextField("Prawdopodobienstwo zakupów w %");
        final static JTextField restauracjaJTF = new JTextField("Prawdopodobieństwo restauracji w &");
        final static JTextField biuroJTF = new JTextField("Prawdopodobienstwo biura w %");





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
                    frame.add(startButton);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                    new OknoStartowe();
                }
            });




        }

        private void initComponents(){

            pojawienieSieCombo.addItem(1.0);
            pojawienieSieCombo.addItem(2.0);
            pojawienieSieCombo.addItem(3.0);
            pojawienieSieCombo.addItem(4.0);
            pojawienieSieCombo.addItem(5.0);
            pojawienieSieCombo.addItem(6.0);
            pojawienieSieCombo.addItem(7.0);
            pojawienieSieCombo.addItem(8.0);
            pojawienieSieCombo.addItem(9.0);
            pojawienieSieCombo.addItem(10.0);


            panel1.add(pojawienieSieCombo);
            panel1.add(zakupyJTF);
            panel1.add(restauracjaJTF);
            panel1.add(biuroJTF);


            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startButtonAction(e);
                }
            });

            pojawienieSieCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pojawienieSieComboAction(e);
                }
            });

            zakupyJTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = zakupyJTF.getText();
                    prawdopodobienstwoZakupow = Integer.parseInt(text);
                }
            });

            restauracjaJTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = restauracjaJTF.getText();
                    prawdopodobienstwoRestauracji = Integer.parseInt(text);
                }
            });

            biuroJTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = biuroJTF.getText();
                    prawdopodobienstwoBiura = Integer.parseInt(text);
                }
            });

        }

        private void pojawienieSieComboAction(ActionEvent e) {
                pojawienieSieCombo = (JComboBox)e.getSource();
                czasPojawieniaKlienta = (Double)pojawienieSieCombo.getSelectedItem();

        }

        private void startButtonAction(ActionEvent e) {
            Sklep sklep = new Sklep(null, "Symulacja", false, false);
            sklep.setCzasGenerowaniaKlienta(czasPojawieniaKlienta);

            Experiment exp = new Experiment("Symulacja - eksperyment", TimeUnit.SECONDS, TimeUnit.MINUTES, null);
            sklep.connectToExperiment(exp);

            sklep.inicjalizacjaGeneratora(prawdopodobienstwoZakupow, prawdopodobienstwoRestauracji, prawdopodobienstwoBiura);
            exp.setShowProgressBar(true);
            exp.stop(new TimeInstant(720, TimeUnit.MINUTES));
            exp.tracePeriod(new TimeInstant(0), new TimeInstant(100, TimeUnit.MINUTES));
            exp.debugPeriod(new TimeInstant(0), new TimeInstant(50, TimeUnit.MINUTES));

            exp.start();

            exp.report();

            System.out.println("Ilość obsłużonych łacznie klientów w kasach: " + sklep.getIloscObsluzonychKlientow());
            System.out.println("Ilość obsłużonych łacznie klientów na stoisku meble: " +sklep.getMeble().klienciNaStoisku);
            System.out.println("Ilość obsłużonych łacznie klientów na stoisku dekoracje: " +sklep.getDekoracje().klienciNaStoisku);
            System.out.println("Ilość obsłużonych łacznie klientów na stoisku lazienka: " +sklep.getLazienka().klienciNaStoisku);
            System.out.println("Ilość obsłużonych łacznie klientów na stoisku kuchnia: " +sklep.getKuchnia().klienciNaStoisku);
            System.out.println("Ilość obsłużonych łacznie klientów na stoisku sypialnia: " +sklep.getSypialnia().klienciNaStoisku);
            System.out.println("Ilość ludzi w sklepie przez cały dzień: " + sklep.getWszyscyKlienci()+ "\r\n");
            System.out.println("Ilość wszystkich klientów w restauracji przez cały dzień: " + sklep.getWszyscyWRestauracji());
            System.out.println("Ilość wszystkich klientów w bierze przez cały dzień: " + sklep.getWszyscyWBiurze());

            exp.finish();

        }
    }

