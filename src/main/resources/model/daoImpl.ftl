package ${packageName}.dao.impl;

import ${packageName}.dao.${materialName?cap_first}Dao;
import ${packageName}.pojo.${materialName?cap_first};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *  ${materialName?cap_first}Dao的实现类
 *
 *  @author ${author}
 *  Date: ${date}
 */
@Repository
public class ${materialName?cap_first}DaoImpl implements ${materialName?cap_first}Dao {

    @Autowired
    private ${materialName?cap_first}Mapper ${materialName?uncap_first}Mapper;

    @Override
    public ${materialName?cap_first} get(long id) {
        return null;
    }

    @Override
    public int delete(long id) {
        return 0;
    }

    @Override
    public long add(${materialName?cap_first} ${materialName?uncap_first}) {
        return 0L;
    }

    @Override
    public int update(${materialName?cap_first} ${materialName?uncap_first}) {
        return 0;
    }
}