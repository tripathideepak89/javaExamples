package com.javaexamples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionProcess {
    /**
     *
     */
    private final List<Transaction> transactionList;

    public TransactionProcess() {
        this.transactionList = new ArrayList<>();
    }
    
    public void addTransaction(Transaction transaction){
        this.transactionList.add(transaction);
    }

    public void deleteTransaction(Integer id){
        this.transactionList.removeIf(t -> t.getId().equals(id));
    }

    public Optional<Transaction> getTransaction(Integer id){
        return this.transactionList.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public List<Transaction> getTransactionList(){
        return this.transactionList;
    }

    public Map<String, Double> getTotalValue(){
        return transactionList.stream().collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getValue)));
    }
}
