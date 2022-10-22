# **Hello World J2EE 1.3**

The original J2EE specification was developed by Sun Microsystems.

Starting with J2EE 1.3, the specification was developed under the Java Community Process.
JSR 58 specifies J2EE 1.3 and JSR 151 specifies the J2EE 1.4 specification.

**The J2EE 1.3 SDK was first released by Sun as a beta in April 2001**

Tutorial: https://docs.oracle.com/javaee/1.3/tutorial/doc/

---

J2EE 1.3 Components

The J2EE 1.3 Specification lists the following components:

- Enterprise JavaBeans 2.0
- JDBC 2.0
- RMI-IIOP
- JNDI 1.2

---

## Challenges [J2EE 1.3]


Compilation jdk1.3.1_20 (Linux x86_64)
https://stackoverflow.com/questions/9701348/java-libjava-so-file-error

```
ln -s /usr/lib32/libstdc++.so.6 /usr/lib32/libstdc++-libc6.1-1.so.2
sudo rm -i libstdc++-libc6.1-1.so.2
```

```
To redeploy an application:
    >java -jar admin_client.jar deployer:oc4j:localhost oc4jadmin <admin_pwd>
     -redeploy
     -file <path-to-file>
     -deploymentName <name>
     -bindAllWebApps
     
```

```
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
```
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

```
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

```
<!-- https://mvnrepository.com/artifact/postgresql/postgresql -->
<dependency>
    <groupId>postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>8.0-312.jdbc3</version>
</dependency>
```

```
<shared-library name="postgresql" version="8.0.312">
    <code-source path="postgresql-8.0-312.jdbc3.jar"/>
</shared-library>
```
