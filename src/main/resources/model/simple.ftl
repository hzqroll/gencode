package ${packageName};

import java.util.List;

/**
 *  @author ${author}
 *  Date: ${DATE}
 */
public class ${className} {

    <#list attrs as attr>
    /**
     * ${attr.desc}
     */
    private ${attr.type} ${attr.name};

    </#list>

    <#list attrs as attr>
    /**
     * 设置${attr.desc}
     *
     * @param ${attr.name} ${attr.desc}
     */
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
        this.${attr.name} = ${attr.name};
    }

    /**
     * 获取 ${attr.desc}
     *
     * @return 返回${attr.name}
     */
    public ${attr.type} get${attr.name?cap_first}(){
        return this.${attr.name};
    }

    </#list>
}