package com.offcn.sellergoods.service;
import com.offcn.entity.GoodsVo;
import com.offcn.entity.PageResult;
import com.offcn.pojo.TbGoods;

import java.util.List;
/**
 * 商品服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(GoodsVo goodsVo);
	
	
	/**
	 * 修改
	 */
	public void update(TbGoods goods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbGoods findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @return
	 */
	public List<TbGoods> findPage(TbGoods goods);

	/*
	商品上下架
	 */
	public void updateMarketable(Long[] ids, String status);


}
