package com.ayz.reggie.DTO;

import com.ayz.reggie.pojo.Setmeal;
import com.ayz.reggie.pojo.SetmealDish;
import lombok.Data;

import java.util.List;
@Data
public class SetmealDTO extends Setmeal {
    //这个套餐下的菜品
    private List<SetmealDish> setmealDishes;
    //套餐分类的名称
    private String categoryName;
}
