package com.roll.gencode.generate;

import java.util.List;

/**
 * @author haozq
 * Date: 2018/7/4 下午4:56
 */
public class GenCommon {

	public GenCommon() {
	}

	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 导入包
	 */
	private List<String> imports;
	/**
	 * 描述
	 */
	private String classDesc;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 日期
	 */
	private String date;
	/**
	 * class/interface
	 */
	private String classKind;
	/**
	 * 名称
	 */
	private String className;
	/**
	 * function
	 */
	private List<Function> functions;


	private class Function {
		/**
		 * fundesc
		 */
		private String funDesc;
		/**
		 * public/private
		 */
		private String funAccess;
		/**
		 * 返回类型
		 */
		private String funReturn;
		/**
		 * 函数名称
		 */
		private String funName;
		/**
		 * 参数
		 */
		private List<Arg> args;

		public String getFunDesc() {
			return funDesc;
		}

		public void setFunDesc(String funDesc) {
			this.funDesc = funDesc;
		}

		public String getFunAccess() {
			return funAccess;
		}

		public void setFunAccess(String funAccess) {
			this.funAccess = funAccess;
		}

		public String getFunReturn() {
			return funReturn;
		}

		public void setFunReturn(String funReturn) {
			this.funReturn = funReturn;
		}

		public String getFunName() {
			return funName;
		}

		public void setFunName(String funName) {
			this.funName = funName;
		}

		public List<Arg> getArgs() {
			return args;
		}

		public void setArgs(List<Arg> args) {
			this.args = args;
		}
	}

	private class Arg {
		/**
		 * 参数类型
		 */
		private String argType;
		/**
		 * 参数名称
		 */
		private String argName;

		public String getArgType() {
			return argType;
		}

		public void setArgType(String argType) {
			this.argType = argType;
		}

		public String getArgName() {
			return argName;
		}

		public void setArgName(String argName) {
			this.argName = argName;
		}
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
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

	public String getClassKind() {
		return classKind;
	}

	public void setClassKind(String classKind) {
		this.classKind = classKind;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
}
