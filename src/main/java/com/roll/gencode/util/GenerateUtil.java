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
}
