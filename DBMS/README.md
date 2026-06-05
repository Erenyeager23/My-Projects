
---

## 1. What are ACID properties? Explain each with an example.

In a database management system, **ACID** is an acronym for a set of properties that guarantee database transactions are processed reliably. If a database complies with these properties, it ensures data integrity even in the event of crashes, power failures, or errors.

To explain each property, let's use a classic **Bank Transfer Example**:

> Imagine **Account A** has $500 and wants to transfer $100 to **Account B**, which currently has $200. This requires two steps:
> 1. Deduct $100 from Account A.
> 2. Add $100 to Account B.
> 
> 

### 1. Atomicity ("All or Nothing")

Atomicity ensures that a transaction is treated as a single, indivisible unit of work. Either the *entire* transaction succeeds, or *none* of it does. If any part fails, the database is rolled back to its original state.

* **Example:** During our bank transfer, if the system crashes *after* deducting $100 from Account A but *before* adding it to Account B, the database rolls back. Account A gets its $100 back so that money doesn't just vanish into thin air.

### 2. Consistency ("Rules Must Be Followed")

Consistency ensures that a transaction can only bring the database from one valid state to another, maintaining all predefined schema rules, constraints (like foreign keys or unique constraints), and cascades.

* **Example:** Before the transfer, the total money in both accounts is $700 ($500 + $200). After a successful transfer, the total must still be $700 ($400 + $300). Furthermore, if an account balance is constrained to never drop below $0, the database will reject a transfer that violates this rule.

### 3. Isolation ("Don't Disturb Me")

Isolation ensures that concurrently executing transactions do not interfere with each other. The execution of one transaction is invisible to other transactions running at the same time until it is fully committed.

* **Example:** Suppose Account A tries to transfer $100 to Account B, and simultaneously, an audit script runs to calculate the total balance of all accounts in the bank. Isolation ensures the audit script will either see the balances *before* the transfer or *after* the transfer, but never an intermediate state (like A having $400 and B still having $200).

### 4. Durability ("Built to Last")

Durability guarantees that once a transaction has been successfully committed, its changes are permanently recorded in non-volatile memory (like a hard drive or SSD). The data will not be lost even if a system crash or power outage occurs immediately afterward.

* **Example:** Once the system displays "Transfer Successful," you can pull the plug on the database server. When it boots back up, Account A will still have $400 and Account B will have $300.

---


## 2. What is normalization? Explain 1NF, 2NF, 3NF, and BCNF.

**Normalization** is a systematic process of organizing a database schema to reduce data redundancy (duplication) and eliminate undesirable characteristics like insertion, update, and deletion anomalies. It involves dividing large tables into smaller, related tables and defining relationships between them.

---

### First Normal Form (1NF)

For a table to be in 1NF, it must meet the following rules:

* Each column must contain **atomic (indivisible) values**. No multi-valued attributes or lists.
* Each record needs to be unique (usually achieved via a Primary Key).

#### ❌ Non-1NF Example:

| EmployeeID | Name | Skills |
| --- | --- | --- |
| 101 | Alice | Java, SQL |

*Problem:* The `Skills` column contains multiple values (`Java, SQL`), making it non-atomic.

#### 1NF Cleaned:

| EmployeeID | Name | Skill |
| --- | --- | --- |
| 101 | Alice | Java |
| 101 | Alice | SQL |

---

### Second Normal Form (2NF)

To be in 2NF, a table must:

1. Be in **1NF**.
2. Have **no partial dependencies**. This means every non-prime attribute (a column that is not part of the primary key) must be fully dependent on the *entire* primary key, not just a part of it. (This only applies to tables with composite primary keys).

#### ❌ Non-2NF Example:

*Composite Primary Key:* (`StudentID`, `CourseID`)

| StudentID (PK) | CourseID (PK) | CourseFee |
| --- | --- | --- |
| 1 | C01 | $200 |
| 1 | C02 | $300 |
| 2 | C01 | $200 |

*Problem:* `CourseFee` depends entirely on `CourseID`, but it does *not* depend on `StudentID`. This partial dependency creates redundancy.

#### 2NF Cleaned (Split into two tables):

**Students_Courses Table:**

| StudentID (PK) | CourseID (FK) |
| --- | --- |
| 1 | C01 |
| 1 | C02 |
| 2 | C01 |

**Courses Table:**

| CourseID (PK) | CourseFee |
| --- | --- |
| C01 | $200 |
| C02 | $300 |

---

### Third Normal Form (3NF)

To be in 3NF, a table must:

1. Be in **2NF**.
2. Have **no transitive dependencies**. This means non-prime attributes must not depend on other non-prime attributes. In simple terms: *A depends on B, and B depends on C, therefore A depends on C through B.* We need to eliminate this.

#### ❌ Non-3NF Example:

*Primary Key:* `EmployeeID`

| EmployeeID (PK) | Name | DepartmentID | DeptName |
| --- | --- | --- | --- |
| 101 | Alice | D01 | HR |
| 102 | Bob | D02 | IT |

*Problem:* `DeptName` depends on `DepartmentID`, which in turn depends on `EmployeeID`. This is a transitive dependency ($EmployeeID \rightarrow DepartmentID \rightarrow DeptName$). If all employees leave HR, we lose the knowledge that D01 is named HR (Deletion Anomaly).

#### 3NF Cleaned (Split into two tables):

**Employees Table:**

| EmployeeID (PK) | Name | DepartmentID (FK) |
| --- | --- | --- |
| 101 | Alice | D01 |
| 102 | Bob | D02 |

**Departments Table:**

| DepartmentID (PK) | DeptName |
| --- | --- |
| D01 | HR |
| D02 | IT |

---

### Boyce-Codd Normal Form (BCNF)

BCNF is a stronger, stricter version of 3NF (sometimes called 3.5NF). It deals with cases where a table has overlapping composite candidate keys.

* Rule: For every functional dependency $X \rightarrow Y$, **$X$ must be a super key**.

#### ❌ Non-BCNF Example:

Imagine a college where a student can take multiple subjects. Each professor teaches only one subject, but a subject can be taught by multiple professors.
*Candidate Keys:* (`StudentID`, `Subject`)

| StudentID | Subject | Professor |
| --- | --- | --- |
| 1 | Math | Prof. Jones |
| 1 | Physics | Prof. Smith |
| 2 | Math | Prof. Jones |

*Problem:* There is a dependency: $Professor \rightarrow Subject$ (because each professor teaches only one subject). However, `Professor` is **not** a super key on its own. This violates BCNF.

#### BCNF Cleaned (Split into two tables):

**Student_Professor Table:**

| StudentID (PK) | Professor (PK/FK) |
| --- | --- |
| 1 | Prof. Jones |
| 1 | Prof. Smith |
| 2 | Prof. Jones |

**Professor_Subject Table:**

| Professor (PK) | Subject |
| --- | --- |
| Prof. Jones | Math |
| Prof. Smith | Physics |

---


## 3. What is denormalization? When would you use it?

**Denormalization** is a database optimization technique where we intentionally introduce redundancy into a previously normalized database. It involves combining data from multiple tables into one or adding redundant copies of data.

Unlike normalization, which aims to optimize **write** performance and save storage space, denormalization aims to optimize **read** performance.

---

### When would you use it?

While normalization is the golden rule for transactional systems (OLTP), denormalization is frequently used in high-read environments, data warehouses, and reporting systems (OLAP) for the following reasons:

* **To Improve Query Performance:** In heavily normalized databases, retrieving data often requires complex SQL queries with numerous `JOIN` operations. Joins are computationally expensive. Denormalization reduces or eliminates the need for joins, allowing the database to fetch data much faster.
* **For Data Warehousing and BI:** Analytical tools often need to aggregate vast amounts of historical data quickly. Storing data in pre-aggregated or flattened structures (like Star or Snowflake schemas) speeds up reporting.
* **To Simplify Queries:** Writing queries against a single flat table is much simpler for developers and reporting tools than navigating a web of dozens of interconnected tables.
* **For Frequently Accessed Derived Data:** If a application constantly runs a query to calculate a value (e.g., the total cost of items in an order), it is often more efficient to store that total directly in the `Orders` table and update it via application logic or a trigger.

