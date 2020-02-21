package pers.darren.demo.common;

import static freemarker.template.Configuration.VERSION_2_3_28;
import static freemarker.template.TemplateExceptionHandler.HTML_DEBUG_HANDLER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.CHINA;

import freemarker.template.Configuration;
import freemarker.template.SimpleScalar;

public final class ConfigSingletonDL {

	private ConfigSingletonDL() {
		// 配置FreeMarker
		configuration = new Configuration(VERSION_2_3_28);
		configuration.setLocale(CHINA);
		configuration.setDateFormat("yyyy-MM-dd");
		configuration.setTimeFormat("HH:mm:ss");
		configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		configuration.setOutputEncoding(UTF_8.name());
		configuration.setDefaultEncoding(UTF_8.name());
		configuration.setURLEscapingCharset(UTF_8.name());
		configuration.setTemplateUpdateDelayMilliseconds(1000);
		configuration.setTemplateExceptionHandler(HTML_DEBUG_HANDLER);
		configuration.setClassForTemplateLoading(ConfigSingletonDL.class, "/view/templates");
		configuration.setSharedVariable("homeUrl", new SimpleScalar("http://localhost:8081/demo"));
	}

	private Configuration configuration;

	public Configuration getConfiguration() {
		return this.configuration;
	}

	private static volatile ConfigSingletonDL configSingletonDL;

	public static final ConfigSingletonDL getInstance() {
		if (configSingletonDL == null) {
			synchronized (ConfigSingletonDL.class) {
				if (configSingletonDL == null) {
					configSingletonDL = new ConfigSingletonDL();
				}
			}
		}
		return configSingletonDL;
	}

	public static final Configuration getPropConfiguration() {
		return getInstance().getConfiguration();
	}
}