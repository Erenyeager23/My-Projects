
---

## 1. The Critical Section Problem

### What It Is

A **Critical Section** is a block of code that accesses shared resources (such as global variables, linked lists, or file streams) that must not be concurrently accessed by more than one thread or process.

The **Critical Section Problem** occurs when multiple concurrent threads execute this code segment simultaneously, leading to non-deterministic execution and data corruption known as a **Race Condition**.

### The 3 Rules to Resolve It

To solve the Critical Section problem, any proposed software or hardware solution must strictly satisfy these three conditions:

1. **Mutual Exclusion:** If process $P_i$ is executing in its critical section, no other processes can be executing in their critical sections for that same shared resource.
2. **Progress:** If no process is executing in its critical section and some processes want to enter, only those processes not executing in their *remainder sections* can participate in deciding which process enters next. This selection cannot be postponed indefinitely.
3. **Bounded Waiting:** There must be a limit on the number of times other processes can enter their critical sections after a process has made a request to enter and before that request is granted. This prevents **starvation**.

---

## 2. Mutex vs. Semaphore

Both are synchronization primitives used to enforce mutual exclusion, but they have fundamentally different designs and use cases.

### Mutex (Mutual Exclusion Lock)

* **Definition:** A locking mechanism used to synchronize access to a resource. It is a locking variable that can only hold a value of $0$ (locked) or $1$ (unlocked).
* **Ownership:** A mutex has strict **ownership**. The specific thread that successfully acquires (locks) the mutex **must** be the one to release (unlock) it.

> ❌ **Can a mutex be released by another thread?** > **No.** In standard operating systems (like POSIX threads), if Thread A locks a mutex, Thread B cannot unlock it. Attempting to do so will result in undefined behavior or a runtime error.

### Semaphore

A semaphore is a signaling mechanism that uses an integer variable to manage concurrent access via two atomic operations: `wait()` (down/decrement) and `signal()` (up/increment).

* **Binary Semaphore:** * Its integer value can only be $0$ or $1$.
* It behaves similarly to a mutex but **lacks ownership**. Any thread can signal a binary semaphore to wake up a thread trapped in a `wait()` state.


* **Counting Semaphore:**
* Its integer value can range across an unrestricted domain (e.g., $0$ to $N$).
* It is used to control access to a finite pool of identical resources. The initial value represents the total number of available resource slots.



---

## 3. The Producer-Consumer (Bounded Buffer) Problem

### The Problem

A finite data buffer of size $N$ is shared between two types of processes:

* **Producers:** Generate data and write it into the buffer slots.
* **Consumers:** Read data out of the buffer slots and process it.

**The Challenges:** The producer must not attempt to add data to the buffer if it is completely full. The consumer must not attempt to read data from the buffer if it is completely empty. Simultaneous access to buffer pointers must be mutually exclusive.

### The Semaphore Solution

This classic synchronization problem is solved using three semaphores:

1. `mutex` (Binary Semaphore, initialized to `1`): Protects the buffer pointers from simultaneous modifications.
2. `empty` (Counting Semaphore, initialized to `N`): Tracks the number of vacant slots remaining.
3. `full` (Counting Semaphore, initialized to `0`): Tracks the number of filled slots available.

#### Producer Pseudo-code

```c
void producer() {
    while(1) {
        // Produce an item
        
        wait(empty); // Decrement empty slots; block if buffer is full (empty == 0)
        wait(mutex); // Enter critical section
        
        // Add item to the buffer
        
        signal(mutex); // Leave critical section
        signal(full);  // Increment filled slots; wake up waiting consumers
    }
}

```

#### Consumer Pseudo-code

```c
void consumer() {
    while(1) {
        wait(full);  // Decrement filled slots; block if buffer is empty (full == 0)
        wait(mutex); // Enter critical section
        
        // Remove item from the buffer
        
        signal(mutex); // Leave critical section
        signal(empty); // Increment empty slots; wake up waiting producers
        
        // Consume the item
    }
}

```

---

## 4. Priority Inversion

### What It Is

**Priority Inversion** is an undesirable scenario in real-time operating systems where a high-priority process is indirectly prevented from executing by a low-priority process.

### How It Happens (The 3-Task Scenario)

Imagine three tasks with different priorities: **High ($H$)**, **Medium ($M$)**, and **Low ($L$)**.

1. Task $L$ executes and acquires a shared resource lock (like a mutex).
2. Task $H$ becomes ready to run and preempts Task $L$.
3. Task $H$ attempts to access the same shared resource, but it is locked by $L$. Task $H$ enters a blocked/waiting state.
4. Task $L$ resumes execution to finish its critical section and release the lock.
5. **The Twist:** Task $M$ becomes ready to run. Because Priority $M > L$, Task $M$ preempts Task $L$.
6. As long as Task $M$ runs, Task $L$ cannot complete its work to release the lock. Consequently, **Task $H$ is starved by Task $M$**, violating the priority scheduling rules.

### How to Resolve It

* **Priority Inheritance Protocol (PIP):** When a high-priority task $H$ blocks on a resource held by low-priority task $L$, the kernel **temporarily bumps the priority of task $L$ up to match task $H$**. This ensures task $L$ cannot be preempted by middle-tier tasks ($M$). Once $L$ finishes its critical section and releases the lock, its priority drops back to normal, allowing $H$ to claim the lock and execute.
* **Priority Ceiling Protocol (PCP):** Every resource is assigned a priority ceiling equal to the highest priority of any task that might lock it. When a task locks the resource, the kernel immediately boosts that task's priority to the resource's ceiling value, preventing intermediate preemption entirely.
