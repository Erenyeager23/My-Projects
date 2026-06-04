# Operating Systems - Processes & Threads (Concurrency)

## 1. Difference Between Process and Thread

| Process                                        | Thread                                           |
| ---------------------------------------------- | ------------------------------------------------ |
| Independent program in execution               | Smallest unit of execution inside a process      |
| Has its own memory space                       | Shares memory with other threads of same process |
| Creation is expensive                          | Creation is faster                               |
| Context switch is slower                       | Context switch is faster                         |
| Communication requires IPC                     | Communication through shared memory              |
| Failure usually doesn't affect other processes | Failure of one thread may affect whole process   |

### Example

* Browser = Process
* Chrome tabs inside browser = Threads

---

## 2. Context Switching

### What is Context Switching?

When CPU switches from one process/thread to another, it saves the current execution state and loads another.

Saved information includes:

* Program Counter (PC)
* CPU Registers
* Stack Pointer
* Process State

---

### Process Context Switch

OS saves:

```text
Process A
    ↓ Save PCB
CPU
    ↓ Load PCB
Process B
```

Requires:

* Switching memory address space
* Updating page tables
* More overhead

---

### Thread Context Switch

```text
Thread 1
    ↓ Save Registers
CPU
    ↓ Load Registers
Thread 2
```

Since threads share same memory:

* No page table change
* Less overhead
* Faster than process switching

---

## 3. Process States

### New

Process is being created.

### Ready

Process is waiting for CPU.

### Running

Process is currently executing.

### Waiting / Blocked

Process waits for:

* I/O
* User input
* Resource availability

### Terminated

Process has finished execution.

---

### State Transition Diagram

```text
           +------+
           | New  |
           +------+
               |
               v
           +------+
           |Ready |
           +------+
               |
      CPU Assigned
               |
               v
          +---------+
          | Running |
          +---------+
           /      \
          /        \
         v          v
+---------+    +---------+
| Waiting |    |Terminate|
+---------+    +---------+
     |
I/O Complete
     |
     v
  Ready
```

---

### What Causes Transitions?

| Transition           | Cause                         |
| -------------------- | ----------------------------- |
| New → Ready          | Process admitted              |
| Ready → Running      | CPU scheduler selects process |
| Running → Waiting    | I/O request                   |
| Waiting → Ready      | I/O completed                 |
| Running → Ready      | Time slice expires            |
| Running → Terminated | Execution completed           |

---

# 4. User-Level Threads vs Kernel-Level Threads

## User-Level Threads (ULT)

Managed by thread library.

Examples:

* POSIX Threads Library
* Green Threads

### Advantages

✅ Fast creation

✅ Fast switching

✅ No kernel involvement

### Disadvantages

❌ If one thread blocks, entire process blocks

❌ Cannot utilize multiple CPUs effectively

---

## Kernel-Level Threads (KLT)

Managed directly by OS kernel.

Examples:

* Linux Threads
* Windows Threads

### Advantages

✅ True parallelism

✅ One blocked thread doesn't block others

✅ Better scheduling

### Disadvantages

❌ Slower creation

❌ More context switching overhead

---

## Comparison

| Feature              | User Thread           | Kernel Thread      |
| -------------------- | --------------------- | ------------------ |
| Managed By           | User Library          | OS Kernel          |
| Speed                | Faster                | Slower             |
| Parallel Execution   | No                    | Yes                |
| Blocking Call Effect | Entire process blocks | Only thread blocks |

---

# 5. Zombie Process vs Orphan Process

## Zombie Process

A process that:

* Finished execution
* Still has an entry in Process Table
* Parent hasn't collected exit status

### Example

```c
child exits
parent doesn't call wait()
```

Zombie remains.

---

### Finding Zombies

Linux:

```bash
ps aux | grep Z
```

or

```bash
top
```

State shown:

```text
Z
```

---

### Cleanup

Parent calls:

```c
wait()
```

or

```c
waitpid()
```

OS removes process entry.

---

## Orphan Process

Child continues running after parent exits.

Example:

```text
Parent dies
Child still running
```

---

### What Happens?

OS assigns orphan to:

```text
init (PID 1)
```

or

```text
systemd
```

which later cleans it.

---

### Finding Orphans

```bash
ps -elf
```

Check:

```text
PPID = 1
```

---

## Comparison

| Zombie                 | Orphan                  |
| ---------------------- | ----------------------- |
| Child terminated       | Child running           |
| Parent alive           | Parent dead             |
| Occupies Process Table | Continues execution     |
| Removed by wait()      | Adopted by init/systemd |

---

# 6. Process Control Block (PCB)

## Definition

PCB is a data structure maintained by OS containing information about a process.

Each process has one PCB.

---

### PCB Contains

