package com.crawler.c_api_new;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DBConnector {

    public static final HikariDataSource dataSource = new HikariDataSource();
    public static final HikariDataSource dataSource1 = new HikariDataSource();
    public static final int MINER_DB = 0;
    public static final int KEYWORD_DB = 1;

    static {
        try {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/crawlerdb");
            dataSource.setUsername("root2");
            dataSource.setPassword("biersaufen");
            dataSource.setMaximumPoolSize(15);
            dataSource.setMinimumIdle(1);
            dataSource.setLeakDetectionThreshold(15000);
            dataSource.setConnectionTestQuery("SELECT 1");
            dataSource.setConnectionTimeout(34000);
            dataSource.setIdleTimeout(28740000);
            dataSource.setMaxLifetime(28740000);

            dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/keyworddb");
            dataSource1.setUsername("root2");
            dataSource1.setPassword("biersaufen");
            dataSource1.setMaximumPoolSize(15);
            dataSource1.setMinimumIdle(1);
            dataSource1.setLeakDetectionThreshold(15000);
            dataSource1.setConnectionTestQuery("SELECT 1");
            dataSource1.setConnectionTimeout(34000);
            dataSource1.setIdleTimeout(28740000);
            dataSource1.setMaxLifetime(28740000);
        } catch (Exception t) {
            Logger logger = LoggerFactory.getLogger(DBConnector.class);
            logger.error("Failure during static initialization", t);
            throw t;
        }

    }

    private DBConnector() {
        //
    }

    public static Connection getConnection(int dataBase) throws SQLException {
        if (dataBase == 0) {
            return dataSource.getConnection();
        } else if (dataBase == 1) {
            return dataSource1.getConnection();
        } else {
            return dataSource.getConnection();
        }
    }
}
