package com.scccy.downloadvideo.common.core.config;



import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.Resource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

@Slf4j
@Configuration
public class DatabaseInitConfig {

    private final DataSource dataSource;
    
    @Value("${spring.sql.init.mode:never}")
    private String initMode;

    public DatabaseInitConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initDatabase() {
        if ("always".equals(initMode)) {
            log.debug("数据库初始化模式: {}", initMode);
            try {
                log.info("开始初始化数据库...");
                
                // 1. 创建数据库
                createDatabaseIfNotExists();
                
                // 2. 创建表结构
                ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
                populator.setSeparator(";");
                populator.setContinueOnError(true);
                populator.setIgnoreFailedDrops(true);
                
                // 设置编码
                populator.setSqlScriptEncoding("UTF-8");
                log.debug("SQL脚本编码设置为: UTF-8");
                
                // 添加SQL文件
                log.info("开始添加SQL脚本...");
                addSqlScript(populator, "sql/conf.sql");
                addSqlScript(populator, "sql/app.sql");
//                addSqlScript(populator, "sql/defaults.sql");
                
                // 执行SQL脚本
                log.info("开始执行SQL脚本...");
                try (Connection conn = dataSource.getConnection()) {
                    Statement stmt = conn.createStatement();
                    log.debug("切换到数据库: video_platform");
                    stmt.execute("USE video_platform");
                    populator.populate(conn);
                }

                log.info("数据库初始化完成");
            } catch (Exception e) {
                log.error("数据库初始化失败: {}", e.getMessage(), e);
                throw new RuntimeException("数据库初始化失败: " + e.getMessage(), e);
            }
        } else {
            log.info("跳过数据库初始化，当前模式：{}", initMode);
        }
    }

    private void createDatabaseIfNotExists() {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            log.debug("创建数据库: video_platform");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS video_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci");
            log.info("数据库创建成功或已存在");
        } catch (SQLException e) {
            log.error("创建数据库失败: {}", e.getMessage(), e);
            throw new RuntimeException("创建数据库失败: " + e.getMessage(), e);
        }
    }
    
    private void addSqlScript(ResourceDatabasePopulator populator, String location) {
        Resource resource = new ClassPathResource(location);
        if (resource.exists()) {
            populator.addScript(resource);
            log.debug("添加SQL脚本: {}", location);
        } else {
            log.warn("SQL文件不存在: {}", location);
        }
    }
} 