package algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class Wypisz extends ExternalEvent {

    Sklep model;
    Klient klient;
    int delay;
    final int SIZE= 600;
    //private ChartFrame chart;
    private FileWriter zapis;



    public Wypisz(Model owner, String name, boolean showTrace) {
        super(owner, name, showTrace);
        model = (Sklep)getModel();

        klient = model.getKlient();
        File file = new File("statystyki.txt");
    }
    
    @Override
    public void eventRoutine() {

        try {
            FileWriter zapis= new FileWriter ("statystyki.txt", true);
            zapis.write("STATYSTYKI SKLEPU Z GODZINY: " + presentTime());
            System.out.println("STATYSTYKI SKLEPU Z GODZINY: " + presentTime());

            //liczba ludzi w calym sklepie


            System.out.println("Aktualni klienci na stoisku meble: " + model.meble.aktualniKlienciNaStoisku);
            System.out.println("Aktualni klienci na stoisku dekoracje: " + model.dekoracje.aktualniKlienciNaStoisku);
            System.out.println("Aktualni klienci na stoisku lazienka: " + model.lazienka.aktualniKlienciNaStoisku);
            System.out.println("Aktualni klienci na stoisku kuchnia: " + model.kuchnia.aktualniKlienciNaStoisku);
            System.out.println("Aktualni klienci na stoisku sypialnia: " + model.sypialnia.aktualniKlienciNaStoisku);

            System.out.println("Akutalni kliencie w sklepie: "+ model.klienciWSklepie);

            //liczba ludzi w barze
            System.out.println("Aktualni klienci w restauracji: " + model.getRestauracja().klienciWRestauracji+ "\r\n");
            System.out.println("Aktualni klienci w biurze: " + model.getBiuro().klienciWBiurze + "\r\r");
        
            zapis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        schedule(new TimeSpan(15, TimeUnit.MINUTES));
        //tutaj ustalasz co ile sie odswieza
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
        }


    }
}
