package edu.stachtiedmann.bank;

/**
 * Interface for Observer
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 14.06.2017
 */
public interface Observer {
  /**
   * aufruf bei aenderungen
   *
   * @param subject zu beobachtendes Subjekt
   */
  public void update(Object subject);
}
