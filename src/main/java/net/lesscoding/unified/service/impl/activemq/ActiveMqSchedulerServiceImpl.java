package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.enums.activemq.ActiveMqMethod;
import net.lesscoding.unified.core.enums.activemq.JolokiaExecuteType;
import net.lesscoding.unified.core.enums.activemq.MbeanFormat;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.JolokiaQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SchedulerQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SchedulerInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqSchedulerService;
import net.lesscoding.unified.utils.PageUtil;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author eleven
 * @date 2025/5/20 16:47
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ActiveMqSchedulerServiceImpl implements ActiveMqSchedulerService {
    private final JolokiaUtil jolokiaUtil;

    @Override
    public SearchResult jobScheduler(CommonQueryDto<String> dto) {

        ConnectConfig config = dto.getConfig();
        String mbean = StrUtil.format(MbeanFormat.BROKER_OP.getFormat(), config.getBrokerName());
        String key = "JMSJobScheduler";
        ActiveMqJolokiaResponse<SearchResult> response = jolokiaUtil.doSearchAttribute(
                config,
                JolokiaExecuteType.READ,
                mbean,
                key,
                new TypeToken<>() {
                }
        );
        return response.getValue();
    }

    @Override
    public Page<SchedulerInfo> allJobs(CommonQueryDto<SchedulerQueryDto> dto) {
        ConnectConfig config = dto.getConfig();
        SchedulerQueryDto params = dto.getParams();
        PageDTO page = dto.getPage();
        String key = "AllJobs";
        JolokiaQueryDto queryDto = new JolokiaQueryDto()
                .setType(JolokiaExecuteType.READ.getType())
                .setMbean(params.getObjectName())
                .setAttribute(Collections.singletonList(key));
        ActiveMqJolokiaResponse<Map<String, Map<String, SchedulerInfo>>> response = jolokiaUtil.getByQueryDto(config, queryDto, new TypeToken<>() {

        });
        List<SchedulerInfo> schedulerInfos = new ArrayList<>(response.getValue().get(key).values());
        if (CollUtil.isNotEmpty(schedulerInfos)) {
            schedulerInfos = schedulerInfos.stream()
                    .filter(item -> params.getStart() == null || !item.getStartTime().isBefore(params.getStart()))
                    .filter(item -> params.getEnd() == null || !item.getStartTime().isAfter(params.getEnd()))
                    .collect(Collectors.toList());
        }
        return PageUtil.getPageByGetter(schedulerInfos, page::getCurrent, page::getSize);
    }

    @Override
    public Boolean removeJobs(CommonQueryDto<SchedulerQueryDto> dto) {
        ConnectConfig config = dto.getConfig();
        SchedulerQueryDto params = dto.getParams();
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethodWithTypeToken(
                config,
                JolokiaExecuteType.EXEC,
                params.getObjectName(),
                ActiveMqMethod.BROKER_REMOVE_JOB,
                List.of(params.getJobId()),
                new TypeToken<>() {

                }
        );
        return response.getStatus() == 200;
    }

    public static void main(String[] args) {
        String str = "{\"request\":{\"mbean\":\"org.apache.activemq:brokerName=localhost,name=JMS,service=JobScheduler,type=Broker\",\"attribute\":\"AllJobs\",\"type\":\"read\"},\"value\":{\"AllJobs\":{\"ID:eleven-60793-1747729261767-6:1:1:1:2\":{\"next\":\"2025-05-20 18:00:00\",\"cronEntry\":\"0\\/1 * * * ?\",\"jobId\":\"ID:eleven-60793-1747729261767-6:1:1:1:2\",\"period\":0,\"delay\":0,\"repeat\":0,\"start\":\"2025-05-20 16:41:01\"},\"ID:eleven-60793-1747729261767-6:1:1:1:1\":{\"next\":\"2025-05-20 18:00:00\",\"cronEntry\":\"0\\/1 * * * ?\",\"jobId\":\"ID:eleven-60793-1747729261767-6:1:1:1:1\",\"period\":0,\"delay\":0,\"repeat\":0,\"start\":\"2025-05-20 16:34:42\"}}},\"timestamp\":1747733829,\"status\":200}";
        ActiveMqJolokiaResponse response = new Gson()
                .fromJson(str, new TypeToken<ActiveMqJolokiaResponse<Map<String, Map<String, SchedulerInfo>>>>() {

                }.getType());
        System.out.println(response);
    }
}
