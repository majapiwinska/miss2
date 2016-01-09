package rendering;

import algo.Kasa;
import algo.Okienko;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kasiazukowska on 2015-12-14.
 */
public class KasyRender extends JPanel {
    private static ArrayList<Kasa> kasyLista = new ArrayList<>();
    Okienko instancjaOkna;

    public KasyRender(ArrayList<Kasa> kasyLista) {
        this.kasyLista = new ArrayList<>(kasyLista);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        instancjaOkna = Okienko.getInstance();

        for(int i=0; i<kasyLista.size(); i++) {
            g.setColor(new Color(255, 248, 78));
            g.fillRect((505+20*i* instancjaOkna.SCALE),105* instancjaOkna.SCALE,15* instancjaOkna.SCALE,5* instancjaOkna.SCALE);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial",Font.PLAIN, 10));
            g.drawString(kasyLista.get(i).getName(), 510+20*i * instancjaOkna.SCALE, 109 * instancjaOkna.SCALE);

        }
    }
}
