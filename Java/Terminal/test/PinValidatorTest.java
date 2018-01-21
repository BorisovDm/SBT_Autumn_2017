import org.junit.Test;
import static org.junit.Assert.assertEquals;
import javax.security.auth.login.AccountLockedException;

public class PinValidatorTest {
    @Test
    public void pinValidatorAccessDefault() throws AccountLockedException {
        PinValidator pinValidator = new PinValidator();
        assertEquals(pinValidator.getAccessState(), false);

        pinValidator.enterPin("IDon'tKnowPassword");
        assertEquals(pinValidator.getAccessState(), false);

        pinValidator.enterPin("0000");
        assertEquals(pinValidator.getAccessState(), true);
    }

    @Test
    public void pinValidatorAccessSetPin() throws AccountLockedException {
        String pinCode = "8887";
        PinValidator pinValidator = new PinValidator(pinCode);
        assertEquals(pinValidator.getAccessState(), false);

        pinValidator.enterPin("IDon'tKnowPassword");
        assertEquals(pinValidator.getAccessState(), false);

        pinValidator.enterPin(pinCode);
        assertEquals(pinValidator.getAccessState(), true);
    }

    @Test
    public void pinValidatorBruteForceAccess(){
        PinValidator pinValidator = new PinValidator("9999");
        assertEquals(pinValidator.getAccessState(), false);

        try {
            for (int i = 1000; i < 2000; i++) {
                pinValidator.enterPin(Integer.toString(i));
                assertEquals(pinValidator.getAccessState(), false);
            }
        } catch (AccountLockedException e) {
            assertEquals(pinValidator.getAccessState(), false);
        }
        assertEquals(pinValidator.getAccessState(), false);
    }

    @Test
    public void pinValidatorImpossibleSituation() throws AccountLockedException {
        String pinCode = "8887";
        PinValidator pinValidator = new PinValidator(pinCode);
        assertEquals(pinValidator.getAccessState(), false);

        pinValidator.enterPin(pinCode);
        assertEquals(pinValidator.getAccessState(), true);

        pinValidator.enterPin("UncorrectPIN");
        assertEquals(pinValidator.getAccessState(), true);

        pinValidator.enterPin(pinCode);
        assertEquals(pinValidator.getAccessState(), true);
    }

    @Test
    public void pinValidatorCheckNumberOfAttempts(){
        PinValidator pinValidator = new PinValidator("9999");
        int attemptsNumber = 0;
        try {
            for (int i = 1000; i < 2000; i++) {
                attemptsNumber++;
                pinValidator.enterPin(Integer.toString(i));
            }
        } catch (AccountLockedException e) {
            assertEquals(pinValidator.getNumberOfAttempts(), attemptsNumber);
        }
    }
}
