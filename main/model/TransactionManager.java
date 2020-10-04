package main.model;

import java.util.Collection;
import java.util.List;

public class TransactionManager {
    static long transactionIdCounter = 0;
    private final Transactions transactions;

    public TransactionManager() {
        this.transactions = new Transactions();
    }

    /**
     * Creates and stores transactions
     * @param amount
     * @param originator
     * @param beneficiary
     * @return created Transaction
     */


    public Transaction createTransaction(double amount,
                                         Account originator,
                                         Account beneficiary) {

        Transaction transaction = new Transaction(transactionIdCounter++, amount, originator, beneficiary, false, false);
        transactions.addTransaction(transaction);
        return transaction;
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
       return transactions.getTransactionsByAccount(account);
    }


    public void rollbackTransaction(Transaction transaction) {
        try{
            transaction.rollback();
            transactions.addTransaction(transaction);
        } catch (IllegalStateException e){
            throw new RuntimeException(e);
        }
    }

    public void executeTransaction(Transaction transaction) {
        try{
            transaction.execute();
            transactions.addTransaction(transaction);
        } catch (IllegalStateException e){
            throw new RuntimeException(e);
        }
    }

    List<Transaction> getTransactions() {
        return transactions.getTransactions();
    }
}
