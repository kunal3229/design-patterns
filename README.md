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


## 🧩 Factory Design Pattern

The Factory Design Pattern is a creational design pattern that provides a way to encapsulate object creation logic. It allows the code to delegate the instantiation of specific types to a factory class instead of using direct `new` calls.
---
### ✅ Problem It Solves

- Decouples client code from concrete implementations.
- Centralizes object creation logic in one place.
- Enables easy addition of new types with minimal changes.
- Supports the Open/Closed Principle.
---
### 📖 Example Use Case: Document Exporter System

This project simulates an enterprise reporting system that allows exporting reports in different formats: `PDF`, `Excel`, `Word`, and `HTML`.

Each exporter implements a common interface, and the correct exporter is selected at runtime using a factory based on a request parameter.
---
### 🏗️ Components

| Class/Interface             | Responsibility                                             |
|----------------------------|------------------------------------------------------------|
| `DocumentExporter`         | Interface defining the contract for all exporters          |
| `PdfExporter`              | Concrete class to export content in PDF format             |
| `ExcelExporter`            | Concrete class to export content in Excel format           |
| `WordExporter`             | Concrete class to export content in Word format            |
| `HtmlExporter`             | Concrete class to export content in HTML format            |
| `DocumentExporterFactory`  | Contains logic to return appropriate exporter based on input |
| `DocumentExportController` | Spring REST controller to handle export requests           |

### 🔁 How It Works

1. User sends an HTTP request like `POST /export?format=pdf` with raw content.  
2. `DocumentExportController` receives the request.  
3. It asks `DocumentExporterFactory` to provide the correct `DocumentExporter` implementation.  
4. The exporter processes the content and returns the formatted result.

### 🚀 Example API Request & Response

```
POST /export?format=pdf
Content-Type: text/plain

Quarterly Report: Q2 Performance

Response:
[PDF Document]: Quarterly Report: Q2 Performance
```

### 🧠 When to Use Factory Pattern

```
1. When the client code needs to create objects but should not depend on their concrete classes.  
2. When object creation logic is complex or repetitive.  
3. When new object types are added frequently.
```

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

