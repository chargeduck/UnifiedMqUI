package net.lesscoding.unified.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author eleven
 * @date 2024/8/15 10:31
 * @apiNote
 */
public interface SysMapper {

    @Select("SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE = 'T'")
    List<String> getAllTables();

    Integer createTable(@Param("ddl") String ddl);
}
