package edu.stachtiedmann;

import edu.stachtiedmann.bank.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * comment about this class
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 04.07.2017
 */
public class TestLogger {
  public static void main(String[] args) {
    Logger.getInst().writeAlert(new NullPointerException("Dies ist ein Test"));
    Logger.getInst().writeAlert(new NullPointerException("ERROR 404"));
    Logger.getInst().writeAlert(new NullPointerException("Alles ist falsch"));
    try {
      BufferedReader reader = new BufferedReader(new FileReader("src/main/java/edu/stachtiedmann/alert.log"));
      String zeile;
      while (null != (zeile = reader.readLine())) {
        zeile = zeile.trim();
        if (!zeile.equals("")) {
          System.out.println(zeile);
        }
      }
    } catch (Exception f) {
      System.out.println("beim lesen ist ein fehler aufgetretn");
    }
  }
}
