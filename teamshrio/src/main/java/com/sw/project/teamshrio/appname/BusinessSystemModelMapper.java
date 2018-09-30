package com.sw.project.teamshrio.appname;

import com.sw.project.teamshrio.appname.BusinessSystemModel;
import com.sw.project.teamshrio.util.PageModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessSystemModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessSystemModel record);

    int insertSelective(BusinessSystemModel record);

    BusinessSystemModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessSystemModel record);

    int updateByPrimaryKey(BusinessSystemModel record);

    List<BusinessSystemModel> queryPageBusinessSystems(PageModel pageModel,BusinessSystemModel businessSystemModel);
}