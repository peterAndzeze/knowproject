package com.sw.project.teamshrio.appname;

import com.sw.project.teamshrio.appname.BusinessSystemModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessSystemModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessSystemModel record);

    int insertSelective(BusinessSystemModel record);

    BusinessSystemModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessSystemModel record);

    int updateByPrimaryKey(BusinessSystemModel record);
}