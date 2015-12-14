import javax.swing.*;
import java.awt.*;

/**
 * Created by kasiazukowska on 2015-12-13.
 */
public class KlientG extends JPanel implements Rysownik{
    public void rysuj(Graphics g){
        g.setColor(Color.cyan);
        int x = 50;
        int y = 50;
        g.fillOval(x,y,10,10);
    }
}
