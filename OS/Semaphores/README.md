# Semaphores

## Objective

Understand what a Semaphore is, why it is needed, how it works internally, and where it is used in Operating Systems and real-world applications.

---

# What is a Semaphore?

A Semaphore is a synchronization mechanism used to control access to a limited number of resources.

It keeps track of how many resources are currently available.

Think of it as a resource counter.

---

# Why Do We Need Semaphores?

Suppose:

* 100 users connect to a database.
* Database allows only 10 connections.

Without control:

All 100 users try to access the database.

This may overload the system.

Semaphore ensures:

Only 10 users access the database simultaneously.

Remaining users wait.

---

# Real Life Analogy

## Parking Lot

Parking Lot Capacity:

3 Cars

Cars Arriving:

* Car A
* Car B
* Car C
* Car D
* Car E

Initially:

Available Slots = 3

Car A enters

Available Slots = 2

Car B enters

Available Slots = 1

Car C enters

Available Slots = 0

Car D arrives

No slot available.

Car D waits.

Car E also waits.

When Car A leaves:

Available Slots = 1

Car D enters.

This is exactly how a semaphore works.

---

# Semaphore as a Counter

Initial:

Semaphore = 3

After first acquire():

Semaphore = 2

After second acquire():

Semaphore = 1

After third acquire():

Semaphore = 0

After fourth acquire():

Thread waits.

No resources available.

---

# Types of Semaphores

## 1. Counting Semaphore

Counter range:

0 to N

Example:

10 Database Connections

Semaphore Value = 10

Up to 10 threads can access simultaneously.

---

## 2. Binary Semaphore

Counter range:

0 or 1

Example:

Printer Access

Available = 1

Busy = 0

Similar to a Mutex.

---

# Semaphore Operations

## acquire()

Request a resource.

Internally:

Semaphore--

If value becomes negative or resource unavailable:

Thread waits.

Example:

```java
parkingLot.acquire();
```

---

## release()

Return a resource.

Internally:

Semaphore++

Example:

```java
parkingLot.release();
```

---

# How the Program Works

We create:

```java
Semaphore parkingLot =
        new Semaphore(3);
```

Meaning:

3 parking slots available.

---

Cars:

```java
Car t1 = new Car(parkingLot, "Car-A");
Car t2 = new Car(parkingLot, "Car-B");
Car t3 = new Car(parkingLot, "Car-C");
Car t4 = new Car(parkingLot, "Car-D");
Car t5 = new Car(parkingLot, "Car-E");
```

Five cars compete for three slots.

---

# Step-by-Step Execution

Initial:

Available Slots = 3

---

Car-A enters

Available Slots = 2

---

Car-B enters

Available Slots = 1

---

Car-C enters

Available Slots = 0

---

Car-D arrives

Must wait.

---

Car-E arrives

Must wait.

---

Car-A leaves

Available Slots = 1

Car-D enters.

---

Car-B leaves

Available Slots = 1

Car-E enters.

---

# Why Threads Are Required

Without threads:

```java
t1.run();
t2.run();
t3.run();
```

Execution becomes sequential.

No competition exists.

Semaphore becomes meaningless.

With threads:

```java
t1.start();
t2.start();
t3.start();
```

Multiple cars attempt entry simultaneously.

Semaphore becomes useful.

---

# start() vs run()

Correct:

```java
t1.start();
```

Creates a new thread.

---

Wrong:

```java
t1.run();
```

Runs on the current thread.

No concurrency.

---

# Thread States

Car Thread Lifecycle:

NEW
↓
RUNNABLE
↓
RUNNING
↓
WAITING (if semaphore unavailable)
↓
RUNNABLE
↓
TERMINATED

---

# Internal Working of Semaphore

Imagine:

Semaphore = 3

Thread 1:

acquire()

Semaphore = 2

---

Thread 2:

acquire()

Semaphore = 1

---

Thread 3:

acquire()

Semaphore = 0

---

Thread 4:

acquire()

No resource available.

Thread moves to WAITING queue.

---

Thread 1:

release()

Semaphore = 1

Waiting thread wakes up.

---

# Where Semaphores Are Used

## Database Connection Pools

Example:

MySQL Connection Pool

Maximum Connections = 20

Semaphore = 20

Only 20 threads access simultaneously.

---

## Web Servers

Examples:

* Apache Tomcat
* Nginx

Control concurrent requests.

---

## Banking Systems

Example:

Only 5 ATMs available.

Customers must wait when all ATMs are occupied.

---

## Thread Pools

Limit active workers.

Example:

10 Worker Threads

Semaphore controls access.

---

## Operating Systems

Control access to:

* Printers
* Scanners
* Files
* Network Resources

---

# Semaphore vs Mutex

| Feature          | Semaphore           | Mutex            |
| ---------------- | ------------------- | ---------------- |
| Resource Counter | Yes                 | No               |
| Ownership        | No                  | Yes              |
| Multiple Threads | Yes                 | No               |
| Values           | 0 to N              | Locked/Unlocked  |
| Main Purpose     | Resource Management | Mutual Exclusion |

---

# Advantages

* Controls limited resources
* Supports concurrency
* Prevents resource exhaustion
* Improves resource utilization

---

# Disadvantages

* Difficult to debug
* Incorrect use may cause deadlocks
* Incorrect use may cause starvation

---

# Common Interview Questions

## What is a Semaphore?

A synchronization mechanism used to control access to limited resources.

---

## What is a Counting Semaphore?

Semaphore whose value ranges from 0 to N.

---

## What is a Binary Semaphore?

Semaphore whose value is either 0 or 1.

---

## Difference Between Mutex and Semaphore?

Mutex provides ownership-based locking.

Semaphore manages resource counts.

---

## What happens when acquire() is called?

Semaphore count decreases.

If resource unavailable:

Thread waits.

---

## What happens when release() is called?

Semaphore count increases.

Waiting thread may wake up.

---

## Why use Semaphore instead of synchronized?

Semaphore allows multiple threads simultaneously.

synchronized allows only one thread.

---

## Can Semaphore cause Deadlocks?

Yes.

Incorrect resource ordering can still create deadlocks.

---

# Common Mistakes

Wrong:

Semaphore and Mutex are identical.

Correct:

Semaphore manages resources.

Mutex protects critical sections.

---

Wrong:

Semaphore always allows one thread.

Correct:

Semaphore may allow N threads.

---

Wrong:

Calling run() creates a thread.

Correct:

start() creates a thread.

---

# Key Takeaways

* Semaphore is a resource counter.
* acquire() requests a resource.
* release() returns a resource.
* Counting Semaphore ranges from 0 to N.
* Binary Semaphore ranges from 0 to 1.
* Used heavily in databases, web servers, operating systems, and banking systems.
* Multiple threads can access resources simultaneously depending on semaphore value.
* Semaphore is primarily used for resource management rather than ownership-based locking.
