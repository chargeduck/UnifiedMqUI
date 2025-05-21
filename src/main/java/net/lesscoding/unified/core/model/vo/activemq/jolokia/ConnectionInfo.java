package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import lombok.Data;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/19 16:18
 * @apiNote
 */
@Data
public class ConnectionInfo {

    private String RemoteAddress;
    private Boolean Active;
    private String UserName;
    private Integer DispatchQueueSize;
    private Integer ActiveTransactionCount;
    private Boolean Connected;
    private Boolean Slow;
    private String ClientId;
    private Long OldestActiveTransactionDuration;
    private Boolean Blocked;
    private List<SearchResult> Producers;
    private List<SearchResult> Consumers;
    private String connectorType;
}