---

### The Trade-offs of Denormalization

Denormalization is a deliberate architectural compromise. You shouldn't do it blindly, as it introduces several disadvantages:

| Normalized (OLTP) | Denormalized (OLAP) |
| --- | --- |
| Focuses on optimizing **inserts, updates, and deletes**. | Focuses on optimizing **select (read) queries**. |
| Minimized data redundancy. | Increased data redundancy. |
| Smaller storage footprint. | Larger storage footprint due to duplicated data. |
| Higher risk of slow queries (due to multiple `JOIN`s). | Higher risk of **data anomalies** (inconsistencies) if updates are not handled carefully. |

> **Analogy:** Normalization is like organizing your clothes perfectly by type, color, and season into distinct boxes so nothing is ever misplaced. Denormalization is like pulling out the outfits you wear every single day and keeping them piled on a chair next to your bed. It makes getting dressed in the morning much faster, but it creates a bit of a mess and takes up extra space in your room.

---


## 4. What is the difference between a primary key, unique key, and foreign key?

In relational databases, keys are fundamental constraints used to uniquely identify rows within a table and establish relationships between different tables.

Here is how they break down and differ from one another:

### 1. Primary Key (PK)

A Primary Key uniquely identifies each record (row) in a database table.

* **Uniqueness:** Every value in a primary key column must be absolutely unique. No two rows can share the same value.
* **Nullability:** It **cannot** contain `NULL` values. Every row *must* have a valid primary key value.
* **Limit:** A table can have **only one** primary key.
* **Index:** The database automatically creates a clustered index on the primary key column by default, making data retrieval incredibly fast.

### 2. Unique Key / Unique Constraint

A Unique Key ensures that all values in a column (or a combination of columns) are distinct across the table.

* **Uniqueness:** Like a primary key, it enforces unique values.
* **Nullability:** It **can** accept `NULL` values. Depending on the database system (like PostgreSQL or SQL Server), you can typically have one or multiple `NULL` values, as `NULL` represents the absence of data rather than a duplicating value.
* **Limit:** You can have **multiple** unique keys/constraints within a single table.
* **Index:** The database automatically creates a non-clustered index on unique key columns.

### 3. Foreign Key (FK)

A Foreign Key is a column (or collection of columns) that establishes a link between the data in two tables. It points to a primary key (or sometimes a unique key) in another table, enforcing **referential integrity**.

* **Uniqueness:** It does not need to be unique. Multiple rows in the child table can reference the exact same row in the parent table (a many-to-one relationship).
* **Nullability:** It **can** accept `NULL` values (unless explicitly marked as `NOT NULL`). A `NULL` foreign key means there is no relationship established for that specific record.
* **Limit:** A table can have **multiple** foreign keys pointing to various other tables.

---

### Quick Comparison Summary

| Feature | Primary Key | Unique Key | Foreign Key |
| --- | --- | --- | --- |
| **Purpose** | Uniquely identifies a record in the current table.

 | Prevents duplicate values in a column. | Establishes a relationship with another table.

 |
| **Allows NULLs?** | ❌ No | Yes | Yes |
| **Count per Table** | Only **One** | **Multiple** allowed | **Multiple** allowed |
| **Data Duplication** | ❌ Strictly unique | ❌ Strictly unique | Can have duplicate values |

> **Real-World Example:**
> Imagine a `Customers` table and an `Orders` table.
> * In the `Customers` table, `CustomerID` is the **Primary Key** (each customer gets one unique, non-null ID). The customer's `EmailAddress` is a **Unique Key** (no two customers can register with the same email, but a user might not provide one initially, leaving it `NULL`).
> * In the `Orders` table, `OrderID` is the **Primary Key**. However, this table also contains a `CustomerID` column. Here, `CustomerID` acts as a **Foreign Key**, linking each order back to the specific customer who placed it. Multiple orders can share the same `CustomerID`.
> 
> 

---

Since you requested to dive deeper into **Question 5**, let’s break down the difference between `WHERE` and `HAVING` using a clear banking application scenario and a practical analogy.

---

## 5. What is the difference between WHERE and HAVING?

### The Banking Analogy: Sorting Cash Bags

Imagine you are a head branch auditor at a bank.

* **`WHERE` is like a security guard standing at the front door.** Before any money is counted or grouped, the guard inspects individual physical bills. If a bill is ripped, fake, or foreign currency, it gets thrown out immediately. The guard filters **individual items**.
* **`GROUP BY` is the process of putting the remaining clean bills into canvas bags** labeled by currency or branch department.
* **`HAVING` is like the auditor inspecting the final closed bags.** You aren't looking at single dollar bills anymore; you are looking at the bags as a whole. You might say, *"Only bring me bags that contain more than $10,000 total."* You are filtering **entire groups**.

---

### The Bank Database Scenario

Imagine your bank has an `Accounts` table that records individual customer accounts across various branches.

| AccountNumber | BranchName | AccountType | Balance |
| --- | --- | --- | --- |
| ACC-101 | Downtown | Savings | $5,000 |
| ACC-102 | Downtown | Checking | $12,000 |
| ACC-103 | Uptown | Savings | $1,500 |
| ACC-104 | Downtown | Savings | $250 |
| ACC-105 | Uptown | Checking | $8,000 |

The bank executive asks you for a report: **"Show me the branches where the average balance of standard *Savings* accounts is greater than $3,000."**

To get this data, you must use both `WHERE` and `HAVING` together because they fulfill completely different steps:

```sql
SELECT BranchName, AVG(Balance) AS AvgSavingsBalance
FROM Accounts
WHERE AccountType = 'Savings'
GROUP BY BranchName
HAVING AVG(Balance) > 3000;

```

### Step-by-Step Execution Profile

1. **The `WHERE` Filter (Row-Level Elimination):**
The database first scans the table row by row. It looks at the `AccountType` column and immediately discards `ACC-102` and `ACC-105` because they are *Checking* accounts. **Aggregate functions like `AVG()` cannot be used here** because the database is still looking at individual data rows.
2. **The `GROUP BY` Clause (Aggregating data):**
The database takes the remaining *Savings* accounts and groups them by their `BranchName`.
* **Downtown Group:** Has `ACC-101` ($5,000) and `ACC-104` ($250). *Average = $2,625*.
* **Uptown Group:** Has `ACC-103` ($1,500). *Average = $1,500*.


3. **The `HAVING` Filter (Group-Level Elimination):**
Now that the groups are built and the averages are calculated, the `HAVING` clause evaluates the group results. It checks if `AVG(Balance) > 3000`.
* Since neither Downtown ($2,625) nor Uptown ($1,500) meets the condition, this specific query would return an empty dataset!



---

### Key Differences Summary

| Feature | WHERE Clause | HAVING Clause |
| --- | --- | --- |
| **Banking Context** | Filters out specific **individual accounts** (e.g., Status = 'Active', Type = 'Savings').

 | Filters out entire **branches or customer segments** based on summary math (e.g., Total branch deposits > $1M).

 |
| **Timing** | Applied **before** the bank groups data together.

 | Applied **after** data has been grouped and summarized.

 |
| **Capability** | Cannot calculate summaries. Writing `WHERE SUM(Balance) > 5000` causes an error.

 | Designed specifically to evaluate summaries like `HAVING SUM(Balance) > 5000`.

 |

---



## 6. Explain the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN.

In a banking system, data is split across multiple tables to reduce duplication (as we saw in normalization). To get a complete view of a customer's profile, we use **JOINS** to stitch that data back together.

To illustrate, let’s use two basic banking tables:

* **`Customers` table**: Contains account holders who signed up.
* **`CreditCards` table**: Contains credit cards issued by the bank.

### 1. INNER JOIN (The "Perfect Match")

