package edu.stachtiedmann.formatierteausgabe;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Christoph Stach on 6/3/17.
 * <p>
 * Formatierte Ausgabe
 */
public class App {
  /**
   * Main (will be invoked when the program launches)
   *
   * @param args Arguments that are passed over the console
   */
  public static void main(String[] args) {
    try (FileWriter fileWriter = new FileWriter("output.txt")) {
      PrintWriter printWriter = new PrintWriter(fileWriter);
      Scanner scanner = new Scanner(System.in);

      System.out.print("Bitte geben Sie eine ganze Zahl ein: ");
      long lng = scanner.nextInt();
      System.out.print("Bitte geben Sie eine Zahl ein: ");
      double dbl = scanner.nextDouble();

      LocalDate dt = LocalDate.now();
      LocalTime tm = LocalTime.now();

      printWriter.printf("%d%n", lng);
      printWriter.printf("%010d%n", lng);
      printWriter.printf("%+,d%n", lng);

      printWriter.printf("%f%n", dbl);
      printWriter.printf("%+.2f%n", dbl);
      printWriter.printf("%e%n", dbl);
      printWriter.printf(Locale.US, "%,.3f%n", dbl);
      printWriter.printf("%f%%%n", dbl);

      printWriter.printf("%tA %te %tB %tY%n", dt, dt, dt, dt);
      printWriter.printf(Locale.ITALY, "%tA %te %tB %tY%n", dt, dt, dt, dt);

      printWriter.printf("%tl:%tM %tp%n", tm, tm, tm);


      printWriter.flush();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

  }

}
