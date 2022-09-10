package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Category;
import com.ayz.reggie.pojo.Dish;
import com.ayz.reggie.pojo.Setmeal;
import com.ayz.reggie.service.CategoryService;
import com.ayz.reggie.service.DishService;
import com.ayz.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setmealService;

    @RequestMapping(value = "/backend/page/category/addCategory.do")
    public R<String> addCategory(@RequestBody Category category){
        boolean save = categoryService.save(category);
        if(save){
            return R.success("添加分类成功!");
        }else{
            return R.error("添加分类失败!");
        }
    }

    /*
    * 分类数据的分页查询
    * */
    @RequestMapping(value = "/backend/page/category/queryCategoryForPage.do")
    public R<Page<Category>> queryCategoryForPage(Integer page,Integer pageSize){
        //准备分页条件构造器
        Page<Category> pageInfo=new Page<>(page,pageSize);
        //进行排序条件的构造
        //排序条件: 先按type排序，type相同按sort排序
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getType,Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    /*
    * 删除分类逻辑如下
    * 如果该分类下有菜品或套餐则不能删除该分类，否则可以删除
    * */
    @DeleteMapping("/backend/page/category/deleteCategoryByIds.do")
    public R<String> deleteCategoryById(Long ids){
        Category category = categoryService.getById(ids);
        int type=category.getType();
        boolean delete=false;
        if(type==1){
            LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Dish::getCategoryId, ids);
            Map<String, Object> dishs = dishService.getMap(queryWrapper);
            if(dishs==null||dishs.isEmpty()){
                delete=true;
            }
        }else{
            LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Setmeal::getCategoryId,ids);
            Map<String, Object> setmeals = setmealService.getMap(queryWrapper);
            if(setmeals==null||setmeals.isEmpty()){
                delete=true;
            }
        }
        if(delete){
            boolean remove = categoryService.removeById(ids);
            if(remove){
                return R.success("删除分类成功!");
            }else{
                return R.error("未知错误!");
            }
        }
        return R.error("该分类下还有商品,不能删除!");
    }

    /*
    * 根据分类的id修改分类的信息
    *
    * */
    @PutMapping(value = "/backend/page/category/updateCategoryById.do")
    public R<String> updateCategoryById(@RequestBody Category category){
        boolean update = categoryService.updateById(category);
        if(update){
            return R.success("修改分类信息成功!");
        }else{
            return R.error("修改分类信息失败!");
        }
    }
    /*
    * 获取所有的分类的名称
    * */

    @RequestMapping(value = "/backend/page/food/getCategoryListByType.do")
    public R<List<Category>> getCategoryListByType(String type){
        if(StringUtils.isEmpty(type)){
            return R.error("查询分类失败!");
        }
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id","name").eq("type",type);
        //还需要排序处理
        queryWrapper.orderByAsc("type").orderByDesc("update_time");
        List<Category> categoryList = categoryService.list(queryWrapper);
        return R.success(categoryList);
    }

    /*
    * 获取所有分类的数据
    *
    * */
    @RequestMapping("/front/page/index/queryAllCategory.do")
    public R<List<Category>> queryAllCategory(){
        List<Category> categoryList = categoryService.list();
        return R.success(categoryList);
    }
}
