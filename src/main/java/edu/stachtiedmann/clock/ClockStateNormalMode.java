package edu.stachtiedmann.clock;

/**
 * State: Normal Mode from Clock
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 05.07.2017
 */
public class ClockStateNormalMode implements IState {
  private static ClockStateNormalMode instanz = new ClockStateNormalMode();

  static IState getInstanz() {
    return instanz;
  }

  @Override
  public void changeButton(Clock clock) {
    clock.displayTimeWithLight();
  }

  @Override
  public void modeButton(Clock clock) {
    System.out.println(" UPDATING HR: Press CHANGE button to increase hours.");
    clock.setState(ClockStateChangeModeHour.getInstanz());
  }
}
