package edu.stachtiedmann.baelle;

import java.util.Random;

/**
 * Steuerungsklasse fuer eine Ball-Animation
 *
 * @author Doro
 */
public class Ballspiel {
  private BallFrame f;

  /**
   * erstellt die Steuerungsklasse feur die angegebene Oberflaeche
   *
   * @param f
   */
  public Ballspiel(BallFrame f) {
    this.f = f;
  }

  /**
   * startet einen Ball auf der Oberflaeche und laesst ihn huepfen
   */
  public void ballStarten() {
    Random r = new Random();
    int dauer = r.nextInt(500) + 1000; //Zufallszahl zwischen 1000 und 1500
    Ball b = new Ball(f.getZeichenflaeche());
    b.huepfen(dauer);
  }

  /**
   * haelt alle Baelle auf der Oberflaeche an, so dass sie an ihrer aktuellen Position
   * stehen bleiben
   */
  public void baelleStoppen() {

  }

  /**
   * laesst alle angehaltenen Baelle wieder weiter huepfen
   */
  public void baelleWeiter() {

  }

  /**
   * loescht alle Baelle von der Oberflaeche
   */
  public void alleLoeschen() {

  }
}




