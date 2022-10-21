<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html:html>
    <head>
        <title>Hello World</title>
        <html:base/>
    </head>
    <body>
    <p>Message: <bean:message key="app.message.failure"/></p>
</html:html>


