package com.ctrip.framework.apollo.use.cases.dynamic.datasource.util;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.use.cases.dynamic.datasource.RefreshableDataSourceConfiguration;
import com.ctrip.framework.apollo.use.cases.dynamic.datasource.ds.DynamicDataSource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRefresher {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceRefresher.class);

  private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

  @Autowired
  private DynamicDataSource dynamicDataSource;

  @Autowired
  private DataSourceManager dataSourceManager;

  @Autowired
  private RefreshScope refreshScope;

  @ApolloConfigChangeListener
  public void onChange(ConfigChangeEvent changeEvent) {
    boolean dataSourceConfigChanged = false;
    for (String changedKey : changeEvent.changedKeys()) {
      if (changedKey.startsWith("spring.datasource.")) {
        dataSourceConfigChanged = true;
        break;
      }
    }

    if (dataSourceConfigChanged) {
      try {
        logger.info("Refreshing data source");
        refreshScope.refresh(RefreshableDataSourceConfiguration.DATA_SOURCE_PROPERTIES_BEAN);
        DataSource newDataSource = dataSourceManager.createAndTestDataSource();
        DataSource oldDataSource = dynamicDataSource.setDataSource(newDataSource);
        asyncTerminate(oldDataSource);
        logger.info("Finished refreshing data source");
      } catch (Throwable ex) {
        logger.error("Refreshing data source failed", ex);
      }
    }
  }

  private void asyncTerminate(DataSource dataSource) {
    DataSourceTerminationTask task = new DataSourceTerminationTask(dataSource, scheduledExecutorService);

    //start now
    scheduledExecutorService.schedule(task, 0, TimeUnit.MILLISECONDS);
  }
}
