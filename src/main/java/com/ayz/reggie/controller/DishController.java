package com.ayz.reggie.controller;

import com.ayz.reggie.DTO.DishDTO;
import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Category;
import com.ayz.reggie.pojo.Dish;
import com.ayz.reggie.pojo.DishFlavor;
import com.ayz.reggie.service.CategoryService;
import com.ayz.reggie.service.DishFlavorService;
import com.ayz.reggie.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class DishController {

    @Autowired
    DishService dishService;
    @Autowired
    DishFlavorService dishFlavorService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping(value = "/backend/page/food/addDish.do")
    @Transactional
    public R<String> addDish(@RequestBody DishDTO dishDTO){
        if(dishDTO==null){
            return R.error("添加菜品失败!");
        }
        dishService.save(dishDTO);
        //mybatis-plus主键会有默认填充策略
        //主键的填充是在服务器端完成的，所以是先生成主键注入进对象，再将对象放进数据表中
        if(dishDTO.getFlavors()!=null){
            for (DishFlavor dishFlavor : dishDTO.getFlavors()) {
                dishFlavor.setDishId(dishDTO.getId());
            }
        }
        dishFlavorService.saveBatch(dishDTO.getFlavors());
        return R.success("添加菜品成功");
    }

    /*
    * 进行菜品页面 带条件的分页查询
    * */
    @RequestMapping(value = "/backend/page/food/queryDishByNameForPage.do")
    public R<Page<DishDTO>> queryDishByNameForPage(Integer page,Integer pageSize,String name){
        if(page==null||pageSize==null){
            return R.error("分页查询菜品失败!");
        }
        //1:准备分页条件构造器
        Page<Dish> pageInfo=new Page<>(page,pageSize);
        //2:准备查询条件构造器
        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(name),Dish::getName,name);

        //3：进行查询
        Page<DishDTO> dishDTOPage=new Page<>();
        dishService.page(pageInfo,queryWrapper);
        //4:完成对象的拷贝,把Page<Dish>中的参数数据全部拷贝到Page<DishDTO>
        BeanUtils.copyProperties(pageInfo,dishDTOPage,"records");
        //5:利用CategoryId关联查询出CategoryName
        List<Dish> dishList = pageInfo.getRecords();
        List<DishDTO> dishDTOList=new ArrayList<>();

        for (Dish dish : dishList) {
            DishDTO dishDTO=new DishDTO();
            BeanUtils.copyProperties(dish,dishDTO);
            Category category = categoryService.getById(dish.getCategoryId());
            if(category!=null){
                dishDTO.setCategoryName(category.getName());
            }
            dishDTOList.add(dishDTO);
        }
        dishDTOPage.setRecords(dishDTOList);
        return R.success(dishDTOPage);
    }

    /*
    *
    * 根据菜品的id查询菜品的信息以及该菜品对应的口味信息
    * */
    @RequestMapping("/backend/page/food/queryDishWithFlavorById.do")
    public R<DishDTO> queryDishWithFlavorById(String id){
        DishDTO dishDTO = dishService.queryDishWithFlavorById(id);
        return R.success(dishDTO);
    }

    /*
    * 修改菜品的信息以及该菜品对应口味的信息
    *
    * */
    @RequestMapping(value = "/backend/page/food/updateDish.do")
    @Transactional
    public R<String> updateDish(@RequestBody DishDTO dishDTO){
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        //1: 根据id修改菜品表的数据信息
        dishService.updateById(dish);
        //2: 根据id修改口味表的信息
        //先清除该菜品下所有口味，再添加回去
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDTO.getId());
        dishFlavorService.remove(queryWrapper);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishDTO.getId());
        }
        dishFlavorService.saveBatch(flavors);
        return R.success("修改菜品信息成功!");
    }

    /*
    * 根据菜品分类的id查询该分类下所有的菜品
    * */
    @RequestMapping(value = "/backend/page/food/queryDishsByCategoryId.do")
    public R<List<DishDTO>> queryDishsByCategoryId(Long categoryId){
        //base case
        if(categoryId==null){
            return R.error("根据菜品分类查询菜品失败!");
        }
        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper<>();
        //查询条件为categoryId和status
        //只需要查询出所有起售的商品
        queryWrapper.eq(Dish::getCategoryId,categoryId).eq(Dish::getStatus,1);
        List<Dish> dishList = dishService.list(queryWrapper);
        List<DishDTO> dishDTOList=new ArrayList<>();
        for (Dish dish : dishList) {
            DishDTO dishDTO=new DishDTO();
            BeanUtils.copyProperties(dish,dishDTO);
            dishDTOList.add(dishDTO);
        }
        return R.success(dishDTOList);
    }


}
