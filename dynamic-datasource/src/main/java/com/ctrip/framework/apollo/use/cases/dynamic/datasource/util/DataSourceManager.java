package com.ctrip.framework.apollo.use.cases.dynamic.datasource.util;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DataSourceManager {

  private static final Logger logger = LoggerFactory.getLogger(DataSourceManager.class);

  @Autowired
  private CustomizedConfigurationPropertiesBinder binder;

  @Autowired
  private DataSourceProperties dataSourceProperties;

  public HikariDataSource createDataSource() {
    HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    if (StringUtils.hasText(dataSourceProperties.getName())) {
      dataSource.setPoolName(dataSourceProperties.getName());
    }
    Bindable<?> target = Bindable.of(HikariDataSource.class).withExistingValue(dataSource);
    this.binder.bind("spring.datasource.hikari", target);
    return dataSource;
  }

  public HikariDataSource createAndTestDataSource() throws SQLException {
    HikariDataSource newDataSource = createDataSource();
    try {
      testConnection(newDataSource);
    } catch (SQLException ex) {
      logger.error("Testing connection for data source failed: {}", newDataSource.getJdbcUrl(), ex);
      newDataSource.close();
      throw ex;
    }

    return newDataSource;
  }

  private void testConnection(DataSource dataSource) throws SQLException {
    dataSource.getConnection().close();
  }
}
