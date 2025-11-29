package Wk12_Lab;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashSet<Account> accounts = new HashSet<>();

        int choice = 0;

        while (choice != 6) {
            System.out.println("\n========== BANKING APPLICATION MENU ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Show Transactions");
            System.out.println("5. Show All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String menuInput = sc.nextLine();
            try {
                choice = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input — enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {

                case 1:
                    createAccount(sc, accounts);
                    break;

                case 2:
                    depositMoney(sc, accounts);
                    break;

                case 3:
                    withdrawMoney(sc, accounts);
                    break;

                case 4:
                    showTransactions(sc, accounts);
                    break;

                case 5:
                    showAllAccounts(accounts);
                    break;

                case 6:
                    System.out.println("Thank you! Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    //=========================================
    // CREATE ACCOUNT
    //=========================================
    private static void createAccount(Scanner sc, HashSet<Account> accounts) {
        System.out.print("Enter Account Number (Format AC001): ");
        String accNo = sc.nextLine();

        if (!accNo.matches("AC\\d{3,}")) {
            System.out.println("❌ Invalid account number! Must follow format AC001, AC1000...");
            return;
        }

        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();

        System.out.print("Enter Contact Number (10-12 digits): ");
        String contact = sc.nextLine();
        if (!contact.matches("\\d{10,12}")) {
            System.out.println("❌ Invalid phone number! Must be 10–12 digits.");
            return;
        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
            System.out.println("❌ Invalid email format!");
            return;
        }

        System.out.print("Enter Initial Balance (1 - 1,000,000): ");
        String balInput = sc.nextLine();
        int balance;

        try {
            balance = Integer.parseInt(balInput);
            if (balance < 1 || balance > 1_000_000) {
                System.out.println("❌ Balance must be between 1 and 1,000,000");
                return;
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid amount. Please enter numbers only.");
            return;
        }

        Account newAcc = new Account(accNo, fname, lname, contact, email, balance);

        if (accounts.add(newAcc))
            System.out.println("✅ Account successfully created!");
        else
            System.out.println("❌ ERROR: Duplicate Account Number. Not Added.");
    }

    //=========================================
    // DEPOSIT
    //=========================================
    private static void depositMoney(Scanner sc, HashSet<Account> accounts) {
        System.out.print("Enter Account Number to Deposit: ");
        String accNo = sc.nextLine();

        Account acc = findAccount(accounts, accNo);
        if (acc == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit (1 - 1,000,000): ");
        String amtInput = sc.nextLine();

        try {
            int amt = Integer.parseInt(amtInput);
            if (amt < 1 || amt > 1_000_000) {
                System.out.println("❌ Invalid amount! Must be between 1 and 1,000,000");
                return;
            }
            acc.credit(amt);
        } catch (Exception e) {
            System.out.println("❌ Invalid amount.");
        }
    }

    //=========================================
    // WITHDRAW
    //=========================================
    private static void withdrawMoney(Scanner sc, HashSet<Account> accounts) {
        System.out.print("Enter Account Number to Withdraw From: ");
        String accNo = sc.nextLine();

        Account acc = findAccount(accounts, accNo);
        if (acc == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw (1 - 1,000,000): ");
        String amtInput = sc.nextLine();

        try {
            int amt = Integer.parseInt(amtInput);
            if (amt < 1 || amt > 1_000_000) {
                System.out.println("❌ Invalid amount! Must be between 1 and 1,000,000");
                return;
            }
            acc.debit(amt);
        } catch (Exception e) {
            System.out.println("❌ Invalid amount.");
        }
    }

    //=========================================
    // SHOW TRANSACTIONS
    //=========================================
    private static void showTransactions(Scanner sc, HashSet<Account> accounts) {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        Account acc = findAccount(accounts, accNo);
        if (acc == null) {
            System.out.println("❌ Account not found.");
            return;
        }

        acc.showTransactions();
    }

    //=========================================
    // SHOW ALL ACCOUNTS
    //=========================================
    private static void showAllAccounts(HashSet<Account> accounts) {
        System.out.println("\n--- All Accounts (Using Iterator) ---");

        Iterator<Account> it = accounts.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    //=========================================
    // HELPER: FIND ACCOUNT
    //=========================================
    public static Account findAccount(HashSet<Account> accounts, String accNo) {
        for (Account a : accounts) {
            if (a.getAccNo().equals(accNo))
                return a;
        }
        return null;
    }
}
