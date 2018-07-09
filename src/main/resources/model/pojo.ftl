package ${packageName}.pojo;

import java.util.Date;

/**
 *  Automatic generated sample interface for ${materialName}
 *
 *  @author ${author}
 *  Date: ${date}
 */
public class ${materialName?cap_first} {

<#list models as model>
    /**
     * ${model.desc}
     */
    private ${model.type} ${model.name};

</#list>

<#list models as model>
    /**
     * 设置${model.desc}
     *
     * @param ${model.name} ${model.desc}
     */
    public void set${model.name?cap_first}(${model.type} ${model.name}){
        this.${model.name} = ${model.name};
    }

    /**
     * 获取${model.desc}
     *
     * @return 返回${model.name}
     */
    public ${model.type} get${model.name?cap_first}(){
        return this.${model.name};
    }

</#list>
}