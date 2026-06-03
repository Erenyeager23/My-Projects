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
