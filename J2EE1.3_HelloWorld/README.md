# **Hello World J2EE 1.3**

The original J2EE specification was developed by Sun Microsystems.

Starting with J2EE 1.3, the specification was developed under the Java Community Process.
JSR 58 specifies J2EE 1.3 and JSR 151 specifies the J2EE 1.4 specification.

**The J2EE 1.3 SDK was first released by Sun as a beta in April 2001**

Tutorial: https://docs.oracle.com/javaee/1.3/tutorial/doc/

---

J2EE 1.3 Components

The J2EE 1.3 Specification lists the following components:

- Servlet 2.3
- JavaServer Pages 1.2
- Enterprise JavaBeans 2.0
- JDBC 2.0
- RMI-IIOP
- JNDI 1.2
- Web services 1.1
- Java Message Service 1.0.2
  Java Transaction API 1.0
- Java Authentication and Authorization Service 1.0
- J2EE Connector Architecture 1.0
- SOAP with Attachments API for Java 1.1
  J2EE 1.3 Components
- JavaMail 1.2
  Java IDL 1.0
- Java API for XML Parsing (JAXP) 1.1

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

--
ay que considerar el tamaño de la aplicación para realizar una división por subsistema o directamente dividir por capa
la estructura de paquetes de la aplicación. Por lo tanto, Subsistema puede ser opcional.

Donde CAPA:

presentacion
negocio
persistencia
Donde TIPO:

El tipo es variable en función de la capa, así que los paquetes definidos por cada capa son los siguientes:

persistencia.dao: Agrupan las interfaces de los DAO's de la capa de persistencia
persistencia.dao.impl: Implementación de las interfaces de acceso a datos
persistencia.entidades: Agrupa a las clases de entidad que dan origen a las tables en la base de datos
persistencia.interfaces: Agrupa a las interfaces globales (factoría, genérico,...)
persistencia.util: Agrupa a las clases de apoyo (criteria, etc...)
negocio.servicios: Agrupa a las interfaces que separan la lógica de negocio
negocio.servicios.Impl: Agrupa a las clases que implementan las interfaces de lógica de negocio
negocio.vo: Agrupa a la clases encargadas de transporte de datos entre capas
negocio.dto: Agrupa a la clases de transporte de datos entre diversas máquinas
negocio.util: Agrupa a las clases de apoyo (excepciones, autenticación....)
presentacion.util: Utilidades de apoyo a la capa de presentación (validadores personaliazados, etc...)
presentacion.controlador: Agrupa a las interfaces de los action que produce JSF
presentacion.controlador.Impl: Agrupa a las clases de que implementan los action provenientes de JSF
