package com.ayz.reggie.service;

import com.ayz.reggie.pojo.AddressBook;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AddressBookService extends IService<AddressBook> {

    //设置默认地址
    public boolean setDefaultAddress(Long id);
}
