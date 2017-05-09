package edu.stachtiedmann.schereSteinPapier;

import java.util.Random;

public class SchereSteinPapier {
  int[] zahlen = new int[2];

  SchereSteinPapier() {
    Spieler s01 = new Spieler(0);
    Spieler s02 = new Spieler(1);
    Thread t0 = new Thread(s01);
    Thread t1 = new Thread(s02);
    t0.start();
    t1.start();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {}
    synchronized (this) {
      this.notifyAll();
    }

    try {
      t1.join();
      t0.join();
    } catch (InterruptedException e) {
    }

    gewinn();
  }

  public void gewinn() {
    int gewinner = zahlen[0] > zahlen[1] ? 0 : 1;
    System.out.println("Spieler " + gewinner + " hat gewonnen.");
  }

  class Spieler implements Runnable {
    int index;

    public Spieler(int index) {
      this.index = index;
    }

    public void waehlen() {
      synchronized (SchereSteinPapier.this) {
        try {
          SchereSteinPapier.this.wait();
        } catch (InterruptedException e) {
        }
      }
      Random r = new Random();
      zahlen[index] = r.nextInt(3) + 1;
    }

    @Override
    public void run() {
      waehlen();
    }
  }

  public static void main(String[] args) {
    SchereSteinPapier ssp = new SchereSteinPapier();
  }

}
