package edu.stachtiedmann.clock;

/**
 * eine Uhr
 */
public class Clock {
  private IState state;

  /**
   * get hoiur
   *
   * @return actual hour
   */
  public int getHr() {
    return hr;
  }

  /**
   * getMinute
   *
   * @return actual minute
   */
  public int getMin() {
    return min;
  }

  private int hr;
  private int min;

  /**
   * erzeugt eine Uhr, die die Uhrzeit 0:0 anzeigt
   */
  public Clock() {
    setState(ClockStateNormalMode.getInstanz());
    hr = 0;
    min = 0;
  }

  /**
   * set the Clockstate
   *
   * @param newState new Clockstate
   */
  void setState(IState newState) {
    if (newState == null) {
      this.state = ClockStateNormalMode.getInstanz();
    } else {
      this.state = newState;
    }
  }

  /**
   * get Clockstate
   *
   * @return state of clock
   */
  IState getState() {
    return this.state;
  }

  /**
   * gibt die aktuelle Urzeit auf der Konsole aus
   */
  public void showTime() {
    System.out.println("Current time is Hr : " + hr + " Min: " + min);
  }

  /**
   * Uhr um eine Stunde vorstellen
   */
  public void changeHour() {
    hr++;
    if (hr == 24)
      hr = 0;
    System.out.println("CHANGE pressed - ");
    showTime();
  }

  /**
   * Uhr um eine Minute vorstellen
   */
  public void changeMinute() {
    min++;
    if (min == 60)
      min = 0;
    System.out.println("CHANGE pressed - ");
    showTime();
  }

  /**
   * simuliert den Druck auf den Change-Knopf der Uhr. Im Normal-Modus wird die Uhrzeit mit eingeschaltetem Licht
   * angezeigt, im Aenderungsmodus wird die Uhr um eine Stunde bzw. um eine Minute vorgestellt.
   */
  public void changeButton() {
    state.changeButton(this);
  }

  /**
   * simuliert den Knopf auf den Mode-Knopf der Uhr. Er wechselt zwischen dem Normalmodus, dem Aenderungsmodus fuer
   * die Stunde und dem Aenderungsmodus fuer die Minute.
   */
  public void modeButton() {
    state.modeButton(this);
  }

  /**
   * Anzeige der aktuellen Uhrzeit mit Licht
   */
  public void displayTimeWithLight() {
    System.out.println("LIGHT ON: ");
    showTime();
  }
}
