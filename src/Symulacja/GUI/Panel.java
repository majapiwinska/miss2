package Symulacja.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by maja on 14.12.15.
 */





public class Panel extends JPanel {


    JPanel panel1, panel2, panel3;


    Panel() {

        initComponents();


    }

        private void initComponents () {

            panel1 = new JPanel(new BorderLayout());
            panel2 = new JPanel(new BorderLayout());
            panel3 = new JPanel(new BorderLayout());

            panel1.setBackground(new java.awt.Color(255, 255, 255));
            panel1.setSize(100,100);
            panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 30, 40)));
          /*  panel2.setBackground(new java.awt.Color(255, 255, 255));
            panel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            panel3.setBackground(new java.awt.Color(255, 255, 255));
            panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
*/

            Integer[] liczbKonsultantowWartosci = {1, 2, 3, 4, 5};
            Integer[] liczbaStolikowWartosci = {5, 10, 15, 20, 25, 30, 35, 40};
            Double[] pojawieniaSieKlientaWartosc = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0};

            final JComboBox<Integer> liczbaKonsultantow = new JComboBox(liczbaStolikowWartosci);
            final JComboBox<Integer> liczbaStolikow = new JComboBox(liczbaStolikowWartosci);
            final JComboBox<Integer> pojawienieSieKlienta = new JComboBox(pojawieniaSieKlientaWartosc);

            liczbaKonsultantow.setSelectedIndex(0);

            liczbaKonsultantow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (liczbaKonsultantow.getSelectedIndex() != -1) {
                        Integer daneKonsultant = (Integer) liczbaKonsultantow.getSelectedItem();
                    }
                }
            });

            liczbaStolikow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (liczbaStolikow.getSelectedIndex() != -1) {
                        Integer daneStolik = (Integer) liczbaStolikow.getSelectedItem();
                    }
                }
            });

            pojawienieSieKlienta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (pojawienieSieKlienta.getSelectedIndex() != -1) {
                        Double danePojawienieSieKlienta = (Double) pojawienieSieKlienta.getSelectedItem();
                    }
                }
            });

            panel1.setVisible(true);




            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel1);
            panel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(liczbaKonsultantow)
                                    .addGap(10, 10, 10)
                            )
            );

            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(liczbaKonsultantow)
                                    .addGap(10, 10, 10)
                            )
            );



        }





};
