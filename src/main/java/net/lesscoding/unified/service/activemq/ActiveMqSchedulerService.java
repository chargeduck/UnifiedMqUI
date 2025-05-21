package net.lesscoding.unified.service.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SchedulerQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SchedulerInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;

/**
 * @author eleven
 * @date 2025/5/20 16:47
 * @apiNote
 */
public interface ActiveMqSchedulerService {
    SearchResult jobScheduler(CommonQueryDto<String> dto);

    Page<SchedulerInfo> allJobs(CommonQueryDto<SchedulerQueryDto> dto);

    Boolean removeJobs(CommonQueryDto<SchedulerQueryDto> dto);
}
