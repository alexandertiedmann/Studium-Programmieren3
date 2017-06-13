package edu.stachtiedmann.uhr;

/**
 * Created by Christoph Stach on 6/13/17.
 * <p>
 * View für Uhren
 */
public interface IUhrView {
  public void tick(int hour, int minute, int second);
  public void toggleUhr();
  public void disableUhr();
  public void enableUhr();
}
