package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.entity.SpecificationVo;
import com.offcn.pojo.TbSpecification;
import com.offcn.sellergoods.service.SpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
规格控制层
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Reference
    private SpecificationService specificationService;


    /*
    全部规格列表
     */
    @RequestMapping("/selectAll")
    public List<TbSpecification> selctAll() {
        return specificationService.selectAll();
    }

    /*
    分页列表
     */
    @RequestMapping("/selectPage")
    public PageResult selectPage(int pageNum, int pageSize) {
        return specificationService.selectPage(pageNum, pageSize);
    }

    /*
     增加规格
    */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SpecificationVo specificationVo) {
        try {
            specificationService.insert(specificationVo);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /*
    修改规格
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SpecificationVo specificationVo) {
        try {
            specificationService.update(specificationVo);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /*
    根据ID获取实体
     */
    @RequestMapping("/selectOne")
    public SpecificationVo selectOne(Long id) {
        return specificationService.selectOne(id);
    }

    /*
    删除规格
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            specificationService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }

    /*
    条件搜索
     */
    @RequestMapping("/search")
    public PageResult search(int page, int rows, @RequestBody TbSpecification tbSpecification) {
        return specificationService.findPage(tbSpecification, page, rows);
    }
}
