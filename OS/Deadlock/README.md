# Deadlocks

## Objective

Understand what a deadlock is, why it occurs, how Operating Systems handle it, and how to prevent it.

---

# What is a Deadlock?

A Deadlock is a situation where two or more processes are waiting indefinitely for resources held by each other.

As a result:

- No process can proceed.
- No resource gets released.
- System progress stops.

---

# Simple Definition

A deadlock occurs when:

"Every process is waiting for another process to release a resource."

---

# Real Life Analogy

Imagine two people.

Person A has:

- Pen

Needs:

- Notebook

Person B has:

- Notebook

Needs:

- Pen

Situation:

Person A waits for Notebook.

Person B waits for Pen.

Neither releases what they already hold.

Result:

Deadlock.

---

# Banking Example

Consider two bank accounts.

Account A
Account B

Thread 1:

Locks Account A.

Needs Account B.

Thread 2:

Locks Account B.

Needs Account A.

Both threads wait forever.

Deadlock occurs.

---

# Visualization

Thread 1

Lock A
↓
Wait for B

Thread 2

Lock B
↓
Wait for A

Result:

A → B → A

Circular waiting.

---

# Resource Allocation Graph

P1 → R2

P2 → R1

R1 → P1

R2 → P2

Cycle exists.

Deadlock possible.

---

# Why Deadlocks Occur?

Deadlocks occur when processes compete for limited resources.

Examples:

- Printer
- Scanner
- Database Connection
- File Lock
- Memory Block
- Network Resource

---

# Four Coffman Conditions

For a deadlock to occur all four conditions must be true simultaneously.

---

## 1. Mutual Exclusion

A resource can be used by only one process at a time.

Example:

One printer.

Only one process can print.

---

## 2. Hold and Wait

A process holds one resource and waits for another.

Example:

Process holds Printer.

Waiting for Scanner.

---

## 3. No Preemption

Resources cannot be forcibly taken away.

Only the owning process can release them.

---

## 4. Circular Wait

Processes form a circular chain.

Example:

P1 waits for P2.

P2 waits for P3.

P3 waits for P1.

---

# Deadlock Diagram

P1
↓ waits
P2
↓ waits
P3
↓ waits
P1

Cycle detected.

---

# Process States During Deadlock

Ready
↓
Running
↓
Waiting
↓
Waiting Forever

---

# Output of Program

Possible Output:

Thread-0 locked Account-A

Thread-1 locked Account-B

Thread-0 waiting for Account-B

Thread-1 waiting for Account-A

Program never finishes.

This confirms deadlock.

---

# How to Prevent Deadlocks?

Break at least one Coffman condition.

---

## Method 1: Remove Mutual Exclusion

Allow resource sharing.

Example:

Read-only files.

Multiple users can access.

---

## Method 2: Remove Hold and Wait

Process requests all resources at once.

Example:

Need Printer + Scanner.

Request both together.

---

## Method 3: Remove No Preemption

OS can force resource release.

Example:

Database transaction rollback.

---

## Method 4: Remove Circular Wait

Always acquire resources in a fixed order.

Example:

Always lock A before B.

Never B before A.

Most common solution.

---

# Deadlock Avoidance

System checks before allocating resources.

If allocation can lead to deadlock:

Request denied.

---

# Banker's Algorithm

Purpose:

Avoid deadlocks.

Idea:

Only allocate resources if system remains in a safe state.

---

# Safe State

A system is safe if all processes can complete eventually.

Example:

Resources available for future requests.

No process waits forever.

---

# Unsafe State

Not necessarily deadlocked.

But may lead to deadlock later.

---

# Deadlock Detection

Instead of preventing deadlocks:

Allow them.

Detect later.

Recover later.

Common in databases.

---

# Deadlock Recovery Methods

## 1. Terminate Process

Kill one process.

Deadlock breaks.

---

## 2. Terminate Multiple Processes

Kill processes until cycle disappears.

---

## 3. Resource Preemption

Take resource from one process.

Give to another.

---

# Real World Examples

---

## Database Systems

Examples:

- :contentReference[oaicite:1]{index=1}
- :contentReference[oaicite:2]{index=2}

Transactions may lock rows.

Deadlocks can occur.

---

## Operating Systems

Examples:

- :contentReference[oaicite:3]{index=3}
- :contentReference[oaicite:4]{index=4}

File locks and device locks.

---

## Banking Systems

Money transfer operations.

Concurrent account updates.

---

## E-Commerce Systems

Inventory updates.

Order processing.

Payment processing.

---

# Advantages of Deadlock Handling

- Better resource utilization
- Safe execution
- Improved reliability

---

# Disadvantages

- Detection overhead
- Prevention overhead
- Reduced concurrency

---

# Common Interview Questions

## What is Deadlock?

A condition where processes wait indefinitely for resources held by each other.

---

## What are Coffman Conditions?

1. Mutual Exclusion
2. Hold and Wait
3. No Preemption
4. Circular Wait

---

## Can Deadlock Occur Without Circular Wait?

No.

Circular wait is mandatory.

---

## How Can Deadlock Be Prevented?

Break any Coffman condition.

---

## What is Deadlock Avoidance?

