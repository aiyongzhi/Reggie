package com.ayz.reggie.service.impl;

import com.ayz.reggie.DTO.SetmealDTO;
import com.ayz.reggie.common.R;
import com.ayz.reggie.mapper.SetmealMapper;
import com.ayz.reggie.pojo.Setmeal;
import com.ayz.reggie.pojo.SetmealDish;
import com.ayz.reggie.service.SetmealDishService;
import com.ayz.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    /*
    * 添加套餐，并向套餐菜品关联表中同步添加数据
    * */
    @Autowired
    SetmealDishService setmealDishService;
    @Transactional
    public boolean insertSetmealWithDishes(SetmealDTO setmealDTO){
        //1: 向套餐表中插入数据
        boolean save = this.save(setmealDTO);
        //2: 向套餐菜品表中插入记录
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(String.valueOf(setmealDTO.getId()));
        }
        boolean saveBatch = setmealDishService.saveBatch(setmealDishes);
        return save&saveBatch;
    }

    @Override
    @Transactional
    public R<String> deleteSetmealWithSetmealDishById(Long id) {
        //1: 根据id删除套餐,注意套餐不是想删除就能够删除的
        //正在售卖中的套餐是无法删除的，只有isDelete字段为1才可以删除该套餐
        LambdaQueryWrapper<Setmeal> queryWrapper1=new LambdaQueryWrapper<>();
        queryWrapper1.eq(Setmeal::getId,id);
        Setmeal setmeal = this.getOne(queryWrapper1);
        if(setmeal.getIsDeleted()==0){
            return R.error(setmeal.getName());
        }
        boolean b1 = this.removeById(id);
        //2: 根据套餐id在SetmealDish表中根据套餐id进行删除
        LambdaQueryWrapper<SetmealDish> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,String.valueOf(id));
        boolean b2 = setmealDishService.remove(queryWrapper);
        return R.success(null);
    }
}
