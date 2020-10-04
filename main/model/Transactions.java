package main.model;

import java.util.*;
import java.util.stream.Collectors;

public class Transactions {
    private List<Transaction> transactions;  // Есть ли смысл делать данноеполе final, если я заранее понимаю, что это поле предназначено для истории и будет постоянно изменяться.

     Transactions() {
        this.transactions = new ArrayList<>();
    }

    void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

     Collection<Transaction> getTransactionsByAccount(Account account){
        return transactions.stream().filter(tr -> tr.getOriginator() == account || tr.getBeneficiary() == account).collect(Collectors.toList());
    }

     List<Transaction> getTransactions() {
        return transactions;
    }
}
