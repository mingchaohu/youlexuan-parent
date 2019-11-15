package com.offcn.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbSeller;
import com.offcn.sellergoods.service.SellerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 商户controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbSeller> findAll() {
        return sellerService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return sellerService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param seller
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbSeller seller) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(seller.getPassword());
        seller.setPassword(password);
        try {
            sellerService.add(seller);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param seller
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbSeller seller) {
        try {
            sellerService.update(seller);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @return
     */
    @RequestMapping("/findOne")
    public TbSeller findOne() {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        return sellerService.findOne(sellerId);
    }

    /*
    修改密码
     */
    @RequestMapping("/updatePassword")
    public void updatePassword(Map passwordMap) {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        TbSeller tbSeller = sellerService.findOne(sellerId);
        String password=tbSeller.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        Object oldPassword = passwordMap.get("oldPassword");
        Object newPassword = passwordMap.get("newPassword");
        Object confirmPassword = passwordMap.get("confirmPassword");

        if(oldPassword!=null&&newPassword!=null&&confirmPassword!=null&&oldPassword!=""&&newPassword!=""&&confirmPassword!=""){

            passwordEncoder.matches(password,encode);
        }else {

        }
    }

    /**
     * 批量删除
     *
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(String[] sellerIds) {
        try {
            sellerService.delete(sellerIds);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSeller seller, int page, int rows) {
        return sellerService.findPage(seller, page, rows);
    }

}
