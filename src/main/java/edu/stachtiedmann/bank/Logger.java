package edu.stachtiedmann.bank;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Logger for Exceptions
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 04.07.2017
 */
public class Logger {
  private static Logger inst = new Logger();
  private final String PATH = "src/main/java/edu/stachtiedmann/alert.log";
  private File file = null;
  BufferedWriter in = null;

  /**
   * Konstruktor
   */
  private Logger() {

  }

  /**
   * open file
   * @throws Exception if error
   */
  private void openFile() throws Exception {
    file = new File(PATH);
    if (!file.canRead() || !file.isFile())
      this.in = new BufferedWriter(new FileWriter(PATH,false));
    else
      this.in = new BufferedWriter(new FileWriter(PATH,true));
  }

  /**
   * close the file
   * @throws Exception if error
   */
  private void closeFile() throws Exception {
    in.close();
  }

  /**
   * getter for Instance
   *
   * @return Instance of Logger
   */
  public static Logger getInst() {
    return inst;
  }

  /**
   * @param m Exception to log
   */
  public void writeAlert(Exception m) {
    try {
      //Datei oeffnen
      this.openFile();
      //Datum fuer Fehlermeldung
      LocalTime time = LocalTime.now();
      LocalDate date = LocalDate.now();
      int day = date.getDayOfMonth();
      int month = date.getMonthValue();
      int year = date.getYear();
      int h = time.getHour();
      int min = time.getMinute();
      int sek = time.getSecond();
      //STring fuer Datei zusammenbasteln
      String toFile = "---------- ";
      toFile = toFile + year + "-" + month + "-" + day + "-" + h + ":" + min + ":" + sek + System.lineSeparator();
      toFile = toFile + m.getClass().getCanonicalName() + " : " + m.getMessage() + System.lineSeparator();
      toFile = toFile + "----------";
      //String in Datei schreiben
      try {
        this.in.newLine();
        this.in.write(toFile);
      } catch (Exception e) {
        System.out.println(e.getClass().getCanonicalName() + " " + e.getMessage());
      }
      //Datei schliessen
      this.closeFile();
    } catch (Exception e){
      System.out.println("Error");
    }
  }
}
