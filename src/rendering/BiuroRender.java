package rendering;

import algo.BiuroObslugi;
import algo.Okienko;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kasiazukowska on 2016-01-09.
 */
public class BiuroRender extends JPanel {
    Okienko instancjaOkna;
    private static ArrayList<BiuroObslugi> listaBiur = new ArrayList<BiuroObslugi>();
    public BiuroRender(ArrayList<BiuroObslugi> listaBiur) {
        this.listaBiur = new ArrayList<BiuroObslugi>(listaBiur);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        instancjaOkna = Okienko.getInstance();

        for(int i = 0; i < listaBiur.size(); i++) {
            g.setColor(new Color(255, 78, 51));
            g.fillRect((650+50*i* instancjaOkna.SCALE),130* instancjaOkna.SCALE,30* instancjaOkna.SCALE,13* instancjaOkna.SCALE);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.PLAIN, 12));
            g.drawString(listaBiur.get(i).getName(), 652+50*i * instancjaOkna.SCALE, 138 * instancjaOkna.SCALE);
        }

    }
}
