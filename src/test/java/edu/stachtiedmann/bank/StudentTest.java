package edu.stachtiedmann.bank;

import junit.framework.TestCase;

import java.time.Instant;
import java.util.Date;

/**
 * Test fuer Student
 *
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 18.04.2017
 */
public class StudentTest extends TestCase {
  Student s1, s2;

  protected void setUp() {
    s1 = new Student(
      "Test", "Test", "Teststr",
      "01.01.00", "HTW", 3, "Andgewandte Informatik", Date.from(Instant.parse("2019-03-31T00:00:00.00Z"))
    );

    s2 = new Student(
      "Test", "Test", "Teststr",
      "03.02.89", "HTW", 0, "Angewandte Informatik", Date.from(Instant.parse("2019-03-31T00:00:00.00Z"))
    );
  }

  public void testIstVerguenstigt() {
    assertTrue(s1.istVerguenstigt());
  }

  public void testIstVerguenstigtFail() {
    assertFalse(s2.istVerguenstigt());
  }

  public void testNeueBescheinigung() {
    boolean ok = true;
    try {
      s1.neueBescheinigung(4);
    } catch (ArithmeticException e) {
      ok = false;
    }
    assertTrue(ok);
    assertTrue(s1.getSemester() == 4);
  }

  public void testNeueBescheinigungFail() {
    boolean ok = true;
    try {
      s1.neueBescheinigung(2);
    } catch (ArithmeticException e) {
      ok = false;
    }
    assertFalse(ok);
    assertTrue(s1.getSemester() == 3);
  }


}
