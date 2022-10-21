<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html:html>
    <head>
        <title>Hello World Start</title>
        <html:base/>
    </head>
    <body>
    <p>Message from [message-resources]: <bean:message key="app.message"/></p>

    <a href="HelloWorld.do?success=ok">Go to myTest Success</a>
    <br/>
    <a href="HelloWorld.do">Go to myTest Failure</a>
    </body>
</html:html>


