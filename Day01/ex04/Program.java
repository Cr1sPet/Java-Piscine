package ex04;

import java.util.UUID;

public class Program {

    public static void printArray(Transaction[] transactions) {

        System.out.println("#####################");

        for (int i = 0; i < transactions.length; i++) {
            System.out.println(transactions[i]);
        }
        if(transactions.length ==0) {
            System.out.println("EMPTY");
        }

    }

    public static void main(String[] args) {


        TransactionsService ts = new TransactionsService();

        User user1 = new User("Bob", 1000000);
        User user2 = new User("Mark", 1000000);
        User user3 = new User("Mike", 1000000);

        ts.addUser(user1);
        ts.addUser(user2);
        ts.addUser(user3);

        ts.performTransaction(user1.getId(), user2.getId(), 1340);
        ts.performTransaction(user2.getId(), user1.getId(), 4134);
        ts.performTransaction(user1.getId(), user2.getId(), 61345);
        ts.performTransaction(user2.getId(), user1.getId(), 4);
        ts.performTransaction(user1.getId(), user2.getId(), 76);

        ts.performTransaction(user3.getId(), user1.getId(), 445);
        ts.performTransaction(user2.getId(), user3.getId(), 445);
        ts.performTransaction(user1.getId(), user2.getId(), 76);

        printArray(ts.getUserTransactionsById(user1.getId()));
        printArray(ts.getUserTransactionsById(user2.getId()));

        ts.removeTransactionForUser(user2.getId(), user2.getTransactions()[0].getId());
        ts.removeTransactionForUser(user2.getId(), user2.getTransactions()[0].getId());
        ts.removeTransactionForUser(user3.getId(), user3.getTransactions()[1].getId());

        printArray(ts.getUserTransactionsById(user1.getId()));
        printArray(ts.getUserTransactionsById(user2.getId()));

        printArray(ts.getUnpairedTransactions());

    }

}
