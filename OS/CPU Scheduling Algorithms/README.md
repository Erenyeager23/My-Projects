# CPU Scheduling Algorithms

## Objective

Understand how an Operating System decides which process gets the CPU and for how long.

CPU Scheduling is one of the most important responsibilities of an Operating System because multiple processes may be waiting for CPU execution at the same time.

---

# What is CPU Scheduling?

CPU Scheduling is the process of selecting a process from the Ready Queue and allocating the CPU to it.

In simple words:

The Operating System decides:

* Which process runs first
* Which process runs next
* How long a process can run

---

# Why is CPU Scheduling Needed?

A CPU core can execute only one process at a time.

However, modern computers run many applications simultaneously:

* Chrome
* Spotify
* VS Code
* WhatsApp
* File Explorer

Since all processes cannot execute at the same time on a single core, the Operating System must decide which process gets CPU access.

---

# Real Life Analogy

Imagine a single ATM machine.

Customers arrive:

Customer A → Withdraw Money

Customer B → Check Balance

Customer C → Deposit Money

Only one customer can use the ATM at a time.

The rule used to decide who gets the ATM first is similar to a CPU Scheduling Algorithm.

---

# Process States

A process usually moves through the following states:

New
↓
Ready
↓
Running
↓
Waiting
↓
Ready
↓
Terminated

Scheduling mainly occurs when a process enters the Ready state.

---

# Scheduler

The Scheduler is a component of the Operating System that selects the next process for execution.

Responsibilities:

* Select next process
* Manage Ready Queue
* Improve CPU utilization
* Reduce waiting time
* Improve responsiveness

---

# Dispatcher

The Dispatcher gives control of the CPU to the process selected by the scheduler.

Responsibilities:

* Perform context switch
* Switch CPU mode
* Start process execution

Dispatch Latency:

Time required to stop one process and start another process.

Smaller latency means better performance.

---

# Important Terms

## Arrival Time (AT)

Time at which a process enters the Ready Queue.

Example:

P1 arrives at time 0

P2 arrives at time 2

---

## Burst Time (BT)

Amount of CPU execution time required by a process.

Example:

P1 requires 5 seconds

P2 requires 3 seconds

---

## Completion Time (CT)

Time when a process finishes execution.

---

## Turnaround Time (TAT)

Total time spent by a process in the system.

Formula:

TAT = CT - AT

---

## Waiting Time (WT)

Time spent waiting in the Ready Queue.

Formula:

WT = TAT - BT

---

## Response Time (RT)

Time between process arrival and first CPU allocation.

Important for interactive systems.

---

# Goals of CPU Scheduling

A good scheduling algorithm should:

* Maximize CPU utilization
* Maximize throughput
* Minimize waiting time
* Minimize turnaround time
* Minimize response time
* Ensure fairness

---

# 1. First Come First Serve (FCFS)

## Definition

The process that arrives first gets the CPU first.

Queue behavior:

First In → First Out

---

## Example

Processes:

P1 = 5 sec

P2 = 3 sec

P3 = 2 sec

Execution:

P1 → P2 → P3

---

## Real Life Example

Queue at a ticket counter.

The first customer arriving gets service first.

---

## Advantages

* Simple
* Easy to implement
* Low overhead

---

## Disadvantages

* High waiting time
* Poor responsiveness
* Convoy Effect

---

## Convoy Effect

A short process waits behind a very long process.

Example:

P1 = 100 sec

P2 = 2 sec

P3 = 1 sec

P2 and P3 wait unnecessarily long.

---

# 2. Shortest Job First (SJF)

## Definition

The process with the smallest burst time executes first.

---

## Example

Processes:

P1 = 10 sec

P2 = 2 sec

P3 = 1 sec

Execution:

P3 → P2 → P1

---

## Real Life Example

Supermarket express lane.

Customers with fewer items are served first.

---

## Advantages

* Minimum average waiting time
* Better throughput

---

## Disadvantages

* Difficult to predict burst time
* Long processes may starve

---

# Starvation

A process waits indefinitely because other processes continuously receive CPU allocation.

Example:

Long process waiting while many short processes keep arriving.

---

# 3. Priority Scheduling

## Definition

Processes with higher priority execute first.

---

## Example

Priority Values:

Emergency Transaction = 1

Loan Request = 2

Report Generation = 3

Lower number means higher priority.

Execution:

Emergency → Loan → Report

---

## Real Life Example

Hospital Emergency Room.

Critical patients receive treatment before routine patients.

---

## Advantages

* Important tasks complete quickly
* Suitable for real-time systems

---

## Disadvantages

* Starvation of low-priority processes

---

# Aging

