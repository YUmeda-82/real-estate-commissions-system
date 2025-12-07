# Software Requirements Specification
## Real Estate Commission System

> **Academic Context:** This specification is based on a Software Engineering assignment from FATEC São Paulo.  
> **Original Document:** [View PDF (PT-BR)](../academic/original-specification-ptbr.pdf)<br>
> [Go Back To Home Page](../../README.md)

---

## 📋 Table of Contents
1. [Project Scope](#project-scope)
2. [Domain Model](#domain-model)
3. [Business Rules](#business-rules)
4. [Actors](#actors)
5. [Use Cases](#use-cases)
6. [Functional Requirements](#functional-requirements)
7. [Non-Functional Requirements](#non-functional-requirements)

---

## 🎯 Project Scope

Development of a **Commission Verification and Payment Module** that maintains and tracks financial records related to commissions earned by real estate brokers for completed deals.

The system manages the entire lifecycle of a commission, from deal registration through calculation, approval, and payment recording.

---

## 🏛 Domain Model

### Core Domain Classes

| Class | Description |
|-------|-------------|
| **Broker** | Represents the licensed professional acting as an intermediary agent. This is the entity who will receive the commission payment. |
| **Deal** | Represents the completed commercial transaction event. Stores contract data including closing date, total sale amount, and property identification. |
| **Commission** | Represents the financial relationship generated specifically by a successful Deal completed by a Broker. Calculates and stores the final monetary amount to be paid and its payment status. |

### Entity Relationship
```
Broker 1 ──── * Deal 1 ──── 1 Commission
```

---

## 📜 Business Rules

| ID | Rule |
|----|------|
| **BR-01** | Every Deal must have an associated Broker. |
| **BR-02** | Commission is calculated exclusively based on the Deal amount. |
| **BR-03** | Each Deal generates exactly one Commission. |
| **BR-04** | Commission can only be paid after approval. |
| **BR-05** | Commission payment must record date and responsible party. |
| **BR-06** | Commission payment history for each Broker must be queryable. |
| **BR-07** | Commission payment records cannot be deleted (audit trail). |

---

## 👥 Actors

### Administrator
User responsible for operational and financial system management. Has privileges to:
- Maintain registries (brokers and deals)
- Execute commission calculations
- Approve commission amounts
- Record payment execution

### Broker
End user and system beneficiary. Interaction is consultative only:
- View personal deal history
- Track commission status and amounts receivable

---

## 📊 Use Cases

### UC-01: Manage Broker
**Actor:** Administrator  
**Goal:** Maintain broker registry (CRUD operations)

**Main Flow:**
1. Administrator accesses broker list
2. System displays registered brokers
3. Administrator selects operation:
   - **Create:** Input broker data → System validates → Persists record
   - **Update:** Select broker → Modify data → System validates → Updates record
   - **Deactivate:** Select broker → Confirm → System marks as inactive
   - **View:** Select broker → System displays details

**Business Validation:**
- CPF/ID must be unique
- Cannot deactivate already inactive broker
- All mandatory fields must be filled

---

### UC-02: Manage Deal
**Actor:** Administrator  
**Goal:** Maintain deal registry (CRUD operations)

**Prerequisites:**
- At least one active Broker in the system

**Main Flow:**
1. Administrator accesses deal list
2. System displays registered deals
3. Administrator selects operation:
   - **Create:** Select broker → Input deal data (property, amount, date) → System validates → Persists with status "Pending Calculation"
   - **Update:** Select deal → Modify data → System validates → Updates record
   - **Cancel:** Select deal → Confirm → System changes status to "Cancelled"
   - **View:** Select deal → System displays details

**Business Validation:**
- Sale amount must be > 0
- Date cannot be in the future
- Broker selection is mandatory
- **Critical:** Deals with "Calculated" or "Paid" commissions are locked from editing

---

### UC-03: Calculate Commission
**Actor:** Administrator  
**Goal:** Apply percentage to sale amount and generate financial value payable to broker

**Prerequisites:**
- At least one Deal with status "Pending"

**Main Flow:**
1. Administrator accesses calculation functionality
2. System displays list of "Pending" deals
3. Administrator selects target deal
4. System displays deal details and requests commission percentage
5. Administrator inputs percentage (e.g., 5%)
6. System calculates amount (Sale Value × %) and displays for review
7. Administrator confirms
8. System records commission value and changes status to "Calculated"

**Validations:**
- Percentage must be > 0% and ≤ 100%
- Administrator can simulate different percentages before confirming

---

### UC-04: Approve Commission
**Actor:** Administrator  
**Goal:** Validate and authorize calculated commissions for payment

**Prerequisites:**
- At least one Commission with status "Calculated"

**Main Flow:**
1. Administrator accesses approval functionality
2. System displays "Calculated" commissions (Broker, Sale Value, Commission Amount)
3. Administrator analyzes and selects commission(s) to authorize
4. Administrator clicks "Approve"
5. System requests confirmation
6. Administrator confirms
7. System changes status to "Approved" (ready for payment)

**Alternative Flow:**
- **Rejection:** Administrator can reject calculation → System reverts to "Pending" for recalculation

---

### UC-05: Record Payment
**Actor:** Administrator  
**Goal:** Officially record that commission was transferred to broker

**Prerequisites:**
- At least one Commission with status "Approved"

**Main Flow:**
1. Administrator accesses pending payments list
2. System displays approved commissions
3. Administrator selects record(s) and inputs payment date
4. Administrator confirms operation
5. System changes status to "Paid" and archives in financial history

**Features:**
- **Batch Payment:** Multiple commissions can be processed simultaneously with same payment date
- Payment records are immutable (audit compliance)

---

### UC-06: List Broker Commissions
**Actor:** Broker  
**Goal:** View personal financial history and receivable amounts

**Main Flow:**
1. Broker logs into system
2. Broker accesses "My Commissions" menu
3. System filters commissions by logged-in broker's CPF/ID
4. System displays: Date, Sale Value, Commission Amount, Status (Pending/Calculated/Approved/Paid)
5. Broker can apply filters (date range, status)

---

### UC-07: List Broker Deals
**Actor:** Broker  
**Goal:** View history of sales/contracts under their responsibility

**Main Flow:**
1. Broker logs into system
2. Broker accesses "My Deals" menu
3. System retrieves deals where broker is registered as responsible
4. System displays: Sale Date, Property ID, Total Sale Value, Current Status
5. Broker can view detailed information for each deal

---

## ⚙️ Functional Requirements

The system shall:

- ✅ **FR-01:** Maintain Broker registry (Create, Read, Update, Deactivate)
- ✅ **FR-02:** Maintain Deal registry (Create, Read, Update, Cancel)
- ✅ **FR-03:** Manage Commission lifecycle (Calculate, Approve, Record Payment)
- ✅ **FR-04:** Calculate and associate Commission with Deal
- ✅ **FR-05:** List Deals for a specific Broker
- ✅ **FR-06:** List Commission statement/history

---

## 🔒 Non-Functional Requirements

### Usability
- **NFR-01:** Interface must be intuitive, allowing administrators and brokers to quickly find desired information
- **NFR-02:** System must display clear error messages for easy recovery

### Reliability
- **NFR-03:** Maintain secure commission payment history, preventing accidental deletions
- **NFR-04:** Automatically log date and responsible party for all payment actions

### Security
- **NFR-05:** Require login for access
- **NFR-06:** Ensure users only view their own information (data isolation)
- **NFR-07:** Protect personal and financial data stored in the system

### Compatibility
- **NFR-08:** Support major modern web browsers

### Maintainability
- **NFR-09:** Develop with clean architecture to facilitate future maintenance and evolution
- **NFR-10:** Standardize screen layouts for consistent navigation

---

## 🔄 Commission Status Flow

```
Deal Registered
    ↓
[Pending] → Calculate → [Calculated] → Approve → [Approved] → Record Payment → [Paid]
                                ↓ Reject
                            [Pending]
```

---

## 📝 Notes

- This specification originated from an academic Software Engineering II course at FATEC São Paulo
- The original document includes UI prototypes and detailed test plans (available in PDF)
- Implementation uses event-driven architecture with Apache Kafka for asynchronous processing