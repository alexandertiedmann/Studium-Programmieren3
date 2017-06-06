package edu.stachtiedmann.bank;

/**
 * Created by Christoph Stach on 6/6/17.
 * <p>
 * Kontofabrik
 */
public class SparbuchFabrik extends Kontofabrik {

  @Override
  public Konto erstellen(Kunde inhaber, long kontoNr) throws InstantiationException {
    return new Sparbuch(inhaber, kontoNr);
  }
}
