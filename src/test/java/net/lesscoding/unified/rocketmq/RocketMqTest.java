package net.lesscoding.unified.rocketmq;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.MQVersion;
import org.apache.rocketmq.common.protocol.body.ClusterInfo;
import org.apache.rocketmq.common.protocol.body.KVTable;
import org.apache.rocketmq.common.protocol.header.GetBrokerConfigResponseHeader;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author eleven
 * @date 2025/5/22 13:53
 * @apiNote
 */
@Slf4j
public class RocketMqTest {
    private String nameServer = "localhost:9876";
    private String brokerServer = "localhost:10911";
    private DefaultMQAdminExt adminExt = new DefaultMQAdminExt();
    private Gson gson = new Gson();

    @Test
    public void versionTest() throws Exception {

        adminExt.setNamesrvAddr("localhost:9876");
        adminExt.start();
        System.out.println("RocketMQ 版本: " + adminExt);
        Properties brokerConfig = adminExt.getBrokerConfig(brokerServer);
        System.out.println("Broker: " + gson.toJson(brokerConfig));
        System.out.println();
        Map<String, Properties> nameServerConfig = adminExt.getNameServerConfig(Collections.singletonList(nameServer));
        System.out.println("NameServer: " + gson.toJson(nameServerConfig));
        System.out.println();
        adminExt.shutdown();
        String versionDesc = MQVersion.getVersionDesc(MQVersion.CURRENT_VERSION);
        System.out.println("当前 RocketMQ 版本: " + versionDesc);
        System.out.println();
        GetBrokerConfigResponseHeader getBrokerConfigResponseHeader = new GetBrokerConfigResponseHeader();
    }

    @Test
    public void remotingVersionTest() throws Exception {
        adminExt.setNamesrvAddr("localhost:9876");
        adminExt.start();
        ClusterInfo clusterInfo = adminExt.examineBrokerClusterInfo();
        log.info("op=look_clusterInfo {}", gson.toJson(clusterInfo));
        Map<String, Object> resultMap = new HashMap<>();
        Map<String/*brokerName*/, Map<Long/* brokerId */, Object/* brokerDetail */>> brokerServer = Maps.newHashMap();
        for (BrokerData brokerData : clusterInfo.getBrokerAddrTable().values()) {
            Map<Long, Object> brokerMasterSlaveMap = Maps.newHashMap();
            for (Map.Entry<Long/* brokerId */, String/* broker address */> brokerAddr : brokerData.getBrokerAddrs().entrySet()) {
                KVTable kvTable = adminExt.fetchBrokerRuntimeStats(brokerAddr.getValue());
//                KVTable kvTable = mqAdminExt.fetchBrokerRuntimeStats("127.0.0.1:10911");
                brokerMasterSlaveMap.put(brokerAddr.getKey(), kvTable.getTable());
            }
            brokerServer.put(brokerData.getBrokerName(), brokerMasterSlaveMap);
        }
        resultMap.put("clusterInfo", clusterInfo);
        resultMap.put("brokerServer", brokerServer);
        resultMap.forEach((k, v) -> {
            log.info("op=look_clusterInfo key={} value={}", k, gson.toJson(v));
        });
    }
}
