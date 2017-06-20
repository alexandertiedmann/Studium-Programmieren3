package edu.stachtiedmann.bank;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto implements Serializable {
  private List<Observer> observerlist = new LinkedList<>();
  private Waehrung waehrung;
  /**
   * der Kontoinhaber
   */
  private Kunde inhaber;

  /**
   * die Kontonummer
   */
  private final long nummer;

  /**
   * Property fuer den Kontostand
   */
  private final ReadOnlyDoubleWrapper kontostand = new ReadOnlyDoubleWrapper(0);

  /**
   * Wenn das Konto gesperrt ist (gesperrt = true), können keine Aktionen daran mehr vorgenommen werden,
   * die zum Schaden des Kontoinhabers wären (abheben, Inhaberwechsel)
   */
  private final BooleanProperty gesperrt = new SimpleBooleanProperty(false);

  private final ReadOnlyBooleanWrapper positive = new ReadOnlyBooleanWrapper(true);

  /**
   * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
   * der anfängliche Kontostand wird auf 0 gesetzt.
   *
   * @param inhaber     Kunde
   * @param kontonummer long
   * @throws IllegalArgumentException wenn der Inhaber null
   */
  public Konto(Kunde inhaber, long kontonummer) {
    if (inhaber == null) {
      throw new IllegalArgumentException("Inhaber darf nicht null sein!");
    }
    this.inhaber = inhaber;
    this.nummer = kontonummer;
    this.waehrung = Waehrung.EUR;
    positive.bind(kontostand.greaterThanOrEqualTo(0));
  }

  /**
   * setzt alle Eigenschaften des Kontos auf Standardwerte
   */
  public Konto() {
    //hier ist kein weiterer Code erlaubt
    this(Kunde.MUSTERMANN, 1234567);
  }

  public double getKontostand() {
    return kontostand.get();
  }

  public ReadOnlyDoubleProperty kontostandProperty() {
    return kontostand;
  }

  public void setKontostand(double kontostand) {
    this.kontostand.set(kontostand);
    notifyObserver();
  }

  public BooleanProperty gesperrtProperty() {
    return gesperrt;
  }

  public boolean isPositive() {
    return positive.get();
  }

  public ReadOnlyBooleanProperty positiveProperty() {
    return positive;
  }

  /**
   * Gets nummer
   *
   * @return value of nummer
   */
  public long getKontonummer() {
    return nummer;
  }

  /**
   * liefert den Kontoinhaber zurück
   *
   * @return Kunde
   */
  public final Kunde getInhaber() {
    return this.inhaber;
  }

  /**
   * setzt den Kontoinhaber
   *
   * @param kinh neuer Kontoinhaber
   * @throws GesperrtException        wenn das Konto gesperrt ist
   * @throws IllegalArgumentException wenn kinh null ist
   */
  public final void setInhaber(Kunde kinh) throws GesperrtException {
    if (kinh == null)
      throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
    if (this.gesperrt.get())
      throw new GesperrtException(this.nummer);
    this.inhaber = kinh;
    notifyObserver();
  }


  /**
   * liefert zurück, ob das Konto gesperrt ist oder nicht
   *
   * @return
   */
  public final boolean isGesperrt() {
    return gesperrt.get();
  }

  /**
   * Erhöht den Kontostand um den eingezahlten Betrag.
   *
   * @param betrag double
   * @throws IllegalArgumentException wenn der betrag negativ ist
   */
  public void einzahlen(double betrag) {
    if (betrag < 0) {
      throw new IllegalArgumentException("Negativer Betrag");
    }
    setKontostand(getKontostand() + betrag);
  }

  /**
   * Gibt eine Zeichenkettendarstellung der Kontodaten zurück.
   */
  @Override
  public String toString() {
    String ausgabe;
    ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
      + System.getProperty("line.separator");
    ausgabe += "Inhaber: " + this.inhaber;
    ausgabe += "Aktueller Kontostand: " + this.kontostand + " Euro ";
    ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
    return ausgabe;
  }

  /**
   * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
   *
   * @param betrag double
   * @return true, wenn die Abhebung geklappt hat,
   * false, wenn sie abgelehnt wurde
   * @throws GesperrtException        wenn das Konto gesperrt ist
   * @throws IllegalArgumentException wenn der betrag negativ ist
   */
  public final boolean abheben(double betrag) throws GesperrtException {
    //immer gleich:
    //Betrag groesser als 0
    if (betrag < 0) {
      throw new IllegalArgumentException();
    }
    //Konto ist nicht gesperrt
    if (this.isGesperrt())
      throw new GesperrtException(this.getKontonummer());
    //Pruefung auf weitere Regeln --> wenn abgehoben werden darf --> Kontostand verringern
    if (this.reichtStand(betrag)) {
      setKontostand(getKontostand() - betrag);
      sonderAktion(betrag);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Zusaetzliche Aktionen beim abheben
   *
   * @param betrag
   */
  protected void sonderAktion(double betrag) {
  }

  /**
   * Test ob der Kontostand fuer die Transaktion reicht
   *
   * @param betrag abzuhebender Betrag
   * @return true wenn es reicht
   */
  public abstract boolean reichtStand(double betrag);

  /**
   * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr möglich.
   */
  public final void sperren() {
    gesperrt.set(true);
    notifyObserver();
  }

  /**
   * entsperrt das Konto, alle Kontoaktionen sind wieder möglich.
   */
  public final void entsperren() {
    gesperrt.set(false);
    notifyObserver();
  }


  /**
   * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
   *
   * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
   */
  public final String getGesperrtText() {
    if (this.gesperrt.get()) {
      return "GESPERRT";
    } else {
      return "";
    }
  }

  /**
   * liefert die ordentlich formatierte Kontonummer
   *
   * @return auf 10 Stellen formatierte Kontonummer
   */
  public String getKontonummerFormatiert() {
    return String.format("%10d", this.nummer);
  }

  /**
   * liefert den ordentlich formatierten Kontostand
   *
   * @return formatierter Kontostand mit 2 Nachkommastellen und Währungssymbol €
   */
  public String getKontostandFormatiert() {
    return String.format("%10.2f Euro", this.getKontostand());
  }

  /**
   * Vergleich von this mit other; Zwei Konten gelten als gleich,
   * wen sie die gleiche Kontonummer haben
   *
   * @param other
   * @return true, wenn beide Konten die gleiche Nummer haben
   */
  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (this.getClass() != other.getClass())
      return false;
    if (this.nummer == ((Konto) other).nummer)
      return true;
    else
      return false;
  }

  @Override
  public int hashCode() {
    return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
  }

  /**
   * schreibt this aUF DIE kONSOLE
   */
  public void aufDieKonsoleSchreiben() {
    System.out.println(this.toString());
  }

  /**
   * Gets waehrung
   *
   * @return value of waehrung
   */
  public Waehrung getAktuelleWaehrung() {
    return waehrung;
  }

  /**
   * Sets waehrung
   *
   * @param waehrung value for waehrung
   */
  public void waehrungswechsel(Waehrung waehrung) {
    if (this.waehrung == Waehrung.EUR) {
      this.setKontostand(waehrung.umrechnen(this.getKontostand()));
    } else {
      this.setKontostand(this.getKontostand() / this.waehrung.getValue());
    }

    this.waehrung = waehrung;
    notifyObserver();
  }

  /**
   * returns the list of Observers
   *
   * @return observerlist
   */
  public List<Observer> getObserverlist() {
    return observerlist;
  }

  /**
   * adds new Observer
   *
   * @param ob new Observer
   */
  public void addObserver(Observer ob) {
    observerlist.add(ob);
  }

  /**
   * deletes a Observer
   *
   * @param ob Observer to delete
   */
  public void deleteObserver(Observer ob) {
    observerlist.remove(ob);
  }

  /**
   * notifies all Observer
   */
  protected void notifyObserver() {
    Bank.notifyObserver(this);
  }

}
