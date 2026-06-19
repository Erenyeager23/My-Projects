# 50 Networking MCQs — Cisco / Juniper / Arista / Aruba Interview Prep

A self-contained set of 50 multiple-choice questions covering core networking fundamentals: OSI/TCP-IP models, IP addressing, routing, switching, DNS, DHCP, ARP, TCP/UDP internals, and troubleshooting. Each question includes the correct answer and a short explanation.

> Difficulty mix: foundational → intermediate → "gotcha" questions commonly asked in network engineering / TAC / SDET interviews at networking product companies.

---

## Table of Contents
- [Section 1: Network Fundamentals & Types (Q1–Q6)](#section-1-network-fundamentals--types-q1q6)
- [Section 2: OSI & TCP/IP Models (Q7–Q12)](#section-2-osi--tcpip-models-q7q12)
- [Section 3: IP Addressing, Subnetting & CIDR (Q13–Q22)](#section-3-ip-addressing-subnetting--cidr-q13q22)
- [Section 4: Switching, ARP & MAC (Q23–Q28)](#section-4-switching-arp--mac-q23q28)
- [Section 5: Routing (Q29–Q34)](#section-5-routing-q29q34)
- [Section 6: TCP, UDP & Transport Layer (Q35–Q43)](#section-6-tcp-udp--transport-layer-q35q43)
- [Section 7: DNS, DHCP & NAT (Q44–Q47)](#section-7-dns-dhcp--nat-q44q47)
- [Section 8: Diagnostics & Troubleshooting (Q48–Q50)](#section-8-diagnostics--troubleshooting-q48q50)

---

## Section 1: Network Fundamentals & Types (Q1–Q6)

### Q1. What is the primary purpose of breaking data into packets before transmission?

A) To make the data unreadable to attackers
B) To allow recovery from errors without resending the entire file, and to share the medium fairly
C) To increase the total amount of data sent
D) To avoid the need for IP addresses

**Answer: B**

**Explanation:** Packetization allows large transfers to be broken into manageable chunks so that if one chunk is lost or corrupted, only that chunk needs retransmission — not the entire file. It also allows multiple flows to interleave fairly over a shared medium.

---

### Q2. Which network type typically spans a single building or campus with the highest data rates and lowest error rates?

A) WAN
B) MAN
C) LAN
D) PAN

**Answer: C**

**Explanation:** A LAN (Local Area Network) covers a small geographic area (home, office, building) and offers the highest speeds (1 Gbps–100 Gbps+) with the lowest error rates because cabling runs are short and privately controlled.

---

### Q3. A bank connects its NYC headquarters to 12 branches across the city using a dedicated fiber ring. This is an example of a:

A) LAN
B) MAN
C) WAN
D) VPN

**Answer: B**

**Explanation:** A MAN (Metropolitan Area Network) spans a city or large campus — exactly the scope of interconnecting branches across a single metropolitan area using fiber infrastructure.

---

### Q4. Which of the following best describes a WAN?

A) A network confined to one office floor
B) A network connecting geographically dispersed regions or countries, often over public/shared infrastructure
C) A network that only uses wireless radio waves
D) A network with guaranteed zero latency

**Answer: B**

**Explanation:** WANs (Wide Area Networks) connect distant locations — across cities, countries, or continents — using long-haul links like leased lines, MPLS, or the public Internet, generally with higher latency and variable speeds compared to LANs.

---

### Q5. Which statement about ownership of network types is generally true?

A) LANs are always publicly owned by telecom providers
B) WANs are typically privately owned by a single individual
C) LANs are typically privately owned by a single organization, while WANs often rely on shared/public telecom infrastructure
D) MANs cannot be owned by private consortiums

**Answer: C**

**Explanation:** LANs are usually owned and controlled entirely by one organization (e.g., a company's office network). WANs, by contrast, typically traverse infrastructure owned by multiple ISPs/telecom carriers and the public Internet backbone.

---

### Q6. In a network without centralized resource sharing, what is the most direct business cost mentioned as a consequence?

A) Increased IP address shortage
B) Need to buy duplicate hardware (e.g., one printer per employee) instead of sharing centrally
C) Faster network speeds
D) Reduced need for DNS

