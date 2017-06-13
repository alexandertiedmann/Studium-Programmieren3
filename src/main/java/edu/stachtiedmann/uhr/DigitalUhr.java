package edu.stachtiedmann.uhr;

import javax.swing.*;
import java.awt.*;

/**
 * Stellt eine Digitale Uhr dar, die man anhalten und weiterlaufen lassen kann
 */
public class DigitalUhr extends JFrame implements IUhrView {
  private static final long serialVersionUID = 1L;
  private static final String TITEL = "Digitaluhr";
  public static final String KNOPF_EIN = "Ein";
  public static final String KNOPF_AUS = "Aus";
  private static final int BREITE = 400;
  private static final int HOEHE = 300;

  private JLabel anzeige;
  private JButton[] knoepfe; // Ein = Einschalten der Anzeige,
  // Aus = Ausschalten der Anzeige,

  private boolean uhrAn = true;
  private UhrController controller;

  /**
   * erstellt das Fenster für die digitale Uhr und bringt es auf den
   * Bildschirm; zu Beginn läuft die Uhr im 1-Sekunden-Takt
   */
  public DigitalUhr(UhrController controller) {
    uhrAn = true;
    this.controller = controller;

    // Erstellung der Oberflächenelemente:
    setTitle(TITEL);
    setSize(BREITE, HOEHE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    add(new JLabel(TITEL));
    anzeige = new JLabel();
    add(anzeige);
    knoepfe = new JButton[2];
    knoepfe[0] = new JButton(KNOPF_EIN);
    knoepfe[1] = new JButton(KNOPF_AUS);
    for (JButton knopf : knoepfe) {
      super.add(knopf);
    }
    knoepfe[0].setEnabled(false); // "Ein"


    // Zufügen des ActionListeners zu den Buttons
    for (JButton knopf : knoepfe) {
      knopf.addActionListener(controller);
    }

    // Auf den Bildschirm bringen:
    tick(0, 0, 0);
    pack();
    setVisible(true);
  }

  /**
   * Holen der aktuellen Uhrzeit und Anzeige, wenn die Uhr angestellt ist
   */
  public void tick(int hour, int minute, int second) {
    if (uhrAn) {
      anzeige.setText(String.format("%02d:%02d:%02d", hour, minute, second));
    }
  }

  @Override
  public void toggleUhr() {
    uhrAn = !uhrAn;
    repaint();
  }

  @Override
  public void disableUhr() {
    uhrAn = false;
    repaint();
  }

  @Override
  public void enableUhr() {
    uhrAn = true;
    repaint();
  }

  public void repaint() {
    knoepfe[0].setEnabled(!uhrAn);
    knoepfe[1].setEnabled(uhrAn);
  }
}