A technique used to prevent starvation.

As waiting time increases, process priority gradually improves.

Eventually every process gets CPU time.

---

# 4. Round Robin (RR)

## Definition

Each process gets a fixed amount of CPU time called a Time Quantum.

After the quantum expires, CPU switches to the next process.

---

## Example

Time Quantum = 2 sec

Processes:

P1 = 5 sec

P2 = 4 sec

P3 = 3 sec

Execution:

P1 → P2 → P3 → P1 → P2 → P3 → P1

---

## Real Life Example

A teacher gives each student exactly two minutes to ask questions before moving to the next student.

---

## Advantages

* Fair scheduling
* Good response time
* Suitable for interactive systems

---

## Disadvantages

* Too many context switches if quantum is very small
* Poor response if quantum is too large

---

# Time Quantum Selection

Very Small Quantum:

* Better responsiveness
* More context switches
* Higher overhead

Very Large Quantum:

* Fewer context switches
* Behaves like FCFS

A balanced value is preferred.

---

# Scheduling Algorithm Comparison

| Algorithm   | Fairness | Waiting Time | Response Time | Starvation |
| ----------- | -------- | ------------ | ------------- | ---------- |
| FCFS        | Medium   | High         | Poor          | No         |
| SJF         | Low      | Lowest       | Good          | Yes        |
| Priority    | Low      | Medium       | Good          | Yes        |
| Round Robin | High     | Medium       | Excellent     | No         |

---

# Real World Usage

## Windows

Uses priority-based scheduling with time slicing.

---

## Linux

Uses Completely Fair Scheduler (CFS).

Goal:

Provide fair CPU allocation.

---

## Android

Built on Linux scheduling mechanisms.

---

## Databases

Examples:

* MySQL
* PostgreSQL

Use scheduling internally for query execution.

---

## Web Servers

Examples:

* Apache
* Nginx
* Tomcat

Use scheduling to serve multiple clients efficiently.

---

# Banking Application Example

Incoming Requests:

User A → Balance Inquiry

User B → Money Transfer

User C → Loan Application

The server cannot execute all requests simultaneously on a single CPU core.

Scheduling algorithms determine:

* Which request runs first
* How long it runs
* Which request runs next

---

# Common Interview Questions

## What is CPU Scheduling?

Selecting a process from the Ready Queue and allocating CPU resources.

---

## Why is Scheduling Required?

Because multiple processes compete for a limited number of CPU cores.

---

## Which Scheduling Algorithm Minimizes Waiting Time?

Shortest Job First (SJF).

---

## What is Starvation?

A process waits indefinitely because other processes keep getting CPU access.

---

## How is Starvation Prevented?

Using Aging.

---

## Why is Round Robin Popular?

Because it provides fairness and good responsiveness.

---

## What is Time Quantum?

Maximum CPU time allocated to a process before context switching.

---

## Difference Between Scheduler and Dispatcher?

Scheduler:

Selects the next process.

Dispatcher:

Actually transfers CPU control to that process.

---

# Key Takeaways

* CPU Scheduling decides which process gets the CPU.
* Scheduler selects the next process.
* Dispatcher performs the switch.
* FCFS is simple but may cause Convoy Effect.
* SJF minimizes average waiting time.
* Priority Scheduling handles important tasks first.
* Round Robin provides fairness and is widely used.
* Starvation can occur in SJF and Priority Scheduling.
* Aging is used to prevent starvation.
* Scheduling is essential for multitasking operating systems.


Here is a detailed breakdown of CPU scheduling algorithms, focusing on the Multi-Level Feedback Queue (MLFQ), Round Robin time-slice tradeoffs, and the production scheduling architectures of Windows and Linux.

---

## 1. Multi-Level Feedback Queue (MLFQ)

### How It Works

The **Multi-Level Feedback Queue (MLFQ)** is an adaptive CPU scheduling algorithm designed to optimize process throughput and response time without requiring prior knowledge of a process's execution length.

It divides the ready queue into a hierarchy of distinct sub-queues (e.g., $Q_0, Q_1, \dots, Q_n$), each with different priority levels and scheduling configurations.

```
       [ High Priority ] ──>  Q0  (Time Quantum = 4ms)  ───┐ (Preempted / Demoted)
                              │                            │
       [ Med Priority ]  ──>  Q1  (Time Quantum = 8ms)  <──┘
                              │                            │ (Preempted / Demoted)
       [ Low Priority ]  ──>  Q2  (FCFS / Long Quantum) <──┘

```

The algorithm operates on five core rules (originally formalized by Arpaci-Dusseau):

