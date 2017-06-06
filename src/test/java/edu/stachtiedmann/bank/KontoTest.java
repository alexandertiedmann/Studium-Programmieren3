package edu.stachtiedmann.bank;

import junit.framework.TestCase;

/**
 * Created by Christoph Stach on 4/11/17.
 * <p>
 * Tests für Konto
 */
public class KontoTest extends TestCase {
  private Konto konto;

  /**
   * Erstellt ein neues Konto.
   */
  public void setUp() throws Exception {
    super.setUp();

    this.konto = new Sparbuch();
  }

  /**
   * Testet ob das abheben korrekt funktioniert
   */
  public void testAbheben() {
    this.konto.einzahlen(100);

    try {
      assertTrue(this.konto.abheben(50));
    } catch (GesperrtException e) {
      //
    }

    assertEquals(50d, this.konto.getKontostand());

    try {
      assertFalse(this.konto.abheben(101));
    } catch (GesperrtException e) {
      //
    }

    try {
      //einzahlen damit maximum abgehoben werden kann
      this.konto.einzahlen(100000);
      //false, da mehr als Maximum abgehoben werden soll
      assertFalse(this.konto.abheben(10000));
    }catch (GesperrtException e){
      fail();
    }

    this.konto.sperren();

    try {
      this.konto.abheben(1);
      fail("GesperrtException wurde nicht geworfen");
    } catch (Exception e) {
      assertTrue(e instanceof GesperrtException);
    }
  }

  /**
   * Testet of das Einzahlen auf das Konto funktioniert.
   */
  public void testEinzahlen() {
    this.konto.einzahlen(100);
    assertEquals(100d, this.konto.getKontostand());
  }

  /**
   * Test ob die korrekte aktuelle Währung zurückgegeben wird.
   */
  public void testGetAktuelleWaehrung() {
    assertEquals(Waehrung.EUR, this.konto.getAktuelleWaehrung());
    this.konto.waehrungswechsel(Waehrung.BGN);
    assertEquals(Waehrung.BGN, this.konto.getAktuelleWaehrung());
  }


  /**
   * Testet ob der Währungswechsel korrekt funktioniert.
   */
  public void testWaehrungswechsel() {
    this.konto.einzahlen(1);

    this.konto.waehrungswechsel(Waehrung.BGN);
    assertEquals(1.95583, this.konto.getKontostand());
    this.konto.waehrungswechsel(Waehrung.EUR);
    assertEquals(1d, this.konto.getKontostand());
  }

}
