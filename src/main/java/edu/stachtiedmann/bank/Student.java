package edu.stachtiedmann.bank;

import java.util.Date;

/**
 * Klasse Student
 *
 * @author Alexander Tiedmann (s0556127), Christoph Stach (s0555912)
 * @version 1.0
 * @since 18.04.2017
 */
public class Student extends Kunde {
  private String nameUni;
  private int semester;
  private String fach;
  private Date voraussichtlichesStudienEnde;

  /**
   * Konstruktor fuer Student
   *
   * @param vorname  Vorname
   * @param nachname Nachname
   * @param adresse  Adresse
   * @param gebdat   Geburtsdatum Format: tt.mm.jj
   * @param name     Name der Universitaet
   * @param sem      Aktuelles Semester
   */
  public Student(String vorname, String nachname, String adresse, String gebdat, String name, int sem, String fach, Date voraussichtlichesStudienEnde) {
    super(vorname, nachname, adresse, gebdat);
    this.nameUni = name;
    if (sem <= 0) {
      this.semester = 0;
    } else {
      this.semester = sem;
    }

    this.fach = fach;
    this.voraussichtlichesStudienEnde = voraussichtlichesStudienEnde;
  }

  /**
   * Gets fach
   *
   * @return value of fach
   */
  public String getFach() {
    return fach;
  }

  /**
   * Sets fach
   *
   * @param fach value for fach
   */
  public void setFach(String fach) {
    this.fach = fach;
  }

  /**
   * Gets voraussichtlichesStudienEnde
   *
   * @return value of voraussichtlichesStudienEnde
   */
  public Date getVoraussichtlichesStudienEnde() {
    return voraussichtlichesStudienEnde;
  }

  /**
   * Sets voraussichtlichesStudienEnde
   *
   * @param voraussichtlichesStudienEnde value for voraussichtlichesStudienEnde
   */
  public void setVoraussichtlichesStudienEnde(Date voraussichtlichesStudienEnde) {
    this.voraussichtlichesStudienEnde = voraussichtlichesStudienEnde;
  }

  /**
   * Gets nameUni
   *
   * @return value of nameUni
   */
  public String getNameUni() {
    return nameUni;
  }

  /**
   * Sets nameUni
   *
   * @param nameUni value for nameUni
   */
  public void setNameUni(String nameUni) {
    this.nameUni = nameUni;
  }

  /**
   * Gets semester
   *
   * @return value of semester
   */
  public int getSemester() {
    return semester;
  }

  /**
   * Sets semester
   *
   * @param semester value for semester
   */
  public void setSemester(int semester) {
    this.semester = semester;
  }

  /**
   * Eingabe einer neuen Semesterbescheinigung
   *
   * @param semester neues Semester
   * @throws ArithmeticException wenn neue Bescheinigung aelter oder gleich alt
   */
  public void neueBescheinigung(int semester) throws ArithmeticException {
    if (this.semester < semester) {
      this.semester = semester;
    } else {
      throw new ArithmeticException("Die Bescheinigung ist genauso alt oder aelter");
    }
  }

  /**
   * Prueft ob der Student eine Bescheinigung hat
   *
   * @return Bescheinigung oder nicht
   */
  public boolean istVerguenstigt() {
    return this.semester > 0;
  }

  @Override
  public String toString() {
    String ausgabe;
    ausgabe = super.toString();
    ausgabe += this.semester + System.getProperty("line.separator");
    ausgabe += this.nameUni + System.getProperty("line.separator");
    return ausgabe;
  }
}
