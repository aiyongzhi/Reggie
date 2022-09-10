package com.ayz.reggie.controller;

import com.ayz.reggie.DTO.SetmealDTO;
import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Category;
import com.ayz.reggie.pojo.Setmeal;
import com.ayz.reggie.service.CategoryService;
import com.ayz.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SetmealController {
    @Autowired
    SetmealService setmealService;
    @Autowired
    CategoryService categoryService;
    /*
    * 添加套餐
    * */
    @RequestMapping(value = "/backend/page/combo/addSetmeal.do")
    @CacheEvict(value = "setmealList",key = "'categoryId:'+#setmealDTO.categoryId",condition = "#setmealDTO!=null")
    public R<String> addSetmeal(@RequestBody SetmealDTO setmealDTO){
        boolean b = setmealService.insertSetmealWithDishes(setmealDTO);
        if(b){
            return R.success("添加套餐成功!");
        }else{
            return R.error("添加套餐失败!");
        }
    }

    /*
    * 进行套餐信息的分页查询
    * */
    @RequestMapping(value = "/backend/page/combo/querySetmealByNameForPage.do")
    public R<Page<SetmealDTO>> querySetmealByNameForPage(int page,int pageSize,String name){
        //1: 分页信息
        Page<Setmeal> pageInfo=new Page<>(page,pageSize);
        //2: 构造条件查询器
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,queryWrapper);
        //3:将pageInfo中数据拷贝到setmealDTOPage
        Page<SetmealDTO> setmealDTOPage=new Page<>();
        BeanUtils.copyProperties(pageInfo,setmealDTOPage,"records");
        List<Setmeal> setmealList = pageInfo.getRecords();
        List<SetmealDTO> setmealDTOList=new ArrayList<>();
        for (Setmeal setmeal : setmealList) {
            Category category = categoryService.getById(setmeal.getCategoryId());
            String categoryName= null;
            if(category!=null){
                categoryName=category.getName();
            }
            SetmealDTO setmealDTO=new SetmealDTO();
            BeanUtils.copyProperties(setmeal,setmealDTO);
            setmealDTO.setCategoryName(categoryName);
            setmealDTOList.add(setmealDTO);
        }
        setmealDTOPage.setRecords(setmealDTOList);
        return R.success(setmealDTOPage);
    }

    //根据ids删除套餐
    @DeleteMapping(value = "/backend/page/combo/deleteSetmealByIds.do")
    @CacheEvict(value = "setmealList",allEntries = true)
    public R<String> deleteSetmealByIds(Long[] ids){
        List<String> messageList=new ArrayList<>();
        for (Long id : ids) {
            R<String> res = setmealService.deleteSetmealWithSetmealDishById(id);
            if(res.getCode()==0){
                messageList.add(res.getMsg());
            }
        }
        if(messageList.isEmpty()){
            return R.success("删除套餐成功!");
        }else{
            String message= Arrays.toString(messageList.toArray());
            message+=", 这些套餐正在售卖无法删除!";
            return R.error(message);
        }
    }
}
