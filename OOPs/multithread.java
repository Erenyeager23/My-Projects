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