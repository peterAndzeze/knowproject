package com.sw.project.teamshrio.appname;

import com.sw.project.teamshrio.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        businessSystemModelMapper.
    }
}