```text
PID
Process State
Program Counter
CPU Registers
Scheduling Information
Memory Information
Open Files
I/O Status
```

---

### PCB Diagram

```text
+-------------------+
| Process ID (PID)  |
+-------------------+
| Process State     |
+-------------------+
| CPU Registers     |
+-------------------+
| Program Counter   |
+-------------------+
| Scheduling Info   |
+-------------------+
| Memory Info       |
+-------------------+
| I/O Information   |
+-------------------+
```

---

### Why PCB Important?

During context switch:

```text
Save current PCB
Load next PCB
```

Without PCB, multitasking is impossible.

---

# 7. System Call

## Definition

A system call is a mechanism through which a user program requests services from the OS kernel.

---

### Examples

```c
read()
write()
fork()
exec()
wait()
open()
close()
```

---

# fork()

Creates a new child process.

```c
pid_t pid = fork();
```

After fork:

```text
Parent Process
      |
      +---- Child Process
```

Both execute from next instruction.

---

### Return Values

| Returned Value | Meaning        |
| -------------- | -------------- |
| 0              | Child Process  |
| >0             | Parent Process |
| -1             | Error          |

---

# exec()

Replaces current process image with another program.

```c
execl("/bin/ls","ls",NULL);
```

Current program disappears and new program starts.

---

# wait()

Parent waits for child to finish.

```c
wait(NULL);
```

Used to prevent zombies.

---

### fork + exec + wait Flow

```text
Parent
   |
 fork()
   |
   +------ Child
             |
          exec()
             |
         New Program
             |
          Finish
             |
          wait()
             |
          Parent
```

---

# 8. SIGTERM vs SIGKILL vs SIGSTOP

Signals are used to communicate with processes.

---

## SIGTERM (15)

Graceful termination request.

```bash
kill PID
```

or

```bash
kill -15 PID
```

### Can be caught?

✅ Yes

### Can be ignored?

✅ Yes

---

## SIGKILL (9)

Forcefully kills process.

```bash
kill -9 PID
```

### Can be caught?

❌ No

### Can be ignored?

❌ No

Kernel immediately removes process.

---

## SIGSTOP (19)

Pauses process execution.

```bash
kill -19 PID
```

### Can be caught?

❌ No

### Can be ignored?

❌ No

---

## Comparison

| Signal  | Purpose       | Catchable | Ignorable |
| ------- | ------------- | --------- | --------- |
| SIGTERM | Graceful Stop | Yes       | Yes       |
| SIGKILL | Force Kill    | No        | No        |
| SIGSTOP | Pause Process | No        | No        |

---

# 9. Why Threads are Called Lightweight Processes

Threads are called lightweight because:

* Share process memory
* Share code segment
* Share data segment
* Share open files

Only have:

* Own Stack
* Own Registers
* Own Program Counter

Hence:

```text
Process Creation Cost > Thread Creation Cost
```

and

```text
Process Switch Cost > Thread Switch Cost
```

---

# 10. Advantages and Disadvantages of Multithreading

## Advantages

### 1. Better Responsiveness

GUI remains responsive while background work runs.

Example:

```text
Download File
while
User Uses Application
```

---

### 2. Resource Sharing

Threads share memory.

No expensive IPC required.

---

### 3. Faster Execution

Tasks can run concurrently.

Example:

```text
Thread 1 → Read Data
Thread 2 → Process Data
Thread 3 → Save Data
```

---

### 4. Better CPU Utilization

Multiple cores can execute threads simultaneously.

---

### 5. Lower Memory Usage

Threads require less memory than processes.

---

## Disadvantages

### 1. Race Conditions

Multiple threads modify same data.

```java
count++;
```

May produce incorrect result.

---

### 2. Deadlocks

Threads wait forever for resources.

```text
Thread A waits for B
Thread B waits for A
```

---

### 3. Synchronization Complexity

Need:

* Mutex
* Semaphore
* Monitor
* Locks

---

### 4. Debugging Difficulty

Concurrent bugs are difficult to reproduce.

---

### 5. Security and Stability Risks

One faulty thread can crash entire process.

---

# Interview Quick Summary

### Process

Independent program with separate memory.

### Thread

Execution unit inside process sharing memory.

### PCB

OS structure storing process information.

### fork()

Creates child process.

### exec()

Replaces process image.

### wait()

Parent waits for child and removes zombies.

### Zombie

Terminated child waiting for parent to collect status.

### Orphan

Running child whose parent has terminated.

### SIGTERM

Graceful termination.

### SIGKILL

Force kill.

### SIGSTOP

Pause process.

### Multithreading Benefits

* Faster execution
* Better responsiveness
* Resource sharing

### Multithreading Problems

* Race conditions
* Deadlocks
* Synchronization complexity


