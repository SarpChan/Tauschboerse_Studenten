package de.hsrm.mi.swtpro.backend.service.messagebroker;

import javax.jms.*;
import org.slf4j.*;
import org.springframework.jms.support.converter.MessageConverter;

import de.hsrm.mi.swtpro.backend.model.SwapOffer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * converts swapOffers into messages and the other way around
 * used to send and receive messages form Queue
 */
public class SwapOfferMessageConverter implements MessageConverter {
    private static final Logger logger = LoggerFactory.getLogger(SwapOfferMessageConverter.class);

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Message toMessage(Object object, Session session) throws JMSException {
        SwapOffer swapOffer = (SwapOffer) object;
        String jsontext = null;
        try {
            jsontext = mapper.writeValueAsString(swapOffer);
        } catch (JsonProcessingException e) {
            logger.error("FEHLER toMessage SwapOffer '{}' -> JSON: {}", swapOffer.toString(), e.getMessage());
        }
        TextMessage message = session.createTextMessage(jsontext);
        return message;
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
