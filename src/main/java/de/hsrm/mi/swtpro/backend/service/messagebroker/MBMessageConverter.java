package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.jms.*;
import org.slf4j.*;
import org.springframework.jms.support.converter.MessageConverter;

import de.hsrm.mi.swtpro.backend.model.MessageBrokerMessage;
import de.hsrm.mi.swtpro.backend.model.MessageBrokerPublicMessage;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.SwapOfferFront;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * converts swapOffers into messages and the other way around
 * used to send and receive messages form Queue
 */
@Component
public class MBMessageConverter implements MessageConverter {
    private static final Logger logger = LoggerFactory.getLogger(MBMessageConverter.class);

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Message toMessage(Object object, Session session) throws JMSException {
        MessageBrokerMessage message = (MessageBrokerMessage) object;
        String jsontext = null;
        try {
            jsontext = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error("FEHLER toMessage SwapOffer '{}' -> JSON: {}", message.toString(), e.getMessage());
        }
        TextMessage msg = session.createTextMessage(jsontext);
        return msg;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String jsontext = textMessage.getText();

        SwapOffer swapOffer = null;
        try {
            swapOffer = mapper.readValue(jsontext, SwapOffer.class);
        } catch (JsonProcessingException e) {
            logger.error("FEHLER fromMessage JSON '{}' -> SwapOffer", jsontext, e.getMessage());
        }
        return swapOffer;
    }
}
