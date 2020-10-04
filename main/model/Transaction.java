package main.model;

public class Transaction {
    private final long id;
    private final double amount;
    private final Account originator;
    private final Account beneficiary;
    private final boolean executed;
    private final boolean rolledBack;

    public Transaction(long id, double amount, Account originator, Account beneficiary, boolean executed, boolean rolledBack) {
        this.id = id;
        this.amount = amount;
        this.originator = originator;
        this.beneficiary = beneficiary;
        this.executed = executed;
        this.rolledBack = rolledBack;
    }

    /**
     * Adding entries to both accounts
     * @throws IllegalStateException when was already executed
     */
    public Transaction execute() {
        if (executed) throw new IllegalStateException("Transaction was already executed");
        Transaction executedTransaction = new Transaction(id, amount, originator, beneficiary, true, false);
        originator.addEntry(executedTransaction, -amount);
        beneficiary.addEntry(executedTransaction, amount);
        return executedTransaction;
        //Из за того, что поле executed является final, получается, что либо у созданных entry будет ссылка на  НЕвыполненную транзакцию, либо выполненная транзакция будет создаваться до создания Entry
        //Зачем возвращать транзакцию, если она нигде дальше не используется ?
    }

    /**
     * Removes all entries of current transaction from originator and beneficiary
     * @throws IllegalStateException when was already rolled back
     */
    public Transaction rollback() {
        if (executed) throw new IllegalStateException("Transaction was already rollback");
        Transaction rollbackTransaction = new Transaction(id, amount, originator, beneficiary, true, false);
        originator.addEntry(rollbackTransaction, amount);
        beneficiary.addEntry(rollbackTransaction, -amount);
        return rollbackTransaction;

        //Из за того, что поле rollback является final, получается, что либо у созданных entry будет ссылка на  НЕвыполненную транзакцию, либо выполненная транзакция будет создаваться до создания Entry
        //Зачем возвращать транзакцию, если она нигде дальше не используется ?
    }

    Account getOriginator() {
        return originator;
    }

     Account getBeneficiary() {
        return beneficiary;
    }

    public double getAmount() {
        return amount;
    }
}
