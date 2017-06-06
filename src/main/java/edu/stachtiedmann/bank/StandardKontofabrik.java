package edu.stachtiedmann.bank;

/**
 * Created by Christoph Stach on 6/6/17.
 * <p>
 * Kontofabrik
 */
public class StandardKontofabrik extends Kontofabrik {

  @Override
  public Konto erstellen(Class<? extends Konto> cls, Kunde inhaber, long kontoNr) throws InstantiationException {
    switch (cls.getName()) {
      case "edu.stachtiedmann.bank.Girokonto": {
        return new Girokonto();
      }
      case "edu.stachtiedmann.bank.Sparbuch": {
        return new Sparbuch();
      }
      default: {
        throw new InstantiationException(String.format("Class %s is not supported", cls.getName()));
      }
    }
  }
}
