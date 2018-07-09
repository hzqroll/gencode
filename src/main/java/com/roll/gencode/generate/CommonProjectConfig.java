package com.roll.gencode.generate;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haozq
 * Date: 2018/7/9 上午9:09
 */
class CommonProjectConfig {
	static Logger LOG = LoggerFactory.getLogger("CREATE_PROJECT_FRAME_LOG");
	/**
	 * 项目目录
	 */
	static String userDir = System.getProperty("user.dir");
	/**
	 * 默认的model目录
	 */
	static String modelDir = "/src/main/resources/model";
	/**
	 * 默认的java目录
	 */
	static String javaPath = "/src/main/java";
	/**
	 * 默认的resources目录
	 */
	static String resourcesPath = "/src/main/resources";

	static String jdbcUrl;

	static String jdbcUser;

	static String jdbcPassword;

	static String jdbcDriver;

	static String packageName;

	private static String propertisePath = "genConfig.properties";

	/**
	 * 加载配置文件
	 *
	 * @return 配置文件信息
	 */
	private static Properties loadProperties() {
		Properties prop;
		try {
			prop = new Properties();
			InputStream in = CommonProjectConfig.class.getClassLoader().getResourceAsStream(propertisePath);
			prop.load(in);
		} catch (Exception e) {
			throw new RuntimeException("加载配置文件异常!", e);
		}
		return prop;
	}

	static void initConfig() {
		Properties prop = loadProperties();
		userDir = System.getProperty("user.dir");
		modelDir = prop.getProperty("modelDir");
		javaPath = prop.getProperty("javaPath");
		resourcesPath = prop.getProperty("resourcesPath");
		packageName = prop.getProperty("packageName");

		jdbcUrl = prop.getProperty("jdbc.url");
		jdbcUser = prop.getProperty("jdbc.username");
		jdbcPassword = prop.getProperty("jdbc.password");
		jdbcDriver = prop.getProperty("jdbc.driver.class.name");
	}

	public String getPropertisePath() {
		return propertisePath;
	}

	public void setPropertisePath(String propertisePath) {
		this.propertisePath = propertisePath;
	}
}
