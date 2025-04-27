package net.lesscoding.unified.core.runner;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.enums.SqliteInitTable;
import net.lesscoding.unified.mapper.SysMapper;
import net.lesscoding.unified.utils.IOStreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class SqliteDatabaseInitializer implements ApplicationRunner {

    @Autowired
    private SysMapper sysMapper;

    @Value("${spring.datasource.url:jdbc:sqlite:/.sqlite/data/unified_mq_console.sqlite}")
    private String url;
    public void initTables() {
        List<SqliteInitTable> sqliteInitTables = Arrays.asList(SqliteInitTable.values());
        log.info("默认数据库表{}", sqliteInitTables);
        List<String> allTables = sysMapper.getAllSqliteTables();
        log.info("当前数据库表{}", allTables);
        sqliteInitTables.forEach(derbyInitTable -> {
            String tbName = derbyInitTable.getTbName();
            if (!allTables.contains(tbName.toUpperCase())) {
                ClassPathResource classPathResource = new ClassPathResource(derbyInitTable.getPath());
                try {
                    String sql = IOStreamUtils.inputStreamToString(classPathResource.getStream());
                    log.info("表名{}，sql{}", tbName, sql);
                    sysMapper.createTable(sql);
                    log.info("创建表{}成功", tbName);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        });
        log.info("初始化数据库表完成");
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTables();
    }

    @PostConstruct
    public void initDatabase() {
        checkDataPath();

        try (Connection connection = DriverManager.getConnection(url)) {
            log.info("已存在数据库，不执行自动创建操作");
        } catch (SQLException e) {
            log.info("数据库创建成功");
        }
    }

    private void checkDataPath() {
        // 提取数据库文件路径
        String dbFilePath = url.replace("jdbc:sqlite:", "");
        File dbFile = new File(dbFilePath);
        File parentDir = dbFile.getParentFile();
        // 检查并创建目录
        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                log.info("成功创建数据库目录: {}", parentDir.getAbsolutePath());
            } else {
                log.error("无法创建数据库目录: {}", parentDir.getAbsolutePath());
            }
        }
    }
}