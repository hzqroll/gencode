package com.roll.gencode.generate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by haozq
 * Date: 2018/7/3 上午10:59
 */
public class ModelData {

	public static final String MODEL_DAO = "Dao";
	public static final String MODEL_DAO_IMPL = "DaoImpl";
	public static final String MODEL_SERVICE = "Service";
	public static final String MODEL_SERVICE_IMPL = "ServiceImpl";
	public static final String MODEL_POJO = "pojo";

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * 项目目录
	 */
	private String userDir = System.getProperty("user.dir");

	private String defaultModelDir = "/src/main/resources/model";

	private String javaPath = "/src/main/java";

	private String resourcesPath = "/src/main/resources";

	private String packageNameDir;
	/**
	 * model文件目录
	 */
	private String modelDir;

	/**
	 * model名称
	 */
	private String modelName;

	/**
	 * 需要创建的文件目录
	 */
	private String targetDir;

	/**
	 * 需要创建的文件名
	 */
	private String targetName;

	/**
	 * 需要生成的
	 * <P>默认生成POJO
	 */
	private String targetModel = MODEL_POJO;
	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 作者名
	 * <P>默认当前用户名
	 */
	private String author = System.getProperty("user.name");

	/**
	 * 创建日期
	 * <P>默认当前日期
	 */
	private String date = simpleDateFormat.format(new Date());

	/**
	 * 需要创建的对象名称；
	 * <P>比如：abc，创建service的时候类名为AbcService；
	 * <p>默认首字母大写，末尾加上对应模块信息
	 */
	private String materialName;

	private List<Model> models = new ArrayList<>();

	/**
	 * 具体内容
	 */
	private Map<String, Object> params;

	private String tableName;

	public String getModelDir() {
		return modelDir;
	}

	public void setModelDir(String modelDir) {
		this.modelDir = modelDir;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetModel() {
		return targetModel;
	}

	public void setTargetModel(String targetModel) {
		this.targetModel = targetModel;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 构造默认的代码结构
	 *
	 * @param packageName  包名
	 * @param targetModel  需要生成的目标：dao、service、pojo
	 * @param materialName 表名或者基础名称
	 */
	public void setDefaultPackageName(String packageName, String targetModel, String materialName) {
		this.packageName = packageName;
		this.targetModel = targetModel;
		this.materialName = materialName;
		this.targetDir = userDir + javaPath + captureName(this.materialName);
		this.targetName = captureName(this.materialName) + captureName(this.targetModel);
		this.modelDir = userDir + resourcesPath + "/model";
		this.modelName = this.targetModel + ".ftl";
	}

	public static final class ModelDataBuilder {
		private String modelDir;
		private String modelName;
		private String targetDir;
		private String targetName;
		private String packageName;
		private String targetModel = MODEL_POJO;
		private String author = System.getProperty("user.name");
		private String date = simpleDateFormat.format(new Date());
		private String materialName;
		private List<Model> models = new ArrayList<>();
		private Map<String, Object> params;

		public ModelDataBuilder() {
		}

		public ModelDataBuilder atModelDir(String modelDir) {
			this.modelDir = modelDir;
			return this;
		}

		public ModelDataBuilder atModelName(String modelName) {
			this.modelName = modelName;
			return this;
		}

		public ModelDataBuilder atTargetDir(String targetDir) {
			this.targetDir = targetDir;
			return this;
		}

		public ModelDataBuilder atTargetName(String targetName) {
			this.targetName = targetName;
			return this;
		}

		public ModelDataBuilder atPackageName(String packageName) {
			this.packageName = packageName;
			return this;
		}

		public ModelDataBuilder atTargetModel(String targetModel) {
			this.targetModel = targetModel;
			return this;
		}

		public ModelDataBuilder atAuthor(String author) {
			this.author = author;
			return this;
		}

		public ModelDataBuilder atDate(String date) {
			this.date = date;
			return this;
		}

		public ModelDataBuilder atMaterialName(String materialName) {
			this.materialName = materialName;
			return this;
		}

		public ModelDataBuilder atModels(List<Model> models) {
			this.models = models;
			return this;
		}

		public ModelDataBuilder atParams(Map<String, Object> params) {
			this.params = params;
			return this;
		}

		public ModelDataBuilder atDefaulePackageName(String packageName, String modelName) {
			this.packageName = packageName;
			this.modelName = modelName;
			return this;
		}

		public ModelData build() {
			ModelData modelData = new ModelData();
			modelData.setModelDir(modelDir);
			modelData.setModelName(modelName);
			modelData.setTargetDir(targetDir);
			modelData.setTargetName(targetName);
			modelData.setPackageName(packageName);
			modelData.setTargetModel(targetModel);
			modelData.setAuthor(author);
			modelData.setDate(date);
			modelData.setMaterialName(materialName);
			modelData.setModels(models);
			modelData.setParams(params);
			return modelData;
		}
	}

	public ModelData() {
	}

	/**
	 * 构造默认的代码结构
	 *
	 * @param packageName  包名
	 * @param targetModel  需要生成的目标：dao、service、pojo
	 * @param materialName 表名或者基础名称
	 */
	public ModelData(String packageName, String targetModel, String materialName) {
		this.packageName = packageName;
		this.targetModel = targetModel;
		this.materialName = materialName;
		if (targetModel.endsWith("Impl")) {
			this.targetDir = userDir + javaPath + covertToDir(packageName) + "/" + CovertToSplitDir(this.targetModel);
		} else {
			this.targetDir = userDir + javaPath + covertToDir(packageName) + "/" + unCaptureName(this.targetModel);
		}

		if (targetModel.endsWith(MODEL_POJO)) {
			this.targetName = captureName(this.materialName) + ".java";
		} else {
			this.targetName = captureName(this.materialName) + captureName(this.targetModel) + ".java";
		}
		this.modelDir = userDir + resourcesPath + "/model";
		this.modelName = this.targetModel + ".ftl";
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
}
