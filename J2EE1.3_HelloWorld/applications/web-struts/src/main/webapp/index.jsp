<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<logic:redirect forward="fwd-helloworld"/>
<html:html>
    <head>
        <title>Hello Struts / Servlet 2.3 World!</title>
        <html:base/>
    </head>
    <body>
    <p>Please wait for the web application to start.</p>
    <br/>
    Struts Frame work is the implementation of Model-View-Controller (MVC) design pattern for the JSP.
    <p>
        Struts is maintained as a part of Apache Jakarta project and is open source. Struts Framework is suited for the
        application of any size.
    </p>
    <p>
        Struts follows MVC architecture.The main aim of the MVC architecture is to separate the business logic and
        application data from the presentation data to the user.
    </p>
    <p>
        Below are the benifits of using MVC design pattern.
        resuable : When the problems recurs, there is no need to invent a new solution, we just have to follow the
        pattern and adapt it as necessary.
    </p>
    </body>
</html:html>
