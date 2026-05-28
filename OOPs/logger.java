import java.util.ArrayList;

class Logger {

    private static Logger instance;

    private ArrayList<String> logs;

    private Logger() {

        logs = new ArrayList<>();
    }

    public static synchronized Logger getInstance() {

        if(instance == null) {

            instance = new Logger();
        }

        return instance;
    }

    public void addLog(String message) {

        logs.add(message);
    }

    public void displayLogs() {

        System.out.println("\n------ LOG MESSAGES ------");

        for(String log : logs) {

            System.out.println(log);
        }
    }
}

class Main {

    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.addLog("Application Started this is logger1");
        logger1.addLog("User Logged In this is logger1");
        logger2.addLog("Amount Deposited this is logger2");
        logger2.addLog("Logout Successful this is logger2");

        logger1.displayLogs();
        System.out.println("Logger1 and Logger2 are the same: " + (logger1 == logger2));
    }
}


/*
Definition: The Singleton pattern is a software design pattern that restricts the instantiation of a class to a single instance and provides a global point of access to that instance. 
This is useful when exactly one object is needed to coordinate actions across the system.
steps to implement Singleton pattern in Java:
1. Private Constructor: Make the constructor of the class private to prevent direct instantiation from outside the class.
2. Static Variable: Create a private static variable of the same class type to hold the single instance of the class.
3. Static Method: Provide a public static method that returns the instance of the class. This method checks if the instance already exists; if it does, it returns the existing instance. If it doesn't, it creates a new instance and returns it. 

sychronization can be added to the getInstance() method to make it thread-safe, ensuring that only one instance is created even in a multi-threaded environment. However, this can lead to performance issues, so other techniques like double-checked locking or using an inner static helper class can be used for better performance.
steps to implement thread-safe Singleton pattern in Java:
1. Synchronized Method: Make the getInstance() method synchronized to ensure that only one thread can access it at a time. This is the simplest way to make the Singleton thread-safe, but it can lead to performance issues due to the overhead of synchronization.
2. Double-Checked Locking: This technique reduces the overhead of synchronization by first checking if the instance is null without synchronization. If it is null, then it synchronizes and checks again before creating the instance. This way, synchronization is only used when the instance is being created for the first time.
3. Static Inner Class: This approach uses a static inner class to hold the Singleton instance. The instance is created when the inner class is loaded, which is thread-safe and does not require synchronization. This is often considered the best way to implement a thread-safe Singleton in Java.

Explain the concepts used in the above code:
1. Singleton Pattern: The Logger class is implemented as a Singleton, ensuring that only one instance of the Logger exists throughout the application. This is achieved by making the constructor private and providing a static method to access the instance.
2. Lazy Initialization: The instance of the Logger is created only when it is needed (when getInstance() is called for the first time). This is known as lazy initialization, which can save resources if the instance is not needed immediately.
3. Thread Safety: The getInstance() method is synchronized to ensure that only one thread can create the instance of the Logger at a time, making it thread-safe. This prevents multiple threads from creating multiple instances of the Logger in a multi-threaded environment.
4. Encapsulation: The Logger class encapsulates its data (the logs) and provides public methods to interact with that data (addLog and displayLogs). This hides the internal implementation of how logs are stored and managed from the users of the Logger class.
5. Static Methods and Variables: The getInstance() method and the instance variable are static, meaning they belong to the class rather than any particular instance. This allows the getInstance() method to be called without needing an instance of the Logger class, and ensures that there is only one instance of the Logger shared across the entire application.
*/