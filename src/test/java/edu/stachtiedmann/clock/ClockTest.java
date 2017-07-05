package edu.stachtiedmann.clock;

import junit.framework.TestCase;

/**
 * Tests for Clock
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 05.07.2017
 */
public class ClockTest extends TestCase {
  Clock uhr;

  public void setUp() {
    uhr = new Clock();
  }

  public void testModes() {
    assertTrue(uhr.getState().equals(ClockStateNormalMode.getInstanz()));
    uhr.modeButton();
    assertTrue(uhr.getState().equals(ClockStateChangeModeHour.getInstanz()));
    uhr.modeButton();
    assertTrue(uhr.getState().equals(ClockStateChangeModeMinute.getInstanz()));
    uhr.modeButton();
    assertTrue(uhr.getState().equals(ClockStateNormalMode.getInstanz()));
  }

  public void testChangeInNormal(){
    assertTrue(uhr.getState().equals(ClockStateNormalMode.getInstanz()));
    int hour = uhr.getHr();
    int minute = uhr.getMin();
    uhr.changeButton();
    assertTrue((hour == uhr.getHr()) && (minute == uhr.getMin()));
  }

  public void testChangeInChangeModeHour(){
    uhr.setState(ClockStateChangeModeHour.getInstanz());
    assertTrue(uhr.getState().equals(ClockStateChangeModeHour.getInstanz()));
    int hour = uhr.getHr();
    int minute = uhr.getMin();
    uhr.changeButton();
    assertTrue((hour+1 == uhr.getHr()) && (minute == uhr.getMin()));
  }

  public void testChangeInChangeModeMinute(){
    uhr.setState(ClockStateChangeModeMinute.getInstanz());
    assertTrue(uhr.getState().equals(ClockStateChangeModeMinute.getInstanz()));
    int hour = uhr.getHr();
    int minute = uhr.getMin();
    uhr.changeButton();
    assertTrue((hour == uhr.getHr()) && (minute+1 == uhr.getMin()));
  }
}
