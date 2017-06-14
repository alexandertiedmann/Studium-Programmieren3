package edu.stachtiedmann.bank;

import java.io.Serializable;

/**
 * Observer for konto
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 14.06.2017
 */
public class ConsolObserver implements Observer, Serializable {
  private Kunde ku;
  private long kontonummer;
  private double kontostand;
  private Waehrung waehrung;
  private boolean gesperrt;

  public ConsolObserver() {
    this.ku = null;
    this.kontostand = 0;
    this.kontonummer = 0;
    this.waehrung = Waehrung.EUR;
    this.gesperrt = false;
  }

  @Override
  public void update(Object obj) {
    Konto konto = (Konto) obj;
    this.kontonummer = konto.getKontonummer();
    this.kontostand = konto.getKontostand();
    this.waehrung = konto.getAktuelleWaehrung();
    this.ku = konto.getInhaber();
    this.gesperrt = konto.isGesperrt();
    show();
  }

  public void show() {
    System.out.println(
      "Konto: " + this.kontonummer + System.lineSeparator() +
        "Kontostand: " + this.kontostand + System.lineSeparator() +
        "Waehrung: " + this.waehrung + System.lineSeparator() +
        "Kunde: " + this.ku.getVorname() + " " + this.ku.getNachname() + System.lineSeparator() +
        "gesperrt: " + this.gesperrt + System.lineSeparator()
    );
  }
}