1. **Rule 1:** If $\text{Priority}(A) > \text{Priority}(B)$, process $A$ runs, and $B$ does not.
2. **Rule 2:** If $\text{Priority}(A) = \text{Priority}(B)$, they run in Round Robin (RR) fashion using the time quantum of that specific queue.
3. **Rule 3:** When a process enters the system for the first time, it is placed at the highest priority queue ($Q_0$).
4. **Rule 4 (Feedback):** * **Rule 4a:** If a process uses up its entire time allotment in a single stretch, its priority is **demoted** (shifted down one queue).
* **Rule 4b:** If a process yields the CPU before its time slice is up (e.g., to perform I/O), it **stays** at the same priority level.


5. **Rule 5 (Boosting):** After some fixed time period $S$, move all the processes in the system to the topmost queue ($Q_0$).

### How MLFQ Prevents Starvation

Because long-running, CPU-bound tasks are continuously demoted to the lowest-priority queues, they risk being starved if a continuous stream of short interactive jobs enters the system.

MLFQ prevents this via **Rule 5 (Periodic Priority Boosting)**. By periodically resetting all processes to the top queue, long-running processes are guaranteed to receive a share of the CPU. Furthermore, if a CPU-bound process has changed its behavior to become interactive, the boost allows the algorithm to re-evaluate it.

---

## 2. Round Robin (RR) & Time Quantum Selection

The efficiency of a Round Robin scheduler depends heavily on the size of its **Time Quantum ($q$)**—the maximum continuous time a process can execute before being preempted.

### Scenario A: Time Quantum is Too Large ($q \rightarrow \infty$)

* **What happens:** If the time quantum is larger than the longest process burst time, the algorithm degrades into **First-Come, First-Served (FCFS)** scheduling.
* **Trade-off:**
* **Advantage:** Context-switching overhead drops to zero, maximizing raw CPU computation efficiency.
* **Disadvantage:** Response times suffer drastically. Short interactive processes are forced to wait in the ready queue behind long, compute-heavy jobs, degrading user experience.



### Scenario B: Time Quantum is Too Small ($q \rightarrow 0$)

* **What happens:** The system approaches an ideal mechanism called **Processor Sharing**, where $N$ processes appear to run simultaneously on the hardware, each at $1/N$-th of the total CPU speed.
* **Trade-off:**
* **Advantage:** Excellent response time. Interactive applications feel highly performant because every process gets a turn almost instantly.
* **Disadvantage:** Massive **Context-Switching Overhead**. If a context switch takes $1 \text{ ms}$ and the time quantum is set to $1 \text{ ms}$, the CPU spends 50% of its time executing OS management routines rather than user program instructions.



---

## 3. Real-World OS Schedulers: Windows vs. Linux

Modern commercial operating systems do not use pure textbooks designs like FCFS or pure Round Robin; instead, they implement highly sophisticated hybrid scheduling models.

### Windows Scheduling Architecture

* **What it uses:** Windows uses a **Multilevel Feedback Queue based on priority levels** (32 distinct priority levels divided into Real-Time, Variable, and Idle classes). It is a preemptive, priority-driven scheduler.
* **Why:** Windows is heavily optimized for **Client-Desktop and User-Interface Responsiveness**.
* It employs a technique called **Priority Boosting**. When a user clicks on a window, brings an application to the foreground, or when an application wakes up from an I/O wait, the Windows kernel temporarily boosts that thread's priority and extends its time quantum.
* This guarantees that desktop interactions (mouse moves, window dragging, typing) remain fluid and lag-free, even under heavy background workloads.



### Linux Scheduling Architecture

* **What it uses:** Modern Linux uses a design based on the **Completely Fair Scheduler (CFS)** or its modern successor, the **EEVDF (Earliest Eligible Virtual Deadline First)** scheduler.
* **Why:** Linux is designed to scale dynamically from mobile devices to massive enterprise clouds and supercomputer clusters.
* **How CFS works:** Instead of utilizing traditional fixed priority queues, CFS uses a time-ordered **Red-Black Tree** to track execution metrics. It assigns a `vruntime` (virtual runtime) variable to every process, tracking exactly how much time that process has spent executing on a CPU core. The scheduler always selects the node in the tree with the absolute smallest `vruntime` to execute next.
* **The Rationale:** CFS guarantees perfect proportional fairness. Instead of managing complex rules for shifting tasks across discrete queues, it scales execution dynamically. If there are $N$ processes, each is guaranteed approximately $1/N$ of the CPU power. Priorities are adjusted via a metric called **Nice Values**, which scale the weight of a process’s `vruntime` accumulation (high-priority processes accumulate virtual execution time slower, allowing them to remain on the execution cores longer).