**Answer: B**

**Explanation:** Without a network, resources like printers can't be shared, forcing organizations to buy redundant hardware for every user instead of a few shared devices accessible to everyone over the network.

---

## Section 2: OSI & TCP/IP Models (Q7–Q12)

### Q7. Which OSI layer is responsible for logical addressing and determining the best path between networks?

A) Layer 2 – Data Link
B) Layer 3 – Network
C) Layer 4 – Transport
D) Layer 7 – Application

**Answer: B**

**Explanation:** Layer 3 (Network Layer) handles IP addressing and routing — finding the best path for packets to travel between different networks. Routers operate primarily at this layer.

---

### Q8. Which OSI layer converts frames into raw bits and deals with physical media like copper, fiber, and radio waves?

A) Layer 1 – Physical
B) Layer 2 – Data Link
C) Layer 3 – Network
D) Layer 4 – Transport

**Answer: A**

**Explanation:** The Physical Layer (Layer 1) is concerned purely with the transmission of raw binary bits as electrical signals, light pulses, or radio waves across the physical medium — cables, NICs, hubs, repeaters.

---

### Q9. A Layer 2 switch makes forwarding decisions based on which address type?

A) IP address
B) MAC address
C) Port number
D) DNS name

**Answer: B**

**Explanation:** Switches operate at Layer 2 (Data Link) and forward Ethernet frames based on destination MAC addresses, using a MAC address table to determine which physical port to send traffic out of.

---

### Q10. Which combination correctly maps OSI layers to the TCP/IP model's "Application" layer?

A) Only Layer 7
B) Layers 5, 6, and 7 (Session, Presentation, Application)
C) Layers 3 and 4
D) Layer 1 only

**Answer: B**

**Explanation:** The TCP/IP model condenses OSI's Session, Presentation, and Application layers (5, 6, 7) into a single Application layer, since real-world protocols like HTTP and DNS handle session state and data formatting directly.

---

### Q11. What is the correct order of encapsulation as data travels DOWN the OSI stack during transmission?

A) Bits → Frames → Packets → Segments → Data
B) Data → Segments → Packets → Frames → Bits
C) Packets → Data → Bits → Frames → Segments
D) Frames → Bits → Data → Packets → Segments

**Answer: B**

**Explanation:** As data descends the stack: Application data is segmented at Layer 4 (segments), packaged with IP headers at Layer 3 (packets), framed with MAC headers at Layer 2 (frames), and finally converted to raw bits at Layer 1 for transmission.

---

### Q12. Why does the TCP/IP model use only 4 layers instead of OSI's 7?

A) Because TCP/IP doesn't support encryption
B) It was built protocol-first around real, already-existing implementations rather than as a theoretical standard
C) Because routers cannot process more than 4 layers
D) TCP/IP doesn't need a Transport layer

**Answer: B**

**Explanation:** Unlike OSI (a theoretical ISO standard developed before protocols existed), TCP/IP was built by the U.S. DoD/ARPANET around practical, already-functioning protocols — collapsing Session/Presentation/Application into one layer and Physical/Data Link into "Network Access."

---

## Section 3: IP Addressing, Subnetting & CIDR (Q13–Q22)

### Q13. How many bits make up an IPv4 address?

A) 16 bits
B) 32 bits
C) 64 bits
D) 128 bits

**Answer: B**

**Explanation:** IPv4 addresses are 32 bits long, typically displayed as four 8-bit octets in dotted-decimal notation (e.g., `192.168.1.1`), giving roughly 4.3 billion possible addresses.

---

### Q14. What is the main reason IPv6 was introduced?

A) To make addresses easier to type
B) To solve IPv4 address exhaustion with a vastly larger address space
C) To eliminate the need for routers
D) To replace MAC addresses

**Answer: B**

**Explanation:** IPv4's 32-bit space (~4.3 billion addresses) was being exhausted due to explosive growth in internet-connected devices. IPv6's 128-bit space provides approximately 3.4 × 10³⁸ addresses — effectively unlimited for the foreseeable future.

