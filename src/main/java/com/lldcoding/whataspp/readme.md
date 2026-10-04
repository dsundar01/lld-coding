# WhatsApp

Flow -> 1 to 1 Chat
- user A can send message to others users in the system
- message from user order should be maintained
- server should be decided the message time.

Entity (Story)
- Whatsapp has set of users
- users can message each other.

Class Design
- rules should be within the class.

3. Misplaced Responsibilities (SOPT / Clean Architecture Violation)
   User class doing too much: A User entity shouldn't handle networking, routing, messaging logic, or maintaining chat storage.

Direct coupling: user.sendMessage(receiver, ...) forces direct peer-to-peer invocation between domain models.

Facade anti-pattern: Your WhatsAppFacade class currently just contains a main method instead of actually acting as a Facade pattern to orchestrate services (e.g., UserService, ChatService, NotificationService).