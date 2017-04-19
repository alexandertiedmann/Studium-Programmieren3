package edu.christophstach.bank;

/**
 * Klasse Student
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 18.04.2017
 */
public class Student extends Kunde{
    private String nameUni;
    private int semester;

    /**
     * Konstruktor Student
     * @param name Name der Universitaet
     * @param sem aktuelles Semester
     */
    public Student(String vorname, String nachname, String adresse, String gebdat,String name, int sem){
        super(vorname, nachname, adresse, gebdat);
        this.nameUni = name;
        if (sem <= 0){
            this.semester = 0;
        }else{
            this.semester = sem;
        }
    }

    public String getNameUni(){
        return this.nameUni;
    }

    public int getSemester(){
        return this.semester;
    }

    /**
     * Eingabe einer neuen Semesterbescheinigung
     * @param semester neues Semester
     * @throws ArithmeticException wenn neue Bescheinigung aelter oder gleich alt
     */
    public void neueBescheinigung(int semester) throws ArithmeticException{
        if (this.semester < semester){
            this.semester = semester;
        }else{
            throw new ArithmeticException("Die Bescheinigung ist genauso alt oder aelter");
        }
    }

    /**
     * Prueft ob der Student eine Bescheinigung hat
     * @return Bescheinigung oder nicht
     */
    public boolean istVerguenstigt(){
        if (this.semester > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        String ausgabe;
        ausgabe = super.toString();
        ausgabe += this.semester + System.getProperty("line.separator");
        ausgabe += this.nameUni + System.getProperty("line.separator");
        return ausgabe;
    }
}
