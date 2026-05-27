import java.util.ArrayList;

class Logger {

    private static Logger instance;

    private ArrayList<String> logs;

    private Logger() {

        logs = new ArrayList<>();
    }

    public static Logger getInstance() {

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