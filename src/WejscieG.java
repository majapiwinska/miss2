import javax.swing.*;
import java.awt.*;

/**
 * Created by kasiazukowska on 2015-12-13.
 */
public class WejscieG extends JPanel implements Rysownik {
    public void rysuj(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.drawRect(10, 400, 400, 10);
    }
}
