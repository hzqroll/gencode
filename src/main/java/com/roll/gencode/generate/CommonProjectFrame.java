package com.roll.gencode.generate;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.mybatis.generator.api.ConnectionFactory;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.springframework.util.StringUtils;

import com.google.common.base.CaseFormat;
import com.roll.gencode.util.GenerateUtil;

/**
 * @author haozq
 * Date: 2018/7/4 下午2:53
 */
public class CommonProjectFrame extends CommonProjectConfig {

	/**
	 * 表名
	 */
	private static List<String> tableList = new CopyOnWriteArrayList<>();

	/**
	 * 表信息
	 */
	private static Map<String, List<Model>> tableMap = new ConcurrentHashMap<>();

	private static List<String> preInstallFrame = new ArrayList<>();

	static {
		preInstallFrame.add(ModelData.MODEL_DAO);
		preInstallFrame.add(ModelData.MODEL_DAO_IMPL);
		preInstallFrame.add(ModelData.MODEL_SERVICE);
		preInstallFrame.add(ModelData.MODEL_SERVICE_IMPL);
		preInstallFrame.add(ModelData.MODEL_POJO);

		initConfig();
		setTableList();
		setTableMap();
	}

	/**
	 * 生成顺序：mybasit->pojo->dao->service
	 */
	private void genPreInstallFrame(String packageName, String materialName, List<Model> models) {
		for (String modelName : preInstallFrame) {
			genDefaultJava(packageName, modelName, materialName, models);
		}
	}

	/**
	 * 预设java框架生成
	 */
	private void genDefaultJava(String packageName, String targetModel, String materialName, List<Model> models) {
		ModelData modelData = new ModelData();
		modelData.setPackageName(packageName);
		modelData.setTargetModel(targetModel);
		modelData.setMaterialName(materialName);
		modelData.setModels(models);

		String targetDir;
		if (targetModel.endsWith("Impl")) {
			targetDir = userDir + javaPath + covertToDir(packageName) + "/" + CovertToSplitDir(targetModel);
		} else {
			targetDir = userDir + javaPath + covertToDir(packageName) + "/" + unCaptureName(targetModel);
		}
		modelData.setTargetDir(targetDir);

		String targetName;
		if (targetModel.endsWith(ModelData.MODEL_POJO)) {
			targetName = captureName(materialName) + ".java";
		} else {
			targetName = captureName(materialName) + captureName(targetModel) + ".java";
		}
		modelData.setTargetName(targetName);

		modelData.setModelDir(userDir + modelDir);

		String modelName = targetModel + ".ftl";
		modelData.setModelName(modelName);

		GenerateUtil.generate(modelData);
		LOG.info(".java 生成成功!");
	}

	/**
	 * 预设resource框架生成
	 */
	private void genDefaultResource(String packageName, String targetModel, String materialName, String tableName, List<Model> models) {
		ModelData modelData = new ModelData();
		modelData.setPackageName(packageName);
		modelData.setTargetModel(targetModel);
		modelData.setMaterialName(materialName);
		modelData.setTableName(tableName);
		modelData.setModels(models);

		String targetDir = userDir + resourcesPath + "/" + unCaptureName(targetModel);
		modelData.setTargetDir(targetDir);

		String targetName = captureName(materialName) + captureName(targetModel) + ".xml";
		modelData.setTargetName(targetName);

		modelData.setModelDir(userDir + modelDir);

		String modelName = targetModel + ".ftl";
		modelData.setModelName(modelName);

		GenerateUtil.generate(modelData);
		LOG.info(".resource 生成成功!");
	}

	public void genJava() {
		CommonProjectFrame commonProjectFrame = new CommonProjectFrame();
		for (String tableName : tableList) {
			String materialName = convertName(tableName);
			List<Model> models = tableMap.get(tableName);
			commonProjectFrame.genPreInstallFrame(packageName, materialName, models);
		}
	}

	public void genMapper() {
		CommonProjectFrame commonProjectFrame = new CommonProjectFrame();
		for (String tableName : tableList) {
			String materialName = convertName(tableName);
			List<Model> models = tableMap.get(tableName);
			commonProjectFrame.genDefaultResource(packageName, "mapper", materialName, tableName, models);
		}
	}

