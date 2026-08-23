## What is an ATM?
*  perform **basic financial transactions** such as **withdrawing** cash and **checking account balances** using a debit or credit card and a **secure PIN.**

## 1. Clarifying Requirements
* ask thoughtful questions to **uncover hidden assumptions, clarify ambiguities, and define the system's scope** more precisely.
* types of transcation ? -  cash withdrawal, cash deposit, and balance inquiry."
* type of authentication ? - pin
* supported denomination ? - $100, $50, $20, and $10 (dispense largest 1st  ) 
* Do we need to take input from the user, or can we hardcode a sequence of operations? - hard code it
* daily limit ? not needed now

### Functional Requirements
* Authenticate users via card number and PIN
* Support three transaction types: withdrawal, deposit, and balance inquiry
* Dispense cash using the largest denominations first ($100, $50, $20, $10)
* Validate both account balance and ATM cash inventory before dispensing
* Track ATM state transitions (idle, card inserted, authenticated)
* Simulate bank operations (authentication, balance check, debit, credit) via an in-memory service

### 2. Identifying Core Entities
* TransactionType enum : WITHDRAWAL, DEPOSIT, and BALANCE_INQUIRY.
* ATMState enum: IDLE, CARD_INSERTED, AUTHENTICATED.
* Denomination enum : HUNDRED(100), FIFTY(50), TWENTY(20), TEN(10).
* Card class (im) - When a user inserts a card, we need to capture the card's data: card number, PIN, and which bank account it's linked to
* Account class -   tracks the account number and current balance.
* Transaction class - captures what happened: the type, amount, account number, and timestamp.
* CashDispenser - loading money/denomination exist
* DenominationHandler  - to dispense different denimantor
* Bank Service -   simulation which manages cards, accounts, authentication, and balance operations
* ATM - facade that ties the entire system together

| Entity | Type | Responsibility |
| :--- | :--- | :--- |
| `TransactionType` | Enum | Transaction categories: WITHDRAWAL, DEPOSIT, BALANCE_INQUIRY |
| `ATMState` | Enum | ATM lifecycle states: IDLE, CARD_INSERTED, AUTHENTICATED |
| `Denomination` | Enum | Bill values: HUNDRED(100), FIFTY(50), TWENTY(20), TEN(10) |
| `Card` | Data Class | Card data: number, PIN, linked account |
| `Account` | Data Class | Account data: number, mutable balance |
| `Transaction` | Data Class | Transaction record: type, amount, timestamp |
| `CashHandler` | Interface | Contract for chain-of-responsibility cash dispensing |
| `ATMStateHandler` | Interface | Contract for state-specific ATM behavior |
| `DenominationHandler` | Core Class | Handles dispensing for one denomination, delegates remainder |
| `CashDispenser` | Core Class | Manages cash inventory, builds denomination chain |
| `BankService` | Core Class | Simulates bank: authentication, balance, debit, credit |
| `ATM` | Core Class (Singleton) | Orchestrates state machine, coordinates all components |

### 3. Designing Classes and Relationships
*  For each class, we'll define what data it holds (attributes) / reference and what it can do (methods).
* We'll work bottom-up: simple types first, then data containers, then interfaces, then the classes with real logic.

### Enums & Denominations

#### TransactionType
| Type | Values |
| :--- | :--- |
| `TransactionType` | WITHDRAWAL, DEPOSIT, BALANCE_INQUIRY |

#### ATMState
| Type | Values |
| :--- | :--- |
| `ATMState` | IDLE, CARD_INSERTED, AUTHENTICATED |

#### Denomination Details
| Value | Face Value | Purpose |
| :--- | :--- | :--- |
| `HUNDRED` | 100 | Largest bill, used first |
| `FIFTY` | 50 | Second priority |
| `TWENTY` | 20 | Third priority |
| `TEN` | 10 | Smallest bill, used last |

