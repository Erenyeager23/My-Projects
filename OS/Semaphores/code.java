import java.util.concurrent.Semaphore;

class Car extends Thread {

    private Semaphore parkingLot;

    public Car(
            Semaphore parkingLot,
            String name) {

        super(name);
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {

        try {

            System.out.println(
                    getName()
                    + " is trying to enter the parking lot.");

            parkingLot.acquire();

            System.out.println(
                    getName()
                    + " got a parking slot.");

            Thread.sleep(3000);

            System.out.println(
                    getName()
                    + " is leaving the parking lot.");

            parkingLot.release();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}

class Main {

    public static void main(String[] args) {

        Semaphore parkingLot =
                new Semaphore(3);

        Car t1 =
                new Car(parkingLot, "Car-A");

        Car t2 =
                new Car(parkingLot, "Car-B");

        Car t3 =
                new Car(parkingLot, "Car-C");

        Car t4 =
                new Car(parkingLot, "Car-D");

        Car t5 =
                new Car(parkingLot, "Car-E");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}