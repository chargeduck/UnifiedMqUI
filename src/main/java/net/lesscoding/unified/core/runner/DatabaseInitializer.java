package net.lesscoding.unified.core.runner;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.enums.InitTable;
import net.lesscoding.unified.mapper.SysMapper;
import net.lesscoding.unified.utils.IOStreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DatabaseInitializer implements ApplicationRunner {

    @Autowired
    private SysMapper sysMapper;

    @Value("${spring.datasource.url:jdbc:derby:/.derby/data/unified_mq_console;multipleConnectionsAllowed=true}")
    private String url;

    @Value("${spring.datasource.username:root}")
    private String username;

    @Value("${spring.datasource.password:unified_mq_console}")
    private String password;

    private final String AUTO_CREATE = ";create=true";
    public void initTables() {
        List<InitTable> initTables = Arrays.asList(InitTable.values());
        log.info("默认数据库表{}", initTables);
        List<String> allTables = sysMapper.getAllTables();
        log.info("当前数据库表{}", allTables);
        initTables.forEach(initTable -> {
            String tbName = initTable.getTbName();
            if (!allTables.contains(tbName.toUpperCase())) {
                ClassPathResource classPathResource = new ClassPathResource(initTable.getPath());
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
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            log.info("已存在数据库，不执行自动创建操作");
        } catch (SQLException e) {
            log.info("不存在数据库,添加参数[{}]执行自动创建操作", AUTO_CREATE);
            url += AUTO_CREATE;
            try {
                DriverManager.getConnection(url, username, password);
                log.info("数据库创建成功");
            } catch (SQLException ex) {
                log.error("Derby数据库错误，请联系管理员重试");
            }
        }
    }
}