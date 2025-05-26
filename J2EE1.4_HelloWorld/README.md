# **Hello World J2EE 1.4**

## 🔹 Short History

- **Released**: November 2003 by **Sun Microsystems**.
- **Objective**: To standardize and simplify **enterprise application development**.
- **Highlight**: First J2EE version to strongly support **web services** and **interoperability**.
- **Compliance**: Aligned with **WS-I Basic Profile 1.0** and **Java Web Services Developer Pack (JWSDP)** for SOAP-based web services.

Tutorial: https://docs.oracle.com/javaee/1.4/tutorial/doc/

---

J2EE 1.4 Components

J2EE builds on Java SE and adds APIs for developing large-scale, distributed, multi-tiered applications.

### 1. Web Tier
- **Servlets 2.4** – Handle HTTP requests and responses.
- **JSP 2.0** – JavaServer Pages for generating dynamic web content.
- **Web Container** – Hosts servlets and JSPs, manages lifecycle and configuration.

### 2. Business Tier
- **Enterprise JavaBeans (EJB) 2.1**:
    - **Session Beans** (stateless/stateful)
    - **Entity Beans** (CMP/BMP)
    - **Message-Driven Beans (MDBs)**
- **EJB Container** – Provides services such as transaction management, security, and lifecycle management.

### 3. Integration Tier
- **JMS 1.1 (Java Message Service)** – Asynchronous messaging between components.
- **JCA 1.5 (Java Connector Architecture)** – Connects to enterprise information systems like ERPs.
- **JAX-RPC 1.1** – Java API for XML-based SOAP web services.

### 4. Persistence Tier
- **JDBC 3.0** – Java API for relational database access using SQL.

### 5. Other Core APIs
- **JTA (Java Transaction API)** – Distributed transaction support.
- **JNDI (Java Naming and Directory Interface)** – Directory and naming services.
- **JAAS (Java Authentication and Authorization Service)** – Authentication and authorization framework.

## 🔹 Common Design Patterns in J2EE 1.4

| Pattern                 | Purpose                                                                 |
|-------------------------|-------------------------------------------------------------------------|
| **Front Controller**     | Centralized request handling mechanism (commonly via a servlet).       |
| **DAO (Data Access Object)** | Encapsulates database access logic.                              |
| **Service Locator**      | Caches and abstracts the lookup of EJBs or services.                   |
| **Business Delegate**    | Abstracts the presentation tier from the business logic.               |
| **Transfer Object (DTO)**| Bundles multiple data fields into a single object for network transfer.|
| **Session Facade**       | Provides a unified interface to a set of EJBs.                         |
| **Intercepting Filter**  | Applies cross-cutting logic (e.g., logging, security) before processing. |

---

## Hello World [J2EE 1.4]

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

- JSF 1.1
- MyFaces 1.1.8
- Struts
- JSP
- Junit 3.8 && JMock 1.2 [Java 1.4]

The "Hello World" example application can be deployed in OC4J [oc4j_extended_101350] and JBoss Application Server (AS) 4.x was a production-ready Java 2 Enterprise Edition (J2EE) application server, certified to be fully compliant with the J2EE 1.4 specification.
