package edu.stachtiedmann.bank;

/**
 * Created by Christoph Stach on 6/6/17.
 * <p>
 * Kontofabrik
 */
public abstract class Kontofabrik {
  /**
   * Erstellt ein neues Konto
   *
   * @param inhaber Der Inhaber
   * @param kontoNr Die KontoNr
   * @return Das neue Konto
   */
  public abstract Konto erstellen(Kunde inhaber, long kontoNr) throws InstantiationException;
}
