package com.avg.j2ee13.web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        String success = request.getParameter("success");
        if (success != null) {
            return mapping.findForward("fwd-success");
        } else {
            return mapping.findForward("fwd-failure");
        }
    }

}


