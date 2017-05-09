package edu.stachtiedmann.baelle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Steuerungsklasse fuer eine Ball-Animation
 *
 * @author Doro
 */
public class Ballspiel {
  private BallFrame f;
  private Set<Ball> baelle;

  /**
   * erstellt die Steuerungsklasse feur die angegebene Oberflaeche
   *
   * @param f
   */
  public Ballspiel(BallFrame f) {
    this.f = f;
    this.baelle = new HashSet<>();
  }

  /**
   * startet einen Ball auf der Oberflaeche und laesst ihn huepfen
   */
  public void ballStarten() {
    Random r = new Random();
    int dauer = r.nextInt(500) + 1000; //Zufallszahl zwischen 1000 und 1500
    Ball b = new Ball(f.getZeichenflaeche());
    b.huepfen(dauer);

    baelle.add(b);
  }

  /**
   * haelt alle Baelle auf der Oberflaeche an, so dass sie an ihrer aktuellen Position
   * stehen bleiben
   */
  public void baelleStoppen() {
    baelle.forEach(b -> b.stoppen());
  }

  /**
   * laesst alle angehaltenen Baelle wieder weiter huepfen
   */
  public void baelleWeiter() {
    baelle.forEach(b -> b.weiter());
  }

  /**
   * loescht alle Baelle von der Oberflaeche
   */
  public void alleLoeschen() {
    baelle.forEach(b -> b.loeschenUndStoppen());
    baelle.clear();
  }
}




