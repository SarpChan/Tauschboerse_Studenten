package de.hsrm.mi.swtpro.backend.service.messagebroker;

import javax.jms.*;
import org.slf4j.*;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import de.hsrm.mi.swtpro.backend.model.MessageBrokerNewsMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Converts MessageBrokerMessages into Json and creates a TextMessage
 * Used to send messages to Queues/Topics
 */
@Component
public class MBNewsMessageConverter implements MessageConverter {
    private static final Logger logger = LoggerFactory.getLogger(MBNewsMessageConverter.class);

    ObjectMapper mapper = new ObjectMapper();

    /**
     * @param object MessageBrokerMessage that gets converted
     * @param session session
     * @return TextMessage containing the serialised MessageBrokerMessage
     */
    @Override
    public Message toMessage(Object object, Session session) throws JMSException {
        MessageBrokerNewsMessage message = (MessageBrokerNewsMessage) object;
        String jsontext = null;
        try {
            jsontext = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error("FEHLER toMessage '{}' -> JSON: {}", message.toString(), e.getMessage());
        }
        TextMessage msg = session.createTextMessage(jsontext);
        return msg;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return null;
    }

  
}
