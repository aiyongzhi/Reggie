package com.ayz.reggie.service;

import com.ayz.reggie.DTO.DishDTO;
import com.ayz.reggie.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {

    /*
    * 拓展方法：根据菜品的id查询菜品的信息以及该菜品
    * 对应口味的id
    * */

    public DishDTO queryDishWithFlavorById(String id);
}
