package ex03;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {


        TransactionsList list = new TransactionLInkedList();

        Transaction transaction = new Transaction();
        list.add(transaction);


//
//        for (int i = 0; i < 1; i++) {
//            list.add(new Transaction());
//        }

        list.removeById(transaction.getId());

        Transaction[] transactions = list.toArray();

        System.out.println(transactions.length);

        System.out.println(list.size());


//        Transaction[] transactions = list.toArray();
//        for (int i = 0; i < transactions.length; i++) {
//            System.out.println(transactions[i]);
//        }
//
//        System.out.println( "SIZE : " + list.size());
//
//        list.add(new Transaction());
//        list.add(new Transaction());
//        list.add(new Transaction());
//        list.add(new Transaction());
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(transactions[i]);
//        }
//
//        System.out.println("######################");
//        Transaction[] transactions1 = list.toArray();
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(transactions1[i]);
//        }
//
//        System.out.println("######################");
//
//        UUID test = transactions[2].getId();
//        System.out.println(test);
//        System.out.println("######################");
//
//        System.out.println(test);
//
//        list.removeById(test);
//
//        System.out.println("######################");
//
//
//        Transaction[] transactions2 = list.toArray();
//
//        for (int i = 0; i < 4; i++) {
//            System.out.println(transactions2[i]);
//        }

    }

}
