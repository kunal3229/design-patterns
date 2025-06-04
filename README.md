# design-patterns

**🧩 Strategy Pattern — Behavioral Design Pattern**
**✅ Definition**
The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It allows the algorithm to vary independently from clients that use it.
In simple terms: Use Strategy when you have multiple ways of doing something, and you want to switch between them easily — at runtime.

**📦 Real-World Analogy**
Imagine you have different ways to send a notification: via Email, SMS, or Slack. Each of these is a different strategy for delivering a message. Rather than using if-else or switch logic, you define each strategy separately and inject it as needed.

**🧱 Structure**
NotificationStrategy (interface)
├── EmailNotificationStrategy
├── SmsNotificationStrategy
├── SlackNotificationStrategy

NotificationContext (selects and uses the strategy dynamically)
NotificationController (API layer that receives requests)

**💻 Implementation Summary**
1. Each strategy (EmailNotificationStrategy, etc.) implements the NotificationStrategy interface.
2. NotificationContext uses Spring's ApplicationContext to dynamically retrieve and use the desired strategy bean.
3. The controller delegates the work to NotificationContext based on the channel query parameter.

**🔥 Benefits**
1. Adheres to the Open/Closed Principle — you can add new strategies without modifying existing logic.
2. Avoids messy if-else chains.
3. Promotes cleaner, testable, and maintainable code.
4. Strategies are easily mockable in unit tests.
