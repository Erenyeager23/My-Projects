class ProcessTask {

    String processName;
    int remainingTime;

    public ProcessTask(String processName,
                       int remainingTime) {

        this.processName = processName;
        this.remainingTime = remainingTime;
    }
}

public class ContextSwitchDemo {

    public static void main(String[] args)
            throws InterruptedException {

        ProcessTask[] processes = {

                new ProcessTask("Chrome", 5),
                new ProcessTask("Spotify", 4),
                new ProcessTask("VS Code", 3)
        };

        int timeSlice = 1;

        System.out.println(
                "CPU Scheduling Started\n");

        boolean completed;

        do {

            completed = true;

            for(ProcessTask p : processes) {

                if(p.remainingTime > 0) {

                    completed = false;

                    System.out.println(
                            "CPU -> "
                            + p.processName
                            + " running");

                    Thread.sleep(1000);

                    p.remainingTime -= timeSlice;

                    System.out.println(
                            "Context Switch");

                    System.out.println();
                }
            }

        } while(!completed);

        System.out.println(
                "All Processes Completed");
    }
}

/*
code explains the concept of context switching in CPU scheduling. It simulates a round-robin scheduling algorithm where multiple processes (Chrome, Spotify, VS Code) are given a time slice to execute. The CPU switches between processes after each time slice, demonstrating how context switching allows multiple processes to share CPU time effectively. The program continues until all processes have completed their execution.
code uses a simple loop to manage the execution of processes and simulates the time taken for each process to run, as well as the context switch between them.

definition of context switching: Context switching is the process of storing the state of a currently running process or thread, so that it can be resumed later, and then loading the state of another process or thread to run. This allows multiple processes to share a single CPU effectively, giving the illusion of concurrent execution.

*/