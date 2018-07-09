<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${materialName?cap_first}Dao">

    <resultMap id="${materialName?cap_first}Map" type="${packageName}.pojo.${materialName?cap_first}">
        <#list models as model>
         <result property="${model.name}" column="${model.column}" javaType="${model.type}"/>
        </#list>
    </resultMap>

    <select id="get" parameterType="long" resultMap="${materialName?cap_first}Map">
        select *
        from ${tableName}
        where id = ${r'#{id}'}
    </select>

    <delete id="delete" parameterType="long">
        delete *
        from ${tableName}
        where id = ${r'#{id}'}
    </delete>

    <insert id="add" parameterType="${packageName}.pojo.${materialName?cap_first}" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}(<#list models as model> ${model.column}<#sep>, </#list>)
             values(<#list models as model> ${r'${model.name}'}<#sep>, </#list>)

    </insert>

    <update id="update" parameterType="${packageName}.pojo.${materialName?cap_first}">
        update ${tableName}
        set
        <#list models as model>
            <#if model.name == "id" || model.name?lower_case?contains("create") >
            <#else>
                 <#if model?is_last >
            ${model.column} = ${r'${model.name}'}
                 <#else>
            ${model.column} = ${r'${model.name}'},
                 </#if>
            </#if>
        </#list>
        where id = ${r'#{id}'}
    </update>

</mapper>