package com.avg.j2ee13.web.helloworld.managedbeans;

import com.avg.j2ee13.web.helloworld.listeners.AppContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.event.ActionEvent;

public class HelloManagedBean {

    private Log logger = LogFactory.getLog(AppContextListener.class);

    private String message;

    public HelloManagedBean() {
        this.message = "Hello Managed Bean!!";
    }

    public void action(ActionEvent event) {
        logger.debug(event.getSource().toString());
        String attribute1 = FacesUtil.getActionAttribute(event, "attribute1");
        String attribute2 = FacesUtil.getActionAttribute(event, "attribute2");
        logger.info("attribute1 -> " + attribute1);
        logger.info("attribute2 -> " + attribute2);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
