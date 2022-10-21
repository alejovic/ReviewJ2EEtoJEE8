package com.avg.j2ee13.web.forms;

import com.avg.j2ee13.web.actions.HelloWorldAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class HelloWorldForm extends ActionForm {

    private Log logger = LogFactory.getLog(HelloWorldAction.class);
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.message = null;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        logger.info("HelloWorldForm.validate");
        ActionErrors errors = new ActionErrors();
        if ((message == null) || (message.length() < 1))
            errors.add("message", new ActionMessage("app.message.failure"));
        return errors;
    }

}
