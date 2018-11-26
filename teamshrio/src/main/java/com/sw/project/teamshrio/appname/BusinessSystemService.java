package com.sw.project.teamshrio.appname;

import com.sw.project.teamshrio.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务系统信息
 */
@Service
public class BusinessSystemService {
    @Autowired
    BusinessSystemModelMapper businessSystemModelMapper;

    /**
     * 查询业务系统信息
     * @param pageModel
     * @param businessSystemModel
     * @return
     */
    public PageModel queryPageBusinessSystem(PageModel pageModel, BusinessSystemModel businessSystemModel){
        int count=businessSystemModelMapper.rowCount(pageModel,businessSystemModel);
        if(count>0){
            List<BusinessSystemModel> businessSystemModels=businessSystemModelMapper.queryPageBusinessSystems(pageModel,businessSystemModel);
            pageModel.setRecords(businessSystemModels);
        }
        pageModel.setRowCount(count);
        return pageModel;
    }

    /**
     * 获取业务系统明细
     * @param
     * @return
     */
    public BusinessSystemModel getAppnameInfoById(Long id){
        return  businessSystemModelMapper.selectByPrimaryKey(id);
    }

    /**
     * 执行个更新
     * @param businessSystemModel
     */
    public void updateBusinessSystem(BusinessSystemModel businessSystemModel){
       businessSystemModelMapper.updateByPrimaryKeySelective(businessSystemModel);
    }

    /**
     * 执行插入
     * @param businessSystemModel
     */
    public void insertBusinessSystem(BusinessSystemModel businessSystemModel){
        businessSystemModelMapper.insertSelective(businessSystemModel);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteBusinessSystemById(Long id){
       businessSystemModelMapper.deleteByPrimaryKey(id);
    }
}
