package main.model;

import java.time.LocalDateTime;

public class Entry {
    private final Account account;
    private final Transaction transaction;
    private final double amount;
    private final LocalDateTime time;

    public Entry(Account account, Transaction transaction, double amount, LocalDateTime time) {
        this.account = account;
        this.transaction = transaction;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    LocalDateTime getTime() {
        return time;
    }

    double getAmount() {
        return amount;
    }

     Transaction getTransaction() {
        return transaction;
    }
}
