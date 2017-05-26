package edu.stachtiedmann.nullstelle;

import java.util.function.Function;

/**
 * Created by Christoph Stach on 5/23/17.
 * <p>
 * Nullstellensuche
 */
public class Nullstelle {
  public static float precision = 0.001F;

  /**
   * Sucht die Nullstelle mit dem Bisektionsverfahren
   *
   * @param fkt Dir Funktion
   * @param a   Intervalgrenze
   * @param b   Intervalgrenze
   * @return Die Nullstelle
   * @throws ArithmeticException Wenn keine Nullstelle gefunden wurde.
   */
  public static float suchen(Function<Float, Float> fkt, float a, float b) throws ArithmeticException {
    float mid, midY, aY, bY, tmp;

    if (a > b) {
      tmp = a;
      a = b;
      b = tmp;
    }

    aY = fkt.apply(a);
    bY = fkt.apply(b);

    if (aY == 0F) {
      return a;
    } else if (bY == 0F) {
      return b;
    } else {
      mid = (a + b) / 2F;
      midY = fkt.apply(mid);

      if (midY == 0 || Math.abs(a - b) <= precision || Math.abs(b - a) <= precision) {
        return mid;
      } else if ((aY < 0 && midY > 0)) { // Nullstelle liegt im ersten Interval
        return suchen(fkt, a, mid);
      } else if ((midY < 0 && bY > 0)) { // Nullstelle liegt im zweiten Interval
        return suchen(fkt, mid, b);
      } else {
        throw new ArithmeticException("Keine Nullstelle gefunden");
      }
    }
  }
}
