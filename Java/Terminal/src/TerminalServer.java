public final class TerminalServer {
    private int balance;

    public int getBalance(){
        return balance;
    }

    public void putMoney(int sum) throws IncorrectSumException {
        checkSum(sum);
        balance += sum;
    }

    public void getMoney(int sum) throws IncorrectSumException, InsufficientFundsException {
        checkSum(sum);
        checkRest(sum);
        balance -= sum;
    }

    private void checkSum(int sum) throws IncorrectSumException {
        if(!(sum > 0 & sum % 100 == 0)){
            throw new IncorrectSumException();
        }
    }

    private void checkRest(int sum) throws InsufficientFundsException {
        if (sum > balance){
            throw new InsufficientFundsException();
        }
    }
}
