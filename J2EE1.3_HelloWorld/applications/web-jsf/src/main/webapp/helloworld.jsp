<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core">
<h:head>
    <title>Hello World!</title>
</h:head>
<h:body>
    <h:form>
        <h3>Please enter your message.</h3>
        <table>
            <tr>
                <td>Message:</td>
                <td><h:inputText value="#{helloManagedBean.message}"/></td>
            </tr>
        </table>
        <h:commandButton value="Click" actionListener="#{helloManagedBean.action}">
            <f:attribute name="attribute2" value="value1"/>
            <f:attribute name="attribute2" value="value2"/>
        </h:commandButton>
    </h:form>
</h:body>
</html>
