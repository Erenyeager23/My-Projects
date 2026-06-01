class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {

        System.out.println(
                Thread.currentThread().getName()
                + " wants to withdraw ₹" + amount);

        if (balance >= amount) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " is processing...");

            try {
                Thread.sleep(2000); // simulate bank processing
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance = balance - amount;

            System.out.println(
                    Thread.currentThread().getName()
                    + " successfully withdrew ₹" + amount);

            System.out.println(
                    "Remaining Balance = ₹" + balance);
        }
        else {
            System.out.println(
                    Thread.currentThread().getName()
                    + " Transaction Failed! Insufficient Balance");
        }

        System.out.println("--------------------------------");
    }

    public synchronized void deposit(int amount) {

        System.out.println(
                Thread.currentThread().getName()
                + " depositing ₹" + amount);

        balance += amount;

        System.out.println(
                "Updated Balance = ₹" + balance);

        System.out.println("--------------------------------");
    }

    public void checkBalance() {
        System.out.println(
                Thread.currentThread().getName()
                + " Balance = ₹" + balance);
    }
}

class Customer implements Runnable {

    private BankAccount account;
    private String operation;
    private int amount;

    public Customer(BankAccount account,
                    String operation,
                    int amount) {

        this.account = account;
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public void run() {

        if(operation.equalsIgnoreCase("withdraw")) {
            account.withdraw(amount);
        }
        else if(operation.equalsIgnoreCase("deposit")) {
            account.deposit(amount);
        }
        else if(operation.equalsIgnoreCase("balance")) {
            account.checkBalance();
        }
    }
}

class Main {

    public static void main(String[] args) {

        BankAccount account =
                new BankAccount(10000);

        Thread user1 = new Thread(
                new Customer(account,
                             "withdraw",
                             7000),
                "User-A");

        Thread user2 = new Thread(
                new Customer(account,
                             "withdraw",
                             5000),
                "User-B");

        Thread user3 = new Thread(
                new Customer(account,
                             "deposit",
                             3000),
                "User-C");

        Thread user4 = new Thread(
                new Customer(account,
                             "balance",
                             0),
                "User-D");

        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}

/*
concept of multithreading in Java is a powerful feature that allows multiple threads to run concurrently, enabling efficient utilization of resources and improved performance. In the provided code, we have a BankAccount class that simulates a bank account with methods for withdrawing, depositing, 
and checking the balance. The Customer class implements the Runnable interface and represents a customer performing various operations on the bank account. In the main method, we create a BankAccount instance with an initial balance of ₹10,000
and multiple threads representing different customers performing operations like withdrawing, depositing, and checking the balance. The synchronized keyword is used to ensure that only one thread can access the critical sections of code that modify the account balance at a time, preventing
race conditions and ensuring thread safety. This example demonstrates how multithreading can be used to simulate real-world scenarios, such as banking transactions, while maintaining data integrity and consistency.

explanation of the code:
1. BankAccount Class: This class represents a bank account with a balance. It has methods for withdrawing, depositing, and checking the balance. The withdraw and deposit methods are synchronized to ensure that only one thread can access them at a time, preventing
race conditions and ensuring thread safety.
2. Customer Class: This class implements the Runnable interface and represents a customer performing operations on the bank account. It has a constructor that takes a BankAccount instance, an operation type (withdraw, deposit, or balance), and an amount for withdrawal or deposit.
3. Main Class: In the main method, we create a BankAccount instance with an initial balance of ₹10,000. We then create multiple threads representing different customers performing various operations on the bank account. Each thread is started to execute the run method of the Customer class, which performs the specified operation on the bank account.


 */