Allocating resources only if system remains in a safe state.

---

## What is Banker's Algorithm?

A deadlock avoidance algorithm that checks safe states before allocation.

---

## Difference Between Deadlock Prevention and Avoidance?

Prevention:

Break conditions beforehand.

Avoidance:

Check safe state before allocation.

---

## Difference Between Deadlock and Starvation?

Deadlock:

Processes wait for each other forever.

Starvation:

A process never gets resources because others keep getting priority.

---

# Common Mistakes

Wrong:

Deadlock means system crash.

Correct:

System may still run but affected processes are stuck.

---

Wrong:

Every waiting process is deadlocked.

Correct:

Waiting is normal.

Deadlock means indefinite waiting due to circular dependency.

---

# Key Takeaways

- Deadlock means permanent waiting.
- Four Coffman conditions must hold.
- Circular wait is the main indicator.
- Deadlocks occur in databases, OS, banking systems, and multithreaded applications.
- Prevention, avoidance, detection, and recovery are the four major strategies.
- Fixed lock ordering is the most common practical solution.


Here is the breakdown of Deadlock vs. Livelock, along with the architecture and use cases of Spinlocks.

---

## 1. Deadlock vs. Livelock

Both deadlock and livelock are forms of **liveness failures** where a set of concurrent processes or threads cannot make forward progress. However, their active states are fundamentally different.

### Deadlock

* **What it is:** A state where a set of threads are permanently blocked because each thread holds a resource that another thread needs, and is waiting for a resource held by another thread in the cycle.
* **Thread Activity:** The threads are in a **Blocked/Waiting** state. They consume **0% CPU** because the OS scheduler has suspended them until an event occurs that never will.
* **Analogy:** Two people meet face-to-face in a narrow hallway. Neither person moves out of the way; they both stand completely still, waiting indefinitely for the other to step aside.

### Livelock

* **What it is:** A state where threads continuously change their internal states in response to changes in other threads, but without making any actual forward progress.
* **Thread Activity:** The threads are in an **Active/Running** state. They consume **high CPU** because they are actively executing instructions, running algorithm logic to bypass a conflict that keeps repeating.
* **Analogy:** Two people meet face-to-face in a narrow hallway. Both politely try to step aside at the same time. They both move to the left simultaneously, then both move to the right simultaneously, repeatedly blocking each other despite actively trying to resolve the situation.

### Summary Comparison

| Feature | Deadlock | Livelock |
| --- | --- | --- |
| **Thread State** | Blocked / Waiting | Running / Active |
| **CPU Utilization** | None (Idle) | High (Active spinning/looping) |
| **Recovery** | Requires external OS or user intervention (e.g., killing a process) | Can sometimes resolve naturally if random delays (like exponential backoff) are introduced |

---

## 2. What is a Spinlock?

A **Spinlock** is a low-level synchronization primitive where a thread attempts to acquire a lock by executing a tight loop that repeatedly checks if the lock variable becomes available.

Instead of relinquishing the CPU core and entering a blocked state, the thread **actively waits** ("spins") while consuming CPU cycles until it successfully claims the lock.

### Implementation Mechanics

Spinlocks rely on hardware-supported atomic CPU instructions, such as **Test-and-Set** or **Compare-and-Swap (CAS)**. These primitives guarantee that reading the lock status and modifying it to "locked" happens as a single, uninterrupted operation.

```c
// Conceptual pseudo-code for a basic Test-and-Set spinlock
void lock(spinlock_t *lock) {
    while (test_and_set(lock) == 1) {
        // Do nothing. Spin and waste CPU cycles until the lock is released (0)
    }
}

void unlock(spinlock_t *lock) {
    *lock = 0; // Release the lock
}

```

---

## 3. When Should We Use a Spinlock?

Spinlocks sound highly inefficient because they burn CPU cycles while waiting, but they are incredibly useful under the right architectural constraints:

### 1. The Lock is Held for Short Durations

If the critical section executes quickly (e.g., updating a reference count, changing a pointer, or modifying a primitive variable), the time spent spinning is shorter than the time it would take to execute a full OS context switch.

* Putting a thread to sleep and waking it up requires updating the PCB, changing scheduler queues, and shifting CPU privilege modes.
* If the lock is released in a few nanoseconds, spinning is significantly cheaper.

### 2. In Multiprocessor Systems

Spinlocks are only valid on multi-core systems. On a single-core system, a spinning thread occupies the *only* available CPU core, preventing the thread holding the lock from ever running and releasing it.

### 3. In Operating System Kernels & Device Drivers

Kernels use spinlocks extensively. For example, during interrupt handling, a thread cannot be put to sleep or context-switched out safely. In these environments, code must wait for a hardware structure to clear up via a spinlock.

### Summary: Spinlock vs. Mutex Usage

| Choose a **Spinlock** when: | Choose a standard **Mutex** when: |
| --- | --- |
| The critical section is very brief (a few instructions). | The critical section involves heavy operations (I/O, memory allocation). |
| Context switch overhead is higher than the wait time. | The thread might have to wait for a long or unpredictable duration. |
| You are writing kernel-level code or device drivers. | You are writing standard user-space applications. |
