package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.entity.SpecificationVo;
import com.offcn.pojo.TbSpecification;

import java.util.List;

/*
规格服务层接口
 */
public interface SpecificationService {

    /*
    全部规格列表
     */
    public List<TbSpecification> selectAll();

    /*
    分页列表
     */
    public PageResult selectPage(int pageNum, int pageSize);

    /*
    增加规格
     */
    public void insert(SpecificationVo SpecificationVo);

    /*
    修改规格
     */
    public void update(SpecificationVo SpecificationVo);

    /*
    根据ID获取实体
     */
    public SpecificationVo selectOne(Long id);

    /*
    删除规格
     */
    public void delete(Long[] ids);

    /*
    条件查询
     */
    public PageResult findPage(TbSpecification tbSpecification, int pageNum, int pageSize);
}
