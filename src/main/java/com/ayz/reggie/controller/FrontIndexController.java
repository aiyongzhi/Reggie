package com.ayz.reggie.controller;

import com.ayz.reggie.DTO.DishDTO;
import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Dish;
import com.ayz.reggie.pojo.DishFlavor;
import com.ayz.reggie.pojo.Setmeal;
import com.ayz.reggie.service.DishFlavorService;
import com.ayz.reggie.service.DishService;
import com.ayz.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FrontIndexController {
    @Autowired
    DishService dishService;

    @Autowired
    DishFlavorService dishFlavorService;
    @Autowired
    SetmealService setmealService;

    @Autowired
    @Qualifier("customRedisTemplate")
    RedisTemplate<String, Object> redisTemplate;
    /*
    * 根据菜品分类获取该分类下所有菜品的信息
    * */
    @RequestMapping(value = "/front/page/index/getDishListByCategoryId.do")
    public R<List<DishDTO>> getDishListByCategoryId(Long categoryId, int status){
        //先从redis中获取，redis中没有再向mysql获取
        String key="categoryId:"+String.valueOf(categoryId);
        List<DishDTO> dishDTOList= (List<DishDTO>) redisTemplate.opsForValue().get(key);
        if(dishDTOList!=null){
            return R.success(dishDTOList);
        }
        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper<>();
        //查询条件为categoryId和status
        //只需要查询出所有起售的商品
        queryWrapper.eq(Dish::getCategoryId,categoryId).eq(Dish::getStatus,status);
        List<Dish> dishList = dishService.list(queryWrapper);
        dishDTOList=new ArrayList<>();
        for (Dish dish : dishList) {
            DishDTO dishDTO=new DishDTO();
            BeanUtils.copyProperties(dish,dishDTO);
            //根据菜品的id去菜品口味表中，查出属于该菜品的所有口味
            LambdaQueryWrapper<DishFlavor> queryWrapper1=new LambdaQueryWrapper<>();
            queryWrapper1.eq(DishFlavor::getDishId,dish.getId());
            List<DishFlavor> flavors = dishFlavorService.list(queryWrapper1);
            dishDTO.setFlavors(flavors);
            dishDTOList.add(dishDTO);
        }
        redisTemplate.opsForValue().set(key,dishDTOList);
        return R.success(dishDTOList);
    }

    //获取套餐分类对应的套餐
    @RequestMapping(value = "/front/page/index/getSetmealByCategoryId.do")
    @Cacheable(value = "setmealList",key = "'categoryId:'+#categoryId")
    public R<List<Setmeal>> getSetmealByCategoryId(Long categoryId,int status){
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getCategoryId,categoryId).eq(Setmeal::getStatus,status);
        List<Setmeal> setmealList = setmealService.list(queryWrapper);
        return R.success(setmealList);
    }
}
