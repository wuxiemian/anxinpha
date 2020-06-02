package com.anxinpha.item.service.impl;

import com.anxinpha.item.mapper.GoodsMapper;
import com.anxinpha.item.mapper.PanelContentMapper;
import com.anxinpha.item.mapper.PanelMapper;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.item.pojo.Panel;
import com.anxinpha.item.pojo.PanelContent;
import com.anxinpha.item.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private PanelMapper panelMapper;
    @Autowired
    private PanelContentMapper panelContentMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public List<Panel> getHome() {
        Example example = new Example(Panel.class);
        Criteria criteria = example.createCriteria();
        //条件查询
        criteria.andEqualTo("position",0);
        criteria.andEqualTo("status",1);
        example.setOrderByClause("sort_order");
        List<Panel> list = this.panelMapper.selectByExample(example);
        for(Panel panel:list){
            Example exampleContent = new Example(PanelContent.class);
            exampleContent.setOrderByClause("sort_order");
            Criteria criteriaContent = exampleContent.createCriteria();
            //条件查询
            criteriaContent.andEqualTo("panelId",panel.getId());
            List<PanelContent> contentList = this.panelContentMapper.selectByExample(exampleContent);
            for(PanelContent content:contentList){
                if(content.getProductId() != null){
                    Goods goods= this.goodsMapper.selectByPrimaryKey(content.getProductId());
                    content.setProductName(goods.getTitle());
                    content.setSalePrice(goods.getPrice().divide(BigDecimal.valueOf(100)));
                    content.setSubTitle(goods.getSellPoint());
                }
            }

            panel.setPanelContents(contentList);
        }
        return list;
    }

}