---

### Q15. Given the network `192.168.1.0/24`, what does the `/24` represent?

A) 24 host bits
B) 24 bits reserved for the network portion, leaving 8 bits for hosts
C) The 24th subnet
D) 24 available IP addresses

**Answer: B**

**Explanation:** CIDR slash notation indicates how many of the 32 total bits are fixed as the network prefix. `/24` means the first 24 bits identify the network, leaving the remaining 8 bits (2⁸ = 256, minus network/broadcast = 254 usable) for host addresses.

---

### Q16. An organization needs roughly 1,000 usable host addresses. Which CIDR block fits this most efficiently?

A) /24 (254 hosts)
B) /23 (510 hosts)
C) /22 (1,022 hosts)
D) /16 (65,534 hosts)

**Answer: C**

**Explanation:** A /22 leaves 10 host bits (32-22=10), giving 2¹⁰ = 1,024 total addresses (1,022 usable after network/broadcast) — closely matching the 1,000-host requirement without the massive waste of a /16.

---

### Q17. What is the primary purpose of subnetting?

A) To increase the total number of public IP addresses available globally
B) To divide a large network into smaller segments, limiting broadcast domains and improving security/organization
C) To convert IPv4 addresses into IPv6
D) To assign permanent MAC addresses to devices

**Answer: B**

**Explanation:** Subnetting borrows host bits to create smaller broadcast domains, reducing unnecessary broadcast traffic, enabling security segmentation (e.g., isolating Finance from Guest Wi-Fi), and allowing more efficient address allocation.

---

### Q18. Why was CIDR introduced to replace classful addressing (Class A/B/C)?

A) Classful addressing didn't support subnet masks at all
B) Classful addressing allocated IP blocks in rigid, wasteful chunks (e.g., a Class B for 2,000 hosts wastes 63,000+ addresses)
C) CIDR eliminates the need for routing tables
D) Classful addressing only worked with IPv6

**Answer: B**

**Explanation:** Classful addressing forced organizations into fixed-size blocks (Class A = 16.7M hosts, B = 65,536, C = 254). A company needing 2,000 addresses had to take an entire Class B, wasting over 63,000 addresses. CIDR's variable-length prefixes eliminate this waste.

---

### Q19. Which of these is a valid private IPv4 address range commonly used in home networks?

A) `8.8.8.8`
B) `192.168.1.1`
C) `142.250.190.46`
D) `1.1.1.1`

**Answer: B**

**Explanation:** `192.168.0.0/16` is one of the RFC 1918 reserved private address ranges (along with `10.0.0.0/8` and `172.16.0.0/12`), used internally on LANs and not routable on the public Internet directly.

---

### Q20. What does a subnet mask like `255.255.255.0` tell a device?

A) The device's MAC address
B) Which portion of the IP address is the Network ID vs. the Host ID
C) The device's default gateway
D) The DNS server address

**Answer: B**

**Explanation:** A subnet mask defines the boundary between the network and host portions of an IP address. `255.255.255.0` means the first 24 bits (three octets) are the network ID, and the last 8 bits identify individual hosts.

---

### Q21. How many usable host addresses exist in a `/26` subnet?

A) 64
B) 62
C) 30
D) 126

**Answer: B**

**Explanation:** A /26 leaves 6 host bits (32-26=6), giving 2⁶ = 64 total addresses. Subtracting the network address and broadcast address leaves 62 usable host addresses.

---

### Q22. What is "route aggregation" (supernetting) in the context of CIDR?

A) Splitting one large network into many small subnets
B) Combining multiple smaller network routes into a single, larger routing table entry to reduce table size
C) Assigning multiple MAC addresses to one device
D) A method for encrypting routing tables

**Answer: B**

**Explanation:** Route aggregation lets routers summarize many contiguous smaller networks into one larger CIDR block in their routing tables, preventing core Internet routers from being overwhelmed by an enormous number of individual route entries.

---

## Section 4: Switching, ARP & MAC (Q23–Q28)

### Q24. What type of address is permanently burned into a device's Network Interface Card (NIC) by the manufacturer?

