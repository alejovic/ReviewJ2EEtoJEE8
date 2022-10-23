# **Hello World J2EE 1.3**

The original J2EE specification was developed by Sun Microsystems.

Starting with J2EE 1.3, the specification was developed under the Java Community Process.
JSR 58 specifies J2EE 1.3 and JSR 151 specifies the J2EE 1.4 specification.

**The J2EE 1.3 SDK was first released by Sun as a beta in April 2001**

Tutorial: 
- https://docs.oracle.com/javaee/1.3/tutorial/doc/
- https://www.oreilly.com/library/view/head-first-ejb/0596005717/ch01s09.html

---

## J2EE 1.3 Components

The J2EE 1.3 Specification lists the following components:

- Enterprise JavaBeans 2.0
- JDBC 2.0
- RMI-IIOP
- JNDI 1.2

---

## J2EE Architecture (J2EE 1.3)

### Component:

**Services**
This layer implements the business logic, we would have coupling problems if we not apply the right design.

EJBLocalObject: Local Component Interface which defines internal Business Methods

```java
public interface HelloWorldLocal extends javax.ejb.EJBLocalObject {
    // business methods
}
```

EJBLocalHome: Local Home Interface for HelloWorldLocal.

```java
public interface HelloWorldLocalHome extends javax.ejb.EJBLocalHome {
    public HelloWorldLocal create() throws CreateException;
}
```

Service SessionBean: Business Logic implementation, those are considered as Business Objects (BO)
A Service SessionBean should not be transactional, it should join to the transaction which is started in the Facade
Session Bean.

```java
public abstract class GenericServiceLocalBean implements SessionBean {
    //
}

public class HelloWorldServiceLocalBean extends GenericServiceLocalBean {
    // business logic implementation
}
```

Descriptor [ejb-jar.xml]

```xml

<session id="sessionService_HelloWorld">
    <description><![CDATA[An Session Local Service EJB named HelloWorldLocalBean]]></description>
    <display-name>HelloWorldLocalBean</display-name>
    <ejb-name>HelloWorldLocalBean</ejb-name>
    <local-home>com.avg.j2ee13.ejb.service.HelloWorldLocalHome</local-home>
    <local>com.avg.j2ee13.ejb.service.HelloWorldLocal</local>
    <ejb-class>com.avg.j2ee13.ejb.service.HelloWorldServiceLocalBean</ejb-class>
    <session-type>Stateless</session-type>
    <transaction-type>Container</transaction-type>
</session>
```

**Facade**
In this layer business methods are exposed and the transactions are started.

EJBObject: Remote Component Interface which defines Business Methods

```java
public interface HelloWorldFacade extends javax.ejb.EJBObject {
    // business methods (User stories / Use cases)
}
```

EJBHome: Remote Home Interface for HelloWorldFacade.

```java
public interface HelloWorldFacadeHome extends javax.ejb.EJBHome {
    public HelloWorldFacade create() throws RemoteException, CreateException;
}
```

Facade SessionBean: Business logic implementation,

- A Facade Session Bean starts the transactions
- A Facade is a class that gives a straightforward interface to a complicated subsystem with many moving pieces.
- A Facade may be composed by either other Facades or Services

```java
public abstract class GenericSessionFacadeBean implements SessionBean {
    //
}

public class HelloWorldSessionFacadeBean extends GenericSessionFacadeBean {
    // business logic implementation
}
```

Descriptor [ejb-jar.xml]

```xml

<session id="sessionFacade_HelloWorld">
    <description><![CDATA[An Session Facade EJB named HelloWorldFacadeBean]]></description>
    <display-name>HelloWorldFacadeBean</display-name>
    <ejb-name>HelloWorldFacadeBean</ejb-name>
    <home>com.avg.j2ee13.ejb.facade.HelloWorldFacadeHome</home>
    <remote>com.avg.j2ee13.ejb.facade.HelloWorldFacade</remote>
    <ejb-class>com.avg.j2ee13.ejb.facade.HelloWorldSessionFacadeBean</ejb-class>
    <session-type>Stateless</session-type>
    <transaction-type>Container</transaction-type>
    <env-entry>
        <description>This example uses JNDI</description>
        <env-entry-name>env.entry.mailhost</env-entry-name>
        <env-entry-type>java.lang.String</env-entry-type>
        <env-entry-value>smtp.example.com</env-entry-value>
    </env-entry>
    <!-- Link to Session Service Beans -->
    <ejb-local-ref>
        <ejb-ref-name>HelloWorldServiceLocalBean</ejb-ref-name>
        <ejb-ref-type>Session</ejb-ref-type>
        <local-home>com.avg.j2ee13.ejb.service.HelloWorldLocalHome</local-home>
        <local>com.avg.j2ee13.ejb.service.HelloWorldLocal</local>
        <ejb-link>HelloWorldServiceLocalBean</ejb-link>
    </ejb-local-ref>
</session>
```

**Service Locator**

**Business Objects BO**
It can be a Service SessionBean or a class which implements business logic.

