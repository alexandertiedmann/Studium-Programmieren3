import edu.christophstach.bank.Student;
import junit.framework.TestCase;

/**
 * Test fuer Student
 * @author Alexander Tiedmann (s0556127)
 * @version 1.0
 * @since 18.04.2017
 */
public class StudentTest extends TestCase {
    Student s1,s2;

    protected void setUp(){
        s1 = new Student("Test","Test", "Teststr","2000-01-01","HTW",3);
        s2 = new Student("Test","Test", "Teststr","2000-01-02","HTW",0);
    }

    public void testIstVerguenstigt(){
        assertTrue(s1.istVerguenstigt());
    }

    public void testIstVerguenstigtFail(){
        assertFalse(s2.istVerguenstigt());
    }

    public void testNeueBescheinigung(){
        boolean ok = true;
        try {
            s1.neueBescheinigung(4);
        }catch (ArithmeticException e){
            ok = false;
        }
        assertTrue(ok);
        assertTrue(s1.getSemester() == 4);
    }

    public void testNeueBescheinigungFail(){
        boolean ok = true;
        try {
            s1.neueBescheinigung(2);
        }catch (ArithmeticException e){
            ok = false;
        }
        assertFalse(ok);
        assertTrue(s1.getSemester() == 3);
    }


}