	/**
	 * 获取数据库中的表名
	 */
	public static void setTableList() {
		tableList.clear();
		try {
			JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
			jdbcConnectionConfiguration.setConnectionURL(jdbcUrl);
			jdbcConnectionConfiguration.setUserId(jdbcUser);
			jdbcConnectionConfiguration.setPassword(jdbcPassword);
			jdbcConnectionConfiguration.setDriverClass(jdbcDriver);
			ConnectionFactory connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
			Connection connection = connectionFactory.getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getTables(null, "%", null, new String[]{"TABLE"});
			while (resultSet.next()) {
				tableList.add(resultSet.getString("TABLE_NAME"));
			}
			connection.close();
		} catch (Exception e) {
			LOG.error("get table list failed.", e);
		}
	}

	/**
	 * 获取数据库中的表信息（字段，类型，描述）
	 */
	public static void setTableMap() {
		tableMap.clear();
		try {
			JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
			jdbcConnectionConfiguration.setConnectionURL(jdbcUrl);
			jdbcConnectionConfiguration.setUserId(jdbcUser);
			jdbcConnectionConfiguration.setPassword(jdbcPassword);
			jdbcConnectionConfiguration.setDriverClass(jdbcDriver);
			ConnectionFactory connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
			Connection connection = connectionFactory.getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			for (String tableName : tableList) {
				ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
				List<Model> modelList = new ArrayList<>();
				while (resultSet.next()) {
					Model model = new Model();
					String columnName = resultSet.getString("COLUMN_NAME");
					String columnType = resultSet.getString("TYPE_NAME");
					String desc = resultSet.getString("REMARKS");
					model.setName(convertName(columnName));
					model.setColumn(columnName);
					model.setType(convertType(columnType));
					model.setDesc(StringUtils.isEmpty(desc) ? convertName(columnName) : desc);
					modelList.add(model);
				}
				tableMap.put(tableName, modelList);
			}
			connection.close();
		} catch (Exception e) {
			LOG.error("get table column failed.", e);
		}
	}

	/**
	 * 用用户自定义的表名代替全部表名
	 *
	 * @param tableList 用户自定义表
	 */
	public void setTableList(List<String> tableList) {
		CommonProjectFrame.tableList.clear();
		CommonProjectFrame.tableList.addAll(tableList);
	}

	public static void main(String args[]) {
		CommonProjectFrame commonProjectFrame = new CommonProjectFrame();
		commonProjectFrame.genMapper();
		commonProjectFrame.genJava();
	}

	/**
	 * 转换a.b.c成/a/b/c
	 */
	private String covertToDir(String name) {
		return ("/" + name).replace(".", "/");
	}

	/**
	 * 替换首字母为大写
	 */
	private static String captureName(String name) {
		if (name.isEmpty()) {
			return name;
		}
		char[] cs = name.toCharArray();
		if (cs[0] >= 97 && cs[0] <= 122) {
			cs[0] -= 32;
			return String.valueOf(cs);
		} else {
			return name;
		}
	}

	/**
	 * 替换首字母为小写
	 */
	private static String unCaptureName(String name) {
		if (name.isEmpty()) {
			return name;
		}
		char[] cs = name.toCharArray();
		if (cs[0] >= 65 && cs[0] <= 90) {
			cs[0] += 32;
			return String.valueOf(cs);
		} else {
			return name;
		}
	}

	private String CovertToSplitDir(String name) {
		StringBuilder sb = new StringBuilder(name.toLowerCase());
		return sb.insert(name.lastIndexOf("Impl"), "/").toString().toLowerCase();
	}

	/**
	 * 替换数据库类型为java类型
	 *
	 * @param columnType 数据库字段类型
	 * @return java类型
	 */
	private static String convertType(String columnType) {
		columnType = columnType.toLowerCase();
		if (columnType.contains("bigint")) {
			return "long";
		} else if (columnType.contains("int")) {
			return "int";
		} else if (columnType.contains("time")) {
			return "Date";
		} else {
			return "String";
		}
	}

	/**
	 * 下划线命名，转换为驼峰命名
	 *
	 * @param columnName 表名
	 */
	private static String convertName(String columnName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName.toLowerCase());
	}
}
