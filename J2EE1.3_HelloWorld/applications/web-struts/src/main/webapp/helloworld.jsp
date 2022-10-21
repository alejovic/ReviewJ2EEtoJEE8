<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html:html>
    <head>
        <title><bean:message key="app.jsp.title"/></title>
        <html:base/>
    </head>
    <body>
    <h2><bean:message key="app.jsp.page"/></h2>

    <html:errors/>

    <logic:present name="bean.HelloWorld" scope="request">
        <h2>
            Hello <bean:write name="bean.HelloWorld" property="message"/>!
        </h2>
    </logic:present>

    <html:form action="/HelloWorld.do?action=gotName" focus="message">
        <bean:message key="app.prompt.message"/>
        <html:text property="message" size="16" maxlength="16"/>
        <html:submit property="submit" value="Submit"/>
        <html:reset/>
    </html:form>


    </body>
</html:html>


