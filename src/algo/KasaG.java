package algo; /**
 * Created by kasiazukowska on 2015-12-13.
 */
import javax.swing.*;
import java.awt.*;

public class KasaG extends JPanel implements Rysownik{
     public void rysuj(Graphics g){
         System.out.println("Rysuj");
         Graphics2D g2 = (Graphics2D)g;
         g2.setColor(Color.black);
         g2.fillRect(200, 200, 30, 80);

    }
}
