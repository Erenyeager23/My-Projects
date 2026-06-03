# Context Switching

## Objective

Understand how an Operating System allows multiple processes and threads to share the CPU through Context Switching.

---

# What is Context Switching?

Context Switching is the process of saving the state of the currently running process or thread and restoring the state of another process or thread so that execution can continue.

In simple words:

The CPU stops one task temporarily and starts another task.

---

# Why is Context Switching Needed?

A CPU core can execute only one instruction stream at a time.

However, modern systems run many applications simultaneously:

* Chrome
* Spotify
* VS Code
* WhatsApp
* File Explorer

Since one CPU cannot execute all processes at the same instant, the Operating System rapidly switches between them.

This creates the illusion that all programs are running simultaneously.

---

# Real Life Analogy

Imagine a teacher helping three students.

Student A asks a Math question.

Student B asks a Physics question.

Student C asks a Chemistry question.

Teacher spends:

* 2 minutes with Student A
* 2 minutes with Student B
* 2 minutes with Student C

Then repeats.

The teacher keeps switching attention.

This is similar to Context Switching.

---

# What Happens During Context Switching?

Suppose Chrome is running.

The Operating System interrupts Chrome and saves its current execution state.

The Operating System then loads Spotify's state and allows Spotify to execute.

Later, Chrome resumes from exactly where it stopped.

---

# What Information Must Be Saved?

The Operating System stores the execution state inside a Process Control Block (PCB).

The PCB typically contains:

* Process ID
* Program Counter
* CPU Registers
* Stack Pointer
* Process State
* Scheduling Information
* Memory Information

Without this information the process cannot continue correctly.

---

# Process States

A process generally moves through the following states:

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

Context Switching usually occurs when moving between Ready and Running states.

---

# Context Switching Example

Assume:

Chrome needs 5 seconds.

Spotify needs 4 seconds.

VS Code needs 3 seconds.

Time Slice = 1 second.

Execution:

Chrome
↓
Spotify
↓
VS Code
↓
Chrome
↓
Spotify
↓
VS Code

The CPU continuously switches between them.

---

# What is a Time Slice?

A Time Slice (Quantum) is the maximum amount of CPU time allocated to a process before the Operating System switches to another process.

Example:

Time Slice = 100 ms

Chrome runs for 100 ms.

CPU switches.

Spotify runs for 100 ms.

CPU switches.

VS Code runs for 100 ms.

CPU switches.

---

# Cost of Context Switching

Context Switching is not free.

The CPU must:

1. Save current process registers.
2. Save program counter.
3. Save stack pointer.
4. Load another process registers.
5. Load another process stack pointer.
6. Restore execution state.

During this time no useful application work is performed.

This overhead reduces performance.

---

# Advantages

* Multitasking
* Better responsiveness
* Fair CPU sharing
* Multiple users can share the system

---

# Disadvantages

* CPU overhead
* Cache invalidation
* Reduced throughput if switching occurs too frequently

---

# Process Switch vs Thread Switch

Process Switch

* Different address spaces
* More expensive
* Higher overhead

Thread Switch

* Shared address space
* Faster
* Lower overhead

---

# Where is Context Switching Used?

Operating Systems:

* Windows
* Linux
* Android
* macOS

Applications:

* Banking Systems
* Web Servers
* Databases
* Tomcat
* Spring Boot Applications

---

# Example from Banking Application

A banking server may receive:

User A → Balance Inquiry

User B → Fund Transfer

User C → Loan Request

The CPU continuously switches among these requests.

Without Context Switching only one customer could be served at a time.

---

# Interview Questions

Q1. What is Context Switching?

Saving the state of one process and loading another process state.

Q2. Why is Context Switching required?

To support multitasking.

Q3. Is Context Switching free?

No. It introduces CPU overhead.

Q4. Why is Thread Context Switching faster?

Threads share the same address space.

Q5. What data is stored in a PCB?

Program Counter, Registers, Stack Pointer, Scheduling Information, Memory Information.

---

# Key Takeaways

* CPU executes one process at a time per core.
* Context Switching creates multitasking.
* PCB stores process state.
* Excessive switching reduces performance.
* Thread switches are faster than process switches.
