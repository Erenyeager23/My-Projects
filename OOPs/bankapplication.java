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

/*
Explain all the concepts used in the above code:
1. Inheritance: The SavingsAccount and CurrentAccount classes inherit from the BankAccount class, allowing them to reuse common properties and methods while also providing their own specific implementations.
2. Abstraction: The BankAccount class is abstract, meaning it cannot be instantiated on its own. It defines abstract methods like withdraw() that must be implemented by the subclasses, enforcing a contract for how these accounts should behave.
3. Encapsulation: The BankAccount class encapsulates its properties (accountHolder, accountNumber, balance) and provides public methods to access and modify them, ensuring that the internal state of the account is protected from unauthorized access.
4. Polymorphism: The withdraw() method is overridden in both SavingsAccount and CurrentAccount, allowing for different behaviors when withdrawing money based on the type of account. This demonstrates polymorphism, where a single interface (withdraw method) can have multiple implementations.
5. Static Variable: The totalAccounts variable is static, meaning it is shared among all instances of the BankAccount class. It keeps track of the total number of accounts created, regardless of the specific type of account.
6. Final Method: The showAccountDetails() method is declared as final, which means it cannot be overridden by any subclass. This ensures that the way account details are displayed remains consistent across all types of accounts.
7. Interface: The Loan interface defines a contract for applying for a loan, which is implemented by the SavingsAccount class. This allows for a separation of concerns, where the loan functionality can be added to any class that implements the Loan interface without affecting the core functionality of the BankAccount class.

Explain oops concepts in java:
1. Object-Oriented Programming (OOP) is a programming paradigm that uses objects and classes to design and structure software. It focuses on the concepts of encapsulation, inheritance, polymorphism, and abstraction to create modular and reusable code.
2. Encapsulation: This is the concept of bundling data (variables) and methods that operate on the data into a single unit (class) and restricting access to some of the object's components. This is achieved using access modifiers (private, protected, public) to control visibility.
3. Inheritance: This allows a new class (subclass) to inherit properties and behaviors (fields and methods) from an existing class (superclass). This promotes code reusability and establishes a natural hierarchical relationship between classes.
4. Polymorphism: This allows objects of different classes to be treated as objects of a common superclass. It enables a single interface to represent different underlying forms (data types). Polymorphism can be achieved through method overriding (runtime polymorphism) and method overloading (compile-time polymorphism).
5. Abstraction: This is the concept of hiding the complex implementation details and showing only the necessary features of an object. It can be achieved using abstract classes and interfaces, which allow you to define methods that must be implemented by subclasses, without providing the implementation details in the abstract class or interface itself.
6. Class and Object: A class is a blueprint for creating objects, which are instances of a class. A class defines the properties (fields) and behaviors (methods) that the objects created from the class will have. An object is a specific instance of a class with its own state and behavior.


