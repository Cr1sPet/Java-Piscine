
package ex04;

import java.util.UUID;


class Transaction {

    private UUID id;

    private User recipient;

    private User sender;

    private Category category;

    private Integer amount;

    Transaction() {
        this.id = UUID.randomUUID();
    }

    public Transaction(User recipient, User sender, Category category, Integer amount) {
        this.id = id.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        setAmount(amount);
    }

    public Transaction(User recipient, User sender, Integer amount) {
        this.id = id.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        setAmount(amount);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if (category == Category.DEBITS && amount < 0 ||
            category == Category.CREDITS && amount > 0) {
            amount = 0;
        } else {
            this.amount = amount;
        }
    }

    public enum Category {
        DEBITS,
        CREDITS;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", category=" + category +
                ", amount=" + amount +
                '}';
    }
}