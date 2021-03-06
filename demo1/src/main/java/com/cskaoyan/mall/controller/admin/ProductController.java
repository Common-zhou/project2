package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bo.GoodsInfoForCreate;
import com.cskaoyan.mall.bo.GoodsList;
import com.cskaoyan.mall.service.admin.ProductService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CatAndBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类简介：后台商品管理模块
 * 当前方法：
 *      1.显示商品类别和商家
 *      2.显示当前页商品信息
 *      3.搜索商品
 *      4.显示留言列表
 *      5.搜索留言
 *      6.留言回复
 *      7.删除留言
 *      8.新增商品
 *      9.获取商品详细信息
 *      10.修改商品
 * 创建时间: 2019-09-30 14:06
 *
 * @author EGGE
 */
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * 处理请求：显示商品类别和商家
     * 方法用途：返回商品类别和商家列表
     * 操作简介：将返回值封装到CatAndBrandVo类中
     *
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-09-30 16:17:23
     **/
    @RequestMapping("/admin/goods/catAndBrand")
    public BaseRespVo catAndBrand() {
        List<CatAndBrandVo> brands = productService.findAllBrandToVo();
        List<CatAndBrandVo> categories = productService.findAllCategoriesToVoByLevel();
/*        无法自动封装为date.brands和date.categories
        List<List> dateList=new ArrayList<List>();
        dateList.add(brands);
        dateList.add(categories);
        BaseRespVo success = BaseRespVo.success(dateList);
        return success;*/
        Map date = new HashMap();
        date.put("brandList", brands);
        date.put("categoryList", categories);
        return BaseRespVo.success(date);
    }

    /**
     * 处理请求：显示当前页商品信息/搜索商品
     * 方法用途：返回商品列表
     * 操作简介：根据传入参数返回当前页商品
     *
     * @author EGGE
     * @date 2019-10-04 15:43:12
     * @param page 分页信息 内封装搜索数据
     * @return 返回给前端的数据
     **/
    @RequestMapping("admin/goods/list")
    public BaseRespVo productList(GoodsPage page) {
       /* 使用心得分页插件会更简单
        List<Goods> items=productService.findGoodsByPage(page);
        Integer total=productService.findAmountOfGoods();
        if(total==null){
            total=0;
        }
        Map data=new HashMap();
        data.put("total",total);
        data.put("items",items);
        return BaseRespVo.success(data);*/
        GoodsList data = productService.findGoodsByPage(page);
        return BaseRespVo.success(data);
    }

    /**
     * 处理请求：显示留言列表/搜索留言
     * 方法用途：返回留言列表
     * 操作简介：根据传入参数搜索留言
     *
     * @param page 留言分页信息
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-01 00:24:55
     **/
    @RequestMapping("admin/comment/list")
    public BaseRespVo productList(CommentsPage page) {
 /*       int i;
        if (page.getUserId() != null) {
            try {//尝试将userId转化成int，失败则返回error信息
                i = Integer.parseInt(page.getUserId());
                page.setUserid(i);
            } catch (Exception e) {
                return BaseRespVo.error(null, 402, "参数错误");
            }
        }
        if (page.getValueId() != null) {
            try {//尝试将valueId转化成int，失败则返回error信息
                i = Integer.parseInt(page.getValueId());
                page.setValueid(i);
            } catch (Exception e) {
                return BaseRespVo.error(null, 402, "参数错误");
            }
        }*/
        CommentsList data = productService.findCommentsByPage(page);
        return BaseRespVo.success(data);
    }

    /**
     * 处理请求：留言回复
     * 方法用途：回复留言
     * 操作简介：对留言进行合法性判断并回复
     *
     * @param commitReplyRequest 回复的留言和id
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-01 00:24:48
     **/
    @RequestMapping("admin/order/reply")
    public BaseRespVo replyComment(@RequestBody CommitReplyRequest commitReplyRequest) {
        Integer commentId = commitReplyRequest.getCommentId();
        String content = commitReplyRequest.getContent();
        if (commentId == null || content == null)
            return BaseRespVo.error(null, 402, "参数错误");
        Comment comment = productService.findCommentById(commentId);
        if (comment == null || comment.getDeleted())//deleted代表逻辑删除
            return BaseRespVo.error(null, 404, "未能找到该留言，请检查参数");
        if (comment.getContent() != null)
            return BaseRespVo.error(null, 622, "订单商品已回复!");
        productService.updateComment(comment);
        //未能获取成功时数据，后考虑修改
        return BaseRespVo.success(null);

    }

    /**
     * 处理请求：删除留言
     * 方法用途：删除指定的商品留言
     * 操作简介：逻辑删除指定商品留言
     *
     * @param comment 商品留言信息
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-02 16:40:27
     **/
    @RequestMapping("admin/comment/delete")
    public BaseRespVo deleteComment(@RequestBody Comment comment) {
        productService.deleteCommentById(comment);
        return BaseRespVo.success(null);
    }
    /**
     * 处理请求：删除商品
     * 方法用途：删除指定的商品
     * 操作简介：逻辑删除指定商品
     *
     * @param comment 商品信息
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-02 16:40:27
     **/
    @RequestMapping("admin/goods/delete")
    public BaseRespVo deleteGoods(@RequestBody Goods goods) {
        if(goods!=null&&goods.getId()!=null){
        productService.deleteGoodsByid(goods.getId());
        return BaseRespVo.success(null);}
        return BaseRespVo.error(null,403,"参数有误");
    }

    /**
     * 处理请求：新增商品
     * 方法用途：用于新建商品
     * 操作简介：接收各种参数并存入数据库
     *
     * @param goodsInfo 封装了商品参数的请求头
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-03 14:39:52
     **/
    @RequestMapping("admin/goods/create")
    public BaseRespVo addGoods(@RequestBody GoodsInfoForCreate goodsInfo) {
        Goods goods = goodsInfo.getGoods();
        if (goods.getGoodsSn() == null || goods.getName() == null)
            return BaseRespVo.error(null, 401, "带*为必填项！");
        goods.setAddTime(new Date());
        productService.addGoods(goods);
        Integer goodsId = goods.getId();
        if (goodsId != null) {
            List<GoodsAttribute> goodsAttributes = goodsInfo.getAttributes();
            List<GoodsProduct> goodsProducts = goodsInfo.getProducts();
            List<GoodsSpecification> goodsSpecifications = goodsInfo.getSpecifications();
            if (goodsAttributes != null)
                for (GoodsAttribute goodsAttribute : goodsAttributes) {
                    goodsAttribute.setGoodsId(goodsId);
                    goodsAttribute.setAddTime(new Date());
                    productService.addGoodsAttribute(goodsAttribute);
                }
            if (goodsSpecifications != null)
                for (GoodsSpecification goodsSpecification : goodsSpecifications) {
                    goodsSpecification.setGoodsId(goodsId);
                    goodsSpecification.setAddTime(new Date());
                    productService.addGoodsSpecification(goodsSpecification);
                }
            if (goodsProducts != null)
                for (GoodsProduct goodsProduct : goodsProducts) {
                    goodsProduct.setGoodsId(goodsId);
                    goodsProduct.setAddTime(new Date());
                    productService.addGoodsProduct(goodsProduct);
                }
        }
        return BaseRespVo.success(null);
    }

    /**
     * 处理请求：获取商品详细信息
     * 方法用途：获取商品详细信息
     * 操作简介：根据商品id找到所有有关数据
     *
     * @param id 商品id
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-03 14:46:22
     **/
    @RequestMapping("admin/goods/detail")
    public BaseRespVo getGoodsInfoByGoodsId(String id) {
        GoodsInfoForCreate goodsInfo = new GoodsInfoForCreate();
        int goodsId = 0;
        try {
            goodsId = Integer.parseInt(id);
        } catch (Exception e) {
            BaseRespVo.error(null, 402, "参数错误!");
        }
        Goods goods = productService.findGoodsById(goodsId);
        List<GoodsAttribute> attributes = null;
        List<GoodsProduct> products = null;
        List<GoodsSpecification> specifications = null;
        if (goods != null) {
            attributes = productService.findGoodsAttributesByGoodsId(goodsId);
            products = productService.findGoodsProductsByGoodsId(goodsId);
            specifications = productService.findGoodsSpecificationsByGoodsId(goodsId);
        }
        goodsInfo.setGoods(goods);
        goodsInfo.setAttributes(attributes);
        goodsInfo.setProducts(products);
        goodsInfo.setSpecifications(specifications);

        return BaseRespVo.success(goodsInfo);

    }

    /**
     * 处理请求：修改商品
     * 方法用途：修改商品信息
     * 操作简介：根据传入的信息修改数据库
     *
     * @param goodsInfo 商品信息
     * @return 返回给前端的数据
     * @author EGGE
     * @date 2019-10-03 16:57:10
     **/
    @RequestMapping("admin/goods/update")
    public BaseRespVo updateGoodsInfo(@RequestBody GoodsInfoForCreate goodsInfo) {
        Goods goods = goodsInfo.getGoods();
        if (productService.findGoodsById(goods.getId()) != null)
            productService.updateGoods(goods);
        List<GoodsAttribute> goodsAttributes = goodsInfo.getAttributes();
        List<GoodsProduct> goodsProducts = goodsInfo.getProducts();
        List<GoodsSpecification> goodsSpecifications = goodsInfo.getSpecifications();
        if (goodsAttributes != null) {
            List<GoodsAttribute> oldAttributes = productService.findGoodsAttributesByGoodsId(goods.getId());
            for (GoodsAttribute oldAttribute : oldAttributes) {
                if(oldAttribute!=null&&oldAttribute.getId()!=null) {
                    productService.deleteGoodsAttributeById(oldAttribute.getId());
                }
            }
            for (GoodsAttribute goodsAttribute : goodsAttributes) {
                if (productService.findGoodsAttributesById(goodsAttribute.getId()) != null) {
                    goodsAttribute.setUpdateTime(new Date());
                    productService.updateGoodsAttribute(goodsAttribute);
                }
            }
        }
        if (goodsSpecifications != null) {
            List<GoodsSpecification> oldSpecifications=productService.findGoodsSpecificationsByGoodsId(goods.getId());
            for (GoodsSpecification oldSpecification : oldSpecifications) {
                if(oldSpecification!=null&&oldSpecification.getId()!=null){
                productService.deleteGoodsSpecificationById(oldSpecification.getId());
            }
            }
            for (GoodsSpecification goodsSpecification : goodsSpecifications) {
                if (productService.findGoodsSpecificationById(goodsSpecification.getId()) != null) {
                    goodsSpecification.setUpdateTime(new Date());
                    productService.updateGoodsSpecification(goodsSpecification);
                }
            }
        }
        if (goodsProducts != null) {
            List<GoodsProduct> oldProducts=productService.findGoodsProductsByGoodsId(goods.getId());
            for (GoodsProduct oldProduct : oldProducts) {
                if(oldProduct!=null&&oldProduct.getId()!=null){
                    productService.deleteGoodsProductById(oldProduct.getId());
                }
            }
            for (GoodsProduct goodsProduct : goodsProducts) {
                if (productService.findGoodsProductsById(goodsProduct.getId()) != null) {
                    goodsProduct.setUpdateTime(new Date());
                    productService.updateGoodsProducts(goodsProduct);
                }
            }
        }
        return BaseRespVo.success(null);
    }
}
