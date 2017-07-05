package edu.stachtiedmann.clock;

/**
 * State Change Mode of Hours from Clock
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 05.07.2017
 */
public class ClockStateChangeModeHour implements IState {
  private static ClockStateChangeModeHour instanz = new ClockStateChangeModeHour();

  public static IState getInstanz() {
    return instanz;
  }

  @Override
  public void changeButton(Clock clock) {
    clock.changeHour();
  }

  @Override
  public void modeButton(Clock clock) {
    System.out.println("** UPDATING MIN: Press CHANGE button to increase minutes.");
    clock.setState(ClockStateChangeModeMinute.getInstanz());
  }
}
