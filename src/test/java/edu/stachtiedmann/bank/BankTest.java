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
  public void testGeldEinzahlen(){
    b1.geldEinzahlen(0,100);
    assertTrue(b1.getKontostand(0) == 100);
  }

  /**
   * Test von geldAbheben (Aufruf von testGeldEinzahlen)
   */
  public void testGeldAbheben() {
    this.testGeldEinzahlen();
    boolean ok = true;
    boolean abgehoben = true;
    try {
      abgehoben = b1.geldAbheben(0,10);
    } catch (GesperrtException e ){
      ok = false;
    }
    assertTrue((abgehoben) && (ok));
  }

  /**
   * Test von kontoLoeschen
   */
  public void testKontoLoeschen(){
    boolean ok = b1.kontoLoeschen(1);
    assertTrue(ok);
  }

  /**
   * Test von getKontostand
   */
  public void testGetKontostand(){
    double stand = b1.getKontostand(1);
    assertTrue(stand == 0);
  }

  /**
   * Test von geldUeberweisen (aufruf von testGeldEinzahlen)
   */
  public void testGeldUeberweisen(){
    this.testGeldEinzahlen();
    boolean ok = false;
    try {
      ok = b1.geldUeberweisen(0,1,50,"Testueberweisung");
    }catch (GesperrtException e){
      ok = false;
    }
    assertTrue(ok);
  }
}
