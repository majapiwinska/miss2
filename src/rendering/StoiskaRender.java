package rendering;

import algo.Okienko;
import algo.Stoisko;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kasiazukowska on 2015-12-14.
 */
public class StoiskaRender extends JPanel {

    private static ArrayList<Stoisko> listaStoisk = new ArrayList<Stoisko>();
    Okienko instancjaOkna;

    public StoiskaRender(ArrayList<Stoisko> listaStoisk) {
        this.listaStoisk = new ArrayList<Stoisko>(listaStoisk);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        instancjaOkna = Okienko.getInstance();

        for (int i = 0; i < listaStoisk.size(); i++){
            switch(i){
                case 0: g.setColor(new Color(255, 255, 255));
                        g.fillRect((60+50*instancjaOkna.SCALE),60* instancjaOkna.SCALE,40* instancjaOkna.SCALE,10* instancjaOkna.SCALE);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial",Font.PLAIN, 15));
                        g.drawString(listaStoisk.get(i).getName(), 90+50*instancjaOkna.SCALE, 67 * instancjaOkna.SCALE);
                break;
                case 1: g.setColor(new Color(255, 255, 255));
                        g.fillRect((60+50* instancjaOkna.SCALE),110* instancjaOkna.SCALE,40* instancjaOkna.SCALE,10* instancjaOkna.SCALE);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial",Font.PLAIN, 15));
                        g.drawString(listaStoisk.get(i).getName(), 90+50* instancjaOkna.SCALE, 117 * instancjaOkna.SCALE);
                break;
                case 2: g.setColor(new Color(255, 255, 255));
                        g.fillRect((60+50* instancjaOkna.SCALE),150* instancjaOkna.SCALE,40* instancjaOkna.SCALE,10* instancjaOkna.SCALE);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial",Font.PLAIN, 15));
                        g.drawString(listaStoisk.get(i).getName(), 90+50 * instancjaOkna.SCALE, 157 * instancjaOkna.SCALE);
                break;
                case 3: g.setColor(new Color(255, 255, 255));
                        g.fillRect((60+50* instancjaOkna.SCALE),10* instancjaOkna.SCALE,40* instancjaOkna.SCALE,10* instancjaOkna.SCALE);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial",Font.PLAIN, 15));
                        g.drawString(listaStoisk.get(i).getName(), 87+50 * instancjaOkna.SCALE, 17 * instancjaOkna.SCALE);
                break;
                case 4: g.setColor(new Color(255, 255, 255));
                        g.fillRect((400+50* instancjaOkna.SCALE),60* instancjaOkna.SCALE,40* instancjaOkna.SCALE,10* instancjaOkna.SCALE);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial",Font.PLAIN, 15));
                        g.drawString(listaStoisk.get(i).getName(), 427+50 * instancjaOkna.SCALE, 67 * instancjaOkna.SCALE);
                break;
            }
        }
    }
}
