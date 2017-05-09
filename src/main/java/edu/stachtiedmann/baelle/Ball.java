package edu.stachtiedmann.baelle;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

/**
 * ein huepfender Ball
 *
 * @author Doro
 */
public class Ball {
  private JPanel box;
  private static final int XSIZE = 10;
  private static final int YSIZE = 10;
  private int x = 0;
  private int y = 0;
  private int dx = 2;
  private int dy = 2;
  private Thread thread;
  private BallInterruptMessage ballInterruptMessage;

  private enum BallInterruptMessage {
    PAUSE_BALL,
    RESUME_BALL,
    DELETE_BALL
  }

  /**
   * erstellt einen Ball, der in das angegebene Panel gezeichnet wird
   *
   * @param b Die Zeichenflaeche
   */
  public Ball(JPanel b) {
    box = b;
  }

  /**
   * zeichnet den Ball an seiner aktuellen Position
   */
  public void draw() {
    Graphics g = box.getGraphics();
    g.fillOval(x, y, XSIZE, YSIZE);
    g.dispose();
  }

  /**
   * loescht den Ball von der Oberflaeche
   */
  public void loeschen() {
    Graphics g = box.getGraphics();
    g.setXORMode(box.getBackground());
    g.fillOval(x, y, XSIZE, YSIZE);
  }

  /**
   * bewegt den Ball einen Schritt weiter
   */
  public void move() {
    if (!box.isVisible()) {
      return;
    }

    Graphics g = box.getGraphics();
    g.setXORMode(box.getBackground());
    g.fillOval(x, y, XSIZE, YSIZE);
    x += dx;
    y += dy;
    Dimension d = box.getSize();
    if (x < 0) {
      x = 0;
      dx = -dx;
    }
    if (x + XSIZE >= d.width) {
      x = d.width - XSIZE;
      dx = -dx;
    }
    if (y < 0) {
      y = 0;
      dy = -dy;
    }
    if (y + YSIZE >= d.height) {
      y = d.height - YSIZE;
      dy = -dy;
    }
    g.fillOval(x, y, XSIZE, YSIZE);
    g.dispose();
  }

  /**
   * bewegt den Ball dauer viele Schritte weiter in der Oberflaeche. Um eine angenehme Animation
   * zu erhalten, wird nach jedem Schritt eine Pause eingelegt.
   *
   * @param dauer Anzahl der Schritte
   */
  public void huepfen(int dauer) {
    thread = new Thread(() -> {
      this.draw();
      for (int i = 1; i <= dauer; ) {

        synchronized (this) {
          try {
            if (interrupted()) {

              if (ballInterruptMessage.equals(BallInterruptMessage.DELETE_BALL)) {
                break;
              } else if (ballInterruptMessage.equals(BallInterruptMessage.PAUSE_BALL)) {
                wait();
              } else if (ballInterruptMessage.equals(BallInterruptMessage.RESUME_BALL)) {
                notify();
              }

            } else {
              this.move();
              sleep(5);
              i++;
            }
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }


      }
      this.loeschen();
    });


    thread.start();
  }

  /**
   * Stoppt das huepfen des Balls
   */
  public void stoppen() {
    this.ballInterruptMessage = BallInterruptMessage.PAUSE_BALL;
    thread.interrupt();
  }

  /**
   * Laesst den Ball weiterhuepfen
   */
  public void weiter() {
    this.ballInterruptMessage = BallInterruptMessage.RESUME_BALL;
    thread.interrupt();
  }

  /**
   * Löscht den ball und stoppt den thread
   */
  public void loeschenUndStoppen() {
    this.ballInterruptMessage = BallInterruptMessage.DELETE_BALL;
    thread.interrupt();
  }
}
