package com.roll.gencode.generate;

/**
 * Created by haozq
 * Date: 2018/6/29 下午4:00
 */
public class Model {
	private String name;
	private String type;
	private String desc;

	public Model(String name, String type, String desc) {
		this.type = type;
		this.name = name;
		this.desc = desc;
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
}
