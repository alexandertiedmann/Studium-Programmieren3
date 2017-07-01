package edu.stachtiedmann.oberflaechefxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Christoph Stach on 6/20/17.
 * <p>
 * FxmlApp
 */
public class FxmlApp extends Application {
  /**
   * Main (will be invoked when the program launches)
   *
   * @param primaryStage The stage
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KontoOberflaeche.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/KontoOberflaeche.css").toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
