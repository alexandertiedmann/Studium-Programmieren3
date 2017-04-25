package edu.stachtiedmann.bank;

import java.util.*;

/**
 * Created by Christoph Stach on 4/21/17.
 * <p>
 * Klasse zur Verwaltung einer Bank.
 */
public class Bank {
  private Map<Long, Konto> konten = new HashMap<Long, Konto>();
  private long currentKontoNr = 0;
  private long bankleitzahl;

  /**
   * Setter Konstruktor
   *
   * @param bankleitzahl value of setter
   */
  public Bank(long bankleitzahl) {
    super();

    this.bankleitzahl = bankleitzahl;
  }

  /**
   * Gets bankleitzahl
   *
   * @return value of bankleitzahl
   */
  public long getBankleitzahl() {
    return bankleitzahl;
  }

  /**
   * Sets bankleitzahl
   *
   * @param bankleitzahl value for bankleitzahl
   */
  public void setBankleitzahl(long bankleitzahl) {
    this.bankleitzahl = bankleitzahl;
  }

  /**
   * Erstellt ein neues Girokonto für den Kunden und gibt die KontoNr zurück.
   *
   * @param inhaber Kunde
   * @return Neue KontoNr
   */
  public long girokontoErstellen(Kunde inhaber) {
    konten.put(currentKontoNr, new Girokonto(inhaber, currentKontoNr, 0));

    return currentKontoNr++;
  }

  /**
   * Erstellt ein neues Sparbuch für den Kunden und gibt die KontoNr zurück.
   *
   * @param inhaber Kunde
   * @return Neue KontoNr
   */
  public long sparbuchErstellen(Kunde inhaber) {
    konten.put(currentKontoNr, new Sparbuch(inhaber, currentKontoNr));

    return currentKontoNr++;
  }

  /**
   * Liefert eine Liste von Kontoinformationen aller Konten
   *
   * @return Liste als string
   */
  public String getAlleKonten() {
    return Arrays.toString(konten.values().toArray());
  }

  /**
   * Liefert eine Liste aller gültigen Kontonummern in der Bank
   *
   * @return Liste aller Kontonummern
   */
  public List<Long> getAlleKontoNummern() {
    List<Long> l = new ArrayList<>();
    l.addAll(konten.keySet());

    return l;
  }

  /**
   * Hebt den Betrag vom Konto mit der Nummer von ab und gibt zurück, ob die Abhebung geklappt hat.
   *
   * @param von    Das Konto von dem abgehoben wird
   * @param betrag Der Betrag
   * @return True wenn die Abhebung geklappt hat
   * @throws GesperrtException Wenn das Konto gesperrt ist
   */
  public boolean geldAbheben(long von, double betrag) throws GesperrtException {
    return konten.get(von).abheben(betrag);
  }

  /**
   * zahlt den angegebenen Betrag auf das Konto mit der Nummer auf ein
   *
   * @param auf    Das Konto
   * @param betrag Der Betrag
   */
  public void geldEinzahlen(long auf, double betrag) {
    konten.get(auf).einzahlen(betrag);
  }

  /**
   * löscht das Konto mit der angegebenen nummer und gibt zurück, ob die Löschung geklappt hat
   *
   * @param nummer Die KontoNr
   * @return True wenn geklappt
   */
  public boolean kontoLoeschen(long nummer) {
    boolean removable = konten.containsKey(nummer);
    konten.remove(nummer);

    return removable;
  }

  /**
   * Gibt den Kontostand vom angegeben Konto zurück
   *
   * @param nummer KontoNr
   * @return Kontostand
   */
  public double getKontostand(long nummer) {
    return konten.get(nummer).getKontostand();
  }

  /**
   * Überweise Geld von einem Konto auf ein anderes.
   *
   * @param vonKontonr       von KontoNr
   * @param nachKontonr      nach KontoNr
   * @param betrag           Der Betrag
   * @param verwendungszweck Der Verwendungszweck
   * @return True wenn geklappt
   */
  public boolean geldUeberweisen(long vonKontonr, long nachKontonr, double betrag, String verwendungszweck) throws GesperrtException {
    Konto von = konten.get(vonKontonr);
    Konto nach = konten.get(vonKontonr);

    if (!(von instanceof Girokonto && nach instanceof Girokonto)) {
      return false;
    } else {
      von = (Girokonto) von;
      nach = (Girokonto) nach;

      if (!von.abheben(betrag)) {
        return false;
      } else {
        nach.einzahlen(betrag);

        return true;
      }
    }


  }
}
