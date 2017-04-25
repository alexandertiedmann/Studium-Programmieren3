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

  /**
   * Erstellt eine neue Bank und zwei neue Kunden
   * fuer jeden Kunden ein Konto
   */
  protected void setUp() {
    b1 = new Bank(12345);
    k1 = new Kunde("Hans", "Mueller", "Teststr.", "24.12.00");
    k2 = new Kunde("Otto", "Schulz", "Inderstr.", "10.06.99");
    b1.girokontoErstellen(k1);
    b1.girokontoErstellen(k2);
  }

  /**
   * test von girokontoErstellen
   */
  public void testGirokontoErstellen() {
    long kontonr = b1.girokontoErstellen(k1);
    assertTrue(kontonr == 2);
    long kontonr2 = b1.girokontoErstellen(k2);
    assertTrue(kontonr2 == 3);
  }

  /**
   * Test von sparbuchErstellen
   */
  public void testSparbuchErstellen() {
    long kontonr = b1.sparbuchErstellen(k1);
    assertTrue(kontonr == 2);
    long kontonr2 = b1.sparbuchErstellen(k2);
    assertTrue(kontonr2 == 3);
  }

  /**
   * Test von getAlleKonten
   */
  public void testGetAlleKonten() {
    List<Long> liste = new ArrayList<>();
    liste.add(0l);
    liste.add(1l);
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
    boolean abgehoben = true;
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
    assertFalse(false);
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
  public void testGeldUeberweisenFail() {
    b1.sparbuchErstellen(k1);
    try {
      assertFalse(b1.geldUeberweisen(2, 1, 5000, "Testueberweisung"));
    } catch (GesperrtException e) {
      fail();
    }
  }
}
