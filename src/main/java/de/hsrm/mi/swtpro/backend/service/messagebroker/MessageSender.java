package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.hsrm.mi.swtpro.backend.model.MessageBrokerMessage;
import de.hsrm.mi.swtpro.backend.model.MessageBrokerPublicMessage;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

/**
 * Sends messages to Topics/Queues
 */
@Component
@EnableJms
public class MessageSender {

    private final Map<String, ActiveMQQueue> queueMap = new HashMap<String, ActiveMQQueue>();

    private final String TOPICNAME = "NewsMessageTopic";
    private final ActiveMQTopic newsTopic = new ActiveMQTopic(TOPICNAME);
    private final String TOPICNAMESwapOffer = "SwapOfferTopic";
    private final ActiveMQTopic swapOfferTopic = new ActiveMQTopic(TOPICNAMESwapOffer);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    MBPublicMessageConverter publicMessageConverter;
    @Autowired
    MBMessageConverter messageConverter;
    @Autowired
    UserRepository userRepo;

    /**
     * creates a SwapMessageQueue for all users
     */
    public void addSwapMessageQueues() {
        for(User user : userRepo.findAll()) { 
            String queuename = "SwapMessageQueue" + user.getId();
            ActiveMQQueue queue = new ActiveMQQueue(queuename);
            queueMap.put(queuename, queue);
        }
    }

    /**
     * sends a message to a user's personal queue to inform him that his swapOffer has been accepted
     * @param swapOffer the acceped swapOffer
     * @param userid the user's id
     */
    public void sendPersonalSwapOfferMessage(SwapOffer swapOffer, long userid) {
        if(queueMap.isEmpty()) {
            addSwapMessageQueues();
        }

        String courseTitle = swapOffer.getFromGroup().getCourseComponent().getCourse().getTitle();
        char fromGroupChar = swapOffer.getFromGroup().getGroupChar();
        char toGroupChar = swapOffer.getToGroup().getGroupChar();

        final MessageBrokerMessage message = new MessageBrokerMessage(courseTitle, fromGroupChar, toGroupChar);  
        jmsTemplate.send(queueMap.get("SwapMessageQueue" + userid), session -> messageConverter.toMessage(message, session));
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
        final MessageBrokerMessage message = new MessageBrokerMessage(module.getCourseTitle());
        jmsTemplate.send(newsTopic, session -> messageConverter.toMessage(message, session));
    }
}
