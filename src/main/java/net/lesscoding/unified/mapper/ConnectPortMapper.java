package net.lesscoding.unified.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lesscoding.unified.core.model.dto.ExtraInputPort;
import net.lesscoding.unified.entity.ConnectPort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/24 10:34
 * @apiNote
 */
public interface ConnectPortMapper extends BaseMapper<ConnectPort> {
    int insertBatch(@Param("list") List<ConnectPort> ports);

    int updateBatch(@Param("list") List<ConnectPort> updateList);

    List<ExtraInputPort> getExtraPorts(Integer id);
}
