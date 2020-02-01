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
 * Configuration for Queues and (durable) Topics
 */
@Configuration
@EnableJms
public class MessageSenderConfig {
    Logger logger = LoggerFactory.getLogger(MessageSenderConfig.class);

    /**
     * Adds connector for tcp
     * @return broker
     * @throws Exception thrown if the connection can't be added
     */
    @Bean
    public BrokerService broker() throws Exception {
        logger.info("BrokerService broker() gezogen");
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://0.0.0.0:61616");
        return broker;
    }

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * Defines a factory for a durable topic
     * @return factory
     */
    @Bean(name="myDurableTopicFactory")
    public DefaultJmsListenerContainerFactory makeDurableTopicFactory() {
        logger.info("DefaultJmsListenerContainerFactory myTopicFactory() gezogen");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        //factory.setConcurrency("3-10");
        factory.setClientId("brokerClientId");
        factory.setSubscriptionDurable(true);
        return factory;
    }

    /**
     * Defines a factory for a topic
     * @return factory
     */
    @Bean(name="myTopicFactory")
    public DefaultJmsListenerContainerFactory makeTopicFactory() {
        logger.info("DefaultJmsListenerContainerFactory myTopicFactory() gezogen");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        //factory.setConcurrency("3-10");
        factory.setClientId("brokerClientId");
        return factory;
    }

    /**
     * Defines a factory for a queue
     * @return factory
     */
    @Bean(name="myQueueFactory")
    public DefaultJmsListenerContainerFactory makeQueueFactory() {
        logger.info("DefaultJmsListenerContainerFactory myQueueFactory() gezogen");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        factory.setErrorHandler(new MyErrorHandler());
        return factory;
    }

/**
 * ErrorHandler for message broker
 */
public class MyErrorHandler implements ErrorHandler {
    private Logger logger = LoggerFactory.getLogger(MyErrorHandler.class);

    /**
     * Writes a logger output in case of an error
     */
    @Override
    public void handleError(Throwable t) {
        logger.error("\n---\nFEHLER - {}\n---\n", t.getMessage());
    }
}

}