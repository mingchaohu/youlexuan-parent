package com.offcn.user.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbUser;

import java.util.List;

/*
用户服务层接口
 */
public interface UserService {

    /*
    全部用户列表
     */
    public List<TbUser> selectAll();

    /*
    分页列表
     */
    public PageResult selectPage(int pageNum, int pageSize);

    /*
    增加用户
     */
    public void insert(TbUser user);

    /*
    修改用户
     */
    public void update(TbUser user);

    /*
    根据ID获取实体
     */
    public TbUser selectOne(Long id);

    /*
    删除用户
     */
    public void delete(Long[] ids);

    /*
    条件查询
     */
    public PageResult findPage(TbUser user, int pageNum, int pageSize);
}
