package edu.stachtiedmann.oberflaeche;

import edu.stachtiedmann.bank.GesperrtException;
import edu.stachtiedmann.bank.Girokonto;
import edu.stachtiedmann.bank.Konto;
import edu.stachtiedmann.bank.Kunde;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Created by Christoph Stach on 6/20/17.
 * <p>
 * Der Controller für Kontoo berfläche
 */
public class KontoOberflaecheController {
  private final Stage stage;
  private final KontoOberflaeche view;
  private final Girokonto model;

  public KontoOberflaecheController(Stage stage) {
    this.stage = stage;

    model = new Girokonto(
      new Kunde("Christoph", "Stach", "Plönzeile 21", LocalDate.of(1988, 2, 15)),
      1001,
      500
    );
    model.setKontostand(-50);

    view = new KontoOberflaeche(this, model);
    view.setPadding(new Insets(20, 10, 20, 10));
    stage.setScene(new Scene(view));
  }

  /**
   * Gets view
   *
   * @return value of view
   */
  public KontoOberflaeche getView() {
    return view;
  }

  /**
   * Gets model
   *
   * @return value of model
   */
  public Konto getModel() {
    return model;
  }

  public void abhebenAction(double value) {
    try {
      if (Math.abs((model.getKontostand() - value)) > model.getDispo()) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Sie können nicht weiter ins minus gehen! Ihr Dispo reicht nicht aus.");
        alert.setWidth(500);
        alert.setResizable(true);
        alert.show();
      } else {
        model.abheben(value);
      }

    } catch (GesperrtException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
      alert.setResizable(true);
      alert.show();
    }
  }

  public void einzahlenAction(double value) {
    model.einzahlen(value);
  }
}
