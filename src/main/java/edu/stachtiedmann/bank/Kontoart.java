package edu.stachtiedmann.bank;

/**
 * alle angebotenen Kontoarten
 *
 * @author Doro
 */
public enum Kontoart {
  /**
   * ein Girokonto
   */
  GIROKONTO("Mit ganz viel Dispo"),
  /**
   * ein Sparbuch
   */
  SPARBUCH("viele Zinsen"),
  FESTGELDKONTO("gibts noch nicht...");

  private String werbebotschaft;

  public String getWerbebotschaft() {
    return werbebotschaft;
  }

  private Kontoart(String werbebotschaft) {
    this.werbebotschaft = werbebotschaft;
  }
}
