package com.sw.project.teamshrio.client.jdbctemplate;

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

    public <T> List<T> queryForList(String sql,Class t,int [] types,Object...params){
        return (List<T>) jdbcTemplate.query(sql,params,types,new BeanPropertyRowMapper<T>(t));
    }


}