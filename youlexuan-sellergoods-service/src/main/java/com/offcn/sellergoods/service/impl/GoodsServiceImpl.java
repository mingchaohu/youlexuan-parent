package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.GoodsVo;
import com.offcn.entity.PageResult;
import com.offcn.mapper.*;
import com.offcn.pojo.TbGoods;
import com.offcn.pojo.TbGoodsDesc;
import com.offcn.pojo.TbGoodsExample;
import com.offcn.pojo.TbGoodsExample.Criteria;
import com.offcn.pojo.TbItem;
import com.offcn.sellergoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品服务实现层
 *
 * @author Administrator
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    TbGoodsDescMapper tbGoodsDescMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbSellerMapper tbSellerMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbGoods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(GoodsVo goodsVo) {

        TbGoods goods = goodsVo.getGoods();
        goods.setAuditStatus("0");
        goods.setIsDelete("0");
        goodsMapper.insert(goods);

        Long id = goods.getId();
        TbGoodsDesc goodsDesc = goodsVo.getGoodsDesc();
        goodsDesc.setGoodsId(id);
        tbGoodsDescMapper.insert(goodsDesc);

        for (TbItem tbItem : goodsVo.getItemList()) {
            String title = goodsVo.getGoods().getGoodsName();
            Map<String, Object> specMap = JSON.parseObject(tbItem.getSpec());
            for (String key : specMap.keySet()) {
                title = "" + specMap.get(key);
            }
            tbItem.setTitle(title);
            tbItem.setGoodsId(goodsVo.getGoods().getId());
            tbItem.setSellerId(goodsVo.getGoods().getSellerId());
            tbItem.setCategoryid(goodsVo.getGoods().getCategory3Id());
            tbItem.setCreateTime(new Date());
            tbItem.setUpdateTime(new Date());
            tbItem.setBrand(tbBrandMapper.selectByPrimaryKey(goodsVo.getGoods().getBrandId()).getName());
            tbItem.setCategory(tbItemCatMapper.selectByPrimaryKey(goodsVo.getGoods().getCategory3Id()).getName());
            tbItem.setSeller(tbSellerMapper.selectByPrimaryKey(goodsVo.getGoods().getSellerId()).getNickName());
            List<Map> imageList = JSON.parseArray(goodsVo.getGoodsDesc().getItemImages(), Map.class);
            if (imageList.size() > 0) {
                tbItem.setImage((String) imageList.get(0).get("url"));
            }
            tbItemMapper.insert(tbItem);

        }
    }


    /**
     * 修改
     */
    @Override
    public void update(TbGoods goods) {
        goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbGoods findOne(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
            tbGoods.setIsDelete("1");
            goodsMapper.updateByPrimaryKey(tbGoods);
        }
    }


    @Override
    public List<TbGoods> findPage(TbGoods goods) {
        TbGoodsExample example = new TbGoodsExample();
        Criteria criteria = example.createCriteria();
        if (goods != null) {
            if (goods.getSellerId() != null && goods.getSellerId().length() > 0) {
                criteria.andSellerIdEqualTo(goods.getSellerId());
            }
            if (goods.getAuditStatus() != null && goods.getAuditStatus().length() > 0) {
                criteria.andAuditStatusEqualTo(goods.getAuditStatus());
            }
            if (goods.getGoodsName() != null && goods.getGoodsName().length() > 0) {
                criteria.andGoodsNameLike('%' + goods.getGoodsName() + '%');
            }
            criteria.andIsDeleteIsNull();
        }
        List<TbGoods> tbGoods = goodsMapper.selectByExample(example);
        return tbGoods;
    }

    /*
    商品上下架
     */
    @Override
    public void updateMarketable(Long[] ids, String status) {
        for (Long id : ids) {
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
            tbGoods.setIsMarketable(status);
            goodsMapper.updateByPrimaryKey(tbGoods);
        }
    }

}
