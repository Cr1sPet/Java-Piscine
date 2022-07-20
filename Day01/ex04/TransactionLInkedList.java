package ex04;

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
            throw new TransactionNotFoundException("id is null");
        }

        Node currentNode = head;

        if (currentNode.transaction.getId().equals(id)) {
            head = currentNode.next;
            currentNode.next = null;
            size--;
            return;
        }

        while (currentNode.next != null) {
            if (currentNode.next.transaction.getId().equals(id)) {
                if (currentNode.next == tail) {
                    tail = currentNode;
                    currentNode.next = null;
                }
                else {
                    currentNode.next = currentNode.next.next;
                }
                size--;
                return;
            }
            currentNode = currentNode.next;
        }

        throw new TransactionNotFoundException("cannot find element by id");
    }

    @Override
    public Transaction[] toArray() {

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

    public Integer size() {
        return size;
    }


}
