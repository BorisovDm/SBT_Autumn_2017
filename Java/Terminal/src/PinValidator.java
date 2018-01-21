import javax.security.auth.login.AccountLockedException;

public final class PinValidator {
    private final String pinCode;
    private final int accountLockoutTimeInSeconds = 5;
    private final int numberOfAttempts = 3;
    private boolean access = false;
    private int unsuccessfulAttempts = 0;

    PinValidator(){
        this("0000");
    }

    PinValidator(String pinCode){
        this.pinCode = pinCode;
    }

    public void enterPin(final String enteredPinCode) throws AccountLockedException {
        if (getAccessState())
            return;

        if (enteredPinCode.equals(pinCode)){
            access = true;
        }
        else {
            unsuccessfulAttempts++;
            if(unsuccessfulAttempts == numberOfAttempts){
                unsuccessfulAttempts = 0;
                throw new AccountLockedException();
            }
        }
    }

    public boolean getAccessState(){
        return access;
    }

    public int getAccountLockoutTimeInSeconds(){
        return accountLockoutTimeInSeconds;
    }

    public int getNumberOfAttempts(){
        return numberOfAttempts;
    }
}
