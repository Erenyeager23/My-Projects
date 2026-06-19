# Networking, OS, DSA & Cloud — Practice Questions

A self-test question bank covering Networking, Linux/Unix, Operating Systems, Data Structures & Algorithms, SQL, Shell Scripting, and ML/LLM concepts.

**How to use:** Read the question, note your answer, then click **"Show Answer"** to reveal the solution and explanation.

---

## Table of Contents
- [Networking & Security](#networking--security)
- [Operating Systems & Linux](#operating-systems--linux)
- [Shell Scripting](#shell-scripting)
- [Data Structures & Algorithms (MCQ)](#data-structures--algorithms-mcq)
- [SQL & Databases](#sql--databases)
- [Machine Learning / LLMs](#machine-learning--llms)
- [Coding Problems](#coding-problems)

---

## Networking & Security

### Q1. Which protocol prevents Layer 2 broadcast storms by blocking redundant switch ports?

- A. LACP (Link Aggregation Control Protocol)
- B. HSRP (Hot Standby Router Protocol)
- C. STP (Spanning Tree Protocol)
- D. OSPF (Open Shortest Path First)

<details>
<summary>Show Answer</summary>

**Answer: C. STP (Spanning Tree Protocol)**

STP is specifically designed to prevent Layer 2 routing loops, which are the root cause of broadcast storms. When a network has redundant switch links, broadcast frames can circulate endlessly. STP solves this by strategically blocking redundant ports to create a single, loop-free logical path.

- **LACP** bundles physical links for bandwidth/redundancy — doesn't prevent network-wide loops.
- **HSRP** provides Layer 3 default-gateway redundancy — not a switching-loop solution.
- **OSPF** is a Layer 3 routing protocol — doesn't address Layer 2 loops.
</details>

---

### Q2. A client sends SYN packets to a server but receives no SYN-ACK, while other ports on the same server are reachable and DNS resolves correctly. What is the most likely cause?

- A. The SDN controller has not yet pushed flow rules to the virtual switch
- B. The application DNS TTL is too low
- C. The MTU on the cloud virtual NIC is mismatched
- D. A cloud security group or firewall rule is blocking inbound traffic on the application destination port

<details>
<summary>Show Answer</summary>

**Answer: D. A cloud security group or firewall rule is blocking inbound traffic on the application destination port**

Cloud security groups act as virtual firewalls at the instance level and silently drop inbound traffic unless explicitly allowed. Since routing works and other ports are reachable, the server is up and the network path is healthy — the issue is isolated to one port.

- **SDN controller issue** would typically affect all traffic, not just one port.
- **Low DNS TTL** only affects lookup frequency, not the TCP handshake once the IP is known.
- **MTU mismatch** affects large packets; a SYN packet (~60 bytes) is essentially never fragmented.
</details>

---

### Q3. An attacker sends falsified replies that map their own MAC address to a legitimate IP address (often the default gateway) on a local network. What is this attack and at which layer does it occur?

- A. IP spoofing at Layer 3
- B. ARP poisoning at Layer 2
- C. MAC flooding at Layer 2
- D. DNS hijacking at Layer 7

<details>
<summary>Show Answer</summary>

**Answer: B. ARP poisoning at Layer 2**

ARP (Address Resolution Protocol) maps IP addresses to MAC addresses on a local network. In ARP poisoning, an attacker sends falsified ARP replies linking their MAC to a legitimate IP. ARP operates at the Data Link Layer (Layer 2).

- **IP spoofing** falsifies source IP headers at Layer 3 — not the mechanism here.
- **MAC flooding** overwhelms a switch's CAM table; it doesn't use targeted fake ARP replies.
- **DNS hijacking** operates at Layer 7 and resolves domain names, unrelated to IP-to-MAC mapping.
</details>

---

### Q4. A DHCP pool with an 8-hour lease time is being exhausted rapidly even though the network has far fewer physical devices than available addresses. What's the most likely cause?

- A. DNS dynamic updates are consuming DHCP pool entries
- B. The DHCP server is issuing duplicate leases due to database corruption
- C. STP topology changes are causing hosts to disconnect and reconnect
- D. A rogue DHCP client is requesting leases using randomized MAC addresses, exhausting the pool rapidly

<details>
<summary>Show Answer</summary>

**Answer: D. A rogue DHCP client is requesting leases using randomized MAC addresses, exhausting the pool rapidly**

This is a classic **DHCP Starvation attack**. A rogue device sends repeated DHCP Discover messages, spoofing a new random MAC address each time, tricking the server into thinking many distinct devices are joining. Each fake MAC gets a lease, rapidly draining the pool.

- **DNS dynamic updates** register hostnames to IPs; they don't consume DHCP leases.
- **Duplicate leases** would cause IP conflicts, not pool exhaustion.
- **STP topology changes** cause hosts to reconnect, but they'd typically re-request their *same* prior lease, not consume new pool entries.
</details>

---

### Q5. What does the Internet Protocol (IP) provide in terms of delivery guarantees?

- A. Reliable delivery
- B. Connection-oriented delivery
- C. Best-effort delivery
- D. None of the above

<details>
<summary>Show Answer</summary>

**Answer: C. Best-effort delivery**

IP does not guarantee delivery, ordering, or integrity — it does its best to deliver packets but offers no assurances. It assumes underlying layers are unreliable and provides no built-in error checking or retransmission (that's left to transport-layer protocols like TCP).
</details>

---

### Q6. In the CAN (Controller Area Network) protocol, what mechanism ensures data consistency across all nodes when one node detects a transmission error?

- A. Single-bit errors are converted to multi-bit errors
- B. Multi-bit errors are converted to single-bit errors
- C. Local errors are globalized
- D. Global errors are localized

<details>
<summary>Show Answer</summary>

**Answer: C. Local errors are globalized**

When a node detects a local error (e.g., CRC or bit error), it transmits an Error Active Flag (6 consecutive dominant bits). This violates CAN's bit-stuffing rules, causing every other node to detect a "stuff error" too — globalizing the local error so all nodes reject the corrupted message and the sender retransmits.
</details>

---

### Q7. Which OSI layer is responsible for transmitting raw, unstructured bit streams over a physical medium?

- A. Data Link layer
- B. Network layer
- C. Physical layer
- D. Transport layer

<details>
<summary>Show Answer</summary>

**Answer: C. Physical layer**

The Physical layer deals with mechanical, electrical, and functional specs (voltages, cables, fiber) to move raw bits — no framing, addressing, or logic involved. Data Link handles framing/MAC, Network handles routing/IP addressing, and Transport handles end-to-end segments.
</details>

---

### Q8. By default, which metrics does EIGRP use to calculate its composite routing metric?

- A. Load and Reliability
- B. Bandwidth and Delay
- C. Bandwidth, Load, and Delay
- D. All of Bandwidth, Load, Delay, and Reliability

<details>
<summary>Show Answer</summary>

**Answer: B. Bandwidth and Delay**

By default, EIGRP's K-values are K1=1 (Bandwidth), K2=0 (Load), K3=1 (Delay), K4=0 and K5=0 (Reliability). Only Bandwidth and Delay are enabled by default — Load and Reliability fluctuate too often and would cause route instability if enabled.
</details>

---

### Q9. What device is required to allow communication between two different VLANs?

- A. A hub with sufficient ports
- B. A repeater to amplify signals
- C. A bridge to connect network segments
- D. A Layer 3 switch or router configured for inter-VLAN routing

<details>
<summary>Show Answer</summary>

**Answer: D. A Layer 3 switch or router configured for inter-VLAN routing**

VLANs are isolated broadcast domains at Layer 2. Routing between them (inter-VLAN routing) requires a Layer 3 device — either a router (router-on-a-stick) or a Layer 3 switch using SVIs. Hubs, repeaters, and bridges all operate at Layer 1/2 and can't route between IP subnets.
</details>

---

### Q10. In the AUTOSAR communication stack, which module controls the physical CAN transceiver hardware and provides physical-layer diagnostics?

- A. CANSM (CAN State Manager)
- B. CANTP (CAN Transport Layer)
- C. CANIF (CAN Interface)
- D. CANTRCV (CAN Transceiver Driver)

<details>
<summary>Show Answer</summary>

**Answer: D. CANTRCV (CAN Transceiver Driver)**

CANTRCV interfaces directly with the physical transceiver hardware — controlling power modes, detecting wake-up events, and checking for physical faults. CANSM handles network state/control flow, CANTP segments/reassembles large messages, and CANIF abstracts hardware from upper layers.
</details>

---

### Q11. Which OSI/TCP-IP layer is responsible for "end-to-end" (host-to-host) communication, as opposed to hop-by-hop?

- A. Application layer
- B. Network layer
- C. Physical layer
- D. Transport layer

<details>
<summary>Show Answer</summary>

**Answer: D. Transport layer**

The Transport layer (TCP/UDP) ensures data from a source application reaches the correct destination application end-to-end, managing flow control and segmentation. Network and Data Link layers operate hop-by-hop; Application layer relies on lower layers for actual transmission.
</details>

---

### Q12. Which statements about a Converged Network Interface Card (Converged NIC) in Windows Server are correct?
1. It allows a single network adapter to be used for management and RDMA-enabled storage.
2. It cannot handle tenant traffic.
3. It reduces capital expenditures associated with each server in a datacenter.

- A. Only 1
- B. Only 2
- C. 1 and 3
- D. 1, 2, and 3

<details>
<summary>Show Answer</summary>

**Answer: C. 1 and 3**

A Converged NIC consolidates management, RDMA storage traffic, *and* tenant (VM) traffic over the same physical adapter via the Hyper-V Virtual Switch — so statement 2 is false. Statements 1 and 3 are both correct: it combines traffic types on shared hardware and reduces the need for separate physical NICs, lowering CapEx.
</details>

---

### Q13. A server crashes mid-connection and reboots, losing all connection state. Which of the following is true about what happens next?
1. After rebooting, the server will send a FIN segment to the client.
2. After rebooting, the server will send an RST segment to the client.
3. Before rebooting, if all the client's keep-alive timers expire, the client can still transmit data on the same connection.

- A. Only 1
- B. Only 2
- C. 1 and 3
- D. 2 and 3

<details>
<summary>Show Answer</summary>

**Answer: B. Only 2**

When the client sends data to the rebooted server, the server finds no matching connection state and responds with RST to force the client to close its side. A FIN implies a graceful, mutually-aware shutdown, which can't happen since the server forgot the connection. If the client's keep-alive timers fully expire, the client itself considers the connection dead and won't keep transmitting.
</details>

---

## Operating Systems & Linux

### Q14. What is the role of GRUB in the Linux boot sequence?

- A. It is the first process started by the kernel, responsible for initializing all system services
- B. It initializes hardware devices and loads firmware settings before the kernel starts
- C. It is the bootloader that loads the Linux kernel into memory and passes boot parameters
- D. It manages kernel module loading after the root filesystem is mounted

<details>
<summary>Show Answer</summary>

**Answer: C. It is the bootloader that loads the Linux kernel into memory and passes boot parameters**

GRUB (GRand Unified Bootloader) presents a boot menu, loads the selected kernel and initramfs into RAM, and passes command-line parameters to it. The "first process" role belongs to `init`/`systemd` (PID 1); hardware initialization is BIOS/UEFI's job; module loading after boot is handled by `modprobe`/`udev`.
</details>

---

### Q15. `dig`/`nslookup` successfully resolve external domains and `tcpdump` shows DNS query/response traffic, yet regular applications (ping, curl) fail to resolve the same domains. What's the likely cause?

- A. The system's hostname is not registered in the external DNS zone
- B. The network interface MTU is too small
- C. The DNS server has an expired cache, causing stale NXDOMAIN responses
- D. `/etc/nsswitch.conf` is configured to skip DNS and resolve names using only `/etc/hosts`

<details>
<summary>Show Answer</summary>

**Answer: D. `/etc/nsswitch.conf` is configured to skip DNS and resolve names using only `/etc/hosts`**

Tools like `dig`/`nslookup` bypass `nsswitch.conf` and query `/etc/resolv.conf` directly — explaining why they succeed and show up in `tcpdump`. Regular applications use the standard C library resolver, which obeys `nsswitch.conf`. If it's set to `hosts: files` only (skipping `dns`), normal apps fail to resolve external names entirely.
</details>

---

### Q16. After deleting large log files, `du` shows space was freed but `df` still reports the disk as 99% full. What's the most likely explanation?

- A. The deleted files were hard-linked to other locations
- B. The filesystem metadata tables are corrupted
- C. The log files were compressed before deletion
- D. The deleted files are still held open by running processes, so the kernel hasn't released their disk blocks yet

<details>
<summary>Show Answer</summary>

**Answer: D. The deleted files are still held open by running processes, so the kernel hasn't released their disk blocks yet**

Deleting a file only removes its directory entry; the kernel won't free disk blocks until the open file-descriptor count also reaches zero. `du` walks the directory tree (so it no longer sees the file), but `df` reads filesystem block-allocation metadata directly — and an actively logging process still holding the file open keeps those blocks "in use." Fix: restart the service or find/kill the process via `lsof | grep deleted`.
</details>

---

### Q17. What does the `csh` command refer to, and which of these statements about it are true?
1. It is known as the C shell.
2. It begins by executing commands from `.cshrc`.
3. It invokes a shell command interpreter.

- A. 1 only
- B. 1 and 2
- C. 2 and 3
- D. All of the above

<details>
<summary>Show Answer</summary>

**Answer: D. All of the above**

`csh` is the C shell, developed by Bill Joy, with syntax resembling C. On startup it executes `.cshrc` from the user's home directory, and as a shell, it is by definition a command interpreter.
</details>

---

### Q18. What does the `cat` command primarily do in Linux?

- A. Displays the absolute path of the current directory
- B. Reads a file and outputs its content to the terminal
- C. Lists files and subdirectories
- D. Navigates between directories

<details>
<summary>Show Answer</summary>

**Answer: B. Reads a file and outputs its content to the terminal**

`cat filename.txt` concatenates and displays a file's contents. (`pwd` prints the working directory, `ls` lists contents, `cd` changes directories.)
</details>

---

### Q19. Which command schedules a system shutdown in 5 minutes with a broadcast message "Maintenance Work"?

- A. `shutdown 5 "Maintenance Work"`
- B. `shut down 5 -m "Maintenance work"`
- C. `close 5min "Maintenance Work"`
- D. `close -5 "Maintenance Work"`

<details>
<summary>Show Answer</summary>

**Answer: A. `shutdown 5 "Maintenance Work"`**

`shutdown [TIME] [MESSAGE]` schedules the shutdown for the given number of minutes and broadcasts the message to logged-in users. `shut down` (two words) isn't a valid command, and `close` isn't a standard Linux shutdown utility.
</details>

---

### Q20. Which system call returns the Process ID (PID) of the *parent* of the calling process?

- A. `getpid()`
- B. `getppid()`
- C. `getuid()`
- D. `getpgid()`

<details>
<summary>Show Answer</summary>

**Answer: B. `getppid()`**

`getppid()` returns the parent process's PID. `getpid()` returns the *current* process's own PID, not its parent's.
</details>

---

### Q21. How many quoting mechanisms does Bash recognize, and what are they?

- A. Single quote and double quote only
- B. Single quote, double quote, backslash, and back quote (backtick)
- C. Single quote, double quote, backslash, back quote, and "routing quote"
- D. Backslash and back quote only

<details>
<summary>Show Answer</summary>

**Answer: B. Single quote, double quote, backslash, and back quote (backtick)**

- Single quote (`'`) — preserves literal value of everything inside, no expansion.
- Double quote (`"`) — preserves literal value but allows `$` expansion, command substitution, and arithmetic expansion.
- Backslash (`\`) — escapes the single next character.
- Back quote (`` ` ``) — legacy command substitution syntax.

"Routing quote" is not a real shell concept.
</details>

---

### Q22. In the default column order of `ps -efj` style output, what is the correct left-to-right sequence of: User ID, Process ID, Terminal Name, Command String, Session ID, Parent Process ID, Process Group ID?

- A. PID → UID → PPID → PGID → SID → TTY → CMD
- B. UID → PID → PPID → PGID → SID → TTY → CMD
- C. UID → PPID → PID → SID → PGID → TTY → CMD
- D. UID → PID → SID → PPID → PGID → TTY → CMD

<details>
<summary>Show Answer</summary>

**Answer: B. UID → PID → PPID → PGID → SID → TTY → CMD**

The hierarchical order moves from ownership → process identity → parent identity → group → session → terminal → command: **User ID → Process ID → Parent Process ID → Process Group ID → Session ID → Terminal Name → Command String**.
</details>

---

### Q23. Which command reports disk space usage with output explicitly in 1-kilobyte blocks?

- A. `df -h`
- B. `df -m`
- C. `du -s`
- D. `df -k`

<details>
<summary>Show Answer</summary>

**Answer: D. `df -k`**

`-k` forces 1K block output. `-h` is human-readable (auto-scaled units), `-m` forces 1MB blocks, and `du -s` summarizes directory usage rather than whole-filesystem occupancy.
</details>

---

### Q24. Which statements about Linux kernel module commands are true?
1. `lsmod` is used to load all the modules.
2. `module` is used to append a module to another.
3. `modprobe XYZ` will load the module XYZ.
4. `insmod` and `modprobe` work identically.

- A. 1, 2, and 4
- B. 1, 2, 3, and 4
- C. 1, 3, and 4
- D. All are wrong (only statement 3 alone is true, so none of the listed combinations are fully correct)

<details>
<summary>Show Answer</summary>

**Answer: D. All are wrong**

Only statement 3 is actually true (`modprobe XYZ` loads module XYZ). `lsmod` *lists* loaded modules, it doesn't load them (statement 1 false). There's no `module` command for appending modules (statement 2 false). `insmod` requires a full file path and doesn't resolve dependencies, while `modprobe` only needs the module name and auto-resolves dependencies — so they don't work identically (statement 4 false). Since every answer choice bundles in at least one false statement, none of the combination options is fully correct.
</details>

---

### Q25. What command changes a server's runlevel/power state (e.g., reboot or shutdown) directly from the terminal in traditional Unix/Linux systems?

- A. `boot`
- B. `ifconfig`
- C. `init`
- D. `rc`

<details>
<summary>Show Answer</summary>

**Answer: C. `init`**

`init` (PID 1) manages runlevels; e.g. `init 6` reboots, `init 0` shuts down. `boot` isn't a standalone command, `ifconfig` configures network interfaces (unrelated to power state), and `rc` refers to runcom scripts executed *by* init, not a user command for switching states.
</details>

---

## Shell Scripting

### Q26. Given a loop that increments `i` from 1 to 50 and only prints `i` when `i % 9 == 0` (using `continue` otherwise), what gets printed?

- A. 9 18 27 36 45
- B. 1 9 18 27 36 45
- C. 9 18 27 36 45 50
- D. 0 9 18 27 36 45

<details>
<summary>Show Answer</summary>

**Answer: A. 9 18 27 36 45**

The script only reaches the `echo $i` line (the `else` branch) when `i` is an exact multiple of 9. Within 1–50, the multiples of 9 are 9, 18, 27, 36, and 45 (54 exceeds the loop bound).
</details>

---

### Q27. What does `cat student | sed '4,10d'` do?

- A. Displays only line 4 and line 10 of the student file
- B. Deletes only lines 4 and 10, keeping everything else
- C. Deletes lines 4 through 10, displaying all other lines
- D. Deletes every line except lines 4 through 10

<details>
<summary>Show Answer</summary>

**Answer: C. Deletes lines 4 through 10, displaying all other lines**

`sed '4,10d'` defines the address range 4–10 and applies the `d` (delete) command to it, suppressing those lines while passing all others through unchanged. (Inverting with `!d` would keep only that range; deleting just two discrete lines would need `-e '4d' -e '10d'`; printing just those lines would use `-n '4,10p'`.)
</details>

---

### Q28. A shell loop runs `for val in U N I X` and on each iteration does `a=$a$val`. What is the final value of `a`?

- A. X I N U
- B. UNIX
- C. U N I X
- D. XINU

<details>
<summary>Show Answer</summary>

**Answer: B. UNIX**

Each iteration appends the next letter to `a`: `""→"U"→"UN"→"UNI"→"UNIX"`. The final `echo $a` prints `UNIX`.
</details>

---

### Q29. A script runs `mail -s $Subject $Recipient <<< $Message` as its final line. What happens when it executes successfully?

- A. Prints the email body to the terminal
- B. Prints a confirmation message to the terminal
- C. Sends an email to the recipient and prints nothing in the terminal
- D. Throws an error because `<<<` is invalid syntax

<details>
<summary>Show Answer</summary>

**Answer: C. Sends an email to the recipient and prints nothing in the terminal**

`mail -s $Subject $Recipient` sends mail with the given subject/recipient; the here-string (`<<<`) feeds `$Message` as the body via stdin. On success, `mail` returns silently with no terminal output.
</details>

---

### Q30. Trace this `ksh`/POSIX-shell script:
```ksh
b1=7
b2=5
h=4

if( a=$(((b1+b2)/2)*h))!= 0 )
do
  echo True
done
echo $a
```
What is the output?

- A. True followed by 24
- B. 24
- C. Error
- D. True

<details>
<summary>Show Answer</summary>

**Answer: C. Error**

The script has fundamental syntax errors: shell `if` statements require `then`/`fi`, not `do`/`done` (those are for loops). The parenthesis/assignment syntax around `a=$(((b1+b2)/2)*h)` is also invalid in this context. The script fails to run rather than producing a value — even though the *intended* trapezoid-area calculation, if written correctly with `(( ))` arithmetic context and `then...fi`, would evaluate to `((7+5)/2)*4 = 24`.
</details>

---

### Q31. In positional parameters, which statements are correct?
1. `${10}` through `${n}` represent arguments *before* the ninth argument.
2. `$1` through `$9` represent the first nine command-line arguments.
3. `$0` holds the name of the script/command currently executing.

- A. 1 only
- B. 2 only
- C. 2 and 3
- D. 1, 2, and 3

<details>
<summary>Show Answer</summary>

**Answer: C. 2 and 3**

`$1`–`$9` are the first nine arguments (statement 2, true), and `$0` is the script/command name (statement 3, true). `${10}` and beyond represent arguments **after** the ninth, not before — the braces are required so the shell doesn't parse `$10` as `$1` followed by a literal `0`. So statement 1 is false.
</details>

---

## Data Structures & Algorithms (MCQ)

### Q32. What is the formal definition of a Complete Binary Tree?

- A. Every node has exactly zero or two children
- B. Every leaf node is at the same depth and every internal node has two children
- C. All levels are fully filled except possibly the last, which is filled left to right
- D. Every node has at most one child

<details>
<summary>Show Answer</summary>

**Answer: C. All levels are fully filled except possibly the last, which is filled left to right**

That's the precise definition of a Complete Binary Tree. Option A describes a **Full/Strict Binary Tree** (leaves needn't be at the same depth). Option B describes a **Perfect Binary Tree** (both full and complete).
</details>

---

### Q33. Which algorithm correctly computes single-source shortest paths in a graph that may contain negative edge weights (but no negative cycles)?

- A. Dijkstra's algorithm using a min-heap priority queue
- B. BFS with edge weights stored as node attributes
- C. Bellman-Ford algorithm relaxing all edges n-1 times
- D. Floyd-Warshall algorithm applied from the given source only

<details>
<summary>Show Answer</summary>

**Answer: C. Bellman-Ford algorithm relaxing all edges n-1 times**

Bellman-Ford relaxes all edges n−1 times (the max possible edges in any shortest acyclic path), correctly handling negative weights. Dijkstra's greedy approach fails with negative edges. Plain BFS only works for unweighted graphs. Floyd-Warshall is an all-pairs algorithm — using it for a single source is needlessly inefficient (O(V³)).
</details>

---

### Q34. In Kruskal's algorithm for building a Minimum Spanning Tree, what data structure efficiently detects whether adding an edge would create a cycle?

- A. A min-heap storing all remaining candidate edges by weight
- B. A visited boolean array indexed by vertex number
- C. A Union-Find structure tracking connected components of selected edges
- D. A BFS queue that re-traverses the current tree after each edge addition

<details>
<summary>Show Answer</summary>

**Answer: C. A Union-Find structure tracking connected components of selected edges**

Union-Find (Disjoint Set) efficiently answers "are these two vertices already in the same component?" (Find) and merges components (Union) in near-constant amortized time. A min-heap only sorts edges by weight; a visited array can't distinguish separate trees in a forest; re-running BFS per edge is far too slow.
</details>

---

### Q35. Given a sorted array with duplicates, how can you find the first and last index of a target value in O(log n) time, without linear scanning?

- A. Use a hash map to store all indices in one pass
- B. Sort the array again and take indices in one pass
- C. Apply ternary search instead of binary search
- D. Run two separate binary searches — one biased left to find the first, one biased right to find the last

<details>
<summary>Show Answer</summary>

**Answer: D. Run two separate binary searches — one biased left to find the first, one biased right to find the last**

Each modified binary search continues searching past a match (left or right, respectively) to find the boundary, keeping both searches at O(log n). A hash map requires an O(n) pass to build; sorting again is redundant and O(n log n); ternary search doesn't inherently solve the boundary-finding problem any better than binary search.
</details>

---

### Q36. What's the time complexity of `push()` and `pop()` on a stack implemented with a singly linked list, tracking only the head (`top`) node?

- A. O(1) for both push() and pop()
- B. O(n) for both push() and pop()
- C. O(1) for push(), O(n) for pop()
- D. O(log n) for both push() and pop()

<details>
<summary>Show Answer</summary>

**Answer: A. O(1) for both push() and pop()**

Both operations only touch the head node — creating/relinking a new top node, or removing the current top and advancing the pointer — with no traversal required.
</details>

---

### Q37. A system needs to merge user groups and answer "are these two users in the same group?" queries efficiently and repeatedly. What data structure is ideal?

- A. Queue storing representatives of each connected group
- B. Plain adjacency matrix storing all pairwise connections
- C. Hash table keyed by individual user identifiers
- D. Union-Find structure with path compression

<details>
<summary>Show Answer</summary>

**Answer: D. Union-Find structure with path compression**

Union-Find directly supports merging groups (Union) and connectivity queries (Find) in near-constant amortized time, O(α(n)), thanks to path compression (often paired with union-by-rank). A queue requires scanning; an adjacency matrix needs O(n²) traversal for general connectivity; a hash table alone doesn't track hierarchical group structure.
</details>

---

### Q38. Two recursive functions are given: Function I computes a factorial-style result for `n=7`; Function II computes `x^y` for `x=7, y=5` via repeated multiplication. What are their outputs and time complexities?

- A. Factorial = 5040, O(n); Power = 16807, O(y)
- B. Factorial = 5040, O(n); Power = 16807, O(log y)
- C. Factorial = 5040, O(n²); Power = 16807, O(y)
- D. Factorial = 720, O(n); Power = 16807, O(y)

<details>
<summary>Show Answer</summary>

**Answer: A. Factorial = 5040, O(n); Power = 16807, O(y)**

7! = 7×6×5×4×3×2×1 = 5040, computed via n sequential recursive calls → O(n). 7⁵ = 7×7×7×7×7 = 16807, computed via y sequential multiplications → O(y).
</details>

---

### Q39. Which of the following are genuine advantages of an Unrolled Linked List over a regular singly linked list?
1. Linear search is faster due to better cache behavior.
2. The overhead per *node* is comparatively higher than a singly linked list.
3. It requires less storage space for pointers/references overall.
4. Insertion, deletion, and traversal are generally faster.

- A. 1 and 2
- B. 1, 3, and 4
- C. 2, 3, and 4
- D. 1, 2, 3, and 4

<details>
<summary>Show Answer</summary>

**Answer: B. 1, 3, and 4**

Storing arrays of elements per node improves spatial locality (faster cache-friendly traversal/search), reduces total pointer overhead (one pointer per block instead of per element), and speeds up insert/delete/traversal overall. Statement 2 (higher per-node overhead) is true as a *characteristic* but is a trade-off/disadvantage, not an advantage — so it's excluded from the "advantages" list.
</details>

---

### Q40. For a read-heavy hash table workload, which collision-resolution strategy gives the best CPU cache locality?

- A. Separate chaining using linked list buckets
- B. Separate chaining using binary search trees
- C. Open addressing with linear probing strategy
- D. Rehashing the entire table on every insertion

<details>
<summary>Show Answer</summary>

**Answer: C. Open addressing with linear probing strategy**

Linear probing keeps all keys in one contiguous array, so probing nearby slots benefits from the CPU's cache-line prefetching (spatial locality). Chaining (lists or trees) scatters nodes across heap memory, causing cache misses on pointer chasing. Rehashing on every insert is needlessly expensive.
</details>

---

### Q41. To order a feed of posts that have dependency constraints (post A must appear before post B) in a Directed Acyclic Graph, which technique is correct?

- A. Depth first search ignoring edge directions fully
- B. Prim's algorithm ordering posts by random weights
- C. Breadth first search starting from every vertex
- D. Topological sorting using an indegree-based approach (Kahn's Algorithm)

<details>
<summary>Show Answer</summary>

**Answer: D. Topological sorting using an indegree-based approach (Kahn's Algorithm)**

Kahn's Algorithm computes indegree for every vertex, queues all indegree-0 nodes, then repeatedly removes a node, appends it to the order, and decrements neighbors' indegrees — adding any that hit zero to the queue. Ignoring edge direction destroys the dependency semantics; Prim's is for MSTs on weighted undirected graphs; plain BFS from every vertex doesn't track indegrees or guarantee a valid topological order.
</details>

---

### Q42. A collaborative text editor needs fast insertions/deletions at arbitrary positions in a massive document. Which data structure is ideal?

- A. Simple dynamic array storing the entire document contiguously
- B. Queue appending characters only at the document end
- C. Stack maintaining characters in strict reverse order
- D. Rope implemented as a balanced binary tree

<details>
<summary>Show Answer</summary>

**Answer: D. Rope implemented as a balanced binary tree**

A Rope splits the string into leaf-node substrings within a balanced binary tree, with internal nodes tracking subtree lengths ("weights"). Insertions/deletions become tree split/concatenate operations costing O(log n), instead of the O(n) character-shifting required by a plain contiguous array. Queues and stacks are restricted to end-only/LIFO operations and can't support arbitrary-position edits.
</details>

---

## SQL & Databases

### Q43. Which JOIN type returns every row from the right-hand table, regardless of whether a match exists in the left table?

- A. INNER JOIN
- B. LEFT JOIN
- C. RIGHT JOIN
- D. CROSS JOIN

<details>
<summary>Show Answer</summary>

**Answer: C. RIGHT JOIN**

RIGHT JOIN (RIGHT OUTER JOIN) returns all rows from the right table, filling unmatched left-table columns with NULL. INNER JOIN only returns matches in both tables; LEFT JOIN is the mirror opposite of RIGHT JOIN; CROSS JOIN returns the full Cartesian product with no matching logic.
</details>

---

### Q44. After deleting a row from a table with an `AUTO_INCREMENT` primary key, what happens to that ID the next time a row is inserted?

- A. It reuses the deleted row's ID
- B. It assigns a random unused ID
- C. It restarts numbering from 1
- D. It continues from where the sequence left off, skipping the deleted ID permanently

<details>
<summary>Show Answer</summary>

**Answer: D. It continues from where the sequence left off, skipping the deleted ID permanently**

`AUTO_INCREMENT`/`IDENTITY`/`SERIAL` counters only move forward — they never roll back on delete. This avoids expensive locking/scanning for gap-filling and prevents foreign-key/audit-log conflicts from ID reuse. Reusing IDs, randomizing, or restarting from 1 would all risk duplicate-key errors or broken references.
</details>

---

### Q45. Given a `students` table, what does this query return?
```sql
SELECT * FROM students 
WHERE LENGTH(first_name) > 3;
```
Names in the table: Alice (5), Bob (3), Ben (3), Mike (4), Tara (4).

- A. All five students
- B. Only Bob and Ben
- C. Alice, Mike, and Tara
- D. No rows

<details>
<summary>Show Answer</summary>

**Answer: C. Alice, Mike, and Tara**

`LENGTH(first_name) > 3` keeps only names with more than 3 characters. Bob (3) and Ben (3) fail the condition (3 > 3 is false); Alice (5), Mike (4), and Tara (4) all pass.
</details>

---

### Q46. You need to find pairs of calls from the same customer that occurred within 5 minutes of each other, within a single `calls` table. What's the correct/most efficient approach?

- A. Use a CROSS JOIN of `calls` with itself, without any join condition
- B. Use a NATURAL JOIN of `calls` with itself
- C. Use INNER JOIN `calls AS c1 JOIN calls AS c2 ON c1.customer_id = c2.customer_id`, filtering by call times
- D. Use a subquery on `calls` grouped by `customer_id`

<details>
<summary>Show Answer</summary>

**Answer: C. Use INNER JOIN `calls AS c1 JOIN calls AS c2 ON c1.customer_id = c2.customer_id`, filtering by call times**

This is a self-join: aliasing the table as `c1`/`c2`, joining on matching `customer_id`, and adding a time-window filter (plus excluding `c1.call_id = c2.call_id`) correctly and efficiently identifies same-customer call pairs within 5 minutes. CROSS JOIN creates a wasteful Cartesian product; NATURAL JOIN would match every identical column (including matching a call to itself); grouping by `customer_id` alone loses the per-call timestamp granularity needed for the time-window check.
</details>

---

## Machine Learning / LLMs

### Q47. What is the core purpose of a recommendation system (e.g., YouTube, Netflix, Amazon)?

- A. A system that detects anomalies in user behavior to flag security threats
- B. A system that translates user preferences into structured database search queries
- C. A system that predicts items a user is likely to engage with based on past behavior
- D. A system that classifies user queries into categories to route them to the right department

<details>
<summary>Show Answer</summary>

**Answer: C. A system that predicts items a user is likely to engage with based on past behavior**

Recommendation systems analyze viewing/click/purchase history to proactively surface relevant content. (A describes anomaly/fraud detection; B describes a text-to-SQL engine; D describes intent classification for support routing.)
</details>

---

### Q48. A developer adds three labeled examples of each category directly into a prompt before asking an LLM to classify new text. What prompting technique is this?

- A. Zero-shot prompting
- B. Few-shot prompting
- C. Chain-of-thought prompting
- D. Instruction tuning

<details>
<summary>Show Answer</summary>

**Answer: B. Few-shot prompting**

Including a small number of labeled examples ("shots") directly in the prompt for in-context learning is the definition of few-shot prompting. Zero-shot gives no examples; chain-of-thought asks the model to reason step-by-step; instruction tuning modifies the model's weights during training, not at prompt time.
</details>

---

### Q49. Why does BERT produce incoherent output when forced to generate text autoregressively, one token at a time?

- A. BERT's positional embeddings are fixed-length and cannot represent sequences longer than 512 tokens
- B. BERT uses byte-pair encoding, which produces subword tokens incompatible with sequential generation
- C. BERT's bidirectional attention attends to future tokens during pre-training, so it was never trained to predict the next token from left context only
- D. BERT's feed-forward layers are too narrow to support generation

<details>
<summary>Show Answer</summary>

**Answer: C. BERT's bidirectional attention attends to future tokens during pre-training, so it was never trained to predict the next token from left context only**

BERT is trained with Masked Language Modeling, using both left *and* right context to predict masked words. Autoregressive generation requires predicting the next token using only left context — a mismatch with what BERT was trained to do. The 512-token limit, tokenization scheme, and feed-forward width are not the root cause of the incoherence.
</details>

---

### Q50. What training technique does CLIP (Contrastive Language-Image Pre-training) use to align images and text in a shared embedding space?

- A. Sequential processing where text is converted to images first
- B. Separate training of text and image encoders without interaction
- C. Direct pixel-level matching between text characters and image pixels
- D. Contrastive learning that maximizes similarity between matching text-image pairs while minimizing similarity between non-matching pairs

<details>
<summary>Show Answer</summary>

**Answer: D. Contrastive learning that maximizes similarity between matching text-image pairs while minimizing similarity between non-matching pairs**

Given a batch of N image-text pairs, CLIP maximizes cosine similarity for the N correct (matching) pairs and minimizes it for the N²−N incorrect pairs, enabling strong zero-shot classification. The encoders interact during training via this contrastive objective — they aren't trained in isolation, and there's no pixel-to-character matching or image-first conversion involved.
</details>

---

### Q51. When an LCEL (LangChain Expression Language) chain is called inside a LangGraph node, child spans sometimes go missing from LangSmith traces. What's the fix?

- A. The LCEL chain must be compiled with `.compile()` before wrapping it in a node
- B. The node function must forward the `RunnableConfig` it receives into the chain's invoke call
- C. Each inner LLM call must be individually wrapped in `with_config()`
- D. The LangSmith project name must be set via metadata tags on each LCEL chain explicitly

<details>
<summary>Show Answer</summary>

**Answer: B. The node function must forward the `RunnableConfig` it receives into the chain's invoke call**

LangGraph nodes implicitly receive a `RunnableConfig` carrying trace context. If that config isn't explicitly passed into the inner chain's `invoke`/`ainvoke` call, the chain loses its parent trace context and appears as an isolated/untracked run in LangSmith instead of a nested child span.
</details>

---

### Q52. What architectural concept best describes how LangGraph models multi-agent systems?

- A. Hierarchical delegation protocols between specialized components
- B. Orchestrated interactions between autonomous processing entities
- C. Centralized control structures managing distributed execution units
- D. Sequentially triggered agent activations with predetermined pathways

<details>
<summary>Show Answer</summary>

**Answer: B. Orchestrated interactions between autonomous processing entities**

LangGraph treats each agent/tool as an autonomous node and uses shared state plus conditional edges to orchestrate dynamic interactions between them — supporting loops and non-linear paths. This is distinct from rigid hierarchical delegation, a single centralized controller, or fixed sequential chains (which is what LangGraph was designed to move beyond).
</details>

---

## Coding Problems

> These were originally open-ended coding prompts (not multiple-choice). Solutions are included below each for reference — try solving independently first.

### C1. Minimum Team Cost (greedy + max-heap)

**Problem:** Given `N` candidates with `skillLevel[i]` and `minSalary[i]`, and a target team size `K`, find the minimum total cost team of size `K` such that cost is calculated using the highest salary-to-skill ratio in the team multiplied by total skill.

<details>
<summary>Show Solution (Python)</summary>

```python
import heapq

def minimumTeamCost(N, K, skillLevel, minSalary):
    candidates = sorted(
        [(minSalary[i] / skillLevel[i], skillLevel[i]) for i in range(N)],
        key=lambda x: x[0]
    )
    max_heap = []
    skill_sum = 0
    min_cost = float('inf')

    for ratio, skill in candidates:
        heapq.heappush(max_heap, -skill)
        skill_sum += skill

        if len(max_heap) > K:
            skill_sum += heapq.heappop(max_heap)

        if len(max_heap) == K:
            min_cost = min(min_cost, ratio * skill_sum)

    return round(min_cost)
```

**Approach:** Sort candidates by salary-to-skill ratio. Slide a max-heap of size K through the sorted list, always keeping the K highest skill values seen so far (within the current ratio cutoff). At each point where the heap size hits K, the current ratio is the maximum ratio in the window, so `ratio * skill_sum` is a valid candidate cost — track the minimum.
</details>

---

### C2. Find Next Prime Palindrome ≥ n

**Problem:** Given an integer `n`, find the smallest prime palindrome ≥ n.

<details>
<summary>Show Solution — Brute Force (Python)</summary>

```python
def findPrimePalindrome(n):
    def is_prime(k):
        if k < 2:
            return False
        if k in (2, 3):
            return True
        if k % 2 == 0 or k % 3 == 0:
            return False
        i = 5
        while i * i <= k:
            if k % i == 0 or k % (i + 2) == 0:
                return False
            i += 6
        return True

    while True:
        s = str(n)
        l = len(s)
        if l % 2 == 0 and n > 11:
            n = 10**l
            continue
        if s == s[::-1] and is_prime(n):
            return n
        n += 1

n = int(input())
print(findPrimePalindrome(n))
```

**Optimization note:** All even-length palindromes (except 11) are divisible by 11, so they can never be prime. The code skips straight to the next odd-length power of 10 whenever it detects an even-length candidate, avoiding wasted checks.
</details>

<details>
<summary>Show Solution — Optimized, Generates Palindromes Directly (Python)</summary>

```python
def findPrimePalindrome(n):
    def is_prime(k):
        if k < 2:
            return False
        if k in (2, 3):
            return True
        if k % 2 == 0 or k % 3 == 0:
            return False
        i = 5
        while i * i <= k:
            if k % i == 0 or k % (i + 2) == 0:
                return False
            i += 6
        return True

    if n <= 2: return 2
    if n == 3: return 3
    if n <= 5: return 5
    if n <= 7: return 7
    if n <= 11: return 11

    for length in range(1, 10):
        if length % 2 == 0:
            continue
        half_len = (length + 1) // 2
        for half in range(10**(half_len - 1), 10**half_len):
            s = str(half)
            p = int(s + s[-2::-1])
            if p >= n and is_prime(p):
                return p

n = int(input())
print(findPrimePalindrome(n))
```

**Approach:** Instead of checking every number for palindrome-ness, directly *construct* odd-length palindromes from their left half and mirror it, then test primality only on those candidates — far fewer numbers to check.
</details>

<details>
<summary>Show Solution — Java Version</summary>

```java
static int findPrimePalindrome(int n) {
    while (true) {
        if (n > 11 && ((n >= 10 && n < 100) ||
                       (n >= 1000 && n < 10000) ||
                       (n >= 100000 && n < 1000000) ||
                       (n >= 10000000 && n < 100000000))) {
            if (n < 100) n = 100;
            else if (n < 10000) n = 10000;
            else if (n < 1000000) n = 1000000;
            else n = 100000000;
            continue;
        }
        if (isPalindrome(n) && isPrime(n)) {
            return n;
        }
        n++;
    }
}

static boolean isPalindrome(int n) {
    int reversed = 0, temp = n;
    while (temp > 0) {
        reversed = reversed * 10 + temp % 10;
        temp /= 10;
    }
    return reversed == n;
}

static boolean isPrime(int n) {
    if (n < 2) return false;
    if (n == 2 || n == 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) return false;
    }
    return true;
}
```
</details>

---

### C3. Maximize Minimum Power Supply (binary search + greedy + difference array)

**Problem:** Given `N` cities, a `coverage` radius, `K` extra power stations to place, and `existing` power values per city (where each city's power is the sum of all stations within `coverage` of it), maximize the **minimum** power across all cities after optimally placing the `K` additional stations.

<details>
<summary>Show Solution (Python, compact)</summary>

```python
def maxMinimumPowerSupply(N, coverage, K, existing):
    P = [0] * (N + 1)
    for i in range(N):
        P[i+1] = P[i] + existing[i]

    I = [P[min(N, i + coverage + 1)] - P[max(0, i - coverage)] for i in range(N)]

    def check(t):
        A = [0] * N
        cur = used = 0
        for i in range(N):
            cur += A[i]
            if I[i] + cur < t:
                need = t - I[i] - cur
                used += need
                if used > K:
                    return False
                cur += need
                if i + 2 * coverage + 1 < N:
                    A[i + 2 * coverage + 1] -= need
        return True

    l, r = (min(I), max(I) + K) if N else (0, 0)
    ans = l
    while l <= r:
        m = (l + r) // 2
        if check(m):
            ans, l = m, m + 1
        else:
            r = m - 1
    return ans

N = int(input().strip())
coverage = int(input().strip())
K = int(input().strip())
existing = list(map(int, input().split()))
print(maxMinimumPowerSupply(N, coverage, K, existing))
```

**Approach:**
1. **Prefix sums** compute each city's initial power (`I[i]`) in O(1).
2. **Binary search** on the answer `t` (the achievable minimum power).
3. **Greedy `check(t)`**: walk left to right; whenever a city's power is below `t`, add just enough stations placed as far right as possible (at `i + coverage`) — this benefits the most future cities. A **difference array** (`A`) tracks when that added power "expires" (`i + 2*coverage + 1`), so the whole pass stays O(N) instead of needing nested loops.
4. Overall complexity: O(N log(maxPower)).
</details>

---

### C4. Minimum Idle Time Across Tasks (Interval DP)

**Problem:** Given `N` tasks and `N` server times in array `A`, where task `i` must use a growing pool of selected servers and its idle time equals `max(pool) - min(pool)`, minimize the total summed idle time across all tasks. The pool grows by exactly one server per task, and servers can be added in any order (not just left-to-right after sorting).

<details>
<summary>Show Solution — Naive Greedy (incorrect — left as a cautionary example)</summary>

```python
def solve(N, A):
    A.sort()
    total_idle_time = 0
    current_min = A[0]
    current_max = A[0]
    for i in range(1, N):
        current_min = min(current_min, A[i])
        current_max = max(current_max, A[i])
        total_idle_time += (current_max - current_min)
    return total_idle_time
```

**Why this fails:** Always expanding strictly left-to-right through the sorted array doesn't account for cases where starting from a *cluster* of close values (e.g., picking all the `2`s before expanding outward to `1` and `3`) produces a lower total cost. This naive version can produce a higher total than optimal.
</details>

<details>
<summary>Show Solution — Correct Interval DP (Python)</summary>

```python
def solve(N, A):
    A.sort()
    dp = [0] * N
    for length in range(2, N + 1):
        new_dp = [0] * (N - length + 1)
        for i in range(N - length + 1):
            j = i + length - 1
            new_dp[i] = (A[j] - A[i]) + min(dp[i], dp[i + 1])
        dp = new_dp
    return dp[0]

try:
    T = int(input())
    for _ in range(T):
        N = int(input())
        A = list(map(int, input().split()))
        print(solve(N, A))
except (ValueError, EOFError):
    pass
```

**Approach:** Sort `A`. For each contiguous subarray length (2 to N), `dp[i]` stores the minimum total idle cost for the subarray starting at sorted-index `i` of that length. At each step, the cost is the subarray's range (`A[j] - A[i]`) plus the cheaper of extending from the left or right neighbor's pre-computed cost. This explores the optimal order of "growing outward" from the densest cluster, not just left-to-right.

**Complexity:** O(N²) time, O(N) space — well within typical constraints (N ≤ 2000).
</details>

---

### C5. Minimum Tunnels to Seal (graph isolation)

**Problem:** Given `N` rooms, `M` tunnels (edges), and an infected room `S`, find the minimum number of tunnels to seal so that no other room remains connected to `S`.

<details>
<summary>Show Solution (Python)</summary>

```python
def minTunnelsToSeal(N, M, S, tunnels):
    tunnels_to_seal = 0
    for u, v in tunnels:
        if u == S or v == S:
            tunnels_to_seal += 1
    return tunnels_to_seal

if __name__ == "__main__":
    N = int(input())
    M = int(input())
    S = int(input())
    tunnels = [list(map(int, input().split())) for i in range(M)]
    print(minTunnelsToSeal(N, M, S, tunnels))
```

**Approach:** To fully isolate `S`, you must seal every tunnel directly touching it — i.e., the answer is simply the **degree of node S**. No graph traversal is needed.

**Complexity:** O(M) time, O(1) extra space.
</details>

---

## License / Usage

Feel free to fork, adapt, and extend this question bank. Contributions of additional questions or corrections to existing answers are welcome via pull request.