**Data Access Object DAO**

**Value Objects VO**
A VO should always override the equals() and hashCode() methods. VOs generally encapsulate small objects such as
numbers, dates, strings, and more. They follow the value semantics, i.e., they directly change the object's value and
pass copies around instead of references.

It's a good practice to make Value Objects immutable. The change in values occurs only by creating a new object and not
by updating values in the old object itself. This helps in understanding the implicit contract that two Value Objects
created equal should remain equal.
Reference: https://www.baeldung.com/java-pojo-javabeans-dto-vo

- A Value Object represents itself a fix set of data and is similar to a Java enum.
- A Value Object doesn't have any identity, it is entirely identified by its value and is immutable. A real world
  example would be Color.RED, Color.BLUE, SEX.FEMALE etc.

**Data Transfer Objects DTO**
In a traditional EJB architecture, DTOs serve dual purposes: first, they work around the problem that entity beans are
not serializable; second, they implicitly define an assembly phase where all data to be used by the view is fetched and
marshalled into the DTOs before returning control to the presentation tier.

- DTOs are anemic in general and do not contain any business logic.
- DTOs are often java.io.Serializable - its only needed if you are going to transfer the data across JVMs.

## Challenges [J2EE 1.3]

Compilation jdk1.3.1_20 (Linux x86_64)
https://stackoverflow.com/questions/9701348/java-libjava-so-file-error

```shell
ln -s /usr/lib32/libstdc++.so.6 /usr/lib32/libstdc++-libc6.1-1.so.2
sudo rm -i libstdc++-libc6.1-1.so.2
```

```text
To redeploy an application:
    >java -jar admin_client.jar deployer:oc4j:localhost oc4jadmin <admin_pwd>
     -redeploy
     -file <path-to-file>
     -deploymentName <name>
     -bindAllWebApps
     
```

```shell
RUN clear\
&& export PATH="/usr/lib/jvm/java-8-openjdk/bin/:$PATH"\
&& export OC4J_JVM_ARGS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000"\
&& export OC4J_JVM_ARGS="-Dapp.configuration.properties=/mnt/data/workspace/DEV/ReviewJ2EEtoJEE8/J2EE1.3_HelloWorld/ear/src/main/resources/app.configuration.properties:$OC4J_JVM_ARGS"\
&& ./oc4j -start
```

Prior to version 5.0 of the application server, the function of data access was provided by a single connection
manager (CM) architecture. This connection manager architecture remains available to support Java 2 Platform, Enterprise
Edition (J2EE) 1.2 applications, but another connection manager architecture is provided, based on the JCA architecture
supporting the J2EE 1.3 application style, J2EE 1.4 and Java EE applications.

These architectures are represented by two types of data sources. To choose the correct data source, administrators must
understand the nature of their applications, EJB modules, and enterprise beans.

Data source - This data source uses the JCA standard architecture to provide support for J2EE version 1.3 and 1.4, as
well as Java EE applications. It utilizes the JCA connection manager and the relational resource adapter.

Choice of data source

- J2EE 1.2 application - all EJB 1.1 enterprise beans, JDBC applications, or Servlet 2.2 components must use the 4.0
  data
  source (deprecated).
- J2EE 1.3 (and subsequent releases) application -
    - EJB 1.1 module - all EJB 1.x beans must use the 4.0 data source.
    - EJB 2.0 (and subsequent releases) module - enterprise beans that include container-managed persistence (CMP)
      Version
      1.x, 2.0, and beyond must use the new data source.
    - JDBC applications and Servlet 2.3+ components - must use the new data source.

Dependencies

Oracle JDBC Driver » 10.2.0.2.0
Oracle JDBC driver classes for use with JDK1.2 and JDK1.3
https://www.oracle.com/java/technologies/java-archive-database-downloads.html
https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html

```xml

<dependency>
    <groupId>javax.sql</groupId>
    <artifactId>jdbc-stdext</artifactId>
    <version>2.0</version>
    <scope>system</scope>
    <systemPath>
        /mnt/data/workspace/DEV/ReviewJ2EEtoJEE8/J2EE1.3_HelloWorld/ear/lib/jdbc2_0-stdext.jar
    </systemPath>
</dependency>
```

```xml

<dependency>
    <groupId>com.oracle.oc4j</groupId>
    <artifactId>oc4j-oc4jclient</artifactId>
    <version>10.1.3.5</version>
    <scope>system</scope>
    <systemPath>
        /mnt/data/workspace/DEV/ReviewJ2EEtoJEE8/J2EE1.3_HelloWorld/ear/lib/oc4jclient.jar
    </systemPath>
</dependency>
```

```xml
<!-- https://mvnrepository.com/artifact/postgresql/postgresql -->
<dependency>
    <groupId>postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>8.0-312.jdbc3</version>
</dependency>
```

```xml

<shared-library name="postgresql" version="8.0.312">
    <code-source path="postgresql-8.0-312.jdbc3.jar"/>
</shared-library>
```
