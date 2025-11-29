package Wk12_Lab;

import java.util.HashSet;
import java.util.Iterator;

public class Account {

    private String accNo;
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private int balance;

    // Store transactions as Transaction objects
    private HashSet<Transaction> transactions = new HashSet<>();

    public Account(String accNo, String firstName, String lastName,
                   String contact, String email, int balance) {

        this.accNo = accNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
        this.balance = balance;
    }

    public String getAccNo() { return accNo; }
    public int getBalance() { return balance; }

    public void credit(int amt) {
        Transaction t = new Transaction("CREDIT", amt);

        if (transactions.contains(t)) {
            System.out.println("❗ Duplicate credit transaction NOT allowed!");
            return;
        }

        balance += amt;
        transactions.add(t);
        System.out.println("Credited: " + amt);
    }

    public void debit(int amt) {
        Transaction t = new Transaction("DEBIT", amt);

        if (transactions.contains(t)) {
            System.out.println("❗ Duplicate debit transaction NOT allowed!");
            return;
        }

        if (amt <= balance) {
            balance -= amt;
            transactions.add(t);
            System.out.println("Debited: " + amt);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void showTransactions() {
        System.out.println("\n--- Transactions for Account " + accNo + " ---");

        Iterator<Transaction> it = transactions.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("Total number of transactions: " + transactions.size());
    }

    @Override
    public String toString() {
        return "\nAccount No: " + accNo +
               "\nName: " + firstName + " " + lastName +
               "\nContact: " + contact +
               "\nEmail: " + email +
               "\nBalance: $" + balance +
               "\n----------------------------------------";
    }

    // Prevent duplicate account numbers
    @Override
    public int hashCode() {
        return accNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Account other = (Account) obj;
        return this.accNo.equals(other.accNo);
    }
}
