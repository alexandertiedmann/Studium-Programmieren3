package edu.stachtiedmann.nullstelle;

import java.util.function.Function;

/**
 * Created by Christoph Stach on 5/23/17.
 * <p>
 * Programm zur nullstellen Suche
 */
public class App {
  /**
   * Main (will be invoked when the program launches)
   *
   * @param args Arguments that are passed over the console
   */
  public static void main(String[] args) {
    float fZero, gZero, hZero, kZero;
    Function<Float, Float> f, g, h, k;

    f = x -> (float) Math.pow(x, 2) - 5F;
    g = x -> (float) Math.exp(3 * x) - 7F;
    h = x -> ((5F - x)) / ((float) (Math.pow(x, 3) - 1F));
    k = x -> (float) Math.pow(x, 2) + 1F;

    try {
      fZero = Nullstelle.suchen(f, 0, 10);
      System.out.printf("f(%f) = %f\n", fZero, f.apply(fZero));
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
    }

    try {
      gZero = Nullstelle.suchen(g, 0, 1);
      System.out.printf("g(%f) = %f\n", gZero, g.apply(gZero));
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
    }

    try {
      hZero = Nullstelle.suchen(h, -100, 100);
      System.out.printf("h(%f) = %f\n", hZero, h.apply(hZero));
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
    }

    try {
      kZero = Nullstelle.suchen(k, -100, 100);
      System.out.printf("k(%f) = %f\n", kZero, k.apply(kZero));
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
    }
  }
}
