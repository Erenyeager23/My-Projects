# Processes vs Threads

## Objective

Understand the difference between Processes and Threads using a real-world Banking Application.

---

# What is a Process?

A Process is a program in execution.

Examples:

- Chrome
- Spotify
- VS Code
- WhatsApp

Each process has its own:

- Memory
- Heap
- Stack
- Resources

If one process crashes, other processes continue running.

Example:

Chrome crashes → Spotify still works.

---

# What is a Thread?

A Thread is the smallest unit of execution inside a process.

Threads share:

- Code
- Data
- Heap

Each thread has its own:

- Stack
- Registers
- Program Counter

Example:

Banking Application

    Banking Process
         |
         +-- Balance Thread
         +-- Transfer Thread
         +-- Transaction History Thread

---

# Real World Banking Example

A user performs:

1. Balance Inquiry
2. Money Transfer
3. View Transaction History

All operations belong to the same Banking Application.

Instead of executing sequentially, separate threads execute them concurrently.

---

# Why Use Threads?

Without Threads:

Balance Check
↓
Transfer Money
↓
Transaction History

Total Time = Sum of all tasks

With Threads:

Balance Check
Transfer Money
Transaction History

Run concurrently.

Faster response.

---

# Thread Mapping

| Thread | Responsibility |
|----------|---------------|
| BalanceThread | Display account balance |
| TransferThread | Transfer money |
| HistoryThread | Display recent transactions |

---

# Project Structure

01-Processes-vs-Threads/
│
├── BankingApp.java
├── README.md

---

# Compile

```bash
javac BankingApp.java
```