package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.entity.SpecificationVo;
import com.offcn.mapper.TbSpecificationMapper;
import com.offcn.mapper.TbSpecificationOptionMapper;
import com.offcn.pojo.TbSpecification;
import com.offcn.pojo.TbSpecificationExample;
import com.offcn.pojo.TbSpecificationOption;
import com.offcn.pojo.TbSpecificationOptionExample;
import com.offcn.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
规格服务层实现类
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    /*
    全部规格列表
     */
    @Override
    public List<TbSpecification> selectAll() {
        return tbSpecificationMapper.selectByExample(null);
    }

    /*
    分页列表
   */
    @Override
    public PageResult selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) tbSpecificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /*
     增加规格
    */
    @Override
    public void insert(SpecificationVo specificationVo) {
        //增加tb_specification表数据
        TbSpecification specification = specificationVo.getSpecification();
        tbSpecificationMapper.insert(specification);
        Long id = specification.getId();
        //增加tb_specification_option表数据
        List<TbSpecificationOption> specificationOptionList = specificationVo.getSpecificationOption();
        for (TbSpecificationOption specificationOption : specificationOptionList) {
            specificationOption.setSpecId(id);
            tbSpecificationOptionMapper.insert(specificationOption);
        }
    }

    /*
     修改规格
    */
    @Override
    public void update(SpecificationVo specificationVo) {
        //修改tb_specification表数据
        TbSpecification specification = specificationVo.getSpecification();
        tbSpecificationMapper.updateByPrimaryKey(specification);
        //修改tb_specification_option表数据,先将原数据删除再进行添加新数据
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        Long id = specification.getId();
        criteria.andSpecIdEqualTo(id);
        tbSpecificationOptionMapper.deleteByExample(example);
        List<TbSpecificationOption> specificationOptionList = specificationVo.getSpecificationOption();
        for (TbSpecificationOption specificationOption : specificationOptionList) {
            specificationOption.setSpecId(id);
            tbSpecificationOptionMapper.insert(specificationOption);
        }
    }

    /*
     根据ID获取实体
    */
    @Override
    public SpecificationVo selectOne(Long id) {
        //查询tb_specification表数据
        TbSpecification tbSpecification = tbSpecificationMapper.selectByPrimaryKey(id);
        //查询tb_specification_option表数据
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(example);
        SpecificationVo specificationVo = new SpecificationVo();
        specificationVo.setSpecification(tbSpecification);
        specificationVo.setSpecificationOption(tbSpecificationOptions);
        return specificationVo;
    }

    /*
    删除规格
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //删除tb_specification表数据
            tbSpecificationMapper.deleteByPrimaryKey(id);
            //删除tb_specification_option表数据
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            tbSpecificationOptionMapper.deleteByExample(example);
        }
    }

    /*
    条件搜索
     */
    @Override
    public PageResult findPage(TbSpecification tbSpecification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if (tbSpecification != null) {
        if (tbSpecification.getSpecName() != null && tbSpecification.getSpecName().length() > 0) {
            criteria.andSpecNameLike("%" + tbSpecification.getSpecName() + "%");
        }
    }
    Page<TbSpecification> page = (Page<TbSpecification>) tbSpecificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
}
}
