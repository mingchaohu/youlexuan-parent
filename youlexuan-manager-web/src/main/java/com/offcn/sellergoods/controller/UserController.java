package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbUser;
import com.offcn.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;


    /*
    全部用户列表
     */
    @RequestMapping("/selectAll")
    public List<TbUser> selectAll() {
        return userService.selectAll();
    }

    /*
      分页列表
     */
    @RequestMapping("/selectPage")
    public PageResult selectPage(int page, int rows) {
        return userService.selectPage(page, rows);
    }

    /*
   增加用户
    */
    @RequestMapping("/insert")
    public Result insert(@RequestBody TbUser user) {
        try {
            userService.insert(user);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /*
    修改用户
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbUser user) {
        try {
            userService.update(user);
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
    public TbUser selectOne(Long id) {
        return userService.selectOne(id);
    }

    /*
  删除用户
   */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            userService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }

    /*
   条件查询
   */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbUser user, int page, int rows) {
        return userService.findPage(user, page, rows);
    }
}