An `INNER JOIN` returns records only when there is a match in **both** tables. If a customer doesn't have a credit card, or if a credit card belongs to a deleted user ID, they are excluded.

* **Bank Scenario:** The marketing team wants to send an email promotion *only* to active bank account holders who **also** hold an active credit card with the bank.
* **Result:** You only get customers who exist in the `Customers` table AND have a corresponding matching row in the `CreditCards` table.

```sql
SELECT Customers.CustomerName, CreditCards.CardNumber
FROM Customers
INNER JOIN CreditCards ON Customers.CustomerID = CreditCards.CustomerID;

```

### 2. LEFT JOIN / LEFT OUTER JOIN (All Accounts + Their Cards)

A `LEFT JOIN` returns **all** records from the left table (`Customers`), and the matched records from the right table (`CreditCards`). If there is no match, the right side returns `NULL`.

* **Bank Scenario:** A branch manager wants a comprehensive report of *every single customer* registered at their branch, alongside their credit card number if they have one.
* **Result:** Every customer is listed. If Alice has a card, her card number shows up. If Bob only has a savings account and no credit card, his name shows up, but the card number column will say `NULL`.

```sql
SELECT Customers.CustomerName, CreditCards.CardNumber
FROM Customers
LEFT JOIN CreditCards ON Customers.CustomerID = CreditCards.CustomerID;

```

### 3. RIGHT JOIN / RIGHT OUTER JOIN (All Cards + Their Owners)

A `RIGHT JOIN` is the exact opposite of a `LEFT JOIN`. It returns **all** records from the right table (`CreditCards`), and the matched records from the left table (`Customers`).

* **Bank Scenario:** The fraud detection department has a master list of all credit card numbers flagged for suspicious activity. They want to cross-reference this list with the `Customers` table to see who owns them.
* **Result:** Every flagged credit card is returned. If a card is tied to a valid customer, their name appears. If a card was generated by an offline batch error and isn't tied to an active customer ID in the main database yet, the card is still listed, but the customer name will display as `NULL`.

```sql
SELECT Customers.CustomerName, CreditCards.CardNumber
FROM Customers
RIGHT JOIN CreditCards ON Customers.CustomerID = CreditCards.CustomerID;

```

### 4. FULL JOIN / FULL OUTER JOIN (The Complete Sandbox)

A `FULL JOIN` returns all records when there is a match in either the left or right table. It combines the behavior of both `LEFT JOIN` and `RIGHT JOIN`.

* **Bank Scenario:** During a corporate merger between two systems, IT wants a massive cleanup list containing *all* registered customers and *all* issued credit cards, regardless of whether they are correctly linked together or completely orphaned.
* **Result:** You see everything. Customers with cards, customers without cards (card column is `NULL`), and cards with no associated customer (customer column is `NULL`).

```sql
SELECT Customers.CustomerName, CreditCards.CardNumber
FROM Customers
FULL JOIN CreditCards ON Customers.CustomerID = CreditCards.CustomerID;

```

---

### Comparison Cheat Sheet

| Join Type | What it returns from the Bank Database |
| --- | --- |
| **`INNER JOIN`** | Only customers who **have** a credit card. |
| **`LEFT JOIN`** | **All customers**, with card details if available (otherwise `NULL`). |
| **`RIGHT JOIN`** | **All credit cards**, with owner details if available (otherwise `NULL`). |
| **`FULL JOIN`** | **All customers and all cards**, filling blanks with `NULL` wherever a link is missing. |

---

## 7. What is referential integrity?

**Referential integrity** is a database concept that ensures relationships between tables remain consistent and valid. It dictates that a foreign key value in a "child" table must always point to an existing, valid primary key value in the "parent" table. This prevents "orphaned" records that point to non-existent data.

---

### The Banking Analogy: Credit Cards and Customers

Think of a bank's system. You have a `Customers` table (the parent) and a `CreditCards` table (the child).

* **Referential integrity** is the rule that ensures *a credit card cannot exist unless it is legally tied to a real customer registered at the bank.* * If the bank accidentally prints a credit card with a random `CustomerID` that doesn't match any living person in their master files, that card is an "orphan," and the rule has been broken.

---

### The Bank Database Scenario

Imagine we have these two tables:

**`Customers` (Parent Table)**

| CustomerID (PK) | CustomerName |
| --- | --- |
| 101 | Alice Smith |
| 102 | Bob Jones |

**`CreditCards` (Child Table)**

| CardNumber (PK) | CustomerID (FK) | CardLimit |
| --- | --- | --- |
| 4111-XXXX | 101 | $5,000 |
| 5222-XXXX | 102 | $10,000 |

#### How the Database Enforces Referential Integrity:

The Database Management System (DBMS) monitors three major operations to keep this relationship valid:

1. **Restricting Invalid Inserts:**
If a bank employee tries to log a new credit card into the system with `CustomerID = 999`, the database will throw an error and block the insert. Why? Because customer `999` does not exist in the `Customers` table.
2. **Handling Parent Deletions (Cascades or Restricts):**
What happens if Bob Jones (`CustomerID = 102`) closes his bank account and an employee tries to delete him from the `Customers` table? The database will step in based on predefined rules:
* **RESTRICT / NO ACTION:** The database blocks the deletion of Bob because card `5222-XXXX` is still pointing to him. The employee must cancel the credit card first.
* **CASCADE:** The database automatically deletes Bob *and* simultaneously wipes out his credit card from the `CreditCards` table.
* 
**SET NULL:** The database deletes Bob, but keeps the credit card row, changing its `CustomerID` field to `NULL`.




3. **Restricting Primary Key Updates:**
If you try to change Alice’s `CustomerID` from `101` to `555`, the database will reject the change unless it is set to cascade, because changing it would leave her credit card pointing to a non-existent ID `101`.

---

## 8. What is a transaction?

In a database management system, a **transaction** is a single logical unit of database processing that includes a sequence of database operations (such as reading, writing, updating, or deleting data). For a group of operations to be considered a transaction, they must execute as an all-or-nothing package.

---

### The Banking Analogy: ATM Cash Withdrawal

Imagine you walk up to an ATM to withdraw $100. From your perspective, it's one simple action. However, behind the scenes, the bank's system treats this as a multi-step transaction:

1. The system checks if your account has at least $100.
2. The system deducts $100 from your account balance.
3. The ATM's physical mechanism counts and dispenses the $100 cash.
4. The system logs the transaction in the audit history table.

If the machine jams at step 3 and dispenses no cash, the bank cannot safely leave step 2 complete. The entire sequence must be cancelled, and your account balance must remain unchanged.

---

### The Bank Database Scenario

Let's look at how this sequence maps out inside a banking application's relational database using explicit SQL transaction commands. We will wrap the operations inside a `BEGIN TRANSACTION` block and finish with either a `COMMIT` (to save permanently) or a `ROLLBACK` (to undo changes if something breaks).

```sql
BEGIN TRANSACTION;

-- Step 1: Deduct money from the customer's account
UPDATE Accounts 
SET Balance = Balance - 100 
WHERE AccountNumber = 'ACC-101';

-- Step 2: Record the ATM withdrawal event
INSERT INTO TransactionLogs (AccountNumber, Amount, TransactionType, LogTime)
VALUES ('ACC-101', 100, 'ATM_Withdrawal', NOW());

-- [System checks if everything succeeded]
-- If the cash machine failed to dispense:
-- ROLLBACK TRANSACTION;

-- If everything worked perfectly:
COMMIT TRANSACTION;

```

### The States of a Transaction

A transaction moves through several distinct states during its lifecycle within the database engine:

* 
**Active:** The initial state when the transaction begins executing its operations.


* **Partially Committed:** The state reached after the final statement of the transaction has been executed, but before the modifications are written permanently to disk.
* 
**Committed:** The state reached after successful completion. The changes are now permanent and durable.


* 
**Failed:** The state reached if any of the checks fail or a system error occurs mid-execution.


* 
**Aborted (Rolled Back):** The final state after the database has undone all changes from a failed transaction, safely returning the database to its pre-transaction state.



---


