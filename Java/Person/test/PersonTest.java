import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void testMarryPersonsWithSameGender(){
        testMarryPersonsWithSameGender(true);
        testMarryPersonsWithSameGender(false);
    }

    @Test
    private void testMarryPersonsWithSameGender(boolean gender){
        Person p1 = new Person(gender, "1");
        Person p2 = new Person(gender, "2");
        assertEquals(false, p1.marry(p2));
    }

    @Test
    public void testMarryAlreadyMarriedPersons(){
        Person p1 = new Person(true, "1");
        Person p2 = new Person(false, "2");
        assertEquals(true, p1.marry(p2));

        assertEquals(false, p1.marry(p2));
        assertEquals(false, p2.marry(p1));
    }

    @Test
    public void testDivorceUnmarriedPerson(){
        Person p1 = new Person(true, "1");
        assertEquals(false, p1.divorce());

        Person p2 = new Person(false, "2");
        assertEquals(false, p2.divorce());
    }

    @Test
    public void testDivorceJustUnmarriedPersons(){
        Person p1 = new Person(true, "1");
        Person p2 = new Person(false, "2");
        assertEquals(true, p1.marry(p2));

        assertEquals(true, p1.divorce());
        assertEquals(false, p2.divorce());
        assertEquals(false, p1.divorce());
    }

    @Test
    public void testMarryDivorceFor4Persons(){
        Person p1 = new Person(true, "1");
        Person p2 = new Person(true, "2");
        Person p3 = new Person(false, "3");
        Person p4 = new Person(false, "4");

        assertEquals(true, p1.marry(p3));
        assertEquals(true, p2.marry(p4));

        assertEquals(true, p1.marry(p4));

        assertEquals(false, p2.divorce());
        assertEquals(false, p3.divorce());

        assertEquals(true, p1.divorce());
        assertEquals(false, p1.divorce());
    }
}