package edu.stachtiedmann.bank;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests fuer die Klasse Bank
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 23.04.2017
 */
public class BankTest extends TestCase {
  private Bank b1;
  private Kunde k1, k2;
  private Kontofabrik gkf;
  private Kontofabrik sbf;
  private Observer ob1,ob2;

  /**
   * Erstellt eine neue Bank und zwei neue Kunden
   * fuer jeden Kunden ein Konto
   */
  protected void setUp() throws InstantiationException {
    gkf = new GirokontoFabrik();
    sbf = new SparbuchFabrik();
    b1 = new Bank(12345);
    k1 = new Kunde("Hans", "Mueller", "Teststr.", "24.12.00");
    k2 = new Kunde("Otto", "Schulz", "Inderstr.", "10.06.99");
    b1.kontoErstellen(gkf, k1);
    b1.kontoErstellen(gkf, k2);
    ob1 = new ConsolObserver();
    ob2 = new ConsolObserver();
  }

  /**
   * test von girokontoErstellen
   */
  public void testGirokontoErstellen() throws InstantiationException {
    long kontonr = b1.kontoErstellen(gkf, k1);
    assertTrue(kontonr == 2);
    long kontonr2 = b1.kontoErstellen(gkf, k2);
    assertTrue(kontonr2 == 3);
  }

  /**
   * Test von sparbuchErstellen
   */
  public void testSparbuchErstellen() throws InstantiationException {
    long kontonr = b1.kontoErstellen(sbf, k1);
    assertTrue(kontonr == 2);
    long kontonr2 = b1.kontoErstellen(sbf, k2);
    assertTrue(kontonr2 == 3);
  }

  /**
   * Test von getAlleKonten
   */
  public void testGetAlleKonten() {
    List<Long> liste = new ArrayList<>();
    liste.add(0L);
    liste.add(1L);
    assertTrue(b1.getAlleKontoNummern().equals(liste));
  }

  /**
   * Test von geldEinzahlen
   */
  public void testGeldEinzahlen() {
    try {
      b1.geldEinzahlen(0, 100);
    } catch (IllegalArgumentException e) {
      fail("Es sollte keine Exception geworfen werden");
    }
  }

  /**
   * Test von geldEinzahlen mit negativem Betrag
   */
  public void testGeldEinzahlenNegativerBetrag() {
    try {
      b1.geldEinzahlen(0, -100);
      fail("Sollte eine Exception werfen wenn Betrag negativ");
    } catch (IllegalArgumentException e) {
      //Nothing to do
    }
  }

  /**
   * Test von geldAbheben (Aufruf von testGeldEinzahlen)
   */
  public void testGeldAbheben() {
    this.testGeldEinzahlen();

    try {
      assertTrue(b1.geldAbheben(0, 10));
    } catch (GesperrtException e) {
      fail("Bei normalem Verhalten sollte keine Exception auftreten");
    }

  }

  /**
   * Test von geldAbheben (Aufruf von testGeldEinzahlen)
   * wenn kein Geld vorhanden ist
   */
  public void testGeldAbhebenKeinGeld() {
    this.testGeldEinzahlen();

    try {
      assertFalse(b1.geldAbheben(0, 10000));
    } catch (GesperrtException e) {
      //Nothing to do
    }
  }

  /**
   * Test von kontoLoeschen
   */
  public void testKontoLoeschen() {
    boolean ok = b1.kontoLoeschen(1);
    assertTrue(ok);
  }

  /**
   * Test von kontoLoeschen wenn das Konto nicht vorhanden ist
   */
  public void testKontoLoeschenNichtVorhanden() {
    boolean ok = b1.kontoLoeschen(4);
    assertFalse(ok);
  }

  /**
   * Test von getKontostand
   */
  public void testGetKontostand() {
    double stand = b1.getKontostand(1);
    assertTrue(stand == 0);
  }

  /**
   * Test von geldUeberweisen (aufruf von testGeldEinzahlen)
   */
  public void testGeldUeberweisen() {
    this.testGeldEinzahlen();

    try {
      assertTrue(b1.geldUeberweisen(0, 1, 50, "Testueberweisung"));
    } catch (GesperrtException e) {
      fail("Bei normalem Verhalten sollte keine Exception auftreten");
    }
  }

  /**
   * Test von geldUeberweisen (aufruf von testGeldEinzahlen)
   * Versuch mit Sparbuch sollte fehlschlagen
   */
  public void testGeldUeberweisenFail() throws InstantiationException {
    b1.kontoErstellen(sbf, k1);

    try {
      assertFalse(b1.geldUeberweisen(2, 1, 5000, "Testueberweisung"));
    } catch (GesperrtException e) {
      fail();
    }
  }

  /**
   * Test von clone
   * einzahlung auf ein Konto der ersten Bank
   * Kontostaende sollten unterschiedlich sein
   */
  public void testClone() {
    try {
      Bank b2 = (Bank) b1.clone();
      if (b2.equals(null)) {
        fail();
      } else {
        b1.geldEinzahlen(1, 10);
        assertTrue(b1.getKontostand(1) != b2.getKontostand(1));
        assertTrue(b1.getKontostand(1) == 10);
        assertTrue(b2.getKontostand(1) == 0);
      }
    } catch (CloneNotSupportedException e) {
      fail();
    }
  }

  /**
   * Manueller Test ob der ConsoleObserver auf die Konsole schreibt
   */
  public void testObserver(){
    try {
      b1.addObserver(ob1,1);
      b1.addObserver(ob2,0);
      b1.geldEinzahlen(1,100);
      b1.geldAbheben(1,10);
      b1.geldEinzahlen(0,33);
    }catch (Exception e){
      fail();
    }
  }
}
