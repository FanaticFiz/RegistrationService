package com.simpleteam.config;

import com.simpleteam.jms.SimpleMessageListener;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.sql.DataSource;

/**
 * Configuration class.
 *
 * @version 0.1
 */
@Configuration
public class AppConfiguration {

    /**
     * DataSource for HSQL.
     *
     * @return DataSource.
     */
    @Bean
    public DataSource dataSource() {
        //jdbc:hsqldb:mem:testdb
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/create-db.sql")
                .build();
        return db;
    }

    /**
     * ActiveMQ config.
     *
     * @return connection factory
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        return connectionFactory;
    }

    /**
     * Messsage listener config.
     *
     * @param messageListener listener
     * @return message listener container
     */
    @Bean
    @Autowired
    public DefaultMessageListenerContainer listenerContainer(final SimpleMessageListener messageListener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestinationName("simpleteam");
        container.setMessageListener(messageListener);
        return container;
    }

    /**
     * JMS template.
     *
     * @return template
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestinationName("simpleteam");
        return jmsTemplate;
    }

}
