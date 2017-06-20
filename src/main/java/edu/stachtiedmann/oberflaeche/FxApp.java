package edu.stachtiedmann.oberflaeche;

import edu.stachtiedmann.bank.Girokonto;
import edu.stachtiedmann.bank.Konto;
import edu.stachtiedmann.bank.Kunde;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Christoph Stach on 6/20/17.
 * <p>
 * FxApp
 */
public class FxApp extends Application {
  /**
   * Main (will be invoked when the program launches)
   *
   * @param primaryStage The stage
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    KontoOberflaecheController kontoOberflaecheController = new KontoOberflaecheController();

    primaryStage.setScene(new Scene(kontoOberflaecheController.getView()));
    primaryStage.show();

  }
}
