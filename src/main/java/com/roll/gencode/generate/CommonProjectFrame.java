package com.roll.gencode.generate;

import java.util.ArrayList;
import java.util.List;

import com.roll.gencode.util.GenerateUtil;

/**
 * @author haozq
 * Date: 2018/7/4 下午2:53
 */
public class CommonProjectFrame {

	/**
	 * 项目目录
	 */
	private String userDir = System.getProperty("user.dir");

	/**
	 * 默认的model目录
	 */
	private String defaultModelDir = "/src/main/resources/model";

	/**
	 * 默认的java目录
	 */
	private String javaPath = "/src/main/java";

	/**
	 * 默认的resources目录
	 */
	private String resourcesPath = "/src/main/resources";

	private static List<String> preInstallFrame = new ArrayList<>();

	static {
		preInstallFrame.add(ModelData.MODEL_DAO);
		preInstallFrame.add(ModelData.MODEL_DAO_IMPL);
		preInstallFrame.add(ModelData.MODEL_SERVICE);
		preInstallFrame.add(ModelData.MODEL_SERVICE_IMPL);
		preInstallFrame.add(ModelData.MODEL_POJO);
	}

	/**
	 * List<Model> models = new ArrayList<>();
	 * 		models.add(new Model("id", "Long", "ID"));
	 * 		models.add(new Model("name", "String", "名称"));
	 * 		models.add(new Model("age", "Integer", "年龄"));
	 * 		models.add(new Model("hobby", "List<String>", "习惯"));
	 */
	/**
	 * 预设框架生成
	 */
	public void genPreInstallFrame(String packageName, String materialName, List<Model> models) {
		for (String modelName : preInstallFrame) {
			gen(packageName, modelName, materialName, models);
		}
	}

	public void gen(String packageName, String targetModel, String materialName, List<Model> models) {
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

		String modelDir = userDir + resourcesPath + "/model";
		modelData.setModelDir(modelDir);

		String modelName = targetModel + ".ftl";
		modelData.setModelName(modelName);

		GenerateUtil.generate(modelData);
	}

	public static void main(String args[]) {
		CommonProjectFrame commonProjectFrame = new CommonProjectFrame();
		String packageName = "com.roll.gencode";
		String materialName = "demo";
		List<Model> models = new ArrayList<>();
		models.add(new Model("id", "Long", "ID"));
		models.add(new Model("name", "String", "名称"));
		models.add(new Model("age", "Integer", "年龄"));
		models.add(new Model("hobby", "List<String>", "习惯"));
		commonProjectFrame.genPreInstallFrame(packageName, materialName, models);
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
