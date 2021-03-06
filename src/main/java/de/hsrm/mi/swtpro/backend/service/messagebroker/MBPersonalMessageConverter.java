package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.jms.*;
import org.slf4j.*;
import org.springframework.jms.support.converter.MessageConverter;

import de.hsrm.mi.swtpro.backend.model.MessageBrokerPersonalMessage;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import de.hsrm.mi.swtpro.backend.model.SwapOfferFront;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
/**
 * Converts MessageBrokerPersonalMessages into Json and creates a TextMessage
 * Used to send messages to Queues/Topics
 */
@Component
public class MBPersonalMessageConverter implements MessageConverter {
    private static final Logger logger = LoggerFactory.getLogger(MBPersonalMessageConverter.class);

    ObjectMapper mapper = new ObjectMapper();

    /**
     * @param object MessageBrokerPublicMessage that gets converted
     * @param session session
     * @return TextMessage containing the serialised MessageBrokerPublicMessage
     */
    @Override
    public Message toMessage(Object object, Session session) throws JMSException {
        MessageBrokerPersonalMessage message = (MessageBrokerPersonalMessage) object;
        String jsontext = null;
        try {
            jsontext = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error("FEHLER toMessage '{}' -> JSON: {}", message.toString(), e.getMessage());
        }
        TextMessage msg = session.createTextMessage(jsontext);
        return msg;
    }

    /**
     * Creates a swapOfferFront from swapOffer and converts it into a Json String
     * @param swapOffer swapOffer that gets serialised
     * @return serialised swapOffer as String
     */
    public String swapOfferToJSON(SwapOffer swapOffer) throws JMSException {
        String courseName = swapOffer.getFromGroup().getCourseComponent().getCourse().getTitle();
        String courseType = swapOffer.getFromGroup().getCourseComponent().getType().name();
        DayOfWeek fromDay = swapOffer.getFromGroup().getDayOfWeek();
        LocalTime fromEndTime = swapOffer.getFromGroup().getEndTime();
        char fromGroup = swapOffer.getFromGroup().getGroupChar();
        LocalTime fromStartTime = swapOffer.getFromGroup().getStartTime();
        DayOfWeek toDay = swapOffer.getToGroup().getDayOfWeek();
        LocalTime toEndTime = swapOffer.getToGroup().getEndTime();
        char toGroup = swapOffer.getToGroup().getGroupChar();
        LocalTime toStartTime = swapOffer.getToGroup().getStartTime();
        SwapOfferFront s = SwapOfferFront.builder().Id(swapOffer.getId()).courseName(courseName).courseType(courseType).fromDay(fromDay).fromGroup(fromGroup).toDay(toDay).toGroup(toGroup).build();
        String jsontext = null;
        try {
            jsontext = mapper.writeValueAsString(s);
            jsontext = jsontext.replace("\"toStartTime\":null","\"toStartTime\":\""+toStartTime.toString()+"\"");
            jsontext = jsontext.replace("\"fromStartTime\":null","\"fromStartTime\":\""+fromStartTime.toString()+"\"");
            jsontext = jsontext.replace("\"toEndTime\":null","\"toEndTime\":\""+toEndTime.toString()+"\"");
            jsontext = jsontext.replace("\"fromEndTime\":null","\"fromEndTime\":\""+fromEndTime.toString()+"\"");
            String s1 = "\"toStartTime\":\""+toStartTime.toString()+"\"";
        } catch (JsonProcessingException e) {
            logger.error("FEHLER toMessage SwapOffer '{}' -> JSON: {}", s.toString(), e.getMessage());
        }
        return jsontext;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException {
        return null;
    }
}
