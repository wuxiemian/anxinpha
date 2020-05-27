package com.anxinpha.item.service.impl;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.item.mapper.GoodsMapper;
import com.anxinpha.item.mapper.ShopMapper;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.item.pojo.Shop;
import com.anxinpha.item.service.ShopService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 获取所有的店铺集合
     * @return
     */
    @Override
    public List<Shop> getAllShops(String searchKey, String minDate, String maxDate) {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(searchKey)){
            criteria.andLike("shopName","%"+searchKey+"%");
        }
        if (StringUtils.isNotBlank(minDate)){
            criteria.andGreaterThanOrEqualTo("created",minDate);
        }
        if (StringUtils.isNotBlank(maxDate)){
            criteria.andLessThanOrEqualTo("created",maxDate);
        }
        return this.shopMapper.selectByExample(example);
    }

    /**
     * 获取店铺数量
     *
     * @return
     */
    @Override
    public int getShopCount() {
        return this.shopMapper.selectAll().size();
    }

    /**
     * 添加店铺
     *
     * @param shop
     * @return
     */
    @Override
    public void addShop(Shop shop) {
        if (StringUtils.isBlank(shop.getShopIcon())){
            shop.setShopIcon(null);
        }
        if (StringUtils.isBlank(shop.getShopDesc())){
            shop.setShopDesc(null);
        }
        shop.setCreated(new Date());
        this.shopMapper.insertSelective(shop);
    }

    /**
     * 修改店铺信息
     *
     * @param shop
     * @return
     */
    @Override
    public void updateShop(Shop shop) {
        if (StringUtils.isBlank(shop.getShopIcon())){
            shop.setShopIcon(null);
        }
        if (StringUtils.isBlank(shop.getShopDesc())){
            shop.setShopDesc(null);
        }
        this.shopMapper.updateByPrimaryKeySelective(shop);
    }

    /**
     * 删除店铺及其商品
     *
     * @param shopId
     * @return
     */
    @Override
    public void deleteShop(Long shopId) {
        Goods goods = new Goods();
        goods.setShopId(shopId);
        this.goodsMapper.delete(goods);
        this.shopMapper.deleteByPrimaryKey(shopId);
    }

    /**
     * 通过名获得店铺
     *
     * @param shopName
     * @return
     */
    @Override
    public Shop getShopByShopName(String shopName) {
        Shop shop = new Shop();
        shop.setShopName(shopName);
        return this.shopMapper.selectOne(shop);
    }
}
