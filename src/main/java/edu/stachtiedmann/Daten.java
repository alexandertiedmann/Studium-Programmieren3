package edu.stachtiedmann;

import java.io.Serializable;

/**
 * Daten mit einer ID
 *
 * @param <IDTyp> Datentyp der ID
 * @author Doro
 */
public interface Daten<IDTyp> extends Serializable {
  /**
   * liefert die ID des Datenpaketes
   *
   * @return
   */
  public IDTyp getId();
}
