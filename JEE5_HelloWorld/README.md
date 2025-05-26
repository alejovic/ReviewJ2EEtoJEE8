# **Hello World JEE 5**

## 🔹 Short History

- **Released**: May 2006 by **Sun Microsystems**.
- **Name Change**: First version to adopt the new name **"Java EE"**, dropping "J2EE".
- **Major Shift**: Focused on **simplification**, **annotations**, and **POJO-based development**.
- **Highlights**:
  - Introduced **EJB 3.0**, a complete overhaul with simpler APIs.
  - Embraced **Java annotations** to reduce boilerplate code and XML configuration.
  - Improved support for **web services** with JAX-WS.

Tutorial: https://docs.oracle.com/javaee/5/tutorial/doc/

---

JEE 5 Components

Java EE 5 built on top of Java SE 5 and aimed to simplify enterprise application development using annotations and dependency injection.

### 1. Web Tier
- **Servlets 2.5** – Minor update, integrated more easily with annotations.
- **JSP 2.1** – Integrated with Expression Language (EL) improvements.
- **JSF 1.2 (JavaServer Faces)** – Component-based UI framework.
- **Web Container** – Hosts servlets, JSPs, JSF, and manages HTTP requests.

### 2. Business Tier
- **EJB 3.0**:
  - POJO-style session beans (no interfaces required).
  - **Entity Beans** replaced by **JPA (Java Persistence API)**.
  - Simplified dependency injection via `@EJB`, `@Resource`, etc.
- **Message-Driven Beans (MDBs)** – Improved ease of use for JMS listeners.

### 3. Integration Tier
- **JMS 1.1** – Unified messaging API (point-to-point and publish-subscribe).
- **JCA 1.5** – Standard connectors for enterprise integration.
- **JAX-WS 2.0** – Replaced JAX-RPC; provided modern SOAP web services.
- **JAX-B 2.0** – Java/XML binding framework.

### 4. Persistence Tier
- **JPA 1.0 (Java Persistence API)** – Simplified ORM persistence model, annotation-driven.
- **JDBC 3.0** – Continued support for SQL-based DB access.

### 5. Other Core APIs
- **JTA** – Transaction management.
- **JNDI** – Resource lookup.
- **JavaMail** – Enterprise email support.
- **Bean Validation (pre-standard)** – Often used via third-party libraries (standardized in Java EE 6).

## 🔹 Common Design Patterns in Java EE 5

| Pattern                 | Purpose                                                                 |
|-------------------------|-------------------------------------------------------------------------|
| **Front Controller**     | JSF’s `FacesServlet` acts as the central request handler.              |
| **DAO**                  | Still used to separate data access logic from business logic.          |
| **Service Locator**      | Less needed due to dependency injection, but still applies in some cases. |
| **Business Delegate**    | Shields presentation tier from business tier; now easier with annotations. |
| **Transfer Object (DTO)**| Used with JPA or web service data exchange.                            |
| **Session Facade**       | Wraps business logic to expose a unified interface, often a stateless bean. |
| **Dependency Injection** | Promoted by `@EJB`, `@Resource`, etc., to simplify resource access.    |

---

## Hello World [JEE5]

Based on the archetype https://maven.apache.org/archetypes/maven-archetype-j2ee-simple/ with slight changes,this is a
J2EE example application that follows the J2EE Blueprints series to create a J2EE enterprise application.

The purpose of this application is to demonstrate the capabilities of the J2EE platform and is written
for learning purposes (for example to illustrate the many different design patterns on various part of J2EE).
It is not coded for performance and is not intended for performance benchmarks.

The application follows the J2EE 1.4 specs which is a set of related specifications and can be seen as a single
standard for implementing and deploying enterprise applications. it also demonstrates the following:

How to use Java Server Pages (jsp).
How to use Java Servlets.
How to use Enterprise JavaBeans (EJB).
How to use Java Message Service (JMS).
How to use JSP Standard Tag Library (JSTL).
How to use the Java API for XML-based RPC (JAX-RPC).
How to use SOAP.
How to exchange and process XML-based documents.
How to develop flexible, scalable, cross-plaform enterprise applications.
How to use the Java BluePrints guidelines and patterns.

Illustrate basic usage of J2EE technology with current best practices, such as:

- JSF 1.2
- MyFaces 2.1 (https://myfaces.apache.org/#/oldVersions?id=apache-myfaces-core-21)
- Struts
- JSP
- Junit 4.0 [Java 1.5]

The "Hello World" example application can be deployed in JBoss Application Server (AS) 5.x which is fully compliant with Java EE 5. 