A) IP address
B) MAC address
C) Gateway address
D) Subnet address

**Answer: B**

**Explanation:** The MAC (Media Access Control) address is a hardware-based, physical address assigned by the manufacturer. Unlike an IP address (logical, can change with network location), a MAC address is fixed for the life of the NIC.

---

### Q24. What problem does ARP (Address Resolution Protocol) solve?

A) Translating domain names into IP addresses
B) Mapping a known IP address to its corresponding MAC address on a local network
C) Assigning IP addresses dynamically
D) Encrypting traffic between two hosts

**Answer: B**

**Explanation:** ARP bridges Layer 3 and Layer 2: when a device knows the destination's IP address but needs to build a physical Ethernet frame, it uses ARP to discover the matching MAC address on the local network.

---

### Q25. In the ARP process, what kind of message is an "ARP Request," and what kind is an "ARP Reply"?

A) Both are unicast
B) ARP Request is broadcast; ARP Reply is unicast
C) ARP Request is unicast; ARP Reply is broadcast
D) Both are multicast

**Answer: B**

**Explanation:** The ARP Request is broadcast to every device on the LAN ("Who has this IP?"), since the sender doesn't know who owns it. Only the device matching that IP responds, and it replies directly (unicast) to the requester.

---

### Q26. Why can't a hub be used effectively in a modern, high-traffic LAN?

A) It only works with fiber-optic cable
B) It blindly duplicates all incoming signals to every port, causing collisions and wasted bandwidth (single shared collision domain)
C) It requires IP addresses to function
D) It only supports IPv6

**Answer: B**

**Explanation:** A hub is a "dumb" Layer 1 device with no addressing intelligence — every bit received on one port is repeated out to all other ports, creating one large shared collision domain and degrading performance as more devices are added.

---

### Q27. What does a switch maintain internally to forward frames to the correct port?

A) A routing table
B) A MAC address table (CAM table)
C) A DNS cache
D) An ARP timeout list

**Answer: B**

**Explanation:** A switch builds and maintains a MAC address table (also called a CAM table) that maps MAC addresses to the specific physical port a device is connected to, allowing it to forward frames only to the intended port instead of flooding all ports.

---

### Q28. What is the primary purpose of VLAN segmentation on a switch?

A) To increase the physical number of switch ports
B) To create isolated logical broadcast domains on the same physical switch, improving security and reducing broadcast traffic
C) To assign public IP addresses to internal hosts
D) To replace the need for a default gateway

**Answer: B**

**Explanation:** VLANs (802.1Q tagging) logically partition a single physical switch into multiple isolated broadcast domains, so devices in different VLANs cannot see each other's broadcast traffic at Layer 2 — improving both security and performance — without requiring separate physical hardware.

---

## Section 5: Routing (Q29–Q34)

### Q29. What is the main functional difference between a switch and a router?

A) A switch forwards based on IP addresses; a router forwards based on MAC addresses
B) A switch forwards frames within a LAN using MAC addresses; a router forwards packets between different networks using IP addresses
C) There is no functional difference
D) Routers only work with wireless networks

**Answer: B**

**Explanation:** Switches operate at Layer 2 to move frames within the same local network using MAC addresses. Routers operate at Layer 3, connecting separate networks/broadcast domains and forwarding packets based on destination IP addresses using a routing table.

---

### Q30. What does NAT (Network Address Translation) primarily accomplish?

A) Translates domain names to IP addresses
B) Allows multiple devices with private IP addresses to share a single public IP address for Internet access
C) Encrypts all outbound traffic
D) Assigns MAC addresses to virtual machines

**Answer: B**

**Explanation:** NAT lets many internally-addressed private devices (e.g., `192.168.x.x`) share one public IP when communicating externally, conserving scarce public IPv4 addresses and adding a layer of obfuscation/security since internal hosts aren't directly reachable from outside.

---

### Q31. In PAT (Port Address Translation / NAT Overload), how does a router distinguish between multiple internal devices sharing one public IP?

A) By using different MAC addresses
B) By assigning each internal flow a unique source port number, tracked in a NAT translation table
C) By using different DNS servers per device
D) It cannot distinguish; only one device can use NAT at a time

