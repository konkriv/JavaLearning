package main.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class Account {
    private final long id;
    private final TransactionManager transactionManager;
    private final Entries entries;
    static int counter = 0;

    public Account(TransactionManager transactionManager) {
        this.id = counter++;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
    }

    /**
     * Withdraws money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount, Account beneficiary) {
        if (currentBalance() <= amount) return false;
        transactionManager.createTransaction(amount, this, beneficiary);
        return true;
    }

    /**
     * Withdraws cash money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdrawCash(double amount) {
        if (currentBalance() <= amount) return false;
        transactionManager.createTransaction(amount, this, getCashMachineAccount());
        return true;
    }

    /**
     * Adds cash money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean addCash(double amount) {
        if (amount <= amount) return false;
        transactionManager.createTransaction(amount, getCashMachineAccount(), this);
        return true;
    }


    public Collection<Entry> history(LocalDate from, LocalDate to) {
        return entries.betweenDates(from, to);
    }

    /**
     * Calculates balance on the accounting entries basis
     *
     * @param date
     * @return balance
     */
    public double balanceOn(LocalDate date) {
        return entries.to(date).stream().mapToDouble(Entry::getAmount).sum();
    }


    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        transactionManager.rollbackTransaction(entries.last().getTransaction());
    }

    void addEntry(Transaction transaction, double amount) {
        entries.addEntry(new Entry(this, transaction, amount, LocalDateTime.now()));
    }

    private Account getCashMachineAccount() {
        return new Account(transactionManager);
    }

    private double currentBalance() {
        return entries.getEntries().stream().mapToDouble(Entry::getAmount).sum();
    }
}

