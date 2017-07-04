package edu.stachtiedmann.oberflaechefxml;

import edu.stachtiedmann.bank.GesperrtException;
import edu.stachtiedmann.bank.Girokonto;
import edu.stachtiedmann.bank.Kunde;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.time.LocalDate;

/**
 * Created by Christoph Stach on 6/20/17.
 * <p>
 * Der Controller für Kontoo berfläche
 */
public class KontoOberflaecheController {
  @FXML
  private Girokonto model;
  @FXML
  private Text kontonummer;
  @FXML
  private Text kontostand;
  @FXML
  private CheckBox gesperrt;
  @FXML
  private TextArea inhaberAdresse;
  @FXML
  private Spinner betrag;

  public KontoOberflaecheController() throws GesperrtException {
  }

  @FXML
  public void initialize() throws GesperrtException {
    gesperrt.selectedProperty().bindBidirectional(model.gesperrtProperty());
    inhaberAdresse.textProperty().bindBidirectional(model.getInhaber().adresseProperty());

    model.positiveProperty().addListener(observable -> {
      if (model.isPositive()) {
        if(kontostand.getStyleClass().contains("negative")) {
          kontostand.getStyleClass().remove("negative");
        }
      } else {
        if(!kontostand.getStyleClass().contains("negative")) {
          kontostand.getStyleClass().add("negative");
        }
      }
    });
  }

  public void einzahlen() {
    double value = (double) betrag.getValue();

    model.einzahlen(value);
  }

  public void abheben() {
    double value = (double) betrag.getValue();

    try {
      if ((model.getKontostand() - value) < (model.getKontostand() - model.getDispo())) {
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
}
