# **Hello World J2EE 1.3**

The original J2EE specification was developed by Sun Microsystems.

Starting with J2EE 1.3, the specification was developed under the Java Community Process.
JSR 58 specifies J2EE 1.3 and JSR 151 specifies the J2EE 1.4 specification.

- **Released**: September 2001 by **Sun Microsystems**.
- **Objective**: To improve the **robustness** and **scalability** of enterprise applications.
- **Focus**: Enhanced **EJB** capabilities, **deployment standardization**, and **J2EE Connector Architecture**.
- **Limitations**: No native web services support; J2EE 1.3 relied on third-party tools or APIs for SOAP-based services.

**The J2EE 1.3 SDK was first released by Sun as a beta in April 2001**

Tutorial: https://docs.oracle.com/javaee/1.3/tutorial/doc/

---

J2EE 1.3 Components

J2EE 1.3 continued the evolution of a component-based, multi-tiered enterprise architecture based on Java.

### 1. Web Tier
- **Servlets 2.3** – Provided request/response handling.
- **JSP 1.2** – Used for dynamic HTML generation using embedded Java.
- **Web Container** – Manages servlet/JSP lifecycle and interactions.

### 2. Business Tier
- **Enterprise JavaBeans (EJB) 2.0**:
  - **Session Beans** – Stateless and stateful beans for business logic.
  - **Entity Beans** – Persistent beans with Container-Managed or Bean-Managed Persistence.
  - **Message-Driven Beans (MDBs)** – Introduced in this version for JMS-based async processing.
- **EJB Container** – Handles transactions, security, and lifecycle for EJBs.

### 3. Integration Tier
- **JMS 1.0.2** – Java Message Service for messaging between components.
- **JCA 1.0 (Java Connector Architecture)** – New in J2EE 1.3; standard way to integrate with enterprise systems (e.g., mainframes, ERPs).

### 4. Persistence Tier
- **JDBC 2.0** – Java API for interacting with relational databases.

### 5. Other Core APIs
- **JTA (Java Transaction API)** – Transaction coordination across components.
- **JNDI (Java Naming and Directory Interface)** – Lookup for resources like EJBs and data sources.
- **JavaMail 1.2** – For sending/receiving emails in enterprise apps.

## 🔹 Common Design Patterns in J2EE 1.3

| Pattern                 | Purpose                                                                 |
|-------------------------|-------------------------------------------------------------------------|
| **Front Controller**     | Centralized handling of web requests (via a single servlet).           |
| **DAO (Data Access Object)** | Encapsulates data access logic for reuse and abstraction.       |
| **Service Locator**      | Reduces overhead of JNDI lookups for EJBs/resources.                  |
| **Business Delegate**    | Hides EJB-specific complexity from the client layer.                   |
| **Transfer Object (DTO)**| Groups data into one object to minimize remote calls.                 |
| **Session Facade**       | Provides a simplified interface to the business logic (EJBs).          |
| **Intercepting Filter**  | Applies reusable pre/post-processing logic to requests.                |

---

## Hello World [J2EE 1.3]

Based on the archetype https://maven.apache.org/archetypes/maven-archetype-j2ee-simple/ with slight changes,this is a
J2EE example application that follows the J2EE Blueprints series to create a J2EE enterprise application.

The purpose of this application is to demonstrate the capabilities of the J2EE platform and is written
for learning purposes (for example to illustrate the many different design patterns on various part of J2EE).
It is not coded for performance and is not intended for performance benchmarks.

The application follows the J2EE 1.3 specs which is a set of related specifications and can be seen as a single
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

- JSF 1.1
- MyFaces 1.1.8
- Struts
- JSP
- Junit 3.8 && JMock 1.2 [Java 1.4]

The "Hello World" example application can be deployed in OC4J [oc4j_extended_101350] and JBoss [3.2.8]
