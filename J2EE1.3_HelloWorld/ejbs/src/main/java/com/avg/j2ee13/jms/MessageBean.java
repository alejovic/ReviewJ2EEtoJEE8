package com.avg.j2ee13.jms;

//http://docs.cs.uct.ac.za/jms_tutorial/doc/j2eeapp1.html#1055859
//https://howtodoinjava.com/jms/jms-point-to-point-message-example/
//https://www.javatpoint.com/jms-tutorial
//https://examples.javacodegeeks.com/enterprise-java/jms/jms-queue-example/

// client delegate jms -> publish event, take event -> ejb or bo


// --> https://examples.javacodegeeks.com/event-driven-data-management-for-microservices/
// https://blog.avenuecode.com/event-driven-architecture-and-its-application-in-java-part-2?hsCtaTracking=57598ef8-07b3-41c4-a684-afcd913eb604%7C4d25f9e1-ffd0-43fa-ac75-281c265f83fa
//https://www.enterpriseintegrationpatterns.com/patterns/messaging/RequestReplyJmsExample.html
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.naming.Context;
import javax.jms.*;

public class MessageBean implements MessageDrivenBean,
        MessageListener {

    private transient MessageDrivenContext mdc = null;
    private Context context;

    /**
     * Constructor, which is public and takes no arguments.
     */
    public MessageBean() {
        System.out.println("In MessageBean.MessageBean()");
    }

    /**
     * setMessageDrivenContext method, declared as public (but
     * not final or static), with a return type of void, and
     * with one argument of type javax.ejb.MessageDrivenContext.
     *
     * @param mdc    the context to set
     */
    public void setMessageDrivenContext(MessageDrivenContext mdc)
    {
        System.out.println("In " +
                "MessageBean.setMessageDrivenContext()");
        this.mdc = mdc;
    }

    /**
     * ejbCreate method, declared as public (but not final or
     * static), with a return type of void, and with no
     * arguments.
     */
    public void ejbCreate() {
        System.out.println("In MessageBean.ejbCreate()");
    }

    /**
     * onMessage method, declared as public (but not final or
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a TextMessage and displays
     * the text.
     *
     * @param inMessage    the incoming message
     */
    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println("MESSAGE BEAN: Message " +
                        "received: " + msg.getText());
            } else {
                System.out.println("Message of wrong type: " +
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            System.err.println("MessageBean.onMessage: " +
                    "JMSException: " + e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            System.err.println("MessageBean.onMessage: " +
                    "Exception: " + te.toString());
        }
    }

    /**
     * ejbRemove method, declared as public (but not final or
     * static), with a return type of void, and with no
     * arguments.
     */
    public void ejbRemove() {
        System.out.println("In MessageBean.remove()");
    }
}
