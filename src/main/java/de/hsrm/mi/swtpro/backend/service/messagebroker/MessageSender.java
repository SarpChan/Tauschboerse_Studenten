package de.hsrm.mi.swtpro.backend.service.messagebroker;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;

/**
 * sends messages to Queue SwapOfferMessageQueue
 */
@Component
@EnableJms
public class MessageSender  {

    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final String QUEUENAME = "SwapOfferMessageQueue" + "userid";
    private final ActiveMQQueue queue = new ActiveMQQueue(QUEUENAME);

    private final String TOPICNAME = "NewsMessageTopic";
    private final ActiveMQQueue topic = new ActiveMQQueue(TOPICNAME);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendSwapOfferMessage(SwapOffer swapOffer) {
        logger.info("sendingMessage: " + swapOffer);
        jmsTemplate.send(queue, session -> session.createTextMessage("Du hast erfolgreich eine Gruppe getauscht." + swapOffer.toString()) );
    }

    public void sendNewsMessage() {
        logger.info("sendingMessage: " );
        jmsTemplate.send(topic, session -> session.createTextMessage() );
    }
}