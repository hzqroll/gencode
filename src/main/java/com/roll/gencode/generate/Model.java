package com.roll.gencode.generate;

/**
 * Created by haozq
 * Date: 2018/6/29 下午4:00
 */
public class Model {
	private String name;
	private String type;
	private String desc;
	/**
	 * 数据库字段名称
	 */
	private String column;
	private String left = "#{";
	private String right = "}";

	public Model() {
	}

	public Model(String name, String type, String desc) {
		this.type = type;
		this.name = name;
		this.desc = desc;
	}

	public Model(String name, String type, String desc, String column) {
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}
}
