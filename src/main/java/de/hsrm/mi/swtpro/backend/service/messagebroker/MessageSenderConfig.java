package de.hsrm.mi.swtpro.backend.service.messagebroker;

import javax.jms.ConnectionFactory;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.util.ErrorHandler;

/**
 * Adds connector (Broker) for tcp
 * Configuration for Queues und durable!! Topics
 */
@Configuration
@EnableJms
public class MessageSenderConfig {
    Logger logger = LoggerFactory.getLogger(MessageSenderConfig.class);

    @Bean
    public BrokerService broker() throws Exception {
        logger.info("BrokerService broker() gezogen");
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://0.0.0.0:61616");
        return broker;
    }

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean(name="myDurableTopicFactory")
    public DefaultJmsListenerContainerFactory makeTopicFactory() {
        logger.info("DefaultJmsListenerContainerFactory myTopicFactory() gezogen");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);  
        factory.setMessageConverter(new SwapOfferMessageConverter());
        //factory.setConcurrency("3-10");
        factory.setClientId("brokerClientId");
        factory.setSubscriptionDurable(true);
        return factory;
    }

    @Bean(name="myQueueFactory")
    public DefaultJmsListenerContainerFactory makeQueueFactory() {
        logger.info("DefaultJmsListenerContainerFactory myQueueFactory() gezogen");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        factory.setMessageConverter(new SwapOfferMessageConverter());
        factory.setErrorHandler(new MyErrorHandler());
        return factory;
    }

/**
 * ErrorHandler
 */
public class MyErrorHandler implements ErrorHandler {
    private Logger logger = LoggerFactory.getLogger(MyErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        logger.error("\n---\nFEHLER - {}\n---\n", t.getMessage());
    }
}

}