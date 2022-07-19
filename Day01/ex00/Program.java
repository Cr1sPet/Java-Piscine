
package ex00;

class Program {

    public static void main(String[] args) {


        System.out.println("TEST user balance parsing ");

        User user3 = new User(3, "Greg", -200);

        System.out.println(user3);

        User user1 = new User(1, "Bob", 100);

        System.out.println(user1);

        User user2 = new User(2, "Mike", 2100);

        System.out.println("###############################");

        System.out.println("TEST transaction category parsing");

        Transaction transaction = new Transaction(user1, user2, Transaction.Category.DEBITS, 100);

        System.out.println(transaction);

        Transaction transaction1 = new Transaction(user1, user2, Transaction.Category.DEBITS, -100);

        System.out.println(transaction1);

        Transaction transaction2 = new Transaction(user1, user2, Transaction.Category.CREDITS, 100);

        System.out.println(transaction2);

        System.out.println("###############################");
    }

}