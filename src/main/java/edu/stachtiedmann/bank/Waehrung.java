package edu.stachtiedmann.bank;

import java.io.Serializable;

/**
 * Created by Christoph Stach on 4/11/17.
 * <p>
 * Stellt den Umrechnungskurs der Währungen zum Euro bereit
 */
public enum Waehrung implements Serializable{
  EUR(1),
  BGN(1.95583),
  LTL(1.95583),
  KM(3.4528);

  private double value;

  private Waehrung(double value) {
    this.value = value;
  }

  /**
   * Rechnet den Wert in Euro in die jeweilige Einheit um
   *
   * @param value Der Wert in Euro
   * @return Die Einheit
   */
  public double umrechnen(double value) {
    return value * this.value;
  }

  /**
   * Gets value
   *
   * @return value of value
   */
  public double getValue() {
    return value;
  }
}
