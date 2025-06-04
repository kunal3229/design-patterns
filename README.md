# Design Patterns

# 🔀 Strategy Design Pattern

## 📌 Intent
The Strategy Pattern is a behavioral design pattern that enables selecting an algorithm’s behavior at runtime. It defines a family of interchangeable algorithms, encapsulates each one, and allows them to be swapped easily.

## ❗ Problem
Using long chains of conditionals (if-else or switch-case) to determine which algorithm or logic to execute leads to rigid and hard-to-maintain code. This violates the Open-Closed Principle and makes the code difficult to test or extend.

## ✅ Solution
The Strategy Pattern encapsulates algorithms (strategies) behind a common interface and delegates the execution to one of the concrete strategies. This allows the behavior to be changed dynamically and independently of the client using it.

## 💡 Analogy
Consider a notification system where messages can be sent via Email, SMS, or WhatsApp. Instead of hardcoding conditions for each, you define a NotificationStrategy interface and create separate strategy implementations for each channel. The strategy is selected based on input and used to send the message.

## 🧱 Structure
- NotificationStrategy: Interface with a send(to, message) method
- EmailNotificationStrategy, SmsNotificationStrategy, WhatsAppNotificationStrategy: Implementations of the strategy
- NotificationService: Context class that uses the appropriate strategy
- NotificationController: Accepts type, to, and message; delegates to NotificationService

## 🚀 Example Endpoint
Test with:
GET http://localhost:8080/notify?type=email&to=kunal@example.com&message=HelloStrategy

Logs:
Sending Email to: kunal@example.com - HelloStrategy

## ⚙️ Benefits
- Adheres to Open-Closed Principle
- Strategies are interchangeable and testable in isolation
- Reduces complexity from conditional logic
- Easy to extend with new strategies without touching existing logic

## 🧠 Interview Tips
- Use real-world examples like payment processing, routing, or discount engines
- Demonstrate how it replaces conditionals with polymorphism
- In Spring Boot, use @Component for strategies and @Autowired + @Qualifier for injecting them
- Mention how Strategy and Factory patterns often complement each other

## 🆚 Strategy vs Factory
| Criteria     | Strategy Pattern                             | Factory Pattern                           |
|--------------|----------------------------------------------|--------------------------------------------|
| Purpose      | Choose behavior                              | Choose object type                         |
| Focus        | Encapsulate interchangeable logic/algorithms| Encapsulate object creation                |
| Type         | Behavioral                                   | Creational                                 |
| Example Use  | Payment types, discount policies             | Notification sender factory                |

## 🔗 Related Patterns
- Factory Pattern – Used to instantiate the correct strategy
- State Pattern – Similar, but for representing object states
- Command Pattern – Encapsulates requests with a common interface

💼 Use the Strategy pattern in your Spring Boot apps to decouple decision-making from behavior execution and make your business logic clean, testable, and extensible.


# 🏭 Factory Design Pattern

## 📌 Intent
The Factory Pattern is a creational design pattern that abstracts the process of object creation by delegating it to a factory class. It allows client code to work with interfaces or abstract types, while the factory decides which concrete implementation to return.

## ❗ Problem
When we have multiple implementations like PdfDocumentExporter, WordDocumentExporter, and ExcelDocumentExporter, directly instantiating them using new in the controller or service leads to tight coupling and violates the Open/Closed Principle. It becomes hard to maintain and extend the codebase.

## ✅ Solution
Encapsulate the object creation logic in a Factory class (DocumentExporterFactory), which returns the appropriate exporter based on input. The controller or service uses a common interface (DocumentExporter) and delegates actual implementation details to the factory.

## 💡 Analogy
Think of an export feature on a website where users can choose to export reports in different formats like PDF, Word, or Excel. Instead of putting multiple if-else checks in the controller, we use a factory to handle that logic and return the correct exporter.

## 🧱 Structure
- DocumentExporter: Common interface with export(String content) method
- PdfDocumentExporter, WordDocumentExporter, ExcelDocumentExporter: Implementations of DocumentExporter
- DocumentExporterFactory: Accepts a format string and returns the correct exporter
- DocumentController: Accepts format and content, delegates to factory, and calls export()

## 🚀 Example Endpoint
GET http://localhost:8080/export?format=pdf&content=HelloFactory

Logs:
Exporting PDF Document: HelloFactory

