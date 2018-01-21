import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TerminalServerTest {
    @Test
    public void TerminalServerCheckInitialBalance() {
        TerminalServer terminalServer = new TerminalServer();
        assertEquals(terminalServer.getBalance(), 0);
    }

    @Test
    public void TerminalServerCheckSum() {
        TerminalServer terminalServer = new TerminalServer();
        boolean success;

        for (int i = -210; i <= 0; i += 10) {
            success = true;
            try {
                terminalServer.putMoney(i);
            } catch (IncorrectSumException e) {
                success = false;
            }
            assertEquals(success, false);
        }

        for (int i = 100; i <= 10000; i += 200) {
            success = true;
            try {
                terminalServer.putMoney(i);
            } catch (IncorrectSumException e) {
                success = false;
            }
            assertEquals(success, true);
        }

        for (int i = 111; i <= 10000; i += 200) {
            success = true;
            try {
                terminalServer.putMoney(i);
            } catch (IncorrectSumException e) {
                success = false;
            }
            assertEquals(success, false);
        }
    }

    @Test
    public void TerminalServerCheckPutMoney() throws IncorrectSumException {
        TerminalServer terminalServer = new TerminalServer();
        int balance = 0;
        assertEquals(terminalServer.getBalance(), balance);

        for (int i = 100; i <= 1000; i += 100) {
            terminalServer.putMoney(i);
            balance += i;
            assertEquals(terminalServer.getBalance(), balance);
        }
    }

    @Test
    public void TerminalServerCheckGetMoney() throws IncorrectSumException, InsufficientFundsException {
        TerminalServer terminalServer = new TerminalServer();
        int count = 10, sum = 100, balance;
        terminalServer.putMoney(count * sum);
        balance = terminalServer.getBalance();

        for (int i = 0; i < count; i++) {
            terminalServer.getMoney(sum);
            balance -= sum;
            assertEquals(terminalServer.getBalance(), balance);
        }
    }

    @Test
    public void TerminalServerCheckRest() throws IncorrectSumException {
        TerminalServer terminalServer = new TerminalServer();
        int sum = 100;
        terminalServer.putMoney(sum);

        boolean success = true;
        try {
            terminalServer.getMoney(2 * sum);
        } catch (InsufficientFundsException e) {
            success = false;
        }
        assertEquals(success, false);
    }
}