**Answer: B**

**Explanation:** PAT tracks connections by mapping each internal (private IP + port) pair to a unique (public IP + port) combination in its translation table, allowing many internal hosts to share one public IP simultaneously and routing return traffic back to the correct internal device.

---

### Q32. What is the key difference between static and dynamic routing?

A) Static routing automatically reroutes around failures; dynamic routing does not
B) Static routes are manually configured and fixed; dynamic routes are automatically learned/updated by routing protocols in response to topology changes
C) Dynamic routing only works on LANs
D) Static routing requires more CPU overhead than dynamic routing

**Answer: B**

**Explanation:** Static routes are hardcoded by an administrator and never change unless manually edited — efficient but inflexible. Dynamic routing protocols (OSPF, BGP, EIGRP) let routers exchange topology information and recalculate paths automatically when links fail or change.

---

### Q33. Which routing protocol powers global Internet routing between different Autonomous Systems (ISPs)?

A) DHCP
B) BGP (Border Gateway Protocol)
C) ARP
D) ICMP

**Answer: B**

**Explanation:** BGP is the dynamic routing protocol used to exchange routing information between Autonomous Systems across the global Internet, allowing ISPs to scale and reroute traffic when transoceanic links or major paths fail.

---

### Q34. What is a "default gateway," and why is it needed?

A) The fastest router on a network
B) The local router's IP address that a device sends traffic to when the destination is outside its own subnet
C) A backup DNS server
D) The first IP address in any subnet

**Answer: B**

**Explanation:** When a device determines (via subnet mask comparison) that a destination IP isn't on its local subnet, it forwards the packet to its configured default gateway — typically the local router — which then routes it toward the destination network.

---

## Section 6: TCP, UDP & Transport Layer (Q35–Q43)

### Q35. Which of the following best characterizes TCP vs UDP?

A) TCP is connectionless and unreliable; UDP is connection-oriented and reliable
B) TCP is connection-oriented and reliable (guarantees delivery/order); UDP is connectionless and best-effort (faster, no guarantees)
C) Both TCP and UDP guarantee packet order
D) UDP requires a three-way handshake; TCP does not

**Answer: B**

**Explanation:** TCP establishes a session via a handshake, numbers packets, and retransmits lost data to guarantee reliable, ordered delivery — at the cost of overhead/speed. UDP skips all of this for minimal latency, accepting that some data may be lost or arrive out of order.

---

### Q36. Which application/protocol would most appropriately use UDP instead of TCP?

A) Sending an email via SMTP
B) Downloading a file via FTP
C) A live VoIP call or online multiplayer game
D) Loading a webpage via HTTPS

**Answer: C**

**Explanation:** Real-time applications like VoIP and gaming prioritize low latency over perfect reliability — a dropped audio packet causing a brief glitch is far less disruptive than the delay caused by waiting for TCP retransmission and reordering.

---

### Q37. What are the three steps of the TCP three-way handshake, in order?

A) ACK → SYN → SYN-ACK
B) SYN → SYN-ACK → ACK
C) SYN → ACK → SYN-ACK
D) ACK → ACK → SYN

**Answer: B**

**Explanation:** The client sends SYN (synchronize, proposing an initial sequence number). The server responds with SYN-ACK (acknowledging the client's sequence number and proposing its own). The client completes the handshake with ACK, after which the connection is ESTABLISHED.

---

### Q38. What is the main purpose of the TCP three-way handshake?

A) To encrypt the session
B) To synchronize initial sequence numbers and confirm both endpoints are ready before exchanging data
C) To assign IP addresses to both devices
D) To discover the destination MAC address

**Answer: B**

**Explanation:** Before reliable, ordered data transfer can begin, both sides must agree on initial sequence numbers (used to track and reorder segments) and confirm mutual readiness and resource allocation for the session.

---

### Q39. What is the function of TCP's congestion control mechanism?

A) To prevent a fast sender from overwhelming a slow receiver's buffer
B) To prevent senders from overwhelming the shared network infrastructure (routers/links) and causing congestion collapse
C) To assign IP addresses dynamically
D) To resolve domain names faster

