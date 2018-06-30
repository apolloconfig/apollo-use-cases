package com.ctrip.framework.apollo.use.cases.dynamic.datasource;

import com.ctrip.framework.apollo.use.cases.dynamic.datasource.ds.DynamicDataSource;
import com.ctrip.framework.apollo.use.cases.dynamic.datasource.util.DataSourceManager;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RefreshableDataSourceConfiguration {
  public static final String DATA_SOURCE_PROPERTIES_BEAN = "DATA_SOURCE_PROPERTIES_BEAN";

  @Primary
  @Bean(DATA_SOURCE_PROPERTIES_BEAN)
  @RefreshScope
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DynamicDataSource dataSource(DataSourceManager dataSourceManager) {
    return new DynamicDataSource(dataSourceManager.createDataSource());
  }
}
