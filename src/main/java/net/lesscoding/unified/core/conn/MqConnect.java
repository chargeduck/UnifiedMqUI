package net.lesscoding.unified.core.conn;

import net.lesscoding.unified.core.exception.MqException;
import net.lesscoding.unified.entity.ConnectConfig;

/**
 * @author eleven
 * @date 2025/4/23 15:47
 * @apiNote
 */
public interface MqConnect extends AutoCloseable{


    void close() throws MqException;

    boolean isClosed() throws MqException;

    ConnectConfig getMqConnectConfig() throws MqException;


    boolean sendMessage(Object message) throws MqException;

}