## ⚙️ Benefits
- Eliminates need for multiple if-else or switch-case logic in controllers/services
- Promotes Open/Closed Principle — new exporters can be added without changing existing code
- Enhances testability — each exporter can be unit tested independently
- Clean separation of concerns — factory handles creation, exporters handle business logic

## 🧠 Interview Tips
- Emphasize that Factory encapsulates object creation logic
- Point out that it reduces tight coupling and improves maintainability
- Mention how Spring itself uses factories (e.g., BeanFactory, ApplicationContext)
- This pattern is very useful when object instantiation depends on runtime parameters

## 🆚 Strategy vs Factory
| Criteria     | Strategy Pattern                               | Factory Pattern                            |
|--------------|------------------------------------------------|---------------------------------------------|
| Purpose      | Choose behavior                                | Choose object type                          |
| Focus        | Encapsulates interchangeable logic/algorithms | Encapsulates object creation                |
| Example Use  | Discount calculation, notification strategy    | Document exporter, payment gateway provider |

## 🔗 Related Patterns
- Strategy Pattern – Each DocumentExporter implementation can also follow Strategy behavior
- Abstract Factory – For creating related object families (e.g., themed UI widgets)
- Singleton – Factory may return singleton exporter instances if required

💼 Use the Factory Pattern in Spring Boot apps to separate object creation from business logic, especially when you have multiple interchangeable implementations selected at runtime (like export formats, parsers, payment processors, etc).


# 🧱 Builder Design Pattern

## 📌 Intent

The Builder Pattern is a creational design pattern used to construct complex objects step-by-step. It is especially helpful when an object has a large number of optional parameters, and a constructor with many parameters becomes hard to read, understand, and maintain.

---

## ❗ Problem Statement

Creating objects with multiple optional fields using constructors or setters leads to:

- Confusing constructor overloads (telescoping constructors)
- Hard-to-read code
- Error-prone assignments (wrong order of parameters)

---

## ✅ Solution

Builder Pattern solves this by:

- Forcing required fields through constructor in the builder class
- Allowing optional fields to be set through fluent `setter-like` methods
- Returning the fully constructed object via a `build()` method

The final object is immutable and constructed in a readable, safe, and extensible way.

---

## 🛠️ Real-World Analogy

Imagine you're ordering a pizza:

- You must choose base and size (required)
- Toppings, cheese, and sauces are optional
- A builder allows you to specify the essentials first, and then choose optional extras fluently

---

## 💡 Spring Boot Example Summary

We implement a NotificationMessage object that might have many optional fields like:

- CC, BCC, attachments
- Retry policies
- Priority, headers, etc.

Using Builder, we:

- Provide required fields (to, subject, body)
- Set optional fields using method chaining
- Keep the NotificationMessage immutable
- Avoid a messy constructor or huge number of setters

Controller shows how easily we can build and send a notification using this builder.

---


## ⚖️ Advantages

| Aspect             | Benefit                                              |
|--------------------|------------------------------------------------------|
| Readability        | Fluent, easy-to-understand object creation           |
| Maintainability    | No need to modify constructor for new optional fields|
| Immutability       | Final fields + no setters prevent accidental mutation|
| Extensibility      | Easy to add validation or logic inside build()       |

---

## 🧠 Interview Tips

- Explain that it separates construction from representation
- Discuss why this is better than constructors or setter chaining
- Compare manual builder vs Lombok @Builder
- Mention that it helps avoid inconsistent object states
- Be ready to show how you’d write validations in the build() method

📝 Also useful when dealing with:

- DTOs with optional fields
- Query Builders (like Hibernate Criteria API)
- Config objects (like Retrofit, OkHttp clients)

---

## 🆚 Manual vs Lombok @Builder

| Feature         | Manual Builder             | Lombok @Builder         |
|------------------|-----------------------------|--------------------------|
| Control          | Full (validation, defaults) | Limited                  |
| Boilerplate      | More                        | Less                     |
| Readability      | High                        | High                     |
| Use Case         | Complex logic               | Simple DTOs              |

## 🔗 Related Patterns

- Factory Pattern – For creating simple objects with no optional fields
- Prototype Pattern – For cloning existing objects
- Fluent Interface – Often combined with builder for clean chaining

---

💼 A must-have pattern in your Spring Boot backend toolkit, especially when designing configuration classes, request models, or messages where optional and required data need clear separation.

