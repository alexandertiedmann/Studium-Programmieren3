package edu.stachtiedmann.bank;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * <p>
 * Kunde einer Bank
 * </p>
 *
 * @author Dorothea Hubrich
 * @version 1.0
 */
public class Kunde implements Comparable<Kunde>,Serializable {
  /**
   * Ein Musterkunde
   */
  public static final Kunde MUSTERMANN = new Kunde("Max", "Mustermann", "zuhause", LocalDate.now());

  /**
   * englische oder deutsche Anrede, je nach den Systemeinstellungen
   */
  private static String ANREDE;

  /**
   * liefert die systemspezifische Anrede
   *
   * @return
   */
  public static String getANREDE() {
    return ANREDE;
  }

  /**
   * der Vorname
   */
  private String vorname;
  /**
   * Der Nachname
   */
  private String nachname;
  /**
   * Die Adresse
   */
  private StringProperty adresse = new SimpleStringProperty();
  /**
   * Geburtstag
   */
  private LocalDate geburtstag;

  /**
   * erzeugt einen Standardkunden
   */
  public Kunde() {
    this("Max", "Mustermann", "Adresse", LocalDate.now());
  }

  /**
   * Erzeugt einen Kunden mit den übergebenen Werten
   *
   * @param vorname
   * @param nachname
   * @param adresse
   * @param gebdat
   * @throws IllegalArgumentException wenn einer der Parameter null ist
   */
  public Kunde(String vorname, String nachname, String adresse, LocalDate gebdat) {
    if (vorname == null || nachname == null || adresse == null || gebdat == null) {
      throw new IllegalArgumentException("null als Parameter nich erlaubt");
    }
    this.vorname = vorname;
    this.nachname = nachname;
    this.adresse.set(adresse);
    this.geburtstag = gebdat;
  }

  /**
   * Erzeugt einen Kunden mit den übergebenen Werten
   *
   * @param vorname
   * @param nachname
   * @param adresse
   * @param gebdat   im Format tt.mm.yy
   * @throws IllegalArgumentException wenn einer der Parameter null ist
   */
  public Kunde(String vorname, String nachname, String adresse, String gebdat) {
    this(vorname, nachname, adresse, LocalDate.parse(gebdat, DateTimeFormatter.ofPattern("dd.MM.yy")));
  }

  /**
   * gibt alle Daten des Kunden aus
   */
  @Override
  public String toString() {
    String ausgabe;
    DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    ausgabe = this.vorname + " " + this.nachname + System.getProperty("line.separator");
    ausgabe += this.adresse + System.getProperty("line.separator");
    ausgabe += df.format(this.geburtstag) + System.getProperty("line.separator");
    return ausgabe;
  }

  /**
   * vollständiger Name des Kunden in der Form "Nachname, Vorname"
   *
   * @return
   */
  public String getName() {
    return this.nachname + ", " + this.vorname;
  }

  /**
   * Adresse des Kunden
   *
   * @return
   */
  public String getAdresse() {
    return adresse.get();
  }

  /**
   * setzt die Adresse auf den angegebenen Wert
   *
   * @param adresse
   * @throw IllegalArgumentException wenn adresse null ist
   */
  public void setAdresse(String adresse) {
    if (adresse == null) {
      throw new IllegalArgumentException("Adresse darf nicht null sein");
    }
    this.adresse.set(adresse);
  }

  public StringProperty adresseProperty() {
    return adresse;
  }

  /**
   * Nachname des Kunden
   *
   * @return
   */
  public String getNachname() {
    return nachname;
  }

  /**
   * setzt den Nachnamen auf den angegebenen Wert
   *
   * @param nachname
   * @throw IllegalArgumentException wenn nachname null ist
   */
  public void setNachname(String nachname) {
    if (nachname == null)
      throw new IllegalArgumentException("Nachname darf nicht null sein");
    this.nachname = nachname;
  }

  /**
   * Vorname des Kunden
   *
   * @return
   */
  public String getVorname() {
    return vorname;
  }

  /**
   * setzt den Vornamen auf den angegebenen Wert
   *
   * @param vorname
   * @throw IllegalArgumentException wenn vorname null ist
   */
  public void setVorname(String vorname) {
    if (vorname == null)
      throw new IllegalArgumentException("Vorname darf nicht null sein");
    this.vorname = vorname;
  }

  /**
   * Geburtstag des Kunden
   *
   * @return
   */
  public LocalDate getGeburtstag() {
    return geburtstag;
  }


  /**
   * Hebt einen Betrag in der gewünschten Währung von dem Konto ab.
   *
   * @param betrag Der Betrag
   * @param w      Die Währung
   * @return Bei Erfolg true
   * @throws GesperrtException Wenn konto gesperrt ist
   */
  public boolean abheben(double betrag, Waehrung w) throws GesperrtException {
    return true;
  }

  /**
   * Zahlt einen Betrag in einer gewünschten Währung auf das Konto ein
   *
   * @param betrag Der Betrag
   * @param w      Die Währung
   */
  public void einzahlen(double betrag, Waehrung w) {

  }

  /**
   * Gibt die aktuell eingestellt Währung für das Konto zurück
   *
   * @return Die Währung
   */
  public Waehrung getAktuelleWaehrung() {
    return Waehrung.EUR;
  }

  /**
   * Wechselt zu der gewünschten Währung und rechnet den vorhanden Betrag um
   *
   * @param w Die Währung
   */
  public void waehrungswechsel(Waehrung w) {

  }


  @Override
  public int compareTo(Kunde arg0) {
    return this.getName().compareTo(arg0.getName());
  }

  static {
    if (Locale.getDefault().getCountry().equals("DE"))
      ANREDE = "Hallo, lieber Kunde...";
    else
      ANREDE = "Dear customer...";
    //System.out.println(this);
  }

  protected void finalize() {
    try {
      //Aufräumarbeiten
      System.out.println("Und Tschüss");
    } catch (Throwable e) {
      //
    }
    try {
      super.finalize();
    } catch (Throwable e) {
      //
    }
  }
}
