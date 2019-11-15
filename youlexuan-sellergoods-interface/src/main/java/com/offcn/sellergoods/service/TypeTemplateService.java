package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

/*
模板服务层接口
 */
public interface TypeTemplateService {

    /*
    返回指定模板id的规格列表，包含规格选项（根据模板id查询规格列表spec_ids）
     */
    public List<Map> findSpecList(Long id);

    /*
    查询所有
     */
    public List<TbTypeTemplate> selectAll();

    /*
    查询品牌和规格
     */
    public Map selectBrandAndSpecification();

    /*
    分页列表
     */
    public PageResult selectPage(int pageNum, int pageSize);

    /*
    增加模板
     */
    public void insert(TbTypeTemplate tbTypeTemplate);

    /*
    修改模板
     */
    public void update(TbTypeTemplate tbTypeTemplate);

    /*
    根据ID获取实体
     */
    public TbTypeTemplate selectOne(Long id);

    /*
    删除模板
     */
    public void delete(Long[] ids);

    /*
    条件查询
     */
    public PageResult findPage(int pageNum, int pageSize, TbTypeTemplate typeTemplate);

}
