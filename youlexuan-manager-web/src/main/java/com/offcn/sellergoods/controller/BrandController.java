package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbBrand;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
品牌控制层
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /*
    全部品牌列表
     */
    @RequestMapping("/selectAll")
    public List<TbBrand> selectAll() {
        return brandService.selectAll();
    }

    /*
      分页列表
     */
    @RequestMapping("/selectPage")
    public PageResult selectPage(int page, int rows) {
        return brandService.selectPage(page, rows);
    }

    /*
   增加品牌
    */
    @RequestMapping("/insert")
    public Result insert(@RequestBody TbBrand brand){
        try {
            brandService.insert(brand);
            return new Result(true,"增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"增加失败");
        }
    }

    /*
    修改品牌
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    /*
    根据ID获取实体
     */
    @RequestMapping("/selectOne")
    public TbBrand selectOne(Long id){
        return brandService.selectOne(id);
    }

     /*
   删除品牌
    */
     @RequestMapping("/delete")
     public Result delete(Long[] ids){
         try {
             brandService.delete(ids);
             return new Result(true,"删除成功");
         } catch (Exception e) {
             e.printStackTrace();
             return new Result(true,"删除失败");
         }
     }

     /*
    条件查询
    */
     @RequestMapping("/search")
     public PageResult search(@RequestBody TbBrand brand, int page, int rows  ){
         return brandService.findPage(brand, page, rows);
     }

}
