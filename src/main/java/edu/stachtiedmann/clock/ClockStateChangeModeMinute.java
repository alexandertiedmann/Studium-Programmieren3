package edu.stachtiedmann.clock;

/**
 * State: Change Mode for Minutes from Clock
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 05.07.2017
 */
public class ClockStateChangeModeMinute implements IState {
  private static ClockStateChangeModeMinute instanz = new ClockStateChangeModeMinute();

  public static IState getInstanz() {
    return instanz;
  }

  @Override
  public void changeButton(Clock clock) {
    clock.changeMinute();
  }

  @Override
  public void modeButton(Clock clock) {
    System.out.println("** Clock is in normal display.");
    clock.setState(ClockStateNormalMode.getInstanz());
  }
}
