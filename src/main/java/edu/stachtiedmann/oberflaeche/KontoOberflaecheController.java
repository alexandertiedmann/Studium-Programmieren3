package edu.stachtiedmann.oberflaeche;

import edu.stachtiedmann.bank.Girokonto;
import edu.stachtiedmann.bank.Konto;
import edu.stachtiedmann.bank.Kunde;
import javafx.geometry.Insets;

import java.time.LocalDate;

/**
 * Created by Christoph Stach on 6/20/17.
 * <p>
 * Der Controller für Kontoo berfläche
 */
public class KontoOberflaecheController {
  private final KontoOberflaeche view;
  private final Konto model;

  public KontoOberflaecheController() {
    view = new KontoOberflaeche();
    view.setPadding(new Insets(20, 10, 20, 10));

    model = new Girokonto(
      new Kunde("Christoph", "Stach", "Plönzeile 21", LocalDate.of(1988, 2, 15)),
      1001,
      500
    );
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
}
