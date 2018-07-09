package ${packageName}.service.impl;

import ${packageName}.dao.${materialName?cap_first}Dao;
import ${packageName}.service.${materialName?cap_first}Service;
import ${packageName}.pojo.${materialName?cap_first};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ${materialName?cap_first}Service的实现类
 *
 * @author ${author}
 * Date: ${date}
 */
@Repository
public class ${materialName?cap_first}ServiceImpl implements ${materialName?cap_first}Service {

    @Autowired
    private ${materialName?cap_first}Dao ${materialName?uncap_first}Dao;

    @Override
    public ${materialName?cap_first} get(long id) {
        return ${materialName?uncap_first}Dao.get(id);
    }

    @Override
    public int delete(long id) {
        return ${materialName?uncap_first}Dao.delete(id);
    }

    @Override
    public long add(${materialName?cap_first} ${materialName?uncap_first}) {
        return ${materialName?uncap_first}Dao.add(${materialName?uncap_first});
    }

    @Override
    public int update(${materialName?cap_first} ${materialName?uncap_first}) {
        return ${materialName?uncap_first}Dao.update(${materialName?uncap_first});
    }
}