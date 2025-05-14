package net.lesscoding.unified.core.model.vo.activemq.jolokia.queue;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author eleven
 * @date 2025/5/10 16:43
 * @apiNote
 */
@Data
public class QueueMessage implements Serializable {

    private String JMSMessageID;
    private Boolean JMSRedelivered;
    private Object ShortProperties;
    private String JMSDeliveryMode;
    private Object ByteProperties;
    private Object LongProperties;
    private Object IntegerProperties;
    private Object JMSXGroupID;
    private String JMSCorrelationID;
    private String PropertiesText;
    private String JMSType;
    private Integer JMSExpiration;
    private Object OriginalDestination;

    private String JMSTimestamp;

    private Object JMSXUserID;
    private Integer JMSXGroupSeq;
    private String Text;
    private Integer JMSPriority;
    private Object JMSReplyTo;
    private String JMSDestination;
    private String BrokerPath;
    private Object DoubleProperties;
    private Object BooleanProperties;
    private Object FloatProperties;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getJMSTimestamp() {

        if (StrUtil.isBlank(JMSTimestamp)) {
            return LocalDateTime.now().format(formatter);
        }
        // 解析输入的日期时间字符串
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(JMSTimestamp);
        // 定义目标格式
        // 转换为目标格式的字符串
        return offsetDateTime.format(formatter);
    }

    public LocalDateTime getSendTime() {
        // 转换为目标格式的字符串
        return LocalDateTime.parse(getJMSTimestamp(), formatter);
    }

    public static void main(String[] args) {
        QueueMessage msg = new QueueMessage();
        msg.setJMSTimestamp("2025-05-13T14:39:37+08:00");
        System.out.println(msg.getSendTime());
        System.out.println(msg.getJMSTimestamp());
    }

}
