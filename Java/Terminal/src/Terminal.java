import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.security.auth.login.AccountLockedException;

public class Terminal {
    private static final PinValidator pinValidator = new PinValidator("123");
    private static final TerminalServer terminalServer = new TerminalServer();

    private static final String WELCOME_MESSAGE = "\nWelcome to the Terminal!\n";
    private static final String COMMAND_INPUT_MESSAGE = ">>> ";
    private static final String INVITATION_TO_ENTER_PIN_MESSAGE = "Enter the PIN code:\n";
    private static final String INVITATION_TO_ENTER_REPLENISH_SUM_MESSAGE = "Enter the sum for replenish:\n";
    private static final String INVITATION_TO_ENTER_WITHDRAWAL_SUM_MESSAGE = "Enter the sum for withdrawal:\n";

    private static final String INVITATION_MESSAGE_TERMINAL = "Number - command:\n" +
            "1 - enter PIN code\n" +
            "2 - exit the Terminal\n" +
            "\nPlease enter the number of command:\n";

    private static final String INVITATION_MESSAGE_SERVER = "\nNumber - command:\n" +
            "1 - find out the balance\n" +
            "2 - put money\n" +
            "3 - take money\n" +
            "4 - exit the Terminal\n" +
            "\nPlease enter the number of command:\n";

    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        boolean accessToAccount = false;
        int enteredCommand;

        System.out.println(WELCOME_MESSAGE);
        while (true){
            if (!accessToAccount) {
                System.out.print(INVITATION_MESSAGE_TERMINAL);
                System.out.print(COMMAND_INPUT_MESSAGE);

                try {
                    enteredCommand = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.print("You entered wrong command!\nTry again.\n\n");
                    continue;
                }

                switch (enteredCommand){
                    case 1:
                        while (true) {
                            try {
                                System.out.print(INVITATION_TO_ENTER_PIN_MESSAGE);
                                System.out.print(COMMAND_INPUT_MESSAGE);

                                pinValidator.enterPin(in.nextLine());
                                accessToAccount = pinValidator.getAccessState();
                                if (accessToAccount) {
                                    System.out.print("You entered the correct PIN code.\nWelcome.\n\n");
                                    break;
                                } else {
                                    System.out.print("You entered the wrong PIN code.\nTry again.\n\n");
                                }
                            } catch (AccountLockedException ex) {
                                int blockedTimeInSeconds = pinValidator.getAccountLockoutTimeInSeconds();
                                int numberOfAttempts = pinValidator.getNumberOfAttempts();
                                String errorMessage = "You entered the wrong PIN code " +
                                        Integer.toString(numberOfAttempts) + " times\n" +
                                        "Your account is locked for " +
                                        Integer.toString(blockedTimeInSeconds) + " seconds";
                                System.out.println(errorMessage);
                                TimeUnit.SECONDS.sleep(blockedTimeInSeconds);
                                break;
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Finish work with the Terminal.\nGoodbye!\n");
                        return;
                    default:
                        System.out.print("You entered wrong command!\nTry again.\n\n");
                        break;
                }
            }
            else{
                System.out.print(INVITATION_MESSAGE_SERVER);
                System.out.print(COMMAND_INPUT_MESSAGE);

                try {
                    enteredCommand = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.print("You entered wrong command!\nTry again.\n\n");
                    continue;
                }

                switch (enteredCommand){
                    case 1:
                        System.out.print("Your balance: " + terminalServer.getBalance() + "\n");
                        break;
                    case 2:
                        System.out.print(INVITATION_TO_ENTER_REPLENISH_SUM_MESSAGE);
                        System.out.print(COMMAND_INPUT_MESSAGE);

                        try {
                            int count = Integer.parseInt(in.nextLine());
                            terminalServer.putMoney(count);
                            System.out.print("Your balance: " + terminalServer.getBalance() + "\n");
                        } catch (IncorrectSumException ex) {
                            System.out.print("Entered incorrect sum\n");
                        }
                        break;
                    case 3:
                        System.out.print(INVITATION_TO_ENTER_WITHDRAWAL_SUM_MESSAGE);
                        System.out.print(COMMAND_INPUT_MESSAGE);

                        try {
                            int count = Integer.parseInt(in.nextLine());
                            terminalServer.getMoney(count);
                            System.out.print("Your balance: " + terminalServer.getBalance() + "\n");
                        } catch (IncorrectSumException ex) {
                            System.out.print("Entered incorrect sum\n");
                        } catch (InsufficientFundsException ex) {
                            System.out.print("Insufficient funds on the balance\n");
                        }
                        break;
                    case 4:
                        System.out.print("Finish work with the Terminal.\nGoodbye!\n");
                        return;
                    default:
                        System.out.print("You entered wrong command!\nTry again.\n\n");
                        break;
                }
            }
        }
    }
}