**Answer: B**

**Explanation:** Congestion control (Slow Start, Congestion Avoidance, Fast Retransmit, Fast Recovery) protects the network itself — routers and links — from being overwhelmed by aggregate sender traffic, distinct from flow control which protects only the receiving endpoint.

---

### Q40. How does TCP's "Slow Start" phase behave?

A) It starts with the maximum window size and decreases over time
B) It starts with a small congestion window and grows it exponentially with each successful ACK, until reaching a threshold
C) It never changes the window size
D) It only applies to UDP traffic

**Answer: B**

**Explanation:** Slow Start begins conservatively (small Cwnd) since the sender doesn't yet know the network's capacity, then doubles the window with every round of successful ACKs — exponential growth — until it hits the Slow Start Threshold (Ssthresh), after which growth becomes linear (Congestion Avoidance).

---

### Q41. What triggers TCP's "Fast Retransmit" mechanism?

A) Receiving three duplicate ACKs for the same segment
B) Waiting for the full retransmission timeout to expire
C) The receiver sending a Window Size of 0
D) A successful three-way handshake

**Answer: A**

**Explanation:** When packets are dropped, subsequent packets arrive out of order, causing the receiver to send duplicate ACKs for the last correctly-received segment. Upon receiving three duplicate ACKs, the sender immediately retransmits the missing segment rather than waiting for a timeout.

---

### Q42. What is the purpose of TCP flow control (the sliding window mechanism)?

A) To prevent network-wide congestion across routers
B) To prevent a fast sender from overwhelming a slower receiver's buffer capacity
C) To assign sequence numbers during the handshake
D) To translate domain names into IP addresses

**Answer: B**

**Explanation:** Flow control is an end-to-end mechanism: the receiver advertises its available buffer space (Receive Window) in every ACK, and the sender must not exceed that limit — protecting the receiver specifically, as opposed to congestion control which protects the network.

---

### Q43. What happens when a TCP receiver advertises a "Zero Window"?

A) The connection is immediately terminated
B) The sender halts transmission and periodically sends Window Probe segments until the receiver's buffer clears
C) The sender switches to UDP
D) The receiver's IP address is reassigned

**Answer: B**

**Explanation:** A Zero Window tells the sender the receiver's buffer is completely full. The sender pauses all data transmission and periodically sends small probe segments to check if buffer space has freed up, resuming full transmission once a non-zero window is advertised.

---

## Section 7: DNS, DHCP & NAT (Q44–Q47)

### Q44. What are the four DNS server types involved in a full (non-cached) DNS resolution, in the order they're typically queried?

A) Authoritative → TLD → Root → Recursor
B) Recursor → Root → TLD → Authoritative
C) TLD → Root → Authoritative → Recursor
D) Root → Authoritative → Recursor → TLD

**Answer: B**

**Explanation:** The client queries its Recursor (resolver), which queries the Root nameserver (which points to the right TLD server), then the TLD nameserver (which points to the Authoritative nameserver for the specific domain), and finally the Authoritative server returns the actual IP address.

---

### Q45. What does the acronym "DORA" represent in the DHCP process?

A) Domain, Origin, Routing, Authentication
B) Discover, Offer, Request, Acknowledge
C) Destination, Output, Reply, ACK
D) DNS, Origin, Resolve, Authoritative

**Answer: B**

**Explanation:** DORA describes the four-step DHCP lease process: the client broadcasts a DHCPDISCOVER, the server responds with a DHCPOFFER, the client broadcasts a DHCPREQUEST accepting that offer, and the server finalizes with a DHCPACK.

---

### Q46. Why does a DHCP client broadcast a DHCPREQUEST instead of simply accepting the first DHCPOFFER silently?

A) Because UDP requires broadcasting by default
B) Because multiple DHCP servers might respond with offers, and the broadcast REQUEST tells all servers which specific offer was accepted (so others can release their reserved address)
C) Because it needs to discover the MAC address of the server
D) DHCPREQUEST is not actually broadcast; this is a trick statement

**Answer: B**

