package edu.stachtiedmann;

import edu.stachtiedmann.bank.*;

/**
 * Created by Christoph Stach on 17/4/17.
 * <p>
 * Mainprogramm für bank-project
 */
public class App {
    /**
     * Main
     *
     * @param args CMD args
     */
    public static void main(String[] args) {
        Kontoart art = Kontoart.GIROKONTO;
        System.out.println(art.name() + " " + art.ordinal());
        art = Kontoart.valueOf("SPARBUCH");
        System.out.println(art.name() + " " + art.ordinal());

        for (int i = 0; i < Kontoart.values().length; i++) {
            System.out.println(Kontoart.values()[i].getWerbebotschaft());
        }


        int a = 50;
        int b = a;
        a += 50;
        System.out.println(b); //50


        //Kunde ich = new Kunde("Dorothea", "Hubrich", "zuhause", LocalDate.parse("1976-07-13"));
        Student ich = new Student("Alexander", "Tiedmann", "@Home", "04.02.93", "HTW", 3);

        Konto meinGiro = new Girokonto(ich, 1234, 1000.0);
        meinGiro.einzahlen(50);
        System.out.println(meinGiro);
        System.out.println("-------------------------");
        meinGiro.aufDieKonsoleSchreiben();

        Konto referenz = meinGiro;
        meinGiro.einzahlen(50);
        System.out.println(referenz.getKontostand());

        System.out.println("Nach dem Umzug:");
        ich.setAdresse("Woanders");
        System.out.println(meinGiro);

        Konto meinSpar = new Sparbuch(ich, 9876);
        meinSpar.einzahlen(50);

        try {
            boolean hatGeklappt = meinSpar.abheben(70);
            System.out.println("Abhebung hat geklappt: " + hatGeklappt);
            System.out.println(meinSpar);
        } catch (GesperrtException e) {
            System.out.println("Zugriff auf gesperrtes Konto - Polizei rufen!");
        }
    }
}
