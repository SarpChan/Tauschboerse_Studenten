package de.hsrm.mi.swtpro.backend.service.messagebroker;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.hsrm.mi.swtpro.backend.model.MessageBrokerNewsMessage;
import de.hsrm.mi.swtpro.backend.model.MessageBrokerPersonalMessage;
import de.hsrm.mi.swtpro.backend.model.MessageBrokerPublicMessage;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

/**
 * Sends messages to Topics/Queues
 */
@Component
@EnableJms
public class MessageSender {

    //private final Map<String, ActiveMQQueue> queueMap = new HashMap<String, ActiveMQQueue>();

    private final String TOPICNAME = "NewsMessageTopic";
    private final ActiveMQTopic newsTopic = new ActiveMQTopic(TOPICNAME);
    private final String TOPICNAMESwapOffer = "SwapOfferTopic";
    private final ActiveMQTopic swapOfferTopic = new ActiveMQTopic(TOPICNAMESwapOffer);
    private final String TOPICNAMEPersonalSwapOffer = "SwapMessagePersonalTopic";
    private final ActiveMQTopic personalSwapOfferTopic = new ActiveMQTopic(TOPICNAMEPersonalSwapOffer);


    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    MBPublicMessageConverter publicMessageConverter;
    @Autowired
    MBNewsMessageConverter messageConverter;
    @Autowired
    MBPersonalMessageConverter personalMessageConverter;
    @Autowired
    UserRepository userRepo;

    /**
     * creates a SwapMessageQueue for all users
     
    public void addSwapMessageQueues() {
        for(User user : userRepo.findAll()) { 
            String queuename = "SwapMessageQueue" + user.getId();
            ActiveMQQueue queue = new ActiveMQQueue(queuename);
            queueMap.put(queuename, queue);
        }
    }*/

    /**
     * sends a message to a user's personal queue to inform him that his swapOffer has been accepted
     * @param swapOffer the acceped swapOffer
     * @param userid the user's id
     */
    public void sendPersonalSwapOfferMessage(SwapOffer swapOffer, long userid) {
        String courseTitle = swapOffer.getFromGroup().getCourseComponent().getCourse().getTitle();
        char fromGroupChar = swapOffer.getFromGroup().getGroupChar();
        char toGroupChar = swapOffer.getToGroup().getGroupChar();

        final MessageBrokerPersonalMessage message = new MessageBrokerPersonalMessage(courseTitle, fromGroupChar, toGroupChar, userid);  
        jmsTemplate.send(personalSwapOfferTopic, session -> personalMessageConverter.toMessage(message, session));
    }

    /**
     * sends a message to the SwapOfferTopic to inform all active clients that a swapOffer has been added or removed
     * @param swapOffer added/removed  swapOffer
     * @param action "add" or "delete"
     */
    public void sendSwapOfferMessage(SwapOffer swapOffer, String action) {
        MessageBrokerPublicMessage message = null;
        if(action.equals("add")) {
            try {
                message = new MessageBrokerPublicMessage(action, publicMessageConverter.swapOfferToJSON(swapOffer));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")){
            message = new MessageBrokerPublicMessage(action, String.valueOf(swapOffer.getId()));
        }
        final MessageBrokerPublicMessage messageWrapper = message;
        jmsTemplate.send(swapOfferTopic, session -> publicMessageConverter.toMessage(messageWrapper, session));
    }

    /**
     * sends a message to the NewsTopic to inform clients about a change in their timetable
     * @param module
     */
    public void sendNewsMessage(TimetableModule module) {
        final MessageBrokerNewsMessage message = new MessageBrokerNewsMessage(module.getCourseTitle());
        jmsTemplate.send(newsTopic, session -> messageConverter.toMessage(message, session));
    }
}
