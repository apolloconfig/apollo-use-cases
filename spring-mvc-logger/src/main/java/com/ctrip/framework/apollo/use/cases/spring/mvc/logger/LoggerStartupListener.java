package com.ctrip.framework.apollo.use.cases.spring.mvc.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @author bugcoder
 * @date 2021/3/17
 */
@Component
@Slf4j
public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	private static boolean started = false;
	private static String APOLLO_SETTING_NAME = "apollo.setting.app.name";
	private static String LOGBACK_SETTING_NAME = "appname";

	@Override
	public void start() {
		if (started) {
			return;
		}
		//从apollo中获取所有配置信息
		Config config = ConfigService.getAppConfig();
		String apolloValue = config.getProperty(APOLLO_SETTING_NAME, "这里设置默认值");
		Context context = getContext();
		//ConfigChangeListener用来监听apollo配置的变化
		config.addChangeListener(new ConfigChangeListener() {
			@Override
			public void onChange(ConfigChangeEvent configChangeEvent) {
				for (String key : configChangeEvent.changedKeys()) {
					ConfigChange change = configChangeEvent.getChange(key);
					reloadDefaultConfiguration(change);
				}
			}
		});
		context.putProperty(LOGBACK_SETTING_NAME, apolloValue);
		log.info("the value of the logback field from apollo, apollo.setting.app.name is {}", apolloValue);
		started = true;
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public boolean isResetResistant() {
		return true;
	}

	@Override
	public void onStart(LoggerContext context) {

	}

	@Override
	public void onReset(LoggerContext context) {

	}

	@Override
	public void onStop(LoggerContext context) {

	}

	@Override
	public void onLevelChange(Logger logger, Level level) {

	}

	/**
	 * 重新加载logback, 并更新logback字段的值
	 * @param change	这里面包含了apollo的变化值
	 */
	private void reloadDefaultConfiguration(ConfigChange change)  {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		ContextInitializer ci = new ContextInitializer(loggerContext);
		URL url = ci.findURLOfDefaultConfigurationFile(true);
		loggerContext.reset();
		loggerContext.putProperty(LOGBACK_SETTING_NAME,change.getNewValue());
		try {
			ci.configureByResource(url);
		} catch (JoranException e) {
			e.printStackTrace();
		}
		log.info("reload loggerContext , you can see that the log has been updated, new value from apollo is {}",change.getNewValue());
	}
}
