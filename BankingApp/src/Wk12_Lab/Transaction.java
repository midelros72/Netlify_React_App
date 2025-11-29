package Wk12_Lab;

import java.time.LocalDate;

public class Transaction {
    private String type;      // CREDIT or DEBIT
    private int amount;       // Amount of transaction
    private LocalDate date;   // Date for information only

    public Transaction(String type, int amount) {
        this.type = type;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String getType() { return type; }
    public int getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    // Duplicate logic: Same TYPE + Same AMOUNT = duplicate
    @Override
    public int hashCode() {
        return (type + amount).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Transaction t = (Transaction) obj;
        return this.type.equals(t.type) && this.amount == t.amount;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + type + ": " + amount;
    }
}
