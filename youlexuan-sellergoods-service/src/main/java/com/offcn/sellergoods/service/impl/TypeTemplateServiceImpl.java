package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.mapper.TbSpecificationMapper;
import com.offcn.mapper.TbSpecificationOptionMapper;
import com.offcn.mapper.TbTypeTemplateMapper;
import com.offcn.pojo.TbSpecificationOption;
import com.offcn.pojo.TbSpecificationOptionExample;
import com.offcn.pojo.TbTypeTemplate;
import com.offcn.pojo.TbTypeTemplateExample;
import com.offcn.sellergoods.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
模板服务层实现类
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    /*
   返回指定模板id的规格列表，包含规格选项
    */
    @Override
    public List<Map> findSpecList(Long id) {
        //查询模板
        TbTypeTemplate tbTypeTemplate = tbTypeTemplateMapper.selectByPrimaryKey(id);
        List<Map> list = JSON.parseArray(tbTypeTemplate.getSpecIds(), Map.class);
        for (Map map : list) {
            //查询规格选项列表
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(Long.parseLong(map.get("id").toString()));
            List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(example);
            map.put("tbSpecificationOptions", tbSpecificationOptions);
        }
        return list;
    }

    /*
        查询所有
         */
    @Override
    public List<TbTypeTemplate> selectAll() {
        return tbTypeTemplateMapper.selectByExample(null);
    }

    /*
         查询品牌和规格
        */
    @Override
    public Map selectBrandAndSpecification() {
        List<Map> brandList = tbBrandMapper.selectAllBrand();
        List<Map> specificationList = tbSpecificationMapper.selectAllSpecification();
        Map<String, Object> map = new HashMap();
        map.put("brandList", brandList);
        map.put("specificationList", specificationList);
        return map;
    }

    /*
      分页列表
     */
    @Override
    public PageResult selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /*
    增加模板
     */
    @Override
    public void insert(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.insert(tbTypeTemplate);
    }

    /*
    修改模板
     */
    @Override
    public void update(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
    }

    /*
    根据ID获取实体
     */
    @Override
    public TbTypeTemplate selectOne(Long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }

    /*
    删除模板
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbTypeTemplateMapper.deleteByPrimaryKey(id);
        }
    }

    /*
    条件查询
     */
    public PageResult findPage(int pageNum, int pageSize, TbTypeTemplate typeTemplate) {
        PageHelper.startPage(pageNum, pageSize);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if (typeTemplate != null && typeTemplate.getName() != null && typeTemplate.getName().length() > 0) {
            criteria.andNameLike("%" + typeTemplate.getName() + "%");
        }
        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
        PageResult pageResult = new PageResult();
        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

}
