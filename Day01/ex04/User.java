package ex04;

import java.util.UUID;

class User {

    private final Integer id;
    private String name;
    private Integer balance;
    private TransactionsList transactionsList = new TransactionLInkedList();

    public User() {
        this.id = UserIdsGenerator.getInstance().generateId();
    }

    public User(String name, int balance) {

        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;

        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionsList.add(transaction);
    }

    public int getId() {
        return id;
    }

    public Transaction[] getTransactions() {
        return transactionsList.toArray();
    }

    public void removeTransactionById(UUID id) {
        transactionsList.removeById(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}