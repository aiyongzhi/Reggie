package com.ayz.reggie.service;

import com.ayz.reggie.DTO.SetmealDTO;
import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SetmealService extends IService<Setmeal> {
    public boolean insertSetmealWithDishes(SetmealDTO setmealDTO);

    //根据id删除套餐以及套餐下的菜品
    public R<String> deleteSetmealWithSetmealDishById(Long id);
}
