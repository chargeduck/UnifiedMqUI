package net.lesscoding.unified.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lesscoding.unified.entity.ConnectConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author eleven
 * @date 2025/4/25 17:02
 * @apiNote
 */
public interface ConnectConfigMapper extends BaseMapper<ConnectConfig> {

    List<ConnectConfig> getMqList(IPage page, @Param("config") ConnectConfig connectConfig);

    int insertConnectConfig(ConnectConfig connectConfig);
}
