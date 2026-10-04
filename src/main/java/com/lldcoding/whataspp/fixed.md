Your design works as a basic prototype, but **it contains a critical bug where sent messages are never stored in the sender's chat history**, alongside a few design anti-patterns for Object-Oriented Design (LLD).

---

### Critical Bug: Sent Messages Aren't Saved

In `sendMessage()`, you check if `this.messages` contains `receiver.getName()`, but you **never add `message` to `this.messages**`.

```java
// BUG in User.java:
if (!this.messages.containsKey(receiver.getName())) {
    this.messages.put(receiver.getName(), new LinkedList<>());
}
// Missing: this.messages.get(receiver.getName()).add(message);

```

* **Result:** John will see 0 messages when printing his chat history, while Jane will see 1 message.

---

### Core Design Issues & Fixes

1. **Missing Facade Pattern Realization**
* Your class is named `WhatsAppFacade`, but it is just a driver class with a `main()` method.
* A true **Facade** should hide the complexity of the chat service (user management, message routing) behind a single interface (e.g., `ChatService.sendMessage(senderId, receiverId, content)`).


2. **Keys mapped by `Name` instead of `ID**`
* Using `HashMap<String, LinkedList<Message>>` with `user.getName()` as the key causes collisons if two users share the same name ("John").
* **Fix:** Key by `User ID` or store a conversation object keyed by `userId`.


3. **Inappropriate Abstractions & Data Types**
* Declare fields using interfaces (`Map`, `List`) instead of concrete implementations (`HashMap`, `LinkedList`).
* Hardcoded `Message ID = 1` in `sendMessage()` means all messages share the same ID.


4. **Tightly Coupled Class Responsibilities**
* `User` handles user identity, message storage, and communication logic. In clean architecture:
* `User` represents identity.
* `ChatService` / `MessageService` handles routing and sending.
* `Conversation` / `ChatHistory` manages storage.





---

### Improved & Corrected LLD Implementation

Here is a refined, bug-free implementation using clean Java principles and a proper Facade pattern:

#### 1. Message Model

```java
package com.lldcoding.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Message {
    private final String id;
    private final String content;
    private final int senderId;
    private final int receiverId;
    private final LocalDateTime timestamp;
}

```

#### 2. User Model

```java
package com.lldcoding.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final int id;
    private final String name;
}

```

#### 3. WhatsApp Service (True Facade)

```java
package com.lldcoding.whatsapp;

import java.time.LocalDateTime;
import java.util.*;

public class WhatsAppFacade {
    private final Map<Integer, User> users = new HashMap<>();
    // Maps userId -> Map of (otherUserId -> list of messages)
    private final Map<Integer, Map<Integer, List<Message>>> userChats = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getId(), user);
        userChats.put(user.getId(), new HashMap<>());
    }

    public void sendMessage(int senderId, int receiverId, String content) {
        if (!users.containsKey(senderId) || !users.containsKey(receiverId)) {
            throw new IllegalArgumentException("Invalid sender or receiver ID");
        }

        Message message = new Message(
            UUID.randomUUID().toString(),
            content,
            senderId,
            receiverId,
            LocalDateTime.now()
        );

        // Store in sender's history
        userChats.get(senderId)
                 .computeIfAbsent(receiverId, k -> new ArrayList<>())
                 .add(message);

        // Store in receiver's history
        userChats.get(receiverId)
                 .computeIfAbsent(senderId, k -> new ArrayList<>())
                 .add(message);
    }

    public List<Message> getChatHistory(int userId, int otherUserId) {
        return userChats.getOrDefault(userId, Collections.emptyMap())
                        .getOrDefault(otherUserId, Collections.emptyList());
    }
}

```

#### 4. Driver Demonstration

```java
package com.lldcoding.whatsapp;

public class Main {
    public static void main(String[] args) {
        WhatsAppFacade whatsapp = new WhatsAppFacade();

        User john = new User(1, "John");
        User jane = new User(2, "Jane");

        whatsapp.registerUser(john);
        whatsapp.registerUser(jane);

        whatsapp.sendMessage(john.getId(), jane.getId(), "Hello Jane!");
        whatsapp.sendMessage(jane.getId(), john.getId(), "Hello John! How are you?");

        System.out.println("John's chat with Jane:");
        whatsapp.getChatHistory(john.getId(), jane.getId())
                .forEach(m -> System.out.println((m.getSenderId() == john.getId() ? "Me: " : "Jane: ") + m.getContent()));
    }
}

```