## 9. What is a deadlock in a database? How can it be prevented?

A **deadlock** occurs in a concurrent database system when two or more transactions are waiting for each other to release locks on resources, creating a permanent circular dependency where none of them can proceed. When this happens, the database engine must step in to break the freeze.

---

### The Banking Analogy: The Dual-Sign-Off Gridlock

Imagine two bank executives, Executive A and Executive B, who need to audit two secure physical files: **File 1 (Corporate Accounts)** and **File 2 (Loan Applications)**. Security protocol dictates that an executive must lock a file while reading it.

1. **Executive A** walks into the archive room and grabs **File 1**.
2. **Executive B** walks in at the exact same time and grabs **File 2**.
3. Now, **Executive A** needs **File 2** to finish the report, so they sit down and wait for Executive B to release it.
4. Meanwhile, **Executive B** needs **File 1** to finish their own report, so they sit down and wait for Executive A to release it.

Both executives will sit there forever, staring at each other, waiting for the other to give up their file. This is a classic deadlock.

---

### The Bank Database Scenario

This dynamic maps directly to database rows when multiple application threads try to update the same target rows in a different chronological sequence:

| Transaction 1 (Transfer from Account A to B) | Transaction 2 (Transfer from Account B to A) |
| --- | --- |
| **Step 1:** Successfully locks **Account A** to deduct money. | **Step 1:** Successfully locks **Account B** to deduct money. |
| **Step 2:** Tries to acquire a lock on **Account B** to deposit money... *Blocks (waits for Transaction 2)*. | **Step 2:** Tries to acquire a lock on **Account B** to deposit money... *Blocks (waits for Transaction 1)*. |

```sql
-- Transaction 1 (Locks A, waits for B)
UPDATE Accounts SET Balance = Balance - 100 WHERE AccountNumber = 'ACC-A';
UPDATE Accounts SET Balance = Balance + 100 WHERE AccountNumber = 'ACC-B';

-- Transaction 2 (Locks B, waits for A)
UPDATE Accounts SET Balance = Balance - 50 WHERE AccountNumber = 'ACC-B';
UPDATE Accounts SET Balance = Balance + 50 WHERE AccountNumber = 'ACC-A';

```

---

### How can it be prevented?

While database engines use a **Deadlock Detector** background process to automatically kill and roll back one of the transactions (the "victim") to free up the other, application developers should actively write code to minimize their occurrence.

* **Maintain a Consistent Access Order (Primary Defense):** Always access resources in the exact same sequence across your entire application codebase. If both transactions updated `ACC-A` first and *then* `ACC-B`, a deadlock could never occur; Transaction 2 would simply wait cleanly at Step 1 until Transaction 1 finished entirely.
* **Keep Transactions Short and Conversational:** Do not let a transaction sit open while performing heavy processing, external API calls, or loading client UI elements. The faster a transaction runs and commits, the less time it holds onto active row locks.


* 
**Use Low Lock Isolation Levels:** Where strict data isolation isn't explicitly required, use optimized isolation settings like *Read Committed Snapshot Isolation (RCSI)* or *Multi-Version Concurrency Control (MVCC)*. This allows read queries to access data without needing to acquire blocking shared locks.


* **Implement Explicit Deadlock Timeouts:** Configure your application's database connection string with an explicit timeout limit (e.g., `SET LOCK_TIMEOUT 2000`). This ensures that a thread will automatically drop out and throw an exception if it is forced to wait for a locked resource for longer than 2 seconds.

---


## 10. What is indexing? Why is it needed?

**Indexing** is a database optimization technique used to speed up the retrieval of records from a table. An index is a separate, specialized data structure (most commonly a B-Tree) that holds a copy of a small portion of a table's data (the indexed column) along with a pointer pointing directly to the actual physical location of the row it came from.

---

### The Banking Analogy: The Ledger Index Book

Imagine you walk into a massive, traditional bank archive room that contains physical leather-bound ledgers documenting millions of global customer accounts. The ledgers are organized purely by the chronological date the accounts were opened.

* **Without an Index (Full Table Scan):** If an executive asks you to look up the account profile for a customer named *"Zachary Vance"*, you would have to walk to the very first shelf, open the first book, and read through every single row, line-by-line, matching names until you hit the very last book on the last shelf. In database terms, this is a **Full Table Scan**, which is incredibly slow and resource-heavy.
* **With an Index:** Now imagine the bank archivist hands you a slim notebook labeled *"Customer Names Index"*. This notebook lists every single customer name in strict **alphabetical order**. You flip directly to the letter **'Z'**, find *"Zachary Vance"*, and right next to his name, it reads: *"Cabinet 4, Ledger 12, Page 402"*. You bypass millions of unrelated records and walk straight to the exact page. That slim notebook is an **Index**.

---

### The Bank Database Scenario

Consider a bank's master `Accounts` table containing 50 million customer records:

```sql
SELECT AccountNumber, CustomerName, Balance
FROM Accounts
WHERE AccountNumber = 'ACC-987452';

```

#### Why Is It Needed?

1. **Drastic Search Acceleration:** Without an index on `AccountNumber`, the database engine must execute a full table scan, spinning through millions of rows on the hard drive to find that one specific account number. With an index, the search operation drops from linear time ($O(N)$) down to logarithmic time ($O(\log N)$), turning a multi-second bottleneck into a millisecond response.
2. **Optimizing Performance Constraints:** Banks run high-velocity queries every second—validating ATM pins, processing wire transfers, and pulling mobile balances. Indexes prevent your database server's CPU and disk I/O from hitting 100% utilization under concurrent user traffic.
3. **Speeding up Table Joins:** When the bank connects your `Accounts` table to your `TransactionHistory` table, it matches them up using your Account Number. Having an index on those relationship columns makes stitching tables together vastly faster.

---

## 11. What is the difference between clustered and non-clustered indexes?

To understand how a database physically stores and finds our banking information, we need to look at the two primary types of indexes: **Clustered** and **Non-Clustered**.

---

### The Banking Analogy: The Phone Book vs. The Index Cards

* **Clustered Index is like a physical Phone Book.** In a phone book, the actual data (names, phone numbers, and physical addresses) is sorted in strict alphabetical order from page one to the end. The order of the index *is* the physical order of the data. You don't look up a name and then go to another book; the record itself sits right there on the sorted page.
* **Non-Clustered Index is like a Library Card Catalog.** The library books (the actual row data) are placed on the shelves in whatever random space is available or by arrival date. However, the card catalog organizes books alphabetically by author name. Each little card contains only the author's name and a **pointer/call number** (e.g., *"Aisle 3, Shelf B"*) telling you exactly where to walk to find the actual physical book.

---

### The Bank Database Scenario

Let’s see how a database engine handles these two index configurations on our master `Accounts` table:

#### 1. Clustered Index

* 
**How it works:** A clustered index dictates the exact physical, chronological order in which the rows of a table are sorted and stored on the hard drive. Because the actual rows can only be stored in one physical order, a table can have **only one** clustered index. By default, databases automatically create this on the table's Primary Key (like `AccountNumber`).


* **Bank Application:** When a query runs to look up an account balance by account number, the database navigates the clustered index and hits the "leaf level." At this exact leaf node, it finds the actual physical table row containing the full customer data (`CustomerName`, `Balance`, `RoutingNumber`, etc.).



#### 2. Non-Clustered Index

* 
**How it works:** A non-clustered index is a completely separate structure from the main data rows. It holds a sorted copy of the chosen index column alongside a **pointer** (the clustered index key or a physical row ID) pointing back to where the full row resides. Since this is a separate structure, you can have **multiple** non-clustered indexes on a single table.


* **Bank Application:** Suppose the customer service department regularly looks up accounts by a customer's government tax ID (`SSN`). We would create a non-clustered index on the `SSN` column.
* 
**The Lookup Process:** When searching by `SSN`, the database engine looks through the slim, sorted `SSN` non-clustered index. Once it locates the target SSN, it extracts the associated pointer (e.g., `AccountNumber = 'ACC-987452'`). It then performs an extra step called a **Key Lookup**, leaping over to the clustered index to pull out the actual account balance or profile details.



