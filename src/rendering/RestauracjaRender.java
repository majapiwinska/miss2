package rendering;

import algo.Okienko;
import algo.Restauracja;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kasiazukowska on 2016-01-09.
 */
public class RestauracjaRender extends JPanel {
    Okienko instancjaOkna;
    private static ArrayList<Restauracja> listaRestauracji = new ArrayList<Restauracja>();
    public RestauracjaRender(ArrayList<Restauracja> listaStoisk) {
        this.listaRestauracji = new ArrayList<Restauracja>(listaStoisk);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        instancjaOkna = Okienko.getInstance();

        for(int i = 0; i < listaRestauracji.size(); i++) {
            g.setColor(new Color(255, 78, 51));
            g.fillRect(500+50*i* instancjaOkna.SCALE,131* instancjaOkna.SCALE,30* instancjaOkna.SCALE,13* instancjaOkna.SCALE);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.PLAIN, 12));
            g.drawString(listaRestauracji.get(i).getName(), 505+50*i * instancjaOkna.SCALE, 138 * instancjaOkna.SCALE);
        }
    }
}
