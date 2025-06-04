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