---

### Key Differences Summary

| Feature | Clustered Index | Non-Clustered Index |
| --- | --- | --- |
| **Physical Storage** | Stores the actual physical table data rows directly inside the index leaf nodes.

 | Stored as a separate data structure completely independent of the table rows.

 |
| **Pointers** | Contains the actual data; no pointers needed at the bottom level. | Contains pointers pointing back to the physical clustered row location.

 |
| **Count Limit** | Strictly **one per table**.

 | <br>**Multiple indexes** allowed per table.

 |
| **Default Creation** | Automatically bound to the table's **Primary Key**.

 | Explicitly created by developers or bound to **Unique Keys**.

 |
| **Write Impact** | Can slow down updates/inserts if the system is forced to physically shift rows around on disk. | Shorter write impact, though it requires storage overhead to keep the separate index tree updated. |

---

## 12. How does a B-Tree index work?

A **B-Tree (Balanced Tree)** is the default self-balancing tree data structure that modern databases use to keep data sorted and allow searches, sequential access, insertions, and deletions in logarithmic time ($O(\log N)$).

The structure consists of three distinct layers of "nodes":

* **Root Node:** The starting point of any search query.
* **Internal Nodes (Routing Nodes):** Intermediate layers that act as signposts to direct the query to the correct region of the tree.
* 
**Leaf Nodes:** The bottom-most layer that contains the actual indexed values and pointers to the physical data rows.



---

### The Banking Analogy: Traveling down Branch Security Clearance

Imagine you are a federal bank examiner visiting a massive multi-floor central vault facility to inspect an individual high-value safety deposit box holding account number **`ACC-74`**.

Instead of searching through millions of boxes row by row, the facility is structured like a B-Tree:

* **The Main Lobby (Root Node):** You walk up to the main directory sign. It reads:
* *If Account Number is between 1 and 50 $\rightarrow$ Go to Hallway A*
* *If Account Number is between 51 and 100 $\rightarrow$ Go to Hallway B*
* *If Account Number is greater than 100 $\rightarrow$ Go to Hallway C*
* Since you need `ACC-74`, you take the elevator down to **Hallway B**.


* **The Hallway Attendant (Internal Node):** You step out into Hallway B and meet a security desk with another directory sign:
* *For accounts 51 to 70 $\rightarrow$ Go to Room 1*
* *For accounts 71 to 90 $\rightarrow$ Go to Room 2*
* *For accounts 91 to 100 $\rightarrow$ Go to Room 3*
* You are routed directly to **Room 2**.


