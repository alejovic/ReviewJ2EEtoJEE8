"# ReviewJ2EE-JEE8" 


1. Registrar usuario
2. Login Usuario
3. Inscribir usuario -> validar email http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx?op=AdvancedVerifyEmail
4. Calcular Precio de Curso -> web service http://www.dneonline.com/calculator.asmx
5. Enviar email notificaciones JMS
6. Inactivar Usuarios -> Quartz, Task,.. depends on JAva spec
7. Cargar Curso -> Batch + Challenge 150.000 records in 5 sg

Client Server Architecture: https://www.cs.bgu.ac.il/~spl191/index.php?page=Communication-overview
1-J2SE (Java 1.4)
	+ server
		+ DAO
		+ Business Object
		+ Facade
	+ client
		+ stand-alone

2-J2EE (Java 1.4)
Patterns - EJB 2.1 + DAO vs Bean
j2ee 1.3 https://slideplayer.com/slide/733820/
j2ee 1.4 https://slideplayer.com/slide/6925452/
todo: http://j2eetutorials.50webs.com/ejb_introduction.html
	+ integration
		+ DAO
		+ Web Service Broker
		+ Service Activator
			+ Java Mail 1.4
	+ business
		+ Business Delegate
		+ Service Locator
		+ Session Facade (EJB 2.1)
		+ Application Service
		+ Business Object
		+ Composite Entity
		+ Transfer Object	-> DTO	
	+ client
		+ web JSF 1.1
		+ stand-alone
		+ applet
		+ CORBA
		+ JMS
		+ WebService

3-JEE5 
Patterns AntiPatterns - EJB 3.0 ALL      
	+ integration
		+ JPA 1.0 
	+ businness
		+ Service Facade EJB 3.0 -> Application Service
		+ Service (Context) EJB 3.0 -> Session Facade
	+ client
		+ web JSF 1.2

4-JEE 6 
CDI - profile
https://www.slideshare.net/shreeg/java-ee-6-component-model-explained	
http://alexander.holbreich.org/javaee5-vs-javaee6/

5-JEE7
Improvements websockets, jms 2.0
6-JEE8 
JaxRs
Mono to MicroService
Docker to AWS
---


matrix jboss j2ee jee
https://access.redhat.com/articles/113373


matrix eclipse jboss
https://developer.jboss.org/wiki/MatrixOfSupportedPlatformsRuntimesAndTechnologiesInJBossToolsJBDS

	
