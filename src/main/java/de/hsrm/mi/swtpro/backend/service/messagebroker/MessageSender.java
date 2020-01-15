package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.TimetableModule;

/**
 * sends messages to Queue SwapOfferMessageQueue
 */
@Component
@EnableJms
public class MessageSender  {

    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final Map<String, ActiveMQQueue> queueMap = new HashMap<String, ActiveMQQueue>();

    private final String TOPICNAME = "NewsMessageTopic";
    private final ActiveMQTopic topic = new ActiveMQTopic(TOPICNAME);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    SwapOfferMessageConverter converter;

    public void addSwapMessageQueue (List<String> userids) {
        for(String userid : userids) {
            String queuename = "SwapMessageQueue" + userid;
            ActiveMQQueue queue = new ActiveMQQueue(queuename);
            queueMap.put(queuename, queue);
        }
    }

    public void sendSwapOfferMessage(SwapOffer swapOffer, String userid) {
        logger.info("sendingMessage: " + swapOffer);
        jmsTemplate.send(queueMap.get("SwapMessageQueue" + userid), session -> 
            session.createTextMessage(converter.toJSON(swapOffer, session) + "Du hast erfolgreich von der Gruppe " + swapOffer.getFromGroup().getCourseComponent().getCourse().getTitle() 
            + " " + swapOffer.getFromGroup().getGroupChar() + " zu " + swapOffer.getToGroup().getGroupChar() + " getauscht.") );
    }

    public void sendNewsMessage(TimetableModule module) {
        logger.info("sendingMessage: " + module);
        jmsTemplate.send(topic, session -> session.createTextMessage("Das Modul " + module.getCourseTitle() + " wurde verändert.") );
    }
}
