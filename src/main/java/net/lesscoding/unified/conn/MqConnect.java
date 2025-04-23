package net.lesscoding.unified.conn;

import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.exception.MqException;

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
