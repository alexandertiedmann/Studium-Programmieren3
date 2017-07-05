package edu.stachtiedmann.clock;

/**
 * Interface for State of Clock
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 05.07.2017
 */
public interface IState {
  /**
   * simuliert den Druck auf den Change-Knopf der Uhr. Im Normal-Modus wird die Uhrzeit mit eingeschaltetem Licht
   * angezeigt, im Aenderungsmodus wird die Uhr um eine Stunde bzw. um eine Minute vorgestellt.
   */
  void changeButton(Clock clock);

  /**
   * simuliert den Knopf auf den Mode-Knopf der Uhr. Er wechselt zwischen dem Normalmodus, dem Aenderungsmodus fuer
   * die Stunde und dem Aenderungsmodus fuer die Minute.
   */
  void modeButton(Clock clock);

}