* **The Safety Deposit Boxes (Leaf Node):** You enter Room 2. The boxes inside are cleanly organized on shelves from 71 to 90. You walk straight to box **74**. You open it and grab the core document (or a physical pointer pointing to the exact warehouse shelf where the client's asset is stored).



By checking only three signs, you bypassed searching through millions of other client records.

---

### The Bank Database Scenario

When you submit this query to find a customer's checking balance:

```sql
SELECT Balance 
FROM Accounts 
WHERE AccountNumber = 74;

```

#### The Internal Execution Process:

1. **Traversing the Tree:** The database engine loads the **Root Node** into memory. It sees that `74` falls within the range pointer mapped to a specific **Internal Node**.
2. **Binary Search on Nodes:** It jumps to that Internal Node, reads the keys, and instantly figures out which **Leaf Node** page contains the value `74`.
3. **Data Extraction:** The engine accesses the targeted Leaf Node page on disk or in cache memory.
* If this is a **Clustered Index**, the leaf node *is* the physical table row, so it directly reads the `Balance` column right then and there.


* If this is a **Non-Clustered Index**, the leaf node provides a pointer (like a Row ID or the Primary Key value), prompting the database engine to perform a quick jump over to fetch the actual balance details.





### Why is it so efficient for banks?

* **It stays Balanced:** As new customers open accounts or close them, the B-Tree automatically splits or merges nodes to ensure all leaf nodes remain at the exact same depth. A search will always take the same number of steps, preventing performance degradation over time.
* **High Fan-Out:** Each node can hold hundreds of pointer keys. This means a B-Tree can index tens of millions of records within a height of just 3 or 4 levels, ensuring that data can be found in a few rapid page reads.

---

## 13. What are views and materialized views?

In database management, **Views** and **Materialized Views** are objects that allow you to store the schema or the results of a predefined query. While they may look like standard tables to an application, they operate very differently under the hood.

---

### The Banking Analogy: The Real-Time Glass Window vs. The Printed Nightly Ledger

* **A Standard View is like a reinforced glass viewing window looking directly into the bank's active vault.** When you step up and peer through the window, you see exactly what is inside the vault at that exact split second. The window itself doesn't hold any physical money—it is just a real-time window into the underlying structure.
* **A Materialized View is like a printed summary snapshot ledger compiled by the accounting team at midnight.** If an executive asks for total regional deposits, you don't run into the vault to count loose change; you look at the printed paper document sitting on the desk. It is incredibly fast to read, but if a customer deposits $10 million at 9:00 AM, that printed sheet will be slightly outdated until the team prints a fresh copy.

---

### The Bank Database Scenario

Imagine a bank's `Transactions` table contains 100 million rows of individual deposits, transfers, and withdrawals. The risk management team frequently needs to analyze a subset of this data: **high-value suspicious transactions over $250,000**.

#### 1. Views (Virtual / Standard Views)

A standard view is a **virtual table**. It does not store any physical data on disk. Instead, it simply saves the SQL query definition.

```sql
CREATE VIEW View_HighValueTransactions AS
SELECT TransactionID, AccountNumber, Amount, LogTime
FROM Transactions
WHERE Amount > 250000;

```

* **Execution Behavior:** When you run `SELECT * FROM View_HighValueTransactions;`, the database engine intercepts the call, combines it with the saved view definition, and executes the underlying search against the massive base `Transactions` table in real time.
* 
**Pros:** It takes up zero disk storage space and always displays perfectly live, real-time data.


* **Cons:** If the underlying table has 100 million rows and lacks proper indexing, running a query against the view will be slow every single time because the database has to do the heavy lifting repeatedly.

#### 2. Materialized Views

A materialized view is a **physical copy of the query results** calculated in advance and written directly to disk storage.

```sql
CREATE MATERIALIZED VIEW MV_HighValueTransactions AS
SELECT TransactionID, AccountNumber, Amount, LogTime
FROM Transactions
WHERE Amount > 250000;

```

* **Execution Behavior:** When you run `SELECT * FROM MV_HighValueTransactions;`, the database doesn't touch the original 100-million-row `Transactions` table at all. It reads the pre-computed results directly from disk, returning the data instantly.
* **Data Freshness:** Because it is stored on disk, it can fall out of sync when updates hit the base table. The bank must configure a refresh strategy:
* **REFRESH COMPLETE:** The entire view is periodically wiped and recalculated from scratch (e.g., every night at midnight).
* **REFRESH FAST (Incremental):** The database tracks changes using database logs and applies only the new updates to the view.


* 
**Pros:** Drastically accelerates complex analytical queries, reporting dashboards, and aggregations.


* 
**Cons:** Consumes storage space and introduces a delay in data updates until a refresh occurs.



---

### Key Differences Summary

| Feature | Standard View | Materialized View |
| --- | --- | --- |
| **Physical Storage** | No storage. Stores only the SQL query logic.

 | Yes. Computes the results and saves them physically on disk.

 |
| **Data Real-Time Access** | Always fresh and identical to the base tables.

 | Can lag behind the live data until a refresh happens.

 |
| **Query Performance** | Depends entirely on the base table's size and indexes. | Extremely fast because the computations are already complete.

 |
| **Use Case** | Hiding complex joins, simplifying security access levels. | Aggregating data warehouses, BI dashboards, and heavy reporting.

 |

---


## 14. What are stored procedures and triggers?

In a database management system, **Stored Procedures** and **Triggers** are blocks of SQL code that are stored directly on the database server. While both represent a form of database-side programming, they differ completely in how they are executed.

---

### The Banking Analogy: The Bank Teller vs. The Security Guard

* **A Stored Procedure is like an experienced Bank Teller.** The teller sits at the counter waiting for you to walk up and explicitly give them an instruction, such as: *"Please process a wire transfer of $500 from Account A to Account B."* They execute a multi-step routine on your command, and they can ask you for parameters (like your ID and destination account).
* **A Trigger is like an automated Security Guard or Alarm System.** The guard does not wait for you to give them orders. Instead, they stand quietly in the corner and **react automatically** the exact split second a specific event occurs—such as someone trying to open a restricted vault door.

---

### The Bank Database Scenario

Let's look at how a bank utilizes both components to manage automated workflows and security audits.

#### 1. Stored Procedures (Explicitly Called)

A stored procedure is a collection of SQL statements that can be saved and reused. It accepts input parameters, performs complex business logic, and must be explicitly run using an execution command like `EXEC` or `CALL`.

* **Bank Application:** Creating a standardized routine to process a money transfer. This ensures the application code doesn't have to write raw SQL every time a transfer happens.

```sql
CREATE PROCEDURE Process_Transfer(
    IN SenderAcc INT,
    IN ReceiverAcc INT,
    IN TransferAmount DECIMAL(10,2)
)
BEGIN
    [cite_start]-- Step 1: Deduct from sender [cite: 20]
    UPDATE Accounts SET Balance = Balance - TransferAmount WHERE AccountNumber = SenderAcc;
    [cite_start]-- Step 2: Add to receiver [cite: 20]
    UPDATE Accounts SET Balance = Balance + TransferAmount WHERE AccountNumber = ReceiverAcc;
END;

```

* **How it runs:** The application explicitly fires it: `CALL Process_Transfer(101, 102, 500.00);`

#### 2. Triggers (Automatically Fired)

A trigger is a specialized database block that automatically fires or executes in response to a specific event (such as `INSERT`, `UPDATE`, or `DELETE`) on a specific table.

* **Bank Application:** Enforcing strict compliance auditing. The bank wants to ensure that anytime an account's balance changes, the modification is automatically logged into a separate audit trail table for security purposes.



```sql
CREATE TRIGGER Audit_Balance_Change
AFTER UPDATE ON Accounts
FOR EACH ROW
BEGIN
    -- Fires automatically whenever a balance is updated
    INSERT INTO AuditLogs (AccountNumber, OldBalance, NewBalance, ChangedAt)
    VALUES (OLD.AccountNumber, OLD.Balance, NEW.Balance, NOW());
END;

```

* **How it runs:** Nobody calls this trigger. If a developer runs a standard `UPDATE Accounts SET Balance = 0 ...`, the trigger wakes up silently in the background and writes the historical log entry.



---

### Key Differences Summary

| Feature | Stored Procedure | Trigger |
| --- | --- | --- |
| **Invocation** | Must be **explicitly called** by the application or developer (`CALL`/`EXEC`).

 | **Automatically executed** by the database engine based on data events (`INSERT`, `UPDATE`, `DELETE`). |
| **Parameters** | Can accept input/output parameters (e.g., specific account numbers, amounts). | **Cannot accept parameters**. It relies purely on context tables like `OLD` and `NEW` row data. |
| **Transaction Control** | Can commit or roll back transactions internally.

 | Runs implicitly inside the transaction that triggered it; it cannot commit or roll back on its own.

 |
| **Primary Use Cases** | Batch operations, encapsulating business logic, standardizing complex routines. | Enforcing data integrity rules, automatic audit logging, calculating real-time metrics. |

---

## 15. What is database sharding and partitioning?

As a bank grows from a single local branch to a massive global institution handling hundreds of millions of accounts, storing all data in one giant database table becomes a massive performance bottleneck. To scale, databases use **partitioning** and **sharding**.

While both techniques involve breaking a huge dataset into smaller, more manageable pieces, they do so at completely different architectural levels.

---

### The Banking Analogy: The Single File Cabinet vs. Separate Branch Buildings

* **Partitioning (Horizontal) is like organizing a single massive filing cabinet within a branch.** Instead of throwing all 1 million customer folders into one giant drawer, you break the cabinet into separate drawers labeled by birth month or account type. Everything still lives inside the **same physical cabinet** in the **same room**, but it is much faster for a teller to search through one specific drawer.
* **Sharding is like splitting your data across completely separate branch buildings.** You put all customers with last names A–M into the *Downtown Branch database server*, and all customers with last names N–Z into the *Uptown Branch database server*. The data is separated across **entirely different physical machines** located in different places.

---

### The Bank Database Scenario

Imagine a bank's master `Transactions` table grows to 500 million rows, causing analytical queries and balance lookups to lag severely.

#### 1. Database Partitioning (Horizontal Partitioning)

Partitioning divides a large table into smaller pieces, called partitions, but keeps all those pieces within the **same database instance** on the **same server**. The application talking to the database doesn't even know partitioning is happening.

* 
**Bank Implementation:** Partitioning the `Transactions` table by **Transaction Date**.


* `Partition 2024`: Stores rows from year 2024.
* `Partition 2025`: Stores rows from year 2025.
* `Partition 2026`: Stores rows from year 2026.


* **Why do it?** If an auditor runs a query to calculate total deposits for May 2026, the database engine executes **Partition Pruning**. It ignores the 2024 and 2025 partitions entirely and searches only the 2026 partition, speeding up query execution drastically.

#### 2. Database Sharding

Sharding is a "shared-nothing" architecture where data is split across **multiple independent databases** running on **completely different physical servers** (machines). Each individual server is called a **shard**.

* **Bank Implementation:** Sharding the database by a **Shard Key**, such as `CustomerID` or `Region`.
* **Server Shard 1 (USA Server):** Holds all customer profiles, accounts, and transaction data for users living in the United States.
* **Server Shard 2 (Europe Server):** Holds all data for users living in Europe.
* **Server Shard 3 (Asia Server):** Holds all data for users living in Asia.


* **Why do it?** A single database server has hardware limits (CPU, RAM, disk space). Sharding allows the bank to scale horizontally by simply adding more cheap servers rather than buying an impossibly expensive supercomputer.



---

### Key Differences Summary

| Feature | Partitioning | Sharding |
| --- | --- | --- |
| **Physical Infrastructure** | All data lives on the **same physical machine/server**.

 | Data is distributed across **multiple distinct physical servers**.

 |
| **Database Instances** | Single database instance handles everything. | Multiple completely independent database instances. |
| **Complexity** | Easy to implement; managed automatically by the DBMS engine. | High complexity; requires a routing layer or application logic to know which server holds which user's data. |
| **Failure Impact** | If the server crashes, the entire database goes offline. | If Shard 2 (Europe) crashes, Shard 1 (USA) and Shard 3 (Asia) keep running smoothly. |
| **Primary Goal** | Optimization of disk storage management and query speed.

 | Overcoming physical hardware limitations to achieve massive scalability.

 |

---


## 16. What is optimistic locking and pessimistic locking?

When multiple users or application threads try to update the exact same bank account record at the same time, the database needs a strategy to handle concurrency. If left unmanaged, one user's updates could silently overwrite another user's changes. Databases solve this using two completely different locking philosophies: **Optimistic Locking** and **Pessimistic Locking**.

---

### The Banking Analogy: The Cooperative Workspace vs. The Locked Vault Room

* **Pessimistic Locking is like a strict bank clerk locking an account file inside a physical vault.** The moment a clerk pulls out a customer's folder to update an address, they lock the cabinet drawer. No other clerk is allowed to look at or touch that folder until the first clerk finishes, signs off, and places it back. It assumes the worst ("someone *will* try to change this at the same time as me").
* **Optimistic Locking is like two clerks working on digital copies of the same account record simultaneously.** They don't block each other from reading the file. However, when Clerk A hits "Save," the system notes the exact time of the save. When Clerk B tries to save their copy a few seconds later, the system checks the file and says: *"Stop! Clerk A updated this record while you were reading it. Your copy is out of date. Please refresh and try again."* It assumes the best ("concurrency conflicts are rare").

---

### The Bank Database Scenario

Imagine a joint checking account shared by a married couple, Alice and Bob, which currently has a balance of **$500**. At the exact same second, Alice tries to withdraw $100 via an ATM, and Bob tries to pay a $50 utility bill online.

#### 1. Pessimistic Locking

This approach locks the targeted data rows immediately upon reading them, forcing any other transaction attempting to access that same data to wait until the lock is released.

* 
**How it executes in SQL:** The application explicitly uses a blocking command like `FOR UPDATE`.


```sql
BEGIN TRANSACTION; [cite_start]-- [cite: 242]

-- Step 1: Lock the row immediately so no one else can touch it
SELECT Balance FROM Accounts WHERE AccountNumber = 'ACC-99' FOR UPDATE;

-- Step 2: Perform calculations and update the balance
UPDATE Accounts SET Balance = Balance - 100 WHERE AccountNumber = 'ACC-99';

COMMIT; [cite_start]-- [cite: 242, 246]

```


* **The Concurrency Flow:** The moment Alice's ATM transaction executes the `SELECT ... FOR UPDATE`, the row for `ACC-99` is locked. When Bob's utility payment attempt runs a split-second later, his transaction freezes and waits. Once Alice's transaction completes and commits, Bob's transaction wakes up, reads the updated balance ($400), and safely processes his deduction.

#### 2. Optimistic Locking

This approach does not place any physical locks on the database records during the read phase. Instead, tables are designed with an extra column, usually named `Version` or `LastUpdated`.

* **How it executes in SQL:** The system reads the row and stores the version number. When writing the update back to the database, it checks if the version matches the original read value.
```sql
-- Step 1: Read the current balance and version number
SELECT Balance, Version FROM Accounts WHERE AccountNumber = 'ACC-99'; 
-- Result: Balance = 500, Version = 1

-- Step 2: Attempt the write, ensuring the version hasn't changed
UPDATE Accounts 
SET Balance = Balance - 100, Version = Version + 1 
WHERE AccountNumber = 'ACC-99' AND Version = 1;

```


* **The Concurrency Flow:** Both Alice's and Bob's operations read `Balance = 500, Version = 1`. Alice's transaction completes a millisecond faster; the database finds a row matching `Version = 1`, updates the balance to $400, and bumps the `Version` to `2`. When Bob's transaction attempts its update, the query searches for a row where `Version = 1`. Because the version is now `2`, the update fails to find a matching row (`Rows Affected = 0`). The database rolls back Bob's transaction safely, prompting the application to refresh the data and try again.

---

### Key Differences Summary

| Feature | Pessimistic Locking | Optimistic Locking |
| --- | --- | --- |
| **Lock Mechanism** | **Physical Locks:** Explicitly locks records at the database level (`FOR UPDATE`). | **Logical Checks:** Relies on application logic tracking `Version` or `Timestamp` values. |
| **When Conflict is Handled** | **Before writing:** Blocks concurrent transactions from reading or modifying the data. | **At compile/save time:** Detects modifications only when attempting to write the change. |
| **System Behavior** | Causes concurrent threads to wait, which can create resource queues or deadlocks.

 | No waiting; transactions fail fast and require the application to retry. |
| **Best Banking Use Case** | **High-conflict scenarios:** Processing high-frequency trading balances where data accuracy outweighs minor delay bottlenecks. | **Low-conflict scenarios:** Updating customer profile details (like modifying phone numbers or email addresses), where concurrent edits are rare. |

---


## 17. How do databases ensure durability after a system crash?

Durability guarantees that once a bank transaction is committed, its changes are safe—even if the power goes out or the operating system crashes the very next millisecond. Databases do not immediately write every change directly to the main database files on disk because random disk I/O is too slow to handle thousands of concurrent banking transactions per second.

Instead, databases ensure durability using a mechanism called **Write-Ahead Logging (WAL)** alongside periodic **Checkpointing**.

---

### The Banking Analogy: The Scrapbook Ledger and the Master Vault Book

Imagine a traditional bank branch with a heavy master ledger book locked inside the vault.

* **The WAL (Scrapbook):** When you make a deposit, the bank teller doesn't instantly walk into the vault, unlock the massive master ledger, flip to your page, and write down the change. Instead, the teller has a small notebook on their desk called a **Log**. They quickly write a line: *"Ticket #402: Add $100 to Account A"*. Writing sequentially in this small notebook takes seconds. Once it's in the log, the deposit is legally secure.
* **The Checkpoint:** At the end of the day (or every 30 minutes), the teller takes the notebook into the vault and copies all the accumulated entries into the permanent master ledger book.

If the bank building catches fire mid-day, the master book might be missing your deposit, but as long as the small log notebook survived on the desk, the auditors can read it and completely rebuild the correct state of the accounts.

---

### The Bank Database Scenario

When an ATM transaction commits, the following internal recovery architecture kicks in to prevent data loss:

#### 1. Write-Ahead Logging (WAL)

The absolute golden rule of database durability is: **The log entry describing the change must be written safely to non-volatile storage (the transaction log file) *before* the actual changes are modified in the main database storage files.**

When you transfer money, the database engine updates the data in its fast temporary memory (RAM Cache) and immediately writes an append-only sequential record of that action to the transaction log on disk. Because sequential disk writing is incredibly fast, it registers the transaction as safely "Committed" immediately.

#### 2. Checkpointing

Periodically, a background process called a **Checkpoint** runs. It flushes all the "dirty pages" (modified account balances currently sitting in fast RAM) over to the main, permanent database storage files on disk. Once written, the log entries prior to the checkpoint are safely marked as synchronized.

---

### The Crash Recovery Process (ARIES Protocol)

If the server suddenly loses power and boots back up, the database engine automatically executes a recovery protocol consisting of two phases:

```
[System Crash] ──► 1. REDO Phase (Replay Log) ──► 2. UNDO Phase (Rollback uncommitted) ──► [Database Consistent]

```

* **The REDO Phase (Rolling Forward):** The engine reads the transaction log starting from the last known checkpoint up to the point of the crash. It replays *all* committed transactions that were written to the log but hadn't yet been copied to the main database files by a checkpoint. This ensures Alice's completed $1,000 deposit is fully intact.
* **The UNDO Phase (Rolling Back):** The engine looks for any transactions that were active and uncompleted when the crash occurred (e.g., Bob's transfer was halfway done). Since these transactions never achieved a `COMMIT` status, the database automatically reverses their partial steps, ensuring data consistency.

---

## 18. Find the second highest salary from an Employee table.

In a banking application, discovering specific ranked values—such as finding the client with the second-highest credit line or the employee with the second-highest salary—is a common query optimization task.

---

### The Banking Analogy: The Grid of Sorted Envelopes

Imagine a HR administrator has physical payroll envelopes for every bank employee laid out on a table. To find the second-highest salary without a computer:

1. The admin sorts all the envelopes from right to left, placing the largest salary amount on the far right.
2. The admin ignores the single largest envelope on the very edge.
3. The next envelope directly adjacent to it is, by definition, the second-highest salary.

If multiple employees earn the exact same highest salary, the admin groups identical amounts together first so they only count as a single unique rank.

---

### The Bank Database Scenario

Consider a standard `Employee` payroll table:

| EmployeeID | EmployeeName | Salary |
| --- | --- | --- |
| 1 | Alice Smith | $95,000 |
| 2 | Bob Jones | $120,000 |
| 3 | Charlie Brown | $120,000 |
| 4 | Diana Prince | $85,000 |

There are two primary ways to solve this using SQL, depending on whether you are using traditional standard commands or modern analytical variations.

#### Solution 1: Using Subqueries (Compatible with almost all SQL Engines)

This approach finds the absolute maximum salary first, filters it out of the dataset, and then finds the maximum value of the remaining records. To handle cases where multiple employees tie for the highest salary (like Bob and Charlie earning $120,000), we use the `DISTINCT` keyword.

```sql
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee
WHERE Salary < (SELECT MAX(Salary) FROM Employee);

```

**How the Database Executes It:**

1. **Inner Query:** `SELECT MAX(Salary) FROM Employee` scans the table and returns $120,000.
2. **Outer Query:** The database filters the table to look *only* at rows where `Salary < 120000`. The remaining salaries are $95,000 and $85,000.
3. **Final Result:** It calculates the `MAX()` of those remaining values, which returns **$95,000**.

#### Solution 2: Using Top-N Filtering (`OFFSET / FETCH` or `LIMIT`)

Modern database engines allow you to sort the entire dataset cleanly and jump directly to a specific row index.

* **MySQL / PostgreSQL Syntax:**
```sql
SELECT DISTINCT Salary 
FROM Employee 
ORDER BY Salary DESC 
LIMIT 1 OFFSET 1;

```


* **SQL Server Syntax:**
```sql
SELECT DISTINCT Salary 
FROM Employee 
ORDER BY Salary DESC
OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY;

```



**How the Database Executes It:**

1. **`DISTINCT` + `ORDER BY`:** It compiles a unique list of salaries and sorts them downwards: `[\$120,000, \$95,000, \$85,000]`.
2. **`OFFSET 1`:** The database skips the first record (`\$120,000`).
3. **`LIMIT 1` / `FETCH NEXT 1`:** It grabs the very next unique row value on the list, returning **$95,000**.

---

## 19. Find duplicate records in a table.

In banking applications, identifying duplicate records is critical for maintaining data cleanliness and compliance. For example, a bank might want to find if a customer accidentally got registered twice with the same email or tax identifier, or if a single financial transaction was mistakenly transmitted more than once.

---

### The Banking Analogy: Checking the Cheque Deposit Log

Imagine a bank clerk reviewing a handwritten daily logbook of cheque deposits. To find if any cheques were recorded more than once by mistake:

1. The clerk groups all log lines by the unique **Cheque Number** and **Account Number**.
2. For each group, the clerk counts how many times that specific cheque appears.
3. If the count for any group is **greater than 1**, the clerk flags that group as a duplicate entry.

---

### The Bank Database Scenario

Consider a `Deposits` log table where a network glitch caused a transaction to be inserted twice:

| TransactionID | AccountNumber | Amount | DepositTime |
| --- | --- | --- | --- |
| TXN-401 | ACC-101 | $500 | 10:15:00 |
| TXN-402 | ACC-102 | $250 | 10:16:00 |
| TXN-403 | ACC-101 | $500 | 10:15:00 |

To identify duplicates, we must look for rows that share identical business keys (such as the same `AccountNumber` and `Amount` occurring at the exact same `DepositTime`). We combine the `GROUP BY` and `HAVING` clauses to filter our aggregates.

#### Solution: Using GROUP BY and HAVING

```sql
SELECT AccountNumber, Amount, DepositTime, COUNT(*) AS OccurrenceCount
FROM Deposits
GROUP BY AccountNumber, Amount, DepositTime
HAVING COUNT(*) > 1;

```

**How the Database Executes It:**

1. 
**`GROUP BY` Step:** The database engine scans the table and bundles the rows into sets based on matching account numbers, amounts, and timestamps.


* Group 1 (`ACC-101`, $500, 10:15:00): Contains 2 rows (`TXN-401`, `TXN-403`).
* Group 2 (`ACC-102`, $250, 10:16:00): Contains 1 row (`TXN-402`).


2. 
**`HAVING` Step:** The database evaluates the aggregated groups and discards any bundle where the total row count is not greater than 1.


3. **Final Result:** It filters out Group 2 and returns Group 1, showing the bank staff exactly which deposit event is duplicated.

---


## 20. Delete duplicate records from a table.

Once duplicate records have been identified in a banking system, the next critical step is safely removing the redundant entries without erasing the legitimate data. This requires a strategy that keeps exactly **one** master record from the duplicate set and deletes the rest.

---

### The Banking Analogy: Voiding the Duplicate Log Entry

Returning to our bank clerk reviewing the handwritten cheque deposit log:

1. The clerk confirms that a deposit of $500 for `ACC-101` at 10:15:00 was written down twice by mistake across two separate lines.
2. To correct this, the clerk doesn't white-out both lines entirely, as that would mean the customer loses their deposit record completely.
3. Instead, the clerk keeps the very first entry line based on its sequential line index number, and draws a red line through the second identical entry to void it.

---

### The Bank Database Scenario

Let's look at our duplicated `Deposits` log table from the previous question:

| TransactionID | AccountNumber | Amount | DepositTime |
| --- | --- | --- | --- |
| TXN-401 | ACC-101 | $500 | 10:15:00 |
| TXN-402 | ACC-102 | $250 | 10:16:00 |
| TXN-403 | ACC-101 | $500 | 10:15:00 |

We want to keep `TXN-401` as the single source of truth and completely remove the duplicate entry `TXN-403`. There are two main ways to write this delete query depending on your database engine.

#### Solution 1: Using a CTE and ROW_NUMBER() (Recommended for PostgreSQL, SQL Server, Oracle)

This elegant approach uses a **Common Table Expression (CTE)** and a window function to group duplicates together and assign a sequential ranking starting at 1 for each unique group.

```sql
WITH CTE_UniqueDeposits AS (
    SELECT TransactionID,
           ROW_NUMBER() OVER (
               PARTITION BY AccountNumber, Amount, DepositTime 
               ORDER BY TransactionID
           ) AS RowNum
    FROM Deposits
)
DELETE FROM Deposits
WHERE TransactionID IN (
    SELECT TransactionID 
    FROM CTE_UniqueDeposits 
    WHERE RowNum > 1
);

```

**How the Database Executes It:**

1. **`PARTITION BY`:** The database temporarily groups rows that share identical values in `AccountNumber`, `Amount`, and `DepositTime`.
2. **`ORDER BY` + `ROW_NUMBER()`:** Inside each group, it assigns numbers sequentially based on the `TransactionID`.
* `TXN-401` gets assigned `RowNum = 1` (The row we want to keep).
* `TXN-403` gets assigned `RowNum = 2` (The duplicate row).


3. **`DELETE WHERE RowNum > 1`:** The database isolates any row with a ranking higher than 1 and targetedly drops `TXN-403` from storage.

#### Solution 2: Using Subqueries with MIN() or MAX() (Compatible with MySQL)

If your database version doesn't support direct target deletes via standard window CTEs, you can use a subquery to look up the minimum valid ID for each unique bundle and delete everything else.

```sql
DELETE FROM Deposits
WHERE TransactionID NOT IN (
    SELECT MinID FROM (
        SELECT MIN(TransactionID) AS MinID
        FROM Deposits
        GROUP BY AccountNumber, Amount, DepositTime
    ) AS TempTable
);

```

**How the Database Executes It:**

1. **Inner Grouping:** The subquery bundles rows by their business keys and uses `MIN(TransactionID)` to find the very first ID generated for that transaction group. This returns a safe list: `['TXN-401', 'TXN-402']`.
2. **The Deletion Step:** The outer query scans the table and purges any row whose `TransactionID` is **not** included in that safe list. As a result, `TXN-403` is wiped out.

---

### Operations Checklist for Bank DBAs

When running duplicate deletions on live production data, financial database administrators always follow a strict safety workflow:

* **Wrap it in a Transaction:** Always execute data purges inside an explicit transaction block (`BEGIN TRANSACTION; ... COMMIT;`) so you can roll back instantly if the affected row count looks incorrect.
* **Verify with a SELECT First:** Before changing the keyword to `DELETE`, run the query as a `SELECT` to make sure you are targeting the exact rows you intend to destroy.
* **Check Foreign Key Constraints:** Ensure that the duplicate records you are deleting do not have child tables (like an `AuditLogs` table) pointing to them, which would trigger referential integrity violations.

---


