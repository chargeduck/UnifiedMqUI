package net.lesscoding.unified.core.model.vo.rocketmq;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/29 16:13
 * @apiNote
 */
@Data
public class BrokerInfo {

    private String brokerName;
    private String brokerId;
    private String msgPutTotalTodayNow;
    private String commitLogDiskRatio;
    private String getFoundTps;
    private String EndTransactionThreadPoolQueueCapacity;
    private String sendThreadPoolQueueHeadWaitTimeMills;
    private String putMessageDistributeTime;
    private String queryThreadPoolQueueHeadWaitTimeMills;
    private String remainHowManyDataToFlush;
    private String msgGetTotalTodayNow;
    private String commitLogMaxOffset;
    private String queryThreadPoolQueueSize;
    private String getMessageEntireTimeMax;
    private String msgPutTotalTodayMorning;
    private String putMessageTimesTotal;
    private String bootTimestamp;
    private String msgGetTotalTodayMorning;
    private String msgPutTotalYesterdayMorning;
    private String msgGetTotalYesterdayMorning;
    private String pullThreadPoolQueueSize;
    private String brokerVersionDesc;
    private String sendThreadPoolQueueSize;
    private String startAcceptSendRequestTimeStamp;
    private String commitLogMinOffset;
    private String putMessageEntireTimeMax;
    private String pullThreadPoolQueueHeadWaitTimeMills;
    private String runtime;
    private String earliestMessageTimeStamp;
    private String commitLogDirCapacity;
    private String dispatchMaxBuffer;
    private String brokerVersion;
    private String putTps;
    private String remainTransientStoreBufferNumbs;
    private String getMissTps;
    private String queryThreadPoolQueueCapacity;
    private String putMessageAverageSize;
    private String getTransferedTps;
    private String dispatchBehindBytes;
    private String putMessageSizeTotal;
    private String sendThreadPoolQueueCapacity;
    private String EndTransactionQueueSize;
    private String getTotalTps;
    private String pullThreadPoolQueueCapacity;
    private String consumeQueueDiskRatio;
    private String pageCacheLockTimeMills;

}
