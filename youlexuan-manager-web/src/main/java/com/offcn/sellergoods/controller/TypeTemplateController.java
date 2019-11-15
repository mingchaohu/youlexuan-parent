package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbTypeTemplate;
import com.offcn.sellergoods.service.TypeTemplateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
模板控制层
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    @Reference
    TypeTemplateService typeTemplateService;

    /*
    查询所有
     */
    @RequestMapping("/selectAll")
    public List<TbTypeTemplate> selectAll() {
        return typeTemplateService.selectAll();
    }

    /*
   查询品牌和规格
    */
    @RequestMapping("/selectBrandAndSpecification")
    public Map selectBrandAndSpecification() {
        return typeTemplateService.selectBrandAndSpecification();
    }


    /*
      分页列表
     */
    @RequestMapping("/selectPage")
    public PageResult selectPage(int page, int rows) {
        return typeTemplateService.selectPage(page, rows);
    }

    /*
   增加模板
    */
    @RequestMapping("/insert")
    public Result insert(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            typeTemplateService.insert(tbTypeTemplate);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /*
    修改模板
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            typeTemplateService.update(tbTypeTemplate);
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
    public TbTypeTemplate selectOne(Long id) {
        return typeTemplateService.selectOne(id);
    }

    /*
    删除模板
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            typeTemplateService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /*
    条件查询
     */
    @RequestMapping("/search")
    public PageResult findPage(int pageNum, int pageSize, @RequestBody TbTypeTemplate typeTemplate) {
        return typeTemplateService.findPage(pageNum, pageSize, typeTemplate);
    }
}