### Custom Exception
| Class | Attributes | Methods / Constructors |
| :--- | :--- | :--- |
| `ATMException` | *None* | `+ATMException(message: string)` |

### Data Classes
### Data Classes & Models

#### `Card`
| Fields / Attributes | Methods / Constructors |
| :--- | :--- |
| `- cardNumber: string`<br>`- pin: string`<br>`- accountNumber: string` | `+ Card(cardNumber: string, pin: string, accountNumber: string)` |

---

#### `Account`
| Fields / Attributes | Methods / Constructors |
| :--- | :--- |
| `- accountNumber: string`<br>`- balance: double` | `+ Account(accountNumber: string, initialBalance: double)`<br>`+ debit(amount: double)`<br>`+ credit(amount: double)` |

---

#### `Transaction`
| Fields / Attributes | Methods / Constructors |
| :--- | :--- |
| `- id: string`<br>`- type: TransactionType`<br>`- amount: double`<br>`- accountNumber: string`<br>`- timestamp: string` | `+ Transaction(id: string, type: TransactionType, amount: double, accountNumber: string)` |

---

### Enumerations

#### `«enumeration» TransactionType`
| Values |
| :--- |
| `WITHDRAWAL` |
| `DEPOSIT` |
| `BALANCE_INQUIRY` |

### Interfaces

* Interfaces define contracts for interchangeable behavior. Our ATM uses two interfaces: one for the Chain of Responsibility pattern (cash dispensing) and one for the State pattern (ATM behavior).

1. CashHandler defines the contract for a single link in the cash dispensing chain.
    * When the ATM needs to dispense $170, it should try $100 bills first, then $50s, then $20s, then $10s. Each denomination handler needs to process what it can and pass the remainder to the next handler. This is the Chain of Responsibility pattern, and the interface defines what each handler must do.
2. ATMStateHandler defines the contract for state-specific ATM behavior.
    * Each state implementation will handle the valid operations and throw ATMException for invalid ones. For example, IdleState.withdraw() throws "Please insert a card first," while AuthenticatedState.withdraw() processes the withdrawal normally.

### Core Classes
1. DenominationHandler
    *   Each denomination in the ATM needs its own handler that knows how many bills of that denomination are available and how to dispense them. 
2. CashDispenser
    * CashDispenser manages the denomination chain and cash inventory.
    * CashDispenser has a composition relationship with DenominationHandler. The CashDispenser creates and owns all handlers.
3. BankService
    * BankService simulates the bank backend with in-memory accounts and cards.
4. ATM
    * ATM is the singleton facade that orchestrates the entire system.

### 3.2 Key Design Patterns
The core challenges are managing state transitions (what operations are valid when?) and dispensing cash across multiple denominations (how to try each denomination in order?). These map naturally to the State and Chain of Responsibility patterns.

1. State Pattern
The Problem: The ATM behaves completely differently depending on whether a card is inserted and whether the user has authenticated. Without a clean abstraction, every public method on ATM would start with:
```
if state is IDLE then reject
else if state is CARD_INSERTED then maybe allow
else if state is AUTHENTICATED then allow
```
With six methods and three states, that's 18 conditional branches scattered across the ATM class. Adding a new state (like OUT_OF_SERVICE) means editing every method.

The Solution: The State pattern encapsulates each state's behavior in a dedicated class. The ATM delegates to its current state handler, which knows what's valid and what isn't.

The State pattern isolates each state's rules into a single class. Adding OUT_OF_SERVICE means adding one class, not editing six methods. In interviews, this is one of the clearest demonstrations of the Open/Closed Principle.

2. Chain of Responsibility Pattern
The Problem: Dispensing $170 requires trying $100 bills first (dispense one, $70 remaining), then $50 bills (dispense one, $20 remaining), then $20 bills (dispense one, done). Each denomination needs to process what it can and hand off the remainder.

The Solution: The Chain of Responsibility pattern links denomination handlers in order from largest to smallest. Each handler in the chain processes what it can and passes the remainder to the next handler.

