package com.ayz.reggie.service.impl;

import com.ayz.reggie.common.UserIdContextHolder;
import com.ayz.reggie.mapper.AddressBookMapper;
import com.ayz.reggie.pojo.AddressBook;
import com.ayz.reggie.service.AddressBookService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
@Slf4j
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
    @Override
    @Transactional
    public boolean setDefaultAddress(Long id) {
        //id:地址记录的id
        //1； 将当前用户管理的所有地址is_default全部修改为0
        Long userId = UserIdContextHolder.getContextHolder();
        LambdaUpdateWrapper<AddressBook> updateWrapper1=new LambdaUpdateWrapper<>();
        updateWrapper1.eq(AddressBook::getUserId,userId);
        updateWrapper1.set(AddressBook::getIsDefault,0);
        boolean b1 = this.update(updateWrapper1);
        //2: 根据id将指定地址设置为默认地址
        LambdaUpdateWrapper<AddressBook> updateWrapper2=new LambdaUpdateWrapper<>();
        updateWrapper2.eq(AddressBook::getId,id);
        updateWrapper2.set(AddressBook::getIsDefault,1);
        boolean b2 = this.update(updateWrapper2);
        return b1&b2;
    }
}
