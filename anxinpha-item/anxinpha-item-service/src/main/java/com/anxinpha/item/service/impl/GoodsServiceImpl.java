package com.anxinpha.item.service.impl;

import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.item.bo.GoodsBo;
import com.anxinpha.common.utils.BoUtils;
import com.anxinpha.item.bo.GoodsDet;
import com.anxinpha.item.mapper.CategoryMapper;
import com.anxinpha.item.mapper.GoodsMapper;
import com.anxinpha.item.mapper.ShopMapper;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.item.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据参数获取该店铺所有商品
     *
     * @param shopId
     * @param searchKey
     * @param minDate
     * @param maxDate
     * @return
     */
    @Override
    public List<Goods> getShopGoods(Long shopId, String searchKey, String minDate, String maxDate) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("shopId",shopId);
        if (StringUtils.isNotBlank(searchKey)){
            criteria.andLike("title","%"+searchKey+"%");
        }
        if (StringUtils.isNotBlank(minDate)){
            criteria.andGreaterThanOrEqualTo("updated",minDate);
        }
        if (StringUtils.isNotBlank(maxDate)){
            criteria.andLessThanOrEqualTo("updated",maxDate);
        }
        return this.goodsMapper.selectByExample(example);
    }

    /**
     * 获得店铺下商品数量
     *
     * @param shopId
     * @return
     */
    @Override
    public int getGoodsCount(Long shopId) {
        Goods goods = new Goods();
        goods.setShopId(shopId);
        return this.goodsMapper.select(goods).size();
    }

    /**
     * 删除商品
     *
     * @param goodsId
     */
    @Override
    public void deleteGoods(String goodsId) {
        String[] ids = goodsId.split(",");
        for (String id : ids) {
            this.goodsMapper.deleteByPrimaryKey(Long.parseLong(id));
        }


    }

    /**
     * 新建商品
     *
     * @param goods
     */
    @Override
    public void addGoods(Goods goods) {
        goods.setPrice(goods.getPrice().multiply(BigDecimal.valueOf(100)));
        goods.setDetail(goods.getDetail().replace("border=\"0\"","border=\"1\""));
        goods.setStatus((byte) 1);
        goods.setCreated(new Date());
        goods.setUpdated(goods.getCreated());
        this.goodsMapper.insertSelective(goods);
    }

    /**
     * 通过id查询商品
     *
     * @param id
     * @return
     */
    @Override
    public GoodsBo getGoodsBoById(Long id) {
        Goods goods = this.goodsMapper.selectByPrimaryKey(id);
        GoodsBo goodsBo = BoUtils.Goods2GoodsBo(goods);
        String[] cids = goodsBo.getCids().split(",");
        StringBuilder cname = new StringBuilder();
        for (String cid : cids) {
            if (cname.toString().equals("")){
                cname.append(this.categoryMapper.selectByPrimaryKey(cid).getName());
            }else {
                cname.append(",").append(this.categoryMapper.selectByPrimaryKey(cid).getName());
            }

        }
        goodsBo.setCname(cname.toString());
        return goodsBo;
    }

    @Override
    public void updateGoods(Goods goods) {
        goods.setUpdated(new Date());
        goods.setPrice(goods.getPrice().multiply(BigDecimal.valueOf(100)));
        this.goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void stopGoods(Long id) {
        this.goodsMapper.stopGoods(id);
    }

    @Override
    public void startGoods(Long id) {
        this.goodsMapper.startGoods(id);
    }

    @Override
    public List<GoodsBo> getGoods(Long userId,String searchKey, String minDate, String maxDate) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        if (userId!=null){
            Long shopId = this.goodsMapper.getShopIdByUserId(userId);
            criteria.andEqualTo("shopId",shopId);
        }
        if (StringUtils.isNotBlank(searchKey)){
            criteria.orLike("title","%"+searchKey+"%").
                    orLike("price","%"+searchKey+"%").
                    orLike("sellPoint","%"+searchKey+"%");
        }
        if (StringUtils.isNotBlank(minDate)){
            criteria.andGreaterThanOrEqualTo("updated",minDate);
        }
        if (StringUtils.isNotBlank(maxDate)){
            criteria.andLessThanOrEqualTo("updated",maxDate);
        }
        List<Goods> goods = this.goodsMapper.selectByExample(example);
        List<GoodsBo> goodsBos = goods.stream().map(good -> {
            GoodsBo goodsBo = BoUtils.Goods2GoodsBo(good);
            String shopName = this.shopMapper.getShopName(good.getShopId());
            goodsBo.setShopname(shopName);
            goodsBo.setPrice(goodsBo.getPrice().multiply(BigDecimal.valueOf(100)));
            return goodsBo;
        }).collect(Collectors.toList());
        return goodsBos;
    }

    @Override
    public int getAllGoodsCount() {
        return this.goodsMapper.selectAll().size();
    }

    @Override
    public GoodsDet getGoodsDetails(Long goodsId) {
        Goods goods=this.goodsMapper.selectByPrimaryKey(goodsId);
        GoodsDet goodsDet=new GoodsDet();
        goodsDet.setProductId(goodsId);
        goodsDet.setProductName(goods.getTitle());
        goodsDet.setSubTitle(goods.getSellPoint());
        if(goods.getLimitNum()!=null&&!goods.getLimitNum().toString().isEmpty()){
            goodsDet.setLimitNum(Long.valueOf(goods.getLimitNum()));
        }else{
            goodsDet.setLimitNum(Long.valueOf(goods.getNum()));
        }
        goodsDet.setSalePrice(goods.getPrice().divide(BigDecimal.valueOf(100)));


        goodsDet.setDetail(goods.getDetail());

        if(goods.getImage()!=null&&!goods.getImage().isEmpty()){
            String[] images =goods.getImage().split(",");
            goodsDet.setProductImageBig(images[0]);
            List<String> list = new ArrayList<>(Arrays.asList(images));
            goodsDet.setProductImageSmall(list);
        }
        return goodsDet;
    }

    @Override
    public Goods getGoodsById(Long id) {
        return this.goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult<GoodsBo> getFrontGoods(int page, int size, String sort, int priceGt, int priceLte, Long cid) {

        if(page<=0) {
            page = 1;
        }
        PageHelper.startPage(page,size);
        Example example = new Example(Goods.class);

        //判断条件
        if(sort.equals("1")){
            example.setOrderByClause("price asc");
        }else if(sort.equals("-1")){
            example.setOrderByClause("price desc");
        }else{
            example.setOrderByClause("created desc");
        }

        Example.Criteria criteria = example.createCriteria();
        if (priceGt != -1){
            criteria.andGreaterThanOrEqualTo("price",priceGt);
        }
        if (priceLte != -1){
            criteria.andLessThanOrEqualTo("price",priceLte);
        }
        if (cid!=-1){
            criteria.orLike("cids","%,"+cid+",%")
                    .orLike("cids",cid+",%")
                    .orLike("cids","%,"+cid);
        }
        List<Goods> goods = this.goodsMapper.selectByExample(example);
        List<GoodsBo> goodsBos = goods.stream().map(good -> {
            GoodsBo goodsBo = BoUtils.Goods2GoodsBo(good);
            String shopName = this.shopMapper.getShopName(good.getShopId());
            goodsBo.setShopname(shopName);
            goodsBo.setImageBig(good.getImages()[0]);
            return goodsBo;
        }).collect(Collectors.toList());
        PageInfo<GoodsBo> pageInfo = new PageInfo<>(goodsBos);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<Goods> getGoods1() {
        return this.goodsMapper.selectAll();
    }
}
