package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.common.UserIdContextHolder;
import com.ayz.reggie.pojo.ShoppingCart;
import com.ayz.reggie.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    //向购物车中添加一个商品

    @RequestMapping(value = "/front/page/index/addShopping.do")
    public R<ShoppingCart> addShopping(@RequestBody ShoppingCart shoppingCart){
        Long userId= UserIdContextHolder.getContextHolder();
        shoppingCart.setUserId(userId);
        //1:如果当前菜品或者套餐在该用户的购物车中，则在原来基础上分数加1
        //2: 如果不在直接加入购物车中
        ShoppingCart res=null;
        if(shoppingCart.getDishId()!=null){//是菜品
            LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(ShoppingCart::getUserId,userId).eq(ShoppingCart::getDishId,shoppingCart.getDishId());
            ShoppingCart cart = shoppingCartService.getOne(queryWrapper);
            if(cart==null){
                shoppingCartService.save(shoppingCart);
            }else{
                cart.setNumber(cart.getNumber()+1);
                shoppingCartService.updateById(cart);
            }
            res=cart;
        }else{//是套餐
            LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(ShoppingCart::getUserId,userId).eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            ShoppingCart cart = shoppingCartService.getOne(queryWrapper);
            if(cart==null){
                shoppingCartService.save(shoppingCart);
            }else{
                cart.setNumber(cart.getNumber()+1);
                shoppingCartService.updateById(cart);
            }
            res=cart;
        }
        return R.success(res);
    }

    //获取当前用户购物车内所有的商品

    @RequestMapping(value = "/front/page/index/getCartList.do")
    public R<List<ShoppingCart>> getCartList(){
        Long userId=UserIdContextHolder.getContextHolder();
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        //购物车内商品按创建时间先后进行排序
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> cartList = shoppingCartService.list(queryWrapper);
        return R.success(cartList);
    }

    //清空购物车内的商品

    @DeleteMapping(value = "/front/page/index/deleteAllShoppingCart.do")
    public R<String> deleteAllShoppingCart(){
        Long userId=UserIdContextHolder.getContextHolder();
        boolean delete = shoppingCartService.deleteByUserId(userId);
        if(delete){
            return R.success("清空购物车成功!");
        }else{
            return R.success("清空购物车失败!");
        }
    }

}
