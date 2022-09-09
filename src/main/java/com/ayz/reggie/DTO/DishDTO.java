package com.ayz.reggie.DTO;

import com.ayz.reggie.pojo.Dish;
import com.ayz.reggie.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class DishDTO extends Dish {
    //封装了口味集合
    private List<DishFlavor> flavors;

    //菜品分类名称
    private String categoryName;

    //菜品的份数
    private Integer copies;
}
