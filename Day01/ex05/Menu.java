import java.util.Scanner;
import java.util.UUID;

public class Menu {

    TransactionsService transactionsService = new TransactionsService();

    Mode mode;
    public static final String []commandsDev = {
        "1. Add a user", "2. View user balances", "3. Perform a transfer", "4. View all transactions for a specific user",
            "5. DEV - remove a transfer by ID", "6. DEV - check transfer validity", "7. Finish execution"
    };

    public static final String []commandsProd = {
        "1. Add a user", "2. View user balances", "3. Perform a transfer", "4. View all transactions for a specific user",
            "5. Finish execution"
    };

    public Menu(Mode mode) {

        this.mode = mode;

    }


    public int readCommand(Scanner scanner, int upBorder) {

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            if (input < 1 && input > upBorder) {
                return -1;
            } else {
                return input;
            }
        } else {
            System.err.println("Invalid input");
            System.exit(-1);
            return 0;
        }
    }

    public void addUser(Scanner scanner) {
        System.out.println("Enter a user name and a balance");
        String username = scanner.next();
        Integer balance = scanner.nextInt();

        User user = new User(username, balance);
        transactionsService.addUser(user);
        System.out.println("User with id == " + user.getId() + " added");
    }

    public void performTransfer(Scanner scanner) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        Integer firstUserId = scanner.nextInt();
        Integer secondUserId = scanner.nextInt();
        Integer amount = scanner.nextInt();

        try {
            transactionsService.performTransaction(firstUserId, secondUserId, amount);
            System.out.println("The transfer is completed");
        } catch (RuntimeException e) {
            System.out.println("The transfer is failed");
        }
    }

    public void viewBalance(Scanner scanner) {
        System.out.println("Enter a user ID");

        Integer id = scanner.nextInt();

        try {
            User user = transactionsService.getUserById(id);
            System.out.println(user.getName() + " " + user.getBalance());
        } catch (RuntimeException e) {
            System.out.println("such user dont exist");
        }
    }

    public void removeTransfer(Scanner scanner) {
        System.out.println("Enter a user ID and a transfer ID");
        Integer userId = scanner.nextInt();
        UUID transferId = UUID.fromString(scanner.next());

        try {

            User user = transactionsService.getUserById(userId);

            Transaction[] transactions = user.getTransactions();

            transactionsService.removeTransactionForUser(userId, transferId);

            for (int i = 0; i < transactions.length; i++) {
                if (transactions[i].getId().equals(transferId)) {
                    if (transactions[i].getAmount() < 0) {

                        System.out.printf("Transfer To %s(id == %s) %s removed\n",  transactions[i].getRecipient().getName(),
                                transactions[i].getRecipient().getId(), transactions[i].getAmount()
                        );

                    } else {

                        System.out.printf("Transfer From %s(id == %s) %s removed\n",  transactions[i].getSender().getName(),
                                transactions[i].getSender().getId(), transactions[i].getAmount()
                        );
                    }
                }
            }


        } catch (RuntimeException e) {
            System.out.println("Cannot remove transfer");
        }
    }

    public void checkTransferValidity() {
        System.out.println("Check results:");
        Transaction[] transactions = transactionsService.getUnpairedTransactions();
        if (transactions.length == 0) {
            System.out.println("EMPTY");
        } else {
            for (int i = 0; i < transactions.length; i++) {
                System.out.printf("%s(id == %s) has an unacknowledged transfer id == %s from" +
                        "%s(id == %s) for %s\n", transactions[i].getSender().getName(), transactions[i].getSender().getId(),
                        transactions[i].getId(), transactions[i].getRecipient().getName(),
                        transactions[i].getRecipient().getId(),
                        transactions[i].getAmount());
            }
        }

    }

    public void viewTransactions(Scanner scanner) {
        System.out.println("Enter a user ID");

        Integer id = scanner.nextInt();

        try {
            Transaction[] transactions = transactionsService.getUserTransactionsById(id);
            User user = transactionsService.getUserById(id);
            for (int i = 0; i < transactions.length; i++) {

                if (transactions[i].getAmount() < 0) {

                    System.out.printf("To %s(id == %s) %s with id == %s\n",  transactions[i].getRecipient().getName(),
                            transactions[i].getRecipient().getId(), transactions[i].getAmount(), transactions[i].getId()
                    );

                } else {

                    System.out.printf("From %s(id == %s) %s with id == %s\n",  transactions[i].getSender().getName(),
                            transactions[i].getSender().getId(), transactions[i].getAmount(), transactions[i].getId()
                    );
                }

            }
        } catch (RuntimeException e) {
            System.out.println("Cannot view transaction");
        }
    }

    public enum Mode {
        PRODUCTION,
        DEVELOPER
    }

    public void start() {
        switch (mode) {
            case DEVELOPER :
                startDevMode();
                break;
            case PRODUCTION:
                startProdMode();
        }
    }

    private void startProdMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PROD");
        while (true) {
            for (int i = 0; i < commandsProd.length; i++) {
                System.out.println(commandsProd[i]);
            }
            int command = readCommand(scanner, commandsProd.length);

            switch (command) {
                case 1 :
                    addUser(scanner);
                    break;
                case 2 :
                    viewBalance(scanner);
                    break;
                case 3 :
                    performTransfer(scanner);
                    break;
                case 4 :
                    viewTransactions(scanner);
                    break;
                case 5 :
                    scanner.close();
                    System.out.println("Good bye!");
                    System.exit(0);

            }

            System.out.println("---------------------------------------------------------");
        }

    }

    private void startDevMode() {


        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < commandsDev.length; i++) {
                System.out.println(commandsDev[i]);
            }
            int command = readCommand(scanner, commandsDev.length);

            switch (command) {
                case 1 :
                    addUser(scanner);
                    break;
                case 2 :
                    viewBalance(scanner);
                    break;
                case 3 :
                    performTransfer(scanner);
                    break;
                case 4 :
                    viewTransactions(scanner);
                    break;
                case 5 :
                    removeTransfer(scanner);
                    break;
                case 6 :
                    checkTransferValidity();
                    break;
                case 7 :
                    scanner.close();
                    System.out.println("Good bye!");
                    System.exit(0);

            }

            System.out.println("---------------------------------------------------------");
        }

    }

}
