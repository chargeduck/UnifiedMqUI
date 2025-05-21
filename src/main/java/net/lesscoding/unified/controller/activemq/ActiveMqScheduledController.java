package net.lesscoding.unified.controller.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SchedulerQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SchedulerInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.service.activemq.ActiveMqSchedulerService;
import org.springframework.web.bind.annotation.*;

/**
 * @author eleven
 * @date 2025/5/20 16:45
 * @apiNote
 */
@RestController
@RequestMapping("/activemq/scheduled")
@RequiredArgsConstructor
public class ActiveMqScheduledController {
    private final ActiveMqSchedulerService schedulerService;

    @PostMapping("/jobScheduler")
    public Result<SearchResult> jobScheduler(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(schedulerService.jobScheduler(dto));
    }

    @PostMapping("/allJobs")
    public Result<Page<SchedulerInfo>> allJobs(@RequestBody CommonQueryDto<SchedulerQueryDto> dto) {
        return Result.success(schedulerService.allJobs(dto));
    }

    @DeleteMapping("/removeJob")
    public Result<Boolean> removeJobs(@RequestBody CommonQueryDto<SchedulerQueryDto> dto) {
        return Result.success(schedulerService.removeJobs(dto));
    }

}
