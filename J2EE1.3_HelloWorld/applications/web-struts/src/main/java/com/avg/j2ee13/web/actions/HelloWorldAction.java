package com.avg.j2ee13.web.actions;

import com.avg.j2ee13.web.forms.HelloWorldForm;
import com.avg.j2ee13.web.listeners.AppContextListener;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldAction extends Action {

    private Log logger = LogFactory.getLog(HelloWorldAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("HelloWorldAction.execute start");
        MessageResources messages = getResources(request);

        HelloWorldForm helloWorldForm = (HelloWorldForm) form;
        logger.info("HelloWorldAction.HelloWorldForm -> " + helloWorldForm.getMessage());

        String message = (String) PropertyUtils.getSimpleProperty(form, "message");
        logger.info("Simple property -> " + message);

        String badPerson = "Atilla the Hun";

        if (message.equals(badPerson)) {
            ActionErrors errors = new ActionErrors();
            errors.add("message", new ActionMessage("app.message.failure", message));
            saveErrors(request, errors);
            logger.warn("HelloWorldAction.ActionError -> " + mapping.getInput());
            return (new ActionForward(mapping.getInput()));
        }
        // invoke delegate

         request.setAttribute( "bean.HelloWorld", helloWorldForm);

        // Remove the Form Bean - don't need to carry values forward
        request.removeAttribute(mapping.getAttribute());

        // Forward control to the specified success URI
        return (mapping.findForward("fwd-success"));
    }

}


