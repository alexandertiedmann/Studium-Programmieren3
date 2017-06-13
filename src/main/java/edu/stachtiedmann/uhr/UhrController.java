package edu.stachtiedmann.uhr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Christoph Stach on 6/13/17.
 * <p>
 * Uhr IController
 */
public class UhrController implements Observer, ActionListener, KeyListener {
  private Observable model;
  private IUhrView digitalUhrView;
  private IUhrView kreisUhrView;

  public UhrController() {
    digitalUhrView = new DigitalUhr(this);
    kreisUhrView = new KreisUhr(this);

    model = new Uhr();
    model.addObserver(this);
  }

  @Override
  public void update(Observable observable, Object o) {
    Uhr uhr = (Uhr) observable;

    kreisUhrView.tick(uhr.getStunde(), uhr.getMinute(), uhr.getSekunde());
    digitalUhrView.tick(uhr.getStunde(), uhr.getMinute(), uhr.getSekunde());
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    switch (actionEvent.getActionCommand()) {
      case DigitalUhr.KNOPF_AUS:
      case DigitalUhr.KNOPF_EIN:
        digitalUhrView.toggleUhr();
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (Character.toUpperCase(keyEvent.getKeyChar())) {
      case 'E':
        kreisUhrView.enableUhr();
      case 'A':
        kreisUhrView.disableUhr();
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {

  }
}
