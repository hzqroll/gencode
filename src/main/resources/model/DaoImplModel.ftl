package ${packageName}.dao.impl;

import java.util.List;

import ${packageName}.dao.${materialName?cap_first}Dao;
import ${packageName}.pojo.${materialName?cap_first};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *  Automatic generated sample class implements from ${materialName?cap_first}
 *
 *  @author ${author}
 *  Date: ${date}
 */
@Repository
public class ${materialName?cap_first}DaoImpl implements ${materialName?cap_first}Dao {

    @Autowired
    private ${materialName?cap_first}Mapper ${materialName?uncap_first}Mapper;

    @Override
    ${materialName} get(long id) {
        return null;
    }

    @Override
    int delete(long id) {
        return 0;
    }

    @Override
    long add(${materialName?cap_first} ${materialName?uncap_first}) {
        return 0L;
    }

    @Override
    int update(${materialName?cap_first} ${materialName?uncap_first}) {
        return 0;
    }
}