**Explanation:** In networks with multiple DHCP servers, several DHCPOFFERs may arrive. Broadcasting the DHCPREQUEST (rather than unicasting) ensures every DHCP server on the segment sees which offer was accepted, so non-selected servers can release their tentatively reserved addresses back into their pool.

---

### Q47. Which statement correctly differentiates NAT's security benefit from its address-conservation benefit?

A) NAT only conserves addresses; it provides no security benefit
B) NAT conserves public IPv4 addresses by sharing one public IP among many private hosts, AND obscures internal hosts from direct external access since only the router's public interface is visible
C) NAT encrypts traffic, which is its only function
D) NAT replaces the need for a firewall entirely

**Answer: B**

**Explanation:** NAT serves two distinct purposes: (1) conserving scarce public IPv4 addresses by letting many private devices share one public IP, and (2) providing a security side-effect — external hosts cannot directly address or initiate connections to internal private IPs, since they're hidden behind the router's translation table.

---

## Section 8: Diagnostics & Troubleshooting (Q48–Q50)

### Q48. What does the TTL (Time To Live) field in an IP packet actually measure?

A) The number of seconds before a packet expires
B) The maximum number of router hops a packet may traverse before being discarded
C) The total bandwidth available on the path
D) The encryption strength of the packet

**Answer: B**

**Explanation:** Despite the name, TTL is a hop counter, not a time value. Each router that forwards the packet decrements TTL by 1; when it reaches 0, the packet is dropped and an ICMP "Time Exceeded" message is sent back to the source — primarily to prevent packets from looping forever due to routing misconfigurations.

---

### Q49. How does the `traceroute`/`tracert` utility use the TTL field to map a network path?

A) It sets TTL to a very high fixed value and waits for one final reply
B) It sends successive probes with incrementing TTL values (1, 2, 3...), causing each router along the path to drop the packet and reply with an ICMP Time Exceeded message, revealing each hop
C) It disables TTL entirely to bypass routers
D) It only works with UDP and cannot use ICMP

**Answer: B**

**Explanation:** Traceroute deliberately sends packets with TTL=1 first (dropped by the first router, which replies), then TTL=2 (dropped by the second router), and so on — using each router's "Time Exceeded" response to incrementally map every hop between source and destination.

---

### Q50. During troubleshooting, `ping` shows 0% packet loss with consistent low latency to a destination, but `traceroute` shows asterisks (`* * *`) at hop 5 before successfully reaching the destination at hop 8. What is the most reasonable interpretation?

A) The entire path is broken and no data can reach the destination
B) Hop 5's router is likely deprioritizing/rate-limiting ICMP TTL-exceeded replies (a common practice) rather than indicating an actual path failure, since traffic clearly continues to later hops and the destination responds normally
C) The destination server is down
D) TTL values are misconfigured on the source machine

**Answer: B**

**Explanation:** Many routers deprioritize or rate-limit ICMP responses (including Time Exceeded messages) for security/performance reasons, causing asterisks in traceroute even when the router is forwarding traffic normally. Since later hops respond and the final ping test shows healthy connectivity, this is a classic case of an intermediate hop silently dropping/ignoring ICMP — not an actual outage. This is a frequently asked "gotcha" question in TAC interviews to test whether candidates over-interpret traceroute asterisks as failures.

---

## Quick Reference: Topics Covered

| Section | Topics |
|---|---|
| Fundamentals | Packetization, LAN/MAN/WAN, resource sharing |
| OSI/TCP-IP | 7-layer vs 4-layer models, encapsulation order |
| IP Addressing | IPv4/IPv6, CIDR, subnetting, private ranges |
| Switching | MAC addresses, ARP, hubs vs switches, VLANs |
| Routing | Routers, NAT/PAT, static vs dynamic routing, BGP, default gateway |
| Transport | TCP vs UDP, 3-way handshake, congestion control, flow control |
| Services | DNS resolution chain, DHCP (DORA), NAT |
| Troubleshooting | TTL, ping, traceroute, packet loss diagnosis |

---

## Contributing

Found an error or want to add more questions? Feel free to open a PR or issue.

## License

Free to use for personal study and interview preparation.