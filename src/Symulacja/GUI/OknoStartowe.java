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

        final static JPanel panel1 = new JPanel();
        final static JPanel panel2 = new JPanel();
        final static JPanel panel3 = new JPanel();
        final static JPanel panel4 = new JPanel();
        final static JPanel panel5 = new JPanel();
         JComboBox<Double> pojawienieSieCombo = new JComboBox<Double>();
        final JComboBox<Double> minCzasStoiska = new JComboBox<Double>();

        JLabel pojawienieLabel = new JLabel("Wybierz co jaki czas pojawi się klient (minuty)");
        JLabel zakupyLabel = new JLabel("Wpisz % generowania prawdopodobieństwa zakupów dla klientów");
        JLabel biuroLabel = new JLabel("Wpisz % generowania prawdopodobieństwa pójścia do biura dla klientów");
        JLabel restauracjaLabel = new JLabel("Wpisz % generowania prawdopodobieństwa pójścia do restauracji dla klientów");

        final static JButton startButton = new JButton("Start");
        final static JComboBox  zakupyJTF = new JComboBox();
        final static JComboBox restauracjaJTF = new JComboBox();
        final static JComboBox biuroJTF = new JComboBox();





        public OknoStartowe() throws HeadlessException {

           initComponents();

        }

        public static void main(String [ ] args){

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    final JFrame frame = new JFrame();
                    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
                    frame.setSize(900, 250);
                    frame.add(panel1);
                    frame.add(panel2);
                    frame.add(panel3);
                    frame.add(panel4);
                    frame.add(panel5);
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

            zakupyJTF.addItem("10");
            zakupyJTF.addItem("20");
            zakupyJTF.addItem("30");
            zakupyJTF.addItem("40");
            zakupyJTF.addItem("50");
            zakupyJTF.addItem("60");
            zakupyJTF.addItem("70");
            zakupyJTF.addItem("80");
            zakupyJTF.addItem("90");



            restauracjaJTF.addItem("10");
            restauracjaJTF.addItem("20");
            restauracjaJTF.addItem("30");
            restauracjaJTF.addItem("40");
            restauracjaJTF.addItem("50");
            restauracjaJTF.addItem("60");
            restauracjaJTF.addItem("70");
            restauracjaJTF.addItem("80");
            restauracjaJTF.addItem("90");



            biuroJTF.addItem("10");
            biuroJTF.addItem("20");
            biuroJTF.addItem("30");
            biuroJTF.addItem("40");
            biuroJTF.addItem("50");
            biuroJTF.addItem("60");
            biuroJTF.addItem("70");
            biuroJTF.addItem("80");

            panel1.add(pojawienieLabel);
            panel1.add(pojawienieSieCombo);
            panel2.add(zakupyLabel);
            panel2.add(zakupyJTF);
            panel3.add(restauracjaLabel);
            panel3.add(restauracjaJTF);
            panel4.add(biuroLabel);
            panel4.add(biuroJTF);
            panel5.add(startButton);


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
                    JComboBox cb = (JComboBox)e.getSource();
                    prawdopodobienstwoZakupow = Integer.parseInt((String)cb.getSelectedItem());

                }
            });




            restauracjaJTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    prawdopodobienstwoRestauracji = Integer.parseInt((String)cb.getSelectedItem());
                }
            });

            biuroJTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    prawdopodobienstwoBiura = Integer.parseInt((String)cb.getSelectedItem());
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

