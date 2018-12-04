package com.btc.springcloud.dao;

import com.btc.springcloud.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CREATE DATABASE cloudDB01 CHARACTER SET UTF8;
 * use cloudDB01;
 * CREATE TABLE dept
 * (
 * deptno BIGINT NOT NULL PRIMARY KEY auto_increment,
 * dname VARCHAR(60),
 * db_source VARCHAR(60)
 * );
 *
 * INSERT INTO dept(dname,db_source) VALUES('开发部',DATABASE());
 * INSERT INTO dept(dname,db_source) VALUES('人事部',DATABASE());
 * INSERT INTO dept(dname,db_source) VALUES('财务部',DATABASE());
 * INSERT INTO dept(dname,db_source) VALUES('市场部',DATABASE());
 * INSERT INTO dept(dname,db_source) VALUES('运维部',DATABASE());
 */
@Mapper
public interface DeptDao {
    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
