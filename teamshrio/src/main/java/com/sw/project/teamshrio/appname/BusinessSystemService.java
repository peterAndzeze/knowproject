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
    public PageModel queryPageBusinessSystem(PageModel pageModel,BusinessSystemModel businessSystemModel){
        int count=businessSystemModelMapper.rowCount(pageModel,businessSystemModel);
        if(count>0){
            List<BusinessSystemModel> businessSystemModels=businessSystemModelMapper.queryPageBusinessSystems(pageModel,businessSystemModel);
            pageModel.setRecords(businessSystemModels);
        }
        pageModel.setRowCount(count);
        return pageModel;
    }
}
