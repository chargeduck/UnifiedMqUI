package net.lesscoding.unified.core.adapter;

import lombok.extern.slf4j.Slf4j;

import javax.jms.*;

/**
 * @author eleven
 * @date 2025/5/29 16:03
 * @apiNote
 */
@Slf4j
public class DefaultConnect implements Connection {

    private static volatile DefaultConnect INSTANCE;
    private DefaultConnect() {}

    public static DefaultConnect instance() {
        if (INSTANCE == null) {
            synchronized (DefaultConnect.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DefaultConnect();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
        return null;
    }

    @Override
    public String getClientID() throws JMSException {
        return null;
    }

    @Override
    public void setClientID(String clientID) throws JMSException {

    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        return null;
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        return null;
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) throws JMSException {

    }

    @Override
    public void start() throws JMSException {

    }

    @Override
    public void stop() throws JMSException {

    }

    @Override
    public void close() throws JMSException {

    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return null;
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        return null;
    }
}
