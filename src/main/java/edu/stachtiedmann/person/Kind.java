package edu.stachtiedmann.person;

/**
 * Klasse Kind
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 28.04.2017
 */
public class Kind extends Person implements Frech<Person> {
  Kuscheltier[] besitzt = new Kuscheltier[2];

  public void wachsen(boolean schnell) {

  }

  @Override
  public boolean aergern(Person p) {
    return false;
  }

  @Override
  public void zungeRausstecken() {

  }
}
