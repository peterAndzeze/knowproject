package com.sw.project.teamshrio.orginfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrgInfoModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgInfoModel record);

    int insertSelective(OrgInfoModel record);

    OrgInfoModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgInfoModel record);

    int updateByPrimaryKey(OrgInfoModel record);

    /**
     * 组织机构集合
     * @param roleId
     * @param parentId
     * @return
     */
    List<OrgInfoModel> getOrgInfosByParentId(@Param("roleId")Long roleId,@Param("parentId")Long parentId);

    void deleteByParentId(Long parentId);
}