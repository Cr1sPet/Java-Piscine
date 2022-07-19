package ex03;

import javax.xml.bind.SchemaOutputResolver;
import java.util.UUID;

public class TransactionLInkedList implements TransactionsList {

    Node head = null;
    Node tail = null;
    int size = 0;

    @Override
    public void add(Transaction transaction) {

        Node tempLast = tail;
        Node temp = new Node(transaction);
        tail = temp;

        if (tempLast == null){
            head = temp;
        } else {
            tempLast.next = temp;
        }
//        tail = temp;
        size++;
    }

    @Override
    public void removeById(UUID id) {

        if (null == id) {
            throw new TransactionNotFoundException("Bad id");
        }

        Node currentNode = head;

        while (currentNode.next != null) {
            if (currentNode.next.transaction.getId().equals(id)) {
                System.out.println(currentNode.transaction);
                currentNode.next = currentNode.next.next;
                System.out.print ("NEW NEXT : ");
                System.out.println(currentNode.next.transaction);
                size--;
                return;
            }
            currentNode = currentNode.next;
        }

        throw new TransactionNotFoundException("Bad id2");
    }

    @Override
    public Transaction[] toArray() {

        if (size == 0) {
            return null;
        }

        Transaction[] transactions = new Transaction[size];

        int i = 0;
        Node currentNode = head;
        while (currentNode != null) {
            transactions[i] = currentNode.transaction;
            currentNode = currentNode.next;
            i++;
        }
        return transactions;
    }

    private class Node {
        Transaction transaction;
        Node next;

        Node ( Transaction transaction ) {
            this.transaction = transaction;
            this.next = null;
        }
    }


}
