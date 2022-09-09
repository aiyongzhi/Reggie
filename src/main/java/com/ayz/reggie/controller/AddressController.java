package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.common.UserIdContextHolder;
import com.ayz.reggie.pojo.AddressBook;
import com.ayz.reggie.service.AddressBookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
public class AddressController {

    @Autowired
    AddressBookService addressBookService;

    @RequestMapping(value = "/front/page/addAddress.do")
    public R<String> addAddress(@RequestBody AddressBook addressBook, HttpSession session) {
        addressBook.setUserId((Long) session.getAttribute("user"));
        boolean save = addressBookService.save(addressBook);
        if (save) {
            return R.success("新增地址成功!");
        } else {
            return R.error("新增地址失败!");
        }
    }

    //查询AddressBook表中的所有记录
    @RequestMapping(value = "/front/page/address/queryAllAddress.do")
    public R<List<AddressBook>> queryAllAddress() {
        List<AddressBook> addressBookList = addressBookService.list();
        return R.success(addressBookList);
    }

    //将地址管理列表中的某一个地址设置为默认地址
    //但注意,你只能设置唯一的一个默认地址

    @RequestMapping("/front/page/address/setDefaultAddress.do")
    public R<String> setDefaultAddress(@RequestBody AddressBook addressBook){
        Long id=addressBook.getId();
        boolean b = addressBookService.setDefaultAddress(id);
        if(b){
            return R.success("设置默认地址成功!");
        }
        return R.error("设置默认地址失败!");
    }

    //查询根据id查询单个地址的信息
    @RequestMapping("/front/page/address/queryAddressBookById.do")
    public R<AddressBook> queryAddressBookById(Long id){
        if(id==null){//base case
            return R.error("查询地址信息失败!");
        }
        AddressBook addressBook = addressBookService.getById(id);
        return R.success(addressBook);
    }

    //获取该用户的默认地址
    @RequestMapping("/front/page/address/getDefaultAddress.do")
    public R<AddressBook> getDefaultAddress(){
        Long userId= UserIdContextHolder.getContextHolder();
        LambdaQueryWrapper<AddressBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,userId);
        queryWrapper.eq(AddressBook::getIsDefault,1);
        AddressBook addressBook = addressBookService.getOne(queryWrapper);
        return R.success(addressBook);
    }
}