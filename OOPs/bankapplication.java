import java.util.*;
interface Loan {
    void applyLoan(double amount);
}


abstract class BankAccount {
    private String accountHolder;
    private int accountNumber;
    protected double balance;
    static int totalAccounts = 0;

    public BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        totalAccounts++;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited successfully");
    }

    public final void showAccountDetails() {
        System.out.println("\n----- ACCOUNT DETAILS -----");
        System.out.println("Name : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : " + balance);
    }

    abstract void withdraw(double amount);
}

class SavingsAccount extends BankAccount implements Loan {

    private double interestRate;

    public SavingsAccount(String name, int accNo, double balance, double interestRate) {
        super(name, accNo, balance);
        this.interestRate = interestRate;
    }

    @Override
    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn from Savings Account");
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest Added : " + interest);
    }

    @Override
    public void applyLoan(double amount) {
        System.out.println("Loan Applied in Savings Account : " + amount);
    }
}

class CurrentAccount extends BankAccount {

    private double overdraftLimit;

    public CurrentAccount(String name, int accNo, double balance, double overdraftLimit) {
        super(name, accNo, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void withdraw(double amount) {

        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println(amount + " withdrawn from Current Account");
        } else {
            System.out.println("Overdraft Limit Exceeded");
        }
    }
}

class BankingApplication {

    public static void main(String[] args) {

        SavingsAccount s1 =new SavingsAccount("Sai", 101, 5000, 5);

        CurrentAccount c1 =new CurrentAccount("eren", 201, 10000, 3000);

        s1.deposit(2000);
        s1.withdraw(3000);
        s1.addInterest();
        s1.applyLoan(50000);
        c1.withdraw(12000);

        s1.showAccountDetails();
        c1.showAccountDetails();

        System.out.println("\nTotal Accounts Created : "+ BankAccount.totalAccounts);
    }
}