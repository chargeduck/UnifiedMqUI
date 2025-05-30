package net.lesscoding.unified.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.model.vo.rocketmq.BrokerInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import org.apache.rocketmq.common.protocol.body.ClusterInfo;
import org.apache.rocketmq.common.protocol.body.KVTable;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author eleven
 * @date 2025/5/29 16:38
 * @apiNote
 */
@Component
@Slf4j
public class RocketMqUtil {
    public DefaultMQAdminExt adminExt(ConnectConfig config) {
        DefaultMQAdminExt adminExt = new DefaultMQAdminExt();
        adminExt.setNamesrvAddr(StrUtil.format("{}:{}", config.getHost(), config.getPort()));
        try {
            adminExt.start();
        } catch (Exception e){
            log.info("RocketMqUtil 启动失败");
        }
        return adminExt;
    }

    public ClusterInfo clusterInfo(ConnectConfig config) {
        DefaultMQAdminExt adminExt = adminExt(config);
        ClusterInfo clusterInfo = null;
        try{
            return adminExt.examineBrokerClusterInfo();
        } catch (Exception e){
            log.info("获取集群信息失败");
            return null;
        }
    }

    public Map<String, Map<Long, BrokerInfo>> brokerServer(ConnectConfig config) throws Exception {
        ClusterInfo clusterInfo = clusterInfo(config);
        DefaultMQAdminExt adminExt = adminExt(config);
        Map<String, Map<Long, BrokerInfo>> resultMap = new HashMap<>();
        BrokerInfo brokerInfo = null;
        for (BrokerData brokerData : clusterInfo.getBrokerAddrTable().values()) {
            Map<Long, BrokerInfo> brokerMasterSlaveMap = Maps.newHashMap();
            for (Map.Entry<Long, String> brokerAddr : brokerData.getBrokerAddrs().entrySet()) {
                KVTable kvTable = adminExt.fetchBrokerRuntimeStats(brokerAddr.getValue());
                brokerMasterSlaveMap.put(brokerAddr.getKey(),
                        BeanUtil.mapToBean(kvTable.getTable(), BrokerInfo.class, true, null));
            }
            resultMap.put(brokerData.getBrokerName(), brokerMasterSlaveMap);
        }
        return resultMap;
    }

    public List<BrokerInfo> brokerServerList(ConnectConfig config) throws Exception {
        Map<String, Map<Long, BrokerInfo>> resultMap = brokerServer(config);
        List<BrokerInfo> resultList = new ArrayList<>();
        resultMap.forEach((brokerName, brokerIdMap) -> {
            brokerIdMap.forEach((brokerId, brokerInfo) -> {
                brokerInfo.setBrokerId(String.valueOf(brokerId));
                brokerInfo.setBrokerName(brokerName);
                resultList.add(brokerInfo);
            });
        });
        return resultList;
    }
}
