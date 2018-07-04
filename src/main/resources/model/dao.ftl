package ${packageName}.dao;

import ${packageName}.pojo.${materialName?cap_first};

/**
 * 自动生成的Dao层接口
 *
 * @author ${author}
 * Date: ${date}
 */
public interface ${materialName?cap_first}Dao {

    /**
     * 返回查询出的 {@link ${materialName?cap_first}}
     *
     * @param id 主键ID
     * @return {@link ${materialName?cap_first}}
     */
    ${materialName?cap_first} get(long id);

    /**
     * 返回插入生成的主键ID
     *
     * @param ${materialName?uncap_first} ${materialName?uncap_first}
     * @return id
     */
    long add(${materialName?cap_first} ${materialName?uncap_first});

    /**
     * 返回删除的行数
     *
     * @param id 主键ID
     * @return delete amount
     */
    int delete(long id);

    /**
     * 返回更新的行数
     *
     * @param ${materialName?uncap_first} ${materialName?uncap_first}
     * @return update amount
     */
    int update(${materialName?cap_first} ${materialName?uncap_first});
}