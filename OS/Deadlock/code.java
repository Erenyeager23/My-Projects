class Account {

    private String name;

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class TransferThread extends Thread {

    private Account from;
    private Account to;

    public TransferThread(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    public void run() {

        synchronized (from) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " locked "
                    + from.getName());

            try {
                Thread.sleep(1000);
            } catch(Exception e) {}

            System.out.println(
                    Thread.currentThread().getName()
                    + " waiting for "
                    + to.getName());

            synchronized (to) {

                System.out.println(
                        Thread.currentThread().getName()
                        + " transferred money");
            }
        }
    }
}

class DeadlockDemo {

    public static void main(String[] args) {

        Account accountA =
                new Account("Account-A");

        Account accountB =
                new Account("Account-B");

        TransferThread t1 =
                new TransferThread(
                        accountA,
                        accountB);

        TransferThread t2 =
                new TransferThread(
                        accountB,
                        accountA);

        t1.start();
        t2.start();
    }
}