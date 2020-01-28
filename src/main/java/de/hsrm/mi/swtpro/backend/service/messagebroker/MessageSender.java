package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

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

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Map<String, Long> map = new HashMap<>();
        map.put("timestamp", timestamp.getTime());
        
        jmsTemplate.convertAndSend(queueMap.get("SwapMessageQueue" + userid), map, new MessagePostProcessor() {
        public Message postProcessMessage(Message message) throws JMSException {
            message.setStringProperty("message", " Du hast erfolgreich von der Gruppe "
            + swapOffer.getFromGroup().getCourseComponent().getCourse().getTitle() + " "
            + swapOffer.getFromGroup().getGroupChar() + " zu " + swapOffer.getToGroup().getGroupChar()
            + " getauscht.");
            return message;
        }
        });
    }

    public void sendSwapOfferMessage(SwapOffer swapOffer, String action) {

        Map<String, String> map = new HashMap<>();
        map.put("action", action);
        map.put("data", String.valueOf(swapOffer.getId()));
        
        jmsTemplate.convertAndSend(swapOfferTopic, map);
    }

    public void sendNewsMessage(TimetableModule module) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Map<String, Long> map = new HashMap<>();
        map.put("timestamp", timestamp.getTime());
        
        jmsTemplate.convertAndSend(newsTopic, map, new MessagePostProcessor() {
        public Message postProcessMessage(Message message) throws JMSException {
            message.setStringProperty("message", " Das Modul " + module.getCourseTitle() + " wurde verändert.");
            return message;
        }
        });
    }
}
