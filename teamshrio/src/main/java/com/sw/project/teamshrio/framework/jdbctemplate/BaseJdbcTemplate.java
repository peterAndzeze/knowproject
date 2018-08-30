package com.sw.project.teamshrio.framework.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sw on 2018/8/5.
 */
public class BaseJdbcTemplate<T extends Serializable> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 返回单个对象
     * @param sql
     * @param t
     * @param <T>
     * @return
     */
    public <T> T queryForObject(String sql,Class<T> t){
        return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<T>(t));
    }

    /**
     * 根据参数返回单个对象
     * @param sql
     * @param t
     * @param params
     * @param <T>
     * @return
     */
    public <T> T queryForObject (String sql,Class<T> t,Object...params){
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<T>(t),params);
    }

    /**
     *
     * @param sql
     * @param t
     * @param <T>
     * @return
     */
    public  <T> List<T> queryForList(String sql,Class t){
        return (List<T>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<T>(t));
    }

    /**
     *
     * @param sql
     * @param t
     * @param types 参数数据类型
     * @param params 参数
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(String sql,Class t,int [] types,Object...params){
        return (List<T>) jdbcTemplate.query(sql,params,types,new BeanPropertyRowMapper<T>(t));
    }

    /**
     * 插入或者更新
     * @param sql
     * @return
     */
    public int executeSql(String sql){
        return jdbcTemplate.update(sql);
    }

    /**
     * 更新或者插入
     * @param sql
     * @param types
     * @param params
     * @return
     */
    public int executeSql(String sql,int [] types,Object[] params){
        return  jdbcTemplate.update(sql,params,types);
    }

    /**
     * 执行批量sql
     * @param sqls
     * @return
     */
    public int[] batchExecuteSql(String[] sqls){
        return  jdbcTemplate.batchUpdate(sqls);
    }

    /**
     * 执行批量sql
     * @param sql
     * @param params
     * @return
     */
    public int[] batchExecuteSql(String  sql,List<Object[]> params){
        return  jdbcTemplate.batchUpdate(sql,params);
    }





}