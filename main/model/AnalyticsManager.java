package main.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        return transactionManager
                .getTransactions()
                .stream()
                .collect(Collectors.groupingBy(Transaction::getBeneficiary))
                .entrySet().stream()
                .max(Comparator.comparingInt(value -> value.getValue().size()))
                .get().getKey();
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {
        return transactionManager.getTransactions()
                .stream()
                .filter(tr -> tr.getOriginator() == account)
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }
}
