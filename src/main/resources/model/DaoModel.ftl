package ${packageName};

import ${packageName}.pojo.${materialName?cap_first};

/**
 *  Automatic generated sample interface for ${materialName?cap_first}
 *
 *  @author ${author}
 *  Date: ${date}
 */
public interface ${materialName?cap_first}Dao {

    /**
     * return {@link ${materialName?cap_first}}
     *
     * @param id 主键ID
     * @return {@link ${materialName?cap_first}}
     */
    ${materialName?cap_first} get(long id);

    /**
     * return auto generate id
     *
     * @param ${materialName?uncap_first} ${materialName?uncap_first}
     * @return id
     */
    long add(${materialName?cap_first} ${materialName?uncap_first});

    /**
     * return delete amount
     *
     * @param id 主键ID
     * @return delete amount
     */
    int delete(long id);

    /**
     * return update amount
     *
     * @param ${materialName?uncap_first} ${materialName?uncap_first}
     * @return update amount
     */
    int update(${materialName?cap_first} ${materialName?uncap_first});
}