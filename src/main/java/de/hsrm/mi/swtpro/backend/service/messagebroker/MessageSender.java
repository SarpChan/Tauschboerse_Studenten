package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

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
 * sends messages
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

    public void addSwapMessageQueues() {
        for(User user : userRepo.findAll()) { 
            String queuename = "SwapMessageQueue" + user.getId();
            ActiveMQQueue queue = new ActiveMQQueue(queuename);
            queueMap.put(queuename, queue);
        }
    }

    public void sendPersonalSwapOfferMessage(SwapOffer swapOffer, String userid) {
        if(queueMap.isEmpty()) {
            addSwapMessageQueues();
        }

        String courseTitle = swapOffer.getFromGroup().getCourseComponent().getCourse().getTitle();
        char fromGroupChar = swapOffer.getFromGroup().getGroupChar();
        char toGroupChar = swapOffer.getToGroup().getGroupChar();

        final MessageBrokerMessage message = new MessageBrokerMessage(courseTitle, fromGroupChar, toGroupChar);  
        jmsTemplate.send(queueMap.get("SwapMessageQueue" + userid), session -> messageConverter.toMessage(message, session));
    }

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

    public void sendNewsMessage(TimetableModule module) {
        final MessageBrokerMessage message = new MessageBrokerMessage(module.getCourseTitle());
        jmsTemplate.send(newsTopic, session -> messageConverter.toMessage(message, session));
    }
}
