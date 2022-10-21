package com.avg.j2ee13.web.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class HelloWorldForm extends ActionForm {

    String message;

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
        ActionErrors errors = new ActionErrors();
        if ((message == null) || (message.length() < 1))
            errors.add("message", new ActionMessage("app.message.failure"));
        return errors;
    }

}
