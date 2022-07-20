package ex04;

import java.util.UUID;

public class TransactionsService {

    private UsersList usersList = new UsersArrayLIst();

    public void addUser(User user) {
        usersList.add(user);
    }

    public void removeTransactionForUser(Integer id, UUID uuid) {
        usersList.getById(id).removeTransactionById(uuid);
    }

    public Integer getBalanceByUserId(Integer userId) {
        return usersList.getById(userId).getBalance();
    }

    public  Transaction[] getUserTransactionsById(Integer id) {
        return usersList.getById(id).getTransactions();
    }

    public void performTransaction(Integer senderId, Integer recipientId, Integer amount) {

        User recipient = usersList.getById(recipientId);
        User sender = usersList.getById(senderId);

        Transaction creditTransaction = new Transaction(sender, recipient,
                Transaction.Category.CREDITS, -amount);

        Transaction debitTransaction = new Transaction(sender, recipient,
                Transaction.Category.DEBITS, amount);

        debitTransaction.setId(creditTransaction.getId());

        Integer senderNewBalance = sender.getBalance() - amount;
        Integer recipientNewBalance = recipient.getBalance() - amount;

        if (senderNewBalance < 0 || recipientNewBalance < 0) {
            throw new IllegalTransactionException();
        }

        sender.addTransaction(creditTransaction);
        recipient.addTransaction(debitTransaction);

        sender.setBalance(senderNewBalance);
        recipient.setBalance(recipientNewBalance);
    }


    public Transaction[]getUnpairedTransactions() {

        TransactionLInkedList transactionLInkedList = new TransactionLInkedList();

        boolean ok = true;
        Transaction[] curTransactions;

        for (int i = 0; i < usersList.size(); i++) {

            User thisUser;
            try {
                thisUser = usersList.getById(i);
            } catch (RuntimeException e) {
                continue;
            }

            curTransactions = thisUser.getTransactions();

            for (int j = 0; j < curTransactions.length; j++) {
                ok = true;
                User otherUser =  curTransactions[j].getSender() == thisUser ? curTransactions[j].getRecipient() : curTransactions[j].getSender();
                Transaction[] otherTransactions = otherUser.getTransactions();
                for (int k = 0; k < otherTransactions.length; k++) {
                    if (curTransactions[j].getId() == otherTransactions[k].getId() ) {
                        ok = false;
                        break;
                    }
                }
                if (otherTransactions.length == 0 || ok == true) {
                    transactionLInkedList.add(curTransactions[j]);
                }
            }

        }
        return transactionLInkedList.toArray();
    }
}
