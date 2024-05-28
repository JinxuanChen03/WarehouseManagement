package com.bjtu.warehouse.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import com.github.jasync.sql.db.mysql.util.URLParser;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class MysqlConfig {

    @Value("${spring.r2dbc.url}")
    private String url;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        com.github.jasync.sql.db.Configuration configuration = URLParser.INSTANCE.parseOrDie(String.format(url, username, password), StandardCharsets.UTF_8);
//        return new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));
//    }
}
