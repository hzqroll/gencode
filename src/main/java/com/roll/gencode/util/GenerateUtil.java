package com.roll.gencode.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.roll.gencode.generate.ModelData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author haozq
 * Date: 2018/7/2 下午5:02
 */
public class GenerateUtil {

	private static final Logger log = LoggerFactory.getLogger(GenerateUtil.class);

	/**
	 * // Create the root hash
	 * Map<String, Object> root = new HashMap<>();
	 * <p>
	 * root.put("packageName", "com.example.codegenerate.model");
	 * root.put("className", "Simple");
	 * root.put("author", "Roll");
	 * root.put("DATE", simpleDateFormat.format(new Date()));
	 * <p>
	 * List<Model> attr_list = new ArrayList<>();
	 * attr_list.add(new Model("id", "Long", "ID"));
	 * attr_list.add(new Model("name", "String", "名称"));
	 * attr_list.add(new Model("age", "Integer", "年龄"));
	 * attr_list.add(new Model("hobby", "List<String>", "习惯"));
	 * root.put("attrs", attr_list);
	 */
	/**
	 * 利用freeMarker创建文件
	 *
	 * @param modelData {@link ModelData}
	 */
	public static void generate(ModelData modelData) {
		try {
			File modelFile = new File(modelData.getModelDir());
			if (!modelFile.exists()) {
				log.info("init freeMaker configuration failed. case bad model location: " + modelData.getModelDir() + ", please check again! ");
			}

			Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
			configuration.setDefaultEncoding("UTF-8");
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			configuration.setDirectoryForTemplateLoading(modelFile);
			Template temp = configuration.getTemplate(modelData.getModelName());

			File targetFile = new File(modelData.getTargetDir());
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			OutputStream outputStream = new FileOutputStream(new File(targetFile, modelData.getTargetName())); //java文件的生成目录
			Writer out = new OutputStreamWriter(outputStream, "utf-8");
			temp.process(modelData, out);
			outputStream.close();
			log.info("generate code success!");
		} catch (Exception e) {
			log.error("generate code failed!", e);
		}
	}

	/*String userDir = System.getProperty("user.dir");
		String targetModel = "dao";
		String packageName = "/com/example/codegenerate" + "/" + targetModel;
		String modelDir = userDir + "/src/main/resources/model";
		String targetDir = userDir + "/src/test/java" + packageName;
		String modelName = "DaoModel.ftl";
		String materialName = "test";
		String targetName = captureName(materialName) + "Dao.java";
		ModelData modelData = new ModelData.ModelDataBuilder().atModelDir(modelDir).atPackageName(packageName.substring(1).replace("/", "."))
				.atModelName(modelName).atTargetDir(targetDir).atTargetModel(targetModel)
				.atTargetName(targetName).atAuthor("roll1").atMaterialName(materialName).build();
		generate(modelData);*/

	/*List<Model> attr_list = new ArrayList<>();
	attr_list.add(new Model("id", "Long","ID"));
	attr_list.add(new Model("name", "String","名称"));
	attr_list.add(new Model("age", "Integer","年龄"));
	attr_list.add(new Model("hobby", "List<String>","习惯"));

	String userDir = System.getProperty("user.dir");
	String targetModel = "pojo";
	String packageName = "/com/example/codegenerate" + "/" + targetModel;
	String modelDir = userDir + "/src/main/resources/model";
	String targetDir = userDir + "/src/test/java" + packageName;
	String modelName = "pojo.ftl";
	String materialName = "test";
	String targetName = captureName(materialName) + ".java";
	ModelData modelData = new ModelData.ModelDataBuilder().atModelDir(modelDir).atPackageName(packageName.substring(1).replace("/", "."))
			.atModelName(modelName).atTargetDir(targetDir).atTargetModel(targetModel).atModels(attr_list)
			.atTargetName(targetName).atAuthor("roll1").atMaterialName(materialName).build();
	generate(modelData);*/
	public static void main(String args[]) {
		/*String packageName = "com.example.codegenerate";
		String targetModel = ModelData.MODEL_DAO;
		String materialName = "simple";
		ModelData modelData = new ModelData(packageName, targetModel, materialName);
		generate(modelData);*/

		String packageName = "com.roll.gencode.test";
		String targetModel = ModelData.MODEL_DAO_IMPL;
		String materialName = "simple";
		ModelData modelData = new ModelData(packageName, targetModel, materialName);
		generate(modelData);
		//System.out.println(captureName("aAbc"));
	}
}
