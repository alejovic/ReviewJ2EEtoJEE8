package com.avg.j2ee13.web.helloworld.managedbeans;

import javax.faces.event.ActionEvent;

public class FacesUtil {

    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }
}
