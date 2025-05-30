package net.lesscoding.unified.service.impl.rocketmq;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.model.vo.rocketmq.BrokerInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.rocketmq.RocketMqBrokerService;
import net.lesscoding.unified.utils.RocketMqUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/29 15:20
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RocketMqBrokerServiceImpl implements RocketMqBrokerService {

    private final RocketMqUtil rocketMqUtil;

    private final Gson gson;

    @Override
    public List<BrokerInfo> getBrokerInfo(ConnectConfig connectConfig) throws Exception {
        return rocketMqUtil.brokerServerList(connectConfig);
    }


}
