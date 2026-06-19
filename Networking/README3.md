# Computer Networking — Scenario-Based MCQs

A set of 50 scenario-based multiple-choice questions covering core computer networking concepts: network types, addressing, OSI/TCP-IP models, routing, transport protocols, DNS/DHCP, and diagnostics. Each question includes the correct answer and a detailed explanation.

---

## How to use this repo

- Try to answer each question before checking the solution.
- Explanations reference the underlying concept so you understand *why*, not just *what*.
- Great for interview prep, networking course revision, or CCNA/Network+ fundamentals practice.

---

## Table of Contents

1. [Q1–Q10: Network Basics, Types, IP/MAC Addressing](#q1-q10)
2. [Q11–Q20: OSI/TCP-IP Models, TCP/UDP, Handshakes](#q11-q20)
3. [Q21–Q30: ARP, Subnetting, CIDR, Hub/Switch/Router](#q21-q30)
4. [Q31–Q40: Gateway, NAT, DHCP, Unicast/Multicast/Broadcast](#q31-q40)
5. [Q41–Q50: ICMP, TTL, VLAN, Routing, Congestion/Flow Control, Sockets, CDN](#q41-q50)

---

<a id="q1-q10"></a>
## Q1–Q10: Network Basics, Types, IP/MAC Addressing

### Q1
A startup has 50 employees in a single office floor in Bengaluru. They want all computers to share two printers and a local file server, with very high speed and low error rates. Which type of network best fits this requirement?

**A.** WAN
**B.** MAN
**C.** LAN
**D.** PAN (Personal Area Network spanning the city)

**Answer: C — LAN**

**Explanation:** A LAN (Local Area Network) is designed exactly for this use case — a single building or floor, high speed (1 Gbps–100 Gbps+), low error rates, and private ownership. A WAN would be overkill and far slower; a MAN is meant for city-wide spans, not a single office.

---

### Q2
A bank wants to connect its headquarters with 12 branches spread across Mumbai using a dedicated high-speed fiber ring, without relying on satellite or undersea cables. What type of network is this?

**A.** LAN
**B.** MAN
**C.** WAN
**D.** Internet backbone

**Answer: B — MAN**

**Explanation:** A MAN (Metropolitan Area Network) spans a town or city — exactly the scope described (multiple branches within one city, connected via city-wide fiber). WAN would apply only if branches were in different countries/continents.

---

### Q3
A multinational company links its New York, London, Tokyo, and Sydney offices using undersea fiber-optic cables and satellite links. What kind of network connects these offices?

**A.** LAN
**B.** MAN
**C.** WAN
**D.** VLAN

**Answer: C — WAN**

**Explanation:** WAN (Wide Area Network) connects geographically dispersed locations across countries/continents, typically using long-haul technologies like undersea cables and satellites — matching this scenario exactly.

---

### Q4
You are sending a large 2 GB video file to a friend over the internet. Midway through the transfer, your Wi-Fi briefly drops one small piece of data. Why doesn't the entire 2 GB file need to be resent from scratch?

**A.** Because the file is sent as a single unbroken stream
**B.** Because the file is split into packets, each with sequence numbers, so only the missing packet needs retransmission
**C.** Because routers automatically repair corrupted files
**D.** Because MAC addresses guarantee delivery

**Answer: B**

**Explanation:** Files are broken into packets, each carrying a header with sequence numbers. If one packet is lost, only that specific packet (identified by its sequence number) needs to be retransmitted — not the entire file. This is a core reason for packetization.

---

### Q5
Inside a single office LAN, Switch A needs to deliver an Ethernet frame to the exact computer it's intended for, among 30 connected machines. What address does the switch use to make this decision?

**A.** IP address
**B.** MAC address
**C.** Port number
**D.** Domain name

**Answer: B — MAC address**

**Explanation:** Switches operate at Layer 2 (Data Link Layer) and forward frames based on the **MAC address** stored in their MAC address table — matching the physical hardware address to the correct switch port. IP addresses are used by routers (Layer 3), not switches.

---

### Q6
You move your work laptop from your company's New York office to the London branch. Your MAC address stays exactly the same, but your IP address changes from a New York range to a London range. Why?

**A.** MAC addresses are reassigned by routers; IP addresses are burned into hardware
**B.** MAC addresses are permanent hardware identifiers; IP addresses are logical and depend on network location
**C.** IP addresses never change under any circumstances
**D.** MAC addresses are randomly regenerated every time you connect to Wi-Fi

**Answer: B**

**Explanation:** The MAC address is burned into the NIC by the manufacturer and never changes. The IP address is a logical address assigned based on which network you're connected to — so it changes when you move to a different network/location, exactly as described in the New York vs. London desk-number analogy.

---

### Q7
A web server hosting a popular e-commerce site needs to be reachable from anywhere in the world via the public internet. What must it have?

**A.** Only a private IP address
**B.** Only a MAC address
**C.** A public IP address
**D.** A subnet mask alone, with no IP address

**Answer: C — A public IP address**

**Explanation:** Public-facing servers (like Google or Netflix) require a public IP address so that browsers anywhere on the internet can locate and route requests to them. Private IPs are only routable within a local network.

---

### Q8
Your home router assigns private IP addresses like `192.168.1.5` to your phone, laptop, and smart TV. These devices can talk to each other directly, but none of them is reachable directly from the public internet. What best explains this?

**A.** Private IP addresses are only valid within the local network and aren't globally routable
**B.** Private IPs are actually public IPs in disguise
**C.** MAC addresses block internet access
**D.** Routers cannot assign IP addresses at all

**Answer: A**

**Explanation:** Private IP ranges (like `192.168.x.x`) are reserved for use inside local networks only. They allow devices to communicate locally, but routers use NAT to translate this traffic before it goes out to the public internet — private IPs themselves are never directly routable on the internet.

---

### Q9
A network engineer says, "IPv4 gives us about 4.3 billion addresses, but IPv6 gives us a virtually inexhaustible supply." Why was IPv6 introduced?

**A.** To make IP addresses shorter and easier to remember
**B.** To solve the global shortage of available IPv4 addresses as billions of new devices came online
**C.** To remove the need for routers
**D.** To replace MAC addresses entirely

**Answer: B**

**Explanation:** IPv4's 32-bit address space (~4.3 billion addresses) became insufficient as smartphones, laptops, servers, and IoT devices exploded in number. IPv6's 128-bit address space (~3.4×10³⁸ addresses) was introduced specifically to solve this exhaustion problem.

---

### Q10
A company's IT helpdesk gets a ticket: "My laptop can print to the local printer just fine, but I can't open any websites." The technician checks and finds the laptop has a valid IP address and subnet mask, but no other network-layer configuration. What is most likely missing?

**A.** A MAC address
**B.** A default gateway / router configuration
**C.** A working printer driver
**D.** A working power cable

**Answer: B**

**Explanation:** The laptop can reach the printer because it's on the same local subnet — but reaching the wider internet (websites outside the local network) requires a default gateway to forward traffic to the router. Missing gateway configuration is a classic cause of "local works, internet doesn't" symptoms.

---

<a id="q11-q20"></a>
## Q11–Q20: OSI/TCP-IP Models, TCP/UDP, Handshakes

### Q11
A network technician is troubleshooting a "site won't load" issue. They start by checking if the Ethernet cable is plugged in and the link light is on, before checking any software settings. Which OSI layer are they starting their troubleshooting at?

**A.** Layer 7 (Application)
**B.** Layer 4 (Transport)
**C.** Layer 1 (Physical)
**D.** Layer 3 (Network)

**Answer: C — Layer 1 (Physical)**

**Explanation:** Checking cables, link lights, and physical connectivity is a Layer 1 (Physical Layer) check. The OSI model encourages a "bottom-up" troubleshooting approach: rule out physical issues before debugging higher-layer software/application issues.

---

### Q12
A developer is building an app that needs to compress data and encrypt it with TLS before sending it over the network. Which OSI layer handles compression and encryption/decryption?

**A.** Layer 5 (Session)
**B.** Layer 6 (Presentation)
**C.** Layer 3 (Network)
**D.** Layer 2 (Data Link)

**Answer: B — Layer 6 (Presentation)**

**Explanation:** The Presentation Layer handles syntax translation, data compression, and encryption/decryption (e.g., SSL/TLS) — preparing data for the Application Layer above it.

---

### Q13
A video conferencing app needs to keep a call session open for the full duration of a meeting, and gracefully close it when the meeting ends. Which OSI layer is primarily responsible for this?

**A.** Layer 5 (Session)
**B.** Layer 4 (Transport)
**C.** Layer 2 (Data Link)
**D.** Layer 7 (Application)

**Answer: A — Layer 5 (Session)**

**Explanation:** The Session Layer manages opening, maintaining, and closing communication sessions between two devices — exactly matching the requirement to keep a call session alive and close it cleanly afterward.

---

### Q14
You're designing a real-time multiplayer game where a 50-millisecond delay ruins the player experience, and losing the occasional position update isn't a big deal — a newer update will arrive shortly anyway. Which transport protocol should you choose?

**A.** TCP, because reliability matters most
**B.** UDP, because speed matters more than guaranteed delivery
**C.** ICMP, because it's used for real-time games
**D.** ARP, because it's the fastest protocol

**Answer: B — UDP**

**Explanation:** UDP is connectionless and skips handshakes, flow control, and retransmission — making it ideal for real-time applications like gaming and VoIP where speed matters more than perfect reliability, and where a lost packet quickly becomes irrelevant.

---

### Q15
You're building a banking application where every transaction byte must arrive completely intact and in the correct order, even if it takes slightly longer. Which transport protocol fits best?

**A.** UDP, because it's faster
**B.** TCP, because it guarantees reliable, ordered delivery
**C.** ICMP, because it's used for financial data
**D.** DNS, because it resolves transaction IDs

**Answer: B — TCP**

**Explanation:** TCP is connection-oriented and guarantees reliable, in-order delivery through acknowledgments and retransmissions — essential for financial transactions where data integrity cannot be compromised, even at the cost of some speed.

---

### Q16
Before a browser and a web server can exchange any HTTP data, they perform an exchange of SYN, SYN-ACK, and ACK packets. What is this process called, and what does it establish?

**A.** DNS resolution; it finds the IP address
**B.** The TCP three-way handshake; it establishes a reliable connection and synchronizes sequence numbers
**C.** ARP broadcast; it finds the MAC address
**D.** NAT translation; it hides the private IP

**Answer: B**

**Explanation:** The SYN → SYN-ACK → ACK exchange is the TCP three-way handshake. It confirms both devices are ready to communicate and synchronizes initial sequence numbers so data can be reliably tracked and reassembled in order.

---

### Q17
A client sends a SYN packet with sequence number `Seq=500` to a server. According to the TCP handshake process, what should the server's SYN-ACK response contain?

**A.** `Ack=500`, with no SYN flag
**B.** `Ack=501` and its own sequence number, e.g. `Seq=900`
**C.** `Ack=0` and `Seq=500`
**D.** No acknowledgment number at all

**Answer: B**

**Explanation:** In step 2 of the handshake, the server acknowledges the client's sequence number by adding 1 (`Ack = 500 + 1 = 501`) and includes its own initial sequence number (e.g., `Seq=900`) as part of the SYN-ACK packet.

---

### Q18
An organization is replacing legacy hardware and needs a model that maps cleanly onto the actual protocols running the modern internet (HTTP, TCP, IP, Ethernet) rather than a purely theoretical 7-layer reference. Which model are they likely adopting for practical implementation?

**A.** OSI model
**B.** TCP/IP model
**C.** ISO model
**D.** CIDR model

**Answer: B — TCP/IP model**

**Explanation:** The TCP/IP model was built directly around real, working protocols (unlike OSI, which is a theoretical standard developed before many modern protocols existed). It compresses OSI's 7 layers into 4 practical layers and is the actual backbone of the modern internet.

---

### Q19
In the TCP/IP model, a single "Application Layer" handles tasks that OSI splits across three separate layers. Which three OSI layers does the TCP/IP Application Layer combine?

**A.** Physical, Data Link, Network
**B.** Network, Transport, Session
**C.** Session, Presentation, Application
**D.** Data Link, Transport, Application

**Answer: C**

**Explanation:** The TCP/IP model merges OSI's Layer 5 (Session), Layer 6 (Presentation), and Layer 7 (Application) into one combined Application Layer — handling user interface, formatting/encryption, and session control together.

---

### Q20
A network engineer needs to download a 10 MB software update and wants zero corrupted bytes, even if it takes a bit longer. Which factor below is a built-in TCP feature that directly supports this requirement?

**A.** Connectionless transmission
**B.** Flow control and error control with retransmission of lost segments
**C.** No handshake required
**D.** No sequence numbers used

**Answer: B**

**Explanation:** TCP's flow control and error control mechanisms detect lost or corrupted segments (via sequence numbers and acknowledgments) and retransmit them automatically — ensuring the downloaded file arrives complete and uncorrupted.

---

<a id="q21-q30"></a>
## Q21–Q30: ARP, Subnetting, CIDR, Hub/Switch/Router

### Q21
Computer A on a LAN knows the IP address of Computer B (`192.168.1.50`) but has no idea what its MAC address is, so it can't build an Ethernet frame to reach it directly. What protocol resolves this?

**A.** DNS
**B.** DHCP
**C.** ARP
**D.** NAT

**Answer: C — ARP (Address Resolution Protocol)**

**Explanation:** ARP maps a known IP address to its corresponding MAC address on a local network. Computer A would broadcast an ARP Request ("Who has 192.168.1.50?") and Computer B would reply directly with its MAC address.

---

### Q22
When Device A broadcasts an ARP request asking "Who has IP `192.168.1.50`?", what do all the *other* devices on the LAN (that don't own that IP) do?

**A.** They all reply with their own MAC addresses
**B.** They forward the request to the internet
**C.** They silently drop/ignore the broadcast since the IP doesn't match theirs
**D.** They crash due to the broadcast

**Answer: C**

**Explanation:** Every device on the LAN receives the ARP broadcast, but only the device whose IP matches the request responds (via unicast ARP Reply). All other devices simply ignore it since the destination IP doesn't match their own.

---

### Q23
A company is assigned the network `192.168.1.0` with subnet mask `255.255.255.0` (254 usable hosts). They want to split it into two isolated subnets of 126 usable hosts each — one for Finance, one for Engineering. What subnet mask should they apply?

**A.** `255.255.255.0`
**B.** `255.255.255.128`
**C.** `255.255.0.0`
**D.** `255.255.255.255`

**Answer: B — `255.255.255.128`**

**Explanation:** Changing the mask from `/24` (`255.255.255.0`) to `/25` (`255.255.255.128`) borrows 1 host bit for the network portion, splitting the original block into two subnets: `192.168.1.0–192.168.1.127` and `192.168.1.128–192.168.1.255`.

---

### Q24
A company's guest Wi-Fi network keeps flooding all connected devices with broadcast traffic from hundreds of users, slowing things down and creating a security risk for sensitive devices on the same network. What technique should the network admin use to isolate broadcast traffic and improve security?

**A.** Replace the router with a hub
**B.** Subnetting (creating smaller broadcast domains)
**C.** Disable DNS
**D.** Increase the TTL value

**Answer: B — Subnetting**

**Explanation:** Subnetting divides a large network into smaller segments, each with its own limited broadcast domain. This contains broadcast traffic within each subnet and lets admins isolate sensitive devices (like payroll servers) from guest traffic.

---

### Q25
A mid-sized company needs exactly 1,000 usable IP addresses for its internal network. Using classful addressing (Class C = 254 hosts, Class B = 65,536 hosts), they'd be forced to waste over 63,000 addresses with a Class B block. What modern addressing method solves this by allowing precisely-sized allocations like a `/22` block (1,024 addresses)?

**A.** NAT
**B.** CIDR (Classless Inter-Domain Routing)
**C.** ARP
**D.** VLAN tagging

**Answer: B — CIDR**

**Explanation:** CIDR replaced rigid classful addressing with flexible slash notation, allowing exact-fit allocations (e.g., `/22` = 1,024 addresses) instead of forcing organizations into oversized, wasteful classful blocks.

---

### Q26
A `/22` network block is assigned to a company. How many usable host addresses does this provide?

**A.** 256
**B.** 512
**C.** 1,024
**D.** 2,048

**Answer: C — 1,024**

**Explanation:** A `/22` mask uses 22 bits for the network, leaving 10 bits for hosts: 2¹⁰ = 1,024 total addresses (some practical deployments reserve a couple for network/broadcast addresses, but the raw calculation is 1,024).

---

### Q27
An old office still uses a legacy device that, upon receiving a signal on one port, blindly copies and blasts it out of every other port — regardless of which device the data is actually meant for. What device is this, and at what OSI layer does it operate?

**A.** Switch, Layer 2
**B.** Hub, Layer 1
**C.** Router, Layer 3
**D.** Gateway, Layer 7

**Answer: B — Hub, Layer 1**

**Explanation:** A hub is a non-intelligent Layer 1 device. It has no concept of addresses (MAC or IP) and simply duplicates incoming electrical signals to every connected port, causing unnecessary traffic and collisions — which is why hubs are now obsolete.

---

### Q28
A company wants a device that intelligently sends data only to the specific port where the destination device is physically connected, based on its hardware address, rather than flooding every port. What should they use?

**A.** Hub
**B.** Switch
**C.** Repeater
**D.** Modem

**Answer: B — Switch**

**Explanation:** A switch operates at Layer 2 and maintains a MAC address table, allowing it to forward frames only to the specific port where the destination device resides — unlike a hub, which blindly floods all ports.

---

### Q29
An employee at Office A wants to send data to a friend at a completely different company across town, on a totally separate network. Which device is necessary to get the data from one network to a different external network?

**A.** Switch
**B.** Hub
**C.** Router
**D.** NIC alone, with no other device

**Answer: C — Router**

**Explanation:** Routers operate at Layer 3 and are specifically designed to forward packets *between* different networks based on IP addresses — exactly what's needed to reach a separate company's network across town, unlike switches/hubs which only operate within a single LAN.

---

### Q30
A network admin says: "Hubs use half-duplex and create one shared collision domain, switches give each port its own collision domain and run full-duplex, and routers connect entirely separate broadcast domains." A junior engineer asks which device should be used to reduce collisions on a busy 40-device office LAN. What's the best answer?

**A.** Replace the switch with a hub for simplicity
**B.** Use a switch — each port gets its own collision domain, eliminating shared-medium collisions
**C.** Use a router instead of a switch within the same LAN
**D.** Collisions can't be reduced; add more cables

**Answer: B**

**Explanation:** Switches give every connected port its own separate collision domain and operate full-duplex (simultaneous send/receive), virtually eliminating the collisions that plague hub-based shared-medium networks.

---

<a id="q31-q40"></a>
## Q31–Q40: Gateway, NAT, DHCP, Unicast/Multicast/Broadcast

### Q31
A laptop at `192.168.1.15` wants to reach `google.com`, which resolves to an external IP. The laptop checks its subnet mask and realizes the destination is NOT on its local subnet. What does it do next?

**A.** Drop the packet permanently
**B.** Broadcast an ARP request to the entire internet
**C.** Forward the packet to its configured default gateway
**D.** Wait indefinitely for DNS to resolve a local route

**Answer: C — Forward the packet to its default gateway**

**Explanation:** When the destination IP isn't on the local subnet, the device automatically routes the packet to its default gateway (typically the local router), which then forwards it onward toward the internet.

---

### Q32
A home network has 5 devices, but the ISP has only provided 1 public IP address. All 5 devices can still browse the internet simultaneously. What technology makes this possible?

**A.** DNS
**B.** NAT (Network Address Translation)
**C.** ARP
**D.** VLAN

**Answer: B — NAT**

**Explanation:** NAT (specifically PAT/NAT Overload) allows multiple devices using private IPs to share a single public IP address by tracking each device's session using unique source port numbers in the router's NAT translation table.

---

### Q33
A security analyst notes that external attackers on the internet cannot directly see or connect to internal devices on a home network, even though those devices actively browse the web. What is the *side benefit* of NAT that explains this?

**A.** NAT encrypts all traffic
**B.** NAT hides internal private IPs behind a single public-facing IP, acting as a natural security barrier
**C.** NAT blocks all internet access
**D.** NAT assigns MAC addresses randomly

**Answer: B**

**Explanation:** Because internal devices use private IPs that are never exposed externally, external parties only ever see the router's single public IP — providing a layer of obfuscation/security as a natural side effect of NAT, in addition to its primary IPv4-conservation purpose.

---

### Q34
A laptop's source port is `4001` when it sends a request out through a NAT router. The router translates it to public IP `203.0.113.50` with port `50001`. When the response comes back addressed to `203.0.113.50:50001`, how does the router know to forward it specifically to the laptop?

**A.** It guesses based on traffic volume
**B.** It checks its NAT Translation Table mapping `203.0.113.50:50001` back to `192.168.1.15:4001`
**C.** It broadcasts the response to all devices
**D.** It uses DNS to look up the laptop

**Answer: B**

**Explanation:** The router maintains a NAT Translation Table logging each private-IP-and-port-to-public-IP-and-port mapping. When a response arrives, it looks up the matching entry and forwards the data to the correct internal device and port.

---

### Q35
A brand-new laptop connects to a coffee shop's Wi-Fi for the first time. Within seconds, it automatically receives an IP address, subnet mask, default gateway, and DNS server — with zero manual configuration. What protocol made this automatic?

**A.** ARP
**B.** DHCP
**C.** NAT
**D.** ICMP

**Answer: B — DHCP**

**Explanation:** DHCP (Dynamic Host Configuration Protocol) automatically assigns IP addresses and network configuration to devices joining a network, eliminating the need for manual setup — exactly what's happening when a device instantly gets online at a coffee shop.

---

### Q36
In the DHCP DORA process, after a client broadcasts a DHCPDISCOVER and receives a DHCPOFFER from a server, what must the client do next, and why is this step necessary even if only one DHCP server exists?

**A.** Immediately start using the offered IP without confirming, since DISCOVER already finalizes it
**B.** Send a DHCPREQUEST to explicitly accept the offer, since multiple DHCP servers could technically respond
**C.** Send another DHCPDISCOVER to double-check
**D.** Nothing further is needed; DORA only has 2 steps

**Answer: B**

**Explanation:** The client must broadcast a DHCPREQUEST explicitly accepting a specific offer. This step exists because, in networks with multiple DHCP servers, several offers could arrive — the REQUEST step makes clear which offer the client is accepting, and lets other servers withdraw their unused offers.

---

### Q37
A company streams quarterly earnings video to exactly the trading desks subscribed to that feed — not to every device on the network, and not to just one device either. Which transmission method is this?

**A.** Unicast
**B.** Broadcast
**C.** Multicast
**D.** Anycast

**Answer: C — Multicast**

**Explanation:** Multicast is one-to-many communication, delivering data only to devices that have specifically subscribed to a multicast group (via IGMP) — efficient for use cases like streaming to a defined audience, avoiding both the inefficiency of broadcast and the redundancy of multiple unicasts.

---

### Q38
A new laptop joining a network has no IP address yet and needs to find a DHCP server, but doesn't know its address. It sends its DHCPDISCOVER message to every device on the local network simultaneously. What type of transmission is this?

**A.** Unicast
**B.** Multicast
**C.** Broadcast
**D.** Point-to-point

**Answer: C — Broadcast**

**Explanation:** DHCPDISCOVER is sent as a broadcast (to `255.255.255.255`) because the client doesn't yet know any specific server's address — broadcast ensures every device on the local segment, including any DHCP server, receives the request.

---

### Q39
A school administrator wants to make a building-wide emergency fire drill announcement that every single person in the building hears, with no exceptions. Networking-wise, this maps most closely to which transmission type, and why is it normally restricted to local networks?

**A.** Unicast — because it's targeted to specific people
**B.** Multicast — because it only reaches subscribed groups
**C.** Broadcast — because it must reach everyone, and routers block broadcast traffic from crossing into other networks to prevent overload
**D.** Anycast — because it reaches the nearest person only

**Answer: C**

**Explanation:** This matches broadcast (one-to-all within a domain). Routers intentionally block broadcast traffic from propagating beyond the local network/broadcast domain — otherwise broadcasts would flood the entire internet, similar to how a fire alarm stays within one building rather than blaring across the whole city.

---

### Q40
An IPv6-only network has completely removed traditional broadcast traffic. What replaces it for tasks like local device discovery?

**A.** Nothing; IPv6 simply has no equivalent mechanism
**B.** Link-Local Multicast
**C.** Increased unicast flooding
**D.** Mandatory NAT

**Answer: B — Link-Local Multicast**

**Explanation:** IPv6 eliminates broadcast entirely and replaces those use cases (like ARP-style discovery) with efficient Link-Local Multicast, reducing unnecessary traffic to devices that aren't interested in a given message.

---

<a id="q41-q50"></a>
## Q41–Q50: ICMP, TTL, VLAN, Routing, Congestion/Flow Control, Sockets, CDN

### Q41
A network engineer runs `ping 8.8.8.8` and watches for replies to confirm whether the destination is reachable and to measure round-trip time. What protocol underlies the `ping` command?

**A.** TCP
**B.** UDP
**C.** ICMP
**D.** ARP

**Answer: C — ICMP**

**Explanation:** `ping` relies entirely on ICMP, sending an ICMP Type 8 (Echo Request) and listening for an ICMP Type 0 (Echo Reply) to measure reachability and round-trip time. It does not transport application data like TCP or UDP.

---

### Q42
Two misconfigured routers accidentally create a routing loop, where a packet keeps bouncing endlessly between them. What mechanism in the IP header ensures this packet eventually gets discarded instead of looping forever and consuming bandwidth?

**A.** Source port number
**B.** TTL (Time to Live)
**C.** Sequence number
**D.** Subnet mask

**Answer: B — TTL**

**Explanation:** The TTL field is decremented by 1 at every router hop. Once it hits 0, the next router drops the packet and sends back an ICMP "Time Exceeded" message — preventing packets stuck in routing loops from circulating forever and overwhelming the network.

---

### Q43
A network engineer runs `traceroute` to a destination server and deliberately wants to see every router along the path. How does `traceroute` exploit the TTL field to achieve this?

**A.** It sets TTL to an extremely high fixed value for every packet
**B.** It sends successive packets with TTL=1, then TTL=2, then TTL=3, etc., causing each router in turn to drop the packet and reply with an ICMP Time Exceeded message
**C.** It disables TTL checking entirely
**D.** It uses ARP broadcasts instead of TTL

**Answer: B**

**Explanation:** Traceroute intentionally sends packets with incrementing TTL values (starting at 1). Each router along the path decrements TTL to 0 at a different "distance," drops the packet, and sends back an ICMP Time Exceeded reply — letting traceroute map the entire path hop by hop.

---

### Q44
A co-working space has Company A and Company B sharing the exact same physical switches and cabling, but their traffic must remain completely isolated at Layer 2 so neither company can see the other's local broadcasts. What should the network admin configure?

**A.** Two separate routers only, with no other config
**B.** VLANs (Virtual LANs) using 802.1Q tagging
**C.** A single shared hub
**D.** Static ARP entries for every device

**Answer: B — VLANs**

**Explanation:** VLANs logically partition a single physical switch infrastructure into multiple isolated broadcast domains, ensuring devices in different VLANs (e.g., Company A vs. Company B) cannot see each other's Layer 2 traffic, even while sharing the same physical hardware.

---

### Q45
Company A is in VLAN 100 and Company B is in VLAN 200 on the same physical switch. Company A's laptop needs to send data to a printer in VLAN 200. What is required to make this communication possible?

**A.** Nothing; VLANs always allow direct Layer 2 communication
**B.** A Layer 3 device (router or Layer 3 switch) to perform inter-VLAN routing
**C.** Disabling VLAN tagging temporarily
**D.** Switching both devices to use hubs instead

**Answer: B**

**Explanation:** Devices in different VLANs are isolated at Layer 2 by design. To communicate, traffic must be routed at Layer 3 — via a router or Layer 3 switch performing inter-VLAN routing — bridging the two otherwise-separate broadcast domains.

---

### Q46
A small branch office has only one possible exit point to the internet, and the IT admin manually configures: "send all non-local traffic out through this one interface." This configuration never changes automatically, even if that link fails. What routing method is this?

**A.** Dynamic routing
**B.** Static routing
**C.** Multicast routing
**D.** ARP routing

**Answer: B — Static routing**

**Explanation:** Static routing involves manually configured, fixed paths that don't adapt automatically to network changes or failures — appropriate here since there's only a single, predictable exit point and no need for automatic rerouting.

---

### Q47
The global internet backbone, where ISPs constantly route around failed undersea cables and shifting traffic conditions, relies on routers automatically exchanging path information and recalculating optimal routes using protocols like BGP and OSPF. What type of routing is this?

**A.** Static routing
**B.** Default routing
**C.** Dynamic routing
**D.** Manual routing

**Answer: C — Dynamic routing**

**Explanation:** Dynamic routing uses protocols (BGP, OSPF, EIGRP, RIP) that let routers automatically discover networks, exchange live path data, and recompute optimal routes — essential for large, constantly-changing infrastructures like the global internet backbone.

---

### Q48
During a large file download, multiple routers along the path become congested, and several packets are dropped. The sender notices this congestion and, instead of resetting its transmission speed all the way back to the slow start phase, cuts its window size in half and resumes a careful linear increase. What TCP mechanism does this describe?

**A.** Flow control's sliding window
**B.** Fast Recovery (within congestion control)
**C.** The TCP three-way handshake
**D.** NAT translation

**Answer: B — Fast Recovery**

**Explanation:** Fast Recovery is triggered after Fast Retransmit detects a lost packet via duplicate ACKs. Instead of dropping all the way back to Slow Start, TCP halves the Slow Start Threshold (Ssthresh), sets the congestion window to that new value, and resumes Congestion Avoidance (linear growth) — avoiding a full reset.

---

### Q49
A fast cloud server is sending data to an older, slower smartphone. The smartphone's buffer fills up completely, so it sends back a TCP packet announcing a Receive Window of 0. What does the sender do?

**A.** Ignore the window size and keep sending at full speed
**B.** Immediately halt transmission, then periodically send small Window Probe segments to check if the buffer has cleared
**C.** Permanently terminate the connection
**D.** Switch to UDP automatically

**Answer: B**

**Explanation:** A Receive Window of 0 signals the receiver's buffer is full. Per TCP's flow control sliding window mechanism, the sender pauses transmission entirely and sends periodic Window Probe segments to check when the receiver has freed up buffer space, resuming once the window reopens.

---

### Q50
A streaming company wants users in Sydney to load video content quickly without every request traveling all the way to a single origin server in New York. They deploy cached copies of static content across regional edge servers worldwide. What is this infrastructure called, and what does it do when a Sydney edge server doesn't yet have the requested file cached?

**A.** A VPN; it always blocks the request
**B.** A CDN (Content Delivery Network); on a cache miss, the edge server fetches the file from the origin server, caches it locally, and serves it to the user
**C.** NAT; it translates the IP address only
**D.** DHCP; it assigns the user a new IP

**Answer: B — CDN**

**Explanation:** A CDN distributes cached copies of static content across globally distributed edge servers (PoPs). On a "cache hit," content is served instantly from the nearby edge server. On a "cache miss," the edge server fetches a fresh copy from the origin server, caches it for future users, and delivers it — reducing latency and offloading the origin server.

---

## License

Feel free to use, fork, and adapt this question set for personal study or educational purposes.