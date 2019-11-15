package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;

/*
品牌服务层接口
 */
public interface BrandService {

    /*
    全部品牌列表
     */
    public List<TbBrand> selectAll();

    /*
    分页列表
     */
    public PageResult selectPage(int pageNum,int pageSize);

    /*
    增加品牌
     */
    public void insert(TbBrand brand);

    /*
    修改品牌
     */
    public void update(TbBrand brand);

    /*
    根据ID获取实体
     */
    public TbBrand selectOne(Long id);

    /*
    删除品牌
     */
    public void delete(Long [] ids);

    /*
    条件查询
     */
    public PageResult findPage(TbBrand brand, int pageNum,int pageSize);
}
