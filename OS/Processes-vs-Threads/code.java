import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {

    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(double balance) {

        this.balance = balance;
        transactions = new ArrayList<>();

        transactions.add(
                "Account Created with Balance ₹" + balance);
    }

    public synchronized void checkBalance() {

        System.out.println(
                "\n" + Thread.currentThread().getName()
                + " -> Current Balance: ₹" + balance);
    }

    public synchronized void transferMoney(double amount) {

        System.out.println(
                "\n" + Thread.currentThread().getName()
                + " -> Processing Transfer...");

        try {
            Thread.sleep(3000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        if(amount <= balance) {

            balance -= amount;

            transactions.add(
                    "Transferred ₹" + amount);

            System.out.println(
                    Thread.currentThread().getName()
                    + " -> Transfer Successful");
        }
        else {

            transactions.add(
                    "Failed Transfer ₹" + amount
                    + " (Insufficient Balance)");

            System.out.println(
                    Thread.currentThread().getName()
                    + " -> Insufficient Balance");
        }
    }

    public synchronized void transactionHistory() {

        System.out.println(
                "\n" + Thread.currentThread().getName()
                + " -> Loading Transaction History...");

        try {
            Thread.sleep(2000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        for(String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class BalanceThread extends Thread {

    private BankAccount account;

    public BalanceThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.checkBalance();
    }
}

class TransferThread extends Thread {

    private BankAccount account;
    private double amount;

    public TransferThread(
            BankAccount account,
            double amount) {

        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.transferMoney(amount);
    }
}

class HistoryThread extends Thread {

    private BankAccount account;

    public HistoryThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.transactionHistory();
    }
}

class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter Initial Balance: ");

        double balance = sc.nextDouble();

        System.out.print(
                "Enter Transfer Amount: ");

        double amount = sc.nextDouble();

        BankAccount account =
                new BankAccount(balance);

        BalanceThread balanceThread =
                new BalanceThread(account);

        TransferThread transferThread =
                new TransferThread(account, amount);

        HistoryThread historyThread =
                new HistoryThread(account);

        balanceThread.start();
        transferThread.start();

        transferThread.join();

        historyThread.start();

        balanceThread.join();
        historyThread.join();

        System.out.println(
                "\nMain Thread -> Banking Operations Completed");

        sc.close();
    }
}

/*

This code simulates a simple banking application using multithreading in Java. It defines a `BankAccount` class that manages the account balance and transaction history, and three thread classes (`BalanceThread`, `TransferThread`, and `HistoryThread`) to perform different operations on the bank account concurrently. The `BankAccount` class has synchronized methods to ensure thread safety when accessing and modifying the account balance
and transaction history. The `main` method initializes the bank account and starts the threads to perform balance checking, money transfer, and viewing transaction history. The threads are joined to ensure that the main thread waits for their completion before printing the final message.

Concepts demonstrated in this code include:
1. Multithreading: Creating and managing multiple threads to perform concurrent operations.
2. Synchronization: Using synchronized methods to prevent race conditions when multiple threads access shared resources (the bank account).
3. Thread Communication: Using `join()` to ensure that the main thread waits for the completion of other threads before proceeding.

definitions:
- Multithreading: The ability of a program to execute multiple threads concurrently, allowing for better performance and responsiveness.
- Synchronization: A mechanism to control access to shared resources in a multithreaded environment, ensuring that only one thread can access the resource at a time to prevent data inconsistency.
- Thread Communication: The process by which threads can coordinate their actions and share information, often using methods like `join()` to wait for other threads to complete before proceeding